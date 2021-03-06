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
		assertEquals(lDNow, Date.fromMillis(lDNow).toMillis());
	}
}
