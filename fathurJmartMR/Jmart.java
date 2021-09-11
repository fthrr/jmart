package fathurJmartMR;


/**
 * Write a description of class Jmart here.
 *
 * @author Fathurrahman Irwansa
 * @version 11 Sept 2021
 */
public class Jmart
{
    public static void main (String[] args){
    }
    
    public int getPromo(){
        return 0;
    }
    
    public String getCustomer(){
        return "oop";
    }
    
    public float getDiscountPercentage(int before, int after){
        if(before < after){
            return 0.0f;
        }
        else{
            return ((before - after)/before)*100;
        }
    }
    
    public int getDiscountedPrice(int price, float discountPercentage){
        if(discountPercentage > 100.f){
            return 0;
        }
        else{
            return price - (int)(discountPercentage * 100);
        }
    }
    
    public int getOriginalPrice(int discountedPrice, float discountPercentage){
        return discountedPrice + (int)((100/discountPercentage)*discountedPrice);
    }
    
    public float getCommissionMultiplier(){
        return 0.05f;
    }
    
    public int getAdjustedPrice(int price){
        return price + (int)(price*0.05f);
    }
    
    public int getAdminFee(int price){
        return (int)getCommissionMultiplier() * price;
    }
}
