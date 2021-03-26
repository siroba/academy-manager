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

public class Invoice {
	private int ID = -1, ID_fa;
	private Date dateIn;
	private String sender, receiver, fiscalNumber, address;

	/** Constructor with ID_invoice
	 * @param ID_invoice
	 * @param ID_fa
	 * @param dateIn
	 */
	public Invoice(int ID_invoice,int ID_fa,  Date dateIn , String sender , String receiver, String fiscalNumber, String address) {
		this.ID_fa = ID_fa;
		this.ID=ID_invoice;
		this.dateIn=dateIn;
		this.sender = sender;
		this.receiver = receiver;
		this.fiscalNumber = fiscalNumber;
		this.address = address;
		
	}
	/**Default Constructor
	 * @param ID_fa
	 * @param dateIn
	 */
	public Invoice(int ID_fa,  Date dateIn,String sender , String receiver, String fiscalNumber, String address) {
		this.ID_fa = ID_fa;
		this.dateIn=dateIn;
		this.sender = sender;
		this.receiver = receiver;
		this.fiscalNumber = fiscalNumber;
		this.address = address;
	
	}
	public static String tableName() {
		return "Invoice";
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
	public static List<Invoice> get(String query, Database db) throws SQLException {
		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query.toString());

		List<Invoice> invoices = new ArrayList<>();

		while (rs.next()) {
			Date dateIn;
			try {
				dateIn = Date.parseString(rs.getString("dateIn"));
			} catch (ParseException e) {
				dateIn = Date.fromMillis(rs.getLong("dateIn"));
			}
			

			Invoice e = new Invoice(
					rs.getInt("ID_invoice"),
					rs.getInt("ID_fa"),
					dateIn,
					rs.getString("sender"),
					rs.getString("receiver"),
					rs.getString("fiscalNumber"),
					rs.getString("address")
					);
					

			invoices.add(e);
		}

		// Very important to always close all the objects related to the database
		rs.close();
		st.close();
		conn.close();

		return invoices;
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
	public static Invoice getOne(String query, Database db) throws SQLException {
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
		

		Invoice e = new Invoice(
				rs.getInt("ID_invoice"),
				rs.getInt("ID_fa"),
				dateIn,
				rs.getString("sender"),
				rs.getString("receiver"),
				rs.getString("fiscalNumber"),
				rs.getString("address")
				);
				


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

		if(this.getID() != -1) {
			String SQL = "INSERT INTO " + tableName() + "(ID_invoice, ID_fa,  dateIn) VALUES(?,?,?)";

			// Prepared Statement initialized with the INSERT statement
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// Sets of the parameters of the prepared statement

			pstmt.setInt(1, this.getID());
			pstmt.setInt(2, this.getID_fa());
			pstmt.setDate(3, this.getDateIn().toSQL());
			pstmt.setString(4,this.getSender());
			pstmt.setString(5, this.getReceiver());
			pstmt.setString(6, this.getFiscalNumber());
			pstmt.setString(7, this.getAddress());
			
			pstmt.executeUpdate(); // statement execution
		}else {
			String SQL = "INSERT INTO " + tableName() + " VALUES(null,?,?)";

			// Prepared Statement initialized with the INSERT statement
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// Sets of the parameters of the prepared statement

			pstmt.setDate(1, this.getDateIn().toSQL());
			pstmt.setInt(2, this.getID_fa());
			pstmt.executeUpdate(); // statement execution

			ResultSet tableKeys = pstmt.getGeneratedKeys();
			tableKeys.next();
			this.ID = tableKeys.getInt(1);
		}

		conn.close();
	}
	public Date getDateIn() {
		return dateIn;
	}
	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
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




}
