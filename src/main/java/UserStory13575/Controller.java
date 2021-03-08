package UserStory13575;

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
import PL53.SI2020_PL53.DateTime;
//import Entities.FormativeAction;
import UserStory13575.Data;

public class Controller {
	private Model model;
	private View view;
	private String lastSelectedKey = ""; // remembers the last selected row to restore it when changing the race table
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
	/*
	 * public void initController() {
	 * 
	 * view.getConfirmButton().addActionListener(new ActionListener() { public void
	 * actionPerformed(ActionEvent e) {
	 * 
	 * } });
	 * 
	 * view.getList().addListSelectionListener(new ListSelectionListener() { public
	 * void valueChanged(ListSelectionEvent e) { if (!e.getValueIsAdjusting()) {
	 * Data p = model.getPayment(view.getList().getSelectedIndex());
	 * System.out.println(p.payment.getReceiver()); } }
	 * 
	 * });
	 * 
	 * }
	 */

	public void initController() {

		view.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserStory13575.Data d = model.getData(view.getTable().getSelectedRowCount());
				selectedRow = d;
				view.getAmountPaidTextPane().setText(Float.toString(d.payment.getAmount()));
				view.getDateTextPane().setText(d.payment.getPayDate().toString());
			}
		});

		view.getConfirmButton().addActionListener(new ActionListener() { // TODO
			public void actionPerformed(ActionEvent e) {
				if (selectedRow == null || selectedRow.payment.getAmount() != selectedRow.formativeAction.getFee()
						|| (DateTime.minutesSince(selectedRow.enrollment.getTimeEn(),
								selectedRow.payment.getPayDate()) > 2880)) {
					// 2880 -> 48 * 60 conversion from 48 h to minutes
					JOptionPane.showMessageDialog(null, "ERROR");

				} else {
					selectedRow.payment.setConfirmed(true);
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

	public TableModel getTableModel(UserStory13575.Data[] datas) {

		String header[] = { "Payment Id", "Course name", "Professional name", "Professional email", "Fee",
				"Date of the registration" };

		String body[][] = new String[datas.length][header.length];

		for (int i = 0; i < datas.length; i++) {
			UserStory13575.Data d = datas[i];
			body[i] = new String[] { Integer.toString(d.payment.getID()), d.formativeAction.getName(),
					d.professional.getName(), d.professional.getEmail(), Float.toString(d.formativeAction.getFee()),
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
