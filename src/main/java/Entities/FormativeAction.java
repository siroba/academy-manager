package Entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import BaseProject.Database;
import PL53.SI2020_PL53.Date;

/**
 * Domain model data for the courses IMPORTANT: When using the Apache Commons
 * DbUtils components you must Strictly adhere to the Java capitalization
 * convention: - Capitalize all the words that form an identifier except the
 * first letter of method and variable names. - Do not use underscores Follow
 * also these same criteria in the names of tables and fields of the DB.
 */
public class FormativeAction {
	private int ID = -1;
	private String name, objectives, mainContents, location, status;
	private Teacher teacher;
	private TrainingManager manager;
	private int numberOfHours, spaces;
	private float remuneration;
	private Date enrollmentPeriodStart, enrollmentPeriodEnd, day; //TODO: Is this correct?

	public FormativeAction(String name, String objectives, String mainContents, Teacher teacher, TrainingManager manager, float remuneration,
			String location, Date day, String status, int numberOfHours, int spaces, 
			Date enrollmentPeriodStart, Date enrollmentPeriodEnd) {

		this.name = name;
		this.objectives = objectives;
		this.mainContents = mainContents;
		this.teacher = teacher;
		this.manager = manager;
		this.remuneration = remuneration;
		this.location = location;
		this.day = day;
		this.status = status;
		this.numberOfHours = numberOfHours;
		this.spaces = spaces;
		this.enrollmentPeriodStart = enrollmentPeriodStart;
		this.enrollmentPeriodEnd = enrollmentPeriodEnd;
	}

	// Getter
	public int getID() {
		return ID;
	}
	
	public String getName() {
		return this.name;
	}

	public String getObjectives() {
		return this.objectives;
	}

	public String getMainContents() {
		return this.mainContents;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public float getRemuneration() {
		return this.remuneration;
	}

	public String getLocation() {
		return this.location;
	}

	public Date getDay() {
		return this.day;
	}
	
	public String getStatus() {
		return this.status;
	}

	public int getNumberOfHours() {
		return this.numberOfHours;
	}

	public int getSpaces() {
		return this.spaces;
	}

	public Date getEnrollmentPeriodStart() {
		return this.enrollmentPeriodStart;
	}

	public Date getEnrollmentPeriodEnd() {
		return this.enrollmentPeriodEnd;
	}

	public TrainingManager getManager() {
		return manager;
	}


	// Setter
	public void setName(String value) {
		this.name = value;
	}

	public void setObjectives(String value) {
		this.objectives = value;
	}

	public void setMainContents(String value) {
		this.mainContents = value;
	}

	public void setTeacher(Teacher value) {
		this.teacher = value;
	}

	public void setRemuneration(float value) {
		this.remuneration = value;
	}

	public void setLocation(String value) {
		this.location = value;
	}

	public void setDay(Date value) {
		this.day = value;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public void setNumberOfHours(int value) {
		this.numberOfHours = value;
	}

	public void setSpaces(int value) {
		this.spaces = value;
	}

	public void setEnrollmentPeriodStart(Date value) {
		this.enrollmentPeriodStart = value;
	}

	public void setEnrollmentPeriodEnd(Date value) {
		this.enrollmentPeriodEnd = value;
	}
	
	public void setManager(TrainingManager manager) {
		this.manager = manager;
	}
	
	public static String tableName() {
		return "FormativeAction";
	}

	public static FormativeAction obtain(int formativeActionID, Database db) throws SQLException {
		// Creation of the SQL query
			String query = "SELECT * FROM " + tableName();

			Connection conn = db.getConnection();
			// Statement object needed to send statements to the database
			Statement st = conn.createStatement();
			// executeQuery will return a resultSet
			ResultSet rs = st.executeQuery(query.toString());
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
			rs.next();
			/*
			 * name, objectives, mainContents, teacher, renumeration,
			 location,  day, status, numberOfHours, spaces, 
			enrollmentPeriodStart, enrollmentPeriodEnd*/
			FormativeAction fa = new FormativeAction(
					rs.getString("name"), 
					rs.getString("objectives"),
					rs.getString("mainContent"),
					Teacher.obtain(rs.getInt("ID_teacher")),
					TrainingManager.obtain(rs.getInt("ID_trainingManager")),
					rs.getFloat("remuneration"),
					rs.getString("location"),
					Date.parse(rs.getDate("day")), // TODO: what is "day"?
					rs.getString("status"),
					rs.getInt("duration"),
					rs.getInt("places"),
					Date.random(),
					Date.random());


			// Very important to always close all the objects related to the database
			rs.close();
			st.close();
			conn.close();

			return fa;
	}

	public Date getDate() {
		return this.enrollmentPeriodEnd; // TODO: Is this correct?
	}
}