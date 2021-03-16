package BaseProject;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Dimension;
import java.awt.SystemColor;


public class CourseView {

	private JFrame frame;
	private JTable tabCourses;
	private JTable tabCourseDetails;

	/**
	 * Create the application.
	 */
	public CourseView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Courses");
		frame.setName("Courses");
		frame.setBounds(0, 0, 492, 422);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[][][grow][][][][][][][][]"));
		
		final JLabel lblCourses;

		lblCourses = new JLabel("Select a course to see more information:");
		frame.getContentPane().add(lblCourses, "cell 0 1");

		
		//I include the table in a JScrollPane and set this instead of the table to be able to see the table headers
		tabCourses = new JTable();
		tabCourses.setName("tableCourses");
		tabCourses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabCourses.setDefaultEditor(Object.class, null); //readonly
		JScrollPane tablePanel = new JScrollPane(tabCourses);
		frame.getContentPane().add(tablePanel, "cell 0 2,grow");

		tabCourseDetails = new JTable();
		tabCourseDetails.setName("tabCourseDetails");
		tabCourseDetails.setRowSelectionAllowed(false);
		tabCourseDetails.setDefaultEditor(Object.class, null); //readonly
		tabCourseDetails.setBackground(SystemColor.control);
		JScrollPane tableDetailPanel = new JScrollPane(tabCourseDetails);
		tableDetailPanel.setMinimumSize(new Dimension(350,150));
		tableDetailPanel.setPreferredSize(new Dimension(500,250));
		frame.getContentPane().add(tableDetailPanel, "cell 0 6");
	}

	//Getters and Setters to access from the controller (compact representation)
	public JFrame getFrame() { return this.frame; }
	public JTable getTableCourses() { return this.tabCourses; }
	public JTable getTableCourseDetails() { return this.tabCourseDetails; }
}
