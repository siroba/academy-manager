package CreateFormativeAction;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.Font;
import java.util.List;

import javax.swing.border.Border;
import javax.swing.table.TableModel;

import Entities.Teacher;
import PL53.swing.DateTimeInput;
import PL53.swing.JDecimalField;
import PL53.swing.JIntField;
import PL53.util.DateTime;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class View {

	private JFrame frmCreateANew;
	private JTextField TFName;
	private JIntField TFHours, TFSpaces;
	private JDecimalField TFFee;
	private JTextArea TFObjectives, TFContents;
	private JLabel LabelObjectives;
	private JButton BTNCreate;
	private DateTimeInput enrollStart, enrollEnd, sessionStart;
	private JPanel panel;
	private JPanel sessionsPanel, feesPanel;
	private JTable table, tableFees;
	private JTextField TFLocation, TFGroup;
	private JButton btnAddSession;
	private JButton btnRemoveTeacher;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JButton btnDeleteSession, btnAddFee, btnDeleteFee;
	private JCheckBox chckbxFree;
	private JLabel LabelRemuneration;
	private JDecimalField TFRemuneration;
	private JTable teachersTable;
	private JComboBox<String> teachersComboBox;
	private JButton btnAddTeacher;

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
		frmCreateANew.setBounds(0, 0, 1461, 511);
		frmCreateANew.setLocationRelativeTo(null);
		frmCreateANew.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCreateANew.getContentPane().setLayout(null);

		// Heading
		JLabel lblNewLabel = new JLabel("Plan a formative action");
		lblNewLabel.setBounds(644, 7, 157, 17);
		frmCreateANew.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				
		panel = new JPanel();
		panel.setBounds(7, 35, 407, 381);
		frmCreateANew.getContentPane().add(panel);
		panel.setLayout(null);

		// General Infos - Heading 
		JLabel HeadingGeneralInfos = new JLabel("General information:");
		HeadingGeneralInfos.setFont(new Font("Dialog", Font.BOLD, 12));
		HeadingGeneralInfos.setBounds(9, 15, 161, 14);
		panel.add(HeadingGeneralInfos);
				
		// Name
		JLabel LabelName = new JLabel("Name:");
		LabelName.setBounds(9, 40, 37, 14);
		panel.add(LabelName);
		LabelName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		TFName = new JTextField();
		TFName.setBounds(9, 54, 309, 18);
		panel.add(TFName);
		TFName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		TFName.setColumns(10);

		// Objectives
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(9, 102, 190, 76);
		panel.add(scrollPane_1);

		LabelObjectives = new JLabel("Objectives:");
		LabelObjectives.setBounds(9, 83, 123, 14);
		panel.add(LabelObjectives);
		LabelObjectives.setFont(new Font("Tahoma", Font.PLAIN, 11));

		TFObjectives = new JTextArea();
		TFObjectives.setWrapStyleWord(true);
		scrollPane_1.setViewportView(TFObjectives);
		TFObjectives.setLineWrap(true);

		// Main contents
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(209, 102, 190, 76);
		panel.add(scrollPane_2);
		
		JLabel LabelContents = new JLabel("Main contents:");
		LabelContents.setBounds(209, 83, 123, 14);
		panel.add(LabelContents);
		LabelContents.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		TFContents = new JTextArea();
		TFContents.setWrapStyleWord(true);
		scrollPane_2.setViewportView(TFContents);
		TFContents.setLineWrap(true);

		// Start of enrollment period 
		JLabel LabelStartEnroll = new JLabel("Start of enrollment period:");
		LabelStartEnroll.setBounds(9, 181, 152, 14);
		panel.add(LabelStartEnroll);
		LabelStartEnroll.setFont(new Font("Dialog", Font.PLAIN, 11));

		enrollStart = new DateTimeInput();
		enrollStart.setBounds(9, 205, 388, 70);
		panel.add(enrollStart);
		enrollStart.getDatePanel().getYearsTextField().setBound(2000, 3000);
		enrollStart.getDatePanel().getYearsTextField().setDefaultValue(2021);
		enrollStart.getDatePanel().getDaysTextField().setBound(1, 31);
		enrollStart.getDatePanel().getMonthsTextField().setBound(1, 12);
		enrollStart.setBorder(blackline);

		// End of enrollment period 
		JLabel lblEndOfEnrollment = new JLabel("End of enrollment period:");
		lblEndOfEnrollment.setBounds(9, 283, 155, 15);
		panel.add(lblEndOfEnrollment);
		lblEndOfEnrollment.setFont(new Font("Dialog", Font.PLAIN, 11));

		enrollEnd = new DateTimeInput();
		enrollEnd.setBounds(9, 299, 388, 70);
		panel.add(enrollEnd);
		enrollEnd.getDatePanel().getYearsTextField().setBound(2000, 3000);
		enrollEnd.getDatePanel().getYearsTextField().setDefaultValue(2021);
		enrollEnd.getDatePanel().getDaysTextField().setBound(1, 31);
		enrollEnd.getDatePanel().getMonthsTextField().setBound(1, 12);
		enrollEnd.setBorder(blackline);		

		// Spaces
		JLabel LabelSpaces = new JLabel("Total places:");
		LabelSpaces.setBounds(330, 40, 86, 14);
		panel.add(LabelSpaces);
		LabelSpaces.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		TFSpaces = new JIntField(5);
		TFSpaces.setBounds(330, 54, 69, 18);
		panel.add(TFSpaces);
		TFSpaces.setFont(new Font("Tahoma", Font.PLAIN, 11));
		TFSpaces.setColumns(10);

		// Separator 
		JSeparator separator = new JSeparator();
		separator.setBounds(424, 35, 2, 380);
		separator.setForeground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		frmCreateANew.getContentPane().add(separator);

		// Sessions
		sessionsPanel = new JPanel();
		sessionsPanel.setBounds(430, 35, 377, 381);
		frmCreateANew.getContentPane().add(sessionsPanel);
		sessionsPanel.setLayout(null);

		// Sessions - Heading 
		JLabel HeadingSessions = new JLabel("Sessions:");
		HeadingSessions.setFont(new Font("Dialog", Font.BOLD, 12));
		HeadingSessions.setBounds(7, 15, 71, 14);
		sessionsPanel.add(HeadingSessions);
		
		// Sessions - Table 
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(7, 39, 364, 103);
		sessionsPanel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		// Session - Location 
		JLabel LabelLocation = new JLabel("Location:");
		LabelLocation.setFont(new Font("Dialog", Font.PLAIN, 11));
		LabelLocation.setBounds(7, 204, 51, 14);
		sessionsPanel.add(LabelLocation);
		
		TFLocation = new JTextField();
		TFLocation.setBounds(7, 226, 251, 19);
		sessionsPanel.add(TFLocation);
		TFLocation.setColumns(10);

		// Session - Date 
		JLabel LabelDate = new JLabel("Date of the session:");
		LabelDate.setFont(new Font("Dialog", Font.PLAIN, 11));
		LabelDate.setBounds(7, 261, 134, 14);
		sessionsPanel.add(LabelDate);
		
		sessionStart = new DateTimeInput();
		sessionStart.setBorder(blackline);
		sessionStart.getDatePanel().getYearsTextField().setBound(2000, 3000);
		sessionStart.getDatePanel().getYearsTextField().setDefaultValue(2021);
		sessionStart.setBounds(7, 276, 364, 70);
		sessionsPanel.add(sessionStart);

		// Session - Duration 
		JLabel LabelHours = new JLabel("Number of hours:");
		LabelHours.setFont(new Font("Dialog", Font.PLAIN, 11));
		LabelHours.setBounds(270, 204, 101, 14);
		sessionsPanel.add(LabelHours);

		TFHours = new JIntField(2);
		TFHours.setBound(1, Integer.MAX_VALUE);
		TFHours.setDefaultValue(1);
		TFHours.setBounds(270, 226, 101, 19);
		sessionsPanel.add(TFHours);

		// Session - Button to add session 
		btnAddSession = new JButton("Add session");
		btnAddSession.setBounds(106, 354, 166, 25);
		sessionsPanel.add(btnAddSession);

		// Session - Button to delete session 
		btnDeleteSession = new JButton("Delete session");
		btnDeleteSession.setEnabled(false);
		btnDeleteSession.setBounds(7, 153, 154, 25);
		sessionsPanel.add(btnDeleteSession);
		
		// Separator 
		JSeparator separator2 = new JSeparator();
		separator2.setBounds(817, 35, 2, 380);
		separator2.setForeground(Color.BLACK);
		separator2.setOrientation(SwingConstants.VERTICAL);
		frmCreateANew.getContentPane().add(separator2);
		
		// Fees
		feesPanel = new JPanel();
		feesPanel.setBounds(823, 35, 303, 381);
		frmCreateANew.getContentPane().add(feesPanel);
		feesPanel.setLayout(null);
		
		// Fees - Heading 
		JLabel HeadingFee = new JLabel("Fees:");
		HeadingFee.setFont(new Font("Dialog", Font.BOLD, 12));
		HeadingFee.setBounds(9, 15, 51, 14);
		feesPanel.add(HeadingFee);
		
		// Fees - Free of charge option 
		chckbxFree = new JCheckBox("Free of charge");
		chckbxFree.setFont(new Font("Dialog", Font.PLAIN, 11));
		chckbxFree.setBounds(9, 248, 140, 14);
		feesPanel.add(chckbxFree);
		
		// Fees - Table 
		JScrollPane scrollPaneFees = new JScrollPane();
		scrollPaneFees.setBounds(7, 39, 284, 150);
		feesPanel.add(scrollPaneFees);

		tableFees = new JTable();
		tableFees.setDefaultEditor(Object.class, null);
		tableFees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneFees.setViewportView(tableFees);
		
		// Fees - Button to Delete fee 
		btnDeleteFee = new JButton("Delete fee");
		btnDeleteFee.setEnabled(false);
		btnDeleteFee.setBounds(9, 200, 154, 25);
		feesPanel.add(btnDeleteFee);
		
		// Fees - Group
		JLabel LabelGroup = new JLabel("Group:");
		LabelGroup.setBounds(9, 296, 46, 14);
		feesPanel.add(LabelGroup);
		LabelGroup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		TFGroup = new JTextField();
		TFGroup.setBounds(9, 311, 200, 18);
		feesPanel.add(TFGroup);
		TFGroup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		TFGroup.setColumns(10);
		
		// Fees - Amount 
		JLabel LabelFee = new JLabel("Fee:");
		LabelFee.setBounds(219, 296, 46, 14);
		feesPanel.add(LabelFee);
		LabelFee.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		TFFee = new JDecimalField(6);
		TFFee.setBounds(219, 310, 46, 19);
		TFFee.setBound((float) 0, Float.MAX_VALUE);
		feesPanel.add(TFFee);
		TFFee.setColumns(10);
		
		// Fees - Button to Add fee 
		btnAddFee = new JButton("Add fee");
		btnAddFee.setBounds(62, 354, 174, 25);
		feesPanel.add(btnAddFee);
		
		// Separator 
		JSeparator separator3 = new JSeparator();
		separator3.setBounds(7, 425, 1430, 2);
		separator3.setForeground(Color.BLACK);
		separator3.setOrientation(SwingConstants.HORIZONTAL);
		frmCreateANew.getContentPane().add(separator3);
		
		JSeparator separator4 = new JSeparator();
		separator4.setBounds(7, 28, 1430, 2);
		separator4.setForeground(Color.BLACK);
		separator4.setOrientation(SwingConstants.HORIZONTAL);
		frmCreateANew.getContentPane().add(separator4);
		
		
		// Create Button
		BTNCreate = new JButton("Create formative action");
		BTNCreate.setBounds(606, 435, 233, 23);
		frmCreateANew.getContentPane().add(BTNCreate);
		BTNCreate.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel teachersPanel = new JPanel();
		teachersPanel.setLayout(null);
		teachersPanel.setBounds(1142, 34, 303, 382);
		frmCreateANew.getContentPane().add(teachersPanel);
		
		JLabel lblTeachers = new JLabel("Teachers:");
		lblTeachers.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTeachers.setBounds(9, 15, 79, 14);
		teachersPanel.add(lblTeachers);
		
		JScrollPane scrollPaneTeachers = new JScrollPane();
		scrollPaneTeachers.setBounds(9, 40, 284, 150);
		teachersPanel.add(scrollPaneTeachers);
		
		teachersTable = new JTable();
		teachersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneTeachers.setViewportView(teachersTable);
		
		btnRemoveTeacher = new JButton("Remove teacher");
		btnRemoveTeacher.setEnabled(false);
		btnRemoveTeacher.setBounds(9, 201, 154, 25);
		teachersPanel.add(btnRemoveTeacher);
		
		JLabel LabelGroup_1 = new JLabel("Teacher:");
		LabelGroup_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		LabelGroup_1.setBounds(9, 287, 79, 14);
		teachersPanel.add(LabelGroup_1);
		
		btnAddTeacher = new JButton("Add teacher");
		btnAddTeacher.setBounds(62, 355, 174, 25);
		teachersPanel.add(btnAddTeacher);
		
		teachersComboBox = new JComboBox<String>();
		
		teachersComboBox.setBounds(9, 305, 186, 22);
		teachersPanel.add(teachersComboBox);
		
		TFRemuneration = new JDecimalField(4);
		TFRemuneration.setBounds(210, 307, 51, 18);
		teachersPanel.add(TFRemuneration);
		
		LabelRemuneration = new JLabel("Remuneration:");
		LabelRemuneration.setBounds(210, 287, 83, 14);
		teachersPanel.add(LabelRemuneration);
		LabelRemuneration.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JSeparator separator2_1 = new JSeparator();
		separator2_1.setOrientation(SwingConstants.VERTICAL);
		separator2_1.setForeground(Color.BLACK);
		separator2_1.setBounds(1136, 34, 2, 380);
		frmCreateANew.getContentPane().add(separator2_1);
				
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

	public float getRemuneration() {
		return this.TFRemuneration.getValue();
	}

	public String getLocation() {
		return this.TFLocation.getText();
	}

	public int getSpaces() {
		return this.TFSpaces.getValue();
	}

	public DateTimeInput getEnrollStartDateTimeInput() {
		return enrollStart;
	}
	
	public DateTimeInput getEnrollEndDateTimeInput() {
		return enrollEnd;
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
	
	public void setAvailableTeachers(List<Teacher> availableTeachers) {
		this.teachersComboBox.removeAllItems();
		
		for(Teacher t: availableTeachers) {
			addAvailableTeacher(t);
		}
	}
	
	public void addAvailableTeacher(Teacher t) {
		this.teachersComboBox.addItem(t.getName());
	}
	
	public int getSelectedAvailableTeacher() {
		return this.teachersComboBox.getSelectedIndex();
	}
	
	public void setTableTeachers(TableModel tm) {
		this.teachersTable.setModel(tm);
	}
	
	public int getSelectedSelectedTeacher() {
		return this.teachersTable.getSelectedRow();
	}
	
	protected JButton getBtnAddTeacher() {
		return btnAddTeacher;
	}

	public void resetRemuneration() {
		this.TFRemuneration.setText(0.0f);
	}

	public JButton getBtnRemoveTeacher() {
		return btnRemoveTeacher;
	}

	public Component getTableTeachers() {
		return this.teachersTable;
	}
	
	
}
