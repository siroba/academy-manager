package CloseFormativeAction;

public class FormativeActionDetails {
	private String name;
	private boolean registrationFeesCorrect;
	private boolean remunerationCorrect;
	private boolean allSessionExecuted;
	
	public FormativeActionDetails(String name, boolean registrationFeesCorrect, boolean remunerationCorrect,
			boolean allSessionExecuted) {
		super();
		this.name = name;
		this.registrationFeesCorrect = registrationFeesCorrect;
		this.remunerationCorrect = remunerationCorrect;
		this.allSessionExecuted = allSessionExecuted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRegistrationFeesCorrect() {
		return registrationFeesCorrect;
	}

	public void setRegistrationFeesCorrect(boolean registrationFeesCorrect) {
		this.registrationFeesCorrect = registrationFeesCorrect;
	}

	public boolean isRemunerationCorrect() {
		return remunerationCorrect;
	}

	public void setRemunerationCorrect(boolean remunerationCorrect) {
		this.remunerationCorrect = remunerationCorrect;
	}

	public boolean isAllSessionExecuted() {
		return allSessionExecuted;
	}

	public void setAllSessionExecuted(boolean allSessionExecuted) {
		this.allSessionExecuted = allSessionExecuted;
	}

	
}
