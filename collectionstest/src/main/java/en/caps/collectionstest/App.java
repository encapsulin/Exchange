package en.caps.collectionstest;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		System.out.println("main()");

		
		// 300000 items, 197230ms = 3m 17s 230ms
//		OrderArrayList2 exchange = new OrderArrayList2();//100000 items, 21166ms  = 0m 21s 166ms	
		// 300000 items, 394401ms = 6m 34s 401ms
//		OrderArrayList exchange = new OrderArrayList();  //100000 items, 46996ms  = 0m 46s 996ms
//    	OrderHashMap exchange = new OrderHashMap();	     //100000 items, 67201ms  = 1m 7s 201ms	
//		OrderSortedSet exchange = new OrderSortedSet();  //100000 items, 100296ms = 1m 40s 296ms
		OrderTreeSet exchange = new OrderTreeSet();      //100000 items, 110631ms = 1m 50s 631ms		
//		OrderHashSet exchange = new OrderHashSet();      //100000 items, 148761ms = 2m 28s 761ms
		
		
		long time1 = System.currentTimeMillis();
		int i;
//		exchange.showDebug = true;
		for (i = 0; i < 100000; i++) {
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
