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
	
	/**
	 * Create the frame.
	 */
	public View() {
		setTitle("Select an enrollment to cancel");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 867, 337);
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
		
		btnCancelRegistration = new JButton("Cancel registration");
		btnCancelRegistration.setBounds(329, 263, 207, 25);
		contentPane.add(btnCancelRegistration);
		
		JLabel lblNewLabel_1 = new JLabel("COIIPA's address:");
		lblNewLabel_1.setBounds(20, 149, 96, 14);
		contentPane.add(lblNewLabel_1);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(20, 174, 206, 20);
		contentPane.add(address);
		
		JLabel lblNewLabel_2 = new JLabel("COIIPA's fiscal number:");
		lblNewLabel_2.setBounds(236, 149, 151, 14);
		contentPane.add(lblNewLabel_2);
		
		fiscalNumber = new JTextField();
		fiscalNumber.setColumns(10);
		fiscalNumber.setBounds(236, 174, 206, 20);
		contentPane.add(fiscalNumber);
		
		JLabel lblNewLabel = new JLabel("Date of the invoice:");
		lblNewLabel.setBounds(452, 147, 113, 14);
		contentPane.add(lblNewLabel);
		
		dateIn = new DateInput();
		dateIn.setBorder(BorderFactory.createLineBorder(Color.black));
		dateIn.getYearsTextField().setBound(2021, 3000);
		dateIn.getYearsTextField().setDefaultValue(2021);
		dateIn.setBounds(452, 165, 211, 55);
		contentPane.add(dateIn);
		
		isCash = new JCheckBox("Cash payment");
		isCash.setBounds(703, 173, 97, 23);
		contentPane.add(isCash);
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
