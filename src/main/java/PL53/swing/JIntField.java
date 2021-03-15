package PL53.swing;

import java.text.DecimalFormat;

public class JIntField extends JNumberField{
	// Auto generated serial ID
	private static final long serialVersionUID = 857421043933782945L;
	
	/**
	 * Default constructor
	 * 
	 * @param nDigits Maximum number of digits for the text box
	 */
	public JIntField(int nDigits) {
		super(nDigits);
	}

	/**
	 * You can give this constructor a {@link java.text.DecimalFormat} object. It is
	 * NOT used in the function {@link #getValue()}.
	 * 
	 * @param nDigits Maximum number of digits for the text box
	 * @param format
	 */
	public JIntField(int nDigits, DecimalFormat format) {
		super(nDigits, format);
	}
	
	protected boolean isAllowed(char c) {
		return  (c == '-' && getText().length() == 0);
	}
	
	/**
	 * Set the bounds for the value of the field
	 * 
	 * @param min
	 * @param max
	 */
	public void setBound(int min, int max) {
		this.maxValue = max;
		this.minValue = min;
	}

	public void setDefaultValue(int value) {
		defaultValue = value;
		
		this.setText(Integer.toString(value));
	}
	
	public int getIntValue() {
		return Math.round(this.getValue());
	}
	
	@Override
	protected void initialValue() {
		this.setText(Integer.toString(Math.round(defaultValue)));
	}
}
