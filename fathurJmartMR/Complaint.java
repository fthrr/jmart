package fathurJmartMR;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Write a description of class Complaint here.
 *
 * @author Fathurrahman Irwansa
 * @version 2 Oktober 2021
 */
public class Complaint extends Serializable
{
    public final Date date;
    public String desc;
    
    public Complaint(String desc){
        this.desc = desc;
        this.date = new Date();
    }
    
    /*public boolean read(String content){
        return false;
    }*/
    
    public String toString(){
        SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/yyyy");
        String fixedFormat = formattedDate.format(this.date);
        return
            "Complaint{date="+fixedFormat+", desc='"+this.desc+"'}";
    }
}
