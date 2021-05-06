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

public class PaymentTeacher {

	private int ID = -1;
	private float amount;
	private Date payDate;
	private String ID_invoice;

	private boolean confirmed;

	private String description = "";

	/**
	 * @param ID_invoice
	 * @param amount
	 * @param payDate
	 * @param confirmed
	 * @param description
	 */
	public PaymentTeacher(String ID_invoice, float amount, Date payDate, boolean confirmed, String description) {
		this.ID_invoice = ID_invoice;
		this.amount = amount;
		this.payDate = payDate;

		this.confirmed = confirmed;

		this.description = description;
	}

	/**
	 * Constructor with the ID of the payment teacher
	 *
	 * @param ID_payment
	 * @param ID_fa
	 * @param amount
	 * @param payDate
	 * @param sender
	 * @param receiver
	 * @param fiscalNumber
	 * @param address
	 * @param confirmed
	 */
	public PaymentTeacher(int ID_payment, String ID_invoice, float amount, Date payDate, boolean confirmed,
			String description) {
		this.ID = ID_payment;
		this.ID_invoice = ID_invoice;

		this.amount = amount;
		this.payDate = payDate;

		this.confirmed = confirmed;

		this.description = description;
	}

	public static String tableName() {
		return "PaymentTeacher";
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
	public static List<PaymentTeacher> get(String query, Database db) throws SQLException {
		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query.toString());

		List<PaymentTeacher> enrollments = new ArrayList<>();

		while (rs.next()) {
			Date datepay;
			try {
				datepay = Date.parseString(rs.getString("datePay"));
			} catch (ParseException e) {
				datepay = Date.fromMillis(rs.getLong("datePay"));
			}

			PaymentTeacher e = new PaymentTeacher(rs.getInt("ID_payment"), rs.getString("ID_invoice"),
					rs.getFloat("amount"), datepay, rs.getBoolean("confirmed"), rs.getString("description"));

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
	public static PaymentTeacher getOne(String query, Database db) throws SQLException {
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

		PaymentTeacher e = new PaymentTeacher(rs.getInt("ID_payment"), rs.getString("ID_invoice"),
				rs.getFloat("amount"), datepay, rs.getBoolean("confirmed"), rs.getString("description"));

		// Very important to always close all the objects related to the database
		rs.close();
		st.close();
		conn.close();

		return e;
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

		if (this.getID() != -1) {
			String SQL = "INSERT INTO " + tableName()
					+ "(ID_payment, ID_invoice, amount, payDate, confirmed, description) VALUES(?,?,?,?,?,?,?,?,?,?)";

			// Prepared Statement initialized with the INSERT statement
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// Sets of the parameters of the prepared statement

			pstmt.setInt(1, this.getID());
			pstmt.setString(2, this.getID_invoice());

			pstmt.setFloat(3, this.getAmount());
			pstmt.setDate(4, this.getPayDate().toSQL());

			pstmt.setBoolean(5, this.isConfirmed());
			pstmt.setString(6, this.getDescription());
			pstmt.executeUpdate(); // statement execution
			pstmt.close();
		} else {
			String SQL = "INSERT INTO " + tableName() + " VALUES(null,?,?,?,?,?)";

			// Prepared Statement initialized with the INSERT statement
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// Sets of the parameters of the prepared statement

			pstmt.setFloat(1, this.getAmount());
			pstmt.setDate(2, this.getPayDate().toSQL());

			pstmt.setBoolean(3, this.isConfirmed());
			pstmt.setString(4, this.getID_invoice());
			pstmt.setString(5, this.getDescription());
			pstmt.executeUpdate(); // statement execution

			ResultSet tableKeys = pstmt.getGeneratedKeys();
			tableKeys.next();
			this.ID = tableKeys.getInt(1);
			pstmt.close();
			tableKeys.close();
		}

		conn.close();
	}

	public String getID_invoice() {

		return ID_invoice;
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

	public void setInvoiceID(String ID) {
		this.ID_invoice = ID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
