package com.fathurJmartMR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

import com.fathurJmartMR.dbjson.JsonDBEngine;
import com.fathurJmartMR.dbjson.JsonTable;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
/**
 * Write a description of class Jmart here.
 *
 * @author Fathurrahman Irwansa
 * @version 8 November 2021
 */

@SpringBootApplication
public class Jmart {
	
	/*public static long DELIVERED_LIMIT_MS = 3000;
	public static long ON_DELIVERY_LIMIT_MS = 3000;
	public static long ON_PROGRESS_LIMIT_MS = 3000;
	public static long WAITING_CONF_LIMIT_MS = 3000;*/
	
    public static void main (String[] args){
    	JsonDBEngine.Run(Jmart.class);
    	SpringApplication.run(Jmart.class, args);
    	Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
    	/*SpringApplication.run(Jmart.class, args);
    	try {
    		JsonTable<Payment> table = new JsonTable<>(Payment.class, "../jmart/json/randomPaymentList.json");
    		ObjectPoolThread<Payment> paymentPool = new ObjectPoolThread<Payment>("Thread-PP", Jmart::paymentTimekeeper);
    		paymentPool.start();
    		table.forEach(payment -> paymentPool.add(payment));
    		while(paymentPool.size() != 0);
    		paymentPool.exit();
    		while(paymentPool.isAlive());
    		System.out.print("Thread exited successfully");
    		
    		Gson gson = new Gson();
    		table.forEach(payment -> {
    			String history = gson.toJson(payment.history);
    			System.out.println(history);
    		});
    	}
    	catch(Throwable t){
    		t.printStackTrace();
    	}*/
    }
    
    /*public static boolean paymentTimekeeper (Payment payment)
	{
		Payment.Record paymentHistory = payment.history.get(payment.history.size() - 1);
        long elapsed = Math.abs(paymentHistory.date.getTime() - (new Date()).getTime());

        if(paymentHistory.status == Invoice.Status.WAITING_CONFIRMATION && elapsed > WAITING_CONF_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Waiting"));
            return true;
        }
        else if(paymentHistory.status == Invoice.Status.ON_PROGRESS && elapsed > ON_PROGRESS_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Progress"));
            return true;
        } 
        else if(paymentHistory.status == Invoice.Status.ON_DELIVERY && elapsed > ON_DELIVERY_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "Delivery"));
            return false;
        }
        else if(paymentHistory.status == Invoice.Status.DELIVERED && elapsed > DELIVERED_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "Finish"));
            return true;
        }
        else {
        	return false;
        }
	}*/
}
    
	/*try
	{
		String filepath = "../jmart/json/account.json";
		JsonTable<Account> tableAccount = new JsonTable<>(Account.class, filepath);
		tableAccount.add(new Account("name", "email", "password"));
		tableAccount.writeJson();

		tableAccount = new JsonTable<>(Account.class, filepath);
		tableAccount.forEach(account -> System.out.println(account.toString()));
		
		//List<Product> list = read("../jmart/json/randomProductList.json");
		//List<Product> byPrice = filterByPrice(list, 13000.0, 15000.0);
		//forEach(product -> System.out.println(product.price));
		//List<Product> byName = filterByName(list, "gtx", 1, 5);
		//byName.forEach(product -> System.out.println(product.name));
		//System.out.println("");
		//List<Product> byId = filterByAccountId(list, 1, 0, 5);
		//byId.forEach(product -> System.out.println(product.name));
	}
	catch(Throwable t)
	{
		t.printStackTrace();
	}*/
	
    /*public static List<Product> read(String filepath){
    	List<Product> products = new ArrayList<>();
        try{
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(filepath));
            reader.beginArray();
            while(reader.hasNext()){
                products.add(gson.fromJson(reader, Product.class));
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return products;
    }
    
    public static List<Product> filterByCategory (List<Product> list, ProductCategory category){
    	List<Product> filtered = new ArrayList<>();
    	for(Product product : list){
            if(product.category.equals(category)){
                filtered.add(product);
            }
        }
        return filtered;
    }
    
    public static List<Product> filterByPrice (List<Product> list, double minPrice, double maxPrice){
    	List<Product> filtered = new ArrayList<>();
    	for(int i = 0; i < list.size(); i++){
            if(minPrice <= 0.0){
                if(list.get(i).price <= maxPrice){
                    filtered.add(list.get(i));
                }
            }else if(maxPrice <= 0.0){
                if(list.get(i).price >= minPrice){
                    filtered.add(list.get(i));
                }
            }else{
                if(list.get(i).price >= minPrice && list.get(i).price <= maxPrice){
                    filtered.add(list.get(i));
                }
            }
        }
        return filtered;
    }
    
    public static List<Product> filterByAccountId(List<Product> list,int accountId, int page, int pageSize){
    	Predicate<Product> pred = filtering -> (filtering.accountId == accountId);
    	List<Product> paginated = paginate(list, page, pageSize, pred);
    	
        return paginated;
    }
    
    public static List<Product> filterByName (List<Product> list, String search, int page, int pageSize){
    	Predicate<Product> pred = filtering -> (filtering.name.toLowerCase().contains(search.toLowerCase()));
    	List<Product> paginated = paginate(list, page, pageSize, pred);
    	
        return paginated;
    }
    
    private static List<Product> paginate (List<Product> list, int page, int pageSize, Predicate<Product> pred){
    	if(pageSize < 0 || page < 0) {
    		return Collections.emptyList();
        }
    	else {
    		return list.stream().filter(filtered -> 
    		   	   pred.predicate(filtered)).skip(page*pageSize).limit(pageSize).collect(Collectors.toList());
    	}
    }*/
