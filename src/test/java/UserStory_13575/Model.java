package UserStory_13575;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import BaseProject.Database;
import BaseProject.UnexpectedException;
//import Entities.FormativeAction;


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
public class Model {

	private Database db=new Database();
	
	/**
	 * Gets the list of active races in object form for a given registration date.
	 * @throws SQLException 
	 */

	/*public void setFormativeAction(FormativeAction fA) throws SQLException {
		String sql= "";
		
		Connection conn = db.getConnection();
		PreparedStatement p;
	}*/
}