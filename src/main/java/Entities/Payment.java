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
import PL53.SI2020_PL53.Random;

public class Payment {
	private int ID = -1;
	private float amount;
	private Date payDate;
	private String sender, receiver, fiscalNumber, address;
	
	
	/**
	 * This reference is needed for the {@link Entities.Refund#percentage() refund_percentage} function.
	 */
	private Enrollment enrollment;

	public Payment(Enrollment enrollment, float amount, Date payDate, String sender, String receiver, String fiscalNumber, String address) {
		this.enrollment = enrollment;
		this.amount = amount;
		this.payDate = payDate;
		this.sender = sender;
		this.receiver = receiver;
		this.fiscalNumber = fiscalNumber;
		this.address = address;

	}

	public Payment(int ID, Enrollment enrollment, float amount, Date payDate, String sender, String receiver, String fiscalNumber,
			String address) {
		this.ID = ID;
		this.enrollment = enrollment;
		this.amount = amount;
		this.payDate = payDate;
		this.sender = sender;
		this.receiver = receiver;
		this.fiscalNumber = fiscalNumber;
		this.address = address;
		
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
	 * Method to delete the element matching the given id from the table.
	 * @throws SQLException
	 */
	public void delete(Database db) throws SQLException {
		String SQL = "DELETE FROM " + tableName() + " WHERE ID_payment=?";

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
	public static List<Payment> obtainAll(Database db) throws SQLException {
		//Creation of the SQL query
		String query = "SELECT * FROM " + tableName();

		Connection conn = db.getConnection();
		//Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		//executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query.toString());
		
		List<Payment> payments = new ArrayList<>();
		
		/*this.ID = ID;
		this.enrollment = enrollment;
		this.amount = amount;
		this.payDate = payDate;
		this.sender = sender;
		this.receiver = receiver;
		this.fiscalNumber = fiscalNumber;
		this.address = address;
		this.paid = paid;*/
		
		while(rs.next()) {
			Payment p = new Payment(
					rs.getInt("ID_payment"),
					null, //TODO enrollment
					rs.getFloat("amount"),
					Date.parse(rs.getDate("dataPay")),
					rs.getString("receiver"),
					rs.getString("sender"),
					//rs.getString("ID_invoice"),
					rs.getString("fiscalNumber"),
					rs.getString("address")
					
					);
			
			payments.add(p);
		}
		
		//Very important to always close all the objects related to the database
		rs.close();
		st.close();
		conn.close();
		
		return payments;
	}
	

	//TODO
		public static Payment obtain(int iD_payment, Database db) throws SQLException {
			//Creation of the SQL query
			String query = "SELECT * FROM " + tableName() + " WHERE ID_payment=" + iD_payment;

			Connection conn = db.getConnection();
			//Statement object needed to send statements to the database
			Statement st = conn.createStatement();
			
			//executeQuery will return a resultSet
			ResultSet rs = st.executeQuery(query.toString());
			
			Payment p = null;
			
			while(rs.next()) {
				p = new Payment(
						rs.getInt("ID_payment"),
						null, //TODO enrollment
						rs.getFloat("amount"),
						Date.parse(rs.getDate("dataPay")),
						rs.getString("receiver"),
						rs.getString("sender"),
						//rs.getString("ID_invoice"),
						rs.getString("fiscalNumber"),
						rs.getString("address")
						
						);
			}
			
			//Very important to always close all the objects related to the database
			rs.close();
			st.close();
			conn.close();
			
			return p;
		}
		
		/**
		 * Creates the given number of random objects
		 * 
		 * @param numberElements
		 * @return
		 */
		public static List<Payment> create(int numberElements) {
			List<Payment> payments = new ArrayList<>(); // List where the products will be inserted
			
			Random random = new Random(); // random object to obtain random values
			float amount;
			
			String receiver, sender, fiscalNumber,address;
			boolean paid;
			Date payDate;
			for (int i = 0; i < numberElements; i++) {
				amount= (float) Math.random();
				payDate=Date.random();
				receiver = random.name(3, 10);
				sender = random.name(5, 12);
				fiscalNumber = random.name(10, 10);
				address= random.name(12, 15);
				
				
				//enrollmet is null by the moment TODO
				payments.add(new Payment(null,amount,payDate, receiver,sender,fiscalNumber,address)); // new product is added to the list
			}
			
			return payments;
		}
		
		/**
		 * Inserts itself into the given database
		 * 
		 * @param db
		 * @throws SQLException
		 * @throws ParseException 
		 */
		public void insert(Database db) throws SQLException, ParseException{
			String SQL = "INSERT INTO " + tableName() + "(amount, datePay, sender, receiver, fiscalNumber, address) VALUES(?,?,?,?,?,?)";
			
			Connection conn = db.getConnection(); // Obtain the connection
			// Prepared Statement initialized with the INSERT statement
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// Sets of the parameters of the prepared statement
			
			
			pstmt.setFloat(1, this.getAmount());
			pstmt.setDate(2, this.getPayDate().toSQL());
			pstmt.setString(3, this.getReceiver());
			pstmt.setString(4, this.getSender());
			pstmt.setString(5, this.getFiscalNumber());
			pstmt.setString(5, this.getAddress());
			
			
			pstmt.executeUpdate(); // statement execution
			
			ResultSet tableKeys = pstmt.getGeneratedKeys();
			tableKeys.next();
			this.ID = tableKeys.getInt(1);
			
			conn.close();
		}

		/**
		 * Inserts all the given Payments into the given database
		 * 
		 * @param Payments
		 * @param db
		 * @throws SQLException
		 * @throws ParseException 
		 */
		public static void insert(List<Payment> payments, Database db) throws SQLException, ParseException {
			for(Payment p: payments)
				p.insert(db);
		}

		
		

	public int getID() {
		return ID;
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



	public Enrollment getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}

	
}
