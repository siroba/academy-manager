package Entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import BaseProject.DbUtil;
import Utils.Database;
import PL53.SI2020_PL53.Date;
import PL53.SI2020_PL53.DateTime;
import PL53.SI2020_PL53.Random;

/**
 * Domain model data for the courses IMPORTANT: When using the Apache Commons
 * DbUtils components you must Strictly adhere to the Java capitalization
 * convention: - Capitalize all the words that form an identifier except the
 * first letter of method and variable names. - Do not use underscores Follow
 * also these same criteria in the names of tables and fields of the DB.
 */
public class FormativeAction {
	private int ID = -1;
	private String name, location, objectives, mainContents, teacherName;
	private Status status;
	private float duration, remuneration, fee;
	private int totalPlaces;
	DateTime enrollmentStart, enrollmentEnd, faStart;

	/**
	 * Constructor that assigns random values
	 */
	public FormativeAction() {
		Random r = new Random();
		this.name = r.name(3, 10);
		this.duration = (float) Math.random() * 2f;
		this.location = r.name(5, 15);
		this.remuneration = (float) Math.random() * 40f;
		this.fee = (float) Math.random() * 80f;
		this.totalPlaces = r.nextInt(100);
		this.objectives = r.name(3, 20);
		this.mainContents = r.name(3, 10);
		this.teacherName = r.name(3, 10);
		this.status = Status.values()[r.nextInt(Status.values().length)];
		this.enrollmentStart = new DateTime(Date.random());
		this.enrollmentEnd = new DateTime(Date.random());
		this.faStart = new DateTime(Date.random());
	}

	/**
	 * Default constructor
	 *
	 * @param name
	 * @param duration
	 * @param location
	 * @param remuneration
	 * @param fee
	 * @param totalPlaces
	 * @param objectives
	 * @param mainContents
	 * @param teacherName
	 * @param status
	 * @param enrollmentStart
	 * @param enrollmentEnd
	 * @param faStart
	 */
	public FormativeAction(String name, float duration, String location, float remuneration, float fee, int totalPlaces,
			String objectives, String mainContents, String teacherName, Status status, DateTime enrollmentStart,
			DateTime enrollmentEnd, DateTime faStart) {

		this.name = name;
		this.duration = duration;
		this.location = location;
		this.remuneration = remuneration;
		this.fee = fee;
		this.totalPlaces = totalPlaces;
		this.objectives = objectives;
		this.mainContents = mainContents;
		this.teacherName = teacherName;
		this.status = status;
		this.enrollmentStart = enrollmentStart;
		this.enrollmentEnd = enrollmentEnd;
		this.faStart = faStart;
	}
	

