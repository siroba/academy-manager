package Entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Utils.Database;
import PL53.SI2020_PL53.Date;
import PL53.SI2020_PL53.DateTime;
import PL53.SI2020_PL53.Random;

public class Enrollment {
	private int ID_fa, ID_professional;
	private Status status;
	private DateTime timeEn;

	/**
	 * Enrollment default constructor. The date and time are assumed to be today and now.
	 *
	 * @param name
	 * @param status
	 * @param formativeAction
	 * @param professional
	 */
	public Enrollment(int ID_fa, int ID_professional, Status status, DateTime timeEn) {
		this.status = status;
		this.timeEn = timeEn;
		this.ID_fa = ID_fa;
		this.ID_professional = ID_professional;
	}

	/**
	 * Enrollment random constructor
	 *
	 * @param name
	 * @param status
	 * @param formativeAction
	 * @param professional
	 */
	public Enrollment() {
		Random r = new Random();

		this.status = Status.values()[r.nextInt(Status.values().length)];
		this.timeEn = new DateTime(Date.random());
		this.ID_fa = -1;
		this.ID_professional = -1;
	}

	public static String tableName() {
		return "Enrollment";
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
	 * Does the query you specify and returns a list with all the results
	 *
	 * @param query
	 * @param db
	 * @return
	 * @throws SQLException
	 */
	public static List<Enrollment> get(String query, Database db) throws SQLException {
		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query.toString());

		List<Enrollment> enrollments = new ArrayList<>();

		while (rs.next()) {
			Enrollment e = new Enrollment(
					rs.getInt("ID_fa"),
					rs.getInt("ID_professional"),
					Status.valueOf(rs.getString("status")),
					new DateTime(Date.parse(rs.getTimestamp("dateEn")))); // TODO: Fix parse

			enrollments.add(e);
		}

		// Very important to always close all the objects related to the database
		rs.close();
		st.close();
		conn.close();

		return enrollments;
	}

	/**
	 * Does the query you specify and returns the first result
	 *
	 * @param query
	 * @param db
	 * @return
	 * @throws SQLException
	 */
	public static Enrollment getOne(String query, Database db) throws SQLException {
		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query.toString());
		rs.next();

		Enrollment e = new Enrollment(
					rs.getInt("ID_fa"),
					rs.getInt("ID_professional"),
					Status.valueOf(rs.getString("status")),
					new DateTime(Date.parse(rs.getTimestamp("dateEn")))); // TODO: Fix parse

		// Very important to always close all the objects related to the database
		rs.close();
		st.close();
		conn.close();

		return e;
	}

	/**
	 * Inserts all the given enrollments into the given database
	 *
	 * @param professionals
	 * @param db
	 * @throws SQLException
	 */
	public static void insert(List<Professional> professionals, Database db) throws SQLException {
		for (Professional p : professionals)
			p.insert(db);
	}

	/**
	 * Inserts itself into the given database
	 *
	 * @param db
	 * @throws SQLException
	 * @throws ParseException
	 */
	public void insert(Database db) throws SQLException, ParseException {
		/*
		 * status TEXT NOT NULL CHECK( status IN('received','confirmed','cancelled')),
		 * dateEn DATE NOT NULL, name TEXT NOT NULL, ID_fa INTEGER NOT NULL UNIQUE,
		 * ID_student INTEGER NOT NULL UNIQUE,
		 */

		String SQL = "INSERT INTO " + tableName() + "(ID_fa, ID_professional, status, timeEn) VALUES(?,?,?,?)";

		Connection conn = db.getConnection(); // Obtain the connection
		// Prepared Statement initialized with the INSERT statement
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		// Sets of the parameters of the prepared statement

		pstmt.setInt(1, this.getID_fa());
		pstmt.setInt(2, this.getID_professional());
		pstmt.setString(3, this.getStatus().toString().toLowerCase());
		pstmt.setDate(4, this.getTimeEn().toSQL());

		pstmt.executeUpdate(); // statement execution

		conn.close();
	}



	public int getID_fa() {
		return ID_fa;
	}

	public void setID_fa(int iD_fa) {
		ID_fa = iD_fa;
	}

	public int getID_professional() {
		return ID_professional;
	}

	public void setID_professional(int iD_professional) {
		ID_professional = iD_professional;
	}

	public DateTime getTimeEn() {
		return timeEn;
	}

	public void setTimeEn(DateTime timeEn) {
		this.timeEn = timeEn;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public enum Status {
		RECEIVED, CONFIRMED, CANCELLED;
	}
}
