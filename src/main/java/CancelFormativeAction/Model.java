package CancelFormativeAction;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Entities.Enrollment;
import Entities.FormativeAction;
import Entities.Payment;
import Entities.Professional;
import UserStory13578.Data;
import Utils.Database;

public class Model {
	private Database db = new Database();
	private FormativeAction[] data, cancelled;

	public void initModel() throws SQLException, ParseException {
		String query = "SELECT * FROM FormativeAction WHERE status IN ('ACTIVE', 'DELAYED')";

		List<FormativeAction> la = FormativeAction.get(query, db);

		data = new FormativeAction[la.size()];
		data = la.toArray(data);
		

		String queryCancelled = "SELECT * FROM FormativeAction WHERE status='CANCELLED'";

		List<FormativeAction> laC = FormativeAction.get(queryCancelled, db);
		
		cancelled = new FormativeAction[laC.size()];
		cancelled = laC.toArray(cancelled);
	}

	public FormativeAction[] getAllData() {
		return data;
	}

	public double getPayments(int index) {
		String query = "SELECT SUM(amount) FROM Payment GROUP BY ID_fa HAVING ID_fa=?";

		List<Object[]> obj = db.executeQueryArray(query, data[index].getID());

		if (obj.size() == 0)
			return 0;

		return (double) obj.get(0)[0];
	}

	public double getInvoices(int index) {
		String query = "SELECT SUM(amount) FROM PaymentTeacher "
				+ "INNER JOIN Invoice ON Invoice.ID_invoice=PaymentTeacher.ID_invoice "
				+ "GROUP BY ID_fa HAVING ID_fa=?";


		List<Object[]> obj = db.executeQueryArray(query, data[index].getID());

		if (obj.size() == 0)
			return 0;

		return (double) obj.get(0)[0];
	}

	public void cancel(int index) {
		String query="UPDATE FormativeAction SET status='CANCELLED' WHERE ID_fa=?";
		
		db.executeUpdateQuery(query, data[index].getID());
	}

	public Data[] getSolicitedRefunds(FormativeAction fa) throws SQLException {
		String queryEnrollments = "SELECT Enrollment.* FROM Enrollment "
								+ "INNER JOIN Payment on Payment.ID_fa=Enrollment.ID_fa AND Payment.ID_professional=Enrollment.ID_professional "
								+ "WHERE Enrollment.ID_fa=" + fa.getID() + ";";

		String queryPayments = "SELECT * FROM Payment WHERE ID_fa=" + fa.getID() + " AND ID_professional=";
		String queryProfessional = "SELECT * FROM Professional WHERE ID_professional=";
		
		List<Data> allData = new ArrayList<Data>();
		
		for(Enrollment en: Enrollment.get(queryEnrollments, db)) {
			Data d = new Data();
			d.formativeAction = fa;
			d.enrollment = en;
			d.professional = Professional.getOne(queryProfessional + en.getID_professional(), db);
			d.payment = Payment.getOne(queryPayments + en.getID_professional(), db);
			
			allData.add(d);
		}
		
		Data[] dd = new Data[allData.size()];
		dd = allData.toArray(dd);
		return dd;
	}
	
	/**
	 * Refunds all the money to the professionals
	 * 
	 * @param index Selected formative action
	 */
	public void refund(int index) {
		// TODO Auto-generated method stub

	}

	/**
	 * Generates an invoice for the teachers of the formative action that have
	 * already been payed.
	 * 
	 * @param index Selected formative action
	 */
	public void invoiceTeachers(int index) {
		// TODO Auto-generated method stub

	}

	public FormativeAction[] getCancelled() {
		return cancelled;
	}

}
