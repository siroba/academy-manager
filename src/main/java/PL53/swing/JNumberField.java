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
	private static final long serialVersionUID = 374166271716319247L;

	/**
	 * The maximum length of the number
	 */
	private int maxLength;

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
	 * delete the content on focus gained.
	 */
	private void init() {
		this.setText("0");

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar()) || getText().length() >= maxLength) {
					e.consume();
				}
			}
		});

		this.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				setText("");
			}
		});
	}

	/**
	 * If the text field is empty, it returns 0. Otherwise, it parses the text ({@link #getText()}) to an integer.
	 */
	public Integer getValue() {
		if (this.getText().length() > 0)
			return Integer.parseInt(this.getText());
		else
			return 0;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
}
