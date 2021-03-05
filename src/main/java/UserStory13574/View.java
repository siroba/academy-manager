package UserStory13574;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entities.FormativeAction;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class View extends JFrame {
	// Auto-generated serial ID
	private static final long serialVersionUID = -70145237442153458L;

	private JPanel contentPane, selectionPanel, registrationPanel;
	private JButton btnEnroll;
	private JList<String> faList;
	private JScrollPane scrollPane;
	private JTextPane textSelectedFA;
	private JTextField profName;
	private JButton backButton;
	private JTextField textSurname;
	private JTextField textPhone;
	private JTextField textEmail;
	private JButton btnConfirmAndEnroll;

	/**
	 * Create the frame.
	 */
	public View() {
		setTitle("Enroll in a Formative Action");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		selectionPanel = new JPanel();
		selectionPanel.setBounds(12, -11, 428, 262);
		contentPane.add(selectionPanel);
		selectionPanel.setLayout(null);

		btnEnroll = new JButton("Enroll");
		btnEnroll.setBounds(12, 226, 404, 25);
		selectionPanel.add(btnEnroll);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 37, 404, 177);
		selectionPanel.add(scrollPane);

		faList = new JList<String>();
		scrollPane.setViewportView(faList);
		faList.setValueIsAdjusting(true);
		faList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JLabel lblSelectTheFormative = new JLabel("Select the Formative Action you want to enroll in:");
		lblSelectTheFormative.setBounds(12, 15, 389, 27);
		selectionPanel.add(lblSelectTheFormative);

		registrationPanel = new JPanel();
		registrationPanel.setVisible(false);
		registrationPanel.setBounds(12, -6, 426, 264);
		contentPane.add(registrationPanel);
		registrationPanel.setLayout(null);

		JLabel lblFormativeAction = new JLabel("Selected Formative Action:");
		lblFormativeAction.setBounds(12, 32, 235, 15);
		registrationPanel.add(lblFormativeAction);

		textSelectedFA = new JTextPane();
		textSelectedFA.setEditable(false);
		textSelectedFA.setBounds(224, 26, 190, 21);
		registrationPanel.add(textSelectedFA);

		backButton = new JButton("< Back");
		backButton.setBounds(0, 239, 117, 25);
		registrationPanel.add(backButton);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(12, 71, 70, 15);
		registrationPanel.add(lblName);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 59, 402, 2);
		registrationPanel.add(separator);

		profName = new JTextField();
		profName.setBounds(67, 69, 114, 19);
		registrationPanel.add(profName);
		profName.setColumns(10);

		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(212, 71, 70, 15);
		registrationPanel.add(lblSurname);

		textSurname = new JTextField();
		textSurname.setBounds(287, 69, 114, 19);
		registrationPanel.add(textSurname);
		textSurname.setColumns(10);

		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(12, 129, 70, 15);
		registrationPanel.add(lblPhone);

		textPhone = new JTextField();
		textPhone.setBounds(67, 127, 114, 19);
		registrationPanel.add(textPhone);
		textPhone.setColumns(10);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(212, 129, 70, 15);
		registrationPanel.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setBounds(266, 127, 135, 19);
		registrationPanel.add(textEmail);
		textEmail.setColumns(10);

		btnConfirmAndEnroll = new JButton("Confirm and enroll");
		btnConfirmAndEnroll.setBounds(243, 239, 171, 25);
		registrationPanel.add(btnConfirmAndEnroll);
	}

	public JButton getEnrollBtn() {
		return this.btnEnroll;
	}

	public void setFAList(List<FormativeAction> list) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();

		for(FormativeAction fa: list)
			listModel.addElement(fa.getName());

		this.faList.setModel(listModel);
	}

	public JList<String> getFAList(){
		return this.faList;
	}

	public void changeView(boolean change) {
		this.selectionPanel.setVisible(!change);

		this.registrationPanel.setVisible(change);
	}

	public void setTextSelectedFA(String text) {
		textSelectedFA.setText(text);
	}

	public JTextPane getTextSelectedFA() {
		return textSelectedFA;
	}
	public JButton getBackButton() {
		return backButton;
	}
	public JButton getBtnConfirmAndEnroll() {
		return btnConfirmAndEnroll;
	}
	public String getProfName() {
		return profName.getText();
	}
	public String getTextSurname() {
		return textSurname.getText();
	}
	public String getTextPhone() {
		return textPhone.getText();
	}
	public String getTextEmail() {
		return textEmail.getText();
	}
}
