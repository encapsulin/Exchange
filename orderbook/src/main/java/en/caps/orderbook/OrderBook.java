package en.caps.orderbook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class OrderBook {

	List<Order> aList = new ArrayList<Order>();

	String file_in = "";
	String file_out = "";

	public OrderBook(String file_in, String file_out) {
		this.file_in = file_in;
		this.file_out = file_out;
	}

	public void processFile() throws IOException {
		try {

//			File file = new File(file_out);
//			file.createNewFile();
			FileWriter writer;
			writer = new FileWriter(file_out);
//			writer.write("Test data");
			writer.close();

			Stream<String> lines = Files.lines(Paths.get(file_in));
			String sLine = "";
			lines.forEach(l -> processLine(l));
			lines.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	void processLine(String l) {
		System.out.println();
		System.out.println(l);
		String[] sArray = l.split(",");
		// System.out.println(sArray);
		if (sArray.length == 0)
			return;

		if (sArray[0].equals("u") && sArray.length == 4) {
			int price = Integer.parseInt(sArray[1], 10);
			int size = Integer.parseInt(sArray[2], 10);
			char type = Order.parseType(sArray[3]);
			this.update(price, size, type);
		}

		if (sArray[0].equals("q")) {
			if (sArray.length == 2) {
				char type = Order.parseType(sArray[1]);
				this.queryBest(type);
			}
			if (sArray.length == 3) {
				int price = Integer.parseInt(sArray[2], 10);
				this.querySize(price);
			}
		}

		if (sArray[0].equals("o") && sArray.length == 3) {
			char type = Order.parseType(sArray[1]);
			int size = Integer.parseInt(sArray[2], 10);
			this.orderSellOrBuy(type, size);
		}
	}

	public void update(int price, int size, char type) {
		System.out.printf(" -> update(%d,%d,%s)", price, size, type);
		Order order = new Order(price, size, type);
		aList.add(order);
	}

	void fileOutput(String s) {

		try {
			FileWriter fw = new FileWriter(file_out, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(s);
			bw.newLine();
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void queryBest(char bidOrAsk) {
		System.out.printf(" -> queryBest(%s)", bidOrAsk);
		Order order = findBest(bidOrAsk);
		if (order != null) {
			System.out.println(order);
			fileOutput(order.getPrice() + "," + order.getSize());
		}
	}

	public void querySize(int price) {
		System.out.printf(" -> querySize(%d)", price);
		int sum = 0;
		sum = aList.stream().filter(o -> o.getPrice() == price).mapToInt(Order::getSize).sum();
		fileOutput(sum + "");
	}

	public void orderSellOrBuy(char type, int size) {
		ArrayList<Order> aListSorted = new ArrayList<>(aList);
		if (type == 's')
			Collections.sort(aListSorted, new Comparator<Order>() {
				@Override
				public int compare(Order lhs, Order rhs) {
					if (lhs.getPrice() > rhs.getPrice())
						return 1;
					if (lhs.getPrice() < rhs.getPrice())
						return -1;
					return 0;
				}
			});
		if (type == 'b')
			Collections.sort(aListSorted, new Comparator<Order>() {
				@Override
				public int compare(Order lhs, Order rhs) {
					if (lhs.getPrice() > rhs.getPrice())
						return -1;
					if (lhs.getPrice() < rhs.getPrice())
						return 1;
					return 0;
				}
			});
		for (Order o : aListSorted) {
			if (o.getType() == type) {
				int diff = Math.abs(o.getSize() - size);
				size -= diff;
				o.setSize(o.getSize() - diff);
			}
			if (size == 0)
				break;
		}
	}

	public Order findBest(char type) {
		Order order = null;
		if (type == 'b')
			order = aList.stream().filter(o -> o.getType() == type && o.getSize() > 0)
					.max(Comparator.comparing(Order::getPrice)).orElse(null);
		if (type == 'a')
			order = aList.stream().filter(o -> o.getType() == type && o.getSize() > 0)
					.min(Comparator.comparing(Order::getPrice)).orElse(null);
		return order;
	}
}
