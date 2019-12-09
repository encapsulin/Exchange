package en.caps.collectionstest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class TestArrayList {

	List<Order> aList;

	public TestArrayList() {
		aList = new ArrayList<>();
	}

	class SortByPrice implements Comparator<Order>{

		@Override
		public int compare(Order o1, Order o2) {
			if(o1.getPrice() > o2.getPrice())
				return 1;
			if(o1.getPrice() < o2.getPrice())
				return -1;			
			return 0;
		}
		
	}
	
	public void run(int iCnt) {
		int i = 0;
		do {
			Random rnd = new Random();
			Order order = new Order(i, true, rnd.nextInt(10), rnd.nextInt(20));
			aList.add(order);
		} while (++i < iCnt);
		System.out.println(aList.size());
		Collections.sort( aList, new SortByPrice());
		System.out.println(aList);		
		System.out.println(aList.get(0));
		System.out.println(aList.get(iCnt-1));
	}

	public Order searchMaxUsingOldschool() {
		Order order = null;
		for (Order o : aList) {
			if (order == null || (order.getCount() > 0 && order.getPrice() < o.getPrice()))
				order = o;
		}
		return order;
	}

	public Order searchMaxUsingStream() {
		Order order = null;
		order = aList.stream().filter(o -> o.getCount() > 0).max(Comparator.comparing(Order::getPrice)).orElse(null);
		return order;
	}

	public static void main(String[] args) {
		TestArrayList test = new TestArrayList();
		test.run(10);
		
		Order order = null;
		
		for (int i = 0; i < 10000; i++)
			order = test.searchMaxUsingOldschool();
		System.out.println(order);
		
		for (int i = 0; i < 10000; i++)
			order = test.searchMaxUsingStream();
		System.out.println(order);
	}

}
