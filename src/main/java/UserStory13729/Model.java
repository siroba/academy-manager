package UserStory13729;

import java.sql.SQLException;
import java.util.List;

import Entities.FormativeAction;
import Utils.Database;

public class Model {
	private Database db = new Database();
	private FormativeAction[] data;

	public void initModel() throws SQLException {
		String query = "SELECT * FROM FormativeAction WHERE status='ACTIVE'";

		List<FormativeAction> la = FormativeAction.get(query, db);

		data = new FormativeAction[la.size()];
		data = la.toArray(data);
	}

	public FormativeAction[] getAllData() {
		return data;
	}

}
