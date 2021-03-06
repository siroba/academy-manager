package UserStory13578;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

import Entities.Enrollment;
import Entities.FormativeAction;
import Entities.Professional;
import Exceptions.InvalidFieldValue;
import Utils.Database;

/**
 * Access to course data, used as a model for the swing example and for unit and
 * user interface testing.
 *
 * <br/>
 * In the methods of this example all the business logic is performed by a
 * single sql query, so we always use the utility methods in the Database class
 * that are used in the example. <br/>
 * The utility methods in the Database class that use apache commons-dbutils and
 * control the connection are always used. In case different queries are
 * performed in the same method, the connection should be controlled from this
 * class. (see as example the implementation in Database).
 *
 * <br/>
 * If you use any other framework to manage persistence, the functionality
 * provided by this class would be the one assigned to the Services,
 * Repositories, Repositories, and Services, Repositories, and Services. <br/>
 * If you use some other framework to handle persistence, the functionality
 * provided by this class would be the one assigned to Services, Repositories
 * and DAOs.
 */
public class Model {

	private Database db = new Database();
	private Data[] data;

	public void initModel() throws SQLException, ParseException {
		data = initData();
	}

	public Data getData(int i) {
		return this.data[i];
	}

	public Data[] getAllData() {
		return this.data;
	}

	private Data[] initData() throws SQLException, ParseException {
		String query = "SELECT * FROM Enrollment;"; // TODO: "WHERE status='received';"
		String queryFa = "SELECT * FROM FormativeAction WHERE ID_fa=";
		String queryProf = "SELECT * FROM Professional WHERE ID_professional=";

		List<Enrollment> en = Enrollment.get(query, db);
		List<Data> data = new ArrayList<Data>();

		for (Enrollment e : en) {
			Data d = new Data();

			d.enrollment = e;
			d.formativeAction = FormativeAction.getOne(queryFa + e.getID_fa() + ";", db);
			d.professional = Professional.getOne(queryProf + e.getID_professional() + ";", db);

			data.add(d);
		}

		Data data2[] = new Data[data.size()];
		return data.toArray(data2);
	}

	public void deleteEnrollment(Data selected) {
		String query = "DELETE FROM Enrollment WHERE ID_fa=? AND ID_professional=?";
		
		db.executeUpdateQuery(query, selected.formativeAction.getID(), selected.professional.getID());
	}
}
