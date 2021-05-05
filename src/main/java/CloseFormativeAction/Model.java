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

	public List<String> getListFormativeAction() {
		try {
			Connection cn = db.getConnection();
			PreparedStatement ps = cn
					.prepareStatement("SELECT nameFa FROM FormativeAction where lower(status) = 'active' OR lower(status) = 'delayed';");
			ResultSet rs = ps.executeQuery();

			List<String> formativeActionList = new ArrayList<String>();

			while (rs.next()) {
				formativeActionList.add(rs.getString("nameFa"));
			}

			rs.close();
			ps.close();
			cn.close();

			return formativeActionList;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}

	public int getSumIncomeConfirmedProfessional(String nameFa) {
		try {
			int ret;
			Connection cn = db.getConnection();

			PreparedStatement ps = cn
					.prepareStatement("select distinct(T.ID_fa), T.nameFa, T.status, T.income_confirmed from "
							+ "(select fA.ID_fa, fA.nameFa, fA.status, (ifnull(sum(CASE WHEN i.receiver='COIIPA' THEN p.amount END), 0) - ifnull(sum(CASE WHEN i.sender='COIIPA' THEN p.amount END), 0)) as income_confirmed "
							+ "from FormativeAction fA left join Enrollment e on fA.ID_fa=e.ID_fa left join Invoice i on e.ID_professional=i.ID_professional AND e.ID_fa=i.ID_fa left join Payment p on p.ID_invoice=i.ID_invoice group by fA.ID_fa) T "
							+ "left join Session s on s.ID_fa=T.ID_fa " + "where T.nameFa = ?");
			ps.setString(1, nameFa);

			ResultSet rs = ps.executeQuery();
			ret = rs.getInt("income_confirmed");
			rs.close();
			ps.close();
			cn.close();
			return ret;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}

	public int getSumIncomeExpectedProfessional(String nameFa) {
		try {

			int ret;
			Connection cn = db.getConnection();

			PreparedStatement ps = cn
					.prepareStatement("select distinct(T.ID_fa), T.nameFa, T.status, T.income_estimated from "
							+ "(select fA.ID_fa, fA.nameFa, fA.status,  (ifnull(sum(CASE WHEN i.receiver='COIIPA' THEN i.amount END), 0) - ifnull(sum(CASE WHEN i.sender='COIIPA' THEN i.amount END), 0)) as income_estimated "
							+ "from FormativeAction fA left join Enrollment e on fA.ID_fa=e.ID_fa left join Invoice i on e.ID_professional=i.ID_professional AND e.ID_fa=i.ID_fa group by fA.ID_fa) T "
							+ "left join Session s on s.ID_fa=T.ID_fa " + "where T.nameFa = ?");
			ps.setString(1, nameFa);

			ResultSet rs = ps.executeQuery();

			ret = rs.getInt("income_estimated");

			rs.close();
			ps.close();
			cn.close();

			return ret;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}

	public boolean getProfessionalsWithStatusReceived(String fa) {

		try {

			boolean ret = false;
			Connection cn = db.getConnection();

			PreparedStatement ps = cn
					.prepareStatement("select count(*) as profs from FormativeAction fa "
							+ "inner join Enrollment e on fa.ID_fa= e.ID_fa "
							+ "where fa.nameFa = ? and e.status = 'RECEIVED'");
			ps.setString(1, fa);

			ResultSet rs = ps.executeQuery();

			if (rs.getInt("profs") > 0) {
				ret = true;
			}
			else {
				ret = false;
			}

			rs.close();
			ps.close();
			cn.close();

			return ret;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}

	public int getPaymentTeacherConfirmed(String nameFa) {
		try {
			int ret;
			Connection cn = db.getConnection();

			PreparedStatement ps = cn.prepareStatement("select sum(p.amount) as confirmed from FormativeAction fa \n"
					+ "inner join InvoiceTeacher i ON fa.ID_fa=i.ID_fa \n"
					+ "inner join PaymentTeacher p ON p.ID_invoice=i.ID_invoice \n"
					+ "where fa.nameFa = ? and p.confirmed = 1;");
			ps.setString(1, nameFa);

			ResultSet rs = ps.executeQuery();
			ret = rs.getInt("confirmed");

			rs.close();
			ps.close();
			cn.close();

			return ret;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}

	public int getPaymentTeacherExpected(String nameFa) {
		try {
			int ret;
			Connection cn = db.getConnection();

			PreparedStatement ps = cn
					.prepareStatement("select sum(s.remuneration) as remuneration from TeacherTeaches s \n"
							+ "inner join FormativeAction fa on s.ID_fa=fa.ID_fa\n" + "where fa.nameFa = ?;");
			ps.setString(1, nameFa);

			ResultSet rs = ps.executeQuery();
			ret = rs.getInt("remuneration");
			rs.close();
			ps.close();
			cn.close();

			return ret;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}

	public Date getDateOfLastSession(String nameFa) {
		try {
			Date ret;
			Connection cn = db.getConnection();

			PreparedStatement ps = cn.prepareStatement("select s.sessionStart as StartDate from Session s \n"
					+ "inner join FormativeAction fa on s.ID_fa = fa.ID_fa \n" + "where fa.nameFa = ? \n"
					+ "order by s.sessionStart DESC limit 1;");
			ps.setString(1, nameFa);

			ResultSet rs = ps.executeQuery();
			ret = rs.getDate(1);
			rs.close();
			ps.close();
			return ret;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}

	public void closeFormativeAction(String nameFa) {
		try {
			Connection cn = db.getConnection();

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
