package RegisterPayment;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import javax.swing.JTextPane;

import javax.swing.table.TableModel;

import PL53.swing.DateInput;
import PL53.swing.DateTimeInput;
import PL53.swing.JDecimalField;
import PL53.swing.JNumberField;
import PL53.util.DateTime;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;




public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1470469012765627954L;
	private JPanel contentPane;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	
	private DateInput dateTextPane;
	private JButton confirmButton;
	private JDecimalField amountPaidTextField;
	private JTable table;
	private JScrollPane scrollPane;
	private JCheckBox isCash;
	private JTable movementsTable;
	
	public View() {
		
		setBounds(100, 100, 1602, 581);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Register Payments");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(225, 28, 254, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Pending Payments");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(34, 69, 193, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Information about the selected payment");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(34, 326, 288, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Amonut paid");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_3.setBounds(35, 359, 87, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Date");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_4.setBounds(294, 359, 45, 13);
		contentPane.add(lblNewLabel_4);
		
	
		
		 confirmButton = new JButton("Confirm");
		
	
		 
			dateTextPane = new DateInput();
			Border blackline = BorderFactory.createLineBorder(Color.black);
			dateTextPane.setBorder(blackline);
			dateTextPane.setBounds(344, 359, 408, 67);
			contentPane.add(dateTextPane);
			
		 
		confirmButton.setBounds(264, 470, 164, 46);
		contentPane.add(confirmButton);
		
		amountPaidTextField = new JDecimalField(2);
		amountPaidTextField.setBounds(147, 353, 99, 19);
		amountPaidTextField.setBound( 0.f,Float.MAX_VALUE);

		contentPane.add(amountPaidTextField);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 92, 788, 224);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		isCash = new JCheckBox("Cash payment");
		isCash.setBounds(34, 393, 212, 21);
		contentPane.add(isCash);
		
		JLabel lblNewLabel_5 = new JLabel("Movements");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_5.setBounds(860, 70, 140, 13);
		contentPane.add(lblNewLabel_5);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(812, 92, 788, 224);
		contentPane.add(scrollPane_1);
		
		movementsTable = new JTable();
		scrollPane_1.setRowHeaderView(movementsTable);
		
		JButton addPayment = new JButton("Add new payment");
		addPayment.setBounds(1024, 470, 183, 34);
		contentPane.add(addPayment);
		
		DateInput dateTextPane_1 = new DateInput();
		dateTextPane_1.setBounds(1158, 359, 311, 67);
		contentPane.add(dateTextPane_1);
		
		JDecimalField amountPaidTextField_1 = new JDecimalField(2);
		amountPaidTextField_1.setBounds(989, 356, 99, 19);
		contentPane.add(amountPaidTextField_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Amonut paid");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_3_1.setBounds(874, 359, 87, 13);
		contentPane.add(lblNewLabel_3_1);
		
		JCheckBox isCash_1 = new JCheckBox("Cash payment");
		isCash_1.setBounds(874, 405, 159, 21);
		contentPane.add(isCash_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Date");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_4_1.setBounds(1109, 359, 45, 13);
		contentPane.add(lblNewLabel_4_1);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(810, 0, 2, 560);
		contentPane.add(separator);
	}

	public Window getFrame() {
		// TODO Auto-generated method stub
		return this.getFrame();
	}

	public JButton getConfirmButton() {
		// TODO Auto-generated method stub
		return this.confirmButton;
	}
	
	public DateInput getDateTextPane(){
		return this.dateTextPane;
	}
	
	
	
	public float getAmountPaidTextField() {
		return this.amountPaidTextField.getValue();
	}

	
	public void setAmountPaidTextField(String string) {
		this.amountPaidTextField.setText(string);
	}
	
	public void setDateTextPane(DateTime datetime) {
		this.dateTextPane.setToolTipText(datetime.toString());
	}
	

	public JTable getTable() {
		return table;
	}
	
	public void setTable(TableModel tm) {
		this.table.setModel(tm);
	}
	
	public int getSelected() {
		return this.table.getSelectedRow();
	}
	
	public boolean isCash() {
		return isCash.isSelected();
	}
	public JTable getMovementsTable() {
		return movementsTable;
	}
}
