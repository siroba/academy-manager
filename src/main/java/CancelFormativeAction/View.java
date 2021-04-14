package CancelFormativeAction;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import PL53.swing.CheckboxTableModel;

import Utils.SwingUtil;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class View extends JFrame {

	// Auto generated serial ID
	private static final long serialVersionUID = -4972701049019946391L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnCancel;
	private JPanel panel;
	private JTable tableCancelledFA;
	private JPanel panel_1;
	private JTable tableRefunds;
	private JScrollPane scrollPane_2;
	private JButton btnRefund;

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1118, 378);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(11, 0, 565, 232);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 27, 565, 171);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblChooseAFormative = new JLabel("Choose a Formative Action to cancel:");
		lblChooseAFormative.setBounds(1, 5, 273, 15);
		panel.add(lblChooseAFormative);
		
		btnCancel = new JButton("Cancel the Formative Action");
		btnCancel.setBounds(153, 207, 258, 25);
		panel.add(btnCancel);
		
		panel_1 = new JPanel();
		panel_1.setBounds(600, 0, 489, 292);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSelectACanceled = new JLabel("Select a canceled Formative Action:");
		lblSelectACanceled.setBounds(8, 5, 273, 15);
		panel_1.add(lblSelectACanceled);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 27, 489, 112);
		panel_1.add(scrollPane_1);
		
		tableCancelledFA = new JTable();
		scrollPane_1.setViewportView(tableCancelledFA);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setEnabled(false);
		scrollPane_2.setBounds(0, 180, 489, 100);
		panel_1.add(scrollPane_2);
		
		tableRefunds = new JTable();
		scrollPane_2.setViewportView(tableRefunds);
		
		btnRefund = new JButton("Refund selected professionals");
		btnRefund.setBounds(739, 292, 211, 23);
		contentPane.add(btnRefund);
	}

	public void setTable(TableModel tm) {
		table.setModel(tm);
		SwingUtil.autoAdjustColumns(table);
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}

	public int getSelected() {
		return table.getSelectedRow();
	}
	public JTable getTableCancelledFA() {
		return tableCancelledFA;
	}
	public void setTableCancelledFA(TableModel tm) {
		tableCancelledFA.setModel(tm);
		SwingUtil.autoAdjustColumns(tableCancelledFA);
	}

	public Integer[] getCheckedProfessionals() {
		List<Integer> l = new ArrayList<Integer>();
		
		for(int i = 0; i<tableRefunds.getRowCount(); i++) {
			if(((CheckboxTableModel) tableRefunds.getModel()).isChecked(i))
				l.add(i);
		}
		
		Integer[] list = new Integer[l.size()];
		return l.toArray(list);
	}
	
	public JTable getTableRefunds() {
		return tableCancelledFA;
	}
	public void setTableRefunds(TableModel tm) {
		tableRefunds.setModel(tm);
		SwingUtil.autoAdjustColumns(tableRefunds);
	}
	
	public void enableRefundsScroll() {
		this.scrollPane_2.setEnabled(true);
	}
	
	public JButton getBtnRefund() {
		return btnRefund;
	}
}
