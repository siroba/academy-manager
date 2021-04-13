package PL53.swing;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class CheckboxTableModel extends DefaultTableModel {
	// Auto generated Serial ID
	private static final long serialVersionUID = -8888080945748327616L;

	public CheckboxTableModel() {
		this.addColumn("Check");
	}
	
	public void addColumns(String[] header) {
		for(String s: header) {
			this.addColumn(s);
		}
	}
	
	public void addRows(Object[][] rows) {
		for(Object[] row: rows)
			this.addRow(row);
	}
	
	public void addRow(Object[] row) {
		Vector<Object> v = convertToVector(row);
		v.add(0, false);
		
        addRow(v);	
	}

	@Override
	public Class<?> getColumnClass(int column) {
		if(column == 0) {
			return Boolean.class;
		}else {
			return super.getColumnClass(column);
		}
	}
	
	/*@Override
	public void setValueAt(Object obj, int row, int column) {
		super.setValueAt(obj, row, column+1);
	}
	
	
	
	public void setChecked(boolean b, int row) {
		super.setValueAt(b, row, 0);
	}*/
}
