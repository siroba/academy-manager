package UserStory13573;

/**
 * Domain model data for the courses
 * IMPORTANT: When using the Apache Commons DbUtils components you must
 * Strictly adhere to the Java capitalization convention:
 * - Capitalize all the words that form an identifier 
 * except the first letter of method and variable names.
 * - Do not use underscores
 * Follow also these same criteria in the names of tables and fields of the DB.
 */

public class FormativeActionEntity {
	private String name; 
	private String objectives; 
	private String mainContents; 
	private String teacher;
	private int remuneration;
	private String location;
	private String day;
	private int numberOfHours;
	private int spaces;
	private String enrollmentPeriodStart;
	private String enrollmentPeriodEnd;
	
	
	public FormativeActionEntity(String name, String objectives, String mainContents, String teacher, int remuneration,
			String location, String day, int numberOfHours, int spaces, String enrollmentPeriodStart,
			String enrollmentPeriodEnd) {
		
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
	public String getName() { return this.name; }
	public String getObjectives() { return this.objectives; }
	public String getMainContents() { return this.mainContents; }
	public String getTeacher() { return this.teacher; }
	public int getRemuneration() { return this.remuneration; }
	public String getLocation() { return this.location; }
	public String getDay() { return this.day; }
	public int getNumberOfHours() { return this.numberOfHours; }
	public int getSpaces() { return this.spaces; }
	public String getEnrollmentPeriodStart() { return this.enrollmentPeriodStart; }
	public String getEnrollmentPeriodEnd() { return this.enrollmentPeriodEnd; }

	
	// Setter
	public void setName(String value) { this.name=value; }
	public void setObjectives(String value) { this.objectives=value; }
	public void setMainContents(String value) { this.mainContents=value; }
	public void setTeacher(String value) { this.teacher=value; }
	public void setRemuneration(int value) { this.remuneration=value; }
	public void setLocation(String value) { this.location=value; }	
	public void setDay(String value) { this.day=value; }
	public void setNumberOfHours(int value) { this.numberOfHours=value; }
	public void setSpaces(int value) { this.spaces=value; }
	public void setEnrollmentPeriodStart(String value) { this.enrollmentPeriodStart=value; }
	public void setEnrollmentPeriodEnd(String value) { this.enrollmentPeriodEnd=value; }

	
}
