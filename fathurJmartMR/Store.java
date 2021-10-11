package fathurJmartMR;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Write a description of class Store here.
 *
 * @author Fathurrahman Irwansa
 * @version 2 Oktober 2021
 */
public class Store extends Recognizable
{
    public static final String REGEX_PHONE = "^\\d{9,12}$";
    public static final String REGEX_NAME = "^[A-Z](?!.*(\\s)\\1).{4,20}$";
    public String name;
    public double balance;
    public String address;
    public String phoneNumber;
    
    public Store(String name, String address, String phoneNumber, double balance){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }
    
    
    /*@Override
    public boolean read(String content){
       return false;
    }*/
    
    public String toString(){
        return
            "Name: "+this.name+"\n"+
            "Address: "+this.address+"\n"+
            "Phone Number: "+this.phoneNumber+"\n"+
            "Balance: "+this.balance+"\n";
    }
    
    public boolean validate(){
        Pattern pattern = Pattern.compile(REGEX_NAME);
        Matcher matcher = pattern.matcher(name);
        Pattern pattern2 = Pattern.compile(REGEX_PHONE);
        Matcher matcher2 = pattern2.matcher(phoneNumber);
        return matcher.find() && matcher2.find();
    }
}
