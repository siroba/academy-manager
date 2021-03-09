package Entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import PL53.SI2020_PL53.DateTime;
import Utils.Database;

public class Invoice {
	private int ID = -1, ID_fa;
	private DateTime dateIn;

	public Invoice(int ID_invoice,int ID_fa,  DateTime dateIn) {
		this.ID_fa = ID_fa;
		this.ID=ID_invoice;
		this.dateIn=dateIn;
		
	}
	public Invoice(int ID_fa,  DateTime dateIn) {
		this.ID_fa = ID_fa;
		this.dateIn=dateIn;
	
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
			DateTime dateIn;
			try {
				dateIn = DateTime.parseString(rs.getString("dateIn"));
			} catch (ParseException e) {
				dateIn = DateTime.fromMillis(rs.getLong("dateIn"));
			}
			

			Invoice e = new Invoice(
					rs.getInt("ID_invoice"),
					rs.getInt("ID_fa"),
					dateIn);
					

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

		DateTime dateIn;
		try {
			dateIn = DateTime.parseString(rs.getString("dateIn"));
		} catch (ParseException e) {
			dateIn = DateTime.fromMillis(rs.getLong("dateIn"));
		}
		

		Invoice e = new Invoice(
				rs.getInt("ID_invoice"),
				rs.getInt("ID_fa"),
				dateIn
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
	public void insert(Database db) throws SQLException {
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
			pstmt.setTimestamp(3, this.getDateIn().toTimestamp());
			
			pstmt.executeUpdate(); // statement execution
		}else {
			String SQL = "INSERT INTO " + tableName() + " VALUES(null,?,?)";

			// Prepared Statement initialized with the INSERT statement
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// Sets of the parameters of the prepared statement

			pstmt.setTimestamp(1, this.getDateIn().toTimestamp());
			pstmt.setInt(2, this.getID_fa());
			pstmt.executeUpdate(); // statement execution

			ResultSet tableKeys = pstmt.getGeneratedKeys();
			tableKeys.next();
			this.ID = tableKeys.getInt(1);
		}

		conn.close();
	}
	public DateTime getDateIn() {
		return dateIn;
	}
	public void setDateIn(DateTime dateIn) {
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




}
