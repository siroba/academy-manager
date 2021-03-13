package UserStory13729;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import PL53.swing.DateTimeInput;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;

public class View extends JFrame {
	// Generated serial ID
	private static final long serialVersionUID = -2728093507089041550L;
	
	private JPanel contentPane;
	private JTable table;
	
	/**
	 * Create the frame.
	 */
	public View() {
		setTitle("Delay a Formative Action");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 473, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 441, 163);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblSelectTheFormative = new JLabel("Select the Formative Action you want to delay:");
		lblSelectTheFormative.setBounds(10, 12, 363, 15);
		contentPane.add(lblSelectTheFormative);
		
		JLabel lblDelay = new JLabel("Delay amount:");
		lblDelay.setBounds(10, 215, 166, 15);
		contentPane.add(lblDelay);
		
		DateTimeInput datetimeOffsetPanel = new DateTimeInput();
		datetimeOffsetPanel.setBounds(10, 229, 441, 58);
		contentPane.add(datetimeOffsetPanel);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(139, 299, 195, 25);
		contentPane.add(btnConfirm);
	}

	public void initView() {
		this.setVisible(true);
	}
	
	public void setTable(TableModel tm) {
		
	}
}
