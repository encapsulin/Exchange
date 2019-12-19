package exchangetask;

import java.util.ArrayList;

public class Order {
	long orderId;
	boolean isBuy;// Buy or Sell
	int Price;
	int Size;

	public Order() {
		orderId = 0;
		isBuy = false;
		Price = 0;
		Size = 0;
	}

	public Order(long iOrderId, boolean isBuy, int iPrice, int iSize) {
		orderId = iOrderId;
		this.isBuy = isBuy;
		Price = iPrice;
		Size = iSize;
	}

	public void setId(long iOrderId) {
		orderId = iOrderId;
	}

	public void setPrice(int iPrice) {
		Price = iPrice;
	}

	public void setSize(int iSize) {
		Size = iSize;
	}

	public long getOrderId() {
		return orderId;
	}

	public int getPrice() {
		return Price;
	}

	public int getSize() {
		return Size;
	}
	
	public String toString() {
			//String s;
		return "orderId:"+orderId+",isBuy:"+isBuy+",Price:"+Price+",Size:"+Size;
		
	}
}
