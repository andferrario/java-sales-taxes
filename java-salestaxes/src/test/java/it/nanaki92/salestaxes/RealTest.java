package it.nanaki92.salestaxes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RealTest {
	
	private static final int STANDARD_TAX = 10;
	private static final int IMPORTED_TAX = 5;
	private CashRegister cashRegister;
	
	@Before
	public void setUp() {
		cashRegister = new CashRegister(STANDARD_TAX, IMPORTED_TAX);
    }
	
	@Test
	public void input1() throws Exception {
		
		cashRegister.checkoutOrder("1 book at 12.49\n" + 
				"1 music CD at 14.99\n" + 
				"1 chocolate bar at 0.85");
		
		String recipit = cashRegister.printRecipit();
		
		String expected = "1 book: 12.49\n" + 
				"1 music CD: 16.49\n" + 
				"1 chocolate bar: 0.85\n" + 
				"Sales Taxes: 1.50\n" + 
				"Total: 29.83";
		assertEquals(expected, recipit);
	}
	
	@Test
	public void input2() throws Exception {
		
		cashRegister.checkoutOrder("1 imported box of chocolates at 10.00\n" + 
				"1 imported bottle of perfume at 47.50");
		
		String recipit = cashRegister.printRecipit();
		
		String expected = "1 imported box of chocolates: 10.50\n" + 
				"1 imported bottle of perfume: 54.65\n" + 
				"Sales Taxes: 7.65\n" + 
				"Total: 65.15";
		assertEquals(expected, recipit);
	}
	
	@Test
	public void input3() throws Exception {
		
		cashRegister.checkoutOrder("1 imported bottle of perfume at 27.99\n" + 
				"1 bottle of perfume at 18.99\n" + 
				"1 packet of headache pills at 9.75\n" + 
				"1 box of imported chocolates at 11.25");
		
		String recipit = cashRegister.printRecipit();
		
		String expected = "1 imported bottle of perfume: 32.19\n" + 
				"1 bottle of perfume: 20.89\n" + 
				"1 packet of headache pills: 9.75\n" + 
				"1 imported box of chocolates: 11.85\n" + 
				"Sales Taxes: 6.70\n" + 
				"Total: 74.68";
		assertEquals(expected, recipit);
	}
	
}
