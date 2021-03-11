package UserStory13579;

import PL53.SI2020_PL53.DateTime;

public class FinancialBalance {
	private DateTime date;
	private String name;
	private String status;
	private float incomeConfirmed;
	private float expensesConfirmed;
	private float balanceConfirmed;
	private float incomeEstimated;
	private float expensesEstimated;
	private float balanceEstimated;
	
	public FinancialBalance(DateTime date, String name, String status, float incomeConfirmed, float expensesConfirmed, float balanceConfirmed,
							float incomeEstimated, float expensesEstimated, float balanceEstimated) {
	
		this.date = date;
		this.name = name;
		this.status = status;
		this.incomeConfirmed = incomeConfirmed;
		this.expensesConfirmed = expensesConfirmed;
		this.balanceConfirmed = balanceConfirmed;
		this.incomeEstimated = incomeEstimated;
		this.expensesEstimated = expensesEstimated;
		this.balanceEstimated = balanceEstimated;
	}
	
	// Getter
	public DateTime getDate() { return this.date; } 
	public String getName() { return this.name; } 
	public String getStatus() { return this.status; } 
	public float getIncomeConfirmed() { return this.incomeConfirmed; }
	public float getExpensesConfirmed() { return this.expensesConfirmed; }
	public float getBalanceConfirmed() { return this.balanceConfirmed; }
	public float getIncomeEstimated() { return this.incomeEstimated; }
	public float getExpensesEstimated() { return this.expensesEstimated; }
	public float getBalanceEstimated() { return this.balanceEstimated; }

	// Setter 
	public void setDate(DateTime value) { this.date=value; }
	public void setName(String value) { this.name=value; }
	public void setStatus(String value) { this.status=value; }
	public void setIncomeConfirmed(float value) {this.incomeConfirmed=value; }
	public void setExpensesConfirmed(float value) {this.expensesConfirmed=value; }
	public void setBalanceConfirmed(float value) {this.balanceConfirmed=value; }
	public void setIncomeEstimated(float value) {this.incomeEstimated=value; }
	public void setExpensesEstimated(float value) {this.expensesEstimated=value; }
	public void setbalanceEstimated(float value) {this.balanceEstimated=value; }

}
