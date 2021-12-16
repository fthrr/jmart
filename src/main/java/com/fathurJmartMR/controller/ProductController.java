package com.fathurJmartMR.controller;

import java.util.ArrayList;
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
import com.fathurJmartMR.Payment;
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
	 * Method untuk mendapatkan product yang dipilih
	 * @param id		product id
	 * @param page		page number
	 * @param pageSize	ukuran page
	 * @return productPage
	 */
	@GetMapping("/{id}/page")
    @ResponseBody List<Product> getProducts(@PathVariable int id, @RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="1000") int pageSize){
		List<Product> productList = new ArrayList<>();
        Account accountTarget = Algorithm.<Account>find(AccountController.accountTable,  a -> a.id == id);
        if(accountTarget != null){
            for(Product product : ProductController.productTable){
                for(Payment payment : PaymentController.paymentTable){
                    if(payment.productId == product.id && product.accountId == accountTarget.id){
                        productList.add(product);
                    }
                }
            }
        }
        return Algorithm.paginate(productList, page, pageSize, e->true);
    }
	
	/**
	 * Method untuk mendapatkan product pembelian dari seller
	 * @param id	product id
	 * @param page	page number
	 * @param pageSize	ukuran page
	 * @return
	 */
    @GetMapping("/{id}/purchases/page")
    @ResponseBody List<Product> getMyProducts(@PathVariable int id, @RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="1000") int pageSize){
        List<Product> productList = new ArrayList<>();
        List<Payment> paymentList = Algorithm.<Payment>paginate(PaymentController.paymentTable, page, pageSize, p -> p.buyerId == id);
        for(Product product : getJsonTable()){
            for(Payment payment : paymentList){
                if(payment.productId == product.id){
                    productList.add(product);
                }
            }
        }
        return Algorithm.<Product>paginate(productList, page, pageSize, e -> true);
    }
	
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
