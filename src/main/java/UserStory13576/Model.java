package UserStory13576;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PL53.SI2020_PL53.Date;
import Utils.UnexpectedException;

public class Model {
	
	public List<FormativeActionList> getListFormativeAction(String filterStatus, String filterDateBegin, String filterDateEnd) {
		try {
			Connection cn=DriverManager.getConnection("jdbc:sqlite:database.db"); 
			PreparedStatement ps = cn.prepareStatement("SELECT fa.nameFa, fa.status, fa.enrollmentStart, fa.enrollmentEnd, fa.totalPlaces, (fa.totalPlaces - count(e.ID_fa)) as leftPlaces, fa.dateFA "
					+ "FROM FormativeAction fa "
					+ "LEFT JOIN Enrollment e ON e.ID_fa=fa.ID_fa "
					+ "WHERE fa.status = ? "
					+ "AND date(fa.dateFa) BETWEEN ? AND ?"
					+ "GROUP BY fa.ID_fa;");
			ps.setString(1, filterStatus);
			ps.setString(2, filterDateBegin);
			ps.setString(3, filterDateEnd);
			ResultSet rs=ps.executeQuery();
			
			
			List<FormativeActionList> formativeActionList = new ArrayList<>();
			
			while (rs.next()) {
				FormativeActionList fs = new FormativeActionList(
						rs.getString("nameFa"), 
						rs.getString("status"), 
						rs.getString("enrollmentStart"), 
						rs.getString("enrollmentEnd"), 
						rs.getInt("totalPlaces"), 
						rs.getInt("leftPlaces"), 
						new Date(Date.parse(rs.getDate("dateFA"))));
				formativeActionList.add(fs);
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

	
	/**
	 * Method to get detailed information about a course. 
	 * 		Objectives of the formative action
	 * 		Main Content of the formative action
	 * 		Location of the formative action
	 * 		Name of the teacher
	 * */
	public FormativeActionDetails getFormativeActionDetails(String lastSelectedKey) {
		try {
			Connection cn=DriverManager.getConnection("jdbc:sqlite:database.db"); 
			StringBuilder query = new StringBuilder();
			query.append("SELECT objectives, mainContent, location, teacherName ");
			query.append("from FormativeAction ");
			query.append("where nameFa=?;");
			PreparedStatement ps = cn.prepareStatement(query.toString());
			ps.setString(1, lastSelectedKey);
			ResultSet rs=ps.executeQuery();
			
			
			List<FormativeActionDetails> formativeActionDetails = new ArrayList<>();
			
			
			FormativeActionDetails fs = new FormativeActionDetails(
					rs.getString("objectives"),
					rs.getString("mainContent"), 
					rs.getString("location"), 
					rs.getString("teacherName"));
			formativeActionDetails.add(fs);
			
			rs.close();
			ps.close();
			cn.close();
			return fs;
		}
		catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}
}
