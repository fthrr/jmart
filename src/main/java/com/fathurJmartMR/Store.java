package com.fathurJmartMR;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fathurJmartMR.dbjson.Serializable;
/**
 * Store class untuk menghandle seluruh aktivitas terkait store
 *
 * @author Fathurrahman Irwansa
 * @version 5 Desember 2021
 */
public class Store extends Serializable
{
    /**
     * Instance variable untuk class store
     */
    public String name;
    public String address;
    public String phoneNumber;
    public double balance;
    public static final String REGEX_PHONE = "^\\d{9,12}$";
    public static final String REGEX_NAME = "^[A-Z](?!.*(\\s)\1).{4,20}$";
    
    /**
     * Constructor method untuk class Store
     * @param name			nama user
     * @param address		alamat user
     * @param phoneNumber	nomor telepon user
     * @param balance		balance user
     */
    public Store(String name, String address, String phoneNumber, double balance){
        this.name = name;
        this.address= address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }
    
    /**
     *Method untuk mengkonversi variable dalam kelas Store menjadi string
     */
    public String toString() {
        return "name: " + this.name + 
                "\naddress: " + this.address + 
                "\nphoneNumber: " + this.phoneNumber;
    }
    
    /**
     * Method untuk memvalidasi data store
     * @return condition
     */
    public boolean validate(){
    	Pattern pPhone = Pattern.compile(REGEX_PHONE);
        Pattern pName = Pattern.compile(REGEX_NAME);
        Matcher mPhone = pPhone.matcher(phoneNumber);
        Matcher mName = pName.matcher(name);
        if(mPhone.find() && mName.find()){
            return true;
        }else{
            return false;
        }
    }

}
