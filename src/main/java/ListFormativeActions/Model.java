package ListFormativeActions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PL53.util.Date;
import Utils.Database;
import Utils.UnexpectedException;

public class Model {
	
	private Database db;

	private final String WITH_STATUS_FILTER = "select fa.nameFa, fa.status, fa.enrollmentStart, fa.enrollmentEnd, fa.totalPlaces "
			+ "from FormativeAction fa left join Enrollment e on e.ID_fa = fa.ID_fa AND (e.ID_fa, e.ID_professional) in "
			+ "(select e.ID_fa, e.ID_professional from Enrollment e where e.status = 'RECEIVED' OR e.status = 'CONFIRMED' )"
			+ "INNER JOIN Session s on fa.ID_fa = s.ID_fa "
			+ "WHERE lower(fa.status) = ? AND date(s.sessionStart) BETWEEN ? AND ? group by fa.ID_fa; ";
	private final String WITHOUT_STATUS_FILTER = "select fa.nameFa, fa.status, fa.enrollmentStart, fa.enrollmentEnd, fa.totalPlaces "
			+ "from FormativeAction fa left join Enrollment e on e.ID_fa = fa.ID_fa AND (e.ID_fa, e.ID_professional) in "
			+ "(select e.ID_fa, e.ID_professional from Enrollment e where e.status = 'RECEIVED' OR e.status = 'CONFIRMED') "
			+ "INNER JOIN Session s on fa.ID_fa = s.ID_fa "
			+ "WHERE date(s.sessionStart) BETWEEN ? AND ?group by fa.ID_fa; ";
	
	public Model() {
		db = new Database();
	}

	public List<FormativeActionList> getListFormativeAction(String filterStatusString, String filterDateBegin,
			String filterDateEnd, boolean filterStatus) {
		try {
			Connection cn = db.getConnection();
			PreparedStatement psFormativeActions;
			if (filterStatus) {
				psFormativeActions = cn.prepareStatement(WITH_STATUS_FILTER);
				psFormativeActions.setString(1, filterStatusString);
				psFormativeActions.setString(2, filterDateBegin);
				psFormativeActions.setString(3, filterDateEnd);
			} else {
				psFormativeActions = cn.prepareStatement(WITHOUT_STATUS_FILTER);
				psFormativeActions.setString(1, filterDateBegin);
				psFormativeActions.setString(2, filterDateEnd);
			}
			ResultSet rsFormativeActions = psFormativeActions.executeQuery();

			List<FormativeActionList> formativeActionList = new ArrayList<>();

			while (rsFormativeActions.next()) {
				PreparedStatement psLeftPlaces = cn
						.prepareStatement("select totalPlaces, (totalPlaces  - count(e.ID_fa)) as leftPlaces "
								+ "from FormativeAction fa " + "left join Enrollment e on fa.ID_fa = e.ID_fa AND (e.ID_fa, e.ID_professional) in "
								+"(select e.ID_fa, e.ID_professional from Enrollment e where e.status = 'RECEIVED' OR e.status = 'CONFIRMED' )"
								+ "where fa.nameFa = ?;");
				psLeftPlaces.setString(1, rsFormativeActions.getString("nameFa"));

				PreparedStatement psFirstSessionStart = cn.prepareStatement(
						"select s.sessionStart " + "from Session s inner join FormativeAction fa on fa.ID_fa = s.ID_fa "
								+ "where fa.nameFa = ? order by s.ID_session ASC LIMIT 1;");
				psFirstSessionStart.setString(1, rsFormativeActions.getString("nameFa"));

				PreparedStatement psLastSessionStart = cn.prepareStatement(
						"select s.sessionStart " + "from Session s inner join FormativeAction fa on fa.ID_fa = s.ID_fa "
								+ "where fa.nameFa = ? order by s.ID_session DESC LIMIT 1;");
				psLastSessionStart.setString(1, rsFormativeActions.getString("nameFa"));

				ResultSet leftPlaces = psLeftPlaces.executeQuery();
				ResultSet firstSession = psFirstSessionStart.executeQuery();
				ResultSet lastSession = psLastSessionStart.executeQuery();

				FormativeActionList fs = new FormativeActionList(rsFormativeActions.getString("nameFa"),
						rsFormativeActions.getString("status"),
						Date.parse(rsFormativeActions.getTimestamp("enrollmentStart")),
						Date.parse(rsFormativeActions.getTimestamp("enrollmentEnd")),
						rsFormativeActions.getInt("totalPlaces"), leftPlaces.getInt("leftPlaces"),
						Date.parse(firstSession.getTimestamp("sessionStart")),
						Date.parse(lastSession.getTimestamp("sessionStart")));
				formativeActionList.add(fs);

				psLeftPlaces.close();
				psFirstSessionStart.close();
				psLastSessionStart.close();
				leftPlaces.close();
				firstSession.close();
				lastSession.close();
			}
			rsFormativeActions.close();
			psFormativeActions.close();
			cn.close();
			return formativeActionList;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * Method to get detailed information about a course. Objectives of the
	 * formative action Main Content of the formative action Location of the
	 * formative action Name of the teacher
	 */
	public FormativeActionDetails getFormativeActionDetails(String lastSelectedKey) {
		try {
			Connection cn = db.getConnection();
			PreparedStatement ps = cn.prepareStatement("SELECT fa.objectives, fa.mainContent, s.location, t.name "
					+ "from FormativeAction fa "
					+ "INNER JOIN Session s ON fa.ID_fa=s.ID_fa "
					+ "LEFT join TeacherTeaches tt on tt.ID_fa=fa.ID_fa "
					+ "LEFT join Teacher t on tt.ID_teacher = t.ID_teacher "
					+ "where nameFa = ?;");
			ps.setString(1, lastSelectedKey);
			ResultSet rs = ps.executeQuery();
			
			FormativeActionDetails fs;

			List<String> locations = new ArrayList<>();
			List<String> teachers = new ArrayList<>();
			List<String> objectives = new ArrayList<>();
			List<String> mainContents = new ArrayList<>();
			while (rs.next()) {
				if (!locations.contains(rs.getString("location"))) { 
					locations.add(rs.getString("location"));
				}
				if (!teachers.contains(rs.getString("name")))
					teachers.add(rs.getString("name"));
				
				if (!objectives.contains(rs.getString("objectives")))
					objectives.add(rs.getString("objectives"));
				
				if (!mainContents.contains(rs.getString("mainContent")))
					mainContents.add(rs.getString("mainContent"));
			}
			
			fs = new FormativeActionDetails(String.join(", ", objectives),
					String.join(", ", mainContents), String.join(", ", locations), String.join(", ", teachers));

			

			rs.close();
			ps.close();
			cn.close();
			return fs;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}
}
