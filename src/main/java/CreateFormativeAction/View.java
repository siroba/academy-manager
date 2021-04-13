package CreateFormativeAction;

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

public class View {

	private JFrame frmCreateANew;
	private JTextField TFName, TFTeacher;
	private JIntField TFHours, TFSpaces;
	private JDecimalField TFRemuneration, TFFee;
	private JTextArea TFObjectives, TFContents;
	private JLabel LabelObjectives;
	private JButton BTNCreate;
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
		frmCreateANew.setBounds(0, 0, 1500, 540);
		frmCreateANew.setLocationRelativeTo(null);
		frmCreateANew.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCreateANew.getContentPane().setLayout(new MigLayout("", "[grow]", "[][][grow][][]"));

		// Heading
		JLabel lblNewLabel = new JLabel("Plan a formative action");
		lblNewLabel.setBounds(155, 19, 180, 17);
		frmCreateANew.getContentPane().add(lblNewLabel, "cell 0 0, alignx center");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				
		panel = new JPanel();
		frmCreateANew.getContentPane().add(panel, "flowx,cell 0 2,grow");
		panel.setLayout(null);

		// General Infos - Heading 
		JLabel HeadingGeneralInfos = new JLabel("General information:");
		HeadingGeneralInfos.setFont(new Font("Dialog", Font.BOLD, 12));
		HeadingGeneralInfos.setBounds(9, 15, 161, 14);
		panel.add(HeadingGeneralInfos);
				
		// Name
		JLabel LabelName = new JLabel("Name:");
		LabelName.setBounds(10, 50, 37, 14);
		panel.add(LabelName);
		LabelName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		TFName = new JTextField();
		TFName.setBounds(50, 48, 273, 18);
		panel.add(TFName);
		TFName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		TFName.setColumns(10);

