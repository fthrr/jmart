package com.fathurJmartMR;

import com.fathurJmartMR.dbjson.Serializable;

/**
 * Class Coupon untuk mendefinisikan setiap fungsi coupon pada jmart
 *
 * @author Fathurrahman Irwansa
 * @version 5 Desember 2021
 */
public class Coupon extends Serializable
{
    /**
     * Enum class from type coupon
     *
     */
    public enum Type{
        DISCOUNT, REBATE
    }
    
    /**
     * Instace variable untuk class coupon
     */
    public final int code;
    public final double cut;
    public final double minimum;
    public final String name;
    public final Type type;
    private boolean used;
    

    /**
     * Method construtor untuk class coupon
     * @param name	coupon name
     * @param code	coupon code
     * @param type	coupon type
     * @param cut	coupon cut
     * @param minimum	coupon minimum price
     */
    public Coupon(String name, int code, Type type, double cut, double minimum)
    {
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
        this.used = false;
    }
    
    /**
     * Method apply untuk memakai coupon
     * @param price		product price
     * @param discount	discount percentage
     * @return adjustedPrice
     */
    public double apply(double price, double discount)
    {
        used = true;
        if(type == Type.DISCOUNT){
            if(cut >= 100){
                return (Treasury.getAdjustedPrice(price, discount) - Treasury.getAdjustedPrice(price, discount) * (100 / 100)); //cut max 100%
            }else if(cut <= 0){
                return (Treasury.getAdjustedPrice(price, discount) - Treasury.getAdjustedPrice(price, discount) * (0 / 100)); //cut min 0%
            }else{
                return (Treasury.getAdjustedPrice(price, discount) - Treasury.getAdjustedPrice(price, discount) * (cut / 100));
            }
        }
        return (Treasury.getAdjustedPrice(price, cut) - cut);
    }
    
    /**
     * Method untuk memeriksa apakah coupon dapat digunaakn
     * @param price		Product price
     * @param discount	discount price
     * @return condition
     */
    public boolean canApply(double price, double discount)
    {
        if(Treasury.getAdjustedPrice(price, discount) >= minimum && !used){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Method untuk mengubah kondisi coupon
     * @return used
     */
    public boolean isUsed()
    {
        return used;
    }

}
