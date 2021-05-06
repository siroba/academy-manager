package PayATeacher;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import Entities.FormativeAction;
import Entities.MovementTeacher;
import Entities.PaymentTeacher;
import Entities.Session;
import Entities.Teacher;
import Entities.TeacherTeaches;
import PL53.util.Date;
import Utils.Database;

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
		String sql = "SELECT  FormativeAction.* FROM FormativeAction "
				+ "WHERE ID_fa IN (SELECT ID_fa FROM TeacherTeaches)";

		List<FormativeAction> formativeActions = FormativeAction.get(sql, db);
		List<Data> data4 = new ArrayList<Data>();
		for  (FormativeAction fa: formativeActions) {
			fa.setSessions(Session.get("Select * From Session WHERE ID_fa = "+ fa.getID(), db));
			fa.setTeacherTeaches(TeacherTeaches.get(fa, db));
			for ( TeacherTeaches tt : fa.getTeacherTeaches()) {
				
				String movementsQuery = "SELECT * FROM InvoiceTeacher WHERE ID_teacher=" + tt.getTeacher().getID() + " AND ID_fa=" + fa.getID();
				List<MovementTeacher> movements = MovementTeacher.get(movementsQuery, db);
				
				Data data3 = new Data();
				data3.formativeAction=fa;
				data3.teacher=tt.getTeacher();
				data3.teacherTeaches = tt;
				data3.movementsTeacher = movements;
				data4.add(data3);
					
					/*for(MovementTeacher mt: movements) {
						String paymens = "SELECT * FROM PaymentTeacher WHERE ID_invoice=" + mt.getID();
						List<PaymentTeacher> payments = PaymentTeacher.get(paymens, db);
						
						if(payments.isEmpty()) {
							Data data3 = new Data();
							data3.formativeAction=fa;
							data3.teacher=tt.getTeacher();
							data3.teacherTeaches = tt;
							data3.movementTeacher = mt;
							data4.add(data3);
						}else {
							for(PaymentTeacher pt: payments) {
								Data data3 = new Data();
								data3.formativeAction=fa;
								data3.teacher=tt.getTeacher();
								data3.teacherTeaches = tt;
								data3.movementTeacher = mt;
								data3.paymentTeacher = pt;
								data4.add(data3);
							}
						}
					}
				}*/

			}
		}
		
		Data data2[] = new Data[data4.size()];
		data2= data4.toArray(data2);
		return data2;
	}

	public void insertInvoice ( MovementTeacher invoice, PaymentTeacher paymentTeacher) throws SQLException, ParseException {
		invoice.insert(db);
		paymentTeacher.setInvoiceID(invoice.getID());
		paymentTeacher.insert(db);
	}
	
	/**
	 * Returns the Fiscal Number of a teacher
	 * 
	 * @param iD_teacher
	 * @return
	 * @throws SQLException
	 */
	public String getFiscalNumber(int iD_teacher) throws SQLException {
		String sql = "SELECT fiscalNumber FROM Teacher WHERE ID_teacher=?";
		
		return (String)db.executeQueryArray(sql, iD_teacher).get(0)[0];
	}
	
	/**
	 * Updates the fiscal number of the teacher calling the function {@link Entities.Teacher#updateTeacherFiscalNumber(Database, String)}
	 * 
	 * @param t
	 * @param fN
	 * @throws SQLException
	 */
	public void updateFiscalNumber(Teacher t, String fN) throws SQLException {
		t.updateTeacherFiscalNumber(db, fN);
	}
	
	/**
	 * Creates a new {@link Entities.PaymentTeacher} and inserts it into the database
	 * 
	 * @param invoiceID
	 * @param toReturn
	 * @param payDate
	 * @param confirmed
	 * @throws SQLException
	 * @throws ParseException
	 */
	public void createPaymentRefund(String invoiceID, float toReturn, Date payDate, boolean confirmed)
			throws SQLException, ParseException {

		PaymentTeacher p = new PaymentTeacher(invoiceID, toReturn, payDate, confirmed, ""); // TODO: Add description
		p.insert(db);
	}

	public List<String[]> getDataForPaymentsTable(Data selectedRow) throws SQLException {
		// MovementTeacher and PaymentTeacher
		
		List<String[]> str = new ArrayList<String[]>();
		
		String sql = "SELECT * FROM InvoiceTeacher WHERE ID_fa=" + selectedRow.formativeAction.getID() + " AND ID_teacher=" + selectedRow.teacher.getID();
		List<MovementTeacher> it = MovementTeacher.get(sql, db);
		
		for(MovementTeacher i: it) {
			String sqlPayment = "SELECT * FROM PaymentTeacher WHERE ID_invoice='" + i.getID() + "'";
			List<PaymentTeacher> mt = PaymentTeacher.get(sqlPayment, db);
		
			for(PaymentTeacher m: mt) {
				String sender = i.getSender();
				String receiver = i.getReceiver();
				float amount = m.getAmount();
				String datePayment = m.getPayDate().toString();
	
				// If the amount is negative, the payment was from COIIPA
				if (amount < 0) {
					String tmp = sender;
					sender = receiver;
					receiver = tmp;
	
					// Otherwise, if the sender is COIIPA, just set the amount to be negative
				} else if (sender.equals("COIIPA")) {
					amount *= -1;
				}
				
				str.add(new String[] {sender, receiver, datePayment, Float.toString(amount)});
			}
		}
		return str;
	}

	/**
	 * Checks if the selected teacher has any "InvoiceTeacher" on his name for the selected FormativeAction.
	 * 
	 * @param selectedRow
	 * @return
	 */
	public boolean hasMovements(Data selectedRow) {
		String sql = "SELECT COUNT(*) FROM InvoiceTeacher WHERE ID_teacher=? AND ID_fa=?";
		
		int count = (Integer)db.executeQueryArray(sql, selectedRow.teacher.getID(), selectedRow.formativeAction.getID()).get(0)[0];
		
		return count > 0;
	}

	/**
	 * Returns the sum of all the positive payments made for this TeacherTeaches
	 * 
	 * @param selectedRow
	 * @return
	 */
	public float getAmountPaid(Data selectedRow) {
		String sql = "SELECT COALESCE((SELECT SUM (PaymentTeacher.amount) FROM Payment "
				+ "INNER JOIN InvoiceTeacher ON PaymentTeacher.ID_invoice=InvoiceTeacher.ID_Invoice AND PaymentTeacher.amount >0 " 
				+ "GROUP BY PaymentTeacher.ID_invoice "
				+ "HAVING InvoiceTeacher.ID_fa=? AND InvoiceTeacher.ID_teacher=? AND PaymentTeacher.amount >0 ), 0.0) as paid;";

		
		double sumPayments = (double) (db
				.executeQueryArray(sql, selectedRow.formativeAction.getID(), selectedRow.teacher.getID()).get(0)[0]);
		
		
		return (float) sumPayments;
	}

	/**
	 * Returns the sum of all the negative payments made for this TeacherTeaches
	 * 
	 * @param selectedRow
	 * @return
	 */
	public float getAmountReturned(Data selectedRow) {
		String sql = "SELECT COALESCE((SELECT SUM (PaymentTeacher.amount) FROM PaymentTeacher "
				+ "INNER JOIN InvoiceTeacher ON PaymentTeacher.ID_invoice=InvoiceTeacher.ID_Invoice AND PaymentTeacher.amount < 0 "
				+ "GROUP BY Payment.ID_invoice "
				+ "HAVING InvoiceTeacher.ID_fa=? AND InvoiceTeacher.ID_teacher=? AND PaymentTeacher.amount < 0 ), 0.0);";

		double sumPayments =  (double) (db
				.executeQueryArray(sql, selectedRow.formativeAction.getID(), selectedRow.teacher.getID()).get(0)[0]);
		return (float)sumPayments;
	}

	/**
	 * Gets the total sum of all the payments made (positive and negative) for this TeacherTeaches
	 * 
	 * @param selectedRow
	 * @return
	 */
	public float getAmountTotalPaid(Data selectedRow) {
		String sql = "SELECT COALESCE((SELECT SUM (PaymentTeacher.amount) FROM PaymentTeacher "
				+ "INNER JOIN InvoiceTeacher ON PaymentTeacher.ID_invoice=InvoiceTeacher.ID_Invoice " + " GROUP BY PaymentTeacher.ID_invoice "
				+ "HAVING InvoiceTeacher.ID_fa=? AND InvoiceTeacher.ID_teacher=?), 0.0);";

		float sumPayments = (float) ((double) (db.executeQueryArray(sql, selectedRow.formativeAction.getID(), selectedRow.teacher.getID()).get(0)[0]));

		
		return sumPayments;
	}

	
	/**
	 * Creates a payment and inserts it into the db
	 * 
	 * @param id_invoice
	 * @param amount
	 * @param payDate
	 * @param confirmed
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 */
	public void createPayment(String id_invoice, float amount, Date payDate, boolean confirmed) throws SQLException, ParseException {		
		PaymentTeacher p = new PaymentTeacher(id_invoice, amount, payDate, confirmed, ""); // TODO: Description
		p.insert(db);
	}
}
