package Entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import BaseProject.Database;
import Entities.Enrollment.Status;
import PL53.SI2020_PL53.Date;
import PL53.SI2020_PL53.DateTime;

public class Refund {
/*
 * ID_refund INTEGER PRIMARY KEY,
ID_payment INTEGER NOT NULL,
porcentageRefund REAL NOT NULL,*/
	
	private int ID = -1;
	private Payment payment;
	
	public Refund(int ID, Payment payment) {
		this.ID = ID;
		this.payment = payment;
	}
	
	/**
	 * Refund constructor, but it takes only the ID of the Formative Action and the Professional object (it does a query from the database).
	
	 */
	public Refund(int ID, int ID_payment, Database db) throws SQLException {
		this.ID = ID;
		this.payment = Payment.obtain(ID_payment, db);
	}
	public Refund(Payment payment) {
		this.payment = payment;
	}

	public float percentage() {
		/*
		 * If a registered person wishes to make a cancellation 7 calendar days or more before the course, 100% of the amount paid will be refunded. 
			If he resigns with between 3 calendar days and 6 calendar days missing, 50% of the amount of the course will be returned. 
			If he resigns with less than 3 calendar days left, the amount of the course will not be refunded.
		 * */
		int days = Date.daysSince(this.payment.getEnrollment().getFormativeAction().getDate(), this.payment.getPayDate());
		
		if(days > 7) return 1f;
		else if (days <= 6 && days >=3) return 0.5f;
		else return 0f;
	}
	
	public float totalRefund() {
		return payment.getAmount()*this.percentage();
	}
	
	public int getID() {
		return ID;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	
	public static String tableName() {
		return "Refund";
	}
	
	/**
	 * Inserts itself into the given database
	 * 
	 * @param db
	 * @throws SQLException
	 */
	public void insert(Database db) throws SQLException {
		/*
		 * status TEXT NOT NULL CHECK( status IN('received','confirmed','cancelled')),
		 * dateEn DATE NOT NULL, name TEXT NOT NULL, ID_fa INTEGER NOT NULL UNIQUE,
		 * ID_student INTEGER NOT NULL UNIQUE,
		 */

		String SQL = "INSERT INTO " + tableName() + "( payment,percentage,totalRefund) VALUES(?,?,?)";

		Connection conn = db.getConnection(); // Obtain the connection
		// Prepared Statement initialized with the INSERT statement
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		// Sets of the parameters of the prepared statement

		pstmt.setInt(1, this.getPayment().getID());
		pstmt.setFloat(2, this.percentage());

		pstmt.executeUpdate(); // statement execution

		conn.close();
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
	 * 
	 * @throws SQLException
	 */
	public void delete(Database db) throws SQLException {
		String SQL = "DELETE FROM " + tableName() + " WHERE ID=? ";

		Connection conn = db.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(SQL);

		pstmt.setInt(1, this.getID());
	

		pstmt.executeUpdate();
		conn.close();
	}
	
	/**
	 * Method to obtain all the elements from the table.
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static List<Refund> obtainAll(Database db) throws SQLException {
		// Creation of the SQL query
		String query = "SELECT * FROM " + tableName();

		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query.toString());

		List<Refund> refund = new ArrayList<>();
		
		
		while (rs.next()) {
			Refund r = new Refund(
					rs.getInt("ID_refund"), 
					rs.getInt("ID_payment"),
					db);

			refund.add(r);
		}

		// Very important to always close all the objects related to the database
		rs.close();
		st.close();
		conn.close();

		return refund;
	}
	
	/**
	 * Inserts all the given Refunds into the given database
	 * 
	 * @param Refund
	 * @param db
	 * @throws SQLException
	 */
	public static void insert(List<Refund> refunds, Database db) throws SQLException {
		for (Refund r : refunds)
			r.insert(db);
	}


	
	
}
