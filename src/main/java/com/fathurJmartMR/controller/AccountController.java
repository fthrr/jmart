package com.fathurJmartMR.controller;

import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.*;
import com.fathurJmartMR.Account;
import com.fathurJmartMR.Store;
import com.fathurJmartMR.dbjson.JsonAutowired;
import com.fathurJmartMR.dbjson.JsonTable;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> 
{
	public static final String REGEX_EMAIL = "^[a-zA-Z0-9&_*~]+(?:\\.[a-zA-Z0-9&_*~]+)*@[A-Za-z0-9]{1}[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9-]+)*$";
	public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
	public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
	public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
	
	public static @JsonAutowired(filepath = "../jmart/json/Account.json", value = Account.class) JsonTable<Account> accountTable;
	
	@Override
	public JsonTable<Account> getJsonTable() {
		return accountTable;
	}
	
	@PostMapping("/login")
	Account login
	(
		@RequestParam String email,
		@RequestParam String password
	)
	{
		for(Account account : accountTable){
			if(account.email.equals(email) && account.password.equals(password)){
				return account;
				}
			}
		return null;
	}
	
	@PostMapping("/register")
	Account register
	(
		@RequestParam String name,
		@RequestParam String email,
		@RequestParam String password
	)
	{
		if((REGEX_PATTERN_EMAIL.matcher(email).find()) && (REGEX_PATTERN_PASSWORD.matcher(password).find()) && !name.isBlank()){
			for(Account account : accountTable){
				if(account.email.equals(email)){
					return null;
                }
            }
            return new Account(name, email, password, 0);
        }
        return null;
	}
	
	@PostMapping("/{id}/registerStore")
	Store registerStore
	(
		@RequestParam int id,
		@RequestParam String name,
		@RequestParam String address,
		@RequestParam String phoneNumber
	)
	{
		 if(accountTable.contains(accountTable.get(id)) && accountTable.get(id).store == null){
			 Store newStore = new Store(name, address, phoneNumber, 0);
			 accountTable.get(id).store = newStore;
			 return newStore;
		 }
		 else{
			 return null;
		 }
	}
	
	@PostMapping("/{id}/topUp")
	boolean topUp
	(
		@RequestParam int id,
		@RequestParam double balance
	)
	{
		if(accountTable.contains(accountTable.get(id))){
			accountTable.get(id).balance += balance;
			return true;
		}
		else{
			return false;
		}
	}
	
	
	//@GetMapping
	//String index() { return "account page"; }
	
	
	//@GetMapping("/{id}")
	//String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
}