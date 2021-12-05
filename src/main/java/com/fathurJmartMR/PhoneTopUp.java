package com.fathurJmartMR;

/**
 * PhoneTopUp class untuk menghandle fungsi phone topUp pada Jmart
 * 
 * @author Fathurrahman Irwansa
 * @version	5 Desember 2021
 *
 */
public class PhoneTopUp extends Invoice{
    /**
     * Instance variable untuk class phone top up
     */
    public String phoneNumber;
    public Status status;
    
    /**
     * Constructor method untuk phoneTopUp Class
     * @param buyerId		buyer id pada jmart
     * @param productId		product id pada jmart
     * @param phoneNumber	phone number pada jmart
     */
    public PhoneTopUp(int buyerId, int productId, String phoneNumber){
        super(buyerId, productId);
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * Override method getTotalPay untuk mendapatkan harga total
     * @return totalPay
     */
    @Override
    public double getTotalPay(Product product){
        return Treasury.getAdjustedPrice(product.price, product.discount);
    }
}
