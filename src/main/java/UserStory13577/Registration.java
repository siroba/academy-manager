package UserStory13577;

import java.util.Date;

public class Registration {
	//p.name, p.surname, date(e.timeEn), Fee.amount, e.status
	private String name;
	private String surnames;
	private Date enrollmentDate;
	private float amount;
	private String status;
	
	public Registration(String name, String surnames, Date enrollmentDate, float amount, String status) {
		this.name = name;
		this.surnames = surnames;
		this.enrollmentDate = enrollmentDate;
		this.amount = amount;
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurnames() {
		return surnames;
	}
	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}
	public Date getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
