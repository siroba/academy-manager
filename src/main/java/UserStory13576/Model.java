package UserStory13576;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PL53.SI2020_PL53.Date;
import Utils.UnexpectedException;

/**
 * btnListFormativeActions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller controller13576=new Controller(new Model(), new View());
				controller13576.initController();
			}
		});
 */

public class Model {
	
	/**
	 * Gets the list formative actions in object formquery.append("SELECT * FROM FormativeAction where status='" + filterStatus + "' and dateFa between '" + filterDateBegin + "' and '" + filterDateEnd + "';");

	 */
	public List<FormativeActionList> getListFormativeAction(String filterStatus, String filterDateBegin, String filterDateEnd) {
		try {
			Connection cn=DriverManager.getConnection("jdbc:sqlite:DemoDB.db"); 
			StringBuilder query = new StringBuilder();
			query.append("SELECT fa.objectives, fa.mainContent, fa.location, t.name "); 
			query.append("from FormativeAction fa, Teacher t ");
			query.append("where fa.nameFa=? and fa.ID_teacher=t.ID_teacher;");
			PreparedStatement ps = cn.prepareStatement("SELECT * FROM FormativeAction where status=? and date(dateFa) BETWEEN ? and ?;");
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
						rs.getInt("totalPlaces"), 
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
	 * Objectives of the formative action
	 * Main Content of the formative action
	 * Location of the formative action
	 * Name of the teacher
	 * */
	public FormativeActionDetails getFormativeActionDetails(String lastSelectedKey) {
		try {
			Connection cn=DriverManager.getConnection("jdbc:sqlite:DemoDB.db"); 
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
