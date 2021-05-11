package delayFormativeAction;

import org.junit.Before;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import DelayFormativeAction.Model;
import Entities.FormativeAction;
import Entities.Session;
import PL53.util.DateTime;
import Utils.Database;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Tests {
	static Database db;
	static Model model;
	DateTime input, addition, expected;
	
	public Tests(DateTime input, DateTime addition, DateTime expected){
		this.input = input;
		this.addition = addition;
		this.expected = expected;
	}
	
	@Before
	public void onSetUp() throws SQLException, ParseException {
		db = new Database();
		model = new Model();
		//model.initModel();
	}


    @Parameterized.Parameters
	public static Collection<DateTime[]> datetimes() {
		return Arrays.asList(new DateTime[][] {
			// 		Given Date and Time		   Addition to the given datetime			Expected output
			{new DateTime(0, 0, 31, 12, 2021),	new DateTime(0, 0, 1, 0, 0),	new DateTime(0, 0, 1, 1, 2022)},
			{new DateTime(59, 23, 31, 3, 2021), new DateTime(1, 0, 0, 0, 0),	new DateTime(0, 0, 1, 4, 2021)},
			{new DateTime(59, 23, 28, 2, 2021), new DateTime(1, 0, 0, 0, 0),	new DateTime(0, 0, 1, 3, 2021)}
		});
	}

	@After
	public void onTearDown() {}
	
	/**
	 * Here, we test the function to add a number of hours, minutes, days, months and years to a date
	 */
	@Test
	public void delayTest() {
		DateTime delayed = model.delay(input, addition);
		
		assertEquals(0, delayed.compareTo(expected));
	}

	@Test
	public void delayFATest() throws SQLException, ParseException {
		DateTime delayAmount = new DateTime(0, 0, 0, 1, 0);
		
		FormativeAction fa = new FormativeAction(
				"Scrum workshop",				// Name of the FA
				10,								// Number of places
				"These are the objectives",		// Objectives
				"These are the main contents",	// Main contents
				FormativeAction.Status.ACTIVE,	// Status
				input,							// Enrollment start
				expected						// Enrollment end
			);
		
		for(int i = 3; i<7; i++)
			fa.addSession(new Session(
					"Classroom B",
					1,
					model.delay(expected, new DateTime(0, 0, i, 0, 0)) // One class per day
				));
		
		// Insert the new FA into the db
		// This also gets the assigned ID for the FA from the db
		fa.insert(db); 
		// Also insert all of its sessions
		for(int i = 0; i<fa.getSessions().size(); i++)
			fa.getSessions().get(i).insert(db);
		
		
		// Let's delay the Formative Action by 1 month
		model.delay(fa, delayAmount);
		
		// Get the delayed FA from the db
		FormativeAction delayedFa = FormativeAction.getOne("SELECT * FROM FormativeAction WHERE ID_fa=" + fa.getID(), db);
		
		// Calculate the two dates to be compared
		DateTime expectedEnrollmentEnd = model.delay(fa.getEnrollmentEnd(), delayAmount);
		DateTime delayedEnrollmentEnd = delayedFa.getEnrollmentEnd();
		
		assertEquals(0, expectedEnrollmentEnd.compareTo(delayedEnrollmentEnd));
		
		// And now let's check all the dates from all the sessions
		for(int i = 0; i<fa.getSessions().size(); i++) {
			DateTime sessionDate = fa.getSessions().get(i).getSessionStart();
			
			DateTime expectedSession = model.delay(sessionDate, delayAmount);
			DateTime delayedSession = delayedFa.getSessions().get(i).getSessionStart();
			
			assertEquals(0, expectedSession.compareTo(delayedSession));
		}
	}
}
