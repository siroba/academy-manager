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

import BaseProject.SwingUtil;
import Entities.FormativeAction;
import Entities.Invoice;
import PL53.swing.CheckboxTableModel;
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
				if(!view.filledCoiipasInfo()) {
					view.setCoiipaInfoRed();
					JOptionPane.showMessageDialog(null,
						    "Please, fill the information of COIIPA.",
						    "Information empty",
						    JOptionPane.ERROR_MESSAGE);
					return;
				}else {
					view.setCoiipaInfoNormal();
				}
				
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

						payments + "€ will be refunded to Professionals and " + teachers + "€ will be returned from the teachers.",

						"Are you sure you want to continue?",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				
				if(option == 0) {
					model.cancel(index);

					try {
						if(teachers > 0) {
							model.invoiceTeachers(index, view.getDateIn(), view.getFiscalNumber(), view.getAddress());
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
						
						Invoice in = new Invoice(
								Float.parseFloat((String) view.getTableRefunds().getValueAt(i, 2)),
								view.getDateIn(),
								"COIIPA", 
								(String) view.getTableRefunds().getValueAt(i, 1),
								view.getAddress(), 
								view.getFiscalNumber(),
								cancelledSelected.getID(), 
								d[i].professional.getID());
						
						model.payRefund(in, view.getIsCash());
					}

					model.initModel();

					view.setTable(getTableModel(model.getAllData()));
					view.setTableCancelledFA(getTableModel(model.getCancelled()));
					view.setTableRefunds(getCheckboxTableModel(new Data[]{}));
				} catch (SQLException | ParseException e1) {
					// TODO Auto-generated catch block
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
			body[i] = new String[] { 
					d.professional.getName(), 
					Float.toString(d.payment.getAmount()),
					d.enrollment.getTimeEn().toString()};
		}

		tm.addRows(body);

		return tm;
	}
}
