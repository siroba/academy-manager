package Entities;

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

}