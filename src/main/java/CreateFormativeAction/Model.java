package CreateFormativeAction;

import java.sql.SQLException;
import java.util.List;

import Entities.Fee;
import Entities.FormativeAction;
import Entities.Session;
import Entities.Teacher;
import Entities.TeacherTeaches;
import Utils.Database;


public class Model {

	private Database db=new Database();
	
	private Teacher[] teachers;
	
	/**
	 * Insert a new formative action as well as the associated fees and sessions into the DB 
	 * @throws  
	 * @throws SQLException 
	 */
	public void setFormativeAction(FormativeAction fA, List<TeacherTeaches> teachers) throws SQLException {
		fA.insert(db);
    
		for(TeacherTeaches tt: teachers) {
			// Needed, because previously, the formative action did not exist
			TeacherTeaches newTt = fA.teach(tt.getTeacher(), tt.getRemuneration());
			newTt.insert(db);
		}
		
		for(Session s: fA.getSessions()) {
			s.insert(db);
		}
		for(Fee f: fA.getFees()) {
			f.insert(db);
		}
	}
	
	/**
	 * Gets all the teachers in the database and stores them in a private array. It also returns the List.
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Teacher> getAllTeachers() throws SQLException {
		List<Teacher> t = Teacher.get("SELECT * FROM Teacher", db);
		
		teachers = t.toArray(new Teacher[t.size()]);
		
		return t;
	}
}