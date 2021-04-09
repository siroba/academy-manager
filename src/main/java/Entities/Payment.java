package Entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import PL53.util.Date;
import PL53.util.DateTime;
import Utils.Database;

public class Payment {
	private int ID = -1, ID_invoice;
	private float amount;
	private Date payDate;
	private String sender, receiver, fiscalNumber, address;
	private boolean confirmed ,cash;

	public Payment(int ID_invoice, float amount, Date payDate, String sender, String receiver, String fiscalNumber,
			String address, boolean confirmed , boolean cash) {
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
	public Payment(int ID_payment, int ID_invoice, float amount, Date payDate, String sender, String receiver, String fiscalNumber,
			String address, boolean confirmed, boolean cash) {
		this.ID = ID_payment;
		this.ID_invoice = ID_invoice;

		this.amount = amount;
		this.payDate = payDate;

		this.confirmed = confirmed;

		this.cash=cash;


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
					rs.getString("sender"),
					rs.getString("receiver"),
					rs.getString("fiscalNumber"),
					rs.getString("address"),
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
				rs.getString("sender"),
				rs.getString("receiver"),
				rs.getString("fiscalNumber"),
				rs.getString("address"),
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
			String SQL = "INSERT INTO " + tableName() + "(ID_payment, ID_fa, ID_professional, amount, payDate, sender, receiver, fiscalNumber,"
					+ " address, confirmed) VALUES(?,?,?,?,?,?,?,?,?,?)";

			// Prepared Statement initialized with the INSERT statement
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// Sets of the parameters of the prepared statement

			pstmt.setInt(1, this.getID());
			pstmt.setInt(2, this.getID_invoice());
			pstmt.setFloat(3, this.getAmount());
			pstmt.setTimestamp(4, this.getPayDate().toTimestamp());
			pstmt.setString(5,this.getSender());
			pstmt.setString(6, this.getReceiver());
			pstmt.setString(7, this.getFiscalNumber());
			pstmt.setString(8, this.getAddress());
			pstmt.setBoolean(9, this.isConfirmed());
			pstmt.setBoolean(10, this.isCash());

			pstmt.executeUpdate(); // statement execution
		}else {
			String SQL = "INSERT INTO " + tableName() + " VALUES(null,?,?,?,?)";

			// Prepared Statement initialized with the INSERT statement
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// Sets of the parameters of the prepared statement

			pstmt.setFloat(1, this.getAmount());
			pstmt.setDate(2, this.getPayDate().toSQL());
			pstmt.setString(3,this.getSender());
			pstmt.setString(4, this.getReceiver());
			pstmt.setString(5, this.getAddress());
			pstmt.setString(6, this.getFiscalNumber());
			pstmt.setBoolean(7, this.isConfirmed());
			pstmt.setBoolean(8, this.isCash());
			pstmt.setInt(9, this.getID_invoice());
			pstmt.executeUpdate(); // statement execution

			ResultSet tableKeys = pstmt.getGeneratedKeys();
			tableKeys.next();
			this.ID = tableKeys.getInt(1);
		}

		conn.close();
	}



	public int getID_invoice() {
		return ID_invoice;
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
