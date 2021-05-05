package PL53.swing;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.BeanProperty;
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
public abstract class JNumberField <T extends Number> extends javax.swing.JFormattedTextField {
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
					T newval = parse(getText() + e.getKeyChar());

					if (newval.floatValue() > maxValue) {
						setText(getMaxValue());
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
				T newval = getInternalValue();

				if (newval.floatValue() < minValue) {
					setText(getMinValue());
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

	protected abstract boolean isAllowed(char c);
	protected abstract T getInternalValue();
	protected abstract T parse(String str);
	/**
	 * If the text field is empty, it returns 0. Otherwise, it parses the text
	 * ({@link #getText()}) to a Float.
	 */
	public abstract T getValue();
	public abstract void setValue(T val);

	public int getMaxLength() {
		return maxLength;
	}
	
	@BeanProperty(preferred = true, bound = false, description = "The maximum number of digits this NumberField can have")
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	
	protected void initialValue() {
		this.setText(getDefaultValue());
	}

	/**
	 * Set the bounds for the value of the field
	 * 
	 * @param min
	 * @param max
	 */
	@BeanProperty(preferred = true, bound = false, description = "The maximum and minimum value (constraints) of this NumberField")
	public void setBound(T min, T max) {
		this.maxValue = max.floatValue();
		this.minValue = min.floatValue();
	}

	public abstract T getDefaultValue();
	public abstract T getMinValue();
	public abstract T getMaxValue();
	
	@BeanProperty(preferred = true, bound = false, visualUpdate = true, description = "Default value on creation")
	public void setDefaultValue(T value) {
		defaultValue = value.floatValue();
		
		this.setText(getDefaultValue());
	}
	
	@BeanProperty(hidden = true)
	public abstract void setText(T f);
}
