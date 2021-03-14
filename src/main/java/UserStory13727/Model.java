package UserStory13727;

import java.sql.SQLException;
import java.util.List;

import Entities.FormativeAction;
import Utils.Database;

public class Model {
	private Database db = new Database();
	private FormativeAction[] data;

	public void initModel() throws SQLException {
		String query = "SELECT * FROM FormativeAction WHERE status IN ('ACTIVE', 'DELAYED')";

		List<FormativeAction> la = FormativeAction.get(query, db);

		data = new FormativeAction[la.size()];
		data = la.toArray(data);
	}

	public FormativeAction[] getAllData() {
		return data;
	}

	public double getPayments(int index) {
		String query = "SELECT SUM(amount) FROM Payment GROUP BY ID_fa HAVING ID_fa=?";
		int id = data[index].getID();
		List<Object[]> obj = db.executeQueryArray(query, data[index].getID());

		if (obj.size() == 0)
			return 0;

		return (double) obj.get(0)[0];
	}

	public double getInvoices(int index) {
		String query = "SELECT SUM(amount) FROM PaymentTeacher "
				+ "INNER JOIN Invoice ON Invoice.ID_invoice=PaymentTeacher.ID_invoice "
				+ "GROUP BY ID_fa HAVING ID_fa=?";

		int id = data[index].getID();
		List<Object[]> obj = db.executeQueryArray(query, data[index].getID());

		if (obj.size() == 0)
			return 0;

		return (double) obj.get(0)[0];
	}

	public void cancel(int index) {
		String query="UPDATE FormativeAction SET status='CANCELLED' WHERE ID_fa=?";
		
		db.executeUpdateQuery(query, data[index].getID());
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

}
