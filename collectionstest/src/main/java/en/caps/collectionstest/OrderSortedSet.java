package en.caps.collectionstest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class OrderSortedSet {

	// 300000 items, 400041ms = 6m 40s 41ms
	// 100000 items, 46996ms = 0m 46s 996ms

//	Set<Order> aList;
	Set<Order> aListSell;
	Set<Order> aListBuy;

	boolean showDebug = false;

	public OrderSortedSet() {
		aListSell = new TreeSet<Order>(new Comparator<Order>() {
			@Override
			public int compare(Order o1, Order o2) {
				if (o1.getPrice() > o2.getPrice())
					return 1;

				return -1;
			}
		});
		aListBuy = new TreeSet<Order>(new Comparator<Order>() {
			@Override
			public int compare(Order o1, Order o2) {
				if (o1.getPrice() > o2.getPrice())
					return -1;
				return 1;
			}
		});
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
			System.out.print(orderNew);
		}

		int iMinMax = 0;
		Order orderBestMatch = null;
		Set<Order> aList;

		if (orderNew.isBuy())
			aList = aListSell;
		else
			aList = aListBuy;

		for (Order order : aList) {
			if (order.getCount() == 0 || (orderNew.isBuy() && order.getPrice() > orderNew.getPrice())
					|| (!orderNew.isBuy() && order.getPrice() < orderNew.getPrice()))
				continue;
			orderBestMatch = order;
			break;
		}

		if (orderBestMatch != null) {
			if (this.showDebug) {
				System.out.println("orderBestMatch:");
				System.out.print(orderBestMatch);
			}
			int diff = orderBestMatch.getCount() - orderNew.getCount();
			if (diff >= 0) {
				orderBestMatch.setCount(diff);
				orderNew.setCount(0);
			} else {
				orderBestMatch.setCount(0);
				orderNew.setCount(-diff);
			}
		}

		if (orderNew.isBuy())
			aListBuy.add(orderNew);
		else
			aListSell.add(orderNew);

		show(aListBuy);
		show(aListSell);
	}

	void show(Set<Order> aList) {
		if (!showDebug)
			return;
		System.out.print("\n=====================================");
		System.out.print("\nid\t| buy?\t| count\t| price");
		System.out.print("\n-------------------------------------");
		for (Order order : aList)
			System.out.printf("\n%d\t| %s\t| %d\t| %d ", order.getId(), order.isBuyS(), order.getCount(),
					order.getPrice());
		System.out.print("\n=====================================");
	}

	int getTotalBuyCount() {
		int i = 0;
		for (Order order : aListBuy)
			i += order.getCount();
		return i;
	}

	int getMaxBuyPrice() {
		int i = 0;
		for (Order order : aListBuy)
			if (order.getCount() > 0 && (i == 0 || i < order.getPrice()))
				i = order.getPrice();
		return i;
	}

	int getTotalSellCount() {
		int i = 0;
		for (Order order : aListSell)
			i += order.getCount();
		return i;
	}

	int getMinSellPrice() {
		int i = 0;
		for (Order order : aListSell)
			if (order.getCount() > 0 && (i == 0 || i > order.getPrice()))
				i = order.getPrice();
		return i;
	}
}
