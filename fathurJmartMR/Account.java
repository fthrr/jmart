package fathurJmartMR;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Write a description of class Account here.
 *
 * @author Fathurrahman Irwansa
 * @version 21 September 2021
 */
public class Account extends Serializable
{
   public static final String REGEX_EMAIL = "^[a-zA-Z0-9&_*~]+(?:\\.[a-zA-Z0-9&_*~]+)*@[A-Za-z0-9]{1}[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9-]+)*$";
   public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
   //public double balance;
   public String name;
   public String email;
   public String password;
   public Store store;
   
   public Account(String name, String email, String password/*, double balance*/){
       this.name = name;
       this.email = email;
       this.password = password;
       //this.balance = balance;
   }
   
   /*@Override
   public boolean read(String content){
       return false;
   }*/
   
   public boolean validate(){
       Pattern emailPattern = Pattern.compile(REGEX_EMAIL);
       Matcher emailMatcher = emailPattern.matcher(this.email);
       Pattern passPattern = Pattern.compile(REGEX_PASSWORD);
       Matcher passMatcher = passPattern.matcher(this.password);
       boolean foundEmail = emailMatcher.find();
       boolean foundPass = passMatcher.find();
       if(foundEmail == true && foundPass == true){
           return true;
       }
       return false;
   }
   
   public String toString(){
        return
            "Name: "+this.name+"\n"+
            "Email: "+this.email+"\n"+
            "Password: "+this.password+"\n";
            //"Balance"+this.balance;
   }
}
