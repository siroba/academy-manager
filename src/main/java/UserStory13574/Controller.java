package UserStory13574;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Entities.Enrollment;
import Entities.FormativeAction;
import Entities.Professional;
import Exceptions.InvalidFieldValue;
import PL53.SI2020_PL53.DateTime;

public class Controller {
	private Model model;
	private View view;
	private FormativeAction selected = null;

	public Controller() {
		this.model = new Model();
		
		try {
			model.loadFormativeActions();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.view = new View();
		//no model-specific initialization, only view-specific initialization
		this.initView();
	}

	/**
	 * Controller initialization: add event handlers to the UI objects.
	 * Each event handler is instantiated in the same way, so that it invokes a private method of this handler, enclosed in a generic exception handler to display windows.
	 * Each event handler is instantiated in the same way, so that it invokes a private method of this controller, enclosed in a generic exception handler to display popup windows when a problem or exception occurs.
	 * popup windows when a problem or controlled exception occurs.
	 */
	public void initController() {
		view.getEnrollBtn().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = view.getFAList().getSelectedIndex();
				if(index >= 0) {
					selected = selectFormativeAction(index);
					view.setTextSelectedFA(selected.getName());
					view.changeView(true);
				}
			}
		});

		view.getBtnConfirmAndEnroll().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = view.getProfName();
				String surname = view.getTextSurname();
				String phone = view.getTextPhone();
				String email = view.getTextEmail();

				try {
					Professional p = model.createProfessional(name, surname, phone, email);
					Enrollment en = p.enroll(selected, p, Enrollment.Status.RECEIVED, DateTime.now());

					model.doEnrollment(p, en);
				} catch (SQLException | ParseException e1) {
					e1.printStackTrace();
				} catch (InvalidFieldValue e2) {
					JOptionPane.showMessageDialog(null, e2.toString());
				}
			}
		});

		view.getBackButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.changeView(false);
			}
		});

		view.getFAList().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

			}
		});
	}


	public void initView() {
		//Open window (replaces the main generated by WindowBuilder)

		view.setFAList(model.getFormativeActions());
		view.setVisible(true);
	}

	private FormativeAction selectFormativeAction(int n) {
		return model.getFormativeAction(n);
	}


}
