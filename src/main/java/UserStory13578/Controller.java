package UserStory13578;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
[x] The system must show a list of the registrations that can be cancelled:
		* Name of the formative action
		* Name and email of the professional
		* Fee paid
		* Date of the course
		* Status of the registration.

[x] When canceling a registration, a message needs to be shown, showing the amount that will be refunded and asking the user for confirmation. 
[?] When a registration is cancelled, another professional might take that place.
[x] In this sprint we assume that refunds are correctly made for all registrations that are cancelled, at the time the cancellation is done.
*/

public class Controller {
	private Model model;
	private View view;

	public Controller() {
		this.model = new Model();

		try {
			model.initModel();
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.view = new View();
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
		view.getBtnCancelRegistration().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Data selected = model.getData(view.getSelected());

				int input = JOptionPane.showConfirmDialog(null,
						"You are going to cancel the registration of " + selected.professional.getName() + 
						" to "	+ selected.formativeAction.getName() + ". Are you sure? " + 
						selected.formativeAction.refund() + "€ will be refunded to " + 
						selected.professional.getName() + ".",
						"Are you sure you want to cancel the registration?", 
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				// 0=yes, 1=no
				
				if(input == 0) {					
					model.deleteEnrollment(selected);

					try {
						model.initModel();
					} catch (SQLException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					view.setTable(getTableModel(model.getAllData()));
				}
			}
		});
	}

	public void initView() {

		// Open window (replaces the main generated by WindowBuilder)
		view.setVisible(true);

		view.setTable(getTableModel(model.getAllData()));
	}

	public TableModel getTableModel(Data data[]) {
		/*
		 * TableModel tmodel=SwingUtil.getTableModelFromPojos(courses, new String[]
		 * {"id", "name"}); view.getTableCourses().setModel(tmodel);
		 * SwingUtil.autoAdjustColumns(view.getTableCourses());
		 */
		// name of the formative action, the name and email of the professional, the fee
		// paid, the date of the course and the status of the registration
		String header[] = { "Formative Action", "Professional name", "Professional email", "Fee", "Date", "Status" };

		String body[][] = new String[data.length][header.length];

		for (int i = 0; i < data.length; i++) {
			Data d = data[i];
			body[i] = new String[] { d.formativeAction.getName(), d.professional.getName(), d.professional.getEmail(),
					Float.toString(d.formativeAction.getFee()), d.formativeAction.getFaStart().toString(),
					d.enrollment.getStatus().toString() };
		}

		TableModel tm = new DefaultTableModel(header, body.length);
		// loads each of the pojos values using PropertyUtils (from apache coommons
		// beanutils)
		for (int i = 0; i < body.length; i++) {
			for (int j = 0; j < header.length; j++) {
				tm.setValueAt(body[i][j], i, j);
			}
		}

		return tm;
	}
}