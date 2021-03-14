package UserStory13727;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;

public class View extends JFrame {

	// Auto generated serial ID
	private static final long serialVersionUID = -4972701049019946391L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnCancel;

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 765, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 27, 742, 171);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblChooseAFormative = new JLabel("Choose a Formative Action to cancel:");
		lblChooseAFormative.setBounds(12, 0, 273, 15);
		contentPane.add(lblChooseAFormative);
		
		btnCancel = new JButton("Cancel the Formative Action");
		btnCancel.setBounds(253, 207, 258, 25);
		contentPane.add(btnCancel);
	}

	public void setTable(TableModel tm) {
		table.setModel(tm);
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}

	public int getSelected() {
		return table.getSelectedRow();
	}
}
