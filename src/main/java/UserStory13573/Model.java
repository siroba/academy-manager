package UserStory13573;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entities.FormativeAction;
import Entities.Teacher;
import Utils.Database;
import Utils.UnexpectedException;


/**
<br/>In the methods of this example all the business logic is performed by a single sql query, so we always use the utility methods in the Database class that are used in the example.
<br/>The utility methods in the Database class that use apache commons-dbutils and control the connection are always used. 
* In case different queries are performed in the same method, the connection should be controlled from this class. 
* (see as example the implementation in Database).
* 
<br/>If you use any other framework to manage persistence, the functionality provided by this class would be the one assigned to the Services, Repositories, Repositories, and Services, Repositories, and Services.
<br/>If you use some other framework to handle persistence, the functionality provided by this class would be the one assigned to Services, Repositories and DAOs.
*/
public class Model {

	private Database db=new Database();
	
	/**
	 * Insert a new formative action into the formativeActions table 
	 */
	public void setFormativeAction(FormativeAction fA) {
		// Query 
		String sql=
				 "insert into formativeAction(id ,name, objectives, mainContents, teacher, manager, remuneration, location, spaces, day, numberOfHours, enrollmentPeriodStart, enrollmentPeriodEnd, fee) values \r\n"
				 + "	(?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?)";
		
		// Execute update query 
		db.executeUpdateQuery(sql, fA.getID(), fA.getName(), fA.getObjectives(), fA.getMainContents(), fA.getTeacher().getID(), "exampleManager", fA.getRemuneration(), fA.getLocation(), fA.getDate().toString(), fA.getNumberOfHours(), fA.getSpaces(), fA.getEnrollmentPeriodStart().toString(), fA.getEnrollmentPeriodEnd().toString(), fA.getFee());

	}
	
	
//	/**
//	 * Get the teacher instance that equals teacherName 
//	 */
//	public Teacher getTeacher(String teacherName) {
//		
//		// Query 
//		String sql = "Select * from Teacher where name = ?";
//		
//		try {
//			// Set up connection
//			Connection cn=DriverManager.getConnection("jdbc:sqlite:DemoDB.db"); //NOSONAR
//			
//			//Statement object
//			PreparedStatement st = cn.prepareStatement(sql);
//			st.setString(1, teacherName);
//			
//			// Execute Query 
//			ResultSet rs=st.executeQuery();
//			
//			// Create new teacher object with the selected data 
//			Teacher teacher = new Teacher(
//					rs.getInt("ID"),
//					rs.getInt("salary"),
//					rs.getString("name"));
//	
//			// Close connection
//			rs.close();
//			st.close();
//			cn.close();
//			return teacher; 
//			
//		} catch (SQLException e) { 
//			throw new UnexpectedException(e);
//		}
//	}
}
