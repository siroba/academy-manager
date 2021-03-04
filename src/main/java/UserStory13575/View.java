package UserStory13575;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entities.FormativeAction;
import PL53.SI2020_PL53.DateTime;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;


public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1470469012765627954L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTable table;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	
	private JTextPane dateTextPane;
	private JButton confirmButton;
	private JTextPane amountPaidTextPane;
	
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 791, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Pending Payments for Available Formative Actions");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(22, 29, 705, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Payments");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(33, 94, 76, 13);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 250, 193, -89);
		contentPane.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(33, 129, 309, 230);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Payment", "Course Name", "Professional Name", "email", "date"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(107);
		table.getColumnModel().getColumn(2).setPreferredWidth(143);
		table.getColumnModel().getColumn(3).setPreferredWidth(114);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		table.setToolTipText("");
		scrollPane_1.setViewportView(table);
		
		JLabel lblNewLabel_2 = new JLabel("Information about the selected payment");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(376, 95, 288, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Amonut paid");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_3.setBounds(376, 130, 87, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Date");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_4.setBounds(393, 178, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		dateTextPane = new JTextPane();
		dateTextPane.setBounds(476, 178, 96, 19);
		contentPane.add(dateTextPane);
		
		 confirmButton = new JButton("Confirm");
		 
		 
		confirmButton.setBounds(438, 226, 85, 21);
		contentPane.add(confirmButton);
		
		amountPaidTextPane = new JTextPane();
		amountPaidTextPane.setBounds(485, 129, 87, 19);
		contentPane.add(amountPaidTextPane);
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
	
	

	
}
