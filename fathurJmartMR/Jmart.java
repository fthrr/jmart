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

    public static Product createProduct(){
        return new Product("Item", 0, false, new PriceTag(100.0), ProductCategory.BOOK);
    }

    public static Coupon createCoupun(){
        return new Coupon("My Coupun", 21312, Coupon.Type.REBATE, 10000, 50000);
    }

    public static ShipmentDuration createShipmentDuration(){
        return new ShipmentDuration(ShipmentDuration.INSTANT, ShipmentDuration.KARGO);
    }
}
