package UserStory13573;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Entities.Fee;
import Entities.FormativeAction;
import Entities.Session;
import PL53.util.DateTime;
import Utils.SwingUtil;

public class Controller implements PL53.util.Controller {
	private Model model;
	private View view;
	private List<Session> sessions = new ArrayList<Session>();
	private List<Fee> fees = new ArrayList<Fee> ();
	
	

	public Controller(Model m, View v) {
		this.model = m;
		this.view = v;
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
				for (int i=0; i<sessions.size(); i++) {
					datesFormativeAction.add(sessions.get(i).getSessionStart());
				}
				// Validate dates 
				if (validateDates(datesFormativeAction, view.getEnrollStart(), view.getEnrollEnd())) {
					// Add session to table 
					sessions.add(new Session(view.getLocation(), view.getTeacher(), view.getNumberOfHours(),
							view.getRemuneration(), view.getSessionDatetime()));

					view.setTable(getTableModel(sessions));
				}
			}
		});

		// Focus Listener to en-/disable delete session button 
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
		view.getTableFees().getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener(){
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
				if (view.getGroup().isBlank()) return; 
				boolean exists = false; 
				for (int i=0; i<fees.size(); i++) {
					if (fees.get(i).getGroup().equals(view.getGroup())) exists = true;
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
			public void actionPerformed(ActionEvent e){
				if (view.getFree().isSelected()) {
					fees.clear();
					view.setTableFees(getTableModelFees(fees));
					fees.add(new Fee("Free of charge", 0));
				}
				else {
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
		for (int i=0; i<sessions.size(); i++) {
			datesFormativeAction.add(sessions.get(i).getSessionStart());
		}
		DateTime dateEnrollStart = view.getEnrollStart();
		DateTime dateEnrollEnd = view.getEnrollEnd();

		// Validate session dates
		if (validateDates(datesFormativeAction, dateEnrollStart, dateEnrollEnd)) {
			// Validate fees
			if (validateFees(fees)) {
				// Create new formative action and add it to DB
				FormativeAction formativeAction = new FormativeAction(view.getName(), view.getSpaces(),
						view.getObjectives(), view.getMainContents(), FormativeAction.Status.ACTIVE, dateEnrollStart,
						dateEnrollEnd);
				
				//  Add associated fees and sessions to fA
				formativeAction.setSessions(sessions);
				formativeAction.setFees(fees);

				try {
					// Insert into DB and close window 
					model.setFormativeAction(formativeAction);
					view.getFrame().setVisible(false);
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
			
		}
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
			JOptionPane.showMessageDialog(null,
				    "Not enough time between start and end of enrollment period.",
				    "Enrollment period not valid",
				    JOptionPane.ERROR_MESSAGE);
			return false;
		} 
		// Check that start of enrollment period is not in the past 
		if (daysBetweenNowStart < 0) {
			JOptionPane.showMessageDialog(null,
				    "Enrollment period can't start in the past",
				    "Start of enrollment period not valid",
				    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// Check that end of enrollment period is not in the past 
		if (daysBetweenNowEnd < 0) {
			JOptionPane.showMessageDialog(null,
				    "Enrollment period can't end in the past",
				    "End of enrollment period not valid",
				    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// Check that the session dates are valid 
		for (int i=0; i<formativeActionDates.size(); i++) {
			
			// Get date of current formative action 
			DateTime formativeActionDate = formativeActionDates.get(i);
			
			long daysBetweenNowAction = DateTime.daysSince(formativeActionDate, now);
			long daysBetweenStartAction = DateTime.daysSince(formativeActionDate, enrollStart);
			long daysBetweenEndAction = DateTime.daysSince(formativeActionDate, enrollEnd);
			
			if (daysBetweenNowAction < 0) {
				JOptionPane.showMessageDialog(null,
					    "Session can't take place in the past.",
					    "Session date not valid",
					    JOptionPane.ERROR_MESSAGE);
				return false;
			} 
			if (daysBetweenStartAction < 21) {
				JOptionPane.showMessageDialog(null,
					    "Enrollment period should begin at least 3 weeks before formative action",
					    "Start of enrollment period not valid",
					    JOptionPane.ERROR_MESSAGE);
				return false;
			} 
			
			if (daysBetweenEndAction <= 0) {
				JOptionPane.showMessageDialog(null,
					    "Enrollment period should end before first session of the formative action takes place.",
					    "End of enrollment period not valid",
					    JOptionPane.ERROR_MESSAGE);
				return false;
			} 
		}
		return true;
	}
	
	/**
	 * Check if the provided fees for the formative action are valid
	 */
	public boolean validateFees(List<Fee> fees) {
		if (view.getFree().isSelected()) return true; 
		for (int i=0; i<fees.size(); i++) {
			if (fees.get(i).getAmount()==0.0){
				// Show error message indicating that every fee must have an amount 
				JOptionPane.showMessageDialog(null,
				    "For every group in the list an amount for the fee must be specified. \nEither delete the groups without amounts or specify the missing amounts. ",
				    "Fees not valid",
				    JOptionPane.ERROR_MESSAGE);
				return false; 
			}
		}
		return true; 
	}

	public TableModel getTableModel(List<Session> sessions) {
		String header[] = { "Date", "Location", "Teacher", "Remuneration" };

		String body[][] = new String[sessions.size()][header.length];

		int i = 0;
		for (Session s : sessions) {
			body[i++] = new String[] { s.getSessionStart().toString(), s.getLocation(), s.getTeacherName(),
					Float.toString(s.getRemuneration()) };
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
		String header[] = { "Group", "Fee"};

		String body[][] = new String[fees.size()][header.length];

		int i = 0;
		for (Fee f : fees) {
			body[i++] = new String[] { f.getGroup(), Float.toString(f.getAmount()) };
		}

		TableModel tm = new DefaultTableModel(header, body.length);

		for (i = 0; i < body.length; i++) {
			for (int j = 0; j < header.length; j++) {
				if (body[i][j].equals("0.0")) body[i][j]="";
				tm.setValueAt(body[i][j], i, j);
			}
		}

		return tm;
	}
}
