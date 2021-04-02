package UserStory13577;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utils.Database;
import Utils.UnexpectedException;

public class Model {
	
	public List<String> getListFormativeAction() {
		try {
			Connection cn=DriverManager.getConnection("jdbc:sqlite:database.db"); 
			PreparedStatement ps = cn.prepareStatement("SELECT nameFa FROM FormativeAction;");
			ResultSet rs=ps.executeQuery();
			
			List<String> formativeActionList = new ArrayList<String>();
			
			while (rs.next()) {
				formativeActionList.add(rs.getString("nameFa"));
			}
			
			rs.close();
			ps.close();
			cn.close();
			
			return formativeActionList;
		}
		catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}
	
	public FormativeActionDetails getFormativeActionDetails(String selectedFormativeAction) {
		try {
			Connection cn=DriverManager.getConnection("jdbc:sqlite:database.db"); 
			StringBuilder query = new StringBuilder();
			query.append("SELECT  fa.nameFa, fa.status, fa.enrollmentStart, fa.enrollmentEnd, fa.totalPlaces, (fa.totalPlaces - count(e.ID_fa)) as leftPlaces ");
			query.append("FROM FormativeAction fa LEFT JOIN Enrollment e on e.ID_fa=fa.ID_fa ");
			query.append("WHERE fa.nameFa = ? ");
			query.append("GROUP BY fa.ID_fa;");
			PreparedStatement ps = cn.prepareStatement(query.toString());
			ps.setString(1, selectedFormativeAction);
			ResultSet rs=ps.executeQuery();			
			
			FormativeActionDetails fs = new FormativeActionDetails(
					rs.getString("nameFa"),
					rs.getString("status"), 
					rs.getDate("enrollmentStart"), 
					rs.getDate("enrollmentEnd"),
					rs.getInt("totalPlaces"),
					rs.getInt("leftPlaces"));
			
			rs.close();
			ps.close();
			cn.close();
			return fs;
		}
		catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/*
	 * TODO Description of Function, parameter and return value
	 * */
	public List<Registration> getRegistrationList(String selectedFormativeAction) {
		Database db = new Database();
		try {
			Connection cn = db.getConnection();
			StringBuilder query = new StringBuilder();
			query.append("SELECT p.name, p.surname, e.timeEn, Fee.amount, e.status ");
			query.append("FROM Professional p ");
			query.append("INNER JOIN Enrollment e ");
			query.append("ON p.ID_professional = e.ID_professional ");
			query.append("INNER JOIN FormativeAction fa ");
			query.append("ON e.ID_fa = fa.ID_fa ");
			query.append("INNER JOIN Fee ");
			query.append("ON fa.ID_fa = Fee.ID_fa ");
			query.append("WHERE lower(fa.nameFa) = lower(?);");
						
			PreparedStatement ps = cn.prepareStatement(query.toString());
			ps.setString(1, selectedFormativeAction);
			
			ResultSet rs = ps.executeQuery();
			
			List<Registration> registrationLists = new ArrayList<>();
						
			while (rs.next()) {
				Registration list = new Registration(
						rs.getString("name"),
						rs.getString("surname"),
						rs.getDate("timeEn"),
						rs.getFloat("amount"),
						rs.getString("status"));
				registrationLists.add(list);
			}
			
			return registrationLists;

		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}
}
