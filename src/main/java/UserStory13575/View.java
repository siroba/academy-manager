package UserStory13575;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Window;


import javax.swing.JButton;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import javax.swing.JTextPane;

import javax.swing.table.TableModel;

import PL53.util.DateTime;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




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
	
	private JTextPane dateTextPane;
	private JButton confirmButton;
	private JTextPane amountPaidTextPane;
	private JTable table;
	private JScrollPane scrollPane;
	
	public View() {
		setBounds(100, 100, 822, 581);
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
		lblNewLabel_4.setBounds(334, 359, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		dateTextPane = new JTextPane();
		dateTextPane.setBounds(413, 353, 200, 19);
		contentPane.add(dateTextPane);
		
		 confirmButton = new JButton("Confirm");
		
		 
		 
		confirmButton.setBounds(266, 438, 149, 21);
		contentPane.add(confirmButton);
		
		amountPaidTextPane = new JTextPane();
		amountPaidTextPane.setBounds(147, 353, 99, 19);
		contentPane.add(amountPaidTextPane);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 92, 788, 224);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

	public Window getFrame() {
		// TODO Auto-generated method stub
		return this.getFrame();
	}

	public JButton getConfirmButton() {
		// TODO Auto-generated method stub
		return this.confirmButton;
	}
	
	public JTextPane getDateTextPane(){
		return this.dateTextPane;
	}
	
	public JTextPane getAmountPaidTextPane() {
		return this.amountPaidTextPane;
	}
	
	public void setAmountPaidTextPane(String string) {
		this.amountPaidTextPane.setText(string);
	}
	
	public void setDateTextPane(DateTime datetime) {
		this.dateTextPane.setText(datetime.toString());
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
}
