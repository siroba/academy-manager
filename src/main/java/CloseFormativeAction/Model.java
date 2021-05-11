package CloseFormativeAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Utils.Database;
import Utils.UnexpectedException;

public class Model {

	Database db;

	public Model() {
		db = new Database();
	}

	/*
	 * Function to get all Formative Actions that can be closed. These are the ones with status ACTIVE or DELAYED all other cannot be closed
	 * */
	public List<String> getListFormativeAction() {
		try {
			// get all names of the active or delayed formative actions from the database
			Connection cn = db.getConnection();
			PreparedStatement ps = cn
					.prepareStatement("SELECT nameFa FROM FormativeAction where lower(status) = 'active' OR lower(status) = 'delayed';");
			ResultSet rs = ps.executeQuery();

			List<String> formativeActionList = new ArrayList<String>();

			// add the results to a list
			while (rs.next()) {
				formativeActionList.add(rs.getString("nameFa"));
			}

			// close resultset, prepared statement and connection
			rs.close();
			ps.close();
			cn.close();

			// return the list with the names of the formative actions
			return formativeActionList;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}

	/*
	 * Function to get the sum of the payments from the professionals and to the professionals (refunds)
	 * */
	public int getSumIncomeConfirmedProfessional(String nameFa) {
		try {
			int ret;
			Connection cn = db.getConnection();

			// get the sum of all movements for a specific formative action
			PreparedStatement ps = cn
					.prepareStatement("select distinct(T.ID_fa), T.nameFa, T.status, T.income_confirmed from "
							+ "(select fA.ID_fa, fA.nameFa, fA.status, (ifnull(sum(CASE WHEN i.receiver='COIIPA' THEN p.amount END), 0) - ifnull(sum(CASE WHEN i.sender='COIIPA' THEN p.amount END), 0)) as income_confirmed "
							+ "from FormativeAction fA left join Enrollment e on fA.ID_fa=e.ID_fa left join Invoice i on e.ID_professional=i.ID_professional AND e.ID_fa=i.ID_fa left join Payment p on p.ID_invoice=i.ID_invoice group by fA.ID_fa) T "
							+ "left join Session s on s.ID_fa=T.ID_fa " + "where T.nameFa = ?");
			ps.setString(1, nameFa);

			ResultSet rs = ps.executeQuery();
			// save the result 
			ret = rs.getInt("income_confirmed");
			
			// close everything
			rs.close();
			ps.close();
			cn.close();
			
			//return the result
			return ret;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}


	/**
	 * Function to get the amount of income through professional payments according to the number of enrollments and the amount of the fee
	 * 
	 * @param nameFa: Name of the corresponding formative action
	 * @return: Expected sum of the Income 
	 */
	public int getSumIncomeExpectedProfessional(String nameFa) {
		try {

			int ret;
			Connection cn = db.getConnection();

			// get expected income through enrollments and corresponding fees as well as the cancelled enrollments 
			PreparedStatement ps = cn
					.prepareStatement("select distinct(T.ID_fa), T.nameFa, T.status, T.income_estimated from "
							+ "(select fA.ID_fa, fA.nameFa, fA.status,  (ifnull(sum(CASE WHEN i.receiver='COIIPA' THEN i.amount END), 0) - ifnull(sum(CASE WHEN i.sender='COIIPA' THEN i.amount END), 0)) as income_estimated "
							+ "from FormativeAction fA left join Enrollment e on fA.ID_fa=e.ID_fa left join Invoice i on e.ID_professional=i.ID_professional AND e.ID_fa=i.ID_fa group by fA.ID_fa) T "
							+ "left join Session s on s.ID_fa=T.ID_fa " + "where T.nameFa = ?");
			ps.setString(1, nameFa);

			ResultSet rs = ps.executeQuery();

			// save the result
			ret = rs.getInt("income_estimated");

			// close everything
			rs.close();
			ps.close();
			cn.close();

			// return the expected income
			return ret;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Function to check if there are enrollments that are with status received.
	 * Important when expected and confirmed income equal but some payed to much and some to less
	 * 
	 * @param fa: Name of the formative action this should be checked
	 * @return: true if there are professionals with status received
	 */
	public boolean getProfessionalsWithStatusReceived(String fa) {
		try {

			boolean ret = false;
			Connection cn = db.getConnection();

			// count in the formative action the enrollments with status received
			PreparedStatement ps = cn
					.prepareStatement("select count(*) as profs from FormativeAction fa "
							+ "inner join Enrollment e on fa.ID_fa= e.ID_fa "
							+ "where fa.nameFa = ? and e.status = 'RECEIVED'");
			ps.setString(1, fa);

			ResultSet rs = ps.executeQuery();

			// if there is at least one enrollment with status received set return value to true
			if (rs.getInt("profs") > 0) {
				ret = true;
			}
			// otherwise set return value to false
			else {
				ret = false;
			}

			// close everything
			rs.close();
			ps.close();
			cn.close();

			// return value
			return ret;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Function to get the amount of movements to and from Teachers
	 * 
	 * @param nameFa: Name of Formative Action
	 * @return: amount of movements to and from teacher
	 */
	public int getPaymentTeacherConfirmed(String nameFa) {
		try {
			int ret;
			Connection cn = db.getConnection();

			// get the sum of movements to the teacher 
			PreparedStatement ps = cn.prepareStatement("select sum(p.amount) as confirmed from FormativeAction fa \n"
					+ "inner join InvoiceTeacher i ON fa.ID_fa=i.ID_fa \n"
					+ "inner join PaymentTeacher p ON p.ID_invoice=i.ID_invoice \n"
					+ "where fa.nameFa = ? and p.confirmed = 1;");
			ps.setString(1, nameFa);

			ResultSet rs = ps.executeQuery();
			ret = rs.getInt("confirmed");

			// close everything
			rs.close();
			ps.close();
			cn.close();

			// return sum
			return ret;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Function to sum the expected payments to the Teacher
	 * 
	 * @param nameFa: Name of the formative Action
	 * @return: sum of expected movements
	 */
	public int getPaymentTeacherExpected(String nameFa) {
		try {
			int ret;
			Connection cn = db.getConnection();

			// sum the remunerations for the teachers for the formative action
			PreparedStatement ps = cn
					.prepareStatement("select sum(s.remuneration) as remuneration from TeacherTeaches s \n"
							+ "inner join FormativeAction fa on s.ID_fa=fa.ID_fa\n" + "where fa.nameFa = ?;");
			ps.setString(1, nameFa);

			ResultSet rs = ps.executeQuery();
			ret = rs.getInt("remuneration");
			rs.close();
			ps.close();
			cn.close();

			// return sum
			return ret;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Function to get Date of the last session to check if all sessions are celebrated
	 * 
	 * @param nameFa Name of formative action
	 * @return date of last session
	 */
	public Date getDateOfLastSession(String nameFa) {
		try {
			Date ret;
			Connection cn = db.getConnection();

			// get last session of a formative action
			PreparedStatement ps = cn.prepareStatement("select s.sessionStart as StartDate from Session s \n"
					+ "inner join FormativeAction fa on s.ID_fa = fa.ID_fa \n" + "where fa.nameFa = ? \n"
					+ "order by s.sessionStart DESC limit 1;");
			ps.setString(1, nameFa);

			ResultSet rs = ps.executeQuery();
			// save date
			ret = rs.getDate(1);
			
			// close everything
			rs.close();
			ps.close();
			cn.close();
			
			// return saved date
			return ret;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Function to set status of formative action to EXECUTED
	 * @param nameFa: Name of formative action
	 */
	public void closeFormativeAction(String nameFa) {
		try {
			Connection cn = db.getConnection();

			// set status to executed for given formative action
			PreparedStatement ps = cn
					.prepareStatement("update FormativeAction set status = 'EXECUTED' where nameFa = ?; ");
			ps.setString(1, nameFa);

			ps.executeUpdate();
			ps.close();
			cn.close();

		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}

}
