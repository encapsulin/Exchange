package en.caps.collectionstest;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App {
	
	public static void main(String[] args) {
		System.out.println("main()");
		
		//OrderParent.exec(list);
//		OrderArrayList exchange = new OrderArrayList(); //100000 items, 46996ms  = 0m 46s 996ms	
//    	OrderHashMap exchange = new OrderHashMap();	    //100000 items, 67201ms  = 1m 7s 201ms	
		OrderSortedSet exchange = new OrderSortedSet();	//100000 items, 100296ms = 1m 40s 296ms
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
