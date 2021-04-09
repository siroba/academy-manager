package UserStory13580;

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
import Entities.InvoiceTeacher;
import Entities.PaymentTeacher;
import Entities.Session;
import PL53.util.Date;
import PL53.util.DateTime;
import UserStory13580.Data;
import UserStory13580.Model;
import UserStory13580.View;

public class Controller implements PL53.util.Controller {
	private Model model;
	private View view;

	private FormativeAction selectedRow;

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

				else {

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
					}
					else if (view.getDateTransferTextField().getYear()==0 )
					{
						JOptionPane.showMessageDialog(null, "You have to introduce a valid year for the date of the transfer (ex: 2021) ");
					}
					else if (view.getDateTextField().getYear()==0 )
					{
						JOptionPane.showMessageDialog(null, "You have to introduce a valid year for the date of the invoice (ex: 2021)");
					}
					else{
					String name = view.getNameTextField();
					String fiscalNumber = view.getFiscalNumberTextField();
					String address = view.getAddressTextField();
					Date dateTransfer = view.getDateTransferTextField();
					int ID_fa = selectedRow.getID();
					float amount = Float.parseFloat((String) view.getTable().getValueAt(view.getSelected(), 3));
					String sender = "COIIPA";
					String receiver = (String) view.getTable().getValueAt(view.getSelected(), 2);
					boolean confirmed = true;
					Date dateInvoice = view.getDateTextField();

					InvoiceTeacher invoice = new InvoiceTeacher(ID_fa, dateInvoice,sender,receiver, address, fiscalNumber);

					PaymentTeacher paymentTeacher = new PaymentTeacher(amount, dateTransfer,  confirmed);

					model.insertInvoice(invoice, paymentTeacher);
					if (paymentTeacher != null) {
						JOptionPane.showMessageDialog(null, "The invoice has been successfully created");
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

	public TableModel getTableModel(FormativeAction[] formativeActions) {

		String header[] = { "Course name", "status", "Teacher name", "Due amount" };

		String body[][] = new String[formativeActions.length][header.length];

		for (int i = 0; i < formativeActions.length; i++) {
			FormativeAction d = formativeActions[i];


			for (Session s : d.getSessions()) {
				body[i] = new String[] { d.getName(), d.getStatus().toString(), s.getTeacherName(),
						Float.toString(s.getRemuneration()) };
			}
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
