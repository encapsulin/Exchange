package exchangetask;

import java.util.Random;

public class Main {

	public static void main(String[] args) throws RequestRejectedException {
		// TODO Auto-generated method stub
		System.out.println("main()");
		Exchange exchange = new Exchange();
		int iOrdersNo = 5;
		System.out.println("Populating the Exchange with random Orders");
		for (int i = 0; i < iOrdersNo; i++) {
			long leftLimit = 1L;
			long rightLimit = 1000000L;
			long orderId = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
			boolean isBuy = (i % 2 == 0) ? true : false;
			int price = (int) (Math.random() * (20));
			int size = (int) (Math.random() * (100));
			exchange.send(orderId, isBuy, price, size);
			exchange.show();
		}

//		exchange.send(1, false, 10, 35);
//		exchange.show();
//		exchange.send(2, true, 11, 53);
//		exchange.show();


		System.out.println("#################");
		System.out.println("getLowestSellPrice: " + exchange.getLowestSellPrice());
		System.out.println("getHighestBuyPrice: " + exchange.getHighestBuyPrice());

		for (int i = 0; i < 3; i++)
			System.out.println("getTotalSizeAtPrice(" + i + "): " + exchange.getTotalSizeAtPrice(i));

		System.out.println("Done.");
	}

	
}
