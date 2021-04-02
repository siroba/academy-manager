package Entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import PL53.util.Date;
import PL53.util.DateTime;
import PL53.util.Random;
import Utils.Database;

/**
 * 
 */
public class FormativeAction {
	private int ID = -1;
	private String name, objectives, mainContents;
	private Status status;
	private int totalPlaces;
	private DateTime enrollmentStart, enrollmentEnd;
	private List<Session> sessions = new ArrayList<Session>();
	private List<Fee> fees = new ArrayList<Fee>();

	/**
	 * Constructor that assigns random values
	 */
	public FormativeAction() {
		Random r = new Random();
		this.name = r.name(3, 10);
		this.totalPlaces = r.nextInt(100);
		this.objectives = r.name(3, 20);
		this.mainContents = r.name(3, 10);
		this.status = Status.values()[r.nextInt(Status.values().length)];
		this.enrollmentStart = new DateTime(Date.random());
		this.enrollmentEnd = new DateTime(Date.random());
	}

	/**
	 * Default constructor
	 *
	 * @param name
	 * @param duration
	 * @param location
	 * @param remuneration
	 * @param totalPlaces
	 * @param objectives
	 * @param mainContents
	 * @param teacherName
	 * @param status
	 * @param enrollmentStart
	 * @param enrollmentEnd
	 * @param faStart
	 */
	public FormativeAction(String name, int totalPlaces,
			String objectives, String mainContents, Status status, DateTime enrollmentStart,
			DateTime enrollmentEnd) {

		this.name = name;
		this.totalPlaces = totalPlaces;
		this.objectives = objectives;
		this.mainContents = mainContents;
		this.status = status;
		this.enrollmentStart = enrollmentStart;
		this.enrollmentEnd = enrollmentEnd;
	}

	/**
	 * Constructor with ID
	 * @param name
	 * @param duration
	 * @param location
	 * @param remuneration
	 * @param totalPlaces
	 * @param objectives
	 * @param mainContents
	 * @param teacherName
	 * @param status
	 * @param enrollmentStart
	 * @param enrollmentEnd
	 * @param faStart
	 */

	public FormativeAction(int ID_fa, String name, int totalPlaces,
			String objectives, String mainContents, Status status, DateTime enrollmentStart,
			DateTime enrollmentEnd, List<Session> sessions) {

		this.ID = ID_fa;
		this.name = name;
		this.totalPlaces = totalPlaces;
		this.objectives = objectives;
		this.mainContents = mainContents;
		this.status = status;
		this.enrollmentStart = enrollmentStart;
		this.enrollmentEnd = enrollmentEnd;
		this.sessions = sessions;
	}

	public static List<FormativeAction> create(int n) {
		List<FormativeAction> faList = new ArrayList<FormativeAction>();

		for (int i = 0; i < n; i++)
			faList.add(new FormativeAction());

		return faList;
	}

	public static String tableName() {
		return "FormativeAction";
	}

	/**
	 * Method to delete all the elements from the table
	 *
	 * @throws SQLException
	 */
	public static void deleteAll(Database db) throws SQLException {
		String SQL = "DELETE FROM " + tableName();

		Connection conn = db.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(SQL);

		pstmt.executeUpdate();
		conn.close();
	}

	/**
	 * Inserts all the given Formative Actions into the given database
	 *
	 * @param professionals
	 * @param db
	 * @throws SQLException
	 * @throws ParseException
	 */
	public static void insert(List<FormativeAction> formativeActions, Database db) throws SQLException, ParseException {
		for (FormativeAction fa : formativeActions)
			fa.insert(db);
	}

	/**
	 * Inserts itself into the given database
	 *
	 * @param db
	 * @throws SQLException
	 * @throws ParseException
	 */
	public void insert(Database db) throws SQLException {
		/*
		 * status TEXT NOT NULL CHECK( status IN('received','confirmed','cancelled')),
		 * dateEn DATE NOT NULL, name TEXT NOT NULL, ID_fa INTEGER NOT NULL UNIQUE,
		 * ID_student INTEGER NOT NULL UNIQUE,
		 */
		Connection conn = db.getConnection(); // Obtain the connection

		if (this.getID() != -1) {
			String SQL = "INSERT INTO " + tableName() + "(ID_fa, nameFa, totalPlaces,"
					+ "objectives, mainContent, status, enrollmentStart, enrollmentEnd) VALUES(?,?,?,?,?,?,?,?)";

			// Prepared Statement initialized with the INSERT statement
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// Sets of the parameters of the prepared statement

			pstmt.setInt(1, this.getID());
			pstmt.setString(2, this.getName());
			pstmt.setInt(4, this.getTotalPlaces());
			pstmt.setString(5, this.getObjectives());
			pstmt.setString(6, this.getMainContents());
			pstmt.setString(7, this.getStatus().toString());
			pstmt.setTimestamp(8, this.getEnrollmentStart().toTimestamp());
			pstmt.setTimestamp(9, this.getEnrollmentEnd().toTimestamp());
			pstmt.executeUpdate(); // statement execution
		} else {
			String SQL = "INSERT INTO " + tableName() + " 	VALUES(null,?,?,?,?,?,?,?)";

			// Prepared Statement initialized with the INSERT statement
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// Sets of the parameters of the prepared statement

			pstmt.setString(1, this.getName());
			pstmt.setInt(3, this.getTotalPlaces());
			pstmt.setString(4, this.getObjectives());
			pstmt.setString(5, this.getMainContents());
			pstmt.setString(6, this.getStatus().toString());
			pstmt.setTimestamp(7, this.getEnrollmentStart().toTimestamp());
			pstmt.setTimestamp(8, this.getEnrollmentEnd().toTimestamp());
			pstmt.executeUpdate(); // statement execution

			ResultSet tableKeys = pstmt.getGeneratedKeys();
			tableKeys.next();
			this.ID = tableKeys.getInt(1);
		}
		
		for(Session s: this.sessions) {
			s.setID_fa(this.getID());
		}

		conn.close();
	}

	/**
	 * Does the query you specify and returns a list with all the results
	 *
	 * @param query
	 * @param db
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 */
	public static List<FormativeAction> get(String query, Database db) throws SQLException {
		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query.toString());

		List<FormativeAction> fa = new ArrayList<FormativeAction>();

		while (rs.next()) {
			DateTime dend, dstart;

			try {
				dend = DateTime.parseString(rs.getString("enrollmentEnd"));
			} catch (ParseException e) {
				dend = DateTime.fromMillis(rs.getLong("enrollmentEnd"));
			}

			try {
				dstart = DateTime.parseString(rs.getString("enrollmentStart"));
			} catch (ParseException e) {
				dstart = DateTime.fromMillis(rs.getLong("enrollmentStart"));
			}

			int id_fa = rs.getInt("ID_fa");
			
			List<Session> sessions = Session.get("SELECT * FROM Session WHERE ID_fa=" + id_fa, db);
			
			FormativeAction f = new FormativeAction(
					id_fa,
					rs.getString("nameFa"),
					rs.getInt("totalPlaces"),
					rs.getString("objectives"),
					rs.getString("mainContent"),
					Status.valueOf(rs.getString("status").toUpperCase()),
					dstart,
					dend,
					sessions);

			fa.add(f);
		}

		// Very important to always close all the objects related to the database
		rs.close();
		st.close();
		conn.close();

		return fa;
	}

	/**
	 * Does the query you specify and returns the first result
	 *
	 * @param query
	 * @param db
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 */
	public static FormativeAction getOne(String query, Database db) throws SQLException, ParseException {
		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query.toString());

		rs.next();

		DateTime dend, dstart;

		try {
			dend = DateTime.parseString(rs.getString("enrollmentEnd"));
		} catch (ParseException e) {
			dend = DateTime.fromMillis(rs.getLong("enrollmentEnd"));
		}

		try {
			dstart = DateTime.parseString(rs.getString("enrollmentStart"));
		} catch (ParseException e) {
			dstart = DateTime.fromMillis(rs.getLong("enrollmentStart"));
		}

		int id_fa = rs.getInt("ID_fa");
		
		List<Session> sessions = Session.get("SELECT * FROM Session WHERE ID_fa=" + id_fa, db);
		
		FormativeAction fa = new FormativeAction(
				id_fa,
				rs.getString("nameFa"),
				rs.getInt("totalPlaces"),
				rs.getString("objectives"),
				rs.getString("mainContent"),
				Status.valueOf(rs.getString("status").toUpperCase()),
				dstart,
				dend,
				sessions);

		// Very important to always close all the objects related to the database
		rs.close();
		st.close();
		conn.close();

		return fa;
	}

//	public float refund() {
//        return this.refundPercentage()*this.getFee();
//    }
//  
//    public float refundPercentage() {
//        int days = Date.daysSince(enrollmentEnd);
//
//        if(days > 7) return 1f;
//        else if (days <= 6 && days >=3) return 0.5f;
//        else return 0f;
//    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObjectives() {
		return objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	public String getMainContents() {
		return mainContents;
	}

	public void setMainContents(String mainContents) {
		this.mainContents = mainContents;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getTotalPlaces() {
		return totalPlaces;
	}

	public void setTotalPlaces(int totalPlaces) {
		this.totalPlaces = totalPlaces;
	}

	public DateTime getEnrollmentStart() {
		return enrollmentStart;
	}

	public void setEnrollmentStart(DateTime enrollmentStart) {
		this.enrollmentStart = enrollmentStart;
	}

	public DateTime getEnrollmentEnd() {
		return enrollmentEnd;
	}

	public void setEnrollmentEnd(DateTime enrollmentEnd) {
		this.enrollmentEnd = enrollmentEnd;
	}

	public int getID() {
		return ID;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		for(Session s: sessions)
			s.setID_fa(this.getID());
		
		this.sessions = sessions;
	}

	public void addSession(Session session) {
		session.setID_fa(this.getID());
		this.sessions.add(session);
	}

	public List<Fee> getFees() {
		return fees;
	}

	public void setFees(List<Fee> fees) {
		for(Fee f: fees)
			f.setID_fa(this.getID());
		
		this.fees = fees;
	}

	public void addFee(Fee fee) {
		fee.setID_fa(this.getID());
		this.fees.add(fee);
	}
	
	public enum Status {
		ACTIVE, DELAYED, EXECUTED, CANCELLED;
	}
}