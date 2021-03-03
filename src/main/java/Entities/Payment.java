package Entities;

/**
 * Domain model data for the payments
 * IMPORTANT: When using the Apache Commons DbUtils components you must
 * Strictly adhere to the Java capitalization convention:
 * - Capitalize all the words that form an identifier 
 * except the first letter of method and variable names.
 * - Do not use underscores
 * Follow also these same criteria in the names of tables and fields of the DB.
 */

public class Payment {
	private int id = -1;
	private String name; 
	private String datePay; 
	private Enrollment enrollment;
	private static int idCounter = 1001;
	
	// Constructor 
	public Payment(String name, String datePay, Enrollment enrollment) {
		
			this.id = idCounter++;
			this.name = name; 
			this.datePay = datePay;
			this.enrollment = enrollment; 			
	}
}
