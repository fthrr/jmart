package com.fathurJmartMR;

import java.util.Date;

import com.fathurJmartMR.dbjson.Serializable;

/**
 * Invoice Class untuk mengatur invoice pada jmart
 *
 * @author Fathurrahman Irwansa
 * @version 5 November 2021
 */

public abstract class Invoice extends Serializable
{
    /**
     * Instance variable untuk class invoice
     */
    public int buyerId;
    public int complaintId;
    public final Date date;
    public int productId;
    public Rating rating;
    
    
    /**
     * Enum class untuk status invoice
     *
     */
    public enum Status{
        WAITING_CONFIRMATION,
        CANCELLED,
        ON_PROGRESS,
        ON_DELIVERY,
        COMPLAINT,
        FINISHED,
        FAILED,
        DELIVERED
    }
    
    /**
     * Enum class untuk rating invoice
     *
     */
    public enum Rating{
        NONE,
        BAD,
        NEUTRAL,
        GOOD
    }
    
    
    /**
     * Record class untuk mendefinisikan protected variable
     *
     */
    class Record{
        public Date date;
        public String message;
        public Status status;
    }
    
    /**
     * Constructor untuk class invoice
     * @param buyerId	Id dari customer jmart
     * @param productId	Id dari product pada jmart
     */
    protected Invoice(int buyerId, int productId){
        this.buyerId = buyerId;
        this.productId = productId;
        this.date = new Date();
        this.rating = Rating.NONE;
        this.complaintId = -1;
    }
    
    
    /**
     * Abstact class getTotalPay
     * @param product	product object
     * @return nothing
     */
    public abstract double getTotalPay(Product product);
}
