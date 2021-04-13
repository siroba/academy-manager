package StatusOfFormativeAction;

import java.sql.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import Utils.SwingUtil;

public class Controller implements PL53.util.Controller {
	private Model model;
	private View view;

	public Controller() {
		this.model = new Model();
		this.view = new View();

		this.initView();
	}

	/**
	 * Init Controller
	 */
	public void initController() {
		view.getListFormativeActions().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				showDetails();
			}
		});

	}

	/**
	 * Init view
	 */
	public void initView() {
		// Updates the view date
		getListFormativeActionList();
		view.getFrame().setVisible(true);
	}

	/**
	 * Getting the list of FormativeActions just needs to get the list of objects
	 * from the model and use SwingUtil method to create a table model which is
	 * finally assigned to the table.
	 */
	public void getListFormativeActionList() {
		List<String> listFormativActions = model.getListFormativeAction();
		DefaultListModel<String> m = (DefaultListModel<String>) view.getListFormativeActions().getModel();

		for (int i = 0; i < listFormativActions.size(); i++) {
			m.addElement(listFormativActions.get(i));
		}
	}

	/**
	 * Method to display details about a selected formative action
	 */
	public void showDetails() {
		showFormativeActionDetails();
		showRegistrationList();
	}

	public void showFormativeActionDetails() {
		FormativeActionDetails fs = model.getFormativeActionDetails(view.getListFormativeActions().getSelectedValue());

		TableModel tmodel = SwingUtil.getRecordModelFromPojo(fs,
				new String[] { "name", "status", "enrollmentPeriodStart", "enrollmentPeriodEnd", "totalPlaces",
						"leftPlaces" },
				new String[] { "Name", "Status", "Enrollment Start", "Enrollment End", "Total places", "Left places" });
		view.getTableFormativeActionDetails().setModel(tmodel);

		if (fs.getEnrollmentPeriodStart().before(new Date(System.currentTimeMillis()))
				&& fs.getEnrollmentPeriodEnd().after(new Date(System.currentTimeMillis()))) {
			view.getLabelActive().setText("OPEN");
		} else {
			view.getLabelActive().setText(" ");
		}
	}

	public void showRegistrationList() {
		List<Registration> registrationList = model
				.getRegistrationList(view.getListFormativeActions().getSelectedValue());
		TableModel tmodel = SwingUtil.getTableModelFromPojos(registrationList,
				new String[] { "name", "surnames", "enrollmentDate", "amount", "status" },
				new String[] { "Name", "Surname", "Date of the registration", "Fee", "Status" }, true);
		view.getTableRegistrations().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTableRegistrations());
	}
}