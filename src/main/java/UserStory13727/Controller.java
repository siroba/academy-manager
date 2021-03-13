package UserStory13727;

import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Entities.FormativeAction;

public class Controller implements PL53.util.Controller{
	private Model model;
	private View view;
	
	public Controller() {
		this.model = new Model();
		
		try {
			this.model.initModel();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		this.view = new View();
		this.initView();
	}

	@Override
	public void initController() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		
	}
	
	public TableModel getTableModel(FormativeAction data[]) {
		String header[] = { "Formative Action", "Date", "Teacher", "Total places"};

		String body[][] = new String[data.length][header.length];

		for (int i = 0; i < data.length; i++) {
			FormativeAction d = data[i];
			body[i] = new String[] { d.getName(), d.getFaStart().toString(), d.getTeacherName(), Integer.toString(d.getTotalPlaces())};
		}

		TableModel tm = new DefaultTableModel(header, body.length);

		for (int i = 0; i < body.length; i++) {
			for (int j = 0; j < header.length; j++) {
				tm.setValueAt(body[i][j], i, j);
			}
		}

		return tm;
	}
}
