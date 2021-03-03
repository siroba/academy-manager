package UserStory13576;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entities.FormativeAction;
import Entities.Teacher;
import Entities.TrainingManager;
import PL53.SI2020_PL53.Date;
import Utils.UnexpectedException;

public class Model {
	
	
	// TODO add filter
	/**
	 * Gets the list formative actions in object form
	 */
	public List<FormativeAction> getListFormativeActions() {
		try {
			Connection cn=DriverManager.getConnection("jdbc:sqlite:DemoDB.db"); 
			Statement stmt=cn.createStatement(); 
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM FormativeAction;");
			ResultSet rs=stmt.executeQuery(query.toString());
			
			List<FormativeAction> formativeActions = new ArrayList<>();
			
			while(rs.next()) {
				FormativeAction fA = new FormativeAction(
						rs.getString("nameFa"), 		// name of the action
						rs.getString("objectives"), 	// objectives
						rs.getString("mainContent"),	//main content
						new Teacher(1, "teacher1"), 	//rs.getString("teacher"), 
						new TrainingManager("trainer1"),//rs.getString("manager"), 
						rs.getInt("renumeration"),		//renumeration
						rs.getString("location"),  		//location
						Date.parseString("2021-10-10"),//Date.parse(rs.getDate("dateFa")),	//date of action
						rs.getString("status"), 		//status
						0, 								//number of hours
						0, 								//slots
						Date.random(), 					//Enrollment start
						Date.random());					//Enrollment end
				formativeActions.add(fA);
			}
			rs.close();
			stmt.close();
			cn.close();
			return formativeActions;
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
			Statement stmt=cn.createStatement(); 
			StringBuilder query = new StringBuilder();
			query.append("SELECT fa.objectives, fa.mainContent, fa.location, t.name ");
			query.append("from FormativeAction fa, Teacher t ");
			query.append("where fa.nameFa=? and fa.ID_teacher=t.ID_teacher;");
			PreparedStatement ps = cn.prepareStatement(query.toString());
			ps.setString(1, lastSelectedKey);
			ResultSet rs=ps.executeQuery();
			
			
			List<FormativeActionDetails> formativeActionDetails = new ArrayList<>();
			
			
			FormativeActionDetails fs = new FormativeActionDetails(
					rs.getString("objectives"),
					rs.getString("mainContent"), 
					rs.getString("location"), 
					rs.getString("name"));
			formativeActionDetails.add(fs);
			
			rs.close();
			stmt.close();
			cn.close();
			return fs;
		}
		catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}
}
