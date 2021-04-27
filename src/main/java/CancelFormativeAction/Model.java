package CancelFormativeAction;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Entities.Enrollment;
import Entities.FormativeAction;
import Entities.Invoice;
import Entities.InvoiceTeacher;
import Entities.Payment;
import Entities.PaymentTeacher;
import Entities.Professional;
import Entities.Teacher;
import PL53.util.Date;
import RegisterCancellations.Data;
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
		String query = "SELECT SUM(amount) FROM Invoice GROUP BY ID_fa HAVING ID_fa=?";

		List<Object[]> obj = db.executeQueryArray(query, data[index].getID());

		if (obj.size() == 0)
			return 0;

		return (double) obj.get(0)[0];
	}

	public double getInvoices(int index) {
		String query = "SELECT SUM(amount) FROM InvoiceTeacher GROUP BY ID_fa HAVING ID_fa=? AND ID_invoice IN (SELECT ID_Invoice FROM PaymentTeacher)";


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
		String queryEnrollments = "SELECT Enrollment.* FROM Enrollment \n"
				+ "INNER JOIN Invoice ON Invoice.ID_fa=Enrollment.ID_fa AND Invoice.ID_professional=Enrollment.ID_professional "
				+ "WHERE Invoice.receiver='COIIPA' AND ID_invoice IN (SELECT ID_invoice FROM Payment) AND Enrollment.ID_fa=" + fa.getID()
				+ " AND (Enrollment.ID_fa,Enrollment.ID_professional) NOT IN (SELECT ID_fa, ID_professional FROM Invoice WHERE sender='COIIPA');";
				
				/*+ "INNER JOIN FormativeAction ON FormativeAction.ID_fa=Enrollment.ID_fa " 
				+ "WHERE FormativeAction.status='CANCELLED' AND "
				+ "		(SELECT inv.amount FROM Invoice as inv  "
				+ "		WHERE inv.ID_professional=Enrollment.ID_professional AND  "
				+ "				inv.ID_fa=Enrollment.ID_fa AND  "
				+ "				sender='COIIPA')<>(SELECT inv.amount FROM Invoice as inv "
				+ "		WHERE inv.ID_professional=Enrollment.ID_professional AND  "
				+ "				inv.ID_fa=Enrollment.ID_fa AND  "
				+ "				receiver='COIIPA');";*/
		
		String queryInvoice = "SELECT * FROM Invoice WHERE ID_fa=" + fa.getID() + " AND ID_professional=";
		String queryPayments = "SELECT * FROM Payment WHERE ID_invoice=";
		String queryProfessional = "SELECT * FROM Professional WHERE ID_professional=";
		
		List<Data> allData = new ArrayList<Data>();
		
		for(Enrollment en: Enrollment.get(queryEnrollments, db)) {
			Data d = new Data();
			d.formativeAction = fa;
			d.enrollment = en;
			d.invoice = Invoice.getOne(queryInvoice + en.getID_professional(), db);
			d.professional = Professional.getOne(queryProfessional + d.invoice.getID_professional(), db);
			d.payment = Payment.getOne(queryPayments + d.invoice.getID(), db);
			
			allData.add(d);
		}
		
		Data[] dd = new Data[allData.size()];
		dd = allData.toArray(dd);
		return dd;
	}

	/**
	 * Generates an invoice for the teachers of the formative action that have
	 * already been payed.
	 * 
	 * @param index Selected formative action
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	public void invoiceTeachers(int index, Date dateIn, String fiscalNumber, String address) throws SQLException, ParseException {
		String query = "SELECT * FROM InvoiceTeacher WHERE ID_fa=" + data[index].getID();
		
		for(InvoiceTeacher inv: InvoiceTeacher.get(query, db)) {
			// Get the teacher involved in the payment
			Teacher t = Teacher.getOne("SELECT * FROM Teacher WHERE ID_teacher=" + inv.getID_teacher(), db);
			
			// As for the ID of the new invoice 
			// TODO: PO does not want this
			String invoiceId = JOptionPane.showInputDialog(null, "What is the ID of the invoice for the teacher " + t.getName() + " for " + inv.getAmount() + "€?", null);
			
			// A simple description for the transaction
			String description = "The formative action " + data[index].getName() + " was cancelled.";
			
			// Create the new invoice and insert it into the DB
			InvoiceTeacher newInv = new InvoiceTeacher(invoiceId, inv.getAmount(), inv.getID_fa(), dateIn, t.getName(), "COIIPA", fiscalNumber, address, t.getID(), description);
			newInv.insert(db);
			
			// Create a new Payment for that Invoice and insert it
			PaymentTeacher p = new PaymentTeacher(newInv.getID(), inv.getAmount(), dateIn, true, ""); // TODO: Description
			p.insert(db);
		}
		
	}

	public FormativeAction[] getCancelled() {
		return cancelled;
	}

	public int getUsedPlaces(int ID_fa) {
		String query = "SELECT COUNT(*) FROM Enrollment WHERE ID_fa=?;";
		
		return (int)(db.executeQueryArray(query, ID_fa).get(0)[0]);
	}

	public void payRefund(Invoice in, boolean cash) throws SQLException, ParseException {
		in.insert(db);
		
		Payment p = new Payment(in.getID(), in.getAmount(), in.getDateIn(), true, cash, ""); // TODO: Description
		p.insert(db);
	}

}
