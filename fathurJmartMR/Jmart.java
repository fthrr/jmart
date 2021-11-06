package fathurJmartMR;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
/**
 * Write a description of class Jmart here.
 *
 * @author Fathurrahman Irwansa
 * @version 6 November 2021
 */
class Jmart
{
    public static void main (String[] args){
    	try
    	{
    		List<Product> list = read("../jmart/json/randomProductList.json");
    		List<Product> filtered = filterByPrice(list, 13000.0, 15000.0);
    		filtered.forEach(product -> System.out.println(product.price));
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
    
    /*System.out.println("acc id:" + new Account(null, null, null, -1).id);
	System.out.println("acc id:" + new Account(null, null, null, -1).id);
	System.out.println("acc id:" + new Account(null, null, null, -1).id);
	
	System.out.println("pay id:" + new Payment(-1, -1, -1, null).id);
	System.out.println("pay id:" + new Payment(-1, -1, -1, null).id);
	System.out.println("pay id:" + new Payment(-1, -1, -1, null).id);
	*/
	/*String filepath = "../jmart/json/city.json";
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
	}*/
}
