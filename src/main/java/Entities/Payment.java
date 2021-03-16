package Entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import PL53.util.DateTime;
import Utils.Database;

public class Payment {
	private int ID = -1, ID_fa, ID_professional;
	private float amount;
	private DateTime payDate;
	private String sender, receiver, fiscalNumber, address;
	private boolean confirmed;
	private RefundStatus refundStatus = RefundStatus.NONE;

	public Payment(int ID_fa, int ID_professional, float amount, DateTime payDate, String sender, String receiver, String fiscalNumber,
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
	public Payment(int ID_payment, int ID_fa, int ID_professional, float amount, DateTime payDate, String sender, String receiver, String fiscalNumber,
			String address, boolean confirmed, RefundStatus refundStatus) {
		this.ID = ID_payment;
		this.ID_fa = ID_fa;
		this.ID_professional = ID_professional;
		this.amount = amount;
		this.payDate = payDate;
		this.sender = sender;
		this.receiver = receiver;
		this.fiscalNumber = fiscalNumber;
		this.address = address;
		this.confirmed = confirmed;
		this.setRefundStatus(refundStatus);
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
			DateTime datepay;
			try {
				datepay = DateTime.parseString(rs.getString("datePay"));
			} catch (ParseException e) {
				datepay = DateTime.fromMillis(rs.getLong("datePay"));
			}

			Payment e = new Payment(
					rs.getInt("ID_payment"),
					rs.getInt("ID_fa"),
					rs.getInt("ID_professional"),
					rs.getFloat("amount"),
					datepay,
					rs.getString("sender"),
					rs.getString("receiver"),
					rs.getString("fiscalNumber"),
					rs.getString("address"),
					rs.getBoolean("confirmed"),
					RefundStatus.valueOf(rs.getString("refundStatus")));

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

		DateTime datepay;
		try {
			datepay = DateTime.parseString(rs.getString("datePay"));
		} catch (ParseException e) {
			datepay = DateTime.fromMillis(rs.getLong("datePay"));
		}

		Payment e = new Payment(
				rs.getInt("ID_payment"),
				rs.getInt("ID_fa"),
				rs.getInt("ID_professional"),
				rs.getFloat("amount"),
				datepay,
				rs.getString("sender"),
				rs.getString("receiver"),
				rs.getString("fiscalNumber"),
				rs.getString("address"),
				rs.getBoolean("confirmed"),
				RefundStatus.valueOf(rs.getString("refundStatus")));

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
	public void insert(Database db) throws SQLException {
		/*
		 * status TEXT NOT NULL CHECK( status IN('received','confirmed','cancelled')),
		 * dateEn DATE NOT NULL, name TEXT NOT NULL, ID_fa INTEGER NOT NULL UNIQUE,
		 * ID_student INTEGER NOT NULL UNIQUE,
		 */
		Connection conn = db.getConnection(); // Obtain the connection

		if(this.getID() != -1) {
			String SQL = "INSERT INTO " + tableName() + "(ID_payment, ID_fa, ID_professional, amount, payDate, sender, receiver, fiscalNumber,"
					+ " address, confirmed, refundStatus) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

			// Prepared Statement initialized with the INSERT statement
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// Sets of the parameters of the prepared statement

			pstmt.setInt(1, this.getID());
			pstmt.setInt(2, this.getID_fa());
			pstmt.setInt(3, this.getID_professional());
			pstmt.setFloat(4, this.getAmount());
			pstmt.setTimestamp(5, this.getPayDate().toTimestamp());
			pstmt.setString(6,this.getSender());
			pstmt.setString(7, this.getReceiver());
			pstmt.setString(8, this.getFiscalNumber());
			pstmt.setString(9, this.getAddress());
			pstmt.setBoolean(10, this.isConfirmed());
			pstmt.setString(8, this.getRefundStatus().toString());
			pstmt.executeUpdate(); // statement execution
		}else {
			String SQL = "INSERT INTO " + tableName() + " VALUES(null,?,?,?,?,?,?,?,?,?,?)";

			// Prepared Statement initialized with the INSERT statement
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// Sets of the parameters of the prepared statement

			pstmt.setFloat(1, this.getAmount());
			pstmt.setTimestamp(2, this.getPayDate().toTimestamp());
			pstmt.setString(3,this.getSender());
			pstmt.setString(4, this.getReceiver());
			pstmt.setString(5, this.getAddress());
			pstmt.setString(6, this.getFiscalNumber());
			pstmt.setBoolean(7, this.isConfirmed());
			pstmt.setString(8, this.getRefundStatus().toString());
			pstmt.setInt(9, this.getID_fa());
			pstmt.setInt(10, this.getID_professional());
			pstmt.executeUpdate(); // statement execution

			ResultSet tableKeys = pstmt.getGeneratedKeys();
			tableKeys.next();
			this.ID = tableKeys.getInt(1);
		}

		conn.close();
	}



	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public DateTime getPayDate() {
		return payDate;
	}

	public void setPayDate(DateTime payDate) {
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
	
	public RefundStatus getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(RefundStatus refundStatus) {
		this.refundStatus = refundStatus;
	}

	public enum RefundStatus {
		NONE, SOLICITED, REFUNDED;
	}
}
