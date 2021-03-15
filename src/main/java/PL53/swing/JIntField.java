package PL53.swing;

import java.text.DecimalFormat;

public class JIntField extends JNumberField<Integer>{
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
	
	@Override
	protected boolean isAllowed(char c) {
		return  (c == '-' && getText().length() == 0);
	}
	
	@Override
	protected void initialValue() {
		this.setText(Integer.toString(Math.round(defaultValue)));
	}

	
	public Integer getValue()  {
		if (numberDigits(getText()) > 0)
			return Integer.parseInt(this.getText());
		else
			return (int) minValue;
	}
	
	@Override
	protected Integer getInternalValue() {
		if (numberDigits(getText()) > 0)
			return Integer.parseInt(this.getText());
		else
			return 0;
	}

	@Override
	protected Integer parse(String str) {
		return Integer.parseInt(str);
	}

	@Override
	public Integer getDefaultValue() {
		return (int) defaultValue;
	}

	@Override
	public Integer getMinValue() {
		return (int) minValue;
	}

	@Override
	public Integer getMaxValue() {
		return (int) maxValue;
	}

	@Override
	public void setText(Integer f) {
		this.setText(f);
	}
}
