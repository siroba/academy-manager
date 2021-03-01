package UserStory13573;

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


public class View13573 {

	private JFrame frame;
	private JTextField TFName;
	private JTextField TFContents;
	private JTextField TFTeacher;
	private JTextField TFRemuneration;
	private JTextField TFLocation;
	private JTextField TFDay;
	private JTextField TFHours;
	private JTextField TFSpaces;
	private JLabel LabelObjectives;
	private JTextField TFObjectives;
	private JButton BTNCreate;
	private JLabel lblNewLabel_1;
	private JTextField TFStartEnroll;
	private JTextField TFEndEnroll;
	private JLabel LabelWarningStartEnroll;

	/**
	 * Create the application.
	 */
	public View13573() {
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
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow][][][][][][][][][][][][grow]"));
		
		JLabel lblNewLabel = new JLabel("Plan a formative action");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(lblNewLabel, "cell 0 0,alignx center,aligny baseline");
		
		JLabel LabelName = new JLabel("Name:");
		LabelName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelName, "flowx,cell 0 1,gapx 20,aligny center");
		
		TFName = new JTextField();
		TFName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(TFName, "cell 0 1");
		TFName.setColumns(10);
		
		LabelObjectives = new JLabel("Objectives:");
		LabelObjectives.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelObjectives, "flowx,cell 0 2,gapx 20,aligny bottom");
		
		JLabel LabelContents = new JLabel("Main contents:");
		LabelContents.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelContents, "flowx,cell 0 3,gapx 20");
		
		TFContents = new JTextField();
		TFContents.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(TFContents, "cell 0 3");
		TFContents.setColumns(10);
		
		JLabel LabelTeacher = new JLabel("Teacher:");
		LabelTeacher.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelTeacher, "flowx,cell 0 4,gapx 20");
		
		TFTeacher = new JTextField();
		TFTeacher.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(TFTeacher, "cell 0 4");
		TFTeacher.setColumns(10);
		
		JLabel LabelRemuneration = new JLabel("Remuneration:");
		LabelRemuneration.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelRemuneration, "flowx,cell 0 5,gapx 20");
		
		TFRemuneration = new JTextField();
		TFRemuneration.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(TFRemuneration, "cell 0 5");
		TFRemuneration.setColumns(10);
		
		JLabel LabelLocation = new JLabel("Location:");
		LabelLocation.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelLocation, "flowx,cell 0 6,gapx 20");
		
		JLabel LabelDate = new JLabel("Date:");
		LabelDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelDate, "flowx,cell 0 7,gapx 20,aligny top");
		
		TFLocation = new JTextField();
		TFLocation.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(TFLocation, "cell 0 6");
		TFLocation.setColumns(10);
		
		TFDay = new JTextField();
		TFDay.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(TFDay, "cell 0 7");
		TFDay.setColumns(10);
		
		JLabel LabelHours = new JLabel("Number of hours:");
		LabelHours.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelHours, "flowx,cell 0 8,gapx 20");
		
		JLabel LabelSpaces = new JLabel("Spaces:");
		LabelSpaces.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelSpaces, "flowx,cell 0 9,gapx 20");
		
		TFHours = new JTextField();
		TFHours.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(TFHours, "cell 0 8");
		TFHours.setColumns(10);
		
		TFSpaces = new JTextField();
		TFSpaces.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(TFSpaces, "cell 0 9");
		TFSpaces.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Start of enrollment period:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(lblNewLabel_1, "flowx,cell 0 10,gapx 20,aligny top");
		
		JLabel LabelEndEnroll = new JLabel("End of enrollment period:");
		LabelEndEnroll.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(LabelEndEnroll, "flowx,cell 0 11,gapx 20");
		
		TFObjectives = new JTextField();
		TFObjectives.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(TFObjectives, "cell 0 2");
		TFObjectives.setColumns(10);
		
		BTNCreate = new JButton("Create formative action");
		BTNCreate.setFont(new Font("Tahoma", Font.BOLD, 11));
		frame.getContentPane().add(BTNCreate, "cell 0 12,alignx center");
		
		TFStartEnroll = new JTextField();
		TFStartEnroll.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(TFStartEnroll, "cell 0 10");
		TFStartEnroll.setColumns(10);
		
		TFEndEnroll = new JTextField();
		TFEndEnroll.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(TFEndEnroll, "cell 0 11");
		TFEndEnroll.setColumns(10);
		
		LabelWarningStartEnroll = new JLabel("  ");
		LabelWarningStartEnroll.setForeground(Color.RED);
		frame.getContentPane().add(LabelWarningStartEnroll, "cell 0 10,gapx 30");
		
	}

	//Getters and Setters to access from the controller (compact representation)
	public JFrame getFrame() { return this.frame; }
	public JButton getCreateBtn() {return this.BTNCreate;}
	public String getName() {return this.TFName.getText();}
	public String getObjectives() {return this.TFObjectives.getText();}
	public String getMainContents() {return this.TFContents.getText();}
	public String getTeacher() {return this.TFTeacher.getText();}
	public String getRemuneration() {return this.TFRemuneration.getText();}
	public String getLocation() {return this.TFLocation.getText();}
	public String getSpaces() {return this.TFSpaces.getText();}
	public String getDay() {return this.TFDay.getText();}
	public String getNumberOfHours() {return this.TFHours.getText();}
	public String getEnrollmentPeriodStart() {return this.TFStartEnroll.getText();}
	public String getEnrollmentPeriodEnd() {return this.TFEndEnroll.getText();}
	public void setWarningEnrollmentPeriodStart(String warning)  { this.LabelWarningStartEnroll.setText(warning); }
}
