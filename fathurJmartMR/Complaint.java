package fathurJmartMR;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Write a description of class Complaint here.
 *
 * @author Fathurrahman Irwansa
 * @version 2 Oktober 2021
 */
public class Complaint extends Recognizable implements FileParser
{
    public final Date date;
    public String desc;
    
    public Complaint(int id, String desc){
        super(id);
        this.desc = desc;
        this.date = new Date();
    }
    
    @Override
    public boolean read(String content){
        return false;
    }
}
