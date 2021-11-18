package com.fathurJmartMR;

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * Write a description of class Shipment here.
 *
 * @author Fathurrahman Irwansa
 * @version 2 Oktober 2021
 */
public class Shipment
{
	public static final SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat("E MMMM dd yyyy");
    public static final Plan INSTANT = new Plan((byte) (1 << 0));
    public static final Plan SAME_DAY = new Plan((byte) (1 << 1));
    public static final Plan NEXT_DAY = new Plan((byte) (1 << 2));
    public static final Plan REGULER = new Plan((byte) (1 << 3));
    public static final Plan KARGO = new Plan((byte) (1 << 4));
    public String address;
    public int cost;
    public byte plan;
    public String receipt;
    
    public static class Plan{
        private final byte bit;
        
        private Plan (byte bit){
            this.bit = bit;
        }
    }
    
    public Shipment(String address, int cost, byte plan, String receipt){
        this.address = address;
        this.cost = cost;
        this.plan = plan;
        this.receipt = receipt;
    }
    
    public String getEstimatedArrival(Date reference){
        Calendar cal = Calendar.getInstance();
        cal.setTime(reference);
        if (plan == INSTANT.bit || plan == SAME_DAY.bit){
            cal.add(Calendar.DATE, 0);
        }
        else if (plan == NEXT_DAY.bit){
            cal.add(Calendar.DATE, 1);
        }
        else if (plan == REGULER.bit){
            cal.add(Calendar.DATE, 2);
        }
        else if (plan == KARGO.bit){
            cal.add(Calendar.DATE, 5);
        }
        return ESTIMATION_FORMAT.format(cal.getTime());
    }
    
    public boolean isDuration (Plan reference){ 
        return (this.plan & reference.bit) != 0;
    }
    
    public static boolean isDuration(byte object, Plan reference) {
    	if((object & reference.bit) == 0) {
    		return false;
    	}
    	return true;
    }
    
    /*public class MultiDuration{
        public byte bit;
        
        public MultiDuration(Plan... args){
            byte flags = 0;
            for (int i = 0; i < args.length; ++i)
                flags |= args[i].bit;
            bit = flags;
        }
        
    }*/
    
    /*@Override
    public boolean read(String content){
        return false;
    }*/
}
