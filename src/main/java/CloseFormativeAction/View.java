package CloseFormativeAction;

import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

public class View {
	
	private JFrame frame;
	private JList<String> listFormativeActions;
	private JButton btnCloseFA;
	
	public View() {
		initComponents();
	}
	
	private void initComponents() {
		frame = new JFrame();
		frame.setTitle("Close Formative Action");
		frame.setName("Close Formative Action");
		frame.setBounds(0, 0, 500, 350);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		JLabel label = new JLabel();
		frame.getContentPane().add(label, "cell 0 0, grow");
		
		listFormativeActions = new JList<String>();
		listFormativeActions.setModel(new DefaultListModel<String>());
		JScrollPane listPanel = new JScrollPane(listFormativeActions);
		listPanel.setMinimumSize(new Dimension(100, 200));
		frame.getContentPane().add(listPanel, "cell 0 1, grow");
		
		btnCloseFA = new JButton("Close");
		btnCloseFA.setEnabled(false);
		frame.getContentPane().add(btnCloseFA, "cell 0 2");
	}

	public JFrame getFrame() { return frame; }
	public JList<String> getListFormativeActions() { return this.listFormativeActions; }
	public JButton getBtnCloseFA() { return this.btnCloseFA; }
}
