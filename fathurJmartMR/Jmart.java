package fathurJmartMR;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Write a description of class Jmart here.
 *
 * @author Fathurrahman Irwansa
 * @version 2 Oktober 2021
 */
public class Jmart
{
    public static void main (String[] args){
        System.out.println(Shipment.Duration.REGULER.getEstimatedArrival(new Date()));
        Store store = new Store(111, "Fathurrahman Irwansa", "Depok", "08558851636");
        System.out.println(store.validate());
    }

    public static Product createProduct(){
        return null;
    }

    public static Coupon createCoupon(){
        return null;
    }
    
    public static Shipment.Duration createShipmentDuration(){
        return null;
    }
}
