package PL53.SI2020_PL53;

import org.junit.Test;
import static org.junit.Assert.*;

import PL53.util.Date;
import PL53.util.DateTime;

public class RegisterPaymentTest {

	@Test
	public void dateInTheFuture() {
		Date testDate = new Date(1, 1, 2021);
		
		// The days to/from the given date
		int difference = Date.daysSince(testDate);
		
		boolean isFuture = difference>0;
		
		assertFalse(isFuture);
	}
	
	@Test
	public void dateInThePast1_3_1() {
		Date testDate = new Date(1, 1, 2021);
		
		// The days to/from the given date
		int difference = Date.daysSince(testDate);

		boolean pastOrCurrentDate = difference<=0; // Date in the past or in the current date
		
		assertTrue(pastOrCurrentDate); 
		
		int hoursDifference = (int)(DateTime.minutesSince(new DateTime(testDate))/60.0f);
		
		assertTrue(hoursDifference<48); // The date is in the period of 48 hours stipulated
	}
	
	@Test
	public void dateInThePast1_3_2_1() {
		Date testDate = new Date(1, 1, 2021);
		
		// The days to/from the given date
		int difference = Date.daysSince(testDate);

		boolean pastOrCurrentDate = difference<=0; // Date in the past or in the current date
		
		assertTrue(pastOrCurrentDate); 
		
		int hoursDifference = Math.abs((int)(DateTime.minutesSince(new DateTime(testDate))/60.0f));
		
		assertFalse(hoursDifference<=48); // The date is in the period of 48 hours stipulated
	}
	
	@Test
	public void dateInThePast1_3_2_2() {
		Date testDate = new Date(1, 1, 2021);
		
		// The days to/from the given date
		int difference = Date.daysSince(testDate);

		boolean pastOrCurrentDate = difference<=0; // Date in the past or in the current date
		
		assertTrue(pastOrCurrentDate); 
		
		int hoursDifference = Math.abs((int)(DateTime.minutesSince(new DateTime(testDate))/60.0f));
		
		assertTrue(hoursDifference>48); // The date is in the period of 48 hours stipulated
	}
	
	
}
