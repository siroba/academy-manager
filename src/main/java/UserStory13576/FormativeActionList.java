package UserStory13576;

import PL53.SI2020_PL53.Date;

public class FormativeActionList {
	private String nameOfAction;
	private String status;
	private String enrollmentPeriodStart;
	private String enrollmentPeriodEnd;
	private int totalPlaces;
	private int leftPlaces;
	private Date date;
	
	public FormativeActionList(String nameOfAction, String status, String enrollmentPeriodStart,
			String enrollmentPeriodEnd, int totalPlaces, int leftPlaces, Date date) {
		super();
		this.nameOfAction = nameOfAction;
		this.status = status;
		this.enrollmentPeriodStart = enrollmentPeriodStart;
		this.enrollmentPeriodEnd = enrollmentPeriodEnd;
		this.totalPlaces = totalPlaces;
		this.leftPlaces = leftPlaces;
		this.date = date;
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

	public String getEnrollmentPeriodStart() {
		return enrollmentPeriodStart;
	}

	public void setEnrollmentPeriodStart(String enrolmentPeriodStart) {
		this.enrollmentPeriodStart = enrolmentPeriodStart;
	}

	public String getEnrollmentPeriodEnd() {
		return enrollmentPeriodEnd;
	}

	public void setEnrollmentPeriodEnd(String enrolmentPeriodEnd) {
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
}
