package CheckFinancialBalance;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.border.Border;

import PL53.swing.DateInput;
import PL53.util.Date;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;


public class View {
	
	private JFrame frame;
	public JScrollPane tableBalancePanel;
	public JTable tableFormativeActions;
	public JScrollPane tableTotalBalancePanel;
	public JTable tableTotalBalance;
	private DateInput start, end;
	private JComboBox<String> status; 
	private JButton btnApplyFilter;
	private JCheckBox chckbxStartDate, chckbxEndDate, chckbxStatus; 

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
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.setTitle("Financial Balance");
		frame.setName("FinancialBalance");
		frame.setBounds(0, 0, 1215, 400);
		frame.setLocationRelativeTo(null);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow][][][][][][][][]"));
		
		// Heading 
		JLabel LabelHeading = new JLabel("Check Financial Balance ");
		LabelHeading.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(LabelHeading, "cell 0 0,alignx center,aligny center, height 50!");
		
		// Filter 
		JLabel lblFilter = new JLabel("Filter:");
		frame.getContentPane().add(lblFilter, "flowx,cell 0 1");
			
		// Filter by start date 
		chckbxStartDate = new JCheckBox("Start date:");
		chckbxStartDate.setSelected(true);
		chckbxStartDate.setFont(new Font("Dialog", Font.PLAIN, 12));
		frame.getContentPane().add(chckbxStartDate, "cell 0 1,gapx 50");
		start = new DateInput();
		int currentYear = Date.now().getYear();
		frame.getContentPane().add(start, "cell 0 1,gapx 10,height 50!" );
		start.getYearsTextField().setBound(2000, 3000);
		start.getYearsTextField().setDefaultValue(currentYear);
		start.getDaysTextField().setBound(1, 31);
		start.getDaysTextField().setDefaultValue(1);
		start.getMonthsTextField().setBound(1, 12);
		start.getMonthsTextField().setDefaultValue(1);
		start.setBorder(blackline);
		
		// Filter by end date
		chckbxEndDate = new JCheckBox("End date:  ");
		chckbxEndDate.setSelected(true);
		chckbxEndDate.setFont(new Font("Dialog", Font.PLAIN, 12));
		frame.getContentPane().add(chckbxEndDate, "cell 0 1,gapx 50");
		end = new DateInput();
		frame.getContentPane().add(end, "cell 0 1,gapx 10,height 50!" );
		end.getYearsTextField().setBound(2000, 3000);
		end.getYearsTextField().setDefaultValue(currentYear);
		end.getDaysTextField().setBound(1, 31);
		end.getDaysTextField().setDefaultValue(31);
		end.getMonthsTextField().setBound(1, 12);
		end.getMonthsTextField().setDefaultValue(12);
		end.setBorder(blackline);
		
		// Filter by status
		chckbxStatus = new JCheckBox("Status:");
		chckbxStatus.setFont(new Font("Dialog", Font.PLAIN, 12));
		frame.getContentPane().add(chckbxStatus, "cell 0 1,gapx 50,height 35!");
		status = new JComboBox<String>();
		status.setFont(new Font("Dialog", Font.PLAIN, 12));
		status.setModel(new DefaultComboBoxModel<String>(new String[] {"Active", "Executed", "Cancelled"}));
		frame.getContentPane().add(status, "cell 0 1,gapx 10");
		
		// Apply filter button 
		btnApplyFilter = new JButton("Apply Filter");
		frame.getContentPane().add(btnApplyFilter, "cell 0 1, gapx 70");
		
		// Financial balance 
		JLabel LabelFinancialBalance = new JLabel("Financial balance");
		LabelFinancialBalance.setFont(new Font("Tahoma", Font.BOLD, 12));
		frame.getContentPane().add(LabelFinancialBalance, "cell 0 5");
		
		tableFormativeActions = new JTable();
		tableFormativeActions.setBackground(new Color(255, 255, 255));
		tableFormativeActions.setName("tableFormativeActions");
		tableFormativeActions.setRowSelectionAllowed(false);
		tableFormativeActions.setDefaultEditor(Object.class, null); //readonly
		JScrollPane tablePanel = new JScrollPane(tableFormativeActions);
		frame.getContentPane().add(tablePanel, "cell 0 6,grow");
		
		// Total balance 
		JLabel LabelTotalBalance = new JLabel("Total balance");
		LabelTotalBalance.setFont(new Font("Tahoma", Font.BOLD, 12));
		frame.getContentPane().add(LabelTotalBalance, "cell 0 7");
		
		tableTotalBalance = new JTable();
		tableTotalBalance.setName("tableTotalBalance");
		tableTotalBalance.setRowSelectionAllowed(false);
		tableTotalBalance.setDefaultEditor(Object.class, null); //readonly
		tableTotalBalance.setBackground(Color.WHITE);
		JScrollPane tableTotalBalancePanel = new JScrollPane(tableTotalBalance);
		tableTotalBalancePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(tableTotalBalancePanel, "cell 0 8, height 38!, width 654!");
		
		
	}
	
	//Getters and Setters to access from the controller (compact representation)
	public JFrame getFrame() { return this.frame; }
	public JTable getTableFormativeActionBalance() { return this.tableFormativeActions; }
	public JTable getTableTotalBalance() { return this.tableTotalBalance; }
	public Date getStartDate() {return start.getDate();}
	public Date getEndDate() {return end.getDate();}
	public String getStatus() { return this.status.getSelectedItem().toString(); }
	public boolean getFilterStartDate() {return this.chckbxStartDate.isSelected(); }
	public boolean getFilterEndDate() {return this.chckbxEndDate.isSelected(); }
	public boolean getFilterStatus() {return this.chckbxStatus.isSelected(); }
	public JButton getBtnApplyFilter() { return this.btnApplyFilter; }
}