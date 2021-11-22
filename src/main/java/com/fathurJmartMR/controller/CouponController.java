package com.fathurJmartMR.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fathurJmartMR.Algorithm;
import com.fathurJmartMR.Coupon;
import com.fathurJmartMR.Predicate;
import com.fathurJmartMR.dbjson.JsonAutowired;
import com.fathurJmartMR.dbjson.JsonTable;

@RestController
@RequestMapping("/coupon")
public class CouponController implements BasicGetController<Coupon>{

	public static @JsonAutowired(filepath = "../jmart/json/Coupon.json", value = Coupon.class) JsonTable<Coupon> couponTable;
	
	@GetMapping("/{id}canApply")
	@ResponseBody boolean canApply
	(
		@PathVariable int id,
		@RequestParam double price,
		@RequestParam double discount
	)
	{
		for(Coupon coupon : couponTable){
            if(coupon.id == id){
                return coupon.canApply(price, discount);
            }
        }
        return false;
	}
	
	@GetMapping("/{id}/isUsed")
    boolean isUsed(
            @PathVariable int id
    )
	{
		Coupon coupon = Algorithm.<Coupon>find(couponTable, obj -> obj.id == id);
        return coupon.isUsed();
    }
	
	@GetMapping("/getAvailable")
    public List<Coupon> getAvailable
    (
    	@RequestParam(defaultValue="1") int page,
    	@RequestParam(defaultValue="5") int pageSize
    )
	{
        Predicate<Coupon> pred = coupon -> !coupon.isUsed();
        return Algorithm.paginate(couponTable, page, pageSize, pred);
    }
	
	@Override
	public JsonTable<Coupon> getJsonTable() {
		return couponTable;
	}

}
