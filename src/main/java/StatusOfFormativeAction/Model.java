package StatusOfFormativeAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Entities.FormativeAction;
import PL53.util.Date;
import Utils.Database;
import Utils.UnexpectedException;

public class Model {

	private Database db;

	public Model() {
		db = new Database();
	}
	
	/*
	 * TODO Description of Function, parameter and return value
	 */
	public List<FormativeActionDetails> getListFormativeAction() {
		try {
			Connection cn = db.getConnection();
			PreparedStatement ps = cn.prepareStatement(
					"SELECT  fa.nameFa, fa.status, fa.enrollmentStart, fa.enrollmentEnd, fa.totalPlaces, (fa.totalPlaces - count(e.ID_fa)) as leftPlaces "
							+ "FROM FormativeAction fa LEFT JOIN Enrollment e on e.ID_fa=fa.ID_fa "
							+ "GROUP BY fa.ID_fa;");
			ResultSet rs = ps.executeQuery();

			List<FormativeActionDetails> formativeActionList = new ArrayList<FormativeActionDetails>();

			while (rs.next()) {
				formativeActionList.add(new FormativeActionDetails(rs.getString("nameFa"), rs.getString("status"),
						Date.parse(rs.getTimestamp("enrollmentStart")), Date.parse(rs.getTimestamp("enrollmentEnd")),
						rs.getInt("totalPlaces"), rs.getInt("leftPlaces")));
			}

			rs.close();
			ps.close();
			cn.close();

			return formativeActionList;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}

	/*
	 * TODO Description of Function, parameter and return value
	 */
	public List<Registration> getRegistrationList(String selectedFormativeAction) {
		try {
			Connection cn = db.getConnection();
			StringBuilder query = new StringBuilder();
			query.append("SELECT p.name, p.surname, e.timeEn, Fee.amount, e.status ");
			query.append("FROM Professional p ");
			query.append("INNER JOIN Enrollment e ");
			query.append("ON p.ID_professional = e.ID_professional ");
			query.append("INNER JOIN FormativeAction fa ");
			query.append("ON e.ID_fa = fa.ID_fa ");
			query.append("INNER JOIN Fee ");
			query.append("ON fa.ID_fa = Fee.ID_fa and Fee.category=e.category ");
			query.append("WHERE lower(fa.nameFa) = lower(?);");

			PreparedStatement ps = cn.prepareStatement(query.toString());
			ps.setString(1, selectedFormativeAction);

			ResultSet rs = ps.executeQuery();

			List<Registration> registrationLists = new ArrayList<>();

			while (rs.next()) {
				Registration list = new Registration(rs.getString("name"), rs.getString("surname"),
						Date.parse(rs.getTimestamp("timeEn")), rs.getFloat("amount"), rs.getString("status"));
				registrationLists.add(list);
			}

			return registrationLists;

		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/*
	 * TODO Description of Function, parameter and return value
	 */
	public FormativeAction getFormativeAction(String name) {
		String query = "SELECT * from FormativeAction fa WHERE fa.nameFa = '" + name + "';";
		try {
			return FormativeAction.getOne(query, db);
		} catch (SQLException | ParseException e) {
			throw new UnexpectedException(e);
		}
	}
	
	/*
	 * TODO Description of Function, parameter and return value
	 */
	public List<Payment> getPayments(String formativeAction, String professional) {
		try {
			Connection cn = db.getConnection();
			
			PreparedStatement ps = cn.prepareStatement(
					"select fa.nameFa, i.sender, i.receiver, pay.amount, pay.datePay from Professional p "
							+ "inner join Enrollment e on p.ID_professional = e.ID_professional "
							+ "inner join FormativeAction fa on e.ID_fa = fa.ID_fa "
							+ "inner join Invoice i on e.ID_professional = i.ID_professional and e.ID_fa = i.ID_fa "
							+ "inner join Payment pay on i.ID_invoice = pay.ID_invoice "
							+ "where lower(p.name) = ? and lower(fa.nameFa) = ?;");
			ps.setString(1, professional.toLowerCase()); 
			ps.setString(2, formativeAction.toLowerCase()); 

			ResultSet rs = ps.executeQuery();

			List<Payment> listPayments = new ArrayList<>();

			while (rs.next()) {
				listPayments.add(new Payment(Date.parse(rs.getTimestamp("datePay")),
						rs.getString("sender").equals("COIIPA") ? -rs.getInt("amount") : rs.getInt("amount")));
			}
			
			return listPayments;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}
}