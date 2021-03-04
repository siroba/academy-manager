package UserStory13574;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entities.FormativeAction;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

public class View extends JFrame {
	// Auto-generated serial ID
	private static final long serialVersionUID = -70145237442153458L;
	
	private JPanel contentPane;
	private JButton btnEnroll;
	private JList<String> faList; 

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblEnr = new JLabel("Enroll in a Formative Action");
		lblEnr.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblEnr, BorderLayout.NORTH);
		
		faList = new JList<String>();
		faList.setValueIsAdjusting(true);
		faList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPane.add(faList, BorderLayout.CENTER);
		
		btnEnroll = new JButton("Enroll");
		contentPane.add(btnEnroll, BorderLayout.SOUTH);
	}

	public Window getFrame() {
		return this.getFrame();
	}
	
	public JButton getEnrollBtn() {
		return this.btnEnroll;
	}
	
	public void setFAList(List<FormativeAction> list) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();		
		
		for(FormativeAction fa: list)
			listModel.addElement(fa.getName());
		
		this.faList.setModel(listModel);
	}
	
	public JList<String> getFAList(){
		return this.faList;
	}
}
