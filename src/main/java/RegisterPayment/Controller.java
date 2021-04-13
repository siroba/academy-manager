package RegisterPayment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.multi.MultiInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import BaseProject.SwingUtil;
import Entities.Payment;
import PL53.util.DateTime;
import RegisterPayment.Data;

public class Controller implements PL53.util.Controller {
	private Model model;
	private View view;
	private Data selectedRow;
	

	public Controller() {
		this.model = new Model();
		try {
			model.initModel();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.view = new View();
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

		view.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				RegisterPayment.Data d = model.getData(view.getSelected());
				selectedRow = d;
				view.getAmountPaidTextPane().setText(Float.toString(d.payment.getAmount()));
				view.getDateTextPane().setText(d.payment.getPayDate().toString());
			}
		});

		view.getConfirmButton().addActionListener(new ActionListener() { // TODO
			public void actionPerformed(ActionEvent e) {
				
			
		
				if (selectedRow == null) {
					JOptionPane.showMessageDialog(null, "You have to select one payment");
				} else if (selectedRow.payment.getAmount() != selectedRow.formativeAction.getFee(selectedRow.enrollment.getGroup())) {
					JOptionPane.showMessageDialog(null, "The payment is different from the fee ");
					
				} else if (Math.abs(DateTime.minutesSince(selectedRow.enrollment.getTimeEn(),
						selectedRow.payment.getPayDate())) > 2880) {
					// 2880 -> 48 * 60 conversion from 48 h to minutes
					JOptionPane.showMessageDialog(null,
							"The payment must be done with a margin of 48 hours before the enrollmet");
				}

				else {
					model.updateStatus(selectedRow.payment.getID(), selectedRow.enrollment.getID_fa(), selectedRow.enrollment.getID_professional());
					try {
						model.initModel();
						view.setTable(getTableModel(model.getAllData()));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		try {
			model.initModel();
		} catch (SQLException | ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		view.setTable(getTableModel(model.getAllData()));
	}

	public void initView() {

		view.setVisible(true);

		view.setTable(getTableModel(model.getAllData()));
	}

	public TableModel getTableModel(RegisterPayment.Data[] datas) {

		String header[] = { "Payment Id", "Course name", "Professional name", "Professional email", "Fee",
				"Date of the registration" };

		String body[][] = new String[datas.length][header.length];

		for (int i = 0; i < datas.length; i++) {
			RegisterPayment.Data d = datas[i];
			body[i] = new String[] { Integer.toString(d.payment.getID()), d.formativeAction.getName(),
					d.professional.getName(), d.professional.getEmail(), Float.toString(d.formativeAction.getFee(d.enrollment.getGroup())),
					d.enrollment.getTimeEn().toString() };
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
