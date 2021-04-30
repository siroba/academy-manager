package CancelFormativeAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Entities.FormativeAction;
import Entities.Movement;
import PL53.swing.CheckboxTableModel;
import PL53.util.Constants;
import PL53.util.Date;
import RegisterCancellations.Data;

public class Controller implements PL53.util.Controller {
	private Model model;
	private View view;

	public Controller() {
		this.model = new Model();

		try {
			this.model.initModel();
		} catch (SQLException | ParseException e) {
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

				if(index == -1) {
					JOptionPane.showMessageDialog(null,
						    "Please, select at least one formative action.",
						    "Did not select a formative action",
						    JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				double payments = model.getPayments(index);
				double teachers = model.getInvoices(index);
        
				int option = JOptionPane.showConfirmDialog(null, 
						payments + "€ will be owed to Professionals" + (teachers>0?(" and " + teachers + "€ will be returned from the teachers."):""),

						"Are you sure you want to continue?",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				
				if(option == 0) {
					model.cancel(index);

					try {
						if(teachers > 0) {
							//model.invoiceTeachers(index, view.getDateIn(), Constants.COIIPAfiscalNumber, Constants.COIIPAadress);
							// TODO: Do we need an invoice or not??
						}
						model.initModel();

						view.setTable(getTableModel(model.getAllData()));
						view.setTableCancelledFA(getTableModel(model.getCancelled()));
					} catch (SQLException | ParseException e1) {
						e1.printStackTrace();
					}
				}

			}
		});

		view.getTableCancelledFA().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				view.enableRefundsScroll();

				try {
					view.setTableRefunds(getCheckboxTableModel(
							model.getSolicitedRefunds(model.getCancelled()[view.getSelectedCanceled()])));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

    
		view.getBtnRefund().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(view.getSelectedCanceled() == -1) {
					JOptionPane.showMessageDialog(null,
						    "Please, select at least one canceled formative action.",
						    "Did not select a formative action",
						    JOptionPane.ERROR_MESSAGE);
					
					return;
				}
				
				Integer[] selected = view.getChecked();
				FormativeAction cancelledSelected = model.getCancelled()[view.getSelectedCanceled()];
				
				try {
					Data[] d = model.getSolicitedRefunds(cancelledSelected);
					
					for (int i : selected) {
						if(i>=d.length)
							continue;
						
						Movement in = new Movement(
								Float.parseFloat((String) view.getTableRefunds().getValueAt(i, 2)),
								view.getDateIn(),
								"COIIPA", 
								(String) view.getTableRefunds().getValueAt(i, 1),
								Constants.COIIPAadress, 
								Constants.COIIPAfiscalNumber,
								cancelledSelected.getID(), 
								d[i].professional.getID(),
								"Refund because the formative action " + cancelledSelected.getName() + " was cancelled.");
						
						model.payRefund(in, view.getIsCash());
					}

					model.initModel();

					view.setTable(getTableModel(model.getAllData()));
					view.setTableCancelledFA(getTableModel(model.getCancelled()));
					view.setTableRefunds(getCheckboxTableModel(new Data[]{}));
				} catch (SQLException | ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	@Override
	public void initView() {
		view.setVisible(true);
		view.setTable(getTableModel(model.getAllData()));
		view.setTableCancelledFA(getTableModel(model.getCancelled()));
	}

	public TableModel getTableModel(FormativeAction data[]) {
		String header[] = { "Formative Action", "End of Enrollment", "Filled places", "Total places" };

		String body[][] = new String[data.length][header.length];

		for (int i = 0; i < data.length; i++) {
			FormativeAction d = data[i];
			// TODO: Fix this
			body[i] = new String[] { 
									d.getName(), 
									d.getEnrollmentEnd().toString(),
									Integer.toString(model.getUsedPlaces(d.getID())), 
									Integer.toString(d.getTotalPlaces())
									};
		}

		TableModel tm = new DefaultTableModel(header, body.length);
		for (int i = 0; i < body.length; i++) {
			for (int j = 0; j < header.length; j++) {
				tm.setValueAt(body[i][j], i, j);
			}
		}

		return tm;
	}

	public CheckboxTableModel getCheckboxTableModel(Data data[]) {
		CheckboxTableModel tm = new CheckboxTableModel();

		String header[] = { "Name", "Refund amount", "Enrolled date" };
		tm.addColumns(header);

		String body[][] = new String[data.length][header.length];

		for (int i = 0; i < data.length; i++) {
			Data d = data[i];
			
			//int daysLeft = Date.daysSince(d.formativeAction.getEnrollmentEnd(), Date.now());
			
			body[i] = new String[] { 
					d.professional.getName(), 
					Float.toString(model.getPayedAmount(d.professional.getID(), d.formativeAction.getID())),
					d.enrollment.getTimeEn().toString()};
		}

		tm.addRows(body);

		return tm;
	}
	
	/**
	 * This is from another user story... oh well
	 * 
	 * If a registered person wishes to make a cancellation 7 calendar days or more before the course, 100% of the amount paid will be refunded. 
	 * If he resigns with between 3 calendar days and 6 calendar days missing, 50% of the amount of the course will be returned. 
	 * If he resigns with less than 3 calendar days left, the amount of the course will not be refunded.
	 * 
	 * @param daysLeft
	 * @param payedAmount
	 * @return
	 */
	public float getRefund(int daysLeft, float payedAmount) {
		if(daysLeft<3)
			return 0;
		
		if(daysLeft<=6)
			return payedAmount * 0.5f;
		
		return payedAmount;
	}
}
