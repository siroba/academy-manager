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

public class Fee {
	private int ID = -1, ID_fa = -1;
	private String group;
	private float amount;

	public Fee(String group) {
		this.group = group;
	}

	public Fee(String group, float amount) {
		this.group = group;
		this.amount = amount;
	}

	public Fee(int ID, int ID_fa, String group, float amount) {
		this.ID = ID;
		this.ID_fa = ID_fa;
		this.group = group;
		this.amount = amount;
	}

	public static String tableName() {
		return "Fee";
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
	 * Inserts all the given Fees into the given database
	 *
	 * @param fees
	 * @param db
	 * @throws SQLException
	 * @throws ParseException
	 */
	public static void insert(List<Fee> fees, Database db) throws SQLException, ParseException {
		for (Fee f : fees)
			f.insert(db);
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

		if (this.getID() != -1) {
			String SQL = "INSERT INTO " + tableName() + " VALUES(?,?,?,?)";

			// Prepared Statement initialized with the INSERT statement
			PreparedStatement pstmt = conn.prepareStatement(SQL);

			pstmt.setInt(1, getID());
			pstmt.setInt(2, getID_fa());
			pstmt.setString(3, getGroup());
			pstmt.setFloat(4, getAmount());

			pstmt.executeUpdate(); // statement execution
		} else {
			String SQL = "INSERT INTO " + tableName() + " VALUES(null,?,?,?)";

			// Prepared Statement initialized with the INSERT statement
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// Sets of the parameters of the prepared statement

			pstmt.setFloat(1, getAmount());
			pstmt.setString(2, getGroup());
			pstmt.setInt(3, getID_fa());
			pstmt.executeUpdate(); // statement execution

			ResultSet tableKeys = pstmt.getGeneratedKeys();
			tableKeys.next();
			this.ID = tableKeys.getInt(1);
		}

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
	public static List<Fee> get(String query, Database db) throws SQLException {
		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query.toString());

		List<Fee> fees = new ArrayList<Fee>();

		while (rs.next()) {
			Fee f = new Fee(rs.getInt("ID_fee"), rs.getInt("ID_fa"), rs.getString("category"), rs.getFloat("amount"));

			fees.add(f);
		}

		// Very important to always close all the objects related to the database
		rs.close();
		st.close();
		conn.close();

		return fees;
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
	public static Fee getOne(String query, Database db) throws SQLException {
		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(query.toString());

		rs.next();

		Fee f = new Fee(rs.getInt("ID_fee"), rs.getInt("ID_fa"), rs.getString("category"), rs.getFloat("amount"));

		// Very important to always close all the objects related to the database
		rs.close();
		st.close();
		conn.close();

		return f;
	}

	public int getID() {
		return ID;
	}

	public int getID_fa() {
		return ID_fa;
	}

	public void setID_fa(int ID_fa) {
		this.ID_fa = ID_fa;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
}
