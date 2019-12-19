package en.caps.orderbook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class OrderBook {

	List<Order> aList = new ArrayList<Order>();
	
	public void readFile(String sFileName) {
		Path p = Paths.get(sFileName);
		try {
			Stream<String> lines = Files.lines(p);
			String sLine = "";
			lines.forEach(l -> System.out.println(l));
			lines.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public void update(int price, int size, char type) {
		
	}
	
	public void queryBestBid() {
		
	}
	
	public void queryBestAsk() {
		
	}	
	
	public void querySize(int price) {
		
	}
	
	public void orderBuy(int size) {
		
	}
	
	public void orderSell(int size) {
		
	}
	
}
