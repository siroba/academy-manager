package StatusOfFormativeAction;

import PL53.util.Date;

public class Payment {
	private Date date;
	private float amount;
	
	public Payment(Date date, float amount) {
		super();
		this.date = date;
		this.amount = amount;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}
