package UserStory13579;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Entities.FormativeAction;
import Entities.Professional;
import Utils.DbUtil;
import Utils.SwingUtil;
import Utils.Util;

public class Controller {

	private Model model;
	private View view;

	public Controller(Model m, View v) {
		this.model = m;
		this.view = v;
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
		//ActionListener defines only one actionPerformed() method, it is a functional interface that can be invoked as follows:
		//view.getBtnTablaCarreras().addActionListener(e -> getListaCarreras());
		//then I invoke the method that responds to the listener in the exceptionWrapper to take care of the exceptions.
	}
	
	public void initView() {
		// Updates the view data
		this.getListFormativeActions();
		this.getListTotalBalance();
		
		//Open window (replaces the main generated by WindowBuilder)
		view.getFrame().setVisible(true); 
	}
	
	/**
	 * Getting the list of races just needs to get the list of objects from the model 
	 * and use SwingUtil method to create a tablemodel which is finally assigned to the table.
	 */
	public void getListFormativeActions() {
		List<FinancialBalance> financialBalances = model.getListFinancialBalanceNoFilter();
		TableModel tmodel=SwingUtil.getTableModelFromPojos(financialBalances, new String[] {"date", "name", "status", "incomeConfirmed", "expensesConfirmed", "balanceConfirmed", "incomeEstimated", "expensesEstimated", "balanceEstimated"}, new String[] {"date", "name", "status", "income confirmed", "expenses confirmed", "balance confirmed", "income estimated", "expenses estimated", "balance estimated"}, true);
		view.getTableFormativeActionBalance().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTableFormativeActionBalance());
	}
	
	public void getListTotalBalance() {
		List<TotalBalance> totalBalance = model.getListTotalBalanceNoFilter();
		TableModel tmodel=SwingUtil.getTableModelFromPojos(totalBalance, new String[] {"totalIncomeConfirmed", "totalExpensesConfirmed", "totalIncomeEstimated", "totalExpensesEstimated"}, new String[] {"total income confirmed", "total expenses confirmed", "total income estimated", "total expenses estimated"}, false);
		view.getTableTotalBalance().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTableTotalBalance());
	}
}
