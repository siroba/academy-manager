package PL53.swing;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
public class JNumberField extends javax.swing.JFormattedTextField {
	/** Auto generated Serial ID */
	protected static final long serialVersionUID = 374166271716319247L;

	/** The maximum length of the number */
	protected int maxLength;

	/**
	 * Bounds for the value of the field. By default, the bounds are infinity (no
	 * bounds).
	 */
	protected float maxValue = Float.MAX_VALUE, minValue = Float.MIN_VALUE;

	protected float defaultValue = 0;

	/**
	 * Default constructor
	 * 
	 * @param nDigits Maximum number of digits for the text box
	 */
	public JNumberField(int nDigits) {
		super();

		this.maxLength = nDigits;

		this.init();
	}

	/**
	 * You can give this constructor a {@link java.text.DecimalFormat} object. It is
	 * NOT used in the function {@link #getValue()}.
	 * 
	 * @param nDigits Maximum number of digits for the text box
	 * @param format
	 */
	public JNumberField(int nDigits, DecimalFormat format) {
		super(format);

		this.maxLength = nDigits;

		this.init();
	}

	/**
	 * Initializes the value to "0" and adds the listeners to allow only numbers and
	 * delete the content on focus gained. If the new value of the number is out of
	 * the limits ({@link #maxValue} and {@link #minValue}), the value is set to the
	 * closest bound.
	 */
	protected void init() {
		this.initialValue();

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// If the character is not a digit nor is it allowed
				if (!Character.isDigit(e.getKeyChar()) || numberDigits(getText()) >= maxLength) {
					if (isAllowed(e.getKeyChar())) {
						setText(getText() + e.getKeyChar());
					}

					e.consume();
				} else {
					float newval = Float.parseFloat(getText() + e.getKeyChar());

					if (newval > maxValue) {
						setText(Float.toString(maxValue));
						e.consume();
					}
				}
			}
		});

		this.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				float newval = getInternalValue();

				if (newval < minValue) {
					setText(Float.toString(minValue));
				}
			}
		});
	}

	protected int numberDigits(String str) {
		int n = 0;

		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				n++;
			}
		}

		return n;
	}

	protected boolean isAllowed(char c) {
		return  (c == '-' && getText().length() == 0) || 
				(c == '.' && !getText().contains("."));
	}

	protected float getInternalValue() {
		if (numberDigits(getText()) > 0)
			return Float.parseFloat(this.getText());
		else
			return 0;
	}

	/**
	 * If the text field is empty, it returns 0. Otherwise, it parses the text
	 * ({@link #getText()}) to an Float.
	 */
	public Float getValue() {
		if (numberDigits(getText()) > 0)
			return Float.parseFloat(this.getText());
		else
			return minValue;
	}

	public float getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	
	protected void initialValue() {
		this.setText(defaultValue);
	}

	/**
	 * Set the bounds for the value of the field
	 * 
	 * @param min
	 * @param max
	 */
	public void setBound(float min, float max) {
		this.maxValue = max;
		this.minValue = min;
	}

	public void setDefaultValue(float value) {
		defaultValue = value;
		
		this.setText(defaultValue);
	}
	
	public void setText(float f) {
		this.setText(Float.toString(f));
	}
}
