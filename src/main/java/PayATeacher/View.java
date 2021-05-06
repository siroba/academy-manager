package PayATeacher;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import PL53.swing.DateInput;
import PL53.swing.JDecimalField;
import PL53.util.Date;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;

public class View extends JFrame {
	// Auto generated serial ID
	private static final long serialVersionUID = -7328790026428798502L;
	
	private JPanel contentPane;
	private DateInput dateTextField;
	private JTextField nameTextField;
	private JTextField fiscalNumberTextField;
	private JTextField addressTextField;
	private DateInput dateTransferTextField;
	private JTable table;
	private JButton registerButton;
	private JTextField IDInvoice;
	private JDecimalField amount;
	private DateInput dateTransferTextField_1;
	private JButton addMovement;
	private JDecimalField amoundRefound;
	private JTextField surname;
	private JTable table_1;
	private JRadioButton radioBtnTeacher;
	private JRadioButton radioBtnCoiipa;

	/**
	 * Create the frame.
	 */
	public View() {
		Border blackline = BorderFactory.createLineBorder(Color.black);
		
		setBounds(100, 100, 1129, 758);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Record Payments made to the teachers");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));

		lblNewLabel.setBounds(423, 10, 346, 31);

		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Record payments");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 40, 251, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Data of the invoice");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 279, 170, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Date of the invoice");
		lblNewLabel_3.setBounds(10, 302, 123, 13);
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		dateTextField = new DateInput();
		dateTextField.setBounds(8, 328, 211, 69);
		dateTextField.setBorder(blackline);
		dateTextField.getYearsTextField().setBound(2000, 3000);
		dateTextField.getYearsTextField().setDefaultValue(2021);
		contentPane.add(dateTextField);
		
		dateTransferTextField = new DateInput();
		dateTransferTextField.setBounds(873, 328, 230, 65);
		dateTransferTextField.setBorder(blackline);
		dateTransferTextField.getYearsTextField().setBound(2000, 3000);
		dateTransferTextField.getYearsTextField().setDefaultValue(2021);
		contentPane.add(dateTransferTextField);
		
		JLabel lblNewLabel_5 = new JLabel("Issuer tax data");
		lblNewLabel_5.setBounds(229, 302, 93, 13);
		contentPane.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JPanel taxDataPanel = new JPanel();
		taxDataPanel.setBounds(229, 328, 634, 143);
		taxDataPanel.setBorder(blackline);
		contentPane.add(taxDataPanel);
		taxDataPanel.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Name");
		lblNewLabel_6.setBounds(22, 20, 45, 13);
		taxDataPanel.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JLabel lblNewLabel_7 = new JLabel("Fiscal number");
		lblNewLabel_7.setBounds(446, 20, 81, 13);
		taxDataPanel.add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JLabel lblNewLabel_8 = new JLabel("Address");
		lblNewLabel_8.setBounds(22, 76, 81, 13);
		taxDataPanel.add(lblNewLabel_8);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		nameTextField = new JTextField();
		nameTextField.setBounds(22, 36, 172, 19);
		taxDataPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		fiscalNumberTextField = new JTextField();
		fiscalNumberTextField.setBounds(446, 36, 163, 19);
		taxDataPanel.add(fiscalNumberTextField);
		fiscalNumberTextField.setColumns(10);
		
		addressTextField = new JTextField();
		addressTextField.setBounds(22, 99, 217, 19);
		taxDataPanel.add(addressTextField);
		addressTextField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("ID Invoice");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_4.setBounds(261, 76, 81, 13);
		taxDataPanel.add(lblNewLabel_4);
		
		IDInvoice = new JTextField();
		IDInvoice.setBounds(261, 99, 163, 19);
		taxDataPanel.add(IDInvoice);
		IDInvoice.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Amount");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_11.setBounds(446, 76, 59, 13);
		taxDataPanel.add(lblNewLabel_11);
		
		amount = new JDecimalField(2);
		amount.setBounds(446, 99, 163, 19);
		taxDataPanel.add(amount);
		amount.setColumns(10);
		
		surname = new JTextField();
		surname.setBounds(261, 36, 163, 19);
		taxDataPanel.add(surname);
		surname.setColumns(10);
		
		JLabel lblNewLabel_15 = new JLabel("Surname");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_15.setBounds(261, 20, 73, 13);
		taxDataPanel.add(lblNewLabel_15);
		
		JLabel lblNewLabel_9 = new JLabel("Date of the transfer");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_9.setBounds(874, 302, 115, 13);
		contentPane.add(lblNewLabel_9);
		
		
		

		registerButton = new JButton("Record the invoice");
		
		registerButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		registerButton.setBounds(909, 415, 158, 41);

		contentPane.add(registerButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 80, 1093, 170);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		scrollPane.setViewportView(table);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 494, 1245, 13);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 260, 1261, 13);
		contentPane.add(separator_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 540, 477, 172);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblNewLabel_10 = new JLabel("Movements");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_10.setBounds(10, 515, 115, 13);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_12 = new JLabel("Record movements");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_12.setBounds(568, 515, 330, 31);
		contentPane.add(lblNewLabel_12);
		
		dateTransferTextField_1 = new DateInput();
		dateTransferTextField_1.setBounds(568, 580, 230, 65);
		dateTransferTextField_1.setBorder(blackline);
		contentPane.add(dateTransferTextField_1);
		
		JLabel lblNewLabel_13 = new JLabel("Date");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_13.setBounds(568, 558, 59, 13);
		contentPane.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Amount");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_14.setBounds(568, 670, 45, 13);
		contentPane.add(lblNewLabel_14);
		
		addMovement = new JButton("Add movement");
		
		addMovement.setFont(new Font("Tahoma", Font.BOLD, 10));
		addMovement.setBounds(861, 671, 170, 41);
		contentPane.add(addMovement);
		
		amoundRefound = new JDecimalField(2);
		amoundRefound.setColumns(10);
		amoundRefound.setBounds(568, 693, 163, 19);
		contentPane.add(amoundRefound);
		
		JLabel lblNewLabel_16 = new JLabel("Sender of the movement");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_16.setBounds(861, 558, 182, 13);
		contentPane.add(lblNewLabel_16);
		
		ButtonGroup bgroup = new ButtonGroup();
		radioBtnTeacher = new JRadioButton("Teacher");
		bgroup.add(radioBtnTeacher);
		radioBtnTeacher.setBounds(861, 580, 103, 21);
		contentPane.add(radioBtnTeacher);
		
		radioBtnCoiipa = new JRadioButton("COIIPA");
		bgroup.add(radioBtnCoiipa);
		radioBtnCoiipa.setSelected(true);
		radioBtnCoiipa.setBounds(861, 604, 103, 21);
		contentPane.add(radioBtnCoiipa);
	}
	
	public String getAddressTextField() {
		return addressTextField.getText();
	}
	
	public Date getDateTextField() {
		return dateTextField.getDate();
	}
	public JTable getTable() {
		return table;
	}


	public String getNameTextField() {
		return nameTextField.getText();
	}

	public void setNameTextField(JTextField nameTextField) {
		this.nameTextField = nameTextField;
	}

	public String getFiscalNumberTextField() {
		return fiscalNumberTextField.getText();
	}

	public void setFiscalNumberTextField(JTextField fiscalNumberTextField) {
		this.fiscalNumberTextField = fiscalNumberTextField;
	}

	public Date getDateTransferTextField() {
		return dateTransferTextField.getDate();
	}	

	public void setAddressTextField(JTextField addressTextField) {
		this.addressTextField = addressTextField;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void setTable(TableModel tm) {
		this.table.setModel(tm);
	}
	
	public int getSelected() {
		return this.table.getSelectedRow();
	}

	public JButton getRegisterButton() {
		return registerButton;
	}
	public String getIDInvoice(){
		return IDInvoice.getText();
	}

	public void setIDInvoice(JTextField IDInvoice) {
		this.IDInvoice=IDInvoice;
	}
	public Date getDateTransferTextField_1() {
		return dateTransferTextField_1.getDate();
	}
	public JButton getAddMovement() {
		return addMovement;
	}
	public float getAmount() {
		return  amount.getValue();
	}
	public float getAmoundRefound() {
		return amoundRefound.getValue();
	}
	
	public JTextField getSurname() {
		return surname;
	}
	public boolean getCheckTeacher() {
		return radioBtnTeacher.isSelected();
	}
	public boolean getCheckCOIIPA() {
		return radioBtnCoiipa.isSelected();
	}

	public void setMovementTable(TableModel tm) {
		this.table_1.setModel(tm);
	}

	public JTable getTable_1() {
		return table_1;
	}
}
