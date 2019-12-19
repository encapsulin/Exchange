package en.caps.orderbook;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		Order order = null;

		OrderBook ob = new OrderBook();

		ob.processLine("u,9,1,bid");
		assertTrue(ob.findBest('b').getPrice() == 9);

		ob.processLine("u,11,5,ask");
		assertTrue(ob.findBest('a').getPrice() == 11);
		assertTrue(ob.findBest('b').getPrice() == 9);

		ob.processLine("u,11,5,bid");
		assertTrue(ob.findBest('a') == null);
		assertTrue(ob.findBest('b').getPrice() == 9);
		
		ob.processLine("u,9,2,ask");
		assertTrue(ob.findBest('a').getPrice() == 9);
		assertTrue(ob.findBest('b') == null);
	}
}
