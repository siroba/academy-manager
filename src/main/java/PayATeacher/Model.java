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
				
				/*String movementsQuery = "SELECT * FROM InvoiceTeacher WHERE ID_teacher=" + tt.getTeacher().getID() + " AND ID_fa=" + fa.getID();
				List<MovementTeacher> movements = MovementTeacher.get(movementsQuery, db);
				
				if(movements.isEmpty()) {*/
					Data data3 = new Data();
					data3.formativeAction=fa;
					data3.teacher=tt.getTeacher();
					data3.teacherTeaches = tt;
					data4.add(data3);
				/*}else {
					for(MovementTeacher mt: movements) {
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
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public String getFiscalNumber(Teacher t) throws SQLException {
		String sql = "SELECT fiscalNumber FROM Teacher WHERE ID_teacher=?";
		
		return db.executeQueryPojo(String.class, sql, t.getID()).get(0);
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
			String sqlPayment = "SELECT * FROM PaymentTeacher WHERE ID_invoice=" + i.getID();
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

	public boolean hasMovements(Data selectedRow) {
		String sql = "SELECT COUNT(*) FROM InvoiceTeacher WHERE ID_teacher=? AND ID_fa=?";
		
		int count = (Integer)db.executeQueryArray(sql, selectedRow.teacher.getID(), selectedRow.formativeAction.getID()).get(0)[0];
		
		return count > 0;
	}
}
