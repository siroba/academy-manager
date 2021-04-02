package UserStory13573;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.table.TableModel;

import PL53.swing.DateTimeInput;
import PL53.swing.JDecimalField;
import PL53.swing.JIntField;
import PL53.util.DateTime;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class View {

	private JFrame frmCreateANew;
	private JTextField TFName, TFTeacher;
	private JIntField TFHours, TFSpaces;
	private JDecimalField TFRemuneration, TFFee;
	private JTextArea TFObjectives, TFContents;
	private JLabel LabelObjectives;
	private JButton BTNCreate;
	private JTextArea labelWarningStartEnroll;
	private JLabel labelWarningDay_1, labelWarningEndEnroll, labelWarningDay;
	private DateTimeInput enrollStart, enrollEnd, sessionStart;
	private JPanel panel;
	private JPanel sessionsPanel, feesPanel;
	private JTable table, tableFees;
	private JTextField TFLocation, TFGroup;
	private JButton btnAddSession;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JButton btnDeleteSession, btnAddFee, btnDeleteFee;
	private JCheckBox chckbxFree;

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Border blackline = BorderFactory.createLineBorder(Color.black);

		frmCreateANew = new JFrame();
		frmCreateANew.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		frmCreateANew.setTitle("Create a new Formative Action");
		frmCreateANew.setName("Courses");
		frmCreateANew.setBounds(0, 0, 1280, 502);
		frmCreateANew.setLocationRelativeTo(null);
		frmCreateANew.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCreateANew.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));

		panel = new JPanel();
		frmCreateANew.getContentPane().add(panel, "flowx,cell 0 0,grow");
		panel.setLayout(null);

		// Heading
		JLabel lblNewLabel = new JLabel("Plan a formative action");
		lblNewLabel.setBounds(115, 19, 180, 17);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		TFName = new JTextField();
		TFName.setBounds(50, 48, 273, 18);
		panel.add(TFName);
		TFName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		TFName.setColumns(10);

		// Name
		JLabel LabelName = new JLabel("Name:");
		LabelName.setBounds(10, 50, 37, 14);
		panel.add(LabelName);
		LabelName.setFont(new Font("Tahoma", Font.PLAIN, 11));

		// Objectives
		LabelObjectives = new JLabel("Objectives:");
		LabelObjectives.setBounds(10, 74, 63, 14);
		panel.add(LabelObjectives);
		LabelObjectives.setFont(new Font("Tahoma", Font.PLAIN, 11));

		// Main contents
		JLabel LabelContents = new JLabel("Main contents:");
		LabelContents.setBounds(210, 74, 83, 14);
		panel.add(LabelContents);
		LabelContents.setFont(new Font("Tahoma", Font.PLAIN, 11));

		TFSpaces = new JIntField(5);
		TFSpaces.setBounds(60, 382, 69, 18);
		panel.add(TFSpaces);
		TFSpaces.setFont(new Font("Tahoma", Font.PLAIN, 11));
		TFSpaces.setColumns(10);

		// Spaces
		JLabel LabelSpaces = new JLabel("Spaces:");
		LabelSpaces.setBounds(10, 383, 46, 14);
		panel.add(LabelSpaces);
		LabelSpaces.setFont(new Font("Tahoma", Font.PLAIN, 11));

		labelWarningStartEnroll = new JTextArea("");
		labelWarningStartEnroll.setWrapStyleWord(true);
		labelWarningStartEnroll.setLineWrap(true);
		labelWarningStartEnroll.setOpaque(false);
		labelWarningStartEnroll.setEditable(false);
		labelWarningStartEnroll.setFocusable(false);
		labelWarningStartEnroll.setBackground(UIManager.getColor("Label.background"));
		labelWarningStartEnroll.setFont(UIManager.getFont("Label.font"));
	    labelWarningStartEnroll.setBorder(UIManager.getBorder("Label.border"));
		labelWarningStartEnroll.setBounds(180, 175, 218, 28);
		panel.add(labelWarningStartEnroll);
		labelWarningStartEnroll.setForeground(Color.RED);
		labelWarningStartEnroll.setFont(new Font("Tahoma", Font.PLAIN, 11));

		labelWarningEndEnroll = new JLabel("");
		labelWarningEndEnroll.setBounds(180, 280, 202, 14);
		panel.add(labelWarningEndEnroll);
		labelWarningEndEnroll.setForeground(Color.RED);
		labelWarningEndEnroll.setFont(new Font("Tahoma", Font.PLAIN, 11));

		TFFee = new JDecimalField(6);
		TFFee.setBounds(180, 382, 46, 19);
		panel.add(TFFee);
		TFFee.setColumns(10);

