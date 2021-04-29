package PL53.swing;

import java.text.DecimalFormat;
import java.util.EventListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.EventListenerList;

import PL53.swing.DateInput.DateModifiedListener;
import PL53.util.Date;
import PL53.util.DateTime;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DateTimeInput extends JPanel {
	/** Auto generated serial ID */
	private static final long serialVersionUID = 1733893010805448139L;

	/**
	 * The format for the hour and the minute, see
	 * {@link JIntField#JIntField(int, DecimalFormat)}.
	 */
	public static final DecimalFormat format = new DecimalFormat("#0");
	private JIntField hoursTextField, minutesTextField;
	private DateInput datePanel;
	private JLabel lblHour, labelSep;
	protected EventListenerList listenerList = new EventListenerList();

	/**
	 * Default constructor. Creates the panel, adds all the components and creates
	 * one {@link DateInput}.
	 */
	public DateTimeInput() {
		format.setMaximumIntegerDigits(2);

		setLayout(null);
		JPanel timePanel = new JPanel();
		timePanel.setBounds(10, 11, 120, 57);
		this.add(timePanel);
		timePanel.setLayout(null);

		lblHour = new JLabel("hour : minute");
		lblHour.setFont(new Font("Dialog", Font.BOLD, 11));
		lblHour.setBounds(24, 0, 86, 22);
		timePanel.add(lblHour);

		hoursTextField = new JIntField(2, format);
		hoursTextField.setBound(0, 23);
		hoursTextField.setHorizontalAlignment(SwingConstants.CENTER);
		hoursTextField.setBounds(21, 20, 34, 22);
		timePanel.add(hoursTextField);

		hoursTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // TODO: The "ActionPerformed" is not working
				if(allTimeFieldsModified()) {
					fireTimeEvent();
					
					if(allTimeFieldsModified())
						fireDateTimeEvent();
				}
			}
		});
		
		labelSep = new JLabel(":");
		labelSep.setBounds(58, 23, 5, 15);
		timePanel.add(labelSep);

		minutesTextField = new JIntField(2, format);
		minutesTextField.setBound(0, 59);
		minutesTextField.setHorizontalAlignment(SwingConstants.CENTER);
		minutesTextField.setBounds(66, 20, 34, 22);
		timePanel.add(minutesTextField);

		minutesTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // TODO: The "ActionPerformed" is not working
				if(allTimeFieldsModified()) {
					fireTimeEvent();
					
					if(allTimeFieldsModified())
						fireDateTimeEvent();
				}
			}
		});
		
		datePanel = new DateInput();
		datePanel.getLblYear().setLocation(28, 0);
		datePanel.getLblYear().setFont(new Font("Dialog", Font.BOLD, 11));
		datePanel.getLblYear().setText("day / month / year");
		datePanel.setLayout(null);
		datePanel.setBounds(148, 11, 208, 57);
		this.add(datePanel);
		
		datePanel.addDateListener(new DateModifiedListener() {
			@Override
			public void dateModified() {
				if(allTimeFieldsModified())
					fireDateTimeEvent();
			}
		});
	}
	
	protected boolean allTimeFieldsModified() {
		return hoursTextField.getValue() != hoursTextField.getDefaultValue()
				&& minutesTextField.getValue() != minutesTextField.getDefaultValue();
	}
	
	protected boolean everythingModified () {
		return datePanel.allDateFieldsModified() && allTimeFieldsModified();
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
  
	public JIntField getHoursTextField() {
		return hoursTextField;
	}

	public JIntField getMinutesTextField() {
		return minutesTextField;
	}

	public JLabel getLblHour() {
		return lblHour;
	}

	public JLabel getLabelSep() {
		return labelSep;
	}

	public DateInput getDatePanel() {
		return datePanel;
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
	
	public void addDateTimeListener(DateTimeModifiedListener listener) {
		listenerList.add(DateTimeModifiedListener.class, listener);
	}

	public void removeDateTimeListener(DateTimeModifiedListener listener) {
		listenerList.remove(DateTimeModifiedListener.class, listener);
	}

	protected void fireTimeEvent() {
		Object[] listeners = listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i = i + 2) {
			if (listeners[i] == DateTimeModifiedListener.class)
				((DateTimeModifiedListener) listeners[i + 1]).timeModified();
		}
	}
	
	protected void fireDateTimeEvent() {
		Object[] listeners = listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i = i + 2) {
			if (listeners[i] == DateTimeModifiedListener.class)
				((DateTimeModifiedListener) listeners[i + 1]).dateTimeModified();
		}
	}
	
	public interface DateTimeModifiedListener extends EventListener {
		public void timeModified();
		public void dateTimeModified();
	}
	
}
