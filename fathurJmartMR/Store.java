package fathurJmartMR;


/**
 * Write a description of class Store here.
 *
 * @author Fathurrahman Irwansa
 * @version 25 September 2021
 */
public class Store extends Recognizable implements FileParser
{
    public String name;
    public String address;
    public String phoneNumber;
    
    public Store(int accountId, String name, String address, String phoneNumber){
        super(accountId);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    public Store(Account account, String name, String address, String phoneNumber){
        super(account.id);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public boolean read(String content){
       return false;
    }
    
    public String toString(){
        return
            "Name: "+this.name+"\n"+
            "Weight: "+this.address+"\n"+
            "conditionUsed: "+this.phoneNumber+"\n";
   }
}
