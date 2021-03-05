package Entities;

import PL53.SI2020_PL53.Random;

public class Teacher {
	private int ID = -1;
	private float salary;
	private String name;

	public Teacher(int ID, float salary, String name) {
		this.ID = ID;
		this.salary = salary;
		this.name = name;
	}

	public Teacher(float salary, String name) {
		this.salary = salary;
		this.name = name;
	}

	public Teacher() {
		Random r = new Random();
		this.salary = r.nextFloat()*2000f;
		this.name = r.name(3, 10);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Teacher obtain(int id) {
		// TODO Auto-generated method stub

		return null;
	}

}
