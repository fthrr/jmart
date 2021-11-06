package fathurJmartMR;


/**
 * Write a description of class Product here.
 *
 * @author Fathurrahman Irwansa
 * @version 18 September 2021
 */
public class Product extends Serializable
{
    public int accountId;
    public ProductCategory category;
    public boolean conditionUsed;
    public double discount;
    public String name;
    public double price;
    public byte shipmentPlans;
    public int weight;
    
    public Product(int accountId, String name, int weight, boolean conditionUsed, double price, double discount, ProductCategory category, byte shipmentPlans)
    {
        
        this.accountId = accountId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.price = price;
        this.discount = discount;
        this.category = category;
        this.shipmentPlans = shipmentPlans;
    }
    
    /*@Override
    public boolean read(String content){
        return false;
    }*/
    
    public String toString(){
        return
        	"Account ID: "+this.accountId+"\n"+
            "Name: "+this.name+"\n"+
            "Weight: "+this.weight+"\n"+
            "conditionUsed: "+this.conditionUsed+"\n"+
            "Price Tag: "+this.price+"\n"+
            "Discount: "+this.discount+"\n"+
            "Category: "+this.category+"\n"+
            "Shipment Plans: "+this.shipmentPlans+"\n";
    }
}
