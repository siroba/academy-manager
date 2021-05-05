package RegisterPayment;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import BaseProject.SwingUtil;
import Entities.FormativeAction;
import Entities.Movement;
import Entities.Payment;
import Entities.Professional;
import Entities.Session;
import PL53.util.Date;
import PL53.util.DateTime;
import PL53.util.FileGenerator;
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

				selectedRow = model.getData(view.getSelectedInvoice());

				showPayments();
			}

		});

		view.getConfirmButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean aux = true;

				if (selectedRow == null) {
					JOptionPane.showMessageDialog(null, "You have to select one payment");
					return;
				}
				if (view.isCash() && view.getAmountPaidTextField() > 1000) {
					JOptionPane.showMessageDialog(null, "With cash the payments has to be lower than 1000�");
					return;
				}

				float alreadyPayed = model.getAmountPaid(selectedRow);
				float totalPayed = alreadyPayed + view.getAmountPayed();
				float toReturn = totalPayed - selectedRow.invoice.getAmount();
				Date payDate = view.getDateTextPane().getDate();

				if (view.getAmountPayed() <= 0) {
					JOptionPane.showMessageDialog(null, "You cannot do a payment for " + view.getAmountPayed() + "�");
					return;
				}

				int calcTime = DateTime.daysSince(view.getDateTextPane().getDate(), selectedRow.enrollment.getTimeEn());

				if (calcTime > 2 || calcTime < -1) {
					DateTime now = DateTime.now();
					long daysBetweenNowAction = DateTime.daysSince(now, view.getDateTextPane().getDate());

					if (daysBetweenNowAction < 0) {

						JOptionPane.showMessageDialog(null,
								"Payments cannot be made in the future( the date must be on the current date or in the past).",
								"date not valid ", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						try {
							if (model.getFreePlaces(selectedRow.formativeAction.getID()) > 0) {
								JOptionPane.showMessageDialog(null,
										"The payment has been made after the extipulated period, any way as there are still free places the enrollment is confirmed");

							} else {
								JOptionPane.showMessageDialog(null,
										"The payment must be done with a margin of 48 hours after the enrollmet");
								return;
							}
						} catch (HeadlessException | SQLException | ParseException e1) {
							e1.printStackTrace();
						}

					}
				}

				if (totalPayed > selectedRow.invoice.getAmount()) {
					aux = false;
					int option = JOptionPane.showConfirmDialog(null, "The sum of payments (" + totalPayed

							+ ") is hihger than the fee, Do you want to return the diferrence ("

							+ String.format("%.2f", (totalPayed - selectedRow.invoice.getAmount())) + ")?", "warning",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

					if (option == 0) {

						/*
						 * Movement invoiceReturn = new Movement(toReturn, payDate,
						 * selectedRow.invoice.getReceiver(), selectedRow.invoice.getSender(),
						 * selectedRow.invoice.getAddress(), selectedRow.invoice.getFiscalNumber(),
						 * selectedRow.invoice.getID_fa(), selectedRow.invoice.getID_professional());
						 */
						try {
							model.createPaymentRefund(selectedRow.invoice.getID(), -toReturn, payDate, view.isCash(),
									true);

						} catch (SQLException | ParseException e1) {

							e1.printStackTrace();
						}

					} else if (option == 1) {
						float amount = view.getAmountPaidTextField();

						try {
							model.createPayment(selectedRow.invoice, amount, payDate, view.isCash(), aux, totalPayed);

						} catch (SQLException | ParseException e1) {
							e1.printStackTrace();
						}

						return;
					}

				} else if (totalPayed < selectedRow.invoice.getAmount()) {
					JOptionPane.showMessageDialog(null, "The payment is lower than  the fee ");
					aux = false;
				}

				float amount = view.getAmountPaidTextField();

				try {
					model.createPayment(selectedRow.invoice, amount, payDate, view.isCash(), aux, totalPayed);
					JOptionPane.showMessageDialog(null, "The payment has been registered");
					view.resetAmountPaid();

					model.initModel();
					view.setTable(getTableModel(model.getAllData()));
				} catch (SQLException | ParseException e1) {
					e1.printStackTrace();
				}
			}

		});

		view.getBtnNewRefund().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date now = Date.now();
				Date refundDate = view.getdateTextPaneRefund().getDate();
				int daysBetweenNowAction = Date.daysSince(now, refundDate);

				float inputAmount = view.getAmountRefund();
				float totalPaid = model.getAmountPaid(selectedRow);
				float fee = selectedRow.fee;

				// Checks
				if (selectedRow == null) {
					JOptionPane.showMessageDialog(null, "You have to select one payment");
					return;

				}else if (inputAmount <= 0) {
					JOptionPane.showMessageDialog(null, "You cannot do a payment for " + view.getAmountPayed() + "�");

					return;
				}else if (daysBetweenNowAction < 0) {
					JOptionPane.showMessageDialog(null,
							"Payments cannot be made in the future( the date must be on the current date or in the past).",
							"date not valid ", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// If the user wants to compensate the movement
				if (inputAmount > totalPaid-fee) {
					int option = JOptionPane.showConfirmDialog(null, "The amount of the movement (" + inputAmount
							+ ") is hihger than the amount that has to bee returned to the professional (" + (totalPaid-fee) + "), Do you want to return the diferrence ("
							+ String.format("%.2f", (inputAmount - (totalPaid-fee))) + ")?", "warning",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

					if (option == 0) {
						float amount = inputAmount - (totalPaid-fee); // the amount to compensate
						try {
							model.createPaymentRefund(selectedRow.invoice.getID(), amount, refundDate, view.isCash(), true);
						} catch (SQLException | ParseException e1) {
							e1.printStackTrace();
						}

					} /*else if (option == 1) {
						try {
							model.createPaymentRefund(selectedRow.invoice.getID(), -amount, payDate, view.isCash(),
									true);

						} catch (SQLException | ParseException e1) {
							e1.printStackTrace();
						}

						return;
					}*/

				}

				try {
					boolean enrollmentConfirmed =  model.createPayment(selectedRow.invoice, amount, payDate, view.isCash(), aux, totalPayed);
					JOptionPane.showMessageDialog(null, "The payment has been registered");
					if (enrollmentConfirmed) {
						// Generate a file to confirm the enrollment
						Professional p = selectedRow.professional;
						FormativeAction fA = selectedRow.formativeAction;
						List<Session> ss = fA.getSessions();
						List<String> body = FileGenerator.bodyConfirmationEnrollment(fA, p, ss, selectedRow.fee, totalPayed);
						FileGenerator.generateFile("service@coiipa.com", p.getEmail(), "Confirmation of Enrollment", body, "ConfirmationEnrollment" + File.separator + "Confirmation_enrollment_fA" + fA.getID() + "_p" + p.getID() + ".txt");
					}
					view.resetAmountPaid();
					model.initModel();
					view.setTable(getTableModel(model.getAllData()));
				} catch (SQLException | ParseException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

	}

	public void initView() {

		view.setVisible(true);

		view.setTable(getTableModel(model.getAllData()));
	}

	public TableModel getTableModel(List<Data> list) {

		String header[] = { "Course name", "Professional name", "Professional surname", "Professional email", "Fee",
				"Date of the registration", "Amount paid", "Amount returned" };

		String body[][] = new String[list.size()][header.length];
		for (int i = 0; i < list.size(); i++) {
			Data d = list.get(i);

			body[i] = new String[] { d.formativeAction.getName(), d.professional.getName(), d.professional.getSurname(),
					d.professional.getEmail(), Float.toString(d.invoice.getAmount()),
					d.enrollment.getTimeEn().toString(), Float.toString(model.getAmountPaid(d)),
					Float.toString(model.getAmountReturned(d)) };
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

	public void showPayments() {
		List<AuxPayment> paymentList = model.getPayments(selectedRow.formativeAction.getName(),
				model.getData(view.getTable().getSelectedRow()));
		TableModel tmodel = SwingUtil.getTableModelFromPojos(paymentList,
				new String[] { "sender", "receiver", "date", "amount" });
		view.getMovementsTable().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getMovementsTable());

		/*
		 * float amount = 0; for (AuxPayment payment : paymentList) { amount +=
		 * payment.getAmount(); }
		 */
		// view.getLabelSummary().setText("" + amount);

	}

}
