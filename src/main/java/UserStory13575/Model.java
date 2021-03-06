package UserStory13575;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import Utils.Database;
import Utils.UnexpectedException;
import Entities.Enrollment;
import Entities.FormativeAction;
import Entities.Payment;
//import Entities.FormativeAction;
import Entities.Professional;
import UserStory13575.Data;

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

	private List<Data> payments;

	/**
	 * Gets the list of active races in object form for a given registration date.
	 * 
	 * @throws SQLException
	 * @throws ParseException 
	 */

	/*
	 * public void setFormativeAction(FormativeAction fA) throws SQLException {
	 * String sql= "";
	 * 
	 * Connection conn = db.getConnection(); PreparedStatement p; }
	 */

	public List<Data> getActivePayments() throws SQLException, ParseException {

		List<Payment> pendingpayments = new ArrayList<Payment>();
		String sql = "SELECT DISTINCT Payment.* FROM Payment "
				+ "INNER JOIN  Enrollment  on  Enrollment.ID_fa=Payment.ID_fa "
				+ "INNER JOIN FormativeAction on FormativeAction.ID_fa=Enrollment.ID_fa "
				+ "WHERE  FormativeAction.status = 'active'; ";//AND Payment.confirmed = 0;

		String queryFa = "SELECT * FROM FormativeAction WHERE ID_fa=";
		String queryProf = "SELECT * FROM Professional WHERE ID_professional=";
		pendingpayments  = Payment.get(sql, db);
		List<Data> data = new ArrayList<Data>();

		for (Payment p : pendingpayments) {
			Data d = new Data();

			d.payment=p;
			d.formativeAction = FormativeAction.getOne(queryFa + p.getID_fa() + ";", db);
			d.professional = Professional.getOne(queryProf + p.getID_professional() + ";", db);
			d.enrollment= Enrollment.getOne(queryEnroll(d.formativeAction.getID(), d.professional.getID()), db);
			data.add(d);
		}
		return data; //TODO
		
	}

	public void initModel() throws SQLException, ParseException {
		payments = getActivePayments();

	}

	public Data getPayment(int index) {
		return payments.get(index);
	}
	
	private String queryEnroll(int ID_fa, int ID_prof) {
		return "SELECT * FROM Enrollment WHERE ID_fa=" + ID_fa + " AND ID_professional=" +ID_prof;

	}
}