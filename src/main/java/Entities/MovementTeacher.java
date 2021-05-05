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
import Utils.Database;

public class MovementTeacher {
	private int ID_fa, ID_teacher;
	private Date dateIn;
	private String sender, receiver, fiscalNumber, address, ID = "", description="";
	private float amount;

	/**
	 * Constructor with ID_invoice
	 * 
	 * @param ID_invoice
	 * @param ID_fa
	 * @param dateIn
	 */
	public MovementTeacher(String ID_invoice, float amount, int ID_fa, Date dateIn, String sender, String receiver,
			String fiscalNumber, String address, int ID_teacher, String description) {
		this.amount = amount;
		this.ID_fa = ID_fa;
		this.ID = ID_invoice;
		this.dateIn = dateIn;
		this.sender = sender;
		this.receiver = receiver;
		this.fiscalNumber = fiscalNumber;
		this.address = address;
		this.ID_teacher = ID_teacher;
		this.description = description;

	}

	/**
	 * Default Constructor
	 * 
	 * @param ID_fa
	 * @param dateIn
	 */
	public MovementTeacher(int ID_fa, float amount, Date dateIn, String sender, String receiver, String fiscalNumber,
			String address, int ID_teacher, String description) {
		this.amount = amount;
		this.ID_fa = ID_fa;
		this.dateIn = dateIn;
		this.sender = sender;
		this.receiver = receiver;
		this.fiscalNumber = fiscalNumber;
		this.address = address;
		this.ID_teacher = ID_teacher;
		this.description = description;
	}

	public static String tableName() {
		return "InvoiceTeacher";
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
	public static List<MovementTeacher> get(String query, Database db) throws SQLException {
		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query.toString());

		List<MovementTeacher> invoices = new ArrayList<>();

		while (rs.next()) {
			Date dateIn;
			try {
				dateIn = Date.parseString(rs.getString("dateIn"));
			} catch (ParseException e) {
				dateIn = Date.fromMillis(rs.getLong("dateIn"));
			}

			MovementTeacher e = new MovementTeacher(rs.getString("ID_invoice"), rs.getFloat("amount"), rs.getInt("ID_fa"),
					dateIn, rs.getString("sender"), rs.getString("receiver"), rs.getString("fiscalNumber"),
					rs.getString("address"), rs.getInt("ID_teacher"), rs.getString("description"));

			invoices.add(e);
		}

		// Very important to always close all the objects related to the database
		rs.close();
		st.close();
		conn.close();

		return invoices;
	}

	public int getID_teacher() {
		return ID_teacher;
	}

	public void setID_teacher(int iD_teacher) {
		ID_teacher = iD_teacher;
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
	public static MovementTeacher getOne(String query, Database db) throws SQLException {
		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query.toString());
		rs.next();

		Date dateIn;
		try {
			dateIn = Date.parseString(rs.getString("dateIn"));
		} catch (ParseException e) {
			dateIn = Date.fromMillis(rs.getLong("dateIn"));
		}

		MovementTeacher e = new MovementTeacher(rs.getString("ID_invoice"), rs.getFloat("amount"), rs.getInt("ID_fa"),
				dateIn, rs.getString("sender"), rs.getString("receiver"), rs.getString("fiscalNumber"),
				rs.getString("address"), rs.getInt("ID_teacher"), rs.getString("description"));

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

		String SQL = "INSERT INTO " + tableName()
				+ "(ID_invoice, amount, ID_fa,  dateIn, sender, receiver , fiscalNumber , address , ID_teacher, description) VALUES(?,?,?,?,?,?,?,?,?,?)";

		// Prepared Statement initialized with the INSERT statement
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		// Sets of the parameters of the prepared statement

		pstmt.setString(1, this.getID());
		pstmt.setFloat(2, this.getAmount());
		pstmt.setInt(3, this.getID_fa());
		pstmt.setDate(4, this.getDateIn().toSQL());
		pstmt.setString(5, this.getSender());
		pstmt.setString(6, this.getReceiver());
		pstmt.setString(7, this.getFiscalNumber());
		pstmt.setString(8, this.getAddress());
		pstmt.setInt(9, this.getID_teacher());
		pstmt.setString(10, this.getDescription());

		pstmt.executeUpdate(); // statement execution

		pstmt.close();

		conn.close();
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public int getID_fa() {
		return ID_fa;
	}

	public void setID_fa(int iD_fa) {
		ID_fa = iD_fa;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
