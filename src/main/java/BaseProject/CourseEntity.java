package BaseProject;

/**
 * Domain model data for the courses
 * IMPORTANT: When using the Apache Commons DbUtils components you must
 * Strictly adhere to the Java capitalization convention:
 * - Capitalize all the words that form an identifier 
 * except the first letter of method and variable names.
 * - Do not use underscores
 * Follow also these same criteria in the names of tables and fields of the DB.
 */

public class CourseEntity {
	private String id;
	private String name; 
	private String day;
	private String teacher;
	private String location;
	private int startTime;
	private int endTime;
	private int price;
	private int spots;
	
	public String getId() { return this.id; }
	public String getName() { return this.name; }
	public String getDay() { return this.day; }
	public String getTeacher() { return this.teacher; }
	public String getLocation() { return this.location; }
	public int getStartTime() { return this.startTime; }
	public int getEndTime() { return this.endTime; }
	public int getPrice() { return this.price; }
	public int getSpots() { return this.spots; }
	public void setId(String value) { this.id=value; }
	public void setName(String value) { this.name=value; }
	public void setDay(String value) { this.day=value; }
	public void setTeacher(String value) { this.teacher=value; }
	public void setLocation(String value) { this.location=value; }
	public void setStartTime(int value) { this.startTime=value; }
	public void setEndTime(int value) { this.endTime=value; }
	public void setPrice(int value) { this.price=value; }
	public void setSpots(int value) { this.spots=value; }

	
}
