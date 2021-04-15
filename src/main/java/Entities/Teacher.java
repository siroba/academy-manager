package Entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import PL53.util.Random;
import Utils.Database;

public class Teacher {
	private int ID = -1;
	private String name;

	public Teacher(int ID, String name) {
		this.ID = ID;
		this.name = name;
	}

	public Teacher(String name) {
		this.name = name;
	}

	public Teacher() {
		Random r = new Random();
		this.name = r.name(3, 10);
	}

	public static String tableName() {
		return "Teacher";
	}

	public void insert(Database db) throws SQLException {
		Connection conn = db.getConnection(); // Obtain the connection
		PreparedStatement pstmt;
		
		if(this.getID() == -1) {
			String sql = "INSERT INTO " + tableName() + " VALUES (null, ?)";
	
			pstmt = conn.prepareStatement(sql);
			// Sets of the parameters of the prepared statement
	
			pstmt.setString(1, this.getName());

			pstmt.executeUpdate();
			
			ResultSet tableKeys = pstmt.getGeneratedKeys();
			tableKeys.next();
			this.ID = tableKeys.getInt(1);
			pstmt.close();
			tableKeys.close();
		}else {
			String sql = "INSERT INTO " + tableName() + " (ID_teacher, name) VALUES (?, ?)";
	
			pstmt = conn.prepareStatement(sql);
			// Sets of the parameters of the prepared statement
	
			pstmt.setInt(1, this.getID());
			pstmt.setString(2, this.getName());

			pstmt.executeUpdate();
			pstmt.close();
		}
		
		conn.close();
	}

	public static List<Teacher> get(String sql, Database db) throws SQLException {
		List<Teacher> list = new ArrayList<Teacher>();

		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			Teacher t = new Teacher(rs.getInt("ID_teacher"), rs.getString("name"));

			list.add(t);
		}

		conn.close();
		st.close();
		rs.close();
		return list;
	}

	public static Teacher getOne(String sql, Database db) throws SQLException {
		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(sql);

		rs.next();

		Teacher t = new Teacher(rs.getInt("ID_teacher"), rs.getString("name"));

		st.close();
		rs.close();
		conn.close();
		
		return t;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
