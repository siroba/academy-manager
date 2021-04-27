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
	private JScrollPane scrollPane_1;
	private JTextPane descriptionPane;
	private JPanel descriptionPanel;
	
	public View() {
		
		setBounds(100, 100, 1428, 581);
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
		
	
		
		 confirmButton = new JButton("Add new payment");
		
	
		 
			dateTextPane = new DateInput();
			dateTextPane.getYearsTextField().setBound(2000, Integer.MAX_VALUE);
			dateTextPane.getYearsTextField().setDefaultValue(2021);
			Border blackline = BorderFactory.createLineBorder(Color.black);
			dateTextPane.setBorder(blackline);
			dateTextPane.setBounds(344, 359, 212, 67);
			contentPane.add(dateTextPane);
			
		 
		confirmButton.setBounds(274, 470, 164, 46);
		contentPane.add(confirmButton);
		
		amountPaidTextField = new JDecimalField(9);
		amountPaidTextField.setBound(0.1f, Float.MAX_VALUE);
		amountPaidTextField.setBounds(147, 353, 99, 19);

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
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(845, 93, 370, 223);
		contentPane.add(scrollPane_1);
		
		movementsTable = new JTable();
		scrollPane_1.setViewportView(movementsTable);
		
		descriptionPanel = new JPanel();
		descriptionPanel.setVisible(false);
		descriptionPanel.setEnabled(false);
		descriptionPanel.setBounds(1238, 93, 169, 118);
		contentPane.add(descriptionPanel);
		descriptionPanel.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Description");
		lblNewLabel_6.setBounds(0, 0, 99, 14);
		descriptionPanel.add(lblNewLabel_6);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 21, 169, 97);
		descriptionPanel.add(scrollPane_2);
		
		descriptionPane = new JTextPane();
		scrollPane_2.setViewportView(descriptionPane);
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
	
	public int getSelectedInvoice() {
		return this.table.getSelectedRow();
	}
	
	public boolean isCash() {
		return isCash.isSelected();
	}
	public JTable getMovementsTable() {
		return movementsTable;
	}

	public void setMovementsTable(TableModel model) {
		movementsTable.setModel(model);
	}

	public float getAmountPayed() {
		return amountPaidTextField.getValue();
	}

	public void setDescription(String str) {
		boolean blank = str == null || str.isBlank();
		
		this.descriptionPanel.setVisible(!blank);
		this.descriptionPanel.setEnabled(!blank);
		
		if(!blank)
			this.descriptionPane.setText(str);
	}
}
