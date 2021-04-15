package CancelFormativeAction;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import PL53.swing.CheckboxTableModel;
import PL53.swing.DateInput;
import PL53.swing.DateTimeInput;
import PL53.util.Date;
import Utils.SwingUtil;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class View extends JFrame {

	// Auto generated serial ID
	private static final long serialVersionUID = -4972701049019946391L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnCancel;
	private JPanel choosePane;
	private JTable tableCancelledFA;
	private JPanel panel_1;
	private JTable tableRefunds;
	private JScrollPane scrollPane_2;
	private JButton btnRefund;
	private JScrollPane cancelTableScrollPane;
	private JScrollPane scrollPane;
	private DateInput dateIn;
	private JTextField address;
	private JLabel lblNewLabel_2;
	private JTextField fiscalNumber;
	private JPanel coiipaPanel;
	private JCheckBox isCash;

	/**
	 * Create the frame.
	 */
	public View() {
		setTitle("Cancel a Formative Action");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1102, 370);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		choosePane = new JPanel();
		choosePane.setBounds(11, 0, 390, 232);
		contentPane.add(choosePane);
		choosePane.setLayout(null);

		cancelTableScrollPane = new JScrollPane();
		cancelTableScrollPane.setBounds(0, 27, 390, 171);
		choosePane.add(cancelTableScrollPane);

		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		cancelTableScrollPane.setViewportView(table);

		JLabel lblChooseAFormative = new JLabel("Choose a Formative Action to cancel:");
		lblChooseAFormative.setBounds(1, 5, 273, 15);
		choosePane.add(lblChooseAFormative);

		btnCancel = new JButton("Cancel the Formative Action");
		btnCancel.setBounds(44, 207, 258, 25);
		choosePane.add(btnCancel);

		panel_1 = new JPanel();
		panel_1.setBounds(426, 0, 426, 292);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblSelectACanceled = new JLabel("Select a canceled Formative Action:");
		lblSelectACanceled.setBounds(8, 5, 273, 15);
		panel_1.add(lblSelectACanceled);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 27, 414, 112);
		panel_1.add(scrollPane);

		tableCancelledFA = new JTable();
		tableCancelledFA.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(tableCancelledFA);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setEnabled(false);
		scrollPane_2.setBounds(0, 180, 414, 100);
		panel_1.add(scrollPane_2);

		tableRefunds = new JTable();
		tableRefunds.setRowSelectionAllowed(false);
		tableRefunds.setFocusable(false);
		scrollPane_2.setViewportView(tableRefunds);

		btnRefund = new JButton("Refund selected professionals");
		btnRefund.setBounds(455, 298, 346, 23);
		contentPane.add(btnRefund);
		
		coiipaPanel = new JPanel();
		coiipaPanel.setBounds(862, 70, 220, 222);
		contentPane.add(coiipaPanel);
		coiipaPanel.setLayout(null);
		
		dateIn = new DateInput();
		dateIn.getYearsTextField().setBound(2000, Integer.MAX_VALUE);
		dateIn.getYearsTextField().setDefaultValue(2021);
		dateIn.setBounds(10, 130, 201, 55);
		coiipaPanel.add(dateIn);
		dateIn.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel lblNewLabel = new JLabel("Date of the invoice:");
		lblNewLabel.setBounds(10, 115, 189, 14);
		coiipaPanel.add(lblNewLabel);
		
		address = new JTextField();
		address.setBounds(10, 25, 196, 20);
		coiipaPanel.add(address);
		address.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("COIIPA's address:");
		lblNewLabel_1.setBounds(10, 0, 152, 14);
		coiipaPanel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("COIIPA's fiscal number:");
		lblNewLabel_2.setBounds(12, 56, 199, 14);
		coiipaPanel.add(lblNewLabel_2);
		
		fiscalNumber = new JTextField();
		fiscalNumber.setBounds(10, 81, 196, 20);
		coiipaPanel.add(fiscalNumber);
		fiscalNumber.setColumns(10);
		
		isCash = new JCheckBox("Cash payment");
		isCash.setBounds(10, 192, 164, 23);
		coiipaPanel.add(isCash);
		dateIn.getYearsTextField().setBound(2021, 3000);
		dateIn.getYearsTextField().setDefaultValue(2021);
	}

	public void setTable(TableModel tm) {
		table.setModel(tm);
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public int getSelected() {
		return table.getSelectedRow();
	}
	
	public int getSelectedCanceled() {
		return tableCancelledFA.getSelectedRow();
	}
	
	public Integer[] getChecked() {
		ArrayList<Integer> l = new ArrayList<Integer>();
		
		for(int row = 0; row<tableRefunds.getRowCount(); row++) {
			if((boolean) tableRefunds.getValueAt(row, 0)) {
				l.add(row);
			}
		}
		
		Integer[] list = new Integer[l.size()];
		list = l.toArray(list);
		return list;
	}

	public JTable getTableCancelledFA() {
		return tableCancelledFA;
	}

	public void setTableCancelledFA(TableModel tm) {
		tableCancelledFA.setModel(tm);
	}

	public Integer[] getCheckedProfessionals() {
		List<Integer> l = new ArrayList<Integer>();

		for (int i = 0; i < tableRefunds.getRowCount(); i++) {
			if (((CheckboxTableModel) tableRefunds.getModel()).isChecked(i))
				l.add(i);
		}

		Integer[] list = new Integer[l.size()];
		return l.toArray(list);
	}

	public JTable getTableRefunds() {
		return tableRefunds;
	}

	public void setTableRefunds(TableModel tm) {
		tableRefunds.setModel(tm);
	}

	public void enableRefundsScroll() {
		this.scrollPane_2.setEnabled(true);
	}

	public JButton getBtnRefund() {
		return btnRefund;
	}

	public JPanel getCancelScrollPane() {
		return choosePane;
	}

	public JScrollPane getCancelTableScrollPane() {
		return cancelTableScrollPane;
	}

	protected JScrollPane getScrollPane_1() {
		return scrollPane;
	}

	protected JScrollPane getScrollPane_2() {
		return scrollPane_2;
	}
	
	public Date getDateIn() {
		return dateIn.getDate();
	}
	public String getAddress() {
		return address.getText();
	}
	public String getFiscalNumber() {
		return fiscalNumber.getText();
	}
	
	public boolean getIsCash() {
		return isCash.isSelected();
	}
	
	public boolean filledCoiipasInfo() {
		return  (address.getText().length()>0) &&
				(fiscalNumber.getText().length()>0);
	}

	public void setCoiipaInfoRed() {
		coiipaPanel.setBorder(BorderFactory.createLineBorder(Color.red));
	}

	public void setCoiipaInfoNormal() {
		coiipaPanel.setBorder(null);
	}
}
