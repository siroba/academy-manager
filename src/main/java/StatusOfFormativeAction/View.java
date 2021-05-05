package StatusOfFormativeAction;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;

public class View {
	private JFrame frame;
	private JTable tabRegistration;
	private JLabel lblActive;
	private JTable tabFormativeActions;
	private JTable tabPayments;
	private JLabel lblSummary;
	private JTable tabTeacher;
	
	public View() {
		initComponents();
	}
	
	private void initComponents() {
		frame = new JFrame();
		frame.setTitle("Status of Formative Actions");
		frame.setName("Status of Formative Actions");
		frame.setBounds(0, 0, 900, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[][200:250:][200:250:][200:250:][40]"));
		
		lblActive = new JLabel();
		lblActive.setText(" ");
		lblActive.setPreferredSize(new Dimension(100 ,22));
		frame.getContentPane().add(lblActive, "cell 0 0, grow");
		
		tabFormativeActions = new JTable();
		tabFormativeActions.setName("tabListFormativeActions");
		tabFormativeActions.setDefaultEditor(Object.class, null);
		tabFormativeActions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane tableListFormativeActions = new JScrollPane(tabFormativeActions);
		frame.getContentPane().add(tableListFormativeActions, "cell 0 1 2 1, grow");
		
		tabRegistration = new JTable();
		tabRegistration.setDefaultEditor(Object.class, null);
		//tabRegistration.setPreferredScrollableViewportSize(null);
		tabRegistration.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane tableRegPanel = new JScrollPane(tabRegistration);
		frame.getContentPane().add(tableRegPanel, "cell 0 2, grow");
		
		tabTeacher = new JTable();
		tabTeacher.setDefaultEditor(Object.class, null);
		tabTeacher.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		frame.getContentPane().add(new JScrollPane(tabTeacher), "cell 1 2, grow");
		
		tabPayments = new JTable();
		tabPayments.setDefaultEditor(Object.class, null);
		tabPayments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane tablePayments = new JScrollPane(tabPayments);
		frame.getContentPane().add(tablePayments, "cell 0 3 2 1, grow");
		
		frame.getContentPane().add(new JLabel("Summary: "), "cell 0 4");
		lblSummary = new JLabel();
		frame.getContentPane().add(lblSummary, "cell 0 4");
	}
	
	//Getters to access from the controller (compact representation)
	public JFrame getFrame() { return this.frame; }
	public JTable getTableRegistrations() { return this.tabRegistration; }
	public JLabel getLabelActive() { return this.lblActive; }
	public JTable getTableFormativeActions() { return this.tabFormativeActions; }
	public JTable getTablePayments() { return this.tabPayments; }
	public JLabel getLabelSummary() { return this.lblSummary; }
	public JTable getTableTeacher() { return this.tabTeacher; }
}