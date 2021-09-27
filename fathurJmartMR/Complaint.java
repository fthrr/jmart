package fathurJmartMR;


/**
 * Write a description of class Complaint here.
 *
 * @author Fathurrahman Irwansa
 * @version 25 Maret 2021
 */
public class Complaint extends Transaction implements FileParser
{
    public int paymentId;
    public String desc;
    
    public Complaint(int id, Payment payment, String desc){
        super(id, 0, 0);
        this.desc = desc;
    }
    
    public Complaint(int id, int buyerId, int storeId, int paymentId, String desc){
        super(id, buyerId, storeId);
        this.paymentId = paymentId;
        this.desc = desc;
    }
    
    public boolean validate(){
        return false;
    }
    
    public Transaction perform(){
        return null;
    }
    
    @Override
    public boolean read(String content){
        return false;
    }
}
