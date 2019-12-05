package en.caps.collectionstest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

public class OrderHashSet {

	HashSet<Order> aListSell;
	HashSet<Order> aListBuy;

	boolean showDebug = false;

	public OrderHashSet() {
		aListSell = new HashSet<Order>();
		aListBuy = new HashSet<Order>();
	}

	public void orderAdd(Order order) {
		// aList.add(order);
		addAndMatchCorrespondingOrder(order);
	}

	public void orderDelete(int id) {
//		Iterator<Order> itr = aList.iterator();
//		while (itr.hasNext()) {
//			Order o = itr.next();
//			if (o.getId() == id)
//				itr.remove();
//		}

	}

	void addAndMatchCorrespondingOrder(Order orderNew) {
		if (this.showDebug) {
			System.out.println("\n\norderNew:");
			System.out.print(orderNew);
		}

		int iMinMax = 0;
		Order orderBestMatch = null;
		HashSet<Order> aList;

//		Comparator<Order> comparator = new Comparator<Order>() {
//			@Override
//			public int compare(Order o1, Order o2) {
//				if (o1.getPrice() < o2.getPrice())
//					return -1;
//				else if (o1.getPrice() > o2.getPrice())
//					return 1;
//				return 0;
//			}
//		};
	
		if (orderNew.isBuy()) {
			orderBestMatch = aListSell.stream()
					.filter(order -> order.getCount() > 0 && order.getPrice() <= orderNew.getPrice())
					.min(Comparator.comparing(Order::getPrice))
					// .orElseThrow(NoSuchElementException::new);
					.orElse(null);
		} else {// seller
			orderBestMatch = aListBuy.stream()
					.filter(order -> order.getCount() > 0 && orderNew.getPrice() <= order.getPrice())
					.max(Comparator.comparing(Order::getPrice))
					// .orElseThrow(NoSuchElementException::new);
					.orElse(null);
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

	void show(HashSet<Order> aList) {
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
		return aListBuy.stream().mapToInt(order -> order.getCount()).sum();
	}

	int getMaxBuyPrice() {
		return aListBuy.stream().filter(order -> order.getCount() > 0).mapToInt(order -> order.getPrice()).max()
				.orElse(0);
	}

	int getTotalSellCount() {
		return aListSell.stream().mapToInt(order -> order.getCount()).sum();
	}

	int getMinSellPrice() {
		return aListSell.stream().filter(order -> order.getCount() > 0).mapToInt(order -> order.getPrice()).min()
				.orElse(0);
	}

}
