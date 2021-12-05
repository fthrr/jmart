package com.fathurJmartMR;

import com.fathurJmartMR.dbjson.Serializable;

/**
 * Product class untuk menghandle seluruh product pada jmart
 *
 * @author Fathurrahman Irwansa
 * @version 18 September 2021
 */
public class Product extends Serializable
{
    /**
     * Instance variable pada class Product
     */
    public int accountId;
    public ProductCategory category;
    public boolean conditionUsed;
    public double discount;
    public String name;
    public double price;
    public byte shipmentPlans;
    public int weight;
    
    /**
     * Constructor method untuk class Product
     * @param accountId		account id
     * @param name			product name
     * @param weight		berat product
     * @param conditionUsed kondisi product
     * @param price			harga product
     * @param discount		discount product
     * @param category		product category
     * @param shipmentPlans	jenis pengiriman
     */
    public Product(int accountId, String name, int weight, boolean conditionUsed, double price, double discount,
                   ProductCategory category, byte shipmentPlans)
    {
        this.accountId = accountId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.price = price;
        this.discount = discount;
        this.category = category;
        this.shipmentPlans = shipmentPlans;
        
    }
    
    /**
     *Method untuk mengkonversi variable dalam kelas Product menjadi string
     */
    @Override
    public String toString(){
        return("Name: " + name + "\nWeight: " + weight + "\nconditionUsed: " + conditionUsed + 
               "\nprice: " + price + "\ncategory: " + category + "\ndiscount: " + discount + "\naccountId: " + accountId);
    }

}
