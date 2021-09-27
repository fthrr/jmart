package fathurJmartMR;


/**
 * Write a description of class Payment here.
 *
 * @author Fathurrahman Irwansa
 * @version 25 September 2021
 */
public class Payment extends Transaction implements FileParser
{
    public int productId;
    public ShipmentDuration shipmentDuration;
    
    public Payment(int id, int buyerId, Product product, ShipmentDuration shipmentDuration){
        super(product.id, buyerId, id);
        this.shipmentDuration = shipmentDuration;
    }

    public Payment(int id, int buyerId, int storeId, int productId, ShipmentDuration shipmentDuration){
        super(id, buyerId, storeId);
        this.productId = productId;
        this.shipmentDuration = shipmentDuration;
    }

    @Override
    public boolean read(String content){
        return false;
    }

    public boolean validate(){
        return false;
    }

    public Transaction perform(){
        return null;
    }
}
