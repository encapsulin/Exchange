package en.caps.orderbook;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Basic order book management" );
        
        String file_in = "file_in.txt";
        String file_out = "file_out.txt";
        if(args.length > 0)
        	filename = args[0];
        
        OrderBook ob = new OrderBook();
        ob.readFile(filename);
    }
}