//		// Fee
//		LabelFee = new JLabel("Fee:");
//		LabelFee.setBounds(150, 383, 24, 14);
//		panel.add(LabelFee);
//		LabelFee.setFont(new Font("Tahoma", Font.PLAIN, 11));

		// Create Button
		BTNCreate = new JButton("Create formative action");
		BTNCreate.setBounds(114, 420, 186, 24);
		panel.add(BTNCreate);
		BTNCreate.setFont(new Font("Tahoma", Font.BOLD, 11));

		// Start of enrollment period 
		JLabel LabelStartEnroll = new JLabel("Start of enrollment period:");
		LabelStartEnroll.setBounds(12, 181, 152, 14);
		panel.add(LabelStartEnroll);
		LabelStartEnroll.setFont(new Font("Dialog", Font.PLAIN, 11));

		enrollStart = new DateTimeInput();
		enrollStart.setBounds(12, 205, 378, 70);
		panel.add(enrollStart);
		enrollStart.getDatePanel().getYearsTextField().setBound(2000, 3000);
		enrollStart.getDatePanel().getYearsTextField().setDefaultValue(2021);
		enrollStart.getDatePanel().getDaysTextField().setBound(1, 31);
		enrollStart.getDatePanel().getMonthsTextField().setBound(1, 12);
		enrollStart.getDatePanel().getYearsTextField().setBound(2000, 3000);
		enrollStart.setBorder(blackline);

		// End of enrollment period 
		JLabel lblEndOfEnrollment = new JLabel("End of enrollment period:");
		lblEndOfEnrollment.setBounds(12, 283, 155, 15);
		panel.add(lblEndOfEnrollment);
		lblEndOfEnrollment.setFont(new Font("Dialog", Font.PLAIN, 11));

		enrollEnd = new DateTimeInput();
		enrollEnd.setBounds(12, 299, 378, 70);
		panel.add(enrollEnd);
		enrollEnd.getDatePanel().getYearsTextField().setBound(2000, 3000);
		enrollEnd.getDatePanel().getYearsTextField().setDefaultValue(2021);
		enrollEnd.getDatePanel().getDaysTextField().setBound(1, 31);
		enrollEnd.getDatePanel().getMonthsTextField().setBound(1, 12);
		enrollEnd.getDatePanel().getYearsTextField().setBound(2000, 3000);
		enrollEnd.setBorder(blackline);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 93, 188, 76);
		panel.add(scrollPane_1);

		// Objectives 
		TFObjectives = new JTextArea();
		TFObjectives.setWrapStyleWord(true);
		scrollPane_1.setViewportView(TFObjectives);
		TFObjectives.setLineWrap(true);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(210, 93, 188, 76);
		panel.add(scrollPane_2);

		// Main contents 
		TFContents = new JTextArea();
		TFContents.setWrapStyleWord(true);
		scrollPane_2.setViewportView(TFContents);
		TFContents.setLineWrap(true);

		// Seperator 
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		frmCreateANew.getContentPane().add(separator, "cell 0 0,growy");

		// Session panel
		sessionsPanel = new JPanel();
		frmCreateANew.getContentPane().add(sessionsPanel, "cell 0 0,grow");
		sessionsPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(7, 26, 402, 103);
		sessionsPanel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel LabelLocation = new JLabel("Location:");
		LabelLocation.setFont(new Font("Dialog", Font.PLAIN, 11));
		LabelLocation.setBounds(9, 201, 51, 14);
		sessionsPanel.add(LabelLocation);

		labelWarningDay = new JLabel("  ");
		labelWarningDay.setForeground(Color.RED);
		labelWarningDay.setFont(new Font("Dialog", Font.PLAIN, 11));
		labelWarningDay.setBounds(9, 201, 8, 14);
		sessionsPanel.add(labelWarningDay);

		// Date of session 
		JLabel LabelDate = new JLabel("Date of the session:");
		LabelDate.setFont(new Font("Dialog", Font.PLAIN, 11));
		LabelDate.setBounds(7, 231, 134, 14);
		sessionsPanel.add(LabelDate);

		// Duration of session 
		JLabel LabelHours = new JLabel("Number of hours:");
		LabelHours.setFont(new Font("Dialog", Font.PLAIN, 11));
		LabelHours.setBounds(257, 201, 101, 14);
		sessionsPanel.add(LabelHours);

		sessionStart = new DateTimeInput();
		sessionStart.setBorder(blackline);
		sessionStart.getDatePanel().getYearsTextField().setBound(2000, 3000);
		sessionStart.getDatePanel().getYearsTextField().setDefaultValue(2021);
		sessionStart.setBounds(7, 246, 378, 70);

		sessionsPanel.add(sessionStart);

		TFLocation = new JTextField();
		TFLocation.setBounds(78, 198, 166, 19);
		sessionsPanel.add(TFLocation);
		TFLocation.setColumns(10);

		TFHours = new JIntField(2);
		TFHours.setBound(0, 24);
		TFHours.setBounds(362, 198, 39, 19);
		sessionsPanel.add(TFHours);

		btnAddSession = new JButton("Add session");
		btnAddSession.setBounds(129, 420, 166, 25);
		sessionsPanel.add(btnAddSession);

		// Teacher
		JLabel LabelTeacher = new JLabel("Teacher:");
		LabelTeacher.setBounds(7, 328, 50, 14);
		sessionsPanel.add(LabelTeacher);
		LabelTeacher.setFont(new Font("Tahoma", Font.PLAIN, 11));

		TFTeacher = new JTextField();
		TFTeacher.setBounds(63, 328, 142, 18);
		sessionsPanel.add(TFTeacher);
		TFTeacher.setFont(new Font("Tahoma", Font.PLAIN, 11));
		TFTeacher.setColumns(10);

		TFRemuneration = new JDecimalField(4);
		TFRemuneration.setBound(0f, Float.MAX_VALUE);
		TFRemuneration.setBounds(307, 328, 51, 18);
		sessionsPanel.add(TFRemuneration);
		TFRemuneration.setFont(new Font("Tahoma", Font.PLAIN, 11));
		TFRemuneration.setColumns(10);

		// Remuneration
		JLabel LabelRemuneration = new JLabel("Remuneration:");
		LabelRemuneration.setBounds(217, 328, 83, 14);
		sessionsPanel.add(LabelRemuneration);
		LabelRemuneration.setFont(new Font("Tahoma", Font.PLAIN, 11));

		labelWarningDay_1 = new JLabel("");
		labelWarningDay_1.setFont(new Font("Dialog", Font.PLAIN, 11));
		labelWarningDay_1.setForeground(Color.RED);
		labelWarningDay_1.setBounds(140, 231, 229, 14);
		sessionsPanel.add(labelWarningDay_1);

		// Delete session 
		btnDeleteSession = new JButton("Delete session");
		btnDeleteSession.setEnabled(false);
		btnDeleteSession.setBounds(7, 138, 154, 25);
		sessionsPanel.add(btnDeleteSession);
		
		// Seperator 
		JSeparator separator2 = new JSeparator();
		separator2.setForeground(Color.BLACK);
		separator2.setOrientation(SwingConstants.VERTICAL);
		frmCreateANew.getContentPane().add(separator2, "cell 0 0,growy");
		
		// Fee panel
		feesPanel = new JPanel();
		frmCreateANew.getContentPane().add(feesPanel, "cell 0 0,grow");
		feesPanel.setLayout(null);
		
		// Label fees
		JLabel HeadingFee = new JLabel("Fees:");
		HeadingFee.setFont(new Font("Dialog", Font.BOLD, 12));
		HeadingFee.setBounds(9, 25, 51, 14);
		feesPanel.add(HeadingFee);
		
		// Free of charge option 
		chckbxFree = new JCheckBox("Free of charge");
		chckbxFree.setFont(new Font("Dialog", Font.PLAIN, 11));
		chckbxFree.setBounds(9, 60, 140, 14);
		feesPanel.add(chckbxFree);
		
		// Fee table 
		JScrollPane scrollPaneFees = new JScrollPane();
		scrollPaneFees.setBounds(7, 100, 400, 150);
		feesPanel.add(scrollPaneFees);

		tableFees = new JTable();
		scrollPaneFees.setViewportView(tableFees);
		
		// Group
		JLabel LabelGroup = new JLabel("Group:");
		LabelGroup.setBounds(10, 283, 46, 14);
		feesPanel.add(LabelGroup);
		LabelGroup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		TFGroup = new JTextField();
		TFGroup.setBounds(60, 282, 140, 18);
		feesPanel.add(TFGroup);
		TFGroup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		TFGroup.setColumns(10);
		
		// Fee 
		JLabel LabelFee = new JLabel("Fee:");
		LabelFee.setBounds(220, 283, 46, 14);
		feesPanel.add(LabelFee);
		LabelFee.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		TFFee = new JDecimalField(6);
		TFFee.setBounds(250, 282, 46, 19);
		feesPanel.add(TFFee);
		TFFee.setColumns(10);
		
		// Add fee 
		btnAddFee = new JButton("Add fee");
		btnAddFee.setBounds(30, 330, 154, 25);
		feesPanel.add(btnAddFee);
				
		// Delete fee 
		btnDeleteFee = new JButton("Delete fee");
		btnDeleteFee.setEnabled(false);
		btnDeleteFee.setBounds(240, 330, 154, 25);
		feesPanel.add(btnDeleteFee);
		
	}

	// Getters and Setters to access from the controller (compact representation)
	public JFrame getFrame() {
		return this.frmCreateANew;
	}

	public JButton getCreateBtn() {
		return this.BTNCreate;
	}

	public String getName() {
		return this.TFName.getText();
	}

	public String getObjectives() {
		return this.TFObjectives.getText();
	}

	public String getMainContents() {
		return this.TFContents.getText();
	}

	public String getTeacher() {
		return this.TFTeacher.getText();
	}

	public float getRemuneration() {
		return this.TFRemuneration.getValue();
	}

	public String getLocation() {
		return this.TFLocation.getText();
	}

	public int getSpaces() {
		return this.TFSpaces.getValue();
	}

	public DateTime getSessionDatetime() {
		return sessionStart.getDateTime();
	}

	public int getNumberOfHours() {
		return this.TFHours.getValue();
	}

	public DateTime getEnrollStart() {
		return enrollStart.getDateTime();
	}

	public DateTime getEnrollEnd() {
		return enrollEnd.getDateTime();
	}

