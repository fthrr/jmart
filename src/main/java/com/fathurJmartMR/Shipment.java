package com.fathurJmartMR;

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * Shipment class untuk menghandle aktivitas terkait pengiriman pada Jmart
 *
 * @author Fathurrahman Irwansa
 * @version 2 Oktober 2021
 */
public class Shipment
{
    
    /**
     * Instance variable untuk class shipment
     */
    public String address;
    public int shipmentCost;
    public byte plan;
    public String receipt;
    public static final Plan INSTANT = new Plan((byte) (1 << 0));
    public static final Plan SAME_DAY = new Plan((byte) (1 << 1));
    public static final Plan NEXT_DAY = new Plan((byte) (1 << 2));
    public static final Plan REGULER = new Plan((byte) (1 << 3));
    public static final Plan KARGO = new Plan((byte) (1 << 4));
    public static final SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat("EEE MMMM dd yyyy");

    /**
     * Inner class Plan pada class Shipment
     *
     */
    static class Plan {
        /**
         * Instance variable untuk inner class Plan
         */
        public final byte bit;
        
        /**
         * Constructor method untuk inner class Plan
         */
        private Plan(byte bit){
            this.bit = bit;
        }
    }

    /**
     * Method untuk mendapatkan estimasi product sampai ke tujuan
     * @param reference Date reference untuk shipment
     * @return Estimation Format
     */
    public String getEstimatedArrival(Date reference){
        Calendar kalender = Calendar.getInstance();
        kalender.setTime(reference);
        if(plan == INSTANT.bit || plan == SAME_DAY.bit){
            kalender.add(Calendar.DATE, 0);}
        else if (plan == NEXT_DAY.bit)
            kalender.add(Calendar.DATE, 1);
        else if (plan == REGULER.bit)
            kalender.add(Calendar.DATE, 2);
        else if (plan == KARGO.bit)
            kalender.add(Calendar.DATE, 5);

        return ESTIMATION_FORMAT.format(kalender.getTime());
    }

    /**
     * Method untuk mendapatkan durasi
     * @param reference Date reference untuk mendapatkan duration
     * @return duration
     */
    public boolean isDuration(Plan reference)
    {
        return (this.plan & reference.bit) == reference.bit;
    }

    /**
     * @param object	object yang diukur
     * @param reference Date reference untuk mendapatkan duration
     * @return duration
     */
    public static boolean isDuration(byte object, Plan reference)
    {
        return (object & reference.bit) == reference.bit;
    }
    
    /**
     * Constructor method untuk class shipment
     * 
     * @param address		Alamat tujuan
     * @param shipmentCost	Biaya pengiriman
     * @param plan			Plan pengiriman
     * @param receipt		Receipt pengiriman
     */
    public Shipment(String address, int shipmentCost, byte plan, String receipt) {
        this.address = address;
        this.shipmentCost = shipmentCost;
        this.plan = plan;
        this.receipt = receipt;
    }
    
}