package fathurJmartMR;

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * Write a description of class Shipment here.
 *
 * @author Fathurrahman Irwansa
 * @version 2 Oktober 2021
 */
public class Shipment implements FileParser
{
    public String address;
    public int shipmentCost;
    public Duration duration;
    public String receipt;
    
    public static class Duration{
        private final byte bit;
        
        public static final SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat("E MMMM dd yyyy");
        public static final Duration INSTANT = new Duration((byte) (1 << 0));
        public static final Duration SAME_DAY = new Duration((byte) (1 << 1));
        public static final Duration NEXT_DAY = new Duration((byte) (1 << 2));
        public static final Duration REGULER = new Duration((byte) (1 << 3));
        public static final Duration KARGO = new Duration((byte) (1 << 4));
        
        private Duration (byte bit){
            this.bit = bit;
        }
        
        public String getEstimatedArrival(Date reference){
            Calendar cal = Calendar.getInstance();
            cal.setTime(reference);
            if (bit == Duration.INSTANT.bit || bit == Duration.SAME_DAY.bit){
                cal.add(Calendar.DATE, 0);
            }
            else if (bit == Duration.NEXT_DAY.bit){
                cal.add(Calendar.DATE, 1);
            }
            else if (bit == Duration.REGULER.bit){
                cal.add(Calendar.DATE, 2);
            }
            else if (bit == Duration.KARGO.bit){
                cal.add(Calendar.DATE, 5);
            }
            return ESTIMATION_FORMAT.format(cal.getTime());
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
