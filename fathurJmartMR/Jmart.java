package fathurJmartMR;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.google.gson.*;
/**
 * Write a description of class Jmart here.
 *
 * @author Fathurrahman Irwansa
 * @version 2 Oktober 2021
 */
class Jmart
{
	class Country
	{
		public String name;
		public int population;
		public List<String> listOfStates;
	}
	
    public static void main (String[] args){
    	String filepath = "../jmart/json/city.json";
    	Gson gson = new Gson();
    	try
    	{
    		BufferedReader br = new BufferedReader(new FileReader(filepath));
    		Country input = gson.fromJson(br, Country.class);
    		System.out.println("name: " + input.name);
    		System.out.println("population: " + input.population);
    		System.out.println("states: ");
    		input.listOfStates.forEach(state -> System.out.println(state));
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    }
    	//System.out.println("Hello from Eclipse!");
        /*Account account = new Account(333, "Fathurrahman", "fathur.asd@ui.ac.id", "Test123");
        System.out.println("Validate Account = "+account.validate());
        Complaint complaint = new Complaint(222, "Mantap");
        System.out.println(complaint.toString());*/

    /*public static Product createProduct(){
        return null;
    }

    public static Coupon createCoupon(){
        return null;
    }
    
    public static Shipment.Duration createShipmentDuration(){
        return null;
    }*/
}
