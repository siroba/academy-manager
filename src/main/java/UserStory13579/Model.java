package UserStory13579;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Utils.ApplicationException;
import Entities.FormativeAction;
import Entities.Professional;
import PL53.SI2020_PL53.FinancialBalance;
import PL53.SI2020_PL53.TotalBalance;
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
		Connection cn=DriverManager.getConnection("jdbc:sqlite:DemoDB.db"); //NOSONAR
		Statement stmt=cn.createStatement(); //NOSONAR
		StringBuilder query = new StringBuilder();
		// Query misses: fA with no enrollments and year check
		// select fA.day, fA.name, count(p.amount)*fA.fee as total_income_confirmed, fA.remuneration as total_expenses_confirmed, ( count(p.amount)*fA.fee-fA.remuneration)as balance_confirmed, (count(CASE WHEN p.amount IS NULL THEN 1 END)+count(p.amount))*fA.fee as total_income_estimated, fA.remuneration as total_expenses_estimated, ((count(CASE WHEN p.amount IS NULL THEN 1 END)+count(p.amount))*fA.fee -fA.remuneration) as total_balance_estimated from Enrollment e left join Payment p on e.ID_enrollment=p.ID_enrollment left join FormativeAction fA on fA.ID_formativeAction=e.ID_fa group by fa.ID_formativeAction;
		query.append("SELECT fA.day as date, fA.name as name, fA.day as status, ");
		query.append("count(p.amount)*fA.fee as incomeConfirmed, ");
		query.append("fA.remuneration as expensesConfirmed, ");
		query.append("( count(p.amount)*fA.fee-fA.remuneration)as balanceConfirmed,  ");
		query.append("count(e.ID_enrollment)*fA.fee as incomeEstimated, ");
		query.append("fA.remuneration as expensesEstimated, ");
		query.append("(count(e.ID_enrollment)*fA.fee -fA.remuneration) as balanceEstimated  ");
		query.append("from Enrollment e  ");
		query.append("left join Payment p on e.ID_enrollment=p.ID_enrollment  ");
		query.append("left join FormativeAction fA on fA.ID_formativeAction=e.ID_fa ");
		query.append("group by fa.ID_formativeAction ");
		query.append("UNION ");
		query.append("SELECT fA.day as date, fA.name as name, fA.day as status, ");
		query.append("count(p.amount)*fA.fee as incomeConfirmed, ");
		query.append("fA.remuneration as expensesConfirmed, ");
		query.append("( count(p.amount)*fA.fee-fA.remuneration)as balanceConfirmed,  ");
		query.append("count(e.ID_enrollment)*fA.fee as incomeEstimated, ");
		query.append("fA.remuneration as expensesEstimated, ");
		query.append("(count(e.ID_enrollment)*fA.fee -fA.remuneration) as balanceEstimated  ");
		query.append("from FormativeAction fA ");
		query.append("left join enrollment e on fA.ID_formativeAction=e.ID_fa  ");
		query.append("left join payment p on e.ID_enrollment=p.ID_enrollment ");
		query.append("group by fa.ID_formativeAction ");
		ResultSet rs=stmt.executeQuery(query.toString());
		
		List<FinancialBalance> financialBalances = new ArrayList<>();
		
		while(rs.next()) {
			FinancialBalance fB = new FinancialBalance(
					rs.getString("date"),
					rs.getString("name"),
					"????",
					rs.getInt("incomeConfirmed"),
					rs.getInt("expensesConfirmed"),
					rs.getInt("balanceConfirmed"),
					rs.getInt("incomeEstimated"),
					rs.getInt("expensesEstimated"),
					rs.getInt("balanceEstimated"));
			
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
		Connection cn=DriverManager.getConnection("jdbc:sqlite:DemoDB.db"); //NOSONAR
		Statement stmt=cn.createStatement(); //NOSONAR
		StringBuilder query = new StringBuilder();
		// Query misses: fA with no enrollments and year check
		// select fA.day, fA.name, count(p.amount)*fA.fee as total_income_confirmed, fA.remuneration as total_expenses_confirmed, ( count(p.amount)*fA.fee-fA.remuneration)as balance_confirmed, (count(CASE WHEN p.amount IS NULL THEN 1 END)+count(p.amount))*fA.fee as total_income_estimated, fA.remuneration as total_expenses_estimated, ((count(CASE WHEN p.amount IS NULL THEN 1 END)+count(p.amount))*fA.fee -fA.remuneration) as total_balance_estimated from Enrollment e left join Payment p on e.ID_enrollment=p.ID_enrollment left join FormativeAction fA on fA.ID_formativeAction=e.ID_fa group by fa.ID_formativeAction;
		query.append("SELECT sum(totalIncomeConfirmed) as totalIncomeConfirmed, sum(totalExpensesConfirmed) as totalExpensesConfirmed,  ");
		query.append("sum(totalIncomeEstimated) as totalIncomeEstimated, sum(totalExpensesEstimated) as totalExpensesEstimated ");
		query.append("FROM ");
		query.append("(select count(p.amount)*fA.fee as totalIncomeConfirmed, fA.remuneration as totalExpensesConfirmed,   ");
		query.append("count(e.ID_enrollment)*fA.fee as totalIncomeEstimated, fA.remuneration as totalExpensesEstimated ");
		query.append("from Enrollment e  ");
		query.append("left join Payment p on e.ID_enrollment=p.ID_enrollment ");
		query.append("left join FormativeAction fA on fA.ID_formativeAction=e.ID_fa ");
		query.append("group by fa.ID_formativeAction ");
		query.append("UNION ");
		query.append("select count(p.amount)*fA.fee as totalIncomeConfirmed, fA.remuneration as totalExpensesConfirmed,   ");
		query.append("count(e.ID_enrollment)*fA.fee as totalIncomeEstimated, fA.remuneration as totalExpensesEstimated  ");
		query.append("from FormativeAction fA ");
		query.append("left join Enrollment e on  fA.ID_formativeAction=e.ID_fa ");
		query.append("left join Payment p on e.ID_enrollment=p.ID_enrollment ");
		query.append(" group by fa.ID_formativeAction); ");
		ResultSet rs=stmt.executeQuery(query.toString());
		
		TotalBalance tB = new TotalBalance(
				rs.getInt("totalIncomeConfirmed"),
				rs.getInt("totalExpensesConfirmed"),
				rs.getInt("totalIncomeEstimated"),
				rs.getInt("totalExpensesEstimated"));
		
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
