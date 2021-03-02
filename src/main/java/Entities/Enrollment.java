package Entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import BaseProject.Database;
import PL53.SI2020_PL53.Date;

public class Enrollment {
	private boolean isCancelled;
	private Date date;
	private String name;
	private FormativeAction formativeAction;
	private Professional professional;
	private Status status;
	
	/**
	 * Enrollment default constructor. "isCancelled" is assumed to be false and the date is assumed to be today.
	 * 
	 * @param name
	 * @param formativeAction
	 * @param date
	 */
	public Enrollment(String name, Status status, FormativeAction formativeAction, Professional professional) {
		this.isCancelled = false;
		this.status = status;
		this.date = Date.now();
		this.name = name;
		this.formativeAction = formativeAction;
		this.professional = professional;
	}

	/**
	 * Enrollment constructor.
	 * 
	 * @param name
	 * @param formativeAction
	 * @param date
	 * @param isCancelled
	 */
	public Enrollment(String name, Status status, FormativeAction formativeAction, Professional professional, Date date, boolean isCancelled) {
		this.isCancelled = isCancelled;
		this.status = status;
		this.date = new Date(date);
		this.name = name;
		this.formativeAction = formativeAction;
		this.professional = professional;
	}

	/**
	 * Enrollment default constructor, but it takes care of creating the date
	 * object. "isCancelled" is assumed to be false.
	 * 
	 * @param name
	 * @param formativeAction
	 * @param day
	 * @param month
	 * @param year
	 */
	public Enrollment(String name, Status status, FormativeAction formativeAction, Professional professional, int day, int month, int year) {
		this.isCancelled = false;
		this.status = status;
		this.date = new Date(day, month, year);
		this.name = name;
		this.formativeAction = formativeAction;
		this.professional = professional;
	}

	/**
	 * Enrollment default constructor, but it takes care of creating the date
	 * object.
	 * 
	 * @param name
	 * @param formativeAction
	 * @param day
	 * @param month
	 * @param year
	 * @param isCancelled
	 */
	public Enrollment(String name, Status status, FormativeAction formativeAction, Professional professional, int day, int month, int year, boolean isCancelled) {
		this.isCancelled = isCancelled;
		this.status = status;
		this.date = new Date(day, month, year);
		this.name = name;
		this.formativeAction = formativeAction;
		this.professional = professional;
	}
	
	/**
	 * Enrollment constructor, but it takes care of creating the date and it takes only the ID of the Formative Action and the Professional
	 * object.
	 * 
	 * @param name
	 * @param formativeActionID
	 * @param professionalID
	 * @param day
	 * @param month
	 * @param year
	 * @param isCancelled
	 * @throws SQLException 
	 */
	public Enrollment(String name, Status status, int formativeActionID, int professionalID, int day, int month, int year, boolean isCancelled) throws SQLException {
		this.isCancelled = isCancelled;
		this.status = status;
		this.date = new Date(day, month, year);
		this.name = name;
		this.formativeAction = FormativeAction.obtain(formativeActionID, new Database()); //TODO: change this!
		this.professional = Professional.obtain(professionalID, new Database()); //TODO: change this!
	}
	
	/**
	 * Enrollment constructor, but it takes only the ID of the Formative Action and the Professional
	 * object.
	 * 
	 * @param name
	 * @param formativeActionID
	 * @param professionalID
	 * @param day
	 * @param month
	 * @param year
	 * @param isCancelled
	 * @throws SQLException 
	 */
	public Enrollment(String name, Status status, int formativeActionID, int professionalID, Date date, boolean isCancelled) throws SQLException {
		this.isCancelled = isCancelled;
		this.status = status;
		this.date = date;
		this.name = name;
		this.formativeAction = FormativeAction.obtain(formativeActionID, new Database()); //TODO: change this!
		this.professional = Professional.obtain(professionalID, new Database()); //TODO: change this!
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
	 * Method to delete the element matching the given id from the table.
	 * @throws SQLException
	 */
	public void delete(Database db) throws SQLException {
		String SQL = "DELETE FROM " + tableName() + " WHERE ID_fa=? AND ID_professional=?";

		Connection conn = db.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(SQL);

		pstmt.setInt(1, this.formativeAction.getID());
		pstmt.setInt(2, this.professional.getID());
		
		pstmt.executeUpdate();
		conn.close();
	}

	/**
	 * Method to obtain all the elements from the table.
	 * 
	 * @return 
	 * @throws SQLException
	 */
	public static List<Enrollment> obtainAll(Database db) throws SQLException {
		//Creation of the SQL query
		String query = "SELECT * FROM " + tableName();

		Connection conn = db.getConnection();
		//Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		//executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query.toString());
		
		List<Enrollment> enrollments = new ArrayList<>();
		
		while(rs.next()) {
			Enrollment e = new Enrollment(
					rs.getString("name"),
					Status.valueOf(rs.getString("status")),
					rs.getInt("ID_fa"),
					rs.getInt("ID_professional"),
					Date.parse(rs.getDate("dateEn")),
					rs.getBoolean("isCancelled"));
			
			enrollments.add(e);
		}
		
		//Very important to always close all the objects related to the database
		rs.close();
		st.close();
		conn.close();
		
		return enrollments;
	}

	/**
	 * Inserts all the given professionals into the given database
	 * 
	 * @param professionals
	 * @param db
	 * @throws SQLException
	 */
	public static void insert(List<Professional> professionals, Database db) throws SQLException {
		for(Professional p: professionals)
			p.insert(db);
	}

	/**
	 * Inserts itself into the given database
	 * 
	 * @param db
	 * @throws SQLException
	 */
	public void insert(Database db) throws SQLException{
		/*
		 * status TEXT NOT NULL CHECK( status IN('received','confirmed','cancelled')),
			dateEn DATE NOT NULL,
			name TEXT NOT NULL,
			ID_fa INTEGER NOT NULL UNIQUE,
			ID_student INTEGER NOT NULL UNIQUE,
		 * */
		
		String SQL = "INSERT INTO " + tableName() + "(status, dateEn, name, ID_fa, ID_student) VALUES(?,?,?,?,?)";
		
		Connection conn = db.getConnection(); // Obtain the connection
		// Prepared Statement initialized with the INSERT statement
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		// Sets of the parameters of the prepared statement
		
		pstmt.setString(1, this.getName());
		
		try {
			pstmt.setDate(2, this.getDate().toSQL());
		} catch (ParseException e) {
			System.err.println("Couldn't parse the date " + this.getDate());
		}
		
		pstmt.setString(3, this.getName());
		pstmt.setInt(4, this.getFormativeAction().getID());
		pstmt.setInt(5, this.getProfessional().getID());
		pstmt.executeUpdate(); // statement execution
		
		conn.close();
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDate(int day, int month, int year) {
		this.date = new Date(day, month, year);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FormativeAction getFormativeAction() {
		return formativeAction;
	}

	public void setFormativeAction(FormativeAction formativeAction) {
		this.formativeAction = formativeAction;
	}
	
	public Professional getProfessional() {
		return professional;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
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
