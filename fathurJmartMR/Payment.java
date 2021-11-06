package fathurJmartMR;


/**
 * Write a description of class Payment here.
 *
 * @author Fathurrahman Irwansa
 * @version 25 September 2021
 */
public class Payment extends Invoice
{
    public int productCount;
    public Shipment shipment;

    public Payment(int buyerId, int productId, int productCount, Shipment shipment){
    	super(buyerId, productId);
    	this.productCount = productCount;
        this.shipment = shipment;
    }

	@Override
	public double getTotalPay() {
		return 0;
	}
}
