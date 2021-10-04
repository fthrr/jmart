package fathurJmartMR;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Write a description of class Account here.
 *
 * @author Fathurrahman Irwansa
 * @version 21 September 2021
 */
public class Account extends Recognizable implements FileParser
{
   public static final String REGEX_EMAIL = "^[a-zA-Z0-9&_*~]+(?:\\.[a-zA-Z0-9&_*~]+)*@[A-Za-z0-9]{1}[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9-]+)*$";
   public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
   public String name;
   public String email;
   public String password;
   
   public Account(int id, String name, String email, String password){
       super(id);
       this.name = name;
       this.email = email;
       this.password = password;
   }
   
   @Override
   public boolean read(String content){
       return false;
   }
   
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
   /*public String toString(){
        return
            "Name: "+this.name+"\n"+
            "Weight: "+this.email+"\n"+
            "conditionUsed: "+this.password+"\n";
   }*/
}
