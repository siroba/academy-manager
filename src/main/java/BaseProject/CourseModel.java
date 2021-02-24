package BaseProject;
import java.util.*;


/**
* Access to course data, 
* used as a model for the swing example and for unit and user interface testing.
* 
<br/>In the methods of this example all the business logic is performed by a single sql query, so we always use the utility methods in the Database class that are used in the example.
<br/>The utility methods in the Database class that use apache commons-dbutils and control the connection are always used. 
* In case different queries are performed in the same method, the connection should be controlled from this class. 
* (see as example the implementation in Database).
* 
<br/>If you use any other framework to manage persistence, the functionality provided by this class would be the one assigned to the Services, Repositories, Repositories, and Services, Repositories, and Services.
<br/>If you use some other framework to handle persistence, the functionality provided by this class would be the one assigned to Services, Repositories and DAOs.
*/
public class CourseModel {

	private Database db=new Database();
	
	/**
	 * Gets the list of active races in object form for a given registration date.
	 */
	public List<CourseEntity> getListCourses() {
		String sql=
				 "SELECT id, name from courses";
		//String d=Util.dateToIsoString(fechaInscripcion);
		return db.executeQueryPojo(CourseEntity.class, sql);
	}
	
	public CourseEntity getCourse(int id) {
		String sql="SELECT day, teacher, location, startTime, endTime, price, spots from courses where id=?";
		List<CourseEntity> courses=db.executeQueryPojo(CourseEntity.class, sql, id);
		validateCondition(!courses.isEmpty(),"Id de carrera no encontrado: "+id);
		return courses.get(0);
	}
	
	private void validateCondition(boolean condition, String message) {
		if (!condition)
			throw new ApplicationException(message);
	}
	

}
