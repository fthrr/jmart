package com.fathurJmartMR.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fathurJmartMR.Algorithm;
import com.fathurJmartMR.dbjson.JsonTable;
import com.fathurJmartMR.dbjson.Serializable;

/**
 * Class BasicGetController untuk mendapatkan beberapa paramater basic
 * @author Fathurrahman Irwansa
 * @version 5 Desember 2021
 */
public interface BasicGetController<T extends Serializable> {
	
	/**
	 * Method untuk mendapatkan page yang diinginkan
	 * @param page		nilai page
	 * @param pageSize	ukuran page
	 * @return paginate
	 */
	@GetMapping("/page")
    default @ResponseBody List<T> getPage(@RequestParam(defaultValue="1") int page, @RequestParam(defaultValue="5") int pageSize){
        return Algorithm.<T>paginate(getJsonTable(),page,pageSize,e -> true);
    }
	
	/**
	 * Method untuk mendapatkan informasi user berdasarkan id
	 * @param id		id user
	 * @return userIdValue
	 */
	@GetMapping("/{id}")
	default T getById(@PathVariable int id){
		return Algorithm.<T>find(getJsonTable(),(e) -> e.id == id);
	}

    /**
     * Abstract method untuk mendapatkan JsonTable
     * @return jsonTable
     */
    public abstract JsonTable<T> getJsonTable();
}
