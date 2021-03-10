package UserStory13576;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import Utils.SwingUtil;

public class Controller {

	private Model model;
	private View view;
	private String lastSelectedKey=""; //remembers the last selected row to restore it when changing the race table

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		
		this.initView();
	}
	
	/**
	 * Init Controller
	 */
	public void initController() {
		view.getTableFormativeActions().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				SwingUtil.exceptionWrapper(() -> showDetails());
			}
		});
		
		view.getButtonSearch().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				
				try {
					java.util.Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(view.getEnrollmentStart().getText());
					java.util.Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(view.getEnrollmentEnd().getText());
					
					String start = formatter.format(startDate);
					String end = formatter.format(endDate);
					
					getListFormativeActionList(view.getComboBoxStatusSelection().getSelectedItem().toString(), start, end);
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Wrong pattern for date please use yyyy-MM-dd", "Error parsing date", JOptionPane.ERROR_MESSAGE);
				}
				
				
			
			}
		});
		
	}
	/**
	 * Init view
	 */
	public void initView() {
		//Updates the view date
		this.getListFormativeActionList("ACTIVE", "2021-01-01", "2030-11-14");
		
		view.getFrame().setVisible(true); 
	}
	
	/**
	 * Getting the list of FormativeActions just needs to get the list of objects from the model 
	 * and use SwingUtil method to create a table model which is finally assigned to the table.
	 */
	public void getListFormativeActionList(String status, String start, String end) {
		List<FormativeActionList> formativeActions=model.getListFormativeAction(status, start, end); // and fa.dateFA >= 04-03-2021
		TableModel tmodel=SwingUtil.getTableModelFromPojos(formativeActions, new String[] {"nameOfAction", "status", "enrollmentPeriodStart", "enrollmentPeriodEnd", "totalPlaces", "leftPlaces", "date"});
		view.getTableFormativeActions().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTableFormativeActions());
	}
	/**
	 * Method to display details about a selected formative action 
	 */
	public void showDetails() {
		//Obtains the selected key and saves it to remember the selection in future interactions.
		this.lastSelectedKey=SwingUtil.getSelectedKey(view.getTableFormativeActions());
		
		//Details of the selected career
		FormativeActionDetails formativeActionDetails=model.getFormativeActionDetails(this.lastSelectedKey);
		TableModel tmodel=SwingUtil.getRecordModelFromPojo(formativeActionDetails, new String[] {"objectives", "mainContents","place", "teacher_name"});
		view.getTableFormativeActionDetails().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTableFormativeActionDetails());
	}
}
