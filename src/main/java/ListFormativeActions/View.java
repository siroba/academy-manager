package ListFormativeActions;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

import PL53.swing.DateInput;
import PL53.util.Date;
import net.miginfocom.swing.MigLayout;

public class View {
	private JFrame frame;
	private JTable tabFormativeActions;
	private JTable tabFormativeActionsDetails;
	private JComboBox<String> cbStatusSelection;
	private JButton btSearch;
	private JCheckBox cbFilterStatus, cbFilterStartDate, cbFilterEndDate;
	private DateInput start, end;

	public View() {
		initComponents();
	}

	private void initComponents() {

		Border blackline = BorderFactory.createLineBorder(Color.black);

		frame = new JFrame();
		frame.setTitle("Courses");
		frame.setName("Courses");
		frame.setBounds(0, 0, 1000, 422);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[][grow][grow]"));

		// Filter by status
		cbFilterStatus = new JCheckBox("Status:");
		cbFilterStatus.setFont(new Font("Dialog", Font.PLAIN, 12));
		frame.getContentPane().add(cbFilterStatus, "cell 0 1,gapx 10,height 35!");
		cbStatusSelection = new JComboBox<String>();
		cbStatusSelection.setFont(new Font("Dialog", Font.PLAIN, 12));
		cbStatusSelection
				.setModel(new DefaultComboBoxModel<String>(new String[] { "Active", "Executed", "Cancelled" }));
		frame.getContentPane().add(cbStatusSelection, "cell 0 1,gapx 10");

		// Filter by start date
		cbFilterStartDate = new JCheckBox("Start date:");
		cbFilterStartDate.setSelected(false);
		cbFilterStartDate.setFont(new Font("Dialog", Font.PLAIN, 12));
		frame.getContentPane().add(cbFilterStartDate, "cell 0 1,gapx 25");
		start = new DateInput();
		int currentYear = Date.now().getYear();
		frame.getContentPane().add(start, "cell 0 1,gapx 10,height 50!");
		start.getYearsTextField().setBound(2000, 3000);
		start.getYearsTextField().setDefaultValue(currentYear);
		start.getDaysTextField().setBound(1, 31);
		start.getDaysTextField().setDefaultValue(1);
		start.getMonthsTextField().setBound(1, 12);
		start.getMonthsTextField().setDefaultValue(1);
		start.setBorder(blackline);

		// Filter by end date
		cbFilterEndDate = new JCheckBox("End date:");
		cbFilterEndDate.setSelected(false);
		cbFilterEndDate.setFont(new Font("Dialog", Font.PLAIN, 12));
		frame.getContentPane().add(cbFilterEndDate, "cell 0 1,gapx 25");
		end = new DateInput();
		frame.getContentPane().add(end, "cell 0 1,gapx 10,height 50!");
		end.getYearsTextField().setBound(2000, 3000);
		end.getYearsTextField().setDefaultValue(currentYear);
		end.getDaysTextField().setBound(1, 31);
		end.getDaysTextField().setDefaultValue(1);
		end.getMonthsTextField().setBound(1, 12);
		end.getMonthsTextField().setDefaultValue(1);
		end.setBorder(blackline);

		btSearch = new JButton("Search");
		frame.getContentPane().add(btSearch, "cell 0 1, gapx 25");

		tabFormativeActions = new JTable();
		tabFormativeActions.setName("tableCourses");
		tabFormativeActions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabFormativeActions.setDefaultEditor(Object.class, null); // readonly
		JScrollPane tablePanel = new JScrollPane(tabFormativeActions);
		frame.getContentPane().add(tablePanel, "cell 0 2, grow");

		tabFormativeActionsDetails = new JTable();
		tabFormativeActionsDetails.setName("tabCourseDetails");
		tabFormativeActionsDetails.setRowSelectionAllowed(false);
		tabFormativeActionsDetails.setDefaultEditor(Object.class, null); // readonly
		tabFormativeActionsDetails.setBackground(SystemColor.control);
		JScrollPane tableDetailPanel = new JScrollPane(tabFormativeActionsDetails);
		frame.getContentPane().add(tableDetailPanel, "cell 0 3, grow");
	}

	// Getters to access from the controller (compact representation)
	public JFrame getFrame() {
		return this.frame;
	}

	public JTable getTableFormativeActions() {
		return this.tabFormativeActions;
	}

	public JTable getTableFormativeActionDetails() {
		return this.tabFormativeActionsDetails;
	}

	public JComboBox<String> getComboBoxStatusSelection() {
		return this.cbStatusSelection;
	}

	public JButton getButtonSearch() {
		return this.btSearch;
	}

	public JCheckBox getCheckBoxFilterStatus() {
		return this.cbFilterStatus;
	}

	public JCheckBox getCheckBoxFilterStartDate() {
		return this.cbFilterStartDate;
	}

	public JCheckBox getCheckBoxFilterEndDate() {
		return this.cbFilterEndDate;
	}

	public DateInput getStart() {
		return this.start;
	}

	public DateInput getEnd() {
		return this.end;
	}

}
