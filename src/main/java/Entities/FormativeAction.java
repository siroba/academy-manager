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
	private String name, objectives, mainContents, location;
	private Teacher teacher;
	private TrainingManager manager;
	private int numberOfHours, spaces;
	private float remuneration, fee;
	private Date date, enrollmentPeriodStart, enrollmentPeriodEnd; //TODO: Is this correct?

	public FormativeAction(String name, String objectives, String mainContents, Teacher teacher, TrainingManager manager, float remuneration,
			String location, Date date, int numberOfHours, int spaces, 
			Date enrollmentPeriodStart, Date enrollmentPeriodEnd, float fee) {

		this.name = name;
		this.objectives = objectives;
		this.mainContents = mainContents;
		this.teacher = teacher;
		this.manager = manager;
		this.remuneration = remuneration;
		this.location = location;
		this.date = date;
		this.numberOfHours = numberOfHours;
		this.spaces = spaces;
		this.enrollmentPeriodStart = enrollmentPeriodStart;
		this.enrollmentPeriodEnd = enrollmentPeriodEnd;
		this.fee = fee;
	}
	
	public FormativeAction(String name, String objectives, String mainContents, Teacher teacher, float remuneration,
			String location, Date date, int numberOfHours, int spaces, 
			Date enrollmentPeriodStart, Date enrollmentPeriodEnd, float fee) {

		this.name = name;
		this.objectives = objectives;
		this.mainContents = mainContents;
		this.teacher = teacher;
		this.manager = null;
		this.remuneration = remuneration;
		this.location = location;
		this.date = date;
		this.numberOfHours = numberOfHours;
		this.spaces = spaces;
		this.enrollmentPeriodStart = enrollmentPeriodStart;
		this.enrollmentPeriodEnd = enrollmentPeriodEnd;
		this.fee = fee;
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

	public Date getDate() {
		return this.date;
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
	
	public float getFee() {
		return this.fee;
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
		this.date = value;
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
	
	public void setFee(float value) {
		this.fee = value;
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
			
			String dateTemp = rs.getString("date");
			/*
			 * name, objectives, mainContents, teacher, remuneration,
			 location,  day, numberOfHours, spaces, 
			enrollmentPeriodStart, enrollmentPeriodEnd*/
			FormativeAction fa = new FormativeAction(
					rs.getString("name"), 
					rs.getString("objectives"),
					rs.getString("mainContent"),
					Teacher.obtain(rs.getInt("ID_teacher")),
					TrainingManager.obtain(rs.getInt("ID_trainingManager")),
					rs.getFloat("remuneration"),
					rs.getString("location"),
					Date.parseString(rs.getString("date")),
					rs.getInt("duration"),
					rs.getInt("places"),
					Date.random(),
					Date.random(),
					rs.getFloat("fee"));


			// Very important to always close all the objects related to the database
			rs.close();
			st.close();
			conn.close();

			return fa;
	}
}
