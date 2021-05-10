package StatusOfFormativeAction;

public class Teacher {
	private String name;
	private String surname;
	private float remuneration;
	
	public Teacher(String name, String surname, float remuneration) {
		super();
		this.name = name;
		this.surname = surname;
		this.remuneration = remuneration;
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
	public float getRemuneration() {
		return remuneration;
	}
	public void setRemuneration(float remuneration) {
		this.remuneration = remuneration;
	}

}
