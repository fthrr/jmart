package fathurJmartMR;


/**
 * Write a description of class Shipment here.
 *
 * @author Fathurrahman Irwansa
 * @version 27 September 2021
 */
public class Shipment implements FileParser
{
    public String address;
    public int shipmentCost;
    public Duration duration;
    public String receipt;
    
    public static class Duration{
        private final int bit;
        public static final Duration INSTANT = new Duration(1<<0);
        public static final Duration SAME_DAY = new Duration(1<<1);
        public static final Duration NEXT_DAY = new Duration(1<<2);
        public static final Duration REGULER = new Duration(1<<3);
        public static final Duration KARGO = new Duration(1<<4);
        
        private Duration (int bit){
            this.bit = bit;
        }
    }
    
    public class MultiDuration{
        public byte bit;
        
        public MultiDuration(Duration... args){
            byte flags = 0;
            for (int i = 0; i < args.length; ++i)
                flags |= args[i].bit;
            bit = flags;
        }
        public boolean isDuration(Duration reference) { 
            return (this.bit & reference.bit) != 0;
        }
    }
    
    public Shipment(String address, int shipmentCost, Duration duration, String receipt){
        this.address = address;
        this.shipmentCost = shipmentCost;
        this.duration = duration;
        this.receipt = receipt;
    }
    
    @Override
    public boolean read(String content){
        return false;
    }
}
