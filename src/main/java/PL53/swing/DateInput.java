package PL53.swing;

import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.EventListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import PL53.util.Date;

import javax.swing.SwingConstants;
import javax.swing.event.EventListenerList;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class DateInput extends JPanel {
	/** Auto generated serial ID */
	private static final long serialVersionUID = 1733893010805448139L;
	private JIntField daysTextField, yearsTextField, monthsTextField;
	private JLabel lblYear, labelSep1, labelSep2;

	/**
	 * The format for the day and month, see
	 * {@link JIntField#JIntField(int, DecimalFormat)}.
	 */
	public static final DecimalFormat format = new DecimalFormat("#0");

	/**
	 * The format for the year, see {@link JIntField#JIntField(int, DecimalFormat)}.
	 */
	public static final DecimalFormat yearFormat = new DecimalFormat("0000");

	/**
	 * The list of event listeners
	 */
	protected EventListenerList listenerList = new EventListenerList();

	/**
	 * Default constructor. Creates the panel and adds all the components.
	 */
	public DateInput() {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(203, 55));

		lblYear = new JLabel("day / month / year");
		lblYear.setFont(new Font("Dialog", Font.BOLD, 11));
		lblYear.setBounds(28, 0, 159, 22);
		add(lblYear);

		daysTextField = new JIntField(2, format);
		daysTextField.setDefaultValue(1);
		daysTextField.setBound(1, 31);
		daysTextField.setHorizontalAlignment(SwingConstants.CENTER);
		daysTextField.setBounds(9, 21, 34, 22);
		add(daysTextField);
		
		daysTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(allDateFieldsModified())
					fireMyEvent();
			}
		});

		labelSep1 = new JLabel("/");
		labelSep1.setBounds(52, 25, 5, 15);
		add(labelSep1);

		monthsTextField = new JIntField(2, format);
		monthsTextField.setDefaultValue(1);
		monthsTextField.setBound(1, 12);
		monthsTextField.setHorizontalAlignment(SwingConstants.CENTER);
		monthsTextField.setBounds(66, 21, 34, 22);
		add(monthsTextField);

		monthsTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(allDateFieldsModified())
					fireMyEvent();
			}
		});
		
		labelSep2 = new JLabel("/");
		labelSep2.setBounds(109, 25, 5, 15);
		add(labelSep2);

		yearsTextField = new JIntField(4, yearFormat);
		yearsTextField.setBound(0, Integer.MAX_VALUE);
		yearsTextField.setHorizontalAlignment(SwingConstants.CENTER);
		yearsTextField.setBounds(123, 21, 70, 22);
		add(yearsTextField);
		
		yearsTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(allDateFieldsModified())
					fireMyEvent();
			}
		});

	}

	protected boolean allDateFieldsModified() {
		return daysTextField.getValue() != daysTextField.getDefaultValue()
				&& monthsTextField.getValue() != monthsTextField.getDefaultValue()
				&& yearsTextField.getValue() != yearsTextField.getDefaultValue();
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

	public JIntField getDaysTextField() {
		return daysTextField;
	}

	public JIntField getYearsTextField() {
		return yearsTextField;
	}

	public JIntField getMonthsTextField() {
		return monthsTextField;
	}

	public JLabel getLblYear() {
		return lblYear;
	}

	public JLabel getLabelSep1() {
		return labelSep1;
	}

	public JLabel getLabelSep2() {
		return labelSep2;
	}

	/**
	 * Parses the values of the fields into a {@link Date} object.
	 * 
	 * @return A {@link Date} object.
	 */
	public Date getDate() {
		return new Date(this.getDay(), this.getMonth(), this.getYear());
	}

	public void addDateListener(DateModifiedListener listener) {
		listenerList.add(DateModifiedListener.class, listener);
	}

	public void removeDateListener(DateModifiedListener listener) {
		listenerList.remove(DateModifiedListener.class, listener);
	}

	protected void fireMyEvent() {
		Object[] listeners = listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i = i + 2) {
			if (listeners[i] == DateModifiedListener.class)
				((DateModifiedListener) listeners[i + 1]).dateModified();
		}
	}

	public interface DateModifiedListener extends EventListener {
		public void dateModified();
	}
}
