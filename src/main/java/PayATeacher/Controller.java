package PayATeacher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Entities.FormativeAction;
import Entities.InvoiceTeacher;
import Entities.PaymentTeacher;
import Entities.Session;
import Entities.TeacherTeaches;
import PL53.util.Date;
import PL53.util.DateTime;
import PayATeacher.Data;
import PayATeacher.Model;
import PayATeacher.View;

public class Controller implements PL53.util.Controller {
	private Model model;
	private View view;

	private Data selectedRow;

	public Controller() {
		this.model = new Model();
		try {
			model.initModel();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ParseException e) {

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
		view.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedRow = model.getData(view.getSelected());

				if (selectedRow == null) {
					JOptionPane.showMessageDialog(null, "You have to select one payment");
				}

			}
		});

		view.getRegisterButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// INPUT THE INVOICE
				try {
					if (selectedRow == null) {
						JOptionPane.showMessageDialog(null, "You have to select one pending payment");
					} else if (view.getNameTextField().length() == 0) {
						JOptionPane.showMessageDialog(null, "You have to introduce the name of the teacher");
					} else if (view.getFiscalNumberTextField().length() == 0) {
						JOptionPane.showMessageDialog(null, "You have to introduce the fiscal number");
					} else if (view.getAddressTextField().length() == 0) {
						JOptionPane.showMessageDialog(null, "You have to introduce the address");
					} else if (view.getDateTransferTextField().getYear() == 0) {
						JOptionPane.showMessageDialog(null,
								"You have to introduce a valid year for the date of the transfer (ex: 2021) ");
					} else if (view.getDateTextField().getYear() == 0) {
						JOptionPane.showMessageDialog(null,
								"You have to introduce a valid year for the date of the invoice (ex: 2021)");
					} else if (view.getIDInvoice().length() == 0) {
						JOptionPane.showMessageDialog(null, "You have to introduce the ID of the invoice");
					} else {
						String name = view.getNameTextField();
						String fiscalNumber = view.getFiscalNumberTextField();
						String address = view.getAddressTextField();
						Date dateTransfer = view.getDateTransferTextField();
						int ID_fa = selectedRow.formativeAction.getID();
						float amount = Float.parseFloat((String) view.getTable().getValueAt(view.getSelected(), 3));
						String sender = "COIIPA";
						String receiver = (String) view.getTable().getValueAt(view.getSelected(), 2);
						boolean confirmed = true;
						Date dateInvoice = view.getDateTextField();
						String IDInvoice = view.getIDInvoice();
						int ID_teacher = selectedRow.teacher.getID();

						// TODO: The amount of the invoice and the payment can differ

						InvoiceTeacher invoice = new InvoiceTeacher(IDInvoice, amount, ID_fa, dateInvoice, sender,
								receiver, fiscalNumber, address, ID_teacher);

						PaymentTeacher paymentTeacher = new PaymentTeacher(IDInvoice, amount, dateTransfer, confirmed);

						model.insertInvoice(invoice, paymentTeacher);
						if (paymentTeacher != null) {
							JOptionPane.showMessageDialog(null, "The invoice has been successfully created anf the payment has been attached");
							model.initModel();

							initView();
						}
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "The Invoice ID must be an integer");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error creating the invoice");
					e1.printStackTrace();
				} catch (ParseException e1) {

					e1.printStackTrace();
				}

			}
		});
	}

	public void initView() {

		view.setVisible(true);
		view.setTable(getTableModel(model.getAllData()));

	}

	public TableModel getTableModel(Data[] datas) {

		String header[] = { "Course name", "status", "Teacher name", "Due amount" };

		ArrayList<String[]> rows = new ArrayList<String[]>();

		for (int i = 0; i < datas.length; i++) {
			Data d = datas[i];

			for (TeacherTeaches tt : d.formativeAction.getTeacherTeaches()) {

				rows.add(new String[] { d.formativeAction.getName(), d.formativeAction.getStatus().toString(), tt.getTeacher().getName(),
						Float.toString(tt.getRemuneration()) });
			}

		}

		String body[][] = new String[rows.size()][header.length];

		for (int i = 0; i < rows.size(); i++) {
			body[i] = rows.get(i);
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
