package CreateFormativeAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Entities.Fee;
import Entities.FormativeAction;
import Entities.Session;
import Entities.Teacher;
import Entities.TeacherTeaches;
import PL53.swing.DateInput.DateModifiedListener;
import PL53.util.DateTime;
import Utils.SwingUtil;

public class Controller implements PL53.util.Controller {
	private Model model;
	private View view;
	private List<Session> sessions = new ArrayList<Session>();
	private List<Fee> fees = new ArrayList<Fee>();
	private List<TeacherTeaches> selectedTeachers = new ArrayList<TeacherTeaches>();
	private List<Teacher> availableTeachers = new ArrayList<Teacher>();

	public Controller(Model m, View v){
		this.model = m;
		this.view = v;

		try {
			availableTeachers = model.getAllTeachers();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// no model-specific initialization, only view-specific initialization
		this.initView();
	}

	/**
	 * Controller initialization: add event handlers to the UI objects. Each event
	 * handler is instantiated in the same way, so that it invokes a private method
	 * of this handler, enclosed in a generic exception handler to display windows.
	 * Each event handler is instantiated in the same way, so that it invokes a
	 * private method of this controller, enclosed in a generic exception handler to
	 * display popup windows when a problem or exception occurs. popup windows when
	 * a problem or controlled exception occurs.
	 */
	public void initController() {
		view.getCreateBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// does not use mouseClicked because when setting single selection in the race
				// table
				// the user could drag the mouse over several rows and only the last one is of
				// interest.
				SwingUtil.exceptionWrapper(() -> createFormativeAction());
			}
		});

		// Add session
		view.getBtnAddSession().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Get dates of sessions, start of enrollment period & end of enrollment period
				List<DateTime> datesFormativeAction = new ArrayList<DateTime>();
				for (int i = 0; i < sessions.size(); i++) {
					datesFormativeAction.add(sessions.get(i).getSessionStart());
				}
				datesFormativeAction.add(view.getSessionDatetime());

				// Validate dates
				if (validateDates(datesFormativeAction, view.getEnrollStart(), view.getEnrollEnd())) {
					// Add session to table
					sessions.add(new Session(view.getLocation(), view.getNumberOfHours(), view.getSessionDatetime()));

					view.setTable(getTableModel(sessions));
				}
			}
		});
		
		view.getBtnAddTeacher().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int teacher = view.getSelectedAvailableTeacher();
				
				if(teacher == -1)
					return;
				
				float rem = view.getRemuneration();
				
				TeacherTeaches tt = new TeacherTeaches(availableTeachers.get(teacher), null, rem);
				
				selectedTeachers.add(tt);
				availableTeachers.remove(teacher);
				
				view.setTableTeachers(getTableModelTeachers(selectedTeachers));
				view.setAvailableTeachers(availableTeachers);
				view.resetRemuneration();
				

				view.getBtnAddTeacher().setEnabled(availableTeachers.size() > 0);
			}
		});
		
		view.getBtnRemoveTeacher().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int teacher = view.getSelectedSelectedTeacher();

				if(teacher == -1)
					return;
				
				availableTeachers.add(selectedTeachers.get(teacher).getTeacher());
				selectedTeachers.remove(teacher);
				
				view.setTableTeachers(getTableModelTeachers(selectedTeachers));
				view.setAvailableTeachers(availableTeachers);
				
				view.getBtnAddTeacher().setEnabled(availableTeachers.size() > 0);
				view.getBtnRemoveTeacher().setEnabled(selectedTeachers.size() > 0);
			}
		});
		
		view.getTableTeachers().addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				view.getBtnRemoveTeacher().setEnabled(true);
			}
		});
		
		view.getEnrollEndDateTimeInput().getDatePanel().addDateListener(new DateModifiedListener() {
			public void dateModified() {
				validateDates(new ArrayList<DateTime>(), view.getEnrollStart(), view.getEnrollEnd());
			}
		});


		view.getTable().addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				view.getBtnDeleteSession().setEnabled(true);
			}
		});

		// Focus Listener to en-/disable delete fee button
		view.getTableFees().addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				view.getBtnDeleteFee().setEnabled(true);
			}
		});

		// Update group and fee TF according to selected cell
		view.getTableFees().getSelectionModel()
				.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						view.setGroup(SwingUtil.getSelectedKey(view.getTableFees()));
						String feeStringSelected = SwingUtil.getSelectedValue(view.getTableFees(), 1);
						if (feeStringSelected.isEmpty()) {
							view.setFee(0);
						} else {
							view.setFee(Float.parseFloat(feeStringSelected));
						}
					}
				});

		// Delete sessions
		view.getBtnDeleteSession().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sessions.remove(view.getTable().getSelectedRow());

				view.setTable(getTableModel(sessions));

				view.getBtnDeleteSession().setEnabled(false);
			}
		});

		// Delete fees
		view.getBtnDeleteFee().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fees.remove(view.getTableFees().getSelectedRow());
				view.setTableFees(getTableModelFees(fees));
				view.getBtnDeleteFee().setEnabled(false);
			}
		});

		// Add fees
		view.getBtnAddFee().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (view.getGroup().isBlank())
					return;
				boolean exists = false;
				for (int i = 0; i < fees.size(); i++) {
					if (fees.get(i).getGroup().equals(view.getGroup()))
						exists = true;
				}
				if (!exists) {
					fees.add(new Fee(view.getGroup(), view.getFee()));
				} else {
					Fee f = fees.get(view.getTableFees().getSelectedRow());
					f.setAmount(view.getFee());
				}
				view.getTableFees().getSelectionModel().clearSelection();
				view.setTableFees(getTableModelFees(fees));
				view.setGroup("");
				view.setFee(0);
			}
		});

		// Free of charge selection
		view.getFree().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (view.getFree().isSelected()) {
					fees.clear();
					view.setTableFees(getTableModelFees(fees));
					fees.add(new Fee("Free of charge", 0));
				} else {
					fees.clear();
					fees.add(new Fee("Standard"));
					fees.add(new Fee("College Members"));
					fees.add(new Fee("UniOvi Members"));
					view.setTableFees(getTableModelFees(fees));
				}
			}
		});
	}

	public void initView() {
		// Open window (replaces the main generated by WindowBuilder)
		view.getFrame().setVisible(true);

		// Initialize fees
		fees.add(new Fee("Standard"));
		fees.add(new Fee("College Members"));
		fees.add(new Fee("UniOvi Members"));

		view.setTable(getTableModel(sessions));
		view.setTableFees(getTableModelFees(fees));
		view.setTableTeachers(getTableModelTeachers(selectedTeachers));
		
		view.setAvailableTeachers(availableTeachers);
		
	}

	/**
	 * Create a new formative action object and add it to the DB
	 */
	public void createFormativeAction() {

		// Sessions and fees
		List<Session> sessions = this.getSessions();
		List<Fee> fees = this.getFees();

		// Get dates of sessions, start of enrollment period & end of enrollment period
		List<DateTime> datesFormativeAction = new ArrayList<DateTime>();
		for (int i = 0; i < sessions.size(); i++) {
			datesFormativeAction.add(sessions.get(i).getSessionStart());
		}
		DateTime dateEnrollStart = view.getEnrollStart();
		DateTime dateEnrollEnd = view.getEnrollEnd();

		// Validate that all required information are provided
		if (view.getName().isBlank()) {
			JOptionPane.showMessageDialog(null, "You need to provide a name to create a formative Action.",
					"Name not valid", JOptionPane.ERROR_MESSAGE);
			return;
		}
		/*
		 * if (view.getTeacher().isBlank()){ JOptionPane.showMessageDialog(null,
		 * "You need to provide a teacher to create a formative Action.",
		 * "Teacher not valid", JOptionPane.ERROR_MESSAGE); return; }
		 */
		if (sessions.size()<=0) {
			JOptionPane.showMessageDialog(null, "You need add at least one session.",
					"There are no sessions!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// Validate session dates
		if (!validateDates(datesFormativeAction, dateEnrollStart, dateEnrollEnd)) {
			return;
		}
		
		// Validate fees
		if (!validateFees(fees)) {
			return;
		}

		if(selectedTeachers.size() <= 0) {
			JOptionPane.showMessageDialog(null, "No teacher was added to this formative action!",
					"There are no teachers.", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Create new formative action and add it to DB
		FormativeAction formativeAction = new FormativeAction(view.getName(), view.getSpaces(),
				view.getObjectives(), view.getMainContents(), FormativeAction.Status.ACTIVE, dateEnrollStart,
				dateEnrollEnd);

		// Add associated fees and sessions to fA
		formativeAction.setSessions(sessions);
		formativeAction.setFees(fees);

		// Insert into DB and close window
		try {
			model.setFormativeAction(formativeAction, selectedTeachers);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// view.getRemuneration());
		view.getFrame().setVisible(false);
	}

	private List<Session> getSessions() {
		return sessions;
	}

	private List<Fee> getFees() {
		return fees;
	}

	/**
	 * Check if the provided dates for the formative action, the start & end of the
	 * enrollment period are valid
	 */
	public boolean validateDates(List<DateTime> formativeActionDates, DateTime enrollStart, DateTime enrollEnd) {
		DateTime now = DateTime.now();
		long daysBetweenStartEnd = DateTime.daysSince(enrollEnd, enrollStart);
		long daysBetweenNowStart = DateTime.daysSince(enrollStart, now);
		long daysBetweenNowEnd = DateTime.daysSince(enrollEnd, now);

		// Check that enrollment period is long enough
		if (daysBetweenStartEnd <= 0) {
			JOptionPane.showMessageDialog(null, "Not enough time between start and end of enrollment period.",
					"Enrollment period not valid", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// Check that start of enrollment period is not in the past
		if (daysBetweenNowStart < 0) {
			JOptionPane.showMessageDialog(null, "Enrollment period can't start in the past",
					"Start of enrollment period not valid", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// Check that end of enrollment period is not in the past
		if (daysBetweenNowEnd < 0) {
			JOptionPane.showMessageDialog(null, "Enrollment period can't end in the past",
					"End of enrollment period not valid", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// Check that the session dates are valid
		for (int i = 0; i < formativeActionDates.size(); i++) {

			// Get date of each session start
			DateTime formativeActionDate = formativeActionDates.get(formativeActionDates.size()-1);
			
			long daysBetweenNowAction = DateTime.daysSince(formativeActionDate, now);
			long daysBetweenStartAction = DateTime.daysSince(formativeActionDate, enrollStart);
			long daysBetweenEndAction = DateTime.daysSince(formativeActionDate, enrollEnd);

			if (daysBetweenNowAction < 0) {
				JOptionPane.showMessageDialog(null, "Session can't take place in the past.", "Session date not valid",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}

			if (daysBetweenStartAction < 21) {
				JOptionPane.showMessageDialog(null,
						"Enrollment period should begin at least 3 weeks before formative action",
						"Start of enrollment period not valid", JOptionPane.WARNING_MESSAGE);
			}

			if (daysBetweenEndAction <= 0) {
				JOptionPane.showMessageDialog(null,
						"Enrollment period should end before first session of the formative action takes place.",
						"End of enrollment period not valid", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if the provided fees for the formative action are valid
	 */
	public boolean validateFees(List<Fee> fees) {
		if (view.getFree().isSelected())
			return true;
		if (fees.size() == 0) {
			// Show error message indicating that there is not fee specified
			JOptionPane.showMessageDialog(null,
					"There is no fee specified. \nEither select the 'Free of charge' option or add a fee.",
					"Fees not valid", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		for (int i = 0; i < fees.size(); i++) {
			if (fees.get(i).getAmount() == 0.0) {
				// Show error message indicating that every fee must have an amount
				JOptionPane.showMessageDialog(null,
						"For every group in the list an amount for the fee must be specified. \nEither delete the groups without amounts or specify the missing amounts. ",
						"Fees not valid", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}

	public TableModel getTableModel(List<Session> sessions) {
		String header[] = { "Date", "Duration", "Location" };

		String body[][] = new String[sessions.size()][header.length];

		int i = 0;
		for (Session s : sessions) {
			body[i++] = new String[] { s.getSessionStart().toString(), Float.toString(s.getNumberOfHours()),
					s.getLocation() };
		}

		TableModel tm = new DefaultTableModel(header, body.length);

		for (i = 0; i < body.length; i++) {
			for (int j = 0; j < header.length; j++) {
				tm.setValueAt(body[i][j], i, j);
			}
		}

		return tm;
	}

	public TableModel getTableModelFees(List<Fee> fees) {
		String header[] = { "Group", "Fee" };

		String body[][] = new String[fees.size()][header.length];

		int i = 0;
		for (Fee f : fees) {
			body[i++] = new String[] { f.getGroup(), Float.toString(f.getAmount()) };
		}

		TableModel tm = new DefaultTableModel(header, body.length);

		for (i = 0; i < body.length; i++) {
			for (int j = 0; j < header.length; j++) {
				if (body[i][j].equals("0.0"))
					body[i][j] = "";
				tm.setValueAt(body[i][j], i, j);
			}
		}

		return tm;
	}

	public TableModel getTableModelTeachers(List<TeacherTeaches> teaches) {
		String header[] = { "Teacher", "Remuneration" };

		String body[][] = new String[teaches.size()][header.length];

		int i = 0;
		for (TeacherTeaches t : teaches) {
			body[i++] = new String[] { t.getTeacher().getName(), Float.toString(t.getRemuneration()) };
		}

		TableModel tm = new DefaultTableModel(header, body.length);

		for (i = 0; i < body.length; i++) {
			for (int j = 0; j < header.length; j++) {
				tm.setValueAt(body[i][j], i, j);
			}
		}

		return tm;
	}
}
