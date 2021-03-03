package UserStory13576;

import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;

public class View {
	private JFrame frame;
	private JTable tabFormativeActions;
	private JTable tabFormativeActionsDetails;
	
	public View() {
		initComponents();
	}
	
	private void initComponents() {
		frame = new JFrame();
		frame.setTitle("Courses");
		frame.setName("Courses");
		frame.setBounds(0, 0, 541, 422);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		tabFormativeActions = new JTable();
		tabFormativeActions.setName("tableCourses");
		tabFormativeActions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabFormativeActions.setDefaultEditor(Object.class, null); //readonly
		JScrollPane tablePanel = new JScrollPane(tabFormativeActions);
		frame.getContentPane().add(tablePanel, "cell 0 1, grow");
		
		tabFormativeActionsDetails = new JTable();
		tabFormativeActionsDetails.setName("tabCourseDetails");
		tabFormativeActionsDetails.setRowSelectionAllowed(false);
		tabFormativeActionsDetails.setDefaultEditor(Object.class, null); //readonly
		tabFormativeActionsDetails.setBackground(SystemColor.control);
		JScrollPane tableDetailPanel = new JScrollPane(tabFormativeActionsDetails);
//		tableDetailPanel.setMinimumSize(new Dimension(350,150));
//		tableDetailPanel.setPreferredSize(new Dimension(500,250));
		frame.getContentPane().add(tableDetailPanel, "cell 0 2, grow");
	}
	
	//Getters to access from the controller (compact representation)
	public JFrame getFrame() { return this.frame; }
	public JTable getTableFormativeActions() { return this.tabFormativeActions; }
	public JTable getTableFormativeActionDetails() { return this.tabFormativeActionsDetails; }

}