	/**
	 * Constructor with ID
	 *
	 * @param ID_fa
	 * @param name
	 * @param duration
	 * @param location
	 * @param remuneration
	 * @param fee
	 * @param totalPlaces
	 * @param objectives
	 * @param mainContents
	 * @param teacherName
	 * @param status
	 * @param enrollmentStart
	 * @param enrollmentEnd
	 * @param faStart
	 */
	public FormativeAction(int ID_fa, String name, float duration, String location, float remuneration, float fee, int totalPlaces,
			String objectives, String mainContents, String teacherName, Status status, DateTime enrollmentStart,
			DateTime enrollmentEnd, DateTime faStart) {

		this.ID = ID_fa;
		this.name = name;
		this.duration = duration;
		this.location = location;
		this.remuneration = remuneration;
		this.fee = fee;
		this.totalPlaces = totalPlaces;
		this.objectives = objectives;
		this.mainContents = mainContents;
		this.teacherName = teacherName;
		this.status = status;
		this.enrollmentStart = enrollmentStart;
		this.enrollmentEnd = enrollmentEnd;
		this.faStart = faStart;
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
	public void insert(Database db) throws SQLException, ParseException {
		/*
		 * status TEXT NOT NULL CHECK( status IN('received','confirmed','cancelled')),
		 * dateEn DATE NOT NULL, name TEXT NOT NULL, ID_fa INTEGER NOT NULL UNIQUE,
		 * ID_student INTEGER NOT NULL UNIQUE,
		 */

		String SQL = "INSERT INTO " + tableName() + "(nameFa, duration, location, remuneration, fee, totalPlaces,"
				+ "objectives, mainContent, teacherName, status, enrollmentStart, enrollmentEnd, dateFa) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection conn = db.getConnection(); // Obtain the connection
		// Prepared Statement initialized with the INSERT statement
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		// Sets of the parameters of the prepared statement

		pstmt.setString(1, this.getName());
		pstmt.setFloat(2, this.getDuration());
		pstmt.setString(3, this.getLocation());
		pstmt.setFloat(4, this.getRemuneration());
		pstmt.setFloat(5, this.getFee());
		pstmt.setInt(6, this.getTotalPlaces());
		pstmt.setString(7, this.getObjectives());
		pstmt.setString(8, this.getMainContents());
		pstmt.setString(10, this.getTeacherName());
		pstmt.setString(11, this.getStatus().toString());
		pstmt.setDate(12, this.getEnrollmentStart().toSQL());
		pstmt.setDate(13, this.getEnrollmentEnd().toSQL());
		pstmt.setDate(14, this.getFaStart().toSQL());

		pstmt.executeUpdate(); // statement execution

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
	public static List<FormativeAction> get(String query, Database db) throws SQLException, ParseException {
		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query.toString());

		List<FormativeAction> fa = new ArrayList<FormativeAction>();

		while (rs.next()) {
			FormativeAction f = new FormativeAction(
					rs.getInt("ID_fa"),
					rs.getString("nameFa"),
					rs.getFloat("duration"),
					rs.getString("location"),
					rs.getFloat("remuneration"),
					rs.getFloat("fee"),
					rs.getInt("totalPlaces"),
					rs.getString("objectives"),
					rs.getString("mainContent"), 
					rs.getString("teacherName"),
					Status.valueOf(rs.getString("status").toUpperCase()),
					DateTime.parseString(rs.getString("enrollmentStart")), // TODO: Fix parsing
					DateTime.parseString(rs.getString("enrollmentEnd")),
					DateTime.parseString(rs.getString("dateFA")));

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
		FormativeAction fa = new FormativeAction(
				rs.getInt("ID_fa"),
				rs.getString("nameFa"),
				rs.getFloat("duration"),
				rs.getString("location"),
				rs.getFloat("remuneration"),
				rs.getFloat("fee"),
				rs.getInt("totalPlaces"),
				rs.getString("objectives"),
				rs.getString("mainContent"),
				rs.getString("teacherName"),
				Status.valueOf(rs.getString("status").toUpperCase()),
				DateTime.parseString(rs.getString("enrollmentStart")), // TODO: Fix parsing
				DateTime.parseString(rs.getString("enrollmentEnd")),
				DateTime.parseString(rs.getString("dateFA")));

		// Very important to always close all the objects related to the database
		rs.close();
		st.close();
		conn.close();

		return fa;
	}


	public float refund() {
		return this.refundPercentage()*this.getFee();
	}
	
	public float refundPercentage() {
		int days = Date.daysSince(enrollmentEnd);

		if(days > 7) return 1f;
		else if (days <= 6 && days >=3) return 0.5f;
		else return 0f;
	}
	
	public DateTime getFaStart() {
		return faStart;
	}

	public void setFaStart(DateTime faStart) {
		this.faStart = faStart;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public float getRemuneration() {
		return remuneration;
	}

	public void setRemuneration(float remuneration) {
		this.remuneration = remuneration;
	}

	public float getFee() {
		return fee;
	}

	public void setFee(float fee) {
		this.fee = fee;
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

	public enum Status {
		ACTIVE, EXECUTED, CANCELLED;
	}
}
