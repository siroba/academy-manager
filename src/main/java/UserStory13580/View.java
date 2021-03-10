package UserStory13580;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View extends JFrame {

	private JPanel contentPane;
	private JTextField dateTextField;
	private JTextField nameTextField;
	private JTextField fiscalNumberTextField;
	private JTextField addressTextField;
	private JTextField dateTransferTextField;
	private JTable table;
	private JButton registerButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {
		setBounds(100, 100, 691, 651);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Record Payments");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(247, 10, 163, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Pending Payments");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(32, 61, 153, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Data of the invoice");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(50, 283, 170, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Date");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_3.setBounds(51, 326, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("Issuer tax data");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_5.setBounds(50, 376, 93, 13);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Name");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_6.setBounds(51, 410, 45, 13);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Fiscal number");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_7.setBounds(247, 410, 81, 13);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Address");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_8.setBounds(432, 410, 81, 13);
		contentPane.add(lblNewLabel_8);
		
		dateTextField = new JTextField();
		dateTextField.setBounds(126, 323, 129, 19);
		contentPane.add(dateTextField);
		dateTextField.setColumns(10);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(50, 445, 135, 19);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		fiscalNumberTextField = new JTextField();
		fiscalNumberTextField.setBounds(229, 445, 163, 19);
		contentPane.add(fiscalNumberTextField);
		fiscalNumberTextField.setColumns(10);
		
		addressTextField = new JTextField();
		addressTextField.setBounds(432, 445, 217, 19);
		contentPane.add(addressTextField);
		addressTextField.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Date of the transfer");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_9.setBounds(50, 507, 115, 13);
		contentPane.add(lblNewLabel_9);
		
		dateTransferTextField = new JTextField();
		dateTransferTextField.setBounds(175, 504, 135, 19);
		contentPane.add(dateTransferTextField);
		dateTransferTextField.setColumns(10);
		
		registerButton = new JButton("Register");
		
		registerButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		registerButton.setBounds(253, 563, 115, 41);
		contentPane.add(registerButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 101, 596, 117);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		scrollPane.setViewportView(table);
	}
	
	public String getAddressTextField() {
		return addressTextField.getText();
	}
	
	public String getDateTextField() {
		return dateTextField.getText();
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

	public String getDateTransferTextField() {
		return dateTransferTextField.getText();
	}

	public void setDateTransferTextField(JTextField dateTransferTextField) {
		this.dateTransferTextField = dateTransferTextField;
	}

	public void setDateTextField(JTextField dateTextField) {
		this.dateTextField = dateTextField;
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
}
