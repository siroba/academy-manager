package PL53.SI2020_PL53;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import org.junit.Before;
import org.junit.Test;

public class DateTest {
	long lDNow, lDtNow;
	DateTime dtNow;
	Date dNow;
	
	@Before
	public void initTest() {
		LocalDateTime ldt = LocalDateTime.now();

		lDtNow = ldt.truncatedTo(ChronoUnit.MINUTES).toEpochSecond(ZoneOffset.ofHours(1))*1000L;
		lDNow = ldt.truncatedTo(ChronoUnit.DAYS).toEpochSecond(ZoneOffset.ofHours(1))*1000L;

		dNow = Date.now();
		dtNow = DateTime.now();
	}

	@Test
	public void testDateTimeMillis() {
		assertEquals(lDtNow, dtNow.toMillis());
	}
	
	@Test
	public void testDateMillis() {
		assertEquals(lDNow, dNow.toMillis());
	}

	@Test
	public void testDateTimeFromMillis() {
		assertEquals(lDtNow, DateTime.fromMillis(lDtNow).toMillis());
	}
	
	@Test
	public void testDateFromMillis() {
		assertEquals(lDNow, Date.fromMillis(lDNow).toMillis());
	}
	
	@Test
	public void testDateTimeCreation() {
		int minute = 30;
		int hour = 00;
		int day = 20;
		int month = 12;
		int year = 2021;
		
		LocalDateTime dt1 = LocalDateTime.of(year, month, day, hour, minute);
		DateTime dt2 = new DateTime(minute, hour, day, month, year);
		
		assertEquals(dt1.toEpochSecond(ZoneOffset.ofHours(1))*1000L, dt2.toMillis());
	}
	
	@Test
	public void testStringParsing() {
		int minute = 30;
		int hour = 00;
		int day = 20;
		int month = 12;
		int year = 2021;
		//'2011-12-03T10:15:30'
		String t = year + "-" + month + "-" + day + " 0" + hour + ":" + minute;

		DateTime dt2;
		try {
			dt2 = DateTime.parseString(t);

			assertEquals(dt2.getHour(), hour);
			assertEquals(dt2.getMinute(), minute);
			assertEquals(dt2.getDay(), day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
