package com.fathurJmartMR.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.*;
import com.fathurJmartMR.Account;
import com.fathurJmartMR.Algorithm;
import com.fathurJmartMR.Store;
import com.fathurJmartMR.dbjson.JsonAutowired;
import com.fathurJmartMR.dbjson.JsonTable;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
		try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashPassword = no.toString(16);
            while (hashPassword.length() < 32) {
                hashPassword = "0" + hashPassword;
            }
            String hashed = hashPassword;
            return Algorithm.<Account>find(accountTable, obj -> obj.email.equals(email) && obj.password.equals(hashed));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
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
		 if(name.isBlank()) return null;
		 
		 Matcher matcher1 = REGEX_PATTERN_EMAIL.matcher(email);
		 if(!matcher1.find()) return null;
		 
		 Matcher matcher2 = REGEX_PATTERN_PASSWORD.matcher(password);
		 if(!matcher2.find()) return null;
		 
		 if(Algorithm.<Account>find(accountTable, obj -> obj.email.equals(email)) != null) return null;
		 
		 MessageDigest md = null;
		 try {
			 md = MessageDigest.getInstance("MD5");
		 } catch (NoSuchAlgorithmException e) {
			 e.printStackTrace();
		 }
		 
		 byte[] digest = md.digest(password.getBytes());
		 BigInteger no = new BigInteger(1, digest);
		 String hashed = no.toString(16);
		 while (hashed.length() < 32) {
			 hashed = "0" + hashed;
		 }
		 
		 Account account = new Account(name, email, hashed, 0);
		 accountTable.add(account);
		 
		 return account;
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
	
	
	@GetMapping
	String index() { return "account page"; }
	
	
	//@GetMapping("/{id}")
	//String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
}