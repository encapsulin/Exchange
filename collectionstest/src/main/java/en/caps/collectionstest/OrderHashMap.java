package en.caps.collectionstest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

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
	
	public static void main(String[] args) {
		System.out.println("main()");
		
		//OrderParent.exec(list);
//		OrderArrayList exchange = new OrderArrayList(); //100000 items, 46996ms  = 0m 46s 996ms	
    	OrderHashMap exchange = new OrderHashMap();	    //100000 items, 67201ms  = 1m 7s 201ms	
//		OrderSortedSet exchange = new OrderSortedSet();	//100000 items, 100296ms = 1m 40s 296ms
		exchange.showDebug = true;
		
		long time1 = System.currentTimeMillis();
//		System.out.println(time1);
		int i;
		for (i = 0; i < 10; i++) {
			Random rnd = new Random();
			exchange.orderAdd(new Order(i, (i % 2 == 0), rnd.nextInt(10), rnd.nextInt(10)));
			if(i%10000==0)
				System.out.println(i);
		}
		long time2 = System.currentTimeMillis();
		long timeDiff = time2 - time1;
		int iMin = (int) (timeDiff / 1000 / 60);
		int iSec = (int) (timeDiff - iMin * 1000 * 60)/1000;
		int iMillis = (int) (timeDiff - iMin * 1000 * 60 - iSec * 1000);
		System.out.printf("%d items, %dms = %dm %ds %dms", i, timeDiff, iMin, iSec, iMillis);

	}

}
