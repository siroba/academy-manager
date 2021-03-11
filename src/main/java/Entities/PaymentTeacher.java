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

public class PaymentTeacher {
	
		private int ID = -1, ID_invoice=-1;
		private float amount;
		private DateTime payDate;
		private String sender, receiver, fiscalNumber, address;
		private boolean confirmed;

		/** Default Constructor
		 * @param amount
		 * @param payDate
		 * @param sender
		 * @param receiver
		 * @param fiscalNumber
		 * @param address
		 * @param confirmed
		 */
		public PaymentTeacher( float amount, DateTime payDate, String sender, String receiver, String fiscalNumber,
				String address, boolean confirmed) {
			
			this.amount = amount;
			this.payDate = payDate;
			this.sender = sender;
			this.receiver = receiver;
			this.fiscalNumber = fiscalNumber;
			this.address = address;
			this.confirmed = confirmed;
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
		public PaymentTeacher(int ID_payment, int ID_invoice,  float amount, DateTime payDate, String sender, String receiver, String fiscalNumber,
				String address, boolean confirmed) {
			this.ID = ID_payment;
			this.ID_invoice = ID_invoice;
			
			this.amount = amount;
			this.payDate = payDate;
			this.sender = sender;
			this.receiver = receiver;
			this.fiscalNumber = fiscalNumber;
			this.address = address;
			this.confirmed = confirmed;
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
				DateTime datepay;
				try {
					datepay = DateTime.parseString(rs.getString("datePay"));
				} catch (ParseException e) {
					datepay = DateTime.fromMillis(rs.getLong("datePay"));
				}

				PaymentTeacher e = new PaymentTeacher(
						rs.getInt("ID_payment"),
						rs.getInt("ID_invoice"),
						
						rs.getFloat("amount"),
						datepay,
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
		 * @throws ParseException
		 */
		public static PaymentTeacher getOne(String query, Database db) throws SQLException {
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

			PaymentTeacher e = new PaymentTeacher(
					rs.getInt("ID_payment"),
					rs.getInt("ID_invoice"),
					rs.getFloat("amount"),
					datepay,
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
				String SQL = "INSERT INTO " + tableName() + "(ID_payment, ID_invoice, amount, payDate, sender, receiver, fiscalNumber,"
						+ " address, confirmed) VALUES(?,?,?,?,?,?,?,?,?)";

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
				pstmt.executeUpdate(); // statement execution
			}else {
				String SQL = "INSERT INTO " + tableName() + " VALUES(null,?,?,?,?,?,?,?,?)";

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
				pstmt.setInt(8, this.getID_invoice());
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

		public void setInvoiceID(int ID_invoice) {
			this.ID_invoice= ID_invoice; 
		}

		
	}

