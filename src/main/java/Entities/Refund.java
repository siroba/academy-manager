package Entities;

public class Refund {
/*
 * ID_refund INTEGER PRIMARY KEY,
ID_payment INTEGER NOT NULL,
porcentageRefund REAL NOT NULL,*/

	private int ID = -1;
	private Payment payment;

	public Refund(int ID, Payment payment) {
		this.ID = ID;
		this.payment = payment;
	}

	public Refund(Payment payment) {
		this.payment = payment;
	}

	public float percentage() {
		/*
		 * If a registered person wishes to make a cancellation 7 calendar days or more before the course, 100% of the amount paid will be refunded.
			If he resigns with between 3 calendar days and 6 calendar days missing, 50% of the amount of the course will be returned.
			If he resigns with less than 3 calendar days left, the amount of the course will not be refunded.
		 * */
		//Date.daysSince(this.payment.getEnrollment().getFormativeAction().getEnrollmentEnd(), this.payment.getPayDate());
		int days = 1; //TODO: Get days between the request and the end of the enrollment period

		if(days > 7) return 1f;
		else if (days <= 6 && days >=3) return 0.5f;
		else return 0f;
	}

	public float totalRefund() {
		return payment.getAmount()*this.percentage();
	}

	public int getID() {
		return ID;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}


}