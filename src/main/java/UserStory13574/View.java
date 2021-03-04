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
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;

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
		setTitle("Enroll in a Formative Action");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnEnroll = new JButton("Enroll");
		btnEnroll.setBounds(5, 226, 430, 25);
		contentPane.setLayout(null);
		contentPane.add(btnEnroll);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 433, 214);
		contentPane.add(scrollPane);
		
		faList = new JList<String>();
		scrollPane.setViewportView(faList);
		faList.setValueIsAdjusting(true);
		faList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
