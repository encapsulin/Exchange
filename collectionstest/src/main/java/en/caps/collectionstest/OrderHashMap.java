package en.caps.collectionstest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OrderHashMap {

	Map<Integer, Order> aList;
	//300000 items, 400041ms = 6m 40s 41ms
	//100000 items, 46996ms = 0m 46s 996ms
	
	boolean showDebug = false;

	public OrderHashMap() {
		aList = new HashMap<Integer, Order>();
	}

	public void orderAdd(Order order) {
		// aList.add(order);
		addAndMatchCorrespondingOrder(order);
	}

//	public void orderDelete(int id) {
//		Iterator<Order> itr = aList.iterator();
//		while (itr.hasNext()) {
//			Order o = itr.next();
//			if (o.getId() == id)
//				itr.remove();
//		}
//
//	}

	void addAndMatchCorrespondingOrder(Order orderNew) {
		if (this.showDebug) {
			System.out.println("\n\norderNew:");
			System.out.println(orderNew);
		}

		int iMinMax = 0;
		Order orderBestMatch = null;
		for (Order order:aList.values()) {		

			if (order.getCount() == 0 || orderNew.isBuy() == order.isBuy())
				continue;

			if ((orderNew.isBuy() && iMinMax < order.getPrice())
					|| (!orderNew.isBuy() && iMinMax > order.getPrice())) {
				iMinMax = order.getPrice();
				orderBestMatch = order;
			}
		}

		if (orderBestMatch != null) {
			if (this.showDebug) {
				System.out.println("orderBestMatch:");
				System.out.println(orderBestMatch);
			}
			int diff = orderBestMatch.getCount() - orderNew.getCount();
			if (diff >= 0) {
				orderBestMatch.setCount(diff);
				orderNew.setCount(0);
			} else {
				orderBestMatch.setCount(0);
				orderNew.setCount(diff);
			}
		}

		aList.put(orderNew.getId(),orderNew);

		show();
	}

	void show() {
		if (!showDebug)
			return;
		System.out.print("=====================================");
		System.out.print("\nid\t| buy?\t| count\t| price");
		System.out.print("\n-------------------------------------");
		for (Order order:aList.values()) 
			System.out.printf("\n%d\t| %s\t| %d\t| %d ", order.getId(), order.isBuyS(), order.getCount(),
					order.getPrice());
		System.out.print("\n=====================================");
	}

	int getTotalBuyCount() {
		int i = 0;
		for (Order order:aList.values()) 
			if (order.isBuy())
				i += order.getCount();
		return i;
	}

	int getMaxBuyPrice() {
		int i = 0;
		for (Order order:aList.values()) 
			if (order.isBuy() && (i == 0 || i < order.getPrice()))
				i = order.getPrice();
		return i;
	}
	
	int getTotalSellCount() {
		int i = 0;
		for (Order order:aList.values()) 
			if (!order.isBuy())
				i += order.getCount();
		return i;
	}

	int getMinSellPrice() {
		int i = 0;
		for (Order order:aList.values()) 
			if (!order.isBuy() && (i == 0 || i > order.getPrice()))
				i = order.getPrice();
		return i;
	}	
}
