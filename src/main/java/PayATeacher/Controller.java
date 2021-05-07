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
import Entities.MovementTeacher;
import Entities.PaymentTeacher;
import Entities.Teacher;
import PL53.util.Date;
import PL53.util.DateTime;

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
				} else {
					view.setInvoicesDropdownVisible(selectedRow.movementsTeacher.size() > 1);

					if (!selectedRow.movementsTeacher.isEmpty()) {
						try {
							TableModel tm = getModelPayments(model.getDataForPaymentsTable(selectedRow));
							view.setTablePayments(tm);

							if (selectedRow.movementsTeacher.size() > 1)
								view.setInvoicesDropdown(selectedRow.movementsTeacher);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}

					view.setFiscalNumber(selectedRow.teacher.getFiscalNumber());
				}

			}
		});

		view.getRegisterButton().addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				try {
					DateTime now = DateTime.now();

					// Data from the form
					String name = view.getNameTextField();
					String surname = view.getSurname().getText();
					String fiscalNumber = view.getFiscalNumberTextField();
					String address = view.getAddressTextField();
					Date dateTransfer = view.getDateTransferTextField();
					Date dateInvoice = view.getDateTextField();
					String IDInvoice = view.getIDInvoice();
					float amount = view.getAmount();

					int ID_teacher = selectedRow.teacher.getID();
					int ID_fa = selectedRow.formativeAction.getID();

					// Data from the database
					// float professionalPaid = model.getAmountPaid(selectedRow); // Sum of all the
					// payments to COIIPA
					// float refundedAmount = model.getAmountReturned(selectedRow);// Sum of all the
					// payments to the professional
					// float totalPaid = model.getAmountTotalPaid(selectedRow); // Sum of all the
					// payments
					String fiscalNumberDB = model.getFiscalNumber(ID_teacher);

					float remuneration = selectedRow.teacherTeaches.getRemuneration();
					String sender = "COIIPA";
					String reciever = selectedRow.teacher.getName();

					// Payment calculations
					// float fee = selectedRow.invoice.getAmount();
					// float newTotal = totalPaid + newPayment;
					float toReturn = remuneration - amount;

					// Dates...
					int daysBetweenNowAction = DateTime.daysSince(dateTransfer, now);

					/////////////////////////////////////////////////////////////////////////////////////

					// INPUT THE INVOICE
					if (selectedRow == null) {
						JOptionPane.showMessageDialog(null, "You have to select one pending payment");
					} else if (view.getNameTextField().length() == 0) {
						JOptionPane.showMessageDialog(null, "You have to introduce the name of the teacher");
					} else if (view.getFiscalNumberTextField().length() == 0) {
						JOptionPane.showMessageDialog(null, "You have to introduce the fiscal number");
					} else if (!Teacher.checkFiscalNumber(view.getFiscalNumberTextField())) {
						JOptionPane.showMessageDialog(null, "Please provide a valid fiscal number. E.g. \"55566677R\".",
								"Invalid fiscal number", JOptionPane.ERROR_MESSAGE);
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
					} else if (view.getAmount() < 0) {
						JOptionPane.showMessageDialog(null, "You have to introduce the amount of the invoice");
					} else if (daysBetweenNowAction > 0) {
						JOptionPane.showMessageDialog(null, "Tranfers cannot be made in the future.",
								"Date not valid ( the date must be on the current date or in the past)",
								JOptionPane.ERROR_MESSAGE);
					} else {
						// Check if fiscal number stored in the DB matches the entered one & provide the
						// option to update it in the db if not
						if (fiscalNumber.compareTo(fiscalNumberDB) != 0) {
							int option = JOptionPane.showConfirmDialog(null,
									"The fiscal number for the teacher " + reciever
											+ " does not match the one stored in the database.\n"
											+ "Would you like to replace the fiscal number in the database \""
											+ fiscalNumberDB + "\" by \"" + fiscalNumber + "\"?",
									"WARNING", JOptionPane.YES_NO_CANCEL_OPTION);

							if (option == JOptionPane.YES_OPTION) {
								model.updateFiscalNumber(selectedRow.teacher, fiscalNumber);
								JOptionPane.showMessageDialog(null,
										"The fiscal number of " + reciever + " has been updated succesfully.");
							} else if (option == JOptionPane.CANCEL_OPTION) {
								JOptionPane.showMessageDialog(null,
										"No invoice has been created and no payment has been made");
								return;
							}
						}

						////////////////////////////// CREATE THE INVOICE AND THE PAYMENT
						////////////////////////////// //////////////////////////////

						MovementTeacher invoice = new MovementTeacher(IDInvoice, amount, ID_fa, dateInvoice, sender,
								reciever, fiscalNumber, address, ID_teacher, "");

						PaymentTeacher paymentTeacher = new PaymentTeacher(IDInvoice, amount, dateTransfer, true, "");

						model.insertInvoice(invoice, paymentTeacher);

						////////////////////////////////////////////////////////////////////////////////////////////////

						if (amount > remuneration) {
							String str = String.format("%.2f", (remuneration - amount));

							int option = JOptionPane.showConfirmDialog(null, "The amount inputted (" + amount
									+ ") is hihger than the amount agreed, Do you want to return the diferrence (" + str
									+ ")?", "warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

							if (option == 0) {

								try {
									model.createPaymentRefund(IDInvoice, -toReturn, dateInvoice, true);
								} catch (SQLException | ParseException e1) {
									e1.printStackTrace();
								}

								JOptionPane.showMessageDialog(null,
										"The invoice has been successfully created and the payment has been attached");
							}
						} else if (amount < remuneration) {
							JOptionPane.showMessageDialog(null,
									"The amount inputted is lower than  the agreed amount ");
						}

						if (paymentTeacher != null) {
							JOptionPane.showMessageDialog(null,
									"The invoice has been successfully created and the payment has been attached");

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
			// @SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {

				boolean teacher = view.getCheckTeacher();
				boolean COIIPA = view.getCheckCOIIPA();

				// Data from the form
				float newRefund = view.getAmoundRefound();
				Date refundDate = view.getDateTransferTextField_1();

				// Data from the database
				// float teacherPaid = model.getAmountPaid(selectedRow); // Sum of all the
				// payments to COIIPA
				// float COIIPAPaid = model.getAmountReturned(selectedRow); // Sum of all the
				// payments to the teacher
				float totalPaid = model.getAmountTotalPaid(selectedRow); // Sum of all the payments
				int numberInvoices = selectedRow.movementsTeacher.size();

				// Payment calculations
				float remuneration = selectedRow.teacherTeaches.getRemuneration();
				float newTotal = totalPaid + newRefund;
				float toReturn = newTotal - remuneration;

				// float toReturn = totalPaid - newRefund ;//newTotal - remuneration;

				// Dates...
				DateTime now = DateTime.now();
				int daysBetweenNowAction = Date.daysSince(now, refundDate);

				/////////////////////////////////////////////////////////////////////////////////////

				// Checks
				if (selectedRow == null) {
					JOptionPane.showMessageDialog(null, "You have to select one teacher");
					return;
				} else if (numberInvoices == 0) {
					JOptionPane.showMessageDialog(null,
							"There are no Invoices recorded for the selected option. Please, record one before paying.");
					return;
				} else if (newRefund <= 0) {
					JOptionPane.showMessageDialog(null,
							"You cannot do a payment for " + view.getAmoundRefound() + "�");

					return;
				} else if (daysBetweenNowAction < 0) {
					JOptionPane.showMessageDialog(null,
							"Payments cannot be made in the future( the date must be on the current date or in the past).",
							"date not valid ", JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					String movementID;

					if (numberInvoices > 1) {
						movementID = view.getSelectedMovement();
					} else {
						movementID = selectedRow.movementsTeacher.get(0).getID();
					}

					if (teacher) { // Movement made by the teacher to refund money
						
						toReturn = remuneration - (totalPaid - newRefund);
						
						
					
						
						if (toReturn > 0) { // Payment higher than it should be
							String difference = String.format("%.2f", (newRefund - toReturn));
							
							
								int option = JOptionPane.showConfirmDialog(null, "The amount of the movement ("
										+ newRefund
										+ ") is higher than the amount that has to be returned to COIIPA . Do you want to return the difference ("
										+ toReturn + ")?", "Warning", JOptionPane.YES_NO_OPTION,
										JOptionPane.WARNING_MESSAGE);

								if (option == 0) { // The user clicked YES
									float amount = newRefund - toReturn; // the amount to compensate
									// model.createPaymentRefund(movementID, -amount, refundDate, true);
									model.createPaymentRefund(movementID, toReturn, refundDate, true);
								}
							
							
						}
						if (toReturn <0) {
							int option2 = JOptionPane.showConfirmDialog(null, "The amount of the movement ("
									+ newRefund
									+ ") is different than the amount that has to be returned to COIIPA . Do you want to return  ("
									+ (-(toReturn -newRefund) )+ ")?", "Warning", JOptionPane.YES_NO_OPTION,
									JOptionPane.WARNING_MESSAGE);
							if (option2 == 0) { // The user clicked YES
								float amount = newRefund - toReturn; // the amount to compensate
								// model.createPaymentRefund(movementID, -amount, refundDate, true);
								model.createPaymentRefund(movementID, toReturn, refundDate, true);
							}

						}
						
						
						/*
						 * if (toReturn < 0) { // Payment lower than it should be
						 * JOptionPane.showMessageDialog(null, "The payment is lower than  the fee "); }
						 */

						model.createPayment(movementID, -newRefund, refundDate, true);
						JOptionPane.showMessageDialog(null, "The payment has been registered");

					} else if (COIIPA) { // Movement made by COIIPA to the teacher
						if ((toReturn) > 0) { // Payment higher than it should be

							String difference = String.format("%.2f", (newRefund - toReturn));

							int option = JOptionPane.showConfirmDialog(null, "The amount of the movement (" + newRefund
									+ ") is higher than the amount that has to be returned to the teacher . Do you want to return the difference ("
									+ toReturn + ")?", "Warning", JOptionPane.YES_NO_OPTION,
									JOptionPane.WARNING_MESSAGE);

							if (option == 0) { // The user clicked YES
								// float amount = newRefund - toReturn; // the amount to compensate
								model.createPaymentRefund(movementID, -toReturn, refundDate, true);
							}
						} else if (toReturn < 0) { // Payment lower than it should be
							JOptionPane.showMessageDialog(null, "The payment is lower than  the remuneration ");
						}

						model.createPaymentRefund(movementID, newRefund, refundDate, true);
						JOptionPane.showMessageDialog(null, "The payment has been registered");

					}
				} catch (SQLException | ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public void initView() {

		view.setVisible(true);
		view.setTable(getDataModel(model.getAllData()));

	}

	public TableModel getDataModel(Data[] datas) {

		String header[] = { "Course name", "status", "Teacher name", "Teacher surname", "Remuneration" };

		ArrayList<String[]> rows = new ArrayList<String[]>();

		for (int i = 0; i < datas.length; i++) {
			Data d = datas[i];

			rows.add(new String[] { d.formativeAction.getName(), d.formativeAction.getStatus().toString(),
					d.teacher.getName(), d.teacher.getSurname(), Float.toString(d.teacherTeaches.getRemuneration()) });

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

	/**
	 * Expects the input to be according to
	 * {@link Model#getDataForPaymentsTable(Data)}
	 * 
	 * @param pt
	 * @return
	 */
	public TableModel getModelPayments(List<String[]> pt) {
		String header[] = { "Sender", "Receiver", "date", "Amount" };

		String body[][] = new String[pt.size()][header.length];

		for (int i = 0; i < pt.size(); i++) {
			String[] str = pt.get(i);
			body[i] = new String[] { str[0], str[1], str[2], str[3] };
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
