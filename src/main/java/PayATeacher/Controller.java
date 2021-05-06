package PayATeacher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import BaseProject.SwingUtil;
import Entities.FormativeAction;
import Entities.MovementTeacher;
import Entities.PaymentTeacher;
import Entities.Session;
import Entities.Teacher;
import Entities.TeacherTeaches;
import PL53.util.Date;
import PL53.util.DateTime;
import PayATeacher.Data;
import PayATeacher.Model;
import PayATeacher.View;
import RegisterPayment.AuxPayment;

public class Controller implements PL53.util.Controller {
	private Model model;
	private View view;

	private Data selectedRow;

	public Controller() {
		this.model = new Model();

		try {
			model.initModel();
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}

		this.view = new View();
		this.initView();
	}

	public void initController() {
		view.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedRow = model.getData(view.getSelected());

				if (selectedRow == null) {
					JOptionPane.showMessageDialog(null, "You have to select one payment");
				} else if (selectedRow.movementTeacher != null) {
					TableModel tm = showPayments(model.getAllData(selectedRow));
					view.setMovementTable(tm);
				}

			}
		});

		view.getRegisterButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DateTime now = DateTime.now();
				
				// Data from the form
				String name = view.getNameTextField();
				String surname = view.getSurname().getText();
				String fiscalNumber = view.getFiscalNumberTextField();
				String address = view.getAddressTextField();
				Date dateTransfer = view.getDateTransferTextField();
				Date dateInvoice = view.getDateTextField();
				String IDInvoice = view.getIDInvoice();

				int ID_teacher = selectedRow.teacher.getID();
				int ID_fa = selectedRow.formativeAction.getID();
				
				// Data from the database
				//float professionalPaid = model.getAmountPaid(selectedRow); // Sum of all the payments to COIIPA
				//float refundedAmount = model.getAmountReturned(selectedRow);// Sum of all the payments to the professional
				//float totalPaid = model.getAmountTotalPaid(selectedRow);	// Sum of all the payments
				String fiscalNumberDB = model.getFiscalNumber(selectedRow); //new Teacher((String) view.getTable().getValueAt(view.getSelected(), 2), (String) view.getTable().getValueAt(view.getSelected(), 3), "", "", ""));

				float remuneration = selectedRow.teacherTeaches.getRemuneration();
				String sender = "COIIPA";

				// Payment calculations
				//float fee = selectedRow.invoice.getAmount();
				//float newTotal = totalPaid + newPayment;
				//float toReturn = newTotal - fee;

				// Dates...
				int daysBetweenNowAction = DateTime.daysSince(dateTransfer, now);
			
				/////////////////////////////////////////////////////////////////////////////////////
				
				// INPUT THE INVOICE
				try {
					if (selectedRow == null) {
						JOptionPane.showMessageDialog(null, "You have to select one pending payment");
					} else if (view.getNameTextField().length() == 0) {
						JOptionPane.showMessageDialog(null, "You have to introduce the name of the teacher");
					} else if (view.getFiscalNumberTextField().length() == 0) {
						JOptionPane.showMessageDialog(null, "You have to introduce the fiscal number");
					} else if (!Teacher.checkFiscalNumber(view.getFiscalNumberTextField())) {
						JOptionPane.showMessageDialog(null,
							    "Please provide a valid fiscal number. E.g. \"55566677R\".",
							    "Invalid fiscal number",
							    JOptionPane.ERROR_MESSAGE);
					} else if (view.getAddressTextField().length() == 0) {
						JOptionPane.showMessageDialog(null, "You have to introduce the address");
					} else if (view.getDateTransferTextField().getYear() == 0) {
						JOptionPane.showMessageDialog(null, "You have to introduce a valid year for the date of the transfer (ex: 2021) ");
					} else if (view.getDateTextField().getYear() == 0) {
						JOptionPane.showMessageDialog(null, "You have to introduce a valid year for the date of the invoice (ex: 2021)");
					} else if (view.getIDInvoice().length() == 0) {
						JOptionPane.showMessageDialog(null, "You have to introduce the ID of the invoice");
					} else if (view.getAmount() < 0) {
						JOptionPane.showMessageDialog(null, "You have to introduce the amount of the invoice");
					} else if (daysBetweenNowAction > 0) {
						JOptionPane.showMessageDialog(null, 
								"Tranfers cannot be made in the future.",
								"Date not valid ( the date must be on the current date or in the past)",
								JOptionPane.ERROR_MESSAGE);
					} else {
						//Amount agreed 
						// CHeck if fiscal number stored in the DB matches the entered one & provide the option to update it in the db if not 
						if (fiscalNumber != fiscalNumberDB) {
							int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;
							int option = JOptionPane.showConfirmDialog (null, "The fiscal number for the teacher " + receiver + " does not match the one stored in the database. \nWould you like to replace the fiscal number in the database "+  fiscalNumberDB +" by " + fiscalNumber + "?","WARNING", dialogButton);
				            if(option == JOptionPane.YES_OPTION) {
				            	model.updateFiscalNumber(new Teacher((String) view.getTable().getValueAt(view.getSelected(), 2), (String) view.getTable().getValueAt(view.getSelected(), 3), "", "", ""), fiscalNumber);
				            	JOptionPane.showMessageDialog(null,
										"The fiscal number of " + receiver + " has been updated succesfully.");
				            }	
				            if(option == JOptionPane.CANCEL_OPTION) {
				            	JOptionPane.showMessageDialog(null,
										"No invoice has been created and no payment has been made");
				            	return;
				            }	
						}
						
						// TODO: The amount of the invoice and the payment can differ

						if (amount > remuneration) {
							String str = String.format("%.2f", (remuneration - amount));
						
							int option = JOptionPane.showConfirmDialog(null, "The amount inputted (" + amount
									+ ") is hihger than the amount agreed, Do you want to return the diferrence ("
									+ str + ")?", "warning",
											JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE));

							if (option == 0) {
								float toReturn = amountAgreed - amount;

								try {
									model.createPaymentRefund(selectedRow.movementTeacher.getID(), -toReturn,
											dateInvoice, false, true);
								} catch (SQLException | ParseException e1) {
									e1.printStackTrace();
								}

								JOptionPane.showMessageDialog(null,
									"The invoice has been successfully created and the payment has been attached");
							}
						}
						if (amount < remuneration) {
							JOptionPane.showMessageDialog(null,
									"The amount inputted is lower than  the agreed amount ");

						}

						MovementTeacher invoice = new MovementTeacher(IDInvoice, amount, ID_fa, dateInvoice, sender,
								receiver, fiscalNumber, address, ID_teacher, "");

						PaymentTeacher paymentTeacher = new PaymentTeacher(IDInvoice, amount, dateTransfer, true, "");

						model.insertInvoice(invoice, paymentTeacher);
						if (paymentTeacher != null) {
							JOptionPane.showMessageDialog(null, "The invoice has been successfully created and the payment has been attached");

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

		// Function to add movements
		view.getAddMovement().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean teacher = view.getCheckTeacher();
				boolean COIIPA = view.getCheckCOIIPA();
				
				float amount = view.getAmoundRefound();
				Date payDate = view.getDateTransferTextField_1();
				// Movement made by the teacher to refund money
				if (teacher == true) {
					

				}

				// Movement made by COIIPA to the teacher
				if (COIIPA == true) {

				}
				
				else {
					JOptionPane.showMessageDialog(null, "You must select the sender of the movement",
							"Select sender",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	public void initView() {

		view.setVisible(true);
		view.setTable(getTableModel(model.getAllData()));

	}

	public TableModel getTableModel(Data[] datas) {

		String header[] = { "Course name", "status", "Teacher name", "Teacher surname", "Due amount" };

		ArrayList<String[]> rows = new ArrayList<String[]>();

		for (int i = 0; i < datas.length; i++) {
			Data d = datas[i];

			for (TeacherTeaches tt : d.formativeAction.getTeacherTeaches()) {

				rows.add(new String[] { d.formativeAction.getName(), d.formativeAction.getStatus().toString(),
						tt.getTeacher().getName(), tt.getTeacher().getSurname(),
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

	public TableModel showPayments(Data[] pt) {
		String header[] = { "Sender", "Receiver", "date", "Amount" };

		String body[][] = new String[pt.length][header.length];

		for (int i = 0; i < pt.length; i++) {
			Data d = pt[i];
			body[i] = new String[] { d.movementTeacher.getSender(), d.movementTeacher.getReceiver(),
					d.paymentTeacher.getPayDate().toString(), Float.toString(d.paymentTeacher.getAmount()) };
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
