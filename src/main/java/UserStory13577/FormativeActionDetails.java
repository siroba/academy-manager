package UserStory13577;

import java.util.Date;

public class FormativeActionDetails {
//(name, state, enrolment period, date, total places and places left)
	private String name;
	private String status;
	private Date enrollmentPeriodStart;
	private Date enrollmentPeriodEnd;
	private int totalPlaces;
	private int leftPlaces;
	
	public FormativeActionDetails(String name, String status, Date enrollmentPeriodStart, Date enrollmentPeriodEnd,
			int totalPlaces, int leftPlaces) {
		this.name = name;
		this.status = status;
		this.enrollmentPeriodStart = enrollmentPeriodStart;
		this.enrollmentPeriodEnd = enrollmentPeriodEnd;
		this.totalPlaces = totalPlaces;
		this.leftPlaces = leftPlaces;
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
}
