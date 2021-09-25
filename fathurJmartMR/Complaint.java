package fathurJmartMR;


/**
 * Write a description of class Complaint here.
 *
 * @author Fathurrahman Irwansa
 * @version 25 Maret 2021
 */
public class Complaint extends Recognizable implements FileParser
{
    public int paymentId;
    public String desc;
    
    /*public Complaint(int id, Payment payment, String desc){
        super(id);
    }*/
    
    public Complaint(int id, int buyerId, int storeId, int paymentId, String desc){
        super(id);
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
