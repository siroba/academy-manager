package BaseProject;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.commons.beanutils.PropertyUtils;

/**
  * Utility methods for user interfaces with swing (populating tables from a POJO object that has been obtained from the database, exception handling for POJO methods).
 * that has been obtained from the database, exception handling for controller methods, auto-sizing of columns, etc.). 
 * controller methods, auto-sizing of columns, etc.)
 */
public class SwingUtil {
	private SwingUtil() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Executes a method in response to an event by wrapping it in a standard exception handler.
	 * displays an informational message or error message depending on the exception raised
	 * (used in the Controller when installing the handlers in response to swing events)
	 * NOTE: If returning parameters use Consumer instead of Runnable: http://www.baeldung.com/java-lambda-exceptions
	 * @param consumer Method to execute (no input parameters or output values)
	 */
	public static void exceptionWrapper(Runnable consumer) {
		try {
			consumer.run();
		} catch (ApplicationException e) { //controlled exception from which the application can be retrieved
			showMessage(e.getMessage(), "Informacion",JOptionPane.INFORMATION_MESSAGE);
		} catch (RuntimeException e) { //restof exceptions, in addition to the info window shows the stacktrace
			e.printStackTrace(); //NOSONAR
			showMessage(e.toString(), "Excepcion no controlada",JOptionPane.ERROR_MESSAGE);
		}
	}	 
	private static void showMessage(String message, String title, int type) {
		//Since this method does not receive the context of the application window, 
		//does not use the static showMessageDialog method of JOptionPane 
		//and sets the position so that it does not appear in the center of the screen.
	    JOptionPane pane = new JOptionPane(message,type,JOptionPane.DEFAULT_OPTION);
	    pane.setOptions(new Object[] {"ACEPTAR"}); //set this value so that it is not language-dependent
	    JDialog d = pane.createDialog(pane, title);
	    d.setLocation(200,200);
	    d.setVisible(true);
	}
	
	/**
	 * Sets all columns of the table to the size corresponding to the tablemodel content.
	 */
	public static void autoAdjustColumns(JTable table) {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // if used ON the last column will be expanded in the panel.
		TableColumnAdjuster tca=new TableColumnAdjuster(table);
		tca.adjustColumns();
	}

	/** 
	 * Gets the key (first column) of the selected row in the race table or empty string (if it does not exist).
	 */
	public static String getSelectedKey(JTable table) {
		int row=table.getSelectedRow(); //the item in the first column is the race id
		if (row>=0)
			return (String)table.getModel().getValueAt(row,0);
		else //no rows selected
			return "";
	}
	
	/** 
	 * Selects the table row with the specified key and returns the value of the resulting key of the selected row.
	 * (the same key or empty string if the row does not exist)
	 */
	public static String selectAndGetSelectedKey(JTable table, String key) {
		for (int i=0; i<table.getModel().getRowCount(); i++)
			if (table.getModel().getValueAt(i, 0).equals(key)) {
				table.setRowSelectionInterval(i, i);
				return key;
			}
		return ""; //this key no longer exists
	}

	//http://www.baeldung.com/apache-commons-beanutils
	//http://commons.apache.org/proper/commons-beanutils/javadocs/v1.8.2/apidocs/org/apache/commons/beanutils/PropertyUtilsBean.html

	/**
	 * Creates a tablemodel from a list of POJO objects with the columns indicated.
	 * @param pojos List of objects whose attributes will be used to create the tablemodel.
	 * (uses apache commons beanutils). If null only creates the tablemodel with the column headers.
	 * @param colProperties The attribute names of the objects (sorted) that will be included in the tablemodel
	 * (each one must have the corresponding getter)
	 */
	public static <E> TableModel getTableModelFromPojos(List<E> pojos, String[] colProperties) {
		//Initial tablemodel creation and dimensioning
		//note that in order for the table to display the columns it must be inside a JScrollPane
		TableModel tm;
		if (pojos==null) // only the columns (e.g. for initializations)
			return new DefaultTableModel(colProperties,0);
		else
			tm=new DefaultTableModel(colProperties, pojos.size());
		//loads each of the pojos values using PropertyUtils (from apache coommons beanutils)
		for (int i=0; i<pojos.size(); i++) {
			for (int j=0; j<colProperties.length; j++) {
				try {
					Object pojo=pojos.get(i);
					Object value=PropertyUtils.getSimpleProperty(pojo, colProperties[j]);
					tm.setValueAt(value, i, j);
				} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					throw new UnexpectedException(e);
				}
			}
		}
		return tm;
	}
	public static <E> TableModel getRecordModelFromPojo(E pojo, String[] colProperties) {
		//Initial tablemodel creation and dimensioning
		//since there will be only two columns I put a header with two empty values, so that 
		//appear very small but with the necessary handler to be able to resize it
		TableModel tm;
		tm=new DefaultTableModel(new String[] {"",""}, colProperties.length);
		//loads each of the pojos values using PropertyUtils (from apache commons beanutils)
			for (int j=0; j<colProperties.length; j++) {
				try {
					tm.setValueAt(colProperties[j], j, 0);
					Object value=PropertyUtils.getSimpleProperty(pojo, colProperties[j]);
					tm.setValueAt(value, j, 1);
				} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					throw new UnexpectedException(e);
				}
			}
		return tm;
	}
	
	/**
	 * Creates a Comboboxmodel from a list of objects.
	 * @param lst List of arrays of objects from which the first of each will be used to populate the combo.
	 */
	public static ComboBoxModel<Object> getComboModelFromList(List<Object[]> lst) {
		DefaultComboBoxModel<Object> cm=new DefaultComboBoxModel<>();
		for (int i=0; i<lst.size(); i++) {
			Object value=lst.get(i)[0];
			cm.addElement(value);
		}
		return cm;
	}
	
	

}