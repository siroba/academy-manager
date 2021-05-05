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
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JPanel invoiceInfoPanel;
	private JCheckBox chckbxNewCheckBox;

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setTitle("Enroll in a Formative Action");
		setBounds(100, 100, 760, 403);
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
		registrationPanel.setBounds(452, -13, 298, 366);
		contentPane.add(registrationPanel);
		registrationPanel.setVisible(false);
		registrationPanel.setLayout(null);

		// Information
		JLabel lblFillYourInformation = new JLabel("Fill your information:");
		lblFillYourInformation.setBounds(12, 18, 238, 15);
		registrationPanel.add(lblFillYourInformation);

		// Name
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(12, 51, 70, 15);
		registrationPanel.add(lblName);

		profName = new JTextField();
		profName.setBounds(67, 49, 199, 19);
		registrationPanel.add(profName);
		profName.setColumns(10);

		// Surename
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(12, 84, 70, 15);
		registrationPanel.add(lblSurname);

		textSurname = new JTextField();
		textSurname.setBounds(87, 82, 179, 19);
		registrationPanel.add(textSurname);
		textSurname.setColumns(10);

		// Phone
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(12, 117, 70, 15);
		registrationPanel.add(lblPhone);

		textPhone = new JTextField();
		textPhone.setBounds(67, 115, 114, 19);
		registrationPanel.add(textPhone);
		textPhone.setColumns(10);

		// Email
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 150, 70, 15);
		registrationPanel.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setBounds(67, 148, 148, 19);
		registrationPanel.add(textEmail);
		textEmail.setColumns(10);

		// Group
		JLabel lblGroup = new JLabel("Group:");
		lblGroup.setBounds(12, 183, 70, 15);
		registrationPanel.add(lblGroup);

		cbGroup = new JComboBox<String>();
		cbGroup.setBounds(67, 181, 148, 19);
		registrationPanel.add(cbGroup);

		invoiceInfoPanel = new JPanel();
		invoiceInfoPanel.setVisible(false);
		invoiceInfoPanel.setBounds(12, 237, 277, 54);
		registrationPanel.add(invoiceInfoPanel);
		invoiceInfoPanel.setLayout(null);

		// Address
		JLabel lblNewLabel = new JLabel("Address: ");
		lblNewLabel.setBounds(0, 3, 72, 13);
		invoiceInfoPanel.add(lblNewLabel);

		Address = new JTextField();
		Address.setBounds(70, 0, 197, 19);
		invoiceInfoPanel.add(Address);
		Address.setColumns(10);

		// Fiscal Number
		JLabel lblNewLabel_1 = new JLabel("Fiscal number: ");
		lblNewLabel_1.setBounds(2, 34, 111, 13);
		invoiceInfoPanel.add(lblNewLabel_1);

		FiscalNumber = new JTextField();
		FiscalNumber.setBounds(99, 31, 168, 19);
		invoiceInfoPanel.add(FiscalNumber);
		FiscalNumber.setColumns(10);

		// Fee
		lblTheFeeWill = new JLabel("The Fee will be: ");
		lblTheFeeWill.setBounds(11, 302, 135, 15);
		registrationPanel.add(lblTheFeeWill);

		txtFee = new JTextField();
		txtFee.setEditable(false);
		txtFee.setBounds(129, 300, 121, 19);
		registrationPanel.add(txtFee);
		txtFee.setColumns(10);

		// Confirm and enroll button
		btnConfirmAndEnroll = new JButton("Confirm and enroll");
		btnConfirmAndEnroll.setBounds(31, 330, 235, 25);
		registrationPanel.add(btnConfirmAndEnroll);
		
		chckbxNewCheckBox = new JCheckBox("Do you want a receipt?");
		chckbxNewCheckBox.setBounds(12, 207, 169, 23);
		registrationPanel.add(chckbxNewCheckBox);

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

	protected JPanel getInvoiceInfoPanel() {
		return invoiceInfoPanel;
	}

	public boolean getWantInvoice() {
		return chckbxNewCheckBox.isSelected();
	}

	public JCheckBox getChckbxNewCheckBox() {
		return chckbxNewCheckBox;
	}

	public void enableInvoice(boolean b) {
		invoiceInfoPanel.setVisible(b);
	}

}
