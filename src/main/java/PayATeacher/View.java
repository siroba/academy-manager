package PayATeacher;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import PL53.swing.DateInput;
import PL53.swing.DateTimeInput;
import PL53.swing.JDecimalField;
import PL53.swing.JIntField;
import PL53.swing.JNumberField;
import PL53.util.Date;
import PL53.util.DateTime;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

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
	private JButton addPayment;
	private JDecimalField amoundRefound;
	private JTextField surname;
	private JTextField dueAmountTextField;

	/**
	 * Create the frame.
	 */
	public View() {
		Border blackline = BorderFactory.createLineBorder(Color.black);
		
		setBounds(100, 100, 1206, 758);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Record Payments made to the teachers");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));

		lblNewLabel.setBounds(409, 10, 454, 31);

		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Record payments");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(32, 61, 251, 30);
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
		dateTextField.setBounds(8, 325, 211, 69);
		dateTextField.setBorder(blackline);
		dateTextField.getYearsTextField().setBound(2000, 3000);
		contentPane.add(dateTextField);
		
		dateTransferTextField = new DateInput();
		dateTransferTextField.setBounds(10, 580, 230, 65);
		dateTransferTextField.setBorder(blackline);
		dateTransferTextField.getYearsTextField().setBound(2000, 3000);
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
		lblNewLabel_6.setBounds(13, 20, 45, 13);
		taxDataPanel.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JLabel lblNewLabel_7 = new JLabel("Fiscal number");
		lblNewLabel_7.setBounds(428, 13, 81, 13);
		taxDataPanel.add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JLabel lblNewLabel_8 = new JLabel("Address");
		lblNewLabel_8.setBounds(10, 76, 81, 13);
		taxDataPanel.add(lblNewLabel_8);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		nameTextField = new JTextField();
		nameTextField.setBounds(12, 36, 135, 19);
		taxDataPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		fiscalNumberTextField = new JTextField();
		fiscalNumberTextField.setBounds(428, 36, 163, 19);
		taxDataPanel.add(fiscalNumberTextField);
		fiscalNumberTextField.setColumns(10);
		
		addressTextField = new JTextField();
		addressTextField.setBounds(13, 99, 217, 19);
		taxDataPanel.add(addressTextField);
		addressTextField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("ID Invoice");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_4.setBounds(255, 76, 81, 13);
		taxDataPanel.add(lblNewLabel_4);
		
		IDInvoice = new JTextField();
		IDInvoice.setBounds(240, 99, 163, 19);
		taxDataPanel.add(IDInvoice);
		IDInvoice.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Amount");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_11.setBounds(428, 76, 59, 13);
		taxDataPanel.add(lblNewLabel_11);
		
		amount = new JDecimalField(2);
		amount.setBounds(428, 99, 163, 19);
		taxDataPanel.add(amount);
		amount.setColumns(10);
		
		surname = new JTextField();
		surname.setBounds(190, 36, 182, 19);
		taxDataPanel.add(surname);
		surname.setColumns(10);
		
		JLabel lblNewLabel_15 = new JLabel("Surname");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_15.setBounds(185, 13, 73, 13);
		taxDataPanel.add(lblNewLabel_15);
		
		JLabel lblNewLabel_9 = new JLabel("Date of the transfer");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_9.setBounds(10, 545, 115, 13);
		contentPane.add(lblNewLabel_9);
		
		
		

		registerButton = new JButton("Pay the teacher");
		
		registerButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		registerButton.setBounds(279, 580, 158, 41);

		contentPane.add(registerButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 101, 695, 117);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		scrollPane.setViewportView(table);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 494, 1245, 13);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 239, 1261, 13);
		contentPane.add(separator_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(715, 101, 367, 117);
		contentPane.add(scrollPane_1);
		
		JLabel lblNewLabel_10 = new JLabel("Movements");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_10.setBounds(715, 72, 115, 13);
		contentPane.add(lblNewLabel_10);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(530, 494, 12, 239);
		contentPane.add(separator_3);
		
		JLabel lblNewLabel_12 = new JLabel("Payments made by the teachers");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_12.setBounds(568, 517, 330, 31);
		contentPane.add(lblNewLabel_12);
		
		dateTransferTextField_1 = new DateInput();
		dateTransferTextField_1.setBounds(568, 580, 230, 65);
		dateTransferTextField_1.setBorder(blackline);
		contentPane.add(dateTransferTextField_1);
		
		JLabel lblNewLabel_13 = new JLabel("Date");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_13.setBounds(578, 558, 59, 13);
		contentPane.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Amount");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_14.setBounds(568, 670, 45, 13);
		contentPane.add(lblNewLabel_14);
		
		addPayment = new JButton("Add payment");
		addPayment.setFont(new Font("Tahoma", Font.BOLD, 10));
		addPayment.setBounds(898, 581, 170, 41);
		contentPane.add(addPayment);
		
		amoundRefound = new JDecimalField(2);
		amoundRefound.setColumns(10);
		amoundRefound.setBounds(568, 693, 163, 19);
		contentPane.add(amoundRefound);
		
		JLabel lblNewLabel_16 = new JLabel("Due amount");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_16.setBounds(757, 670, 106, 13);
		contentPane.add(lblNewLabel_16);
		
		dueAmountTextField = new JTextField();
		dueAmountTextField.setText("");
		dueAmountTextField.setBounds(757, 693, 137, 19);
		contentPane.add(dueAmountTextField);
		dueAmountTextField.setColumns(10);
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
	public DateInput getDateTransferTextField_1() {
		return dateTransferTextField_1;
	}
	public JButton getAddPayment() {
		return addPayment;
	}
	public float getAmount() {
		return  amount.getValue();
	}
	public float getAmoundRefound() {
		return amoundRefound.getValue();
	}
	public JTextField getDueAmountTextField() {
		return dueAmountTextField;
	}
	public JTextField getSurname() {
		return surname;
	}
}
