package ListFormativeActions;

/**
 * Helper class
 */
public class FormativeActionDetails {
	private String objectives;
	private String mainContents;
	private String place;
	private String teacher_name;

	public FormativeActionDetails(String objectives, String mainContents, String place, String teacher_name) {
		this.objectives = objectives;
		this.mainContents = mainContents;
		this.place = place;
		this.teacher_name = teacher_name;
	}

	public String getObjectives() {
		return objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	public String getMainContents() {
		return mainContents;
	}

	public void setMainContents(String mainContents) {
		this.mainContents = mainContents;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

}
