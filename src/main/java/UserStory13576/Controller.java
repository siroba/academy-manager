package UserStory13576;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.table.TableModel;

import PL53.swing.DateInput;
import Utils.SwingUtil;

public class Controller implements PL53.util.Controller {

	private Model model;
	private View view;
	private String lastSelectedKey = ""; // remembers the last selected row to restore it when changing the race table

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;

		this.initView();
	}

	public String normalizedDate(DateInput date) {
		String d = (date.getDay() < 10 ? "0" : "") + date.getDay();
		String mm = (date.getMonth() < 10 ? "0" : "") + date.getMonth();
		return date.getYear() + "-" + mm + "-" + d;
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

		view.getButtonSearch().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String startString;
				String endString;

				if (view.getCheckBoxFilterStartDate().isSelected()) {
					startString = normalizedDate(view.getStart());
				} else {
					startString = "1970-01-01";
				}

				if (view.getCheckBoxFilterEndDate().isSelected()) {
					endString = normalizedDate(view.getEnd());
				} else {
					endString = "3000-01-01";
				}

				getListFormativeActionList(view.getComboBoxStatusSelection().getSelectedItem().toString().toLowerCase(),
						startString, endString);
			}
		});

	}

	/**
	 * Init view
	 */
	public void initView() {
		// Updates the view date
		this.getListFormativeActionList("", "2021-01-01", "2030-11-14");

		view.getFrame().setVisible(true);
	}

	/**
	 * Getting the list of FormativeActions just needs to get the list of objects
	 * from the model and use SwingUtil method to create a table model which is
	 * finally assigned to the table.
	 */
	public void getListFormativeActionList(String status, String start, String end) {

		List<FormativeActionList> formativeActions = model.getListFormativeAction(status, start, end,
				view.getCheckBoxFilterStatus().isSelected());

		TableModel tmodel = SwingUtil.getTableModelFromPojos(formativeActions,
				new String[] { "nameOfAction", "sessionNumber", "status", "enrollmentPeriodStart",
						"enrollmentPeriodEnd", "totalPlaces", "leftPlaces", "date" },
				new String[] { "Formative Action", "Session Number", "Status", "Start of enrollment period",
						"End of enrollment period", "Total places", "Left places", "Date" },
				false);
		view.getTableFormativeActions().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTableFormativeActions());
	}

	/**
	 * Method to display details about a selected formative action
	 */
	public void showDetails() {
		// Obtains the selected key and saves it to remember the selection in future
		// interactions.
		this.lastSelectedKey = SwingUtil.getSelectedKey(view.getTableFormativeActions());

		// Details of the selected career
		FormativeActionDetails formativeActionDetails = model.getFormativeActionDetails(this.lastSelectedKey);
		TableModel tmodel = SwingUtil.getRecordModelFromPojo(formativeActionDetails,
				new String[] { "objectives", "mainContents", "place", "teacher_name" },
				new String[] { "Objectives", "Main content", "Location", "Name of the teacher" });
		view.getTableFormativeActionDetails().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTableFormativeActionDetails());
	}
}
