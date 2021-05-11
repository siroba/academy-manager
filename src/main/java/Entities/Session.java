package Entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import PL53.util.DateTime;
import Utils.Database;

public class Session {
	private int ID = -1, ID_fa = -1;
	private String location;
	private int numberOfHours;
	private DateTime sessionStart;

	/**
	 * @param location
	 * @param numberOfHours
	 * @param sessionStart
	 */
	public Session(String location, int numberOfHours, DateTime sessionStart) {
		this.location = location;
		this.numberOfHours = numberOfHours;
		this.sessionStart = sessionStart;
	}

	/**
	 * @param ID
	 * @param ID_fa
	 * @param location
	 * @param numberOfHours
	 * @param sessionStart
	 */
	public Session(int ID, int ID_fa, String location, int numberOfHours, DateTime sessionStart) {
		this.ID = ID;
		this.ID_fa = ID_fa;
		this.location = location;
		this.numberOfHours = numberOfHours;
		this.sessionStart = sessionStart;
	}

	public static String tableName() {
		return "Session";
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
	 * Inserts all the given Sessions into the given database
	 *
	 * @param sessions
	 * @param db
	 * @throws SQLException
	 * @throws ParseException
	 */
	public static void insert(List<Session> sessions, Database db) throws SQLException, ParseException {
		for (Session s : sessions)
			s.insert(db);
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
			String SQL = "INSERT INTO " + tableName() + " VALUES(?,?,?,?,?)";

			// Prepared Statement initialized with the INSERT statement
			PreparedStatement pstmt = conn.prepareStatement(SQL);

			pstmt.setInt(1, getID());
			pstmt.setInt(2, getID_fa());
			pstmt.setString(3, getLocation());
			pstmt.setInt(4, getNumberOfHours());
			pstmt.setString(5,  getSessionStart().toSQLiteString());

			pstmt.executeUpdate(); // statement execution
			pstmt.close();
		} else {
			String SQL = "INSERT INTO " + tableName() + " VALUES(null,?,?,?,?)";

			// Prepared Statement initialized with the INSERT statement
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// Sets of the parameters of the prepared statement

			pstmt.setInt(1, getID_fa());
			pstmt.setString(2, getLocation());
			pstmt.setInt(3, getNumberOfHours());
			pstmt.setString(4,  getSessionStart().toSQLiteString());
			pstmt.executeUpdate(); // statement execution

			ResultSet tableKeys = pstmt.getGeneratedKeys();
			tableKeys.next();
			this.ID = tableKeys.getInt(1);
			pstmt.close();
			tableKeys.close();
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
	public static List<Session> get(String query, Database db) throws SQLException {
		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query.toString());

		List<Session> sessions = new ArrayList<Session>();

		while (rs.next()) {
			DateTime dstart;

			try {
				dstart = DateTime.parseString(rs.getString("sessionStart"));
			} catch (ParseException e) {
				dstart = DateTime.fromMillis(rs.getLong("sessionStart"));
			}

			Session s = new Session(rs.getInt("ID_session"), rs.getInt("ID_fa"), rs.getString("location"), rs.getInt("duration"), dstart);

			sessions.add(s);
		}

		// Very important to always close all the objects related to the database
		rs.close();
		st.close();
		conn.close();

		return sessions;
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
	public static Session getOne(String query, Database db) throws SQLException {
		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query.toString());

		rs.next();

		DateTime dstart;

		try {
			dstart = DateTime.parseString(rs.getString("sessionStart"));
		} catch (ParseException e) {
			dstart = DateTime.fromMillis(rs.getLong("sessionStart"));
		}

		Session s = new Session(rs.getInt("ID_session"), rs.getInt("ID_fa"), rs.getString("location"), rs.getInt("duration"), dstart);

		// Very important to always close all the objects related to the database
		rs.close();
		st.close();
		conn.close();

		return s;
	}

	public int getID() {
		return ID;
	}

	public int getID_fa() {
		return ID_fa;
	}

	public void setID_fa(int ID_fa) {
		this.ID_fa = ID_fa;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getNumberOfHours() {
		return numberOfHours;
	}

	public void setNumberOfHours(int numberOfHours) {
		this.numberOfHours = numberOfHours;
	}

	public DateTime getSessionStart() {
		return sessionStart;
	}

	public void setSessionStart(DateTime sessionStart) {
		this.sessionStart = sessionStart;
	}
}
