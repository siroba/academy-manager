package Entities;

import BaseProject.Database;
import PL53.SI2020_PL53.Date;

/**
 * Domain model data for the courses IMPORTANT: When using the Apache Commons
 * DbUtils components you must Strictly adhere to the Java capitalization
 * convention: - Capitalize all the words that form an identifier except the
 * first letter of method and variable names. - Do not use underscores Follow
 * also these same criteria in the names of tables and fields of the DB.
 */
public class FormativeAction {
	private String name, objectives, mainContents, location, day;
	private Teacher teacher;
	private int numberOfHours, spaces;
	float remuneration;
	private Date enrollmentPeriodStart, enrollmentPeriodEnd; //TODO: Is this correct?

	public FormativeAction(String name, String objectives, String mainContents, Teacher teacher, float remuneration,
			String location, String day, int numberOfHours, int spaces, 
			Date enrollmentPeriodStart, Date enrollmentPeriodEnd) {

		this.name = name;
		this.objectives = objectives;
		this.mainContents = mainContents;
		this.teacher = teacher;
		this.remuneration = remuneration;
		this.location = location;
		this.day = day;
		this.numberOfHours = numberOfHours;
		this.spaces = spaces;
		this.enrollmentPeriodStart = enrollmentPeriodStart;
		this.enrollmentPeriodEnd = enrollmentPeriodEnd;
	}

	// Getter
	public String getName() {
		return this.name;
	}

	public String getObjectives() {
		return this.objectives;
	}

	public String getMainContents() {
		return this.mainContents;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public float getRemuneration() {
		return this.remuneration;
	}

	public String getLocation() {
		return this.location;
	}

	public String getDay() {
		return this.day;
	}

	public int getNumberOfHours() {
		return this.numberOfHours;
	}

	public int getSpaces() {
		return this.spaces;
	}

	public Date getEnrollmentPeriodStart() {
		return this.enrollmentPeriodStart;
	}

	public Date getEnrollmentPeriodEnd() {
		return this.enrollmentPeriodEnd;
	}

	// Setter
	public void setName(String value) {
		this.name = value;
	}

	public void setObjectives(String value) {
		this.objectives = value;
	}

	public void setMainContents(String value) {
		this.mainContents = value;
	}

	public void setTeacher(Teacher value) {
		this.teacher = value;
	}

	public void setRemuneration(float value) {
		this.remuneration = value;
	}

	public void setLocation(String value) {
		this.location = value;
	}

	public void setDay(String value) {
		this.day = value;
	}

	public void setNumberOfHours(int value) {
		this.numberOfHours = value;
	}

	public void setSpaces(int value) {
		this.spaces = value;
	}

	public void setEnrollmentPeriodStart(Date value) {
		this.enrollmentPeriodStart = value;
	}

	public void setEnrollmentPeriodEnd(Date value) {
		this.enrollmentPeriodEnd = value;
	}

	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static FormativeAction obtain(int formativeActionID, Database database) {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getDate() {
		return this.enrollmentPeriodEnd; // TODO: Is this correct?
	}
}
