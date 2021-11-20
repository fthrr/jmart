package com.fathurJmartMR;

import java.util.Date;

import com.fathurJmartMR.dbjson.Serializable;

import java.util.ArrayList;

/**
 * Write a description of class Invoice here.
 *
 * @author Fathurrahman Irwansa
 * @version 2 Oktober 2021
 */
public abstract class Invoice extends Serializable
{
    public enum Status{
        CANCELLED,
        COMPLAINT,
        DELIVERED,
        FAILED,
        FINISHED,
        ON_DELIVERY,
        ON_PROGRESS,
        WAITING_CONFIRMATION;
    }
    
    public enum Rating{
        NONE,
        BAD,
        NEUTRAL,
        GOOD;
    }
    
    public final Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
    //public Status status;
    
    public ArrayList<Record> history = new ArrayList<Record>();
    
    public class Record{
        public Status status;
        public Date date;
        public String message;
    }
    
    protected Invoice(int buyerId, int productId){
        this.buyerId = buyerId;
        this.productId = productId;
        this.complaintId = -1;
        this.date = java.util.Calendar.getInstance().getTime();
        this.rating = Rating.NONE;
        //this.status = Status.WAITING_CONFIRMATION;
    }
    
    /*@Override
    public boolean read(String content){
        return false;
    }*/
    
    public abstract double getTotalPay(Product product);
}
