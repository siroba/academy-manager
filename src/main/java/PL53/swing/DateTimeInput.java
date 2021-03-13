package PL53.swing;

import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import PL53.util.Date;
import PL53.util.DateTime;

public class DateTimeInput extends JPanel {
	/** Auto generated serial ID */
	private static final long serialVersionUID = 1733893010805448139L;

	/**
	 * The format for the hour and the minute, see
	 * {@link JNumberField#JNumberField(int, DecimalFormat)}.
	 */
	public static final DecimalFormat format = new DecimalFormat("#0");
	private JNumberField hoursTextField, minutesTextField;
	private DateInput datePanel;

	/**
	 * Default constructor. Creates the panel, adds all the components and creates
	 * one {@link DateInput}.
	 */
	public DateTimeInput() {
		format.setMaximumIntegerDigits(2);

		setLayout(null);
		JPanel timePanel = new JPanel();
		timePanel.setBounds(5, 9, 148, 57);
		this.add(timePanel);
		timePanel.setLayout(null);

		JLabel lblHour = new JLabel("hours : minutes");
		lblHour.setBounds(17, 0, 120, 22);
		timePanel.add(lblHour);

		hoursTextField = new JNumberField(2, format);
		hoursTextField.setBound(0, 23);
		hoursTextField.setHorizontalAlignment(SwingConstants.CENTER);
		hoursTextField.setBounds(25, 20, 34, 22);
		timePanel.add(hoursTextField);
		hoursTextField.setText("0");

		JLabel labelSep = new JLabel(":");
		labelSep.setBounds(62, 23, 5, 15);
		timePanel.add(labelSep);

		minutesTextField = new JNumberField(2, format);
		minutesTextField.setBound(0, 59);
		minutesTextField.setHorizontalAlignment(SwingConstants.CENTER);
		minutesTextField.setBounds(70, 20, 34, 22);
		timePanel.add(minutesTextField);
		minutesTextField.setText("0");

		datePanel = new DateInput();
		datePanel.setLayout(null);
		datePanel.setBounds(165, 9, 208, 57);
		this.add(datePanel);
	}

	public int getHour() {
		return this.hoursTextField.getValue();
	}

	public int getMinute() {
		return this.minutesTextField.getValue();
	}

	public int getDay() {
		return datePanel.getDay();
	}

	public int getMonth() {
		return datePanel.getMonth();
	}

	public int getYear() {
		return datePanel.getYear();
	}

	public JNumberField getDaysTextField() {
		return datePanel.getDaysTextField();
	}

	public JNumberField getYearsTextField() {
		return datePanel.getYearsTextField();
	}

	public JNumberField getMonthsTextField() {
		return datePanel.getMonthsTextField();
	}

	public JNumberField getHoursTextField() {
		return hoursTextField;
	}

	public JNumberField getMinutesTextField() {
		return minutesTextField;
	}

	/**
	 * See {@link DateInput#getDate()}.
	 * 
	 * @return {@link Date} object.
	 */
	public Date getDate() {
		return datePanel.getDate();
	}

	/**
	 * Parses the values of the fields to a {@link DateTime} object.
	 * 
	 * @return {@link DateTime} object.
	 */
	public DateTime getDateTime() {
		return new DateTime(this.getMinute(), this.getHour(), datePanel.getDay(), datePanel.getMonth(),
				datePanel.getYear());
	}

}
