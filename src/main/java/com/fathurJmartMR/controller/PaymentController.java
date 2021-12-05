package com.fathurJmartMR.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fathurJmartMR.Account;
import com.fathurJmartMR.Invoice;
import com.fathurJmartMR.ObjectPoolThread;
import com.fathurJmartMR.Payment;
import com.fathurJmartMR.Product;
import com.fathurJmartMR.Shipment;
import com.fathurJmartMR.dbjson.JsonAutowired;
import com.fathurJmartMR.dbjson.JsonTable;

/**
 * Class untuk mengatur berbagai aktivitas terkait pembayaran
 * @author Fathurrahman Irwansa
 * @version 5 Desember 2021
 */
@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>
{
	/**
	 * Instance variable untuk paymnet controller
	 */
	public static final long DELIVERED_LIMIT_MS = 1000;
	public static final long ON_DELIVERY_LIMIT_MS = 1000;
	public static final long ON_PROGRESS_LIMIT_MS = 1000;
	public static final long WAITING_CONF_LIMIT_MS = 1000;
	public static @JsonAutowired(value = Payment.class, filepath = "C:\\OOP\\jmart\\json\\Payment.json") JsonTable<Payment> paymentTable;
	public static ObjectPoolThread<Payment> poolThread = new ObjectPoolThread<Payment>("Thread", PaymentController::timekeeper);


    /**
     * Method untuk menerima pembayaran
     * @param id	payment id
     * @return condition
     */
    @PostMapping("/{id}/accept")
    boolean accept(@PathVariable int id) {
        for(Payment payment : paymentTable){
            if(payment.id == id){
                if(payment.history.get(payment.history.size() - 1).status == Invoice.Status.WAITING_CONFIRMATION){
                    payment.history.add(new Payment.Record(Invoice.Status.ON_PROGRESS, "ON_PROGRESS"));
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method untuk membatalkan pembayaran
     * @param id	payment id
     * @return condition
     */
    @PostMapping("/{id}/cancel")
    boolean cancel(@PathVariable int id) {
        for(Payment payment : paymentTable){
            if(payment.id == id){
                if(payment.history.get(payment.history.size() - 1).status == Invoice.Status.WAITING_CONFIRMATION){
                    payment.history.add(new Payment.Record(Invoice.Status.CANCELLED, "CANCELLED"));
                    return true;
                }
            }
        }
         return false;
    }

    /**
     * Method untuk membuat pembayaran baru
     * @param buyerId			id Buyer
     * @param productId			id product
     * @param productCount		jumlah product
     * @param shipmentAddress	alamat pengiriman
     * @param shipmentPlan		jenis pengiriman
     * @return	newPayment
     */
    @PostMapping("/create")
    Payment create(@RequestParam int buyerId, @RequestParam int productId, @RequestParam int productCount, @RequestParam String shipmentAddress, @RequestParam byte shipmentPlan) {
        for(Account account : AccountController.accountTable){
            if(account.id == buyerId){
                for(Product product : ProductController.productTable){
                    if(product.accountId == productId){
                        Payment newPayment = new Payment(buyerId, productId, productCount, new Shipment(shipmentAddress, 0, shipmentPlan, null));
                        double totalPay = newPayment.getTotalPay(product);
                        if(account.balance >= totalPay){
                            account.balance -= totalPay;
                            newPayment.history.add(new Payment.Record(Invoice.Status.WAITING_CONFIRMATION, "WAITING_CONFIRMATION"));
                            paymentTable.add(newPayment);
                            poolThread.add(newPayment);
                            return newPayment;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Method untuk mendapatkan paymentTable
     */
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    /**
     * Method untuk menyelesaikan pembayaran
     * @param id		payment id
     * @param receipt	payment receipt
     * @return condition
     */
    @PostMapping("/{id}/submit")
    boolean submit(@PathVariable int id, String receipt) {
        for(Payment payment : paymentTable){
            if(payment.id == id){
                if(payment.history.get(payment.history.size() - 1).status == Invoice.Status.ON_PROGRESS){
                    if(!receipt.isBlank()){
                        payment.shipment.receipt = receipt;
                        payment.history.add(new Payment.Record(Invoice.Status.ON_DELIVERY, "ON_DELIVERY"));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Method untuk membatasi waktu pembayaran
     * @param payment	object payment
     * @return condition
     */
    private static Boolean timekeeper(Payment payment) {
        if (payment.history.isEmpty()) {
            return false;
        } else {
            Payment.Record record = payment.history.get(payment.history.size() - 1);
            long elapsed = System.currentTimeMillis() - record.date.getTime();
            if (record.status.equals(Invoice.Status.WAITING_CONFIRMATION) && (elapsed > WAITING_CONF_LIMIT_MS)) {
                record.status = Invoice.Status.FAILED;
                return true;
            } else if (record.status.equals(Invoice.Status.ON_PROGRESS) && (elapsed > ON_PROGRESS_LIMIT_MS)) {
                record.status = Invoice.Status.FAILED;
                return true;
            } else if (record.status.equals(Invoice.Status.ON_DELIVERY) && (elapsed > ON_PROGRESS_LIMIT_MS)) {
                record.status = Invoice.Status.DELIVERED;
                return true;
            } else if (record.status.equals(Invoice.Status.DELIVERED) && (elapsed > DELIVERED_LIMIT_MS)) {
                record.status = Invoice.Status.FINISHED;
                return true;
            } else {
                return false;
            }
        }
    }
}
