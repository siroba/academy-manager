package UserStory13576;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.table.TableModel;

import Entities.FormativeAction;
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
		//TODO Not needed in the moment put back in after first table works correctly! 
		view.getTableFormativeActions().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//does not use mouseClicked because when setting single selection in the race table
				//the user could drag the mouse over several rows and only the last one is of interest.
				SwingUtil.exceptionWrapper(() -> showDetails());
			}
		});
		
	}
	/**
	 * Init view
	 */
	public void initView() {
		//Updates the view data
		this.getListFormativeActions();
		
		view.getFrame().setVisible(true); 
	}
	
	/**
	 * Getting the list of FormativeActions just needs to get the list of objects from the model 
	 * and use SwingUtil method to create a table model which is finally assigned to the table.
	 */
	public void getListFormativeActions() {
		List<FormativeAction> formativeActions=model.getListFormativeActions();
		TableModel tmodel=SwingUtil.getTableModelFromPojos(formativeActions, new String[] {"name", "status", "enrollmentPeriodStart", "enrollmentPeriodEnd", "spaces", "spaces", "day"});
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
