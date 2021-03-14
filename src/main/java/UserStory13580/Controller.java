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
import Entities.Invoice;
import Entities.PaymentTeacher;
import PL53.util.DateTime;
import UserStory13580.Data;
import UserStory13580.Model;
import UserStory13580.View;

public class Controller {
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

					String name = view.getNameTextField();
					String fiscalNumber = view.getFiscalNumberTextField();
					String address = view.getAddressTextField();
					DateTime dateTransfer = DateTime.parseString(view.getDateTransferTextField());
					int ID_fa = selectedRow.getID();
					float amount = selectedRow.getRemuneration();
					String sender = "COIIPA";
					String receiver = selectedRow.getTeacherName();
					boolean confirmed = true;
					DateTime dateInvoice = DateTime.parseString(view.getDateTextField());

					Invoice invoice = new Invoice(ID_fa, dateInvoice);

					PaymentTeacher paymentTeacher = new PaymentTeacher( amount, dateTransfer, sender,
							receiver, fiscalNumber, address, confirmed);
					model.insertInvoice(invoice , paymentTeacher);
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "The Invoice ID must be an integer");
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "The Data must be a Data");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error creating the invoice");
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
			body[i] = new String[] { d.getName(), d.getStatus().toString(), d.getTeacherName(),
					Float.toString(d.getRemuneration()) };
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
