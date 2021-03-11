package PL53.SI2020_PL53;

import static org.junit.Assert.assertEquals;

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
		Date d = Date.fromMillis(lDNow);
		System.out.println(d);
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

		assertEquals(dt1.toEpochSecond(ZoneOffset.ofHours(1)) * 1000L, dt2.toMillis());
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

		assertEquals(dt2.getHour(), hour);
		assertEquals(dt2.getMinute(), minute);
		assertEquals(dt2.getDay(), day);
		assertEquals(dt2.getMonth(), month);
		assertEquals(dt2.getYear(), year);
	}

	@Test(expected = ParseException.class)
	public void testStringParseException() throws ParseException {
		DateTime.parseString("1615067820000");
	}
	
	/*@Test
	public void testDbInsertion() {
		//String sql = "INSERT INTO Payment VALUES(null,?,?,?,?,?,?,?,?,?)";
		
		Random r = new Random();
		
		DateTime insert = new DateTime(Date.random());
		System.out.println(insert);
		
		Payment p = new Payment(1000, 2000, r.nextFloat()*100f, insert, r.name(3, 10),r.name(3, 10),r.name(3, 10),r.name(3, 10), false);
		try {
			p.insert(db);
			
			Payment p2 = Payment.getOne("SELECT * FROM Payment WHERE ID_payment=" + p.getID(), db);
			
			DateTime read = p2.getPayDate();
			System.out.println(read);
			
			assertEquals(insert.toString(), read.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
	
	@Test
	public void testDbExtraction() {
		Random r = new Random();
		String sql = "INSERT INTO Payment (ID_payment, ID_fa, ID_professional, amount, payDate, sender, receiver, fiscalNumber, address, confirmed) VALUES(?,?,?,?,?,?,?,?,?,?)";
		
		db.executeUpdate(sql, 1, 1000, 2000, r.nextFloat()*100f, "2021-03-09 12:05", r.name(3, 10),r.name(3, 10),r.name(3, 10),r.name(3, 10), false);
		
		System.out.println(db.executeQueryArray("SELECT datePay FROM Payment WHERE ID_payment=1").get(0).toString());
	}

	@Test
	public void testMinutes() {
		DateTime d1 = new DateTime(0, 0, 1, 1, 2021);
		DateTime d2 = new DateTime(3, 0, 1, 1, 2021);
		
		assertEquals(DateTime.minutesSince(d1, d2), d1.getMinute()-d2.getMinute());
	}
	

	@Test
	public void testDays() {
		Date d1 = new Date(1, 1, 2021);
		Date d2 = new Date(3, 1, 2021);
		
		assertEquals(DateTime.daysSince(d1, d2), d1.getDay()-d2.getDay());
	}
}
