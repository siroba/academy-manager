package RegisterCancellations;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

import Entities.Enrollment;
import Entities.FormativeAction;
import Entities.Movement;
import Entities.Payment;
import Entities.Professional;
import Utils.Database;
import  PL53.util.Date;

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
		String query = "SELECT * FROM Enrollment INNER JOIN FormativeAction ON Enrollment.ID_fa=FormativeAction.ID_fa WHERE Enrollment.status='RECEIVED' AND FormativeAction.status<>'CANCELLED';"; // TODO: "WHERE status='received';"
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

	public void deleteEnrollment(Data selected, float refundAmount, Date dateIn, String sender, String receiver, String address, String fiscalNumber, boolean cash) throws SQLException, ParseException {
		
		// 1 - Cancel the enrollment
		String query = "UPDATE Enrollment SET status='CANCELLED' WHERE ID_fa=? AND ID_professional=?";
		db.executeUpdateQuery(query, selected.formativeAction.getID(), selected.professional.getID());
		
		// 2 - Create a new invoice for the refund
			// 2.1 - Get the amount payed by the professional
		//float payedAmount = getPayedAmount(selected.professional.getID(), selected.formativeAction.getID());
		
			// 2.2 - Get the appropiate percentage to refund
		//float refundPercentage = selected.formativeAction.getRefundPercentage(dateIn));
		
		if(refundAmount > 0) { // If there is nothing to return, do not proceed			
				// 2.3 - Create the Invoice for the same value payed

			Movement refundInvoice = new Movement(refundAmount, dateIn, sender, receiver, address, fiscalNumber, selected.formativeAction.getID(), selected.professional.getID(), ""); // TODO: Description

			refundInvoice.insert(db); // Insert it to update its ID
	
				// 2.4 - Generate a Payment for the amount due
			Payment p = new Payment(refundInvoice.getID(), refundAmount, dateIn, true, cash, ""); // TODO: Description
			//p.insert(db); // Insert it into the database
		}
	}
	
	public float getPayedAmount(int ID_professional, int ID_fa) {
		String query = "SELECT SUM(Payment.amount) FROM Payment " + 
				"INNER JOIN Invoice ON Invoice.ID_invoice=Payment.ID_invoice " + 
				"WHERE ID_professional=? AND Invoice.ID_fa=?;";
		return (float)((double)(db.executeQueryArray(query, ID_professional, ID_fa).get(0)[0]));
	}
}
