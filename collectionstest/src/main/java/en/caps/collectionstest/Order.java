package en.caps.collectionstest;

public class Order {

	private int id;
	private int count;
	private int price;
	private boolean buy;

	public Order(int id, boolean buy, int count, int price) {
		super();
		this.id = id;
		this.count = count;
		this.price = price;
		this.buy = buy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isBuy() {
		return buy;
	}

	public void setBuy(boolean sellBuy) {
		this.buy = sellBuy;
	}

	public String isBuyS() {
		return (buy)?"Buy ":"Sell";
	}
	@Override
	public String toString() {		
		return "Order [id=" + id + ", buy=" + this.isBuyS() + ", count=" + count + ", price=" + price + "]";
	}

}
