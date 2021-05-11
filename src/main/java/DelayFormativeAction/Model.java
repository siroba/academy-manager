package DelayFormativeAction;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import Entities.FormativeAction;
import Entities.Session;
import PL53.swing.DateTimeInput;
import PL53.util.DateTime;
import Utils.Database;

public class Model {
	private Database db = new Database();
	private FormativeAction[] data;

	public void initModel() throws SQLException, ParseException {
		String query = "SELECT * FROM FormativeAction WHERE status IN ('ACTIVE', 'DELAYED')";

		List<FormativeAction> la = FormativeAction.get(query, db);

		data = new FormativeAction[la.size()];
		data = la.toArray(data);
	}

	public FormativeAction[] getAllData() {
		return data;
	}

	public FormativeAction getData(int selected) {
		return data[selected];
	}

	/**
	 * Delays a Formative Action and all of its sessions.
	 * 
	 * @param fa Formative Action to delay
	 * @param dateTime Delay amount (in datetime object)
	 */
	public void delay(FormativeAction fa, DateTime dateTime) {		
		String sessionQuery = "UPDATE Session SET sessionStart=? WHERE ID_session=?";
		
		for(Session s: fa.getSessions()) // Delay all the sessions by the same amount
			db.executeUpdate(sessionQuery, delay(s.getSessionStart(), dateTime).toSQLiteString(), s.getID());
		
		// Delay the end of the enrollment period by the same amount
		DateTime enEnd = delay(fa.getEnrollmentEnd(), dateTime);
		
		String query = "UPDATE FormativeAction SET enrollmentEnd=?, status=? WHERE ID_fa=?";
		
		db.executeUpdate(query, enEnd.toSQLiteString(), FormativeAction.Status.DELAYED.toString(), fa.getID());
	}

	private static int daysInMonth[] = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public DateTime delay(DateTime dt, DateTime dti) {
		int minute = dt.getMinute() + dti.getMinute();
		int hour = dt.getHour() + dti.getHour();
		int day = dt.getDay() + dti.getDay();
		int month = dt.getMonth() + dti.getMonth();
		int year = dt.getYear() + dti.getYear();
		
		
		
		if(minute > 59) {
			hour += minute/60;
			minute = minute%60;
		}
		
		if(hour > 23) {
			day += hour/24;
			hour = hour%24;
		}
		
		if(month > 12) {
			year += month/12;
			month = month%12;
		}
		
		if(day > daysInMonth[month-1]) {
			int daysInThisMonth = daysInMonth[month-1];
			month += day/daysInThisMonth;
			day = day%daysInThisMonth;
		}
		
		if(month > 12) {
			year += month/12;
			month = month%12;
		}
		
		return new DateTime(minute, hour, day, month, year);
	}

	public void refund(int selected) {
		FormativeAction fa = data[selected];
		
		
	}
}
