package ManageTeachers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Entities.Teacher;
import Utils.SwingUtil;

public class Controller{
	private Model model;
	private View view;

	public Controller(ManageTeachers.Model model, ManageTeachers.View view){
		this.model = model;
		this.view = view;
		
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
		// Add a teacher
		view.getAdd().addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if (view.getTeacherName().isBlank()) {
		    		JOptionPane.showMessageDialog(null,
						    "You need to provide a name to add a teacher.",
						    "Name required",
						    JOptionPane.ERROR_MESSAGE);
					return;
		    	}
		    	if (view.getTeacherSurname().isBlank()) {
		    		JOptionPane.showMessageDialog(null,
						    "You need to provide a surname to add a teacher.",
						    "Surname required",
						    JOptionPane.ERROR_MESSAGE);
					return;
		    	}
		    	if (!view.getTeacherPhone().isBlank() && !Teacher.checkPhone(view.getTeacherPhone())) {
		    		JOptionPane.showMessageDialog(null,
						    "Please provide a valid phone number.",
						    "Invalid phone number",
						    JOptionPane.ERROR_MESSAGE);
        			return; 
		    	}
				if (!view.getTeacherEmail().isBlank() && !Teacher.checkEmail(view.getTeacherEmail())) {
					JOptionPane.showMessageDialog(null,
						    "Please provide a valid email adress.",
						    "Invalid email",
						    JOptionPane.ERROR_MESSAGE);
        			return; 
				}
				if (!view.getTeacherFiscalNumber().isBlank() && !Teacher.checkFiscalNumber(view.getTeacherFiscalNumber())) {
					JOptionPane.showMessageDialog(null,
						    "Please provide a valid fiscal number. E.g. \\\"55566677R\\.",
						    "Invalid fiscal number",
						    JOptionPane.ERROR_MESSAGE);
        			return; 
				}
		    	Teacher t = new Teacher(view.getTeacherName(), view.getTeacherSurname(), view.getTeacherPhone(), view.getTeacherEmail(), view.getTeacherFiscalNumber());
		    	try {
					model.insertTeacher(t);
					JOptionPane.showMessageDialog(null,
						    "The teacher has been added successfully.",
						    "Action successfull",
						    JOptionPane.INFORMATION_MESSAGE);
					initView();
				// TODO Validate phone, email, fiscal number if provided
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		    }
		});
		// Selection listener to update selected teacher name and enable fire/update button and text fields 
		view.getTableTeachers().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	String name = SwingUtil.getSelectedValue(view.getTableTeachers(), 0);
	        	String surname = SwingUtil.getSelectedValue(view.getTableTeachers(), 1);
	        	view.setSelectedTeacher(name + " " + surname);
	    		view.getTFPhoneUpdate().setEditable(true);
	    		view.getTFEmailUpdate().setEditable(true);
	    		view.getTFFiscalNumberUpdate().setEditable(true);
	    		view.getRemove().setEnabled(true);
				view.getUpdate().setEnabled(true);
	            
	        }
	    });
		// Remove a teacher
		view.getRemove().addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
	        	String name = SwingUtil.getSelectedValue(view.getTableTeachers(), 0);
	        	String surname = SwingUtil.getSelectedValue(view.getTableTeachers(), 1);
	        	Teacher t = new Teacher(name, surname, "", "", "");
	        	// Check if the teacher can be deleted 
	        	if (model.checkTeacherTeachesInFormativeAction(t)) {
	        		JOptionPane.showMessageDialog(null,
						    "The teacher can not be removed because she/he teaches in an active formative action.",
						    "Action not successfull",
						    JOptionPane.INFORMATION_MESSAGE);
	        		return; 
	        	}
		    	try {
		    		// Remove teacher from the db 
					model.removeTeacher(t);
					JOptionPane.showMessageDialog(null,
						    "The teacher has been removed successfully.",
						    "Action successfull",
						    JOptionPane.INFORMATION_MESSAGE);
					setTeachers();
					initView();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		    }
		});
		// Update teacher 
		view.getUpdate().addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	boolean updatePhone = false; 
		    	boolean updateEmail = false; 
		    	boolean updateFiscalNumber=false; 
	        	String name = SwingUtil.getSelectedValue(view.getTableTeachers(), 0);
	        	String surname = SwingUtil.getSelectedValue(view.getTableTeachers(), 1);
	        	Teacher t = new Teacher(name, surname, "", "", "");
	        	String phone = view.getTeacherPhoneUpdate();
	        	if (!phone.isBlank()) {
	        		updatePhone = true;
	        		if (!Teacher.checkPhone(phone)) {
						JOptionPane.showMessageDialog(null,
							    "The information of the teacher have not been updated. Please provide a valid phone number.",
							    "Invalid phone number",
							    JOptionPane.ERROR_MESSAGE);
	        			return; 
	        		}		
	        	}
	        	String email = view.getTeacherEmailUpdate();
	        	if (!email.isBlank()) {
	        		updateEmail = true;
	        		if (!Teacher.checkEmail(email)) {
						JOptionPane.showMessageDialog(null,
							    "The information of the teacher have not been updated. Please provide a valid email adress.",
							    "Invalid email",
							    JOptionPane.ERROR_MESSAGE);
	        			return; 
	        		}
	        	}
	        	String fiscalNumber = view.getTeacherFiscalNumberUpdate();
	        	if (!fiscalNumber.isBlank()) {
	        		updateFiscalNumber = true;
	        		if (!Teacher.checkFiscalNumber(fiscalNumber)) {
						JOptionPane.showMessageDialog(null,
							    "The information of the teacher have not been updated. Please provide a valid fiscal number. E.g. \\\"55566677R\\.",
							    "Invalid fiscal number",
							    JOptionPane.ERROR_MESSAGE);
	        			return; 
	        		}
	        	}		
		    	try {
		    		// Check if there is anything to update 
		    		if (!updatePhone && !updateEmail && !updateFiscalNumber) {
		    			JOptionPane.showMessageDialog(null,
							    "You need to provide some data to update the teacher's information.",
							    "Nothing to update",
							    JOptionPane.INFORMATION_MESSAGE);
		    			return; 
		    		}
		    		// Update the data 
					model.updateTeacher(t, updatePhone, phone, updateEmail, email, updateFiscalNumber, fiscalNumber);
					JOptionPane.showMessageDialog(null,
						    "The information of the teacher have been updated succesfully.",
						    "Action successfull",
						    JOptionPane.INFORMATION_MESSAGE);
					setTeachers();
					initView();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		    }
		});
	}
	
	public void initView(){
		// Update the view data
		this.setTeachers();
		view.setTeacherName("");
		view.setTeacherSurname("");
		view.setTeacherPhone("");
		view.setTeacherEmail("");
		view.setTeacherFiscalNumber("");
		view.setTFPhoneUpdate("");
		view.setTFEmailUpdate("");
		view.setTFFiscalNumberUpdate("");
		view.setSelectedTeacher("Selected teacher:");
		view.getRemove().setEnabled(false);
		view.getUpdate().setEnabled(false);
		view.getTFPhoneUpdate().setEditable(false);
		view.getTFEmailUpdate().setEditable(false);
		view.getTFFiscalNumberUpdate().setEditable(false);

		
		//Open window (replaces the main generated by WindowBuilder)
		view.setVisible(true);
	}
	
	
	/**
	 * Get the list of teachers from the model 
	 * and create a table model which is finally assigned to the tables.
	 * @throws ParseException 
	 */
	public void setTeachers(){
		
		// Get teachers 
		List<Teacher> teachers = new ArrayList<Teacher>();
		try {
			teachers = model.getListTeachers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Set table model 
		String header[] = { "Name", "Surname", "Phone", "Email", "Fiscal Number"};
		String body[][] = new String[teachers.size()][header.length];
		for (int i = 0; i < teachers.size(); i++) {
			body[i] = new String[] {
					teachers.get(i).getName(),
					teachers.get(i).getSurname(),
					teachers.get(i).getPhone(),
					teachers.get(i).getEmail(),
					teachers.get(i).getFiscalNumber()
					};
		}
		TableModel tm = new DefaultTableModel(header, body.length);
		for (int i = 0; i < body.length; i++) {
			for (int j = 0; j < header.length; j++) {
				tm.setValueAt(body[i][j], i, j);
			}
		view.getTableTeachers().setModel(tm);
		}
	}
	
	
	
	
}
