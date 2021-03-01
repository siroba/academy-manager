package Entities;

import java.util.ArrayList;

import Exceptions.InvalidFieldValue;

/**
 * Domain model data for the professionals IMPORTANT: When using the Apache
 * Commons DbUtils components you must Strictly adhere to the Java
 * capitalization convention: - Capitalize all the words that form an identifier
 * except the first letter of method and variable names. - Do not use
 * underscores Follow also these same criteria in the names of tables and fields
 * of the DB.
 */

public class Professional {
	private String name, surname, phone, email;
	private ArrayList<Enrollment> enrollments;

	public Professional(String name, String surname, String phone, String email) {
		this.name = name;
		this.surname = surname;

		if (!checkPhone(phone))
			throw new InvalidFieldValue("phone number", phone);
		else
			this.phone = phone;

		if (!checkEmail(email))
			throw new InvalidFieldValue("email", email);
		else
			this.email = email;

		enrollments = new ArrayList<Enrollment>();
	}

	/**
	 * Enrolls the professional to the given formative action
	 * 
	 * @param FormativeAction
	 * @param EnrollmentName
	 */
	public void enroll(FormativeAction fA, String name) {
		Enrollment e = new Enrollment(name, fA);
		enrollments.add(e);
	}
	
	/**
	 * This function checks whether the phone number is valid or not (true or false)
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean checkPhone(String phone) {
		// Regex pattern that matches a phone number
		String pattern = "^\\+(?:[0-9] ?){6,14}[0-9]$";

		return phone.matches(pattern);
	}
	
	/**
	 * This function checks whether the email address is valid or not (true or
	 * false)
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		// Regex pattern that matches a phone number
		String pattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

		return email.matches(pattern);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		if (!checkPhone(phone))
			throw new InvalidFieldValue("phone number", phone);
		else
			this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (!checkEmail(email))
			throw new InvalidFieldValue("email address", email);
		else
			this.email = email;
	}

}
