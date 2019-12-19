package en.caps.orderbook;

public class Order {

	private int price;
	private int size;
	private char type;

	public Order(int price, int size, char type) {
		super();
		this.price = price;
		this.size = size;
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}
	
	

}
