package UserStory13576;

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

	private final String WITH_STATUS_FILTER = "SELECT fa.nameFa, s.ID_session, fa.status, fa.enrollmentStart, fa.enrollmentEnd, fa.totalPlaces,(fa.totalPlaces - count(e.ID_fa)) AS leftPlaces, s.sessionStart "
			+ "FROM FormativeAction fa " + "INNER JOIN Session s ON fa.ID_fa = s.ID_fa "
			+ "LEFT JOIN Enrollment e ON e.ID_fa = fa.ID_fa " + "WHERE lower(fa.status) = ? "
			+ "AND date(s.sessionStart) BETWEEN ? AND ?" + "GROUP BY s.ID_session;";
	private final String WITHOUT_STATUS_FILTER = "SELECT fa.nameFa, s.ID_session, fa.status, fa.enrollmentStart, fa.enrollmentEnd, fa.totalPlaces,(fa.totalPlaces - count(e.ID_fa)) AS leftPlaces, s.sessionStart "
			+ "FROM FormativeAction fa " + "INNER JOIN Session s ON fa.ID_fa = s.ID_fa "
			+ "LEFT JOIN Enrollment e ON e.ID_fa = fa.ID_fa " + "WHERE date(s.sessionStart) BETWEEN ? AND ?"
			+ "GROUP BY s.ID_session;";

	public List<FormativeActionList> getListFormativeAction(String filterStatusString, String filterDateBegin,
			String filterDateEnd, boolean filterStatus) {
		try {
			Database db = new Database();
			Connection cn = db.getConnection();
			PreparedStatement ps;
			if (filterStatus) {
				ps = cn.prepareStatement(WITH_STATUS_FILTER);
				ps.setString(1, filterStatusString);
				ps.setString(2, filterDateBegin);
				ps.setString(3, filterDateEnd);

			} else {
				ps = cn.prepareStatement(WITHOUT_STATUS_FILTER);
				ps.setString(1, filterDateBegin);
				ps.setString(2, filterDateEnd);
			}
			ResultSet rs = ps.executeQuery();

			List<FormativeActionList> formativeActionList = new ArrayList<>();

			String checkName = "";
			int index = 1;
			while (rs.next()) {
				if (!checkName.equals(rs.getString("nameFa"))) {
					index = 1;
					checkName = rs.getString("nameFa");
				} else {
					index++;

				}
				FormativeActionList fs = new FormativeActionList(rs.getString("nameFa"), rs.getString("status"),
						Date.parse(rs.getTimestamp("enrollmentStart")), Date.parse(rs.getTimestamp("enrollmentEnd")),
						rs.getInt("totalPlaces"), rs.getInt("leftPlaces"), Date.parse(rs.getTimestamp("sessionStart")),
						index);
				formativeActionList.add(fs);
			}
			rs.close();
			ps.close();
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
			Database db = new Database();
			Connection cn = db.getConnection();
			StringBuilder query = new StringBuilder();
			query.append("SELECT fa.objectives, fa.mainContent, s.location, s.teacherName ");
			query.append("from FormativeAction fa ");
			query.append("INNER JOIN Session s ");
			query.append("ON fa.ID_fa=s.ID_fa ");
			query.append("where nameFa=?;");
			PreparedStatement ps = cn.prepareStatement(query.toString());
			ps.setString(1, lastSelectedKey);
			ResultSet rs = ps.executeQuery();

			List<FormativeActionDetails> formativeActionDetails = new ArrayList<>();

			FormativeActionDetails fs = new FormativeActionDetails(rs.getString("objectives"),
					rs.getString("mainContent"), rs.getString("location"), rs.getString("teacherName"));
			formativeActionDetails.add(fs);

			rs.close();
			ps.close();
			cn.close();
			return fs;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}
}
