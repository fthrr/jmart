package fathurJmartMR;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
/**
 * Write a description of class Jmart here.
 *
 * @author Fathurrahman Irwansa
 * @version 8 November 2021
 */
class Jmart
{
    public static void main (String[] args){
    	try
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
    	}
    }
    
    public static List<Product> read(String filepath){
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
    }
}
