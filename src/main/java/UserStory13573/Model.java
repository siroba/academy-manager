package UserStory13573;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import Entities.FormativeAction;
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
		String sql=
				 "insert into formativeActions(name, objectives, mainContents, teacher, remuneration, location, spaces, day, numberOfHours, enrollmentPeriodStart, enrollmentPeriodEnd) values \r\n"
				 + "	(?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?)";
		db.executeUpdateQuery(sql, fA.getName(), fA.getObjectives(), fA.getMainContents(), fA.getTeacher(), fA.getRemuneration(), fA.getLocation(), fA.getDay(), fA.getNumberOfHours(), fA.getSpaces(), fA.getEnrollmentPeriodStart(), fA.getEnrollmentPeriodEnd());
	}
}
