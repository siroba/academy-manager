package RegisterPayment;

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
import Entities.Fee;
import Entities.FormativeAction;
import Entities.Invoice;
import Entities.Payment;
import Entities.PaymentTeacher;
//import Entities.FormativeAction;
import Entities.Professional;
import PL53.util.Date;
import RegisterPayment.Data;

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
		if(i<0 || i> this.data.length-1) {
			return null;
		}
		return this.data[i];
	}

	public Data[] getAllData() {
		return this.data;
	}

	private Data[] initData() throws SQLException, ParseException {
		List<Data> data = new ArrayList<Data>();
		String sql = "	SELECT DISTINCT Invoice.* FROM Invoice\r\n"
				+ "		WHERE Invoice.ID_invoice NOT IN ( SELECT ID_invoice From Payment);";
		String queryFa = "SELECT * FROM FormativeAction WHERE ID_fa=";
		String queryProf = "SELECT * FROM Professional WHERE ID_professional=";



		List<Invoice> invoices= Invoice.get(sql, db);
		for ( Invoice in : invoices){
			String queryEnr = "SELECT * FROM Enrollment WHERE ID_fa=" +in.getID_fa() +" AND ID_professional=" +in.getID_professional();
			String queryFee = "SELECT * FROM Fee WHERE ID_fa="+in.getID_fa() + " AND category='";
			Data d = new Data() ;
			d.invoice= in;
			d.formativeAction = FormativeAction.getOne(queryFa + in.getID_fa(), db);
			d.professional=Professional.getOne(queryProf + in.getID_professional(), db);
			d.enrollment=Enrollment.getOne(queryEnr, db);
			d.fee= Fee.getOne(queryFee + d.enrollment.getGroup() + "'", db).getAmount();
			data.add(d);


		}


		Data data2[] = new Data[data.size()];
		return data.toArray(data2);

	}

	private String queryEnroll(int ID_fa, int ID_prof) {
		return "SELECT * FROM Enrollment WHERE ID_fa=" + ID_fa + " AND ID_professional=" + ID_prof;
	}

	void createPayment(int id_pay ,int id_invoice,  float amount , Date datePay, boolean isCash ) throws SQLException, ParseException {
		Payment p = new Payment(id_invoice,  amount,  datePay, true, isCash);
		p.insert(db);
	}




}
