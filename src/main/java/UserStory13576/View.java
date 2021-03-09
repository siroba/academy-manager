package UserStory13576;

import java.awt.Dimension;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;

public class View {
	private JFrame frame;
	private JTable tabFormativeActions;
	private JTable tabFormativeActionsDetails;
	private JComboBox<String> cbStatusSelection;
	private JTextField tfEnrollmentStart;
	private JTextField tfEnrollmentEnd;
	private JButton btSearch;
	
	
	public View() {
		initComponents();
	}
	
	private void initComponents() {
		frame = new JFrame();
		frame.setTitle("Courses");
		frame.setName("Courses");
		frame.setBounds(0, 0, 541, 422);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[][grow][grow]"));
		
		cbStatusSelection = new JComboBox<String>();
		cbStatusSelection.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "ACTIVE", "CANCELLED", "EXECUTED"}));
		frame.getContentPane().add(cbStatusSelection, "cell 0 1");
		
		tfEnrollmentStart = new JTextField();
		tfEnrollmentStart.setPreferredSize(new Dimension(140, 22));
		tfEnrollmentStart.setText("2021-01-01");
		frame.getContentPane().add(tfEnrollmentStart, "cell 0 1");
		
		tfEnrollmentEnd = new JTextField();
		tfEnrollmentEnd.setPreferredSize(new Dimension(140, 22));
		tfEnrollmentEnd.setText("2030-11-14");
		frame.getContentPane().add(tfEnrollmentEnd, "cell 0 1");
		
		btSearch = new JButton("Search");
		frame.getContentPane().add(btSearch, "cell 0 1");
		
		tabFormativeActions = new JTable();
		tabFormativeActions.setName("tableCourses");
		tabFormativeActions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabFormativeActions.setDefaultEditor(Object.class, null); //readonly
		JScrollPane tablePanel = new JScrollPane(tabFormativeActions);
		frame.getContentPane().add(tablePanel, "cell 0 2, grow");
		
		tabFormativeActionsDetails = new JTable();
		tabFormativeActionsDetails.setName("tabCourseDetails");
		tabFormativeActionsDetails.setRowSelectionAllowed(false);
		tabFormativeActionsDetails.setDefaultEditor(Object.class, null); //readonly
		tabFormativeActionsDetails.setBackground(SystemColor.control);
		JScrollPane tableDetailPanel = new JScrollPane(tabFormativeActionsDetails);
		frame.getContentPane().add(tableDetailPanel, "cell 0 3, grow");
	}
	
	//Getters to access from the controller (compact representation)
	public JFrame getFrame() { return this.frame; }
	public JTable getTableFormativeActions() { return this.tabFormativeActions; }
	public JTable getTableFormativeActionDetails() { return this.tabFormativeActionsDetails; }
	public JComboBox<String> getComboBoxStatusSelection() {return this.cbStatusSelection; }
	public JTextField getEnrollmentStart() { return this.tfEnrollmentStart; }
	public JTextField getEnrollmentEnd() { return this.tfEnrollmentEnd; }
	public JButton getButtonSearch() { return this.btSearch; }

}
