package Entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import PL53.util.Date;
import PL53.util.DateTime;
import Utils.Database;

public class Payment {
	private int ID = -1, ID_invoice;
	private float amount;
	private Date payDate;
	private boolean confirmed ,cash;

	public Payment(int ID_invoice, float amount, Date payDate, boolean confirmed , boolean cash) {
		this.ID_invoice = ID_invoice;
		this.amount = amount;
		this.payDate = payDate;

		this.confirmed = confirmed;
		this.cash = cash;
	}

	/**
	 * Constructor with the ID of the payment
	 *
	 * @param ID_payment
	 * @param ID_fa
	 * @param ID_professional
	 * @param amount
	 * @param payDate
	 * @param sender
	 * @param receiver
	 * @param fiscalNumber
	 * @param address
	 * @param confirmed
	 */
	public Payment(int ID_payment, int ID_invoice, float amount, Date payDate,  boolean confirmed, boolean cash) {
		this.ID = ID_payment;
		this.ID_invoice = ID_invoice;

		this.amount = amount;
		this.payDate = payDate;

		this.confirmed = confirmed;

		this.cash=cash;
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
	 * @throws ParseException
	 */
	public static List<Payment> get(String query, Database db) throws SQLException {
		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query.toString());

		List<Payment> enrollments = new ArrayList<>();

		while (rs.next()) {
			Date datepay;
			try {
				datepay = Date.parseString(rs.getString("datePay"));
			} catch (ParseException e) {
				datepay = Date.fromMillis(rs.getLong("datePay"));
			}

			Payment e = new Payment(
					rs.getInt("ID_payment"),
					rs.getInt("ID_invoice"),

					rs.getFloat("amount"),
					datepay,
					
					rs.getBoolean("confirmed"),
					rs.getBoolean("cash"));
		

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
	 * @throws ParseException
	 */
	public static Payment getOne(String query, Database db) throws SQLException {
		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query.toString());
		rs.next();

		Date datepay;
		try {
			datepay = Date.parseString(rs.getString("datePay"));
		} catch (ParseException e) {
			datepay = Date.fromMillis(rs.getLong("datePay"));
		}

		Payment e = new Payment(
				rs.getInt("ID_payment"),
				rs.getInt("ID_invoice"),
				rs.getFloat("amount"),
				datepay,
				
				rs.getBoolean("confirmed"),
				rs.getBoolean("cash"));

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
		Connection conn = db.getConnection(); // Obtain the connection

		if(this.getID() != -1) {
			String SQL = "INSERT INTO " + tableName() + "(ID_payment, ID_fa, ID_professional, amount, payDate, confirmed,cash) VALUES(?,?,?,?,?,?,?)";

			// Prepared Statement initialized with the INSERT statement
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// Sets of the parameters of the prepared statement

			pstmt.setInt(1, this.getID());
			pstmt.setInt(2, this.getID_invoice());
			pstmt.setFloat(3, this.getAmount());
			pstmt.setTimestamp(4, this.getPayDate().toTimestamp());
			pstmt.setBoolean(5, this.isConfirmed());
			pstmt.setBoolean(6, this.isCash());

			pstmt.executeUpdate(); // statement execution
			pstmt.close();
		}else {
			String SQL = "INSERT INTO " + tableName() + " VALUES(null,?,?,?,?,?)";

			// Prepared Statement initialized with the INSERT statement
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// Sets of the parameters of the prepared statement

			pstmt.setFloat(1, this.getAmount());
			pstmt.setDate(2, this.getPayDate().toSQL());
			pstmt.setBoolean(3, this.isConfirmed());
			pstmt.setBoolean(4, this.isCash());
			pstmt.setInt(5, this.getID_invoice());
			pstmt.executeUpdate(); // statement execution

			ResultSet tableKeys = pstmt.getGeneratedKeys();
			tableKeys.next();
			this.ID = tableKeys.getInt(1);
			pstmt.close();
			tableKeys.close();
		}

		conn.close();
	}







	public void setID_invoice(int iD_invoice) {
		ID_invoice = iD_invoice;
	}

	public boolean isCash() {
		return cash;
	}

	public void setCash(boolean cash) {
		this.cash = cash;
	}

	public void setID(int iD) {
		ID = iD;
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

	

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean paid) {
		this.confirmed = paid;
	}

	public int getID() {
		return ID;
	}

	public int getID_invoice() {
		return ID_invoice;
	}

	public void setID_fa(int iD_fa) {
		ID_invoice = iD_fa;
	}


}
