package fathurJmartMR;


/**
 * Write a description of class Complaint here.
 *
 * @author Fathurrahman Irwansa
 * @version 25 Maret 2021
 */
public class Complaint extends Recognizable implements FileParser
{
    public String date;
    public String desc;
    
    public Complaint(int id, String desc){
        super(id);
        this.desc = desc;
        this.date = "27 September 2021";
    }
    
    @Override
    public boolean read(String content){
        return false;
    }
}
