package fathurJmartMR;

/**
 * Write a description of class Jmart here.
 *
 * @author Fathurrahman Irwansa
 * @version 11 Sept 2021
 */
public class Jmart
{
    public static void main(String[] args){
    }
    
    public static Product createProduct(){
        Product product = new Product("Laptop", 4, true, new PriceTag(9000000), ProductCategory.ELECTRONIC);
        return product;
    }
    
    public static Coupon createCoupun(){
        return null;
    }
    
    public static ShipmentDuration createShipmentDuration(){
        return null;
    }
}
