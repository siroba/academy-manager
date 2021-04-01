package UserStory13579;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entities.Session;
import PL53.util.DateTime;
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
		// Setup connection & statements 
		Connection cn=DriverManager.getConnection("jdbc:sqlite:database.db"); //NOSONAR
		Statement stmtIncomeConfirmed=cn.createStatement(); //NOSONAR
		Statement stmtExpensesConfirmed=cn.createStatement(); //NOSONAR
		Statement stmtIncomeEstimated=cn.createStatement(); //NOSONAR
		Statement stmtExpensesEstimated=cn.createStatement(); //NOSONAR
		
		// Income confirmed
		StringBuilder queryIncomeConfirmed = new StringBuilder();
		queryIncomeConfirmed.append("select fa.nameFa, fa.status, ifnull(sum(CASE WHEN p.confirmed=true THEN p.amount END), 0) as income_confirmed  ");
		queryIncomeConfirmed.append("from FormativeAction fA left join Enrollment e on fA.ID_fa=e.ID_fa left join invoice i on e.ID_professional=i.ID_professional AND e.ID_fa=i.ID_fa left join Payment p on p.ID_invoice=i.ID_invoice group by fa.ID_fa;");
		ResultSet rsIncomeConfirmed=stmtIncomeConfirmed.executeQuery(queryIncomeConfirmed.toString());
		
		// Expenses confirmed
		StringBuilder queryExpensesConfirmed = new StringBuilder();
		queryExpensesConfirmed.append("select fa.nameFa, fa.status, ifnull(sum(CASE WHEN pt.confirmed THEN pt.amount END), 0) as expenses_confirmed ");
		queryExpensesConfirmed.append("from FormativeAction fA left join InvoiceTeacher it on fA.ID_fa=it.ID_fa left join PaymentTeacher pt on pt.ID_invoice=it.ID_invoice group by fa.ID_fa;");
		ResultSet rsExpensesConfirmed=stmtExpensesConfirmed.executeQuery(queryExpensesConfirmed.toString());
		
		// Income estimated
		StringBuilder queryIncomeEstimated = new StringBuilder();
		queryIncomeEstimated.append("select  fa.nameFa, fa.status, ifnull(sum(CASE WHEN e.ID_fa THEN f.amount END), 0) as income_estimated ");
		queryIncomeEstimated.append("from FormativeAction fA left join Fee f on f.id_fa=fa.ID_fa left join enrollment e on fa.ID_fa=e.ID_fa and e.category=f.category group by fa.ID_fa;");
		ResultSet rsIncomeEstimated=stmtIncomeEstimated.executeQuery(queryIncomeEstimated.toString());
			
		// Expenses estimated
		StringBuilder queryExpensesEstimated = new StringBuilder();
		queryExpensesEstimated.append("select fa.nameFa, fa.status, sum(remuneration) as expenses_estimated  ");
		queryExpensesEstimated.append("from FormativeAction fa left join Session s on fa.ID_fa=s.ID_fa group by fa.ID_fa;");
		ResultSet rsExpensesEstimated=stmtExpensesEstimated.executeQuery(queryExpensesEstimated.toString());
		
		// Sessions 
		List<Session> sessions = Session.get("select * from session s", db);
		List<DateTime> ListFaFirstSessionLastSession = getListFirstSessionLastSession(sessions);
		
		// Create list of Financial Balance objects 
		List<FinancialBalance> financialBalances = new ArrayList<>();
		int counter = 0;
		while(rsIncomeConfirmed.next()) {
			rsExpensesConfirmed.next();
			rsIncomeEstimated.next();
			rsExpensesEstimated.next();
			FinancialBalance fB = new FinancialBalance( 
					rsIncomeConfirmed.getString("nameFa"),
					rsIncomeConfirmed.getString("status"),
					ListFaFirstSessionLastSession.get(counter),
					ListFaFirstSessionLastSession.get(counter+1),
					rsIncomeConfirmed.getInt("income_confirmed"),
					rsExpensesConfirmed.getInt("expenses_confirmed"),
					(rsIncomeConfirmed.getInt("income_confirmed") - rsExpensesConfirmed.getInt("expenses_confirmed")),
					rsIncomeEstimated.getInt("income_estimated"),
					rsExpensesEstimated.getInt("expenses_estimated"),
					(rsIncomeEstimated.getInt("income_estimated") - rsExpensesEstimated.getInt("expenses_estimated")));
			counter += 2; 
			financialBalances.add(fB);
		}
		
		// Close statement, result sets & connection
		rsIncomeEstimated.close();
		rsExpensesEstimated.close();
		rsIncomeConfirmed.close();
		rsExpensesConfirmed.close();
		stmtIncomeConfirmed.close();
		stmtExpensesConfirmed.close();
		stmtIncomeEstimated.close();
		stmtExpensesEstimated.close();
		cn.close();
		return financialBalances;
		} catch (SQLException e) { 
			throw new UnexpectedException(e);
		}
	}
	
	
	public List<TotalBalance> getListTotalBalanceNoFilter() {
		//Query all rows from the result of a SQL query
		try {
			
		// Set up connection & statements
		Connection cn=DriverManager.getConnection("jdbc:sqlite:database.db"); //NOSONAR
		Statement stmtTotalIncomeConfirmed=cn.createStatement(); //NOSONAR
		Statement stmtTotalExpensesConfirmed=cn.createStatement(); //NOSONAR
		Statement stmtTotalIncomeEstimated=cn.createStatement(); //NOSONAR
		Statement stmtTotalExpensesEstimated=cn.createStatement(); //NOSONAR
		
		// Total Income Confirmed 
		StringBuilder queryTotalIncomeConfirmed = new StringBuilder();
		queryTotalIncomeConfirmed.append("select sum(income_confirmed) as total_income_confirmed ");
		queryTotalIncomeConfirmed.append("FROM ");
		queryTotalIncomeConfirmed.append("(select fa.nameFa, fa.status, ifnull(sum(CASE WHEN p.confirmed=true THEN p.amount END), 0) as income_confirmed ");
		queryTotalIncomeConfirmed.append("from FormativeAction fA left join Enrollment e on fA.ID_fa=e.ID_fa left join invoice i on e.ID_professional=i.ID_professional AND e.ID_fa=i.ID_fa left join Payment p on p.ID_invoice=i.ID_invoice group by fa.ID_fa);");
		ResultSet rsTotalIncomeConfirmed=stmtTotalIncomeConfirmed.executeQuery(queryTotalIncomeConfirmed.toString());
		
		// Total Expenses Confirmed 
		StringBuilder queryTotalExpensesConfirmed = new StringBuilder();
		queryTotalExpensesConfirmed.append("select sum(expenses_confirmed) as total_expenses_confirmed ");
		queryTotalExpensesConfirmed.append("FROM ");
		queryTotalExpensesConfirmed.append("(select fa.nameFa, fa.status, ifnull(sum(CASE WHEN pt.confirmed THEN pt.amount END), 0) as expenses_confirmed ");
		queryTotalExpensesConfirmed.append("from FormativeAction fA left join InvoiceTeacher it on fA.ID_fa=it.ID_fa left join PaymentTeacher pt on pt.ID_invoice=it.ID_invoice group by fa.ID_fa);");
		ResultSet rsTotalExpensesConfirmed=stmtTotalExpensesConfirmed.executeQuery(queryTotalExpensesConfirmed.toString());
		
		// Total Income Estimated
		StringBuilder queryTotalIncomeEstimated = new StringBuilder();
		queryTotalIncomeEstimated.append("select sum(income_estimated) as total_income_estimated ");
		queryTotalIncomeEstimated.append("FROM ");
		queryTotalIncomeEstimated.append("(select  fa.nameFa, fa.status, ifnull(sum(CASE WHEN e.ID_fa THEN f.amount END), 0) as income_estimated ");
		queryTotalIncomeEstimated.append("from FormativeAction fA left join Fee f on f.id_fa=fa.ID_fa left join enrollment e on fa.ID_fa=e.ID_fa and e.category=f.category group by fa.ID_fa);");
		ResultSet rsTotalIncomeEstimated=stmtTotalIncomeEstimated.executeQuery(queryTotalIncomeEstimated.toString());
		
		// Total Expenses Estimated
		StringBuilder queryTotalExpensesEstimated = new StringBuilder();
		queryTotalExpensesEstimated.append("select sum(expenses_estimated) as total_expenses_estimated ");
		queryTotalExpensesEstimated.append("FROM ");
		queryTotalExpensesEstimated.append("(select fa.nameFa, fa.status, sum(remuneration) as expenses_estimated  ");
		queryTotalExpensesEstimated.append("from FormativeAction fa left join Session s on fa.ID_fa=s.ID_fa group by fa.ID_fa);");
		ResultSet rsTotalExpensesEstimated=stmtTotalExpensesEstimated.executeQuery(queryTotalExpensesEstimated.toString());
		
		// Create Total Balance object 
		TotalBalance tB = new TotalBalance(
				rsTotalIncomeConfirmed.getInt("total_income_confirmed"),
				rsTotalExpensesConfirmed.getInt("total_expenses_confirmed"),
				rsTotalIncomeEstimated.getInt("total_income_estimated"),
				rsTotalExpensesEstimated.getInt("total_expenses_estimated"));

		List<TotalBalance> totalBalance = new ArrayList<>();
		totalBalance.add(tB);

		// Close statements, result sets & connection
		rsTotalIncomeConfirmed.close();
		stmtTotalIncomeConfirmed.close();
		rsTotalExpensesConfirmed.close();
		stmtTotalExpensesConfirmed.close();
		rsTotalIncomeEstimated.close();
		stmtTotalIncomeEstimated.close();
		rsTotalExpensesEstimated.close();
		stmtTotalExpensesEstimated.close();
		cn.close();
		return totalBalance;
		} catch (SQLException e) { 
			throw new UnexpectedException(e);
		}
	}
	
	/**
	 * Returns a list containing the DateTime of the first and last session celebrated for all the formative action  
	 */
	private List<DateTime> getListFirstSessionLastSession(List<Session> sessions) {
		List<DateTime> FirstSessionLastSession = new ArrayList<DateTime>(); 
		int idFaLast = sessions.get(0).getID_fa();
		DateTime DateTimeFaLast = sessions.get(0).getSessionStart();
		FirstSessionLastSession.add(DateTimeFaLast);
		for(int i=1; i<sessions.size(); i++){
			if (idFaLast != sessions.get(i).getID_fa()) {
				FirstSessionLastSession.add(DateTimeFaLast);
				FirstSessionLastSession.add(sessions.get(i).getSessionStart());
			}
			idFaLast = sessions.get(i).getID_fa();
			DateTimeFaLast = sessions.get(i).getSessionStart();
		}
		FirstSessionLastSession.add(sessions.get(sessions.size()-1).getSessionStart());
		return FirstSessionLastSession;
	}		
}
