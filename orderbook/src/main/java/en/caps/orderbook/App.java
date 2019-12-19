package en.caps.orderbook;

import java.io.IOException;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Basic order book management" );
        
        String file_in  = "file_in.txt";
        String file_out = "file_out.txt";
        
        if(args.length > 0)
        	file_in = args[0];
        if(args.length > 1)
        	file_out = args[1];        
        
        
        OrderBook ob = new OrderBook(file_in,file_out);
        try {
			ob.processFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