//	public float getFee() {
//		return this.TFFee.getValue();
//	}

	public void setWarningDay(String warning) {
		this.labelWarningDay_1.setText(warning);
	}

	public void setWarningEnrollmentPeriodStart(String warning) {
		this.labelWarningStartEnroll.setText(warning);
	}

	public void setWarningEnrollmentPeriodStart2(String warning) {
		this.labelWarningStartEnroll.setText(warning);
	}

	public void setWarningEnrollmentPeriodEnd(String warning) {
		this.labelWarningEndEnroll.setText(warning);
	}

	public void setWarningEnrollmentPeriodEnd2(String warning) {
		this.labelWarningEndEnroll.setText(warning);
	}

	public JButton getBtnAddSession() {
		return btnAddSession;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(TableModel tm) {
		table.setModel(tm);
	}

	public JButton getBtnDeleteSession() {
		return btnDeleteSession;
	}

	public void hideWarningDay() {
		this.labelWarningDay_1.setText("");
	}

	public void hideWarningEnrollmentPeriodStart() {
		this.labelWarningStartEnroll.setText("");
	}

	public void hideWarningEnrollmentPeriodEnd() {
		this.labelWarningEndEnroll.setText("");
	}
	
	public JTable getTableFees() {
		return tableFees;
	}

	public void setTableFees(TableModel tm) {
		tableFees.setModel(tm);
	}
	
	public JCheckBox getFree() {
		return this.chckbxFree;
	}
	
	public String getGroup() {
		return this.TFGroup.getText();
	}
	
	public void setGroup(String group) {
		this.TFGroup.setText(group);
	}
	
	public float getFee() {
		return this.TFFee.getValue();
	}
	
	public void setFee(float fee) {
		this.TFFee.setText(fee);
	}
	
	public JButton getBtnAddFee() {
		return this.btnAddFee;
	}
	
	public JButton getBtnDeleteFee() {
		return this.btnDeleteFee;
	}
}
