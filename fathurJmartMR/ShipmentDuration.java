package fathurJmartMR;


/**
 * Write a description of class ShipmentDuration here.
 *
 * @author Fathurrahman Irwansa
 * @version 20 September 2021
 */
public class ShipmentDuration
{
    public static ShipmentDuration INSTANT = new ShipmentDuration(1<<0);
    public static ShipmentDuration SAME_DAY = new ShipmentDuration(1<<1);
    public static ShipmentDuration NEXT_DAY = new ShipmentDuration(1<<2);
    public static ShipmentDuration REGULER = new ShipmentDuration(1<<3);
    public static ShipmentDuration KARGO = new ShipmentDuration(1<<4);
    private final int bit;
    
    private ShipmentDuration(int bit){
        this.bit = bit;
    }
    
    public ShipmentDuration(ShipmentDuration... args){
        int flag = 0;
        for(ShipmentDuration i:args){
            flag = flag;
        };
        this.bit = flag;
    }
    
    public boolean isDuration(ShipmentDuration reference){
        return true;
    }
}
