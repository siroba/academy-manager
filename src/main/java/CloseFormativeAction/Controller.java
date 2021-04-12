package CloseFormativeAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Controller {
	
	Model model;
	View view;
	
	public Controller() {
		model = new Model();
		view = new View();
		
		initView();
	}
	
	public void initController() {
		view.getListFormativeActions().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				String current_sel = view.getListFormativeActions().getSelectedValue();
				if (current_sel.isEmpty()) {
					view.getBtnCloseFA().setEnabled(false);
				}
				else {
					view.getBtnCloseFA().setEnabled(true);
				}
			}
		});
		
		view.getBtnCloseFA().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkFormativeAction()) {
					closeFormativeAction();
				}
				view.getFrame().setVisible(false);
			}
		});
		
	}
	
	public void initView(){
		getListFormativeActionList();
		view.getFrame().setVisible(true);
	}
	
	public void getListFormativeActionList() {
		List<String> listFormativActions = model.getListFormativeAction();
		DefaultListModel<String> m = (DefaultListModel<String>) view.getListFormativeActions().getModel();

		for (int i = 0; i < listFormativActions.size(); i++) {
			m.addElement(listFormativActions.get(i));
		}
	}
	
	public boolean checkFormativeAction() {
		
		String error = "The following error occured while trying to close the formative action:\n\n";
		Boolean errorOccured = false;
		Boolean ret = false;
		String fa = view.getListFormativeActions().getSelectedValue();
		//System.out.println("Formative Acction: " + fa);
		
		int confirmedIncomeProfessional = model.getSumIncomeConfirmedProfessional(fa);
		int expectedIncomeProfessional = model.getSumIncomeExpectedProfessional(fa);
		if (confirmedIncomeProfessional != expectedIncomeProfessional) {
			errorOccured = true;
			error += "The confirmed income from the professionals does not correspond with the expected income\n";
		}
//		System.out.println("Confirmed Income " + confirmedIncomeProfessional);
//		System.out.println("Expected Income " + expectedIncomeProfessional);
		
		int confirmedPaymentsToTeacher = model.getPaymentTeacherConfirmed(fa);
		int expectedPaymentsToTeacher = model.getPaymentTeacherExpected(fa);
		if (confirmedPaymentsToTeacher != expectedPaymentsToTeacher) {
			errorOccured = true;
			error += "The confirmed payments to the teacher does not correspond with the expected payments\n";
		}
//		System.out.println("Confirmed Payments " + confirmedPaymentsToTeacher);
//		System.out.println("Expected Payments " + expectedPaymentsToTeacher);
		
		
		// TODO check if all sessions are in the past
		Date lastSession = model.getDateOfLastSession(fa);
		if (lastSession.after(new Date()) || lastSession.equals(new Date())) {
			errorOccured = true;
			error += "Not all sessions of the formative action have been celebrated\n";
		}
//		System.out.println(error);
//		System.out.println("last session: " + lastSession + "\ncurrent date: " + new Date());
		if (errorOccured) {
			int i = JOptionPane.showConfirmDialog(null, error + "\nYou still want to close the formative action?", "Confirm close of Formative Action", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (i == 0) {
				ret = true;
			}
			else {
				ret = false;
			}
		}
		return ret;
		
	}
	
	public void closeFormativeAction() {
		model.closeFormativeAction(view.getListFormativeActions().getSelectedValue());
	}

}
