package Entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Utils.Database;

/**
 * Domain model data for the formative actions
 * IMPORTANT: When using the Apache Commons DbUtils components you must
 * Strictly adhere to the Java capitalization convention:
 * - Capitalize all the words that form an identifier 
 * except the first letter of method and variable names.
 * - Do not use underscores
 * Follow also these same criteria in the names of tables and fields of the DB.
 */

public class FormativeAction {
	private int id = -1;
	private String name; 
	private String objectives; 
	private String mainContents; 
	private String teacher;
	private int remuneration;
	private String location;
	private String day;
	private int numberOfHours;
	private int spaces;
	private int spacesAvailable;
	private String enrollmentPeriodStart;
	private String enrollmentPeriodEnd;
	private int fee;
	private static int idCounter = 1001;
	
	// Constructor -> sets ID and spacesAvailable automatically 
	public FormativeAction(String name, String objectives, String mainContents, String teacher, int remuneration,
			String location, String day, int numberOfHours, int spaces, String enrollmentPeriodStart,
			String enrollmentPeriodEnd, int fee) {
		
			this.id = idCounter;
			this.name = name; 
			this.objectives = objectives;
			this.mainContents = mainContents;
			this.teacher = teacher;
			this.remuneration = remuneration;
			this.location = location;
			this.day = day;
			this.numberOfHours = numberOfHours;
			this.spaces = spaces;
			this.spacesAvailable = spaces; 
			this.enrollmentPeriodStart = enrollmentPeriodStart;
			this.enrollmentPeriodEnd = enrollmentPeriodEnd;
			this.fee = fee; 
			
	}
	
	// Constructor -> sets spacesAvailable automatically 
		public FormativeAction(int id, String name, String objectives, String mainContents, String teacher, int remuneration,
				String location, String day, int numberOfHours, int spaces, String enrollmentPeriodStart,
				String enrollmentPeriodEnd, int fee) {
			
				this.id = id;
				this.name = name; 
				this.objectives = objectives;
				this.mainContents = mainContents;
				this.teacher = teacher;
				this.remuneration = remuneration;
				this.location = location;
				this.day = day;
				this.numberOfHours = numberOfHours;
				this.spaces = spaces;
				this.spacesAvailable = spaces; 
				this.enrollmentPeriodStart = enrollmentPeriodStart;
				this.enrollmentPeriodEnd = enrollmentPeriodEnd;
				this.fee = fee;
				
		}
		
		// Constructor 
		public FormativeAction(int id, String name, String objectives, String mainContents, String teacher, int remuneration,
				String location, String day, int numberOfHours, int spaces, int spacesAvailable, String enrollmentPeriodStart,
				String enrollmentPeriodEnd, int fee) {
			
				this.id = id;
				this.name = name; 
				this.objectives = objectives;
				this.mainContents = mainContents;
				this.teacher = teacher;
				this.remuneration = remuneration;
				this.location = location;
				this.day = day;
				this.numberOfHours = numberOfHours;
				this.spaces = spaces;
				this.spacesAvailable = spacesAvailable; 
				this.enrollmentPeriodStart = enrollmentPeriodStart;
				this.enrollmentPeriodEnd = enrollmentPeriodEnd;
				this.fee = fee;
				
		}
	
	// Getter
	public int getID() { return this.id; }
	public String getName() { return this.name; }
	public String getObjectives() { return this.objectives; }
	public String getMainContents() { return this.mainContents; }
	public String getTeacher() { return this.teacher; }
	public int getRemuneration() { return this.remuneration; }
	public String getLocation() { return this.location; }
	public String getDay() { return this.day; }
	public int getNumberOfHours() { return this.numberOfHours; }
	public int getSpaces() { return this.spaces; }
	public int getSpacesAvailable() { return this.spacesAvailable; }
	public String getEnrollmentPeriodStart() { return this.enrollmentPeriodStart; }
	public String getEnrollmentPeriodEnd() { return this.enrollmentPeriodEnd; }
	public int getFee() { return this.fee; }

	
	// Setter
	public void setID(int value) { this.id=value; }
	public void setName(String value) { this.name=value; }
	public void setObjectives(String value) { this.objectives=value; }
	public void setMainContents(String value) { this.mainContents=value; }
	public void setTeacher(String value) { this.teacher=value; }
	public void setRemuneration(int value) { this.remuneration=value; }
	public void setLocation(String value) { this.location=value; }	
	public void setDay(String value) { this.day=value; }
	public void setNumberOfHours(int value) { this.numberOfHours=value; }
	public void setSpaces(int value) { this.spaces=value; }
	public void setSpacesAvailable(int value) { this.spacesAvailable=value; }
	public void setEnrollmentPeriodStart(String value) { this.enrollmentPeriodStart=value; }
	public void setEnrollmentPeriodEnd(String value) { this.enrollmentPeriodEnd=value; }
	public void setFee(int value) { this.fee=value; }

	public static String tableName() {
		return "FormativeAction";
	}

	public static FormativeAction obtain(int formativeActionID, Database db) throws SQLException {
		//Creation of the SQL query
		String query = "SELECT * FROM " + tableName() + " WHERE  ID_professional="  + formativeActionID;
		
		Connection conn = db.getConnection();
		//Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		
		//executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query.toString());
		
		FormativeAction fA = null;
		
		while(rs.next()) {
			fA = new FormativeAction(
					rs.getInt("ID_formativeAction"),
					rs.getString("name"),
					rs.getString("objectives"),
					rs.getString("mainContents"),
					rs.getString("teacher"),
					rs.getInt("remuneration"),
					rs.getString("place"),
					rs.getString("day"),
					rs.getInt("numberOfHours"),
					rs.getInt("spaces"),
					rs.getString("enrollmentPeriodStart"),
					rs.getString("enrollmentPeriodEnd"),
					rs.getInt("fee"));
		}
		
		//Very important to always close all the objects related to the database
		rs.close();
		st.close();
		conn.close();
		
		return fA;
	}
}