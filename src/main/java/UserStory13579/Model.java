package UserStory13579;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import PL53.SI2020_PL53.DateTime;
import Utils.ApplicationException;
import Utils.Database;
import Utils.UnexpectedException;


public class Model {

	private Database db=new Database();
	
	/**
	 * Gets the list of active races in object form for a given registration date.
	 */
	public List<FinancialBalance> getListFinancialBalanceNoFilter() {
		//Query all rows from the result of a SQL query
		try {
		Connection cn=DriverManager.getConnection("jdbc:sqlite:database.db"); //NOSONAR
		Statement stmt=cn.createStatement(); //NOSONAR
		StringBuilder query = new StringBuilder();
		// Query misses: fA with no enrollments and year check
		// select fA.dateFA, fa.nameFa, fa.status, ifnull(sum(CASE WHEN p.confirmed=true THEN p.amount END), 0) as income_confirmed, ifnull(sum(CASE WHEN p.confirmed=true AND p.ID_professional=NULL THEN p.amount END), 0) as expenses_confirmed , (ifnull(sum(CASE WHEN p.confirmed=true THEN p.amount END), 0) - ifnull(sum(CASE WHEN p.confirmed=true AND p.ID_professional=NULL THEN p.amount END),0))as balance_confirmed, (count(e.ID_fa))*fA.fee as income_estimated, fA.remuneration as expenses_estimated, ((count(e.ID_fa))*fA.fee - fA.remuneration)as balance_estimated from Enrollment e left join Payment p on e.ID_fa=p.ID_fa AND e.ID_professional=p.ID_professional left join FormativeAction fA on fA.ID_fa=e.ID_fa group by fA.ID_fa UNION  select fA.dateFA, fa.nameFa, fa.status, ifnull(sum(CASE WHEN p.confirmed=true THEN p.amount END),0) as income_confirmed, ifnull(sum(CASE WHEN p.confirmed=true AND p.ID_professional=NULL THEN p.amount END), 0) as expenses_confirmed , (ifnull(sum(CASE WHEN p.confirmed=true THEN p.amount END), 0) - ifnull(sum(CASE WHEN p.confirmed=true AND p.ID_professional=NULL THEN p.amount END),0))as balance_confirmed, (count(e.ID_fa))*fA.fee as income_estimated, fA.remuneration as expenses_estimated, ((count(e.ID_fa))*fA.fee - fA.remuneration)as balance_estimated from FormativeAction fA left join Enrollment e on fA.ID_fa=e.ID_fa left join Payment p on e.ID_professional=p.ID_professional AND e.ID_fa=p.ID_fa group by fA.ID_fa;
		query.append("select fA.dateFA, fa.nameFa, fa.status, ");
		query.append("ifnull(sum(CASE WHEN p.confirmed=true THEN p.amount END),0) as income_confirmed, ");
		query.append("ifnull(sum(CASE WHEN p.confirmed=true AND p.ID_professional=NULL THEN p.amount END),0) as expenses_confirmed, ");
		query.append("(ifnull(sum(CASE WHEN p.confirmed=true THEN p.amount END),0) - ifnull(sum(CASE WHEN p.confirmed=true AND p.ID_professional=NULL THEN p.amount END),0))as balance_confirmed, ");
		query.append("(count(e.ID_fa))*fA.fee as income_estimated, ");
		query.append("fA.remuneration as expenses_estimated, ");
		query.append("((count(e.ID_fa))*fA.fee - fA.remuneration)as balance_estimated ");
		query.append("from Enrollment e  ");
		query.append("left join Payment p on e.ID_fa=p.ID_fa AND e.ID_professional=p.ID_professional  ");
		query.append("left join FormativeAction fA on fA.ID_fa=e.ID_fa  ");
		query.append("group by fa.ID_fa ");
		query.append("UNION ");
		query.append("select fA.dateFA, fa.nameFa, fa.status, ");
		query.append("ifnull(sum(CASE WHEN p.confirmed=true THEN p.amount END),0) as income_confirmed, ");
		query.append("ifnull(sum(CASE WHEN p.confirmed=true AND p.ID_professional=NULL THEN p.amount END),0) as expenses_confirmed, ");
		query.append("(ifnull(sum(CASE WHEN p.confirmed=true THEN p.amount END),0) - ifnull(sum(CASE WHEN p.confirmed=true AND p.ID_professional=NULL THEN p.amount END),0))as balance_confirmed, ");
		query.append("(count(e.ID_fa))*fA.fee as income_estimated, ");
		query.append("fA.remuneration as expenses_estimated, ");
		query.append("((count(e.ID_fa))*fA.fee - fA.remuneration)as balance_estimated ");
		query.append("from FormativeAction fA ");
		query.append("left join Enrollment e on fA.ID_fa=e.ID_fa ");
		query.append("left join Payment p on e.ID_professional=p.ID_professional AND e.ID_fa=p.ID_fa ");
		query.append("group by fA.ID_fa;");
		ResultSet rs=stmt.executeQuery(query.toString());
		
		List<FinancialBalance> financialBalances = new ArrayList<>();
		
		while(rs.next()) {
			FinancialBalance fB = new FinancialBalance(
					DateTime.parse(rs.getTimestamp("dateFa")),
					rs.getString("nameFa"),
					rs.getString("status"),
					rs.getInt("income_confirmed"),
					rs.getInt("expenses_confirmed"),
					rs.getInt("balance_confirmed"),
					rs.getInt("income_estimated"),
					rs.getInt("expenses_estimated"),
					rs.getInt("balance_estimated"));
			
			financialBalances.add(fB);
		}
		
		rs.close();
		stmt.close();
		cn.close();
		return financialBalances;
		} catch (SQLException e) { 
			throw new UnexpectedException(e);
		}
	}
	
	
	

	public List<TotalBalance> getListTotalBalanceNoFilter() {
		//Query all rows from the result of a SQL query
		try {
		Connection cn=DriverManager.getConnection("jdbc:sqlite:database.db"); //NOSONAR
		Statement stmt=cn.createStatement(); //NOSONAR
		StringBuilder query = new StringBuilder();
		// Query misses: fA with no enrollments and year check
		// select sum(total_income_confirmed), sum(total_expenses_confirmed), sum(total_income_estimated), sum(total_expenses_estimated) from (select ifnull(sum(CASE WHEN p.confirmed=true THEN p.amount END), 0) as total_income_confirmed, ifnull(sum(CASE WHEN p.confirmed=true AND p.ID_professional=NULL THEN p.amount END), 0) as total_expenses_confirmed, (count(e.ID_fa))*fA.fee as total_income_estimated, fA.remuneration as total_expenses_estimated from FormativeAction fA left join Enrollment e on fA.ID_fa=e.ID_fa left join Payment p on e.ID_professional=p.ID_professional AND e.ID_fa=p.ID_fa group by fA.ID_fa);	
 
		query.append("select sum(total_income_confirmed) as total_income_confirmed, sum(total_expenses_confirmed) as total_expenses_confirmed, sum(total_income_estimated) as total_income_estimated, sum(total_expenses_estimated) as total_expenses_estimated ");
		query.append("FROM ");
		query.append("(select ifnull(sum(CASE WHEN p.confirmed=true THEN p.amount END), 0) as total_income_confirmed, ");
		query.append("ifnull(sum(CASE WHEN p.confirmed=true AND p.ID_professional=NULL THEN p.amount END), 0) as total_expenses_confirmed,  ");
		query.append("(count(e.ID_fa))*fA.fee as total_income_estimated, ");
		query.append(" fA.remuneration as total_expenses_estimated ");
		query.append("from FormativeAction fA left join Enrollment e on fA.ID_fa=e.ID_fa ");
		query.append("left join Payment p on e.ID_professional=p.ID_professional AND e.ID_fa=p.ID_fa ");
		query.append("group by fA.ID_fa); ");

		ResultSet rs=stmt.executeQuery(query.toString());
		
		TotalBalance tB = new TotalBalance(
				rs.getInt("total_income_confirmed"),
				rs.getInt("total_expenses_confirmed"),
				rs.getInt("total_income_estimated"),
				rs.getInt("total_expenses_estimated"));

		List<TotalBalance> totalBalance = new ArrayList<>();
		totalBalance.add(tB);
		
		rs.close();
		stmt.close();
		cn.close();
		return totalBalance;
		} catch (SQLException e) { 
			throw new UnexpectedException(e);
		}
	}
	
	private void validateCondition(boolean condition, String message) {
		if (!condition)
			throw new ApplicationException(message);
	}
	

}
