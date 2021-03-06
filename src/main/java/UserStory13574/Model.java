package UserStory13574;

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

	private Database db = new Database();
	private List<FormativeAction> formativeActions;

	public void loadFormativeActions() throws SQLException, ParseException {
		String query ="SELECT * FROM FormativeAction "
					+ "WHERE enrollmentEnd>datetime('now','localtime') "
					+ "GROUP BY FormativeAction.nameFa "
					+ "HAVING (SELECT COUNT(Enrollment.ID_fa) FROM Enrollment WHERE Enrollment.ID_fa=FormativeAction.ID_fa)<totalPlaces;";

		this.formativeActions = FormativeAction.get(query, db);
	}

	public List<FormativeAction> getFormativeActions(){
		return this.formativeActions;
	}

	public FormativeAction getFormativeAction(int n) {
		return this.formativeActions.get(n);
	}

	public Professional createProfessional(String name, String surname, String phone, String email) throws SQLException, InvalidFieldValue {
		if(!Professional.checkEmail(email)) throw new InvalidFieldValue("Email", email);
		else if(!Professional.checkPhone(phone)) throw new InvalidFieldValue("Phone", phone);

		Professional p = new Professional(name, surname, phone, email);

		return p;
	}

	public void doEnrollment(Professional p, Enrollment en) throws SQLException, ParseException {
		p.insert(db);
		en.insert(db);
	}

	public void loadFormativeActions(int n) {
		this.formativeActions = FormativeAction.create(n); // TODO: Do a query to the database
	}

	public List<FormativeAction> getFormativeActions(){
		return this.formativeActions;
	}

	public FormativeAction getFormativeAction(int n) {
		return this.formativeActions.get(n);
	}
}
