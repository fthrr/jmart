package com.fathurJmartMR;

/**
 * Write a description of class PriceTag here.
 *
 * @author Fathurrahman Irwansa
 * @version 18 September 2021
 */
public class Treasury
{
    public static final double COMMISSION_MULTIPLIER = 0.05;
    public static final double BOTTOM_PRICE = 20000.0;
    public static final double BOTTOM_FEE   = 1000.0;

    public static double getAdjustedPrice(double price, double discount)
    {
        return getDiscountedPrice(price, discount) + getAdminFee(price, discount);
    }

    public static double getAdminFee(double price, double discount)
    {
        double discountedPrice = getDiscountedPrice(price,discount);
        if (discountedPrice < BOTTOM_PRICE)
            return BOTTOM_FEE;
        return COMMISSION_MULTIPLIER * discountedPrice;
    }

    private static double getDiscountedPrice(double price, double discount)
    {
        if (discount >= 100.0) return 0.0;
        double cut = price * discount / 100.0;
        return price - cut; 
    }
}
