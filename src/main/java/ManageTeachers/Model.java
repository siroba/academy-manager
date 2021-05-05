package ManageTeachers;

import java.sql.*;
import java.util.List;

import Entities.Teacher;
import Utils.Database;

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

	public void updateTeacher(Teacher t, boolean updatePhone, String phone, boolean updateEmail, String email,
			boolean updateFiscalNumber, String fiscalNumber) throws SQLException {
			if (updatePhone)
				t.updateTeacherPhone(db, phone);
			if (updateEmail)
				t.updateTeacherEmail(db, email);
			if (updateFiscalNumber)
				t.updateTeacherFiscalNumber(db, fiscalNumber);
	}
}
