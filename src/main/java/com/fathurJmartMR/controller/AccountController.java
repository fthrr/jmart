package com.fathurJmartMR.controller;

import java.util.regex.Pattern;
import org.springframework.web.bind.annotation.*;
import com.fathurJmartMR.Account;
import com.fathurJmartMR.Algorithm;
import com.fathurJmartMR.Store;
import com.fathurJmartMR.dbjson.JsonAutowired;
import com.fathurJmartMR.dbjson.JsonTable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class AccountController untuk menghandle seluruh urusan terkait Account
 * @author Fathurrahman Irwansa
 * @version 5 Desember 2021
 */
@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> 
{

	/**
	 * Instance variable untuk AccountController
	 */
	public static @JsonAutowired(value=Account.class, filepath="C:\\OOP\\jmart\\json\\Account.json") JsonTable<Account> accountTable;
	public static final String REGEX_EMAIL = "^\\w+([\\.]?[&\\*~\\w+])*@\\w+([\\.-]?)*(\\.\\w{2,3})+$";
    public static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$)(?=.*[A-Z]).{8,}$";
	public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
	public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);

	/**
	 * Method untuk mendapatkan account table
	 */
	public JsonTable<Account> getJsonTable(){
        return accountTable;
    }

	/**
	 * Method mapping untuk user agar bisa login
	 * @param email		user email
	 * @param password	user password
	 * @return account
	 */
	@PostMapping("/login")
	Account login(
			@RequestParam String email,
			@RequestParam String password
	)
	{
		for(Account account : accountTable) {
			try{
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes());
                byte[] bytes = md.digest();
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < bytes.length; i++){
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                if(account.email.equals(email) && account.password.equals(sb.toString())){ //Compare hash in string with equals
                    return account;
                }
            } catch (NoSuchAlgorithmException e){
                e.printStackTrace();
            }
		}
		return null;
	}

	/**
	 * Method mapping agar user dapat register ke program jmart
	 * @param name		nama user
	 * @param email		email user
	 * @param password	password user
	 * @return	newAccount
	 */
	@PostMapping("/register")
	Account register
	(
		  @RequestParam String name,
		  @RequestParam String email,
		  @RequestParam String password
	)
	{
		  String generatedPassword;
		  try {
		   MessageDigest md = MessageDigest.getInstance("MD5");
		     
		            byte[] bytes = md.digest(password.getBytes());
	
		            StringBuilder sb = new StringBuilder();
		            for(int i = 0; i < bytes.length; i++)
		             sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		            
		            generatedPassword = sb.toString();
		  }
		  catch(NoSuchAlgorithmException e) {
			  		throw new RuntimeException(e);
		  }
		  if(!name.isBlank() && REGEX_PATTERN_EMAIL.matcher(email).find() &&
		    REGEX_PATTERN_PASSWORD.matcher(password).find() &&
		    !Algorithm.<Account>exists(getJsonTable(), (account -> account.email.equals(email)))) {
				   getJsonTable().add(new Account(name, email, generatedPassword, 0));
				   return new Account(name, email, generatedPassword, 0);
		  }
		   
		  return null;
	}

    /**
     * Method controller untuk user dapat membuat store baru
     * @param id			id user
     * @param name			nama user
     * @param address		alamat user
     * @param phoneNumber	nomor telepon user
     * @return newStore
     */
	@PostMapping("/{id}/registerStore")
    Store registerStore(@PathVariable int id, @RequestParam String name, @RequestParam String address, @RequestParam String phoneNumber){
        if(accountTable.contains(accountTable.get(id)) && accountTable.get(id).store == null){
            Store newStore = new Store(name, address, phoneNumber, 0);
            accountTable.get(id).store = newStore;
            return newStore;
        }else{
            return null;
        }
    }

    /**
     * Method controller agar user dapat topUp ke Jmart
     * @param id		id user
     * @param balance	balance user
     * @return
     */
	@PostMapping("/{id}/topUp")
    Account topUp(@PathVariable int id, @RequestParam double balance){
        if(accountTable.contains(accountTable.get(id))){
            accountTable.get(id).balance += balance;
            return accountTable.get(id);
        }else{
            return null;
        }

    }

	/**
	 * Method untuk mendapatkan account page untuk melihat server sudah aktif atau belum
	 * @return accountPage
	 */
	@GetMapping
	String index() { return "account page"; }
}