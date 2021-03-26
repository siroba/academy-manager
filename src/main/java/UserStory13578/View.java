package UserStory13578;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DebugGraphics;
import javax.swing.JButton;

public class View extends JFrame {
	// Generated serial ID
	private static final long serialVersionUID = -7620725048709775087L;
	
	private JPanel contentPane;
	private JTable table;
	JButton btnCancelRegistration;
	
	/**
	 * Create the frame.
	 */
	public View() {
		setTitle("Select an enrollment to cancel");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 867, 337);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setRowMargin(3);
		table.setRowHeight(20);
		table.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
		table.setBounds(10, 10, 450, 300);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 855, 241);
		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);
		
		btnCancelRegistration = new JButton("Cancel registration");
		btnCancelRegistration.setBounds(329, 263, 207, 25);
		contentPane.add(btnCancelRegistration);
	}
	
	public JButton getBtnCancelRegistration() {
		return btnCancelRegistration;
	}
	
	public void setTable(TableModel tm) {
		this.table.setModel(tm);
	}
	
	public int getSelected() {
		return this.table.getSelectedRow();
	}
}
