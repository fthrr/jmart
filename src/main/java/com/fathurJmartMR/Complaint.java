package com.fathurJmartMR;

import java.util.Date;

import com.fathurJmartMR.dbjson.Serializable;

import java.text.SimpleDateFormat;

/**
 * Class complaint untuk menampung complaint pada store jmart
 *
 * @author Fathurrahman Irwansa
 * @version 5 Desember 2021
 */
public class Complaint extends Serializable
{  
	/**
	 * Instance variable untuk class Complaint
	 */
	public Date date;
    public String desc;
    
    /**
     * Constructor untuk class complaint
     * @param desc	complaint description
     */
    public Complaint(String desc){
        this.desc = desc;
        this.date = new Date();
        System.out.println(date);
    }
    
    /**
     *Method untuk mengkonversi variable dalam kelas complaint menjadi string
     */
    @Override
    public String toString(){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return ("Complaint{date=" + df.format(date) + ", desc='" + desc + "'}");
    }
       
}
