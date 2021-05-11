package PL53.SI2020_PL53;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Entities.Payment;
import PL53.util.Date;
import PL53.util.DateTime;
import Utils.Database;

public class DateTest {
	long lDNow, lDtNow;
	DateTime dtNow;
	Date dNow;
	Database db;
  
	@Before
	public void setUp() {
		db = new Database();
		
		
		LocalDateTime ldt = LocalDateTime.now();

		lDtNow = ldt.truncatedTo(ChronoUnit.MINUTES).toEpochSecond(ZoneOffset.ofHours(2)) * 1000L;
		lDNow = ldt.truncatedTo(ChronoUnit.DAYS).toEpochSecond(ZoneOffset.ofHours(2)) * 1000L;

		dNow = Date.now();
		dtNow = DateTime.now();
	}

	@After
	public void tearDown() {
		
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
		Date d = Date.fromMillis(lDNow);
		//System.out.println(d);
		assertEquals(lDNow, d.toMillis());
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

		assertEquals(dt1.toEpochSecond(ZoneOffset.ofHours(2)) * 1000L, dt2.toMillis());
	}

	@Test
	public void testStringParsing() throws ParseException {
		int minute = 30;
		int hour = 00;
		int day = 20;
		int month = 12;
		int year = 2021;
		// '2011-12-03T10:15:30'
		String t = year + "-" + month + "-" + day + " 0" + hour + ":" + minute;

		DateTime dt2 = DateTime.parseString(t);

		System.out.println(dt2);

		assertEquals(dt2.getHour(), hour);
		assertEquals(dt2.getMinute(), minute);
		assertEquals(dt2.getDay(), day);
		assertEquals(dt2.getMonth(), month);
		assertEquals(dt2.getYear(), year);
	}

	@Test(expected = java.time.format.DateTimeParseException.class)
	public void testStringParseException() throws ParseException {
		DateTime.parseString("1615067820000");
	}
	
	@Test
	public void testDbInsertion() {		
		Date insert = Date.random();
		
		Payment p = new Payment(1000, 100, insert, true, false, "");
		
		try {
			p.insert(db);
			
			Payment p2 = Payment.getOne("SELECT * FROM Payment WHERE ID_payment=" + p.getID(), db);
			
			Date read = p2.getPayDate();
			
			assertEquals(insert.toString(), read.toString());
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 *  @see <a href="https://www.calculator.net/time-duration-calculator.html?today=11%2F20%2F2021&starthour2=0&startmin2=30&startsec2=0&startunit2=a&ageat=12%2F21%2F2022&endhour2=1&endmin2=31&endsec2=0&endunit2=a&ctype=2&x=73&y=6#twodates"> this </a>
	 */
	@Test
	public void testDateDifference() {
		int minute = 30;
		int hour = 00;
		int day = 20;
		int month = 11;
		int year = 2021;
		// '2011-12-03T10:15:30'
		DateTime dt1 = new DateTime(minute, hour, day, month, year);
		DateTime dt2 = new DateTime(minute+1, hour+1, day+1, month+1, year+1);

		assertEquals(570301, DateTime.minutesSince(dt2, dt1));
	}
}
