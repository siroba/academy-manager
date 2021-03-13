package PL53.swing;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

import PL53.util.Date;

public class DateInput extends JPanel {
	/** Auto generated serial ID */
	private static final long serialVersionUID = 1733893010805448139L;
	private JNumberField daysTextField, yearsTextField, monthsTextField;
	
	/** The format for the day and month, see {@link JNumberField#JNumberField(int, DecimalFormat)}. */
	public static final DecimalFormat format = new DecimalFormat("#0");
	
	/** The format for the year, see {@link JNumberField#JNumberField(int, DecimalFormat)}. */
	public static final DecimalFormat yearFormat = new DecimalFormat("0000");
	
	/**
	 * Default constructor. Creates the panel and adds all the components.
	 */
	public DateInput() {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(241, 46));

		JPanel datePanel = new JPanel();
		datePanel.setLayout(null);
		datePanel.setBounds(0, 0, 238, 45);
		this.add(datePanel);

		JPanel dayPanel = new JPanel();
		dayPanel.setBounds(0, 0, 61, 45);
		datePanel.add(dayPanel);
		dayPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblDay = new JLabel("Days:");
		dayPanel.add(lblDay);
		
		daysTextField = new JNumberField(2, format);
		daysTextField.setText("0");
		dayPanel.add(daysTextField);

		JLabel labelSep1 = new JLabel("/");
		labelSep1.setBounds(68, 23, 16, 15);
		datePanel.add(labelSep1);

		JPanel monthPanel = new JPanel();
		monthPanel.setBounds(79, 0, 70, 45);
		datePanel.add(monthPanel);
		monthPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblMonth = new JLabel("Months:");
		monthPanel.add(lblMonth);

		monthsTextField = new JNumberField(2, format);
		monthsTextField.setText("0");
		monthPanel.add(monthsTextField);

		JLabel labelSep2 = new JLabel("/");
		labelSep2.setBounds(155, 23, 16, 15);
		datePanel.add(labelSep2);

		JPanel yearPanel = new JPanel();
		yearPanel.setBounds(166, 0, 70, 45);
		datePanel.add(yearPanel);
		yearPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblYear = new JLabel("Years:");
		yearPanel.add(lblYear);

		yearsTextField = new JNumberField(4, yearFormat);
		yearsTextField.setText("0");
		yearPanel.add(yearsTextField);
	}

	public int getDay() {
		return daysTextField.getValue();
	}

	public int getMonth() {
		return monthsTextField.getValue();
	}

	public int getYear() {
		return yearsTextField.getValue();
	}

	public JNumberField getDaysTextField() {
		return daysTextField;
	}

	public JNumberField getYearsTextField() {
		return yearsTextField;
	}

	public JNumberField getMonthsTextField() {
		return monthsTextField;
	}
	
	/**
	 * Parses the values of the fields into a {@link Date} object.
	 * 
	 * @return A {@link Date} object.
	 */
	public Date getDate() {
		return new Date(this.getDay(), this.getMonth(), this.getYear());
	}
}
