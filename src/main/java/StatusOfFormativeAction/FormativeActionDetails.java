package StatusOfFormativeAction;

import PL53.util.Date;

public class FormativeActionDetails {
	private String name;
	private String status;
	private Date enrollmentPeriodStart;
	private Date enrollmentPeriodEnd;
	private int totalPlaces;
	private int leftPlaces;
	private Date dateOfFirstSession;
	private Date dateOfLastSession;
	
	public FormativeActionDetails(String name, String status, Date enrollmentPeriodStart, Date enrollmentPeriodEnd,
			int totalPlaces, int leftPlaces, Date dateOfFirstSession, Date dateOfLastSession) {
		this.name = name;
		this.status = status;
		this.enrollmentPeriodStart = enrollmentPeriodStart;
		this.enrollmentPeriodEnd = enrollmentPeriodEnd;
		this.totalPlaces = totalPlaces;
		this.leftPlaces = leftPlaces;
		this.dateOfFirstSession = dateOfFirstSession;
		this.dateOfLastSession = dateOfLastSession;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setEnrollmentPeriodStart(Date enrollmentPeriodStart) {
		this.enrollmentPeriodStart = enrollmentPeriodStart;
	}

	public Date getEnrollmentPeriodEnd() {
		return enrollmentPeriodEnd;
	}

	public void setEnrollmentPeriodEnd(Date enrollmentPeriodEnd) {
		this.enrollmentPeriodEnd = enrollmentPeriodEnd;
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

	public Date getDateOfFirstSession() {
		return dateOfFirstSession;
	}

	public void setDateOfFirstSession(Date dateOfFirstSession) {
		this.dateOfFirstSession = dateOfFirstSession;
	}

	public Date getDateOfLastSession() {
		return dateOfLastSession;
	}

	public void setDateOfLastSession(Date dateOfLastSession) {
		this.dateOfLastSession = dateOfLastSession;
	}
}
