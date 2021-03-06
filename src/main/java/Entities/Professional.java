package Entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import PL53.SI2020_PL53.DateTime;
import PL53.SI2020_PL53.Random;

import Utils.Database;
import Entities.Enrollment.Status;
import Exceptions.InvalidFieldValue;

/**
 * Domain model data for the professionals IMPORTANT: When using the Apache
 * Commons DbUtils components you must Strictly adhere to the Java
 * capitalization convention: - Capitalize all the words that form an identifier
 * except the first letter of method and variable names. - Do not use
 * underscores Follow also these same criteria in the names of tables and fields
 * of the DB.
 */

public class Professional {
	private int id = -1;
	private String name, surname, phone, email;
	private ArrayList<Enrollment> enrollments;

	public Professional(String name, String surname, String phone, String email) {
		this.name = name;
		this.surname = surname;

		if (!checkPhone(phone))
			throw new InvalidFieldValue("phone number", phone);
		else
			this.phone = phone;

		if (!checkEmail(email))
			throw new InvalidFieldValue("email", email);
		else
			this.email = email;

		enrollments = new ArrayList<Enrollment>();
	}

	public Professional(int ID, String name, String surname, String phone, String email) {
		this.id = ID;
		this.name = name;
		this.surname = surname;

		if (!checkPhone(phone))
			throw new InvalidFieldValue("phone number", phone);
		else
			this.phone = phone;

		if (!checkEmail(email))
			throw new InvalidFieldValue("email", email);
		else
			this.email = email;

		enrollments = new ArrayList<Enrollment>();
	}

	/**
	 * Constructor that assigns random values
	 */
	public Professional() {
		Random random = new Random();

		this.name = random.name(3, 10);
		surname = random.name(5, 12);

		email = (name + surname).toLowerCase() + random.nextInt(999) + "@gmail.com";

		phone = random.phone();
	}

	/**
	 * Enrolls the professional to the given formative action
	 *
	 * @param FormativeAction
	 * @param EnrollmentName
	 */
	public Enrollment enroll(FormativeAction fA, Professional p, Status status, DateTime date) {
		Enrollment e = new Enrollment(fA.getID(), p.getID(), status, date);
		enrollments.add(e);

		return e;
	}

	/**
	 * This function checks whether the phone number is valid or not (true or false)
	 *
	 * @param phone
	 * @return
	 */
	public static boolean checkPhone(String phone) {
		// Regex pattern that matches a phone number
		String pattern = "^(\\(?\\+[1-9]{2}\\)?)? ?([0-9] ?){6,14}$";

		return phone.matches(pattern);
	}

	/**
	 * This function checks whether the email address is valid or not (true or
	 * false)
	 *
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		// Regex pattern that matches a phone number
		String pattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

		return email.matches(pattern);
	}

	public static String tableName() {
		return "Professional";
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
	 * @return
	 * @throws SQLException
	 */
	public static List<Professional> get(String query, Database db) throws SQLException {
		Connection conn = db.getConnection();
		//Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		//executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query);

		List<Professional> professionals = new ArrayList<>();

		while(rs.next()) {
			Professional p = new Professional(
					rs.getInt("ID_professional"),
					rs.getString("name"),
					rs.getString("surname"),
					rs.getString("phone"),
					rs.getString("email"));

			professionals.add(p);
		}

		//Very important to always close all the objects related to the database
		rs.close();
		st.close();
		conn.close();

		return professionals;
	}

	/**
	 * Does the query you specify and returns a list with all the results
	 *
	 * @return
	 * @throws SQLException
	 */
	public static Professional getOne(String query, Database db) throws SQLException {
		Connection conn = db.getConnection();
		//Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		//executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query);

		rs.next();
		
		Professional p = new Professional(
				rs.getInt("ID_professional"),
				rs.getString("name"),
				rs.getString("surname"),
				rs.getString("phone"),
				rs.getString("email"));


		//Very important to always close all the objects related to the database
		rs.close();
		st.close();
		conn.close();

		return p;
	}

	/**
	 * Creates the given number of random objects
	 *
	 * @param numberElements
	 * @return
	 */
	public static List<Professional> create(int numberElements) {
		List<Professional> professionals = new ArrayList<>(); // List where the products will be inserted

		for (int i = 0; i < numberElements; i++) {
			professionals.add(new Professional()); // new product is added to the list
		}

		return professionals;
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
		Connection conn = db.getConnection(); // Obtain the connection

		if(this.getID() != -1) {
			String SQL = "INSERT INTO " + tableName() + "(ID_professional, name, surname, phone, email) VALUES(?,?,?,?,?)";

			// Prepared Statement initialized with the INSERT statement
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// Sets of the parameters of the prepared statement

			pstmt.setInt(1, this.getID());
			pstmt.setString(2, this.getName());
			pstmt.setString(3, this.getSurname());
			pstmt.setString(4, this.getPhone());
			pstmt.setString(5, this.getEmail());
			pstmt.executeUpdate(); // statement execution
		}else {
			String SQL = "INSERT INTO " + tableName() + " VALUES(null,?,?,?,?)";

			// Prepared Statement initialized with the INSERT statement
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// Sets of the parameters of the prepared statement

			pstmt.setString(1, this.getName());
			pstmt.setString(2, this.getSurname());
			pstmt.setString(3, this.getPhone());
			pstmt.setString(4, this.getEmail());
			pstmt.executeUpdate(); // statement execution

			ResultSet tableKeys = pstmt.getGeneratedKeys();
			tableKeys.next();
			this.id = tableKeys.getInt(1);
		}

		conn.close();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		if (!checkPhone(phone))
			throw new InvalidFieldValue("phone number", phone);
		else
			this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (!checkEmail(email))
			throw new InvalidFieldValue("email address", email);
		else
			this.email = email;
	}

	public int getID() {
		return this.id;
	}

}
