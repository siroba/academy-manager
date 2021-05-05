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
import PayATeacher.Data;
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
		//TODO


		String sql = "SELECT  FormativeAction.* FROM FormativeAction\r\n"
				+ "WHERE ID_fa NOT IN ( SELECT ID_fa FROM InvoiceTeacher);";


		List<FormativeAction> formativeActions = FormativeAction.get(sql, db);
		List<Data> data4 = new ArrayList<Data>();
		for  (FormativeAction fa: formativeActions) {
			fa.setSessions(Session.get("Select * From Session WHERE ID_fa = "+ fa.getID(), db));
			fa.setTeacherTeaches(TeacherTeaches.get(fa, db));
			for ( TeacherTeaches tt : fa.getTeacherTeaches()) {
				Data data3 = new Data();
				data3.formativeAction=fa;
				data3.teacher=tt.getTeacher();
				data4.add(data3);
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

	// Get fiscal number of teacher
	public String getFiscalNumber(Teacher t) throws SQLException {
		return t.getTeacherByName(db).getFiscalNumber();
	}

	// Update fiscal number of teacher 
	public void updateFiscalNumber(Teacher t, String fN) throws SQLException {
		t.updateTeacherFiscalNumber(db, fN);
	}




}
