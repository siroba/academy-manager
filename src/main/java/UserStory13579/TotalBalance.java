package UserStory13579;

public class TotalBalance {

	private float totalIncomeConfirmed;
	private float totalExpensesConfirmed;
	private float totalIncomeEstimated;
	private float totalExpensesEstimated;
	
	public TotalBalance(float incomeConfirmed, float expensesConfirmed, float incomeEstimated, float expensesEstimated) {
	
		this.totalIncomeConfirmed = incomeConfirmed;
		this.totalExpensesConfirmed = expensesConfirmed;
		this.totalIncomeEstimated = incomeEstimated;
		this.totalExpensesEstimated = expensesEstimated;

	}
	
	// Getter 
	public float getTotalIncomeConfirmed() { return this.totalIncomeConfirmed; }
	public float getTotalExpensesConfirmed() { return this.totalExpensesConfirmed; }
	public float getTotalIncomeEstimated() { return this.totalIncomeEstimated; }
	public float getTotalExpensesEstimated() { return this.totalExpensesEstimated; }

	// Setter 
	public void setTotalIncomeConfirmed(float value) {this.totalIncomeConfirmed=value; }
	public void setTotalExpensesConfirmed(float value) {this.totalExpensesConfirmed=value; }
	public void setTotalIncomeEstimated(float value) {this.totalIncomeEstimated=value; }
	public void setTotalExpensesEstimated(float value) {this.totalExpensesEstimated=value; }


}
