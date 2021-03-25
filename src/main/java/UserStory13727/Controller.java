package UserStory13727;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Entities.FormativeAction;

public class Controller implements PL53.util.Controller {
	private Model model;
	private View view;

	public Controller() {
		this.model = new Model();

		try {
			this.model.initModel();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.view = new View();
		this.initView();
	}

	@Override
	public void initController() {
		view.getBtnCancel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = view.getSelected();
				
				double payments = model.getPayments(index);
				double teachers = model.getInvoices(index);
				
				int option = JOptionPane.showConfirmDialog(null, 
						payments + "€ will be refunded to Professionals and " + teachers + "€ will be returned from the teachers.",
						"Are you sure you want to continue?",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				
				if(option == 0) {
					model.cancel(index);
					model.refund(index);
					model.invoiceTeachers(index);
					
					try {
						model.initModel();

						view.setTable(getTableModel(model.getAllData()));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
	}

	@Override
	public void initView() {
		view.setVisible(true);
		view.setTable(getTableModel(model.getAllData()));
	}

	public TableModel getTableModel(FormativeAction data[]) {
		String header[] = { "Formative Action", "End of Enrollment", "Teacher", "Total places" };

		String body[][] = new String[data.length][header.length];

		for (int i = 0; i < data.length; i++) {
			FormativeAction d = data[i];
			body[i] = new String[] { d.getName(), d.getEnrollmentEnd().toString(), d.getSessions().get(0).getTeacherName(),
					Integer.toString(d.getTotalPlaces()) };
		}

		TableModel tm = new DefaultTableModel(header, body.length);

		for (int i = 0; i < body.length; i++) {
			for (int j = 0; j < header.length; j++) {
				tm.setValueAt(body[i][j], i, j);
			}
		}

		return tm;
	}
}
