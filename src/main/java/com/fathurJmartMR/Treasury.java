package com.fathurJmartMR;

/**
 * Treasury class digunakan untuk mendapatkan berbagai harga product
 *
 * @author Fathurrahman Irwansa
 * @version 5 Desember 2021
 */
public class Treasury
{
    /**
     * Instance variable untuk treasury class
     */
    public static final double COMMISSION_MULTIPLIER = 0.05;
    public static final double BOTTOM_PRICE = 20000.0;
    public static final double BOTTOM_FEE = 1000.0;
    public double discount;
    public double price;
    
    /**
     * Constructor method untuk class treasury
     * @param price harga product
     */
    public Treasury(double price){
        this.price = price;
        this.discount = 0.0;
    }
    
    /**
     * Constructor method untuk class treasury
     * @param price		harga product
     * @param discount	harga discount
     */
    public Treasury(double price, double discount){
        this.price = price;
        this.discount = discount;
    }

    /**
     * Method untuk mendapatkan Adjusted Price
     * @param price		harga product
     * @param discount	harga discount
     * @return	AdjustedPrice
     */
    public static double getAdjustedPrice(double price, double discount){
        return getDiscountedPrice(price, discount) + getAdminFee(price, discount);
    }

    /**
     * Method untuk mendapatkan Admin Fee
     * @param price		harga product
     * @param discount	harga discount
     * @return adminFee
     */
    public static double getAdminFee(double price, double discount){
        double adminFee;
        if(getDiscountedPrice(price, discount) <= BOTTOM_PRICE){
            adminFee = BOTTOM_FEE;
        }
        else{
            adminFee = getDiscountedPrice(price, discount) * COMMISSION_MULTIPLIER;
        }
        
        return adminFee;
    }

    /**
     * Method untuk mendapatkan harga setelah didiskon
     * @param price		harga product
     * @param discount	harga discount
     * @return
     */
    private static double getDiscountedPrice(double price, double discount){
        if(discount >= 100.0){
            return 0.0;
        }
        else{
            return price - ((price * discount)/100);
        }
    }
    
    
}
