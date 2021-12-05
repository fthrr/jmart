package com.fathurJmartMR.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fathurJmartMR.Algorithm;
import com.fathurJmartMR.Coupon;
import com.fathurJmartMR.Predicate;
import com.fathurJmartMR.dbjson.JsonAutowired;
import com.fathurJmartMR.dbjson.JsonTable;

/**
 * Class CouponController untuk mengatur berbagai aktivitas terkait dengan coupon
 * @author Fathurrahman Irwansa
 * @version 5 Desember 2021
 */
@RestController
@RequestMapping("/coupon")
public class CouponController implements BasicGetController<Coupon>{

		/**
		 * Instance variable utuk class coupon controller
		 */
		public static @JsonAutowired(value = Coupon.class, filepath = "C:\\OOP\\jmart\\json\\Coupon.json") JsonTable<Coupon> couponTable;
	
	 	/**
	 	 * Method untuk menentukan apakah coupon dapat digunakan atau tidak
	 	 * @param id		coupon id
	 	 * @param price		product price
	 	 * @param discount	discount price
	 	 * @return condition
	 	 */
	 	@GetMapping("/{id}/canApply")
	 	public boolean canApply(@PathVariable int id, @PathVariable double price, @PathVariable double discount){
		 for(Coupon coupon : couponTable){
	            if(coupon.id == id){
	                return coupon.canApply(price, discount);
	            }
	        }
	     return false;
	     }

	    /**
	     * Method untuk memvalidasi apakah coupin ada atau tidak
	     * @param page		page number
	     * @param pageSize	ukuran page
	     * @return coupon list
	     */
	    @GetMapping("/getAvailable")
	    public List<Coupon> getAvailable(@RequestParam int page, @RequestParam int pageSize){
	        Predicate<Coupon> pred = coupon -> !coupon.isUsed();
	        return Algorithm.paginate(couponTable, page, pageSize, pred);
	    }

	    /**
	     * Method untuk mendapatkan couponTable
	     */
	    public JsonTable<Coupon> getJsonTable() {
	        return couponTable;
	    }

	    /**
	     * Method untuk menentukan apakah coupon sudah digunakan atau belum
	     * @param id	coupon id
	     * @return condition
	     */
	    @GetMapping("/{id}/isUsed")
	    public boolean isUsed(@PathVariable int id){
	        for(Coupon coupon : couponTable){
	            if(coupon.id == id){
	                return coupon.isUsed();
	            }
	        }
	        return false;
	    }
}
