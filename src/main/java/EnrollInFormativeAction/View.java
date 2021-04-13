package EnrollInFormativeAction;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entities.FormativeAction;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class View extends JFrame {
	// Auto-generated serial ID
	private static final long serialVersionUID = -70145237442153458L;

	private JPanel contentPane, selectionPanel, registrationPanel;
	private JList<String> faList;
	private JScrollPane scrollPane;
	private JTextField profName;
	private JTextField textSurname;
	private JTextField textPhone;
	private JTextField textEmail;
	private JButton btnConfirmAndEnroll;
	private JLabel lblTheFeeWill;
	private JTextField txtFee;
	private JComboBox<String> cbGroup;
	private JTextField Address;
	private JTextField FiscalNumber;

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setTitle("Enroll in a Formative Action");
		setBounds(100, 100, 760, 378);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		selectionPanel = new JPanel();
		selectionPanel.setBounds(12, -11, 428, 262);
		contentPane.add(selectionPanel);
		selectionPanel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 37, 404, 218);
		selectionPanel.add(scrollPane);

		faList = new JList<String>();
		scrollPane.setViewportView(faList);
		faList.setValueIsAdjusting(true);
		faList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JLabel lblSelectTheFormative = new JLabel("Select the Formative Action you want to enroll in:");
		lblSelectTheFormative.setBounds(12, 15, 389, 27);
		selectionPanel.add(lblSelectTheFormative);

		registrationPanel = new JPanel();
		registrationPanel.setBounds(452, -13, 298, 358);
		contentPane.add(registrationPanel);
		registrationPanel.setVisible(false);
		registrationPanel.setLayout(null);
		
		JLabel lblFillYourInformation = new JLabel("Fill your information:");
		lblFillYourInformation.setBounds(12, 18, 238, 15);
		registrationPanel.add(lblFillYourInformation);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(12, 51, 70, 15);
		registrationPanel.add(lblName);

		profName = new JTextField();
		profName.setBounds(67, 49, 199, 19);
		registrationPanel.add(profName);
		profName.setColumns(10);

		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(12, 84, 70, 15);
		registrationPanel.add(lblSurname);

		textSurname = new JTextField();
		textSurname.setBounds(87, 82, 179, 19);
		registrationPanel.add(textSurname);
		textSurname.setColumns(10);

		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(12, 117, 70, 15);
		registrationPanel.add(lblPhone);

		textPhone = new JTextField();
		textPhone.setBounds(67, 115, 114, 19);
		registrationPanel.add(textPhone);
		textPhone.setColumns(10);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 150, 70, 15);
		registrationPanel.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setBounds(67,148, 148, 19);
		registrationPanel.add(textEmail);
		textEmail.setColumns(10);

		JLabel lblGroup = new JLabel("Group:");
		lblGroup.setBounds(12, 183, 70, 15);
		registrationPanel.add(lblGroup);

		cbGroup = new JComboBox<String>();
		cbGroup.setBounds(67, 181, 148, 19);
		registrationPanel.add(cbGroup);

		lblTheFeeWill = new JLabel("The Fee will be: ");
		lblTheFeeWill.setBounds(12, 278, 135, 15);
		registrationPanel.add(lblTheFeeWill);
		
		txtFee = new JTextField();
		txtFee.setEditable(false);
		txtFee.setBounds(116, 276, 121, 19);
		registrationPanel.add(txtFee);
		txtFee.setColumns(10);
		
		btnConfirmAndEnroll = new JButton("Confirm and enroll");
		btnConfirmAndEnroll.setBounds(12, 311, 235, 25);
		registrationPanel.add(btnConfirmAndEnroll);
		
		JLabel lblNewLabel = new JLabel("Address");
		lblNewLabel.setBounds(10, 216, 72, 13);
		registrationPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fiscal number");
		lblNewLabel_1.setBounds(12, 247, 101, 13);
		registrationPanel.add(lblNewLabel_1);
		
		Address = new JTextField();
		Address.setBounds(60, 213, 179, 19);
		registrationPanel.add(Address);
		Address.setColumns(10);
		
		FiscalNumber = new JTextField();
		FiscalNumber.setBounds(110, 244, 165, 19);
		registrationPanel.add(FiscalNumber);
		FiscalNumber.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(441, -11, 18, 356);
		contentPane.add(separator);
		separator.setOrientation(SwingConstants.VERTICAL);
	}

	public void setFAList(List<FormativeAction> list) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();

		for (FormativeAction fa : list)
			listModel.addElement(fa.getName());

		this.faList.setModel(listModel);
	}

	public JList<String> getFAList() {
		return this.faList;
	}

	public void changeView(boolean change) {
		//this.selectionPanel.setVisible(!change);

		this.registrationPanel.setVisible(change);
	}

	public JButton getBtnConfirmAndEnroll() {
		return btnConfirmAndEnroll;
	}

	public String getProfName() {
		return profName.getText();
	}
	
	public void setProfName(String text) {
		profName.setText(text);
	}

	public String getTextSurname() {
		return textSurname.getText();
	}
	
	public void setTextSurname(String text) {
		textSurname.setText(text);
	}

	public String getTextPhone() {
		return textPhone.getText();
	}
	
	public void setTextPhone(String text) {
		textPhone.setText(text);
	}

	public String getTextEmail() {
		return textEmail.getText();
	}
	
	public void setTextEmail(String text) {
		textEmail.setText(text);
	} 
	
	public JTextField getTxtFee() {
		return txtFee;
	}
	
	public void setTextFee(String text) {
		txtFee.setText(text);
	} 
	
	public JComboBox<String> getCBGroup() {
		return this.cbGroup;
	}
	public String getGroup() {
		return String.valueOf(cbGroup.getSelectedItem());
	}

	public void setTxtFee(String string) {
		txtFee.setText(string);
	}
	public void addCbGroup(String group) {
		cbGroup.addItem(group);
	}
	public void clearCbGroup() {
		cbGroup.removeAllItems();
	}
	public String getAddress() {
		return Address.getText();
	}
	public String getFiscalNumber() {
		return FiscalNumber.getText();
	}
}
