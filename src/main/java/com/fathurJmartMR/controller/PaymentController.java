package com.fathurJmartMR.controller;

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

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>
{
	public static final long DELIVERED_LIMIT_MS = 3000;
	public static final long ON_DELIVERY_LIMIT_MS = 3000;
	public static final long ON_PROGRESS_LIMIT_MS = 3000;
	public static final long WAITING_CONF_LIMIT_MS = 3000;
	
	public static @JsonAutowired(filepath = "../jmart/json/PaymentList.json", value = Payment.class) JsonTable<Payment> paymentTable;
	
	public static ObjectPoolThread<Payment> poolThread = new ObjectPoolThread<Payment>("Thread", PaymentController::timekeeper);
	
	@PostMapping("/create")
	Payment create
	(
		@RequestParam int buyerId,
		@RequestParam int productId,
		@RequestParam int productCount,
		@RequestParam String shipmentAddress,
		@RequestParam byte shipmentPlan
	)
	{
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
	
	@PostMapping("/{id}/accept")
	boolean accept
	(
		@RequestParam int id
	)
	{
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
	
	@PostMapping("/{id}/cancel")
	boolean cancel
	(
		@RequestParam int id
	)
	{
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
	
	@PostMapping("/{id}/submit")
	boolean submit
	(
		@RequestParam int id,
		@RequestParam String receipt
	)
	{
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
	
	private static boolean timekeeper(Payment payment) {
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
	
	@Override
	public JsonTable<Payment> getJsonTable() {
		return paymentTable;
	}
}
