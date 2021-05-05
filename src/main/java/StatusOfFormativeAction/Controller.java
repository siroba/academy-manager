package StatusOfFormativeAction;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Entities.FormativeAction;
import Utils.SwingUtil;

public class Controller implements PL53.util.Controller {
	private Model model;
	private View view;
	private String lastSelectedKey = ""; // remembers the last selected row to restore it when changing the race table

	public Controller() {
		this.model = new Model();
		this.view = new View();

		this.initView();
	}

	/**
	 * Init Controller
	 */
	public void initController() {
		view.getTableFormativeActions().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				SwingUtil.exceptionWrapper(() -> showRegistrationList());
				SwingUtil.exceptionWrapper(() -> showTeacherList());
				cleanPayments();
			}
		});
		
		view.getTableRegistrations().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				SwingUtil.exceptionWrapper(() -> showPayments());
			}
		});
		
		view.getTableTeacher().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				SwingUtil.exceptionWrapper(() -> showPaymentsTeacher());
			}
		});
	}

	/**
	 * Init view
	 */
	public void initView() {
		// Updates the view date
		showListFormativeActionList();
		view.getFrame().setVisible(true);
	}

	/**
	 * Getting the list of FormativeActions just needs to get the list of objects
	 * from the model and use SwingUtil method to create a table model which is
	 * finally assigned to the table.
	 */
	public void showListFormativeActionList() {
		List<FormativeActionDetails> formativeActionList = model.getListFormativeAction();
		TableModel tmodel = SwingUtil.getTableModelFromPojos(formativeActionList,
				new String[] { "name", "status", "enrollmentPeriodStart", "enrollmentPeriodEnd", "totalPlaces",
						"leftPlaces", "dateOfFirstSession", "dateOfLastSession" },
				new String[] { "Name", "Status", "Enrollment Start", "Enrollment End", "Total places", "Places left", "Date of first session", "Date of last session" },
				false);
		view.getTableFormativeActions().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTableFormativeActions());
	}

	public void showRegistrationList() {
		this.lastSelectedKey = SwingUtil.getSelectedKey(view.getTableFormativeActions());
		List<Registration> registrationList = model.getRegistrationList(lastSelectedKey);
		TableModel tmodel = SwingUtil.getTableModelFromPojos(registrationList,
				new String[] { "name", "surnames", "enrollmentDate", "amount", "status"},
				new String[] { "Name", "Surname", "Date of the registration", "Fee", "Status"}, false);
		view.getTableRegistrations().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTableRegistrations());
		
		FormativeAction fa = model.getFormativeAction(lastSelectedKey);
		if (fa.getEnrollmentStart().toMillis() < PL53.util.DateTime.now().toMillis()
				&& fa.getEnrollmentEnd().toMillis() > PL53.util.DateTime.now().toMillis()) {
			view.getLabelActive().setText("OPEN");
			
		} else {
			view.getLabelActive().setText(" ");
		}
	}
	
	public void showTeacherList() {
		this.lastSelectedKey = SwingUtil.getSelectedKey(view.getTableFormativeActions());
		List<Teacher> teacherList = model.getTeacherList(lastSelectedKey);
		TableModel tmodel = SwingUtil.getTableModelFromPojos(teacherList,
				new String[] { "name", "surname", "remuneration"},
				new String[] { "Name", "Surname", "Remuneration"}, false);
		view.getTableTeacher().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTableTeacher());
	}
	
	public void showPayments() {
		String selectedProfessional = SwingUtil.getSelectedKey(view.getTableRegistrations());
		List<Payment> paymentList = model.getPayments(lastSelectedKey, selectedProfessional);
		TableModel tmodel = SwingUtil.getTableModelFromPojos(paymentList, 
				new String[] {"date", "amount"}, 
				new String[] {"Date", "amount"}, 
				false);
		view.getTablePayments().setModel(tmodel);
		//SwingUtil.autoAdjustColumns(view.getTablePayments());
		
		float amount = 0;
		for (Payment payment : paymentList) {
			amount += payment.getAmount();
		}
		view.getLabelSummary().setText("" + amount);
		
	}
	
	public void showPaymentsTeacher() {
		String selectedTeacher = SwingUtil.getSelectedKey(view.getTableTeacher());
		List<Payment> paymentList = model.getPaymentsTeacher(lastSelectedKey, selectedTeacher);
		TableModel tmodel = SwingUtil.getTableModelFromPojos(paymentList, 
				new String[] {"date", "amount"}, 
				new String[] {"Date", "Amount"}, 
				false);
		view.getTablePayments().setModel(tmodel);
		//SwingUtil.autoAdjustColumns(view.getTablePayments());
		
		float amount = 0;
		for (Payment payment : paymentList) {
			amount += payment.getAmount();
		}
		view.getLabelSummary().setText("" + amount);
		
	}
	
	public void cleanPayments() {
		String[] colHeadings = {"Date","Amount"};
		
		DefaultTableModel model = new DefaultTableModel(0, colHeadings.length) ;
		model.setColumnIdentifiers(colHeadings);
		view.getTablePayments().setModel(model);
		//SwingUtil.autoAdjustColumns(view.getTablePayments());
		view.getLabelSummary().setText("");
	}
}