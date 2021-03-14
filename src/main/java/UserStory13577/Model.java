package UserStory13577;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}
