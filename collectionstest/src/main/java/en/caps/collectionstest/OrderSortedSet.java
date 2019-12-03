package en.caps.collectionstest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class OrderSortedSet {

	// ArrayList 300000 items, 400041ms = 6m 40s 41ms
	// 100000 items, 46996ms = 0m 46s 996ms

//	Set<Order> aList;//300000 items, 669200ms = 11m 9s 200ms
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

	public static void main(String[] args) {
		System.out.println("main()");

		// OrderParent.exec(list);
//		OrderArrayList exchange = new OrderArrayList(); //100000 items, 46996ms  = 0m 46s 996ms	
//    	OrderHashMap exchange = new OrderHashMap();	    //100000 items, 67201ms  = 1m 7s 201ms	
		OrderSortedSet exchange = new OrderSortedSet(); // 100000 items, 100296ms = 1m 40s 296ms
		exchange.showDebug = true;

		long time1 = System.currentTimeMillis();
//		System.out.println(time1);
		int i;
		for (i = 1; i <= 10; i++) {
			Random rnd = new Random();
			exchange.orderAdd(new Order(i, (i % 2 == 0), rnd.nextInt(10), rnd.nextInt(10)));
			if (i % 10000 == 0)
				System.out.println(i);
		}
		long time2 = System.currentTimeMillis();
		long timeDiff = time2 - time1;
		int iMin = (int) (timeDiff / 1000 / 60);
		int iSec = (int) (timeDiff - iMin * 1000 * 60) / 1000;
		int iMillis = (int) (timeDiff - iMin * 1000 * 60 - iSec * 1000);
		System.out.printf("%d items, %dms = %dm %ds %dms", i, timeDiff, iMin, iSec, iMillis);
	}

}
