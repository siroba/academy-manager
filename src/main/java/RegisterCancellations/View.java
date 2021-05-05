package RegisterCancellations;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.DebugGraphics;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import PL53.swing.DateInput;
import PL53.util.Date;
import javax.swing.JCheckBox;

public class View extends JFrame {
	// Generated serial ID
	private static final long serialVersionUID = -7620725048709775087L;
	
	private JPanel contentPane;
	private JTable table;
	JButton btnCancelRegistration;
	private JTextField address;
	private JTextField fiscalNumber;
	public DateInput dateIn;
	private JCheckBox isCash;
	private JPanel invoiceInfoPanel;
	private JPanel panel;
	
	/**
	 * Create the frame.
	 */
	public View() {
		setTitle("Select an enrollment to cancel");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 867, 299);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setRowMargin(3);
		table.setRowHeight(20);
		table.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
		table.setBounds(10, 10, 450, 300);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 831, 126);
		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);
		
		panel = new JPanel();
		panel.setBounds(329, 147, 377, 109);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnCancelRegistration = new JButton("Cancel registration");
		btnCancelRegistration.setBounds(0, 84, 207, 25);
		panel.add(btnCancelRegistration);
		
		JLabel lblNewLabel = new JLabel("Date of the cancellation:");
		lblNewLabel.setBounds(0, 0, 174, 14);
		panel.add(lblNewLabel);
		
		dateIn = new DateInput();
		dateIn.getYearsTextField().setBound(2000, Integer.MAX_VALUE);
		dateIn.getYearsTextField().setDefaultValue(2021);
		dateIn.setBounds(0, 18, 211, 55);
		panel.add(dateIn);
		dateIn.setBorder(BorderFactory.createLineBorder(Color.black));
		
		isCash = new JCheckBox("Cash payment");
		isCash.setBounds(239, 33, 138, 23);
		panel.add(isCash);
		dateIn.getYearsTextField().setBound(2021, 3000);
		dateIn.getYearsTextField().setDefaultValue(2021);
		
		invoiceInfoPanel = new JPanel();
		invoiceInfoPanel.setEnabled(false);
		invoiceInfoPanel.setVisible(false);
		invoiceInfoPanel.setBounds(10, 269, 422, 45);
		contentPane.add(invoiceInfoPanel);
		invoiceInfoPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("COIIPA's address:");
		lblNewLabel_1.setBounds(0, 0, 170, 14);
		invoiceInfoPanel.add(lblNewLabel_1);
		
		address = new JTextField();
		address.setBounds(0, 25, 206, 20);
		invoiceInfoPanel.add(address);
		address.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("COIIPA's fiscal number:");
		lblNewLabel_2.setBounds(216, 0, 151, 14);
		invoiceInfoPanel.add(lblNewLabel_2);
		
		fiscalNumber = new JTextField();
		fiscalNumber.setBounds(216, 25, 206, 20);
		invoiceInfoPanel.add(fiscalNumber);
		fiscalNumber.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Press the button to know the due amount:");
		lblNewLabel_3.setBounds(61, 235, 258, 14);
		contentPane.add(lblNewLabel_3);
	}
	
	public JButton getBtnCancelRegistration() {
		return btnCancelRegistration;
	}
	
	public void setTable(TableModel tm) {
		this.table.setModel(tm);
	}
	
	public int getSelected() {
		return this.table.getSelectedRow();
	}
	
	public String getAddress() {
		return address.getText();
	}
	
	public String getFiscalNumber() {
		return fiscalNumber.getText();
	}
	
	public Date getDateIn() {
		return dateIn.getDate();
	}
	public boolean getIsCash() {
		return isCash.isSelected();
	}
}