		// Objectives
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 93, 208, 76);
		panel.add(scrollPane_1);

		LabelObjectives = new JLabel("Objectives:");
		LabelObjectives.setBounds(10, 74, 123, 14);
		panel.add(LabelObjectives);
		LabelObjectives.setFont(new Font("Tahoma", Font.PLAIN, 11));

		TFObjectives = new JTextArea();
		TFObjectives.setWrapStyleWord(true);
		scrollPane_1.setViewportView(TFObjectives);
		TFObjectives.setLineWrap(true);

		// Main contents
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(260, 93, 208, 76);
		panel.add(scrollPane_2);
		
		JLabel LabelContents = new JLabel("Main contents:");
		LabelContents.setBounds(260, 74, 123, 14);
		panel.add(LabelContents);
		LabelContents.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		TFContents = new JTextArea();
		TFContents.setWrapStyleWord(true);
		scrollPane_2.setViewportView(TFContents);
		TFContents.setLineWrap(true);

		// Start of enrollment period 
		JLabel LabelStartEnroll = new JLabel("Start of enrollment period:");
		LabelStartEnroll.setBounds(12, 181, 152, 14);
		panel.add(LabelStartEnroll);
		LabelStartEnroll.setFont(new Font("Dialog", Font.PLAIN, 11));

		enrollStart = new DateTimeInput();
		enrollStart.setBounds(12, 205, 455, 70);
		panel.add(enrollStart);
		enrollStart.getDatePanel().getYearsTextField().setBound(2000, 3000);
		enrollStart.getDatePanel().getYearsTextField().setDefaultValue(2021);
		enrollStart.getDatePanel().getDaysTextField().setBound(1, 31);
		enrollStart.getDatePanel().getMonthsTextField().setBound(1, 12);
		enrollStart.setBorder(blackline);

		// End of enrollment period 
		JLabel lblEndOfEnrollment = new JLabel("End of enrollment period:");
		lblEndOfEnrollment.setBounds(12, 283, 155, 15);
		panel.add(lblEndOfEnrollment);
		lblEndOfEnrollment.setFont(new Font("Dialog", Font.PLAIN, 11));

		enrollEnd = new DateTimeInput();
		enrollEnd.setBounds(12, 299, 455, 70);
		panel.add(enrollEnd);
		enrollEnd.getDatePanel().getYearsTextField().setBound(2000, 3000);
		enrollEnd.getDatePanel().getYearsTextField().setDefaultValue(2021);
		enrollEnd.getDatePanel().getDaysTextField().setBound(1, 31);
		enrollEnd.getDatePanel().getMonthsTextField().setBound(1, 12);
		enrollEnd.setBorder(blackline);		

		// Spaces
		JLabel LabelSpaces = new JLabel("Spaces:");
		LabelSpaces.setBounds(10, 383, 46, 14);
		panel.add(LabelSpaces);
		LabelSpaces.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		TFSpaces = new JIntField(5);
		TFSpaces.setBounds(60, 382, 69, 18);
		panel.add(TFSpaces);
		TFSpaces.setFont(new Font("Tahoma", Font.PLAIN, 11));
		TFSpaces.setColumns(10);

		// Separator 
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		frmCreateANew.getContentPane().add(separator, "cell 0 2,growy");

		// Sessions
		sessionsPanel = new JPanel();
		frmCreateANew.getContentPane().add(sessionsPanel, "cell 0 2,grow");
		sessionsPanel.setLayout(null);

		// Sessions - Heading 
		JLabel HeadingSessions = new JLabel("Sessions:");
		HeadingSessions.setFont(new Font("Dialog", Font.BOLD, 12));
		HeadingSessions.setBounds(9, 15, 71, 14);
		sessionsPanel.add(HeadingSessions);
		
		// Sessions - Table 
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(7, 56, 472, 103);
		sessionsPanel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		// Session - Location 
		JLabel LabelLocation = new JLabel("Location:");
		LabelLocation.setFont(new Font("Dialog", Font.PLAIN, 11));
		LabelLocation.setBounds(9, 231, 51, 14);
		sessionsPanel.add(LabelLocation);
		
		TFLocation = new JTextField();
		TFLocation.setBounds(78, 228, 166, 19);
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
		sessionStart.setBounds(7, 276, 455, 70);
		sessionsPanel.add(sessionStart);

		// Session - Duration 
		JLabel LabelHours = new JLabel("Number of hours:");
		LabelHours.setFont(new Font("Dialog", Font.PLAIN, 11));
		LabelHours.setBounds(257, 231, 101, 14);
		sessionsPanel.add(LabelHours);

		TFHours = new JIntField(2);
		TFHours.setBound(0, 24);
		TFHours.setBounds(362, 228, 39, 19);
		sessionsPanel.add(TFHours);
		
		// Teacher
		JLabel LabelTeacher = new JLabel("Teacher:");
		LabelTeacher.setBounds(7, 358, 50, 14);
		sessionsPanel.add(LabelTeacher);
		LabelTeacher.setFont(new Font("Tahoma", Font.PLAIN, 11));

		TFTeacher = new JTextField();
		TFTeacher.setBounds(63, 358, 142, 18);
		sessionsPanel.add(TFTeacher);
		TFTeacher.setFont(new Font("Tahoma", Font.PLAIN, 11));
		TFTeacher.setColumns(10);

		// Remuneration
		JLabel LabelRemuneration = new JLabel("Remuneration:");
		LabelRemuneration.setBounds(217, 358, 83, 14);
		sessionsPanel.add(LabelRemuneration);
		LabelRemuneration.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		TFRemuneration = new JDecimalField(4);
		TFRemuneration.setBound(0f, Float.MAX_VALUE);
		TFRemuneration.setBounds(307, 358, 51, 18);
		sessionsPanel.add(TFRemuneration);
		TFRemuneration.setFont(new Font("Tahoma", Font.PLAIN, 11));
		TFRemuneration.setColumns(10);

		// Session - Button to add session 
		btnAddSession = new JButton("Add session");
		btnAddSession.setBounds(159, 390, 166, 25);
		sessionsPanel.add(btnAddSession);

		// Session - Button to delete session 
		btnDeleteSession = new JButton("Delete session");
		btnDeleteSession.setEnabled(false);
		btnDeleteSession.setBounds(7, 168, 154, 25);
		sessionsPanel.add(btnDeleteSession);
		
		// Separator 
		JSeparator separator2 = new JSeparator();
		separator2.setForeground(Color.BLACK);
		separator2.setOrientation(SwingConstants.VERTICAL);
		frmCreateANew.getContentPane().add(separator2, "cell 0 2,growy");
		
		// Fees
		feesPanel = new JPanel();
		frmCreateANew.getContentPane().add(feesPanel, "cell 0 2,grow");
		feesPanel.setLayout(null);
		
		// Fees - Heading 
		JLabel HeadingFee = new JLabel("Fees:");
		HeadingFee.setFont(new Font("Dialog", Font.BOLD, 12));
		HeadingFee.setBounds(9, 15, 51, 14);
		feesPanel.add(HeadingFee);
		
		// Fees - Free of charge option 
		chckbxFree = new JCheckBox("Free of charge");
		chckbxFree.setFont(new Font("Dialog", Font.PLAIN, 11));
		chckbxFree.setBounds(9, 60, 140, 14);
		feesPanel.add(chckbxFree);
		
		// Fees - Table 
		JScrollPane scrollPaneFees = new JScrollPane();
		scrollPaneFees.setBounds(7, 100, 470, 150);
		feesPanel.add(scrollPaneFees);

		tableFees = new JTable();
		tableFees.setDefaultEditor(Object.class, null);
		scrollPaneFees.setViewportView(tableFees);
		
		// Fees - Button to Delete fee 
		btnDeleteFee = new JButton("Delete fee");
		btnDeleteFee.setEnabled(false);
		btnDeleteFee.setBounds(7, 260, 154, 25);
		feesPanel.add(btnDeleteFee);
		
		// Fees - Group
		JLabel LabelGroup = new JLabel("Group:");
		LabelGroup.setBounds(10, 313, 46, 14);
		feesPanel.add(LabelGroup);
		LabelGroup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		TFGroup = new JTextField();
		TFGroup.setBounds(60, 312, 200, 18);
		feesPanel.add(TFGroup);
		TFGroup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		TFGroup.setColumns(10);
		
		// Fees - Amount 
		JLabel LabelFee = new JLabel("Fee:");
		LabelFee.setBounds(280, 313, 46, 14);
		feesPanel.add(LabelFee);
		LabelFee.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		TFFee = new JDecimalField(6);
		TFFee.setBounds(310, 312, 46, 19);
		feesPanel.add(TFFee);
		TFFee.setColumns(10);
		
		// Fees - Button to Add fee 
		btnAddFee = new JButton("Add fee");
		btnAddFee.setBounds(159, 350, 174, 25);
		feesPanel.add(btnAddFee);
		
		// Separator 
		JSeparator separator3 = new JSeparator();
		separator3.setForeground(Color.BLACK);
		separator3.setOrientation(SwingConstants.HORIZONTAL);
		frmCreateANew.getContentPane().add(separator3, "cell 0 3, growx");
		
		JSeparator separator4 = new JSeparator();
		separator4.setForeground(Color.BLACK);
		separator4.setOrientation(SwingConstants.HORIZONTAL);
		frmCreateANew.getContentPane().add(separator4, "cell 0 1, growx, span ");
		
		
		// Create Button
		BTNCreate = new JButton("Create formative action");
		frmCreateANew.getContentPane().add(BTNCreate, "cell 0 4,alignx center,gapy 10");
		BTNCreate.setFont(new Font("Tahoma", Font.BOLD, 12));
				
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
}
