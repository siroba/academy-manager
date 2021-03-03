package Entities;

public class TrainingManager {
	private int ID = -1;
	private String name;

	public TrainingManager(String name) {
		this.name = name;
	}

	public TrainingManager(int ID, String name) {
		this.ID = ID;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public static TrainingManager obtain(int id) {
		// TODO Auto-generated method stub
		
		return null;
	}
}
