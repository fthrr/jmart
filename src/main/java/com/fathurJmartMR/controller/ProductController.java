package com.fathurJmartMR.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fathurJmartMR.Account;
import com.fathurJmartMR.Algorithm;
import com.fathurJmartMR.Predicate;
import com.fathurJmartMR.Product;
import com.fathurJmartMR.ProductCategory;
import com.fathurJmartMR.dbjson.JsonAutowired;
import com.fathurJmartMR.dbjson.JsonTable;

/**
 * Class ProductController untuk mengatur seluruh aktivitas terkait product
 * 
 * @author Fathurrahman Irwansa
 * @version 5 Desember 2021
 *
 */
@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product> {
	
	/**
	 * Instance variable untuk ProductController
	 */
	public static @JsonAutowired(value = Product.class, filepath = "C:\\OOP\\jmart\\json\\ProductList.json") JsonTable<Product> productTable;
	
	/**
	 * Method untuk membuat sebuah product baru pada jmart
	 * @param accountId		user account id
	 * @param name			nama product
	 * @param weight		berat product
	 * @param conditionUsed	kondisi product
	 * @param price			harga product
	 * @param discount		discount product
	 * @param category		kategori product
	 * @param shipmentPlans	jenis pengiriman
	 * @return newProduct
	 */
	@PostMapping("/create")
    Product create(@RequestParam int accountId, @RequestParam String name, @RequestParam int weight, @RequestParam boolean conditionUsed, @RequestParam double price, @RequestParam double discount, @RequestParam ProductCategory category, @RequestParam byte shipmentPlans){
        for(Account account : AccountController.accountTable){
            if(account.id == accountId && account.store != null){
                Product newProduct = new Product(accountId, name, weight, conditionUsed, price, discount, category, shipmentPlans);
                productTable.add(newProduct);
                return newProduct;
            }
        }
        return null;
    }
	
    /**
     * Method untuk mendapatkan productTable
     */
    public JsonTable<Product> getJsonTable() {
        return productTable;
    }
    
    /**
     * Method untuk mendapatkan product berdasarkan store
     * @param id		store id
     * @param page		nomor page
     * @param pageSize	ukuran page
     * @return filteredByStore
     */
    @GetMapping("/{id}/store")
    List<Product> getProductByStore(@PathVariable int id, @RequestParam int page, @RequestParam int pageSize){
        return Algorithm.<Product>paginate(getJsonTable(),page,pageSize, p -> (p.accountId == id));
    }
    
    /**
     * Method untuk mendapatkan product berdasarkan paramater tertentu
     * @param page		nomor page
     * @param pageSize	ukuran page
     * @param accountId	user account id
     * @param search	search name
     * @param minPrice	harga minimal
     * @param maxPrice	harga maksimal
     * @param category	kategori
     * @return filteredByParameter
     */
    @GetMapping("/getFiltered")
    List<Product> getProductFiltered(@RequestParam(defaultValue="0")  int page, @RequestParam(defaultValue="5")  int pageSize,
                                     @RequestParam  int accountId, @RequestParam  String search,
                                     @RequestParam  int minPrice, @RequestParam  int maxPrice,
                                     @RequestParam  ProductCategory category)
    {
        Predicate<Product> pred = p -> ((p.accountId == accountId) && (p.name.toLowerCase().contains(search.toLowerCase())) && (p.price >= minPrice && p.price <= maxPrice) && (p.category == category));
        return Algorithm.<Product>paginate(getJsonTable(),page,pageSize, pred);
    }
}
