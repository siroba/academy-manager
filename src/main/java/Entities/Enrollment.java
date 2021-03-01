package Entities;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;

public class Enrollment {
	private boolean isCancelled;
	private Date date;
	private String name;
	private FormativeAction formativeAction;

	/**
	 * Enrollment default constructor. "isCancelled" is assumed to be false and the date is assumed to be today.
	 * 
	 * @param name
	 * @param formativeAction
	 * @param date
	 */
	public Enrollment(String name, FormativeAction formativeAction) {
		this.isCancelled = false;
		this.date = Date.valueOf(LocalDate.now());
		this.name = name;
		this.formativeAction = formativeAction;
	}

	/**
	 * Enrollment constructor.
	 * 
	 * @param name
	 * @param formativeAction
	 * @param date
	 * @param isCancelled
	 */
	public Enrollment(String name, FormativeAction formativeAction, Date date, boolean isCancelled) {
		this.isCancelled = isCancelled;
		this.date = date;
		this.name = name;
		this.formativeAction = formativeAction;
	}

	/**
	 * Enrollment default constructor, but it takes care of creating the date
	 * object. "isCancelled" is assumed to be false.
	 * 
	 * @param name
	 * @param formativeAction
	 * @param day
	 * @param month
	 * @param year
	 */
	public Enrollment(String name, FormativeAction formativeAction, int day, int month, int year) {
		this.isCancelled = false;
		this.date = Date.valueOf(year + "-" + month + "-" + day);
		this.name = name;
		this.formativeAction = formativeAction;
	}

	/**
	 * Enrollment default constructor, but it takes care of creating the date
	 * object.
	 * 
	 * @param name
	 * @param formativeAction
	 * @param day
	 * @param month
	 * @param year
	 * @param isCancelled
	 */
	public Enrollment(String name, FormativeAction formativeAction, int day, int month, int year, boolean isCancelled) {
		this.isCancelled = isCancelled;
		this.date = Date.valueOf(year + "-" + month + "-" + day);
		this.name = name;
		this.formativeAction = formativeAction;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDate(int day, int month, int year) {
		this.date = Date.valueOf(year + "-" + month + "-" + day);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FormativeAction getFormativeAction() {
		return formativeAction;
	}

	public void setFormativeAction(FormativeAction formativeAction) {
		this.formativeAction = formativeAction;
	}
}
