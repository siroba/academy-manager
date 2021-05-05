package ManageTeachers;

import java.sql.*;
import java.util.List;

import Entities.Teacher;
import Utils.Database;
import Utils.UnexpectedException;

public class Model {

	private Database db = new Database();

	// Insert a teacher to the Teacher table
	public void insertTeacher(Teacher t) throws SQLException {
		t.insert(db);
	}

	// Get all teachers from the Teacher table
	public List<Teacher> getListTeachers() throws SQLException {
		return Teacher.get("SELECT * FROM Teacher", db);
	}

	// Remove a teacher from the Teacher table
	public void removeTeacher(Teacher t) throws SQLException {
		t.removeTeacher(db);
	}

	// Update the phone, email and/or fiscal number of the selected teacher
	public void updateTeacher(Teacher t, boolean updatePhone, String phone, boolean updateEmail, String email,
			boolean updateFiscalNumber, String fiscalNumber) throws SQLException {
			if (updatePhone)
				t.updateTeacherPhone(db, phone);
			if (updateEmail)
				t.updateTeacherEmail(db, email);
			if (updateFiscalNumber)
				t.updateTeacherFiscalNumber(db, fiscalNumber);
	}
	
	// Check if it is possible to delete the teacher or if he teaches in an active or delayed formative action 
	public boolean checkTeacherTeachesInFormativeAction(Teacher t){
		boolean teaches = true; 
		try {
			// Setup connection & statements 
			Connection cn=db.getConnection(); //NOSONAR
			PreparedStatement pstmt;
			
			String sql = "select * from Teacher t "
					+ "left join TeacherTeaches tT on t.ID_teacher=tt.ID_teacher "
					+ "left join FormativeAction fA on fA.ID_fa=tt.ID_fa "
					+ "where t.name=? and t.surname=? and (fA.status='ACTIVE' or fA.status='DELAYED');";
			
			pstmt = cn.prepareStatement(sql);
			
			// Sets of the parameters of the prepared statement
			pstmt.setString(1, t.getName());
			pstmt.setString(2, t.getSurname());

			ResultSet rs = pstmt.executeQuery();
			
			// Check if the result set is empty, if yes the teacher can be removed 
			if (rs.next() == false) {
			      teaches = false;
			    }

			// Close statements and connection 
			rs.close();
			pstmt.close();
			cn.close();
			} catch (SQLException e) { 
				throw new UnexpectedException(e);
			}
			return teaches;
	}
}
