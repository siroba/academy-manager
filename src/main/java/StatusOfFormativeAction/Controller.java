package StatusOfFormativeAction;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
				SwingUtil.exceptionWrapper(() -> showDetails());
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
						"leftPlaces" },
				new String[] { "Name", "Status", "Enrollment Start", "Enrollment End", "Total places", "Places left" },
				false);
		view.getTableFormativeActions().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTableFormativeActions());
	}

	/**
	 * Method to display details about a selected formative action
	 */
	public void showDetails() {
		showRegistrationList();
	}

	public void showRegistrationList() {
		this.lastSelectedKey = SwingUtil.getSelectedKey(view.getTableFormativeActions());
		List<Registration> registrationList = model.getRegistrationList(lastSelectedKey);
		TableModel tmodel = SwingUtil.getTableModelFromPojos(registrationList,
				new String[] { "name", "surnames", "enrollmentDate", "amount", "status" },
				new String[] { "Name", "Surname", "Date of the registration", "Fee", "Status" }, true);
		view.getTableRegistrations().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTableRegistrations());
		
		FormativeAction fa = model.getFormativeAction(lastSelectedKey);
		System.out.println(fa.getEnrollmentStart().toMillis() < PL53.util.DateTime.now().toMillis());
		System.out.println(fa.getEnrollmentEnd().toMillis() > PL53.util.DateTime.now().toMillis());
		if (fa.getEnrollmentStart().toMillis() < PL53.util.DateTime.now().toMillis()
				&& fa.getEnrollmentEnd().toMillis() > PL53.util.DateTime.now().toMillis()) {
			view.getLabelActive().setText("OPEN");
			System.out.println("OPEN");
			
		} else {
			view.getLabelActive().setText(" ");
			System.out.println("Closed");
		}
	}
}
