package exchangetask;

import java.util.ArrayList;

public class Exchange implements ExchangeInterface, QueryInterface {

	ArrayList<Order> alOrders;

	public Exchange() {
		alOrders = new ArrayList<Order>();
	}

	@Override
	public int getTotalSizeAtPrice(int price) throws RequestRejectedException {
		// TODO Auto-generated method stub
		int iTotalSizeAtPrice = 0;
		for (int i = 0; i < alOrders.size(); i++) {
			Order tmp = (Order) alOrders.get(i);
			if ( tmp.Price == price && tmp.Size > 0) {
				iTotalSizeAtPrice += tmp.Size;
			}
		}
		return iTotalSizeAtPrice;
	}

	@Override
	public int getHighestBuyPrice() throws RequestRejectedException {
		// TODO Auto-generated method stub
		int iHighestBuyPrice = 0;
		for (int i = 0; i < alOrders.size(); i++) {
			Order tmp = (Order) alOrders.get(i);
			if (tmp.isBuy && iHighestBuyPrice <= tmp.Price && tmp.Size > 0) {
				iHighestBuyPrice = tmp.Price;
			}
		}
		return iHighestBuyPrice;
	}

	@Override
	public int getLowestSellPrice() throws RequestRejectedException {
		// TODO Auto-generated method stub
		int iLowestSellPrice = 0;
		for (int i = 0; i < alOrders.size(); i++) {
			Order tmp = (Order) alOrders.get(i);
			if (!tmp.isBuy && tmp.Size > 0) {
				if (iLowestSellPrice == 0 || iLowestSellPrice >= tmp.Price) {
					iLowestSellPrice = tmp.Price;
				}

			}
		}
		return iLowestSellPrice;
	}

	@Override
	public void send(long orderId, boolean isBuy, int price, int size) throws RequestRejectedException {
		System.out.printf("\nsend(%d, %b, %s, %s);\n",orderId, isBuy, price, size);
		Order order = new Order(orderId, isBuy, price, size);
		alOrders.add(order);
		// recalcMatchingOrders();
		processOrder(order);
	}

	@Override
	public void cancel(long orderId) throws RequestRejectedException {
		// TODO Auto-generated method stub
		for (int i = 0; i < alOrders.size(); i++) {
			Order tmp = (Order) alOrders.get(i);
			if (tmp.orderId == orderId) {
				// System.out.println("cancel:" + i);
				alOrders.remove(i);
			}
		}
		// recalcMatchingOrders();
	}

	public long getSellerForPrice(int iPriceMax) {
		Order lowestSeller = new Order();
		for (int i = 0; i < alOrders.size(); i++) {
			Order tmp = (Order) alOrders.get(i);
			if (!tmp.isBuy && tmp.Size > 0) {
				if (tmp.Price <= iPriceMax && ( lowestSeller.Price == 0 || lowestSeller.Price > tmp.Price)) {
					lowestSeller.orderId = tmp.orderId;
					lowestSeller.Price = tmp.Price;
				}

			}
		}
		return lowestSeller.orderId;
	}
		void processOrder(Order order) throws RequestRejectedException {
		if (order.isBuy) {
			while (order.Size > 0 && getSellerForPrice(order.Price) > 0) {
				Order lowestSellerOrder = new Order();
				long sellerId = getSellerForPrice(order.Price);
				for (int i = 0; i < alOrders.size(); i++) {
					Order tmp = (Order) alOrders.get(i);
					if (tmp.orderId == sellerId) {
						lowestSellerOrder = tmp;
					}
				}
				

//				System.out.println("order:" + order.toString());
				System.out.println("lowestPiceOrder:" + lowestSellerOrder.toString());
//				
				
				if(lowestSellerOrder.orderId ==0)
					break;
				
				if (order.Size >= lowestSellerOrder.Size) {
					order.Size -= lowestSellerOrder.Size;
					lowestSellerOrder.Size = 0;
				} else {
					lowestSellerOrder.Size -= order.Size;
					order.Size = 0;
				}
//				System.out.println("order:" + order.toString());
//				System.out.println("lowestPiceOrder:" + lowestSellerOrder.toString());
			}
		}

	}

	void show() {
		// System.out.println("#################");
//		System.out.println("the Exchange csv:");
		System.out.println("");
		System.out.println("id	| Buy/Sell	| Price	| Size");
		System.out.println("------------------------------");

		for (int i = 0; i < alOrders.size(); i++) {
			Order tmp = (Order) alOrders.get(i);
			String sBuySell = tmp.isBuy ? "Buy" : "Sell";
			System.out.printf("%d	| %s		| %d	| %d", tmp.orderId, sBuySell, tmp.Price, tmp.Size);

			System.out.println("");
		}
	}
}
