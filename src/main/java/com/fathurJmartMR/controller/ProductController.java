package com.fathurJmartMR.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fathurJmartMR.Account;
import com.fathurJmartMR.Algorithm;
import com.fathurJmartMR.Predicate;
import com.fathurJmartMR.Product;
import com.fathurJmartMR.ProductCategory;
import com.fathurJmartMR.dbjson.JsonAutowired;
import com.fathurJmartMR.dbjson.JsonTable;

@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product> {
	
	public static @JsonAutowired(filepath = "../jmart/json/ProductList.json", value = Product.class) JsonTable<Product> productTable;
	
	@PostMapping("/create")
	Product create
	(
		@RequestParam int accountId,
		@RequestParam String name,
		@RequestParam int weight,
		@RequestParam boolean conditionUsed,
		@RequestParam double price,
		@RequestParam double discount,
		@RequestParam ProductCategory category,
		@RequestParam byte shipmentPlans
	)
	{
		AccountController control1 = new AccountController();
        JsonTable<Account> accountTable = control1.getJsonTable();
        
        for(Account account : accountTable){
            if(account.id == accountId && account.store != null){
                return new Product(accountId, name, weight, conditionUsed, price, discount, category, shipmentPlans);
            }
        }
        return null;
	}
	
	@Override
	public JsonTable<Product> getJsonTable() {
		return productTable;
	}
	
	@GetMapping("/{id}/store")
	@ResponseBody List<Product> getProductByStore
	(
		@PathVariable int id,
		@RequestParam(defaultValue="1") int page,
		@RequestParam(defaultValue="5") int pageSize
	)
	{
		return Algorithm.paginate(productTable, page, pageSize,pred->pred.accountId == id);
	}
	
	@GetMapping("/getFiltered")
	@ResponseBody List<Product> getProductFiltered
	(
			@RequestParam(defaultValue="1") int page,
			@RequestParam(defaultValue="5") int pageSize,
			@RequestParam(defaultValue = "0") int accountId,
			@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "0") int minPrice,
			@RequestParam(defaultValue = "0") int maxPrice,
			@RequestParam(required = false) ProductCategory category
	)
	{
		Predicate<Product> predicate = obj -> {
            if(accountId != 0 && obj.accountId == accountId){
                return true;
            }
            if(!search.isBlank() && obj.name.equals(search)){
                return true;
            }
            if(minPrice != 0 && obj.price > minPrice){
                return true;
            }
            if(maxPrice != 0 && obj.price < maxPrice){
                return true;
            }
            if(category != null && obj.category == category){
                return true;
            }
            return false;
        };
	    return Algorithm.<Product>paginate(getJsonTable(),page,pageSize, predicate);
	}
}
