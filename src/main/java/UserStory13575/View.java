package UserStory13575;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entities.FormativeAction;
import Entities.Payment;
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
import java.awt.Color;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


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
	private JList<String> list;
	private JScrollPane scrollPane_1;
	
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Register Payments");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(88, 29, 254, 20);
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
		lblNewLabel_4.setBounds(34, 406, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		dateTextPane = new JTextPane();
		dateTextPane.setBounds(147, 406, 99, 19);
		contentPane.add(dateTextPane);
		
		 confirmButton = new JButton("Confirm");
		 
		 
		confirmButton.setBounds(157, 469, 149, 21);
		contentPane.add(confirmButton);
		
		amountPaidTextPane = new JTextPane();
		amountPaidTextPane.setBounds(147, 353, 99, 19);
		contentPane.add(amountPaidTextPane);
		 
		 scrollPane_1 = new JScrollPane();
		 scrollPane_1.setBounds(34, 92, 407, 205);
		 contentPane.add(scrollPane_1);
		 
		 list = new JList();
		 scrollPane_1.setViewportView(list);
		 list.setBackground(Color.WHITE);
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
	
	//TODO complete things for the list
	public void setList(List<Data> list2) {
DefaultListModel<String> listModel = new DefaultListModel<String>();        
        
        for(Data pa: list2) 
            listModel.addElement(pa.payment.getReceiver());
        
        this.list.setModel(listModel);
	}
	public JList<String> getList() {
		return this.list;
	}
	
}
