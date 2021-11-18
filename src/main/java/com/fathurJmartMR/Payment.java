package com.fathurJmartMR;

import java.util.ArrayList;
import java.util.Date;
import com.fathurJmartMR.Invoice.Record;

/**
 * Write a description of class Payment here.
 *
 * @author Fathurrahman Irwansa
 * @version 25 September 2021
 */
public class Payment extends Invoice
{
    public ArrayList<Record> history = new ArrayList<Record>();
	public int productCount;
    public Shipment shipment;

    public Payment(int buyerId, int productId, int productCount, Shipment shipment){
    	super(buyerId, productId);
    	this.productCount = productCount;
        this.shipment = shipment;
    }
    
    public static class Record{
    	public final Date date;
    	public String message;
    	public Status status;
    
    	public Record(Status status, String message) {
    		this.status = status;
    		this.message = message;
    		this.date = java.util.Calendar.getInstance().getTime();
    	}
    }

	@Override
    public double getTotalPay(Product product){
        return (productCount * Treasury.getAdjustedPrice(product.price, product.discount));
    }
}
