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

	/**
	 * Create the frame.
	 */
	public View() {
		Border blackline = BorderFactory.createLineBorder(Color.black);
		
		setBounds(100, 100, 1073, 583);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Record Payments made to the teachers");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(247, 10, 454, 31);
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
		dateTransferTextField.setBounds(10, 453, 230, 65);
		dateTransferTextField.setBorder(blackline);
		dateTransferTextField.getYearsTextField().setBound(2000, 3000);
		contentPane.add(dateTransferTextField);
		
		JLabel lblNewLabel_5 = new JLabel("Issuer tax data");
		lblNewLabel_5.setBounds(236, 302, 93, 13);
		contentPane.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JPanel taxDataPanel = new JPanel();
		taxDataPanel.setBounds(229, 325, 806, 69);
		taxDataPanel.setBorder(blackline);
		contentPane.add(taxDataPanel);
		taxDataPanel.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Name");
		lblNewLabel_6.setBounds(13, 20, 45, 13);
		taxDataPanel.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JLabel lblNewLabel_7 = new JLabel("Fiscal number");
		lblNewLabel_7.setBounds(179, 20, 81, 13);
		taxDataPanel.add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JLabel lblNewLabel_8 = new JLabel("Address");
		lblNewLabel_8.setBounds(382, 20, 81, 13);
		taxDataPanel.add(lblNewLabel_8);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		nameTextField = new JTextField();
		nameTextField.setBounds(12, 36, 135, 19);
		taxDataPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		fiscalNumberTextField = new JTextField();
		fiscalNumberTextField.setBounds(179, 36, 163, 19);
		taxDataPanel.add(fiscalNumberTextField);
		fiscalNumberTextField.setColumns(10);
		
		addressTextField = new JTextField();
		addressTextField.setBounds(382, 36, 217, 19);
		taxDataPanel.add(addressTextField);
		addressTextField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("ID Invoice");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_4.setBounds(612, 20, 81, 13);
		taxDataPanel.add(lblNewLabel_4);
		
		IDInvoice = new JTextField();
		IDInvoice.setBounds(609, 36, 163, 19);
		taxDataPanel.add(IDInvoice);
		IDInvoice.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Date of the transfer");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_9.setBounds(10, 427, 115, 13);
		contentPane.add(lblNewLabel_9);
		
		
		
		registerButton = new JButton("Register");
		
		registerButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		registerButton.setBounds(368, 469, 115, 41);
		contentPane.add(registerButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 101, 1025, 117);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		scrollPane.setViewportView(table);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 404, 1085, 13);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 239, 1085, 13);
		contentPane.add(separator_1);
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

}
