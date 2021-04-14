package CreateFormativeAction;

import java.sql.SQLException;

import Entities.Fee;
import Entities.FormativeAction;
import Entities.Session;
import Entities.Teacher;
import Entities.TeacherTeaches;
import Utils.Database;


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
	 * Insert a new formative action as well as the associated fees and sessions into the DB 
	 * @throws  
	 * @throws SQLException 
	 */
	public void setFormativeAction(FormativeAction fA, String teacherName, float remuneration) throws SQLException {
		fA.insert(db);

		Teacher teacher = new Teacher(teacherName);
		teacher.insert(db); // This also assigns an ID to the teacher, so that it can be used in the TeacherTeaches table
		
		TeacherTeaches t = fA.teach(teacher, remuneration);
		t.insert(db);
		

		
		for(Session s: fA.getSessions()) {
			s.insert(db);
		}
		for(Fee f: fA.getFees()) {
			f.insert(db);
		}
	}
	
//	/** // TODO: Reuse for later sprints
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