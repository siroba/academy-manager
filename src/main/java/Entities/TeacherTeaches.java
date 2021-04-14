package Entities;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

import Utils.Database;

public class TeacherTeaches {
	private Teacher teacher;
	private FormativeAction formativeAction;
	private float remuneration;
	
	public TeacherTeaches(Teacher teacher, FormativeAction fa, float remuneration) {
		this.teacher = teacher;
		this.formativeAction = fa;
		this.remuneration = remuneration;
	}
	
	public static String tableName() {return "TeacherTeaches";}
	
	public void insert(Database db) throws SQLException {
		Connection conn = db.getConnection(); // Obtain the connection
		
		String sql = "INSERT INTO " + tableName() + " (ID_teacher, ID_fa, remuneration) VALUES (?, ?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// Sets of the parameters of the prepared statement

		pstmt.setInt(1, this.getTeacherID());
		pstmt.setInt(2, this.getFaID());
		pstmt.setFloat(3, this.getRemuneration());
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	public static List<TeacherTeaches> get(String sql, Database db) throws SQLException, ParseException {
		List<TeacherTeaches> list = new ArrayList<TeacherTeaches>();
		
		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(sql);

		String sqlTeacher = "SELECT * FROM Teacher WHERE ID_teacher=";
		String sqlFa = "SELECT * FROM Teacher WHERE ID_Fa=";
		
		while(rs.next()) {
			TeacherTeaches t = new TeacherTeaches(
					Teacher.getOne(sqlTeacher + rs.getInt("ID_teacher"), db), 
					FormativeAction.getOne(sqlFa + rs.getInt("ID_fa"), db), 
					rs.getFloat("remuneration"));
			
			list.add(t);
		}
		
		rs.close();
		st.close();
		conn.close();
		
		return list;
	}
	
	public static List<TeacherTeaches> get(FormativeAction fa, Database db) throws SQLException {
		List<TeacherTeaches> list = new ArrayList<TeacherTeaches>();
		
		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery("SELECT * FROM TeacherTeaches WHERE ID_fa=" + fa.getID());

		String sqlTeacher = "SELECT * FROM Teacher WHERE ID_teacher=";
		
		while(rs.next()) {
			TeacherTeaches t = new TeacherTeaches(
					Teacher.getOne(sqlTeacher + rs.getInt("ID_teacher"), db), 
					fa, 
					rs.getFloat("remuneration"));
			
			list.add(t);
		}
		
		rs.close();
		st.close();
		conn.close();
		
		return list;
	}
	
	public static TeacherTeaches getOne(String sql, Database db) throws SQLException, ParseException {
		Connection conn = db.getConnection();
		// Statement object needed to send statements to the database
		Statement st = conn.createStatement();
		// executeQuery will return a resultSet
		ResultSet rs = st.executeQuery(sql);

		String sqlTeacher = "SELECT * FROM Teacher WHERE ID_teacher=";
		String sqlFa = "SELECT * FROM Teacher WHERE ID_Fa=";
		
		rs.next();
		
		TeacherTeaches t = new TeacherTeaches(
				Teacher.getOne(sqlTeacher + rs.getInt("ID_teacher"), db), 
				FormativeAction.getOne(sqlFa + rs.getInt("ID_fa"), db), 
				rs.getFloat("remuneration"));

		rs.close();
		st.close();
		conn.close();
		
		return t;
	}
	
	
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public FormativeAction getFormativeAction() {
		return formativeAction;
	}

	public void setFormativeAction(FormativeAction formativeAction) {
		this.formativeAction = formativeAction;
	}

	public int getTeacherID() {
		return teacher.getID();
	}

	public int getFaID() {
		return formativeAction.getID();
	}

	public float getRemuneration() {
		return remuneration;
	}

	public void setRemuneration(float remuneration) {
		this.remuneration = remuneration;
	}
	
	
}
