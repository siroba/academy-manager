package PL53.SI2020_PL53;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import org.junit.Before;
import org.junit.Test;

import Entities.Payment;
import Utils.Database;

public class DateTest {
	long lDNow, lDtNow;
	DateTime dtNow;
	Date dNow;
	Database db = new Database();
  
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
	}
	
	public void testDbExtraction() {
		
	}
}
