package UserStory13573;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class View13573 {

	private JFrame frame;
	private JTextField TFName;
	private JTextField TFContents;
	private JTextField TFTeacher;
	private JTextField TFRemuneration;
	private JTextField TFLocation;
	private JTextField TFHours;
	private JTextField TFSpaces;
	private JLabel LabelObjectives;
	private JTextField TFObjectives;
	private JButton BTNCreate;
	private JLabel LabelStartEnroll;
	private JLabel LabelWarningStartEnroll;
	private JLabel LabelWarningEndEnroll;
	private JComboBox comboBoxDayDay;
	private JComboBox comboBoxDayMonth;
	private JComboBox comboBoxDayYear;
	private JComboBox comboBoxEnrollStartDay;
	private JComboBox comboBoxEnrollStartMonth;
	private JComboBox comboBoxEnrollStartYear;
	private JComboBox comboBoxEnrollEndDay;
	private JComboBox comboBoxEnrollEndMonth;
	private JComboBox comboBoxEnrollEndYear;
	private JLabel LabelWarningDay;

	/**
	 * Create the application.
	 */
	public View13573() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.setTitle("Courses");
		frame.setName("Courses");
		frame.setBounds(0, 0, 492, 422);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow][][][][][][][][][][][][][][grow]"));
		
		// Heading 
		JLabel lblNewLabel = new JLabel("Plan a formative action");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(lblNewLabel, "cell 0 0,alignx center,aligny baseline");
		
		// Name 
		JLabel LabelName = new JLabel("Name:");
		LabelName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelName, "flowx,cell 0 1,gapx 20,aligny center");
		
		TFName = new JTextField();
		TFName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(TFName, "cell 0 1");
		TFName.setColumns(10);
		
		// Objectives 
		LabelObjectives = new JLabel("Objectives:");
		LabelObjectives.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelObjectives, "flowx,cell 0 2,gapx 20,aligny bottom");
		
		TFObjectives = new JTextField();
		TFObjectives.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(TFObjectives, "cell 0 2");
		TFObjectives.setColumns(10);
		
		// Main contents 
		JLabel LabelContents = new JLabel("Main contents:");
		LabelContents.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelContents, "flowx,cell 0 3,gapx 20");
		
		TFContents = new JTextField();
		TFContents.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(TFContents, "cell 0 3");
		TFContents.setColumns(10);
		
		// Teacher
		JLabel LabelTeacher = new JLabel("Teacher:");
		LabelTeacher.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelTeacher, "flowx,cell 0 4,gapx 20");
		
		TFTeacher = new JTextField();
		TFTeacher.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(TFTeacher, "cell 0 4");
		TFTeacher.setColumns(10);
		
		// Remuneration
		JLabel LabelRemuneration = new JLabel("Remuneration:");
		LabelRemuneration.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelRemuneration, "flowx,cell 0 5,gapx 20");
		
		TFRemuneration = new JTextField();
		TFRemuneration.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(TFRemuneration, "cell 0 5");
		TFRemuneration.setColumns(10);
		
		// Location 
		JLabel LabelLocation = new JLabel("Location:");
		LabelLocation.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelLocation, "flowx,cell 0 6,gapx 20");
		
		TFLocation = new JTextField();
		TFLocation.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(TFLocation, "cell 0 6");
		TFLocation.setColumns(10);
		
		// Date 
		JLabel LabelDate = new JLabel("Date:");
		LabelDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelDate, "flowx,cell 0 7,gapx 20,aligny top");
		
		comboBoxDayDay = new JComboBox();
		comboBoxDayDay.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBoxDayDay.setModel(new DefaultComboBoxModel(new String[] {"Day", "01 ", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxDayDay.setMaximumSize(new Dimension(60, 16));
		frame.getContentPane().add(comboBoxDayDay, "cell 0 7,gapx 10");
		
		comboBoxDayMonth = new JComboBox();
		comboBoxDayMonth.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBoxDayMonth.setModel(new DefaultComboBoxModel(new String[] {"Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		comboBoxDayMonth.setMaximumSize(new Dimension(60, 16));
		frame.getContentPane().add(comboBoxDayMonth, "cell 0 7,gapx 10");
		
		comboBoxDayYear = new JComboBox();
		comboBoxDayYear.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBoxDayYear.setModel(new DefaultComboBoxModel(new String[] {"Year", "2021", "2022", "2023", "2024", "2025", "2026"}));
		comboBoxDayYear.setMaximumSize(new Dimension(60, 16));
		frame.getContentPane().add(comboBoxDayYear, "cell 0 7,gapx 10");
		
		LabelWarningDay = new JLabel("  ");
		LabelWarningDay.setForeground(Color.RED);
		LabelWarningDay.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelWarningDay, "cell 0 7,gapx 30");
		
		// Number of hours 
		JLabel LabelHours = new JLabel("Number of hours:");
		LabelHours.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelHours, "flowx,cell 0 8,gapx 20");
		
		TFHours = new JTextField();
		TFHours.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(TFHours, "cell 0 8");
		TFHours.setColumns(10);
		
		// Spaces 
		JLabel LabelSpaces = new JLabel("Spaces:");
		LabelSpaces.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelSpaces, "flowx,cell 0 9,gapx 20");
		
		TFSpaces = new JTextField();
		TFSpaces.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(TFSpaces, "cell 0 9");
		TFSpaces.setColumns(10);
		
		// Start of enrollment period 
		LabelStartEnroll = new JLabel("Start of enrollment period:");
		LabelStartEnroll.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelStartEnroll, "flowx,cell 0 10,gapx 20,aligny top");
		
		comboBoxEnrollStartDay = new JComboBox();
		comboBoxEnrollStartDay.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBoxEnrollStartDay.setModel(new DefaultComboBoxModel(new String[] {"Day", "01 ", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxEnrollStartDay.setMaximumSize(new Dimension(60, 16));
		frame.getContentPane().add(comboBoxEnrollStartDay, "cell 0 10,gapx 10");
		
		comboBoxEnrollStartMonth = new JComboBox();
		comboBoxEnrollStartMonth.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBoxEnrollStartMonth.setModel(new DefaultComboBoxModel(new String[] {"Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		comboBoxEnrollStartMonth.setMaximumSize(new Dimension(60, 16));
		frame.getContentPane().add(comboBoxEnrollStartMonth, "cell 0 10,gapx 10");
		
		comboBoxEnrollStartYear = new JComboBox();
		comboBoxEnrollStartYear.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBoxEnrollStartYear.setModel(new DefaultComboBoxModel(new String[] {"Year", "2021", "2022", "2023", "2024", "2025", "2026"}));
		comboBoxEnrollStartYear.setMaximumSize(new Dimension(60, 16));
		frame.getContentPane().add(comboBoxEnrollStartYear, "cell 0 10,gapx 10");
	
		LabelWarningStartEnroll = new JLabel("");
		LabelWarningStartEnroll.setForeground(Color.RED);
		LabelWarningStartEnroll.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelWarningStartEnroll, "cell 0 11,gapx 160");
		
		// End of enrollment period
		JLabel LabelEndEnroll = new JLabel("End of enrollment period:");
		LabelEndEnroll.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelEndEnroll, "flowx,cell 0 12,gapx 20");
		
		comboBoxEnrollEndDay = new JComboBox();
		comboBoxEnrollEndDay.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBoxEnrollEndDay.setModel(new DefaultComboBoxModel(new String[] {"Day", "01 ", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxEnrollEndDay.setMaximumSize(new Dimension(60, 16));
		frame.getContentPane().add(comboBoxEnrollEndDay, "cell 0 12,gapx 10");
		
		comboBoxEnrollEndMonth = new JComboBox();
		comboBoxEnrollEndMonth.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBoxEnrollEndMonth.setModel(new DefaultComboBoxModel(new String[] {"Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		comboBoxEnrollEndMonth.setMaximumSize(new Dimension(60, 16));
		frame.getContentPane().add(comboBoxEnrollEndMonth, "cell 0 12,gapx 10");
		
		comboBoxEnrollEndYear = new JComboBox();
		comboBoxEnrollEndYear.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBoxEnrollEndYear.setModel(new DefaultComboBoxModel(new String[] {"Year", "2021", "2022", "2023", "2024", "2025", "2026"}));
		comboBoxEnrollEndYear.setMaximumSize(new Dimension(60, 16));
		frame.getContentPane().add(comboBoxEnrollEndYear, "cell 0 12,gapx 10");
		
		LabelWarningEndEnroll = new JLabel("");
		LabelWarningEndEnroll.setForeground(Color.RED);
		LabelWarningEndEnroll.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelWarningEndEnroll, "cell 0 13,gapx 155");
		
		// Create Button 
		BTNCreate = new JButton("Create formative action");
		BTNCreate.setFont(new Font("Tahoma", Font.BOLD, 11));
		frame.getContentPane().add(BTNCreate, "cell 0 14,alignx center");

		
	}

	//Getters and Setters to access from the controller (compact representation)
	public JFrame getFrame() { return this.frame; }
	public JButton getCreateBtn() {return this.BTNCreate;}
	public String getName() {return this.TFName.getText();}
	public String getObjectives() {return this.TFObjectives.getText();}
	public String getMainContents() {return this.TFContents.getText();}
	public String getTeacher() {return this.TFTeacher.getText();}
	public String getRemuneration() {return this.TFRemuneration.getText();}
	public String getLocation() {return this.TFLocation.getText();}
	public String getSpaces() {return this.TFSpaces.getText();}
	public String getDayDay() {return String.valueOf(this.comboBoxDayDay.getSelectedItem());}
	public String getDayMonth() {return String.valueOf(this.comboBoxDayMonth.getSelectedItem());}
	public String getDayYear() {return String.valueOf(this.comboBoxDayYear.getSelectedItem());}
	public String getNumberOfHours() {return this.TFHours.getText();}
	public String getEnrollStartDay() {return String.valueOf(this.comboBoxEnrollStartDay.getSelectedItem());}
	public String getEnrollStartMonth() {return String.valueOf(this.comboBoxEnrollStartMonth.getSelectedItem());}
	public String getEnrollStartYear() {return String.valueOf(this.comboBoxEnrollStartYear.getSelectedItem());}
	public String getEnrollEndDay() {return String.valueOf(this.comboBoxEnrollEndDay.getSelectedItem());}
	public String getEnrollEndMonth() {return String.valueOf(this.comboBoxEnrollEndMonth.getSelectedItem());}
	public String getEnrollEndYear() {return String.valueOf(this.comboBoxEnrollEndYear.getSelectedItem());}
	public void setWarningDay(String warning)  { this.LabelWarningDay.setText(warning); }
	public void setWarningEnrollmentPeriodStart(String warning)  { this.LabelWarningStartEnroll.setText(warning); }
	public void setWarningEnrollmentPeriodStart2(String warning)  { this.LabelWarningStartEnroll.setText(warning); }
	public void setWarningEnrollmentPeriodEnd(String warning)  { this.LabelWarningEndEnroll.setText(warning); }
	public void setWarningEnrollmentPeriodEnd2(String warning)  { this.LabelWarningEndEnroll.setText(warning); }
}
