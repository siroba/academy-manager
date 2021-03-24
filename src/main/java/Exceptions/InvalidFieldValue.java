package Exceptions;

public class InvalidFieldValue extends Error {
	// Serial ID assigned by eclipse
	private static final long serialVersionUID = 3313658448532576366L;

	private String fieldName, value;
	
	public InvalidFieldValue(String fieldName, String value) {
		super("The field \"" + fieldName + "\" with value \"" + value + "\" was not valid.");
		
		this.fieldName = fieldName;
		this.value = value;
	}
  
	@Override
	public String toString() {
		return "The field \"" + fieldName + "\" with value \"" + value + "\" was not valid.";
	}
}
