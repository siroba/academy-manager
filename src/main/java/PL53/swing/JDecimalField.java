package PL53.swing;

import java.beans.JavaBean;
import java.text.DecimalFormat;

/**
 * @author marcos
 *
 *         A simple class to input numbers of a fixed length. When typing a
 *         letter, the event is consumed. When gaining focus, the text is
 *         deleted. Its default value if it is empty and on creation is 0.<br/>
 * 
 *         This is used in the {@link DateTimeInput} and {@link DateInput}
 *         classes.
 */
@JavaBean(defaultProperty = "UIClassID", description = "A component which allows for the editing of a single decimal number with the possibility of adding constraints.")
public class JDecimalField extends JNumberField<Float> {
	// Auto generated serial ID
	private static final long serialVersionUID = 4549728989108805238L;

	/**
	 * Default constructor
	 * 
	 * @param nDigits Maximum number of digits for the text box
	 */
	public JDecimalField(int nDigits) {
		super(nDigits);
	}

	/**
	 * You can give this constructor a {@link java.text.DecimalFormat} object. It is
	 * NOT used in the function {@link #getValue()}.
	 * 
	 * @param nDigits Maximum number of digits for the text box
	 * @param format
	 */
	public JDecimalField(int nDigits, DecimalFormat format) {
		super(nDigits, format);
	}

	@Override
	protected boolean isAllowed(char c) {
		return  (c == '-' && getText().length() == 0) || 
				(c == '.' && !getText().contains("."));
	}

	@Override
	protected Float getInternalValue() {
		if (numberDigits(getText()) > 0)
			return Float.parseFloat(this.getText());
		else
			return 0f;
	}
	
	@Override
	public Float getValue()  {
		if (numberDigits(getText()) > 0)
			return Float.parseFloat(this.getText());
		else
			return minValue;
	}

	@Override
	protected Float parse(String str) {
		return Float.parseFloat(str);
	}
	
	@Override
	public Float getDefaultValue() {
		return defaultValue;
	}

	@Override
	public void setText(Float f) {
		this.setText(Float.toString(f));
	}

	@Override
	public Float getMinValue() {
		return minValue;
	}

	@Override
	public Float getMaxValue() {
		return maxValue;
	}
}
