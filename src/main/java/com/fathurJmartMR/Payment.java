package com.fathurJmartMR;

import java.util.ArrayList;
import java.util.Date;

/**
 * Payment class untuk handle pembayaran pada jmart
 *
 * @author Fathurrahman Irwansa
 * @version 5 Desember 2021
 */
public class Payment extends Invoice
{
    /**
     * Instance Variable untuk class Payment
     */
    public ArrayList<Record> history = new ArrayList<>();
    public int productCount;
    public Shipment shipment;
    
    
    /**
     * Inner class Record untuk merekam aktivitas pembayaran pada jmart
     *
     */
    public static class Record{
        /**
         * Instance variable untuk inner class record
         */
        public final Date date;
        public String message;
        public Status status;
        
        /**
         * Constructor method untuk class record
         * @param status	invoice status
         * @param message	invoice message
         */
        public Record(Status status, String message){
            this.date = new Date();
            this.status = status;
            this.message = message;
        }
    }
    
    /**
     * Constructor method untuk class payment
     * 
     * @param buyerId		Buyer id pada jmart
     * @param productId		Product id pada jmart
     * @param productCount	Jumlah product
     * @param shipment		Shipment activity
     */
    public Payment(int buyerId, int productId, int productCount, Shipment shipment){
        super(buyerId, productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }
    
    /**
     * Override method getTotalPay untuk mendapatkan harga total
     * @return totalPay
     */
    @Override
    public double getTotalPay(Product product){
        return (productCount * Treasury.getAdjustedPrice(product.price, product.discount));
    }


    
}
