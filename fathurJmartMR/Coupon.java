package fathurJmartMR;


/**
 * Write a description of class Coupon here.
 *
 * @author Fathurrahman Irwansa
 * @version 20 September 2021
 */
public class Coupon
{
    public static enum Type {
        DISCOUNT,
        REBATE;
    }
    
    public final String name;
    public final int code;
    public final double cut;
    public final Type type;
    public final double minimum;
    private boolean used;
    
    public Coupon(String name, int code, Type type, double cut, double minimum){
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
        used = false;
    }
    
    public boolean isUsed(){
        return used;
    }
    
    public boolean canApply(PriceTag priceTag){
        if((priceTag.getAdjustedPrice() >= this.minimum) && (used == false)){
            return true;
        }
        else{
            return false;
        }
    }
    
    public double apply(PriceTag priceTag){
        used = true;
        double fixedPrice = 0;
        if(this.type == Type.DISCOUNT){
            fixedPrice = (priceTag.getAdjustedPrice() * this.cut/100);
        }
        else if(this.type == Type.REBATE){
            fixedPrice = (priceTag.getAdjustedPrice() * (100-this.cut)/100);
        }
        return fixedPrice;
    }
}
