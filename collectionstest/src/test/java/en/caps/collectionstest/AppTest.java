package en.caps.collectionstest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
		//OrderArrayList exchange = new OrderArrayList();//100000 items, 46996ms = 0m 46s 996ms		
    	OrderHashMap exchange = new OrderHashMap();
		exchange.showDebug = true;
		int i = 0;
		exchange.orderAdd(new Order(i++, true , 4, 7));
		assertTrue( exchange.getMaxBuyPrice()==7 );
		
		exchange.orderAdd(new Order(i++, false, 3, 9));
		assertTrue( exchange.getMinSellPrice()==9 );
		
		exchange.orderAdd(new Order(i++, true, 1, 9));
		assertTrue( exchange.getTotalSellCount()==2 );		
    }
}
