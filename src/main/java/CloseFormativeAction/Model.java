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
			PreparedStatement ps = cn.prepareStatement("SELECT nameFa FROM FormativeAction where lower(status) = 'active';");
			ResultSet rs=ps.executeQuery();
			
			List<String> formativeActionList = new ArrayList<String>();
			
			while (rs.next()) {
				formativeActionList.add(rs.getString("nameFa"));
			}
			
			rs.close();
			ps.close();
			cn.close();
			
			return formativeActionList;
		}
		catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}
	
	public int getSumIncomeConfirmedProfessional(String nameFa) {
		try {
			int ret;
			Connection cn = db.getConnection();
			
			PreparedStatement ps = cn.prepareStatement("select sum(amount) as confirmed \n"
					+ "from FormativeAction fa \n"
					+ "inner join Enrollment e on fa.ID_fa=e.ID_fa \n"
					+ "inner join Invoice i on i.ID_fa=e.ID_fa AND i.ID_professional=e.ID_professional \n"
					+ "inner join Payment p on i.ID_invoice=p.ID_invoice \n"
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
	
	public int getSumIncomeExpectedProfessional(String nameFa) {
		try {
			
			int ret;
			Connection cn = db.getConnection();
			
			PreparedStatement ps = cn.prepareStatement("select sum(amount) as expected \n"
					+ "from Fee \n"
					+ "inner join FormativeAction fa \n"
					+ "on Fee.ID_fa = fa.ID_fa \n"
					+ "INNER JOIN Enrollment e \n"
					+ "on  e.ID_fa = fa.ID_fa \n"
					+ "WHERE fa.nameFa = ?;");
			ps.setString(1, nameFa);
			
			ResultSet rs = ps.executeQuery();
			
			ret =  rs.getInt("expected");
			
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
			
			PreparedStatement ps = cn.prepareStatement("select sum(amount) as confirmed from FormativeAction fa \n"
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
			
			PreparedStatement ps = cn.prepareStatement("select sum(s.remuneration) as remuneration from Session s \n"
					+ "inner join FormativeAction fa on s.ID_fa=fa.ID_fa\n"
					+ "where fa.nameFa = ?;");
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
					+ "inner join FormativeAction fa on s.ID_fa = fa.ID_fa \n"
					+ "where fa.nameFa = ? \n"
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
			
			PreparedStatement ps = cn.prepareStatement("update FormativeAction set status = 'EXECUTED' where nameFa = ?; ");
			ps.setString(1, nameFa);
			
			ps.executeUpdate();
			ps.close();
			cn.close();
			
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}

}
