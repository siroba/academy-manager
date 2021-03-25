package UserStory13579;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.Component;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class View {
	
	private JFrame frame;
	private JCheckBox CBFilterStartDate;
	public JComboBox CBStartDay;
	public JComboBox CBStartMonth;
	public JComboBox CBStartYear;
	private JCheckBox CBFilterEndDate;
	public JComboBox CBEndDay;
	public JComboBox CBEndMonth;
	public JComboBox CBEndYear;
	public JScrollPane tableBalancePanel;
	public JTable tableFormativeActions;
	public JScrollPane tableTotalBalancePanel;
	public JTable tableTotalBalance;

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.setTitle("FinancialBalance");
		frame.setName("FinancialBalance");
		frame.setBounds(0, 0, 1215, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow 10][grow][][]"));
//		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow 10][][][][][grow][][]"));
		
		// Heading 
		JLabel LabelHeading = new JLabel("Check Financial Balance ");
		LabelHeading.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(LabelHeading, "cell 0 0,aligny baseline");
		
//		// Date filter 
//		JLabel LabelFilterDate = new JLabel("Filter by date:");
//		LabelFilterDate.setFont(new Font("Tahoma", Font.BOLD, 11));
//		frame.getContentPane().add(LabelFilterDate, "flowx,cell 0 1");
//		
//		// Start date 
//		JCheckBox CBFilterStartDate = new JCheckBox("Start date:");
//		CBFilterStartDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		frame.getContentPane().add(CBFilterStartDate, "cell 0 1,gapx 23");
//		
//		JComboBox CBStartDay = new JComboBox();
//		CBStartDay.setFont(new Font("Times New Roman", Font.PLAIN, 10));
//		CBStartDay.setModel(new DefaultComboBoxModel(new String[] {"Day", "01", "02 ", "03 ", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20 ", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
//		frame.getContentPane().add(CBStartDay, "cell 0 1");
//		
//		CBStartMonth = new JComboBox();
//		CBStartMonth.setFont(new Font("Tahoma", Font.PLAIN, 10));
//		CBStartMonth.setModel(new DefaultComboBoxModel(new String[] {"Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
//		frame.getContentPane().add(CBStartMonth, "cell 0 1");
//		
//		JComboBox CBStartYear = new JComboBox();
//		CBStartYear.setFont(new Font("Tahoma", Font.PLAIN, 10));
//		CBStartYear.setModel(new DefaultComboBoxModel(new String[] {"Year", "2020", "2021", "2022", "2023", "2024", "2025"}));
//		frame.getContentPane().add(CBStartYear, "cell 0 1");
//		
//		// End date 
//		JCheckBox CBFilterEndDate = new JCheckBox("End date: ");
//		CBFilterEndDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		frame.getContentPane().add(CBFilterEndDate, "flowx,cell 0 2,gapx 100");
//		
//		JComboBox CBEndDay = new JComboBox();
//		CBEndDay.setFont(new Font("Tahoma", Font.PLAIN, 10));
//		CBEndDay.setModel(new DefaultComboBoxModel(new String[] {"Day", "01", "02 ", "03 ", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20 ", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
//		frame.getContentPane().add(CBEndDay, "cell 0 2");
//		
//		JComboBox CBEndMonth = new JComboBox();
//		CBEndMonth.setFont(new Font("Tahoma", Font.PLAIN, 10));
//		CBEndMonth.setModel(new DefaultComboBoxModel(new String[] {"Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
//		frame.getContentPane().add(CBEndMonth, "cell 0 2");
//		
//		JComboBox CBEndYear = new JComboBox();
//		CBEndYear.setFont(new Font("Tahoma", Font.PLAIN, 10));
//		CBEndYear.setModel(new DefaultComboBoxModel(new String[] {"Year", "2020", "2021", "2022", "2023", "2024", "2025"}));
//		frame.getContentPane().add(CBEndYear, "cell 0 2");
//		
//		// Filter by status
//		JLabel LabelFilterStatus = new JLabel("Filter by status:");
//		LabelFilterStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
//		frame.getContentPane().add(LabelFilterStatus, "flowx,cell 0 3");
//			
//		ButtonGroup groupStatus = new ButtonGroup();
//		
//		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
//		frame.getContentPane().add(rdbtnNewRadioButton, "cell 0 3");
//		
//		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
//		frame.getContentPane().add(rdbtnNewRadioButton_1, "cell 0 3");
//		
//		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("New radio button");
//		frame.getContentPane().add(rdbtnNewRadioButton_2, "cell 0 3");
//		
//		groupStatus.add(rdbtnNewRadioButton);
//		groupStatus.add(rdbtnNewRadioButton_1);
//		groupStatus.add(rdbtnNewRadioButton_2);
//		
		// Financial balance 
		JLabel LabelFinancialBalance = new JLabel("Financial balance");
		LabelFinancialBalance.setFont(new Font("Tahoma", Font.BOLD, 12));
		frame.getContentPane().add(LabelFinancialBalance, "cell 0 4");
		
		tableFormativeActions = new JTable();
		tableFormativeActions.setBackground(new Color(255, 255, 255));
		tableFormativeActions.setName("tableFormativeActions");
		tableFormativeActions.setRowSelectionAllowed(false);
		tableFormativeActions.setDefaultEditor(Object.class, null); //readonly
		JScrollPane tablePanel = new JScrollPane(tableFormativeActions);
		frame.getContentPane().add(tablePanel, "cell 0 5,grow");
		
		// Total balance 
		JLabel LabelTotalBalance = new JLabel("Total balance");
		LabelTotalBalance.setFont(new Font("Tahoma", Font.BOLD, 12));
		frame.getContentPane().add(LabelTotalBalance, "cell 0 6");
		
		tableTotalBalance = new JTable();
		tableTotalBalance.setName("tableTotalBalance");
		tableTotalBalance.setRowSelectionAllowed(false);
		tableTotalBalance.setDefaultEditor(Object.class, null); //readonly
		tableTotalBalance.setBackground(Color.WHITE);
		JScrollPane tableTotalBalancePanel = new JScrollPane(tableTotalBalance);
		frame.getContentPane().add(tableTotalBalancePanel, "cell 0 7, grow");
		
	}
	
	//Getters and Setters to access from the controller (compact representation)
	public JFrame getFrame() { return this.frame; }
//	public JCheckBox getFilterStartDay() {return this.CBFilterStartDate; }
//	public JComboBox getStartDay() {return this.CBStartDay; }
//	public JComboBox getStartMonth() {return this.CBStartMonth; }
//	public JComboBox getStartYear() {return this.CBStartYear; }
//	public JCheckBox getFilterEndDay() {return this.CBFilterEndDate; }
//	public JComboBox getEndDay() {return this.CBEndDay; }
//	public JComboBox getEndMonth() {return this.CBEndMonth; }
//	public JComboBox getEndYear() {return this.CBEndYear; }
	public JTable getTableFormativeActionBalance() { return this.tableFormativeActions; }
	public JTable getTableTotalBalance() { return this.tableTotalBalance; }
}