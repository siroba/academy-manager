package ManageTeachers;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.Color;


public class View extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, RemovePanel, AddPanel;
	public JScrollPane teachersPanel;
	private JTable tableTeachers;
	private JButton btnAdd, btnRemove, btnUpdate;
	private JTextField teacherName, teacherSurname, teacherPhone, teacherEmail, teacherFiscalNumber, teacherPhoneUpdate, teacherEmailUpdate, teacherFiscalNumberUpdate;
	private JLabel lblTeacherNameUpdate;

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setTitle("Manage Teachers");
		setBounds(100, 100, 1400, 350);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Heading 
		JLabel LabelHeading = new JLabel("Add and Remove Teachers");
		LabelHeading.setBounds(579, 12, 264, 10);
		LabelHeading.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(LabelHeading, "aligny center, height 50!");

		// Remove A TEACHER 
		RemovePanel = new JPanel();
		RemovePanel.setBounds(12, 25, 967, 279);
		contentPane.add(RemovePanel);
		RemovePanel.setLayout(null);
		
		JLabel lblRemoveTeacher = new JLabel("List of teachers:");
		lblRemoveTeacher.setBounds(12, 18, 238, 15);
		RemovePanel.add(lblRemoveTeacher);

		// Scroll pane
		teachersPanel = new JScrollPane();
		teachersPanel.setBounds(12, 49, 620, 176);
		RemovePanel.add(teachersPanel);

		// Teacher table 
		tableTeachers = new JTable();
		tableTeachers.setBackground(new Color(255, 255, 255));
		tableTeachers.setDefaultEditor(Object.class, null); //readonly
		teachersPanel.setViewportView(tableTeachers);
		tableTeachers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Remove button
		btnRemove = new JButton("REMOVE TEACHER");
		btnRemove.setEnabled(false);
		btnRemove.setBounds(202, 237, 235, 25);
		RemovePanel.add(btnRemove);
		
		// UPDATE INFORMATION
		// Selected teacher 
		lblTeacherNameUpdate = new JLabel("Selected teacher:");
		lblTeacherNameUpdate.setBounds(651, 51, 316, 15);
		RemovePanel.add(lblTeacherNameUpdate);
		
		// Phone
		JLabel lblPhoneUpdate = new JLabel("Phone:");
		lblPhoneUpdate.setBounds(651, 84, 70, 15);
		RemovePanel.add(lblPhoneUpdate);
		
		teacherPhoneUpdate = new JTextField();
		teacherPhoneUpdate.setColumns(10);
		teacherPhoneUpdate.setBounds(710, 82, 179, 19);
		RemovePanel.add(teacherPhoneUpdate);
		
		// Email
		JLabel lblEmailUpdate = new JLabel("Email:");
		lblEmailUpdate.setBounds(651, 117, 70, 15);
		RemovePanel.add(lblEmailUpdate);

		teacherEmailUpdate = new JTextField();
		teacherEmailUpdate.setColumns(10);
		teacherEmailUpdate.setBounds(705, 115, 179, 19);
		RemovePanel.add(teacherEmailUpdate);
		
		// Fiscal number
		JLabel lblFiscalNumberUpdate = new JLabel("Fiscal Number:");
		lblFiscalNumberUpdate.setBounds(651, 150, 117, 15);
		RemovePanel.add(lblFiscalNumberUpdate);
		
		teacherFiscalNumberUpdate = new JTextField();
		teacherFiscalNumberUpdate.setColumns(10);
		teacherFiscalNumberUpdate.setBounds(768, 148, 179, 19);
		RemovePanel.add(teacherFiscalNumberUpdate);
		
		// Update button
		btnUpdate = new JButton("UPDATE INFORMATION");
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(684, 189, 235, 25);
		RemovePanel.add(btnUpdate);
		
		// Add  A TEACHER
		AddPanel = new JPanel();
		AddPanel.setBounds(1039, 22, 347, 282);
		contentPane.add(AddPanel);
		AddPanel.setLayout(null);
		
		JLabel lblAddTeacher = new JLabel("Add teacher:");
		lblAddTeacher.setBounds(12, 18, 238, 15);
		AddPanel.add(lblAddTeacher);

		// Name 
		JLabel lblName = new JLabel("*Name:");
		lblName.setBounds(12, 51, 70, 15);
		AddPanel.add(lblName);

		teacherName = new JTextField();
		teacherName.setBounds(70, 49, 199, 19);
		AddPanel.add(teacherName);
		teacherName.setColumns(10);

		// Surname 
		JLabel lblSurname = new JLabel("*Surname:");
		lblSurname.setBounds(12, 84, 84, 15);
		AddPanel.add(lblSurname);

		teacherSurname = new JTextField();
		teacherSurname.setBounds(93, 82, 179, 19);
		AddPanel.add(teacherSurname);
		teacherSurname.setColumns(10);
		
		// Phone 
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(12, 114, 70, 15);
		AddPanel.add(lblPhone);

		teacherPhone = new JTextField();
		teacherPhone.setBounds(70, 114, 179, 19);
		AddPanel.add(teacherPhone);
		teacherPhone.setColumns(10);
		
		// Email 
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 147, 70, 15);
		AddPanel.add(lblEmail);

		teacherEmail = new JTextField();
		teacherEmail.setBounds(62, 147, 179, 19);
		AddPanel.add(teacherEmail);
		teacherEmail.setColumns(10);
		
		// Fiscal Number  
		JLabel lblFiscalNumber = new JLabel("Fiscal Number:");
		lblFiscalNumber.setBounds(12, 180, 117, 15);
		AddPanel.add(lblFiscalNumber);

		teacherFiscalNumber = new JTextField();
		teacherFiscalNumber.setBounds(125, 180, 179, 19);
		AddPanel.add(teacherFiscalNumber);
		teacherFiscalNumber.setColumns(10);
		
		// Required label
		JLabel lblRequired = new JLabel("* required:");
		lblRequired.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblRequired.setBounds(12, 213, 107, 15);
		AddPanel.add(lblRequired);

		// Add button
		btnAdd = new JButton("ADD TEACHER");
		btnAdd.setBounds(49, 246, 235, 25);
		AddPanel.add(btnAdd);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(1004, 25, 23, 313);
		contentPane.add(separator);
		separator.setOrientation(SwingConstants.VERTICAL);
	}
	
	public JTable getTableTeachers() { 
		return this.tableTeachers; 
	}

	public JButton getRemove() {
		return btnRemove;
	}
	
	public JButton getUpdate() {
		return btnUpdate;
	}
	
	public JButton getAdd() {
		return btnAdd;
	}
	
	public void setSelectedTeacher(String name) {
		lblTeacherNameUpdate.setText(name);
	}
	
	public String getTeacherPhoneUpdate() {
		return teacherPhoneUpdate.getText();
	}
	
	public String getTeacherEmailUpdate() {
		return teacherEmailUpdate.getText();
	}
	
	public String getTeacherFiscalNumberUpdate() {
		return teacherFiscalNumberUpdate.getText();
	}

	public String getTeacherName() {
		return teacherName.getText();
	}
	
	public void setTeacherName(String text) {
		teacherName.setText(text);
	}

	public String getTeacherSurname() {
		return teacherSurname.getText();
	}
	
	public void setTeacherSurname(String text) {
		teacherSurname.setText(text);
	}
	
	public String getTeacherPhone() {
		return teacherPhone.getText();
	}
	
	public void setTeacherPhone(String phone) {
		teacherPhone.setText(phone);
	}
	
	public String getTeacherEmail() {
		return teacherEmail.getText();
	}
	
	public void setTeacherEmail(String email) {
		teacherEmail.setText(email);
	}
	
	public String getTeacherFiscalNumber() {
		return teacherFiscalNumber.getText();
	}
	
	public void setTeacherFiscalNumber(String fN) {
		teacherFiscalNumber.setText(fN);
	}
	
	public JTextField getTFPhoneUpdate() {
		return teacherPhoneUpdate;
	}
	public JTextField getTFEmailUpdate() {
		return teacherEmailUpdate;
	}
	public JTextField getTFFiscalNumberUpdate() {
		return teacherFiscalNumberUpdate;
	}
	public void setTFPhoneUpdate(String text) {
		teacherPhoneUpdate.setText(text);;
	}
	public void setTFEmailUpdate(String text) {
		teacherEmailUpdate.setText(text);;
	}
	public void setTFFiscalNumberUpdate(String text) {
		teacherFiscalNumber.setText(text);;
	}
	
}