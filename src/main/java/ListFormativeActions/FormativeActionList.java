package ListFormativeActions;

import PL53.util.Date;

public class FormativeActionList {
	private String nameOfAction;
	private String status;
	private Date enrollmentPeriodStart;
	private Date enrollmentPeriodEnd;
	private int totalPlaces;
	private int leftPlaces;
	private Date date;
	private int sessionNumber;

	public FormativeActionList(String nameOfAction, String status, Date enrollmentPeriodStart, Date enrollmentPeriodEnd,
			int totalPlaces, int leftPlaces, Date date, int sessionNumber) {
		this.nameOfAction = nameOfAction;
		this.status = status;
		this.enrollmentPeriodStart = enrollmentPeriodStart;
		this.enrollmentPeriodEnd = enrollmentPeriodEnd;
		this.totalPlaces = totalPlaces;
		this.leftPlaces = leftPlaces;
		this.date = date;
		this.sessionNumber = sessionNumber;
	}

	public String getNameOfAction() {
		return nameOfAction;
	}

	public void setNameOfAction(String nameOfAction) {
		this.nameOfAction = nameOfAction;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEnrollmentPeriodStart() {
		return enrollmentPeriodStart;
	}

	public void setEnrollmentPeriodStart(Date enrolmentPeriodStart) {
		this.enrollmentPeriodStart = enrolmentPeriodStart;
	}

	public Date getEnrollmentPeriodEnd() {
		return enrollmentPeriodEnd;
	}

	public void setEnrollmentPeriodEnd(Date enrolmentPeriodEnd) {
		this.enrollmentPeriodEnd = enrolmentPeriodEnd;
	}

	public int getTotalPlaces() {
		return totalPlaces;
	}

	public void setTotalPlaces(int totalPlaces) {
		this.totalPlaces = totalPlaces;
	}

	public int getLeftPlaces() {
		return leftPlaces;
	}

	public void setLeftPlaces(int leftPlaces) {
		this.leftPlaces = leftPlaces;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(int sessionNumber) {
		this.sessionNumber = sessionNumber;
	}
}
