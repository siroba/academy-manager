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
import PL53.SI2020_PL53.DateTime;

public class Payment {
	private int ID_fa, ID_professional;
	private float amount;
	private Date payDate;
	private String sender, receiver, fiscalNumber, address;
	private boolean confirmed;

	public Payment(int ID_fa, int ID_professional, float amount, Date payDate, String sender, String receiver, String fiscalNumber,
			String address, boolean confirmed) {
		this.ID_fa = ID_fa;
		this.ID_professional = ID_professional;
		this.amount = amount;
		this.payDate = payDate;
		this.sender = sender;
		this.receiver = receiver;
		this.fiscalNumber = fiscalNumber;
		this.address = address;
		this.confirmed = confirmed;
	}
	
	public static String tableName() {
		return "Payment";
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
	public static List<Payment> get(String query, Database db) throws SQLException {
		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query.toString());

		List<Payment> enrollments = new ArrayList<>();
		
		while (rs.next()) {
			Payment e = new Payment(
					rs.getInt("ID_fa"), 
					rs.getInt("ID_professional"),
					rs.getFloat("amount"),
					new DateTime(Date.parse(rs.getDate("datePay"))),
					rs.getString("sender"),
					rs.getString("receiver"),
					rs.getString("fiscalNumber"),
					rs.getString("address"),
					rs.getBoolean("confirmed"));

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
	public static Payment getOne(String query, Database db) throws SQLException {
		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query.toString());
		rs.next();
		
		Payment e = new Payment(
				rs.getInt("ID_fa"), 
				rs.getInt("ID_professional"),
				rs.getFloat("amount"),
				new DateTime(Date.parse(rs.getDate("datePay"))),
				rs.getString("sender"),
				rs.getString("receiver"),
				rs.getString("fiscalNumber"),
				rs.getString("address"),
				rs.getBoolean("confirmed"));

		// Very important to always close all the objects related to the database
		rs.close();
		st.close();
		conn.close();

		return e;
	}

	/**
	 * Inserts all the given professionals into the given database
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

		String SQL = "INSERT INTO " + tableName() + "(ID_fa, ID_professional, amount, payDate, sender, receiver, fiscalNumber,"
				+ " address, confirmed) VALUES(?,?,?,?,?,?,?,?,?)";

		Connection conn = db.getConnection(); // Obtain the connection
		// Prepared Statement initialized with the INSERT statement
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		// Sets of the parameters of the prepared statement

		pstmt.setInt(1, this.getID_fa());
		pstmt.setInt(2, this.getID_professional());
		pstmt.setFloat(3, this.getAmount());
		pstmt.setDate(4, this.getPayDate().toSQL());
		pstmt.setString(5,this.getSender());
		pstmt.setString(6, this.getReceiver());
		pstmt.setString(7, this.getFiscalNumber());
		pstmt.setString(8, this.getAddress());
		pstmt.setBoolean(9, this.isConfirmed());
		

		pstmt.executeUpdate(); // statement execution

		conn.close();
	}

	
	
	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getFiscalNumber() {
		return fiscalNumber;
	}

	public void setFiscalNumber(String fiscalNumber) {
		this.fiscalNumber = fiscalNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean paid) {
		this.confirmed = paid;
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
}
