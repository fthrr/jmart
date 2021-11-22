package com.fathurJmartMR.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fathurJmartMR.Invoice;
import com.fathurJmartMR.ObjectPoolThread;
import com.fathurJmartMR.Payment;
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
	
	public static ObjectPoolThread<Payment> poolThread = new ObjectPoolThread<Payment>(PaymentController::timekeeper);
	
	@PostMapping("/create")
	boolean create
	(
		@RequestParam int buyerId,
		@RequestParam int productId,
		@RequestParam int productCount,
		@RequestParam String shipmentAddress,
		@RequestParam byte shipmentPlan
	)
	{
		return false;
	}
	
	@PostMapping("/{id}/accept")
	boolean accept
	(
		@RequestParam int id
	)
	{
		return false;
	}
	
	@PostMapping("/{id}/cancel")
	boolean cancel
	(
		@RequestParam int id
	)
	{
		return false;
	}
	
	@PostMapping("/{id}/submit")
	boolean submit
	(
		@RequestParam int id,
		@RequestParam String receipt
	)
	{
		return false;
	}
	
	private static boolean timekeeper(Payment payment) {
		Payment.Record paymentHistory = payment.history.get(payment.history.size() - 1);
        long elapsed = Math.abs(paymentHistory.date.getTime() - (new Date()).getTime());

        if(paymentHistory.status == Invoice.Status.WAITING_CONFIRMATION && elapsed > WAITING_CONF_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Waiting"));
            return true;
        }
        else if(paymentHistory.status == Invoice.Status.ON_PROGRESS && elapsed > ON_PROGRESS_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Progress"));
            return true;
        } 
        else if(paymentHistory.status == Invoice.Status.ON_DELIVERY && elapsed > ON_DELIVERY_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "Delivery"));
            return false;
        }
        else if(paymentHistory.status == Invoice.Status.DELIVERED && elapsed > DELIVERED_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "Finish"));
            return true;
        }
        else {
        	return false;
        }
	}
	
	@Override
	public JsonTable<Payment> getJsonTable() {
		return paymentTable;
	}
}
