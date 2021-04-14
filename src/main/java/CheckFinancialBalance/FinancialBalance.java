package CheckFinancialBalance;

import PL53.util.DateTime;

public class FinancialBalance {
	private String name;
	private String status;
	private DateTime firstSession;
	private DateTime lastSession;
	private float incomeConfirmed;
	private float expensesConfirmed;
	private float balanceConfirmed;
	private float incomeEstimated;
	private float expensesEstimated;
	private float balanceEstimated;
	
	public FinancialBalance(String name, String status, DateTime firstSession, DateTime lastSession, float incomeConfirmed, float expensesConfirmed, float balanceConfirmed,
							float incomeEstimated, float expensesEstimated, float balanceEstimated) {
	
		this.name = name;
		this.status = status;
		this.firstSession = firstSession;
		this.lastSession = lastSession; 
		this.incomeConfirmed = incomeConfirmed;
		this.expensesConfirmed = expensesConfirmed;
		this.balanceConfirmed = balanceConfirmed;
		this.incomeEstimated = incomeEstimated;
		this.expensesEstimated = expensesEstimated;
		this.balanceEstimated = balanceEstimated;
	}
	
	// Getter
	public String getName() { return this.name; } 
	public String getStatus() { return this.status; } 
	public DateTime getFirstSession() { return this.firstSession; }
	public DateTime getLastSession() { return this.lastSession; }
	public float getIncomeConfirmed() { return this.incomeConfirmed; }
	public float getExpensesConfirmed() { return this.expensesConfirmed; }
	public float getBalanceConfirmed() { return this.balanceConfirmed; }
	public float getIncomeEstimated() { return this.incomeEstimated; }
	public float getExpensesEstimated() { return this.expensesEstimated; }
	public float getBalanceEstimated() { return this.balanceEstimated; }

	// Setter 
	public void setName(String value) { this.name=value; }
	public void setStatus(String value) { this.status=value; }
	public void setFirstSession(DateTime value) { this.firstSession=value; }
	public void setLastSession(DateTime value) { this.lastSession=value; }
	public void setIncomeConfirmed(float value) {this.incomeConfirmed=value; }
	public void setExpensesConfirmed(float value) {this.expensesConfirmed=value; }
	public void setBalanceConfirmed(float value) {this.balanceConfirmed=value; }
	public void setIncomeEstimated(float value) {this.incomeEstimated=value; }
	public void setExpensesEstimated(float value) {this.expensesEstimated=value; }
	public void setbalanceEstimated(float value) {this.balanceEstimated=value; }

}
