package StatusOfFormativeAction;

import java.awt.Dimension;
import java.awt.SystemColor;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import net.miginfocom.swing.MigLayout;

public class View {
	private JFrame frame;
	private JTable tabFormativeActions;
	private JTable tabFormativeActionsDetails;
	private JTable tabRegistration;
	private JList<String> listFormativeActions;
	private JLabel lblActive;
	
	
	public View() {
		initComponents();
	}
	
	private void initComponents() {
		frame = new JFrame();
		frame.setTitle("Courses");
		frame.setName("Courses");
		frame.setBounds(0, 0, 1000, 350);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		lblActive = new JLabel();
		lblActive.setText(" ");
		lblActive.setPreferredSize(new Dimension(100 ,22));
		frame.getContentPane().add(lblActive, "cell 0 1, grow");
		
		listFormativeActions = new JList<String>();
		listFormativeActions.setModel(new DefaultListModel<String>());
		JScrollPane listPanel = new JScrollPane(listFormativeActions);
		listPanel.setMinimumSize(new Dimension(100, 200));
		frame.getContentPane().add(listPanel, "cell 0 2, grow, width 150!");
				
		tabFormativeActionsDetails = new JTable();
		tabFormativeActionsDetails.setName("tabCourseDetails");
		tabFormativeActionsDetails.setEnabled(false);
		tabFormativeActionsDetails.setDefaultEditor(Object.class, null); //readonly
		tabFormativeActionsDetails.setBackground(SystemColor.control);
		JScrollPane tableDetailPanel = new JScrollPane(tabFormativeActionsDetails);
		frame.getContentPane().add(tableDetailPanel, "cell 1 2, grow, width 250::");
		
		tabRegistration = new JTable();
		tabRegistration.setDefaultEditor(Object.class, null);
		tabRegistration.setPreferredScrollableViewportSize(null);
		JScrollPane tableRegPanel = new JScrollPane(tabRegistration);
		frame.getContentPane().add(tableRegPanel, "cell 2 2, grow, width 500::");
	}
	
	//Getters to access from the controller (compact representation)
	public JFrame getFrame() { return this.frame; }
	public JTable getTableFormativeActions() { return this.tabFormativeActions; }
	public JTable getTableFormativeActionDetails() { return this.tabFormativeActionsDetails; }
	public JTable getTableRegistrations() { return this.tabRegistration; }
	public JList<String> getListFormativeActions() { return this.listFormativeActions; }
	public JLabel getLabelActive() { return this.lblActive; }
}
