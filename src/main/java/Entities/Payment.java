package Entities;

import PL53.SI2020_PL53.Date;

public class Payment {
	private int ID = -1;
	private float amount;
	private Date payDate;
	private String sender, receiver, fiscalNumber, address;
	private boolean paid;
	
	/**
	 * This reference is needed for the {@link Entities.Refund#percentage() refund_percentage} function.
	 */
	private Enrollment enrollment;

	public Payment(Enrollment enrollment, float amount, Date payDate, String sender, String receiver, String fiscalNumber, String address,
			boolean paid) {
		this.enrollment = enrollment;
		this.amount = amount;
		this.payDate = payDate;
		this.sender = sender;
		this.receiver = receiver;
		this.fiscalNumber = fiscalNumber;
		this.address = address;
		this.paid = paid;
	}

	public Payment(int ID, Enrollment enrollment, float amount, Date payDate, String sender, String receiver, String fiscalNumber,
			String address, boolean paid) {
		this.ID = ID;
		this.enrollment = enrollment;
		this.amount = amount;
		this.payDate = payDate;
		this.sender = sender;
		this.receiver = receiver;
		this.fiscalNumber = fiscalNumber;
		this.address = address;
		this.paid = paid;
	}

	public int getID() {
		return ID;
	}
	
	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getFiscalNumber() {
		return fiscalNumber;
	}

	public void setFiscalNumber(String fiscalNumber) {
		this.fiscalNumber = fiscalNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public Enrollment getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}
}
