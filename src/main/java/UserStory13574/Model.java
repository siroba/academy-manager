package UserStory13574;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

import Entities.Enrollment;
import Entities.FormativeAction;
import Entities.Professional;
import Exceptions.InvalidFieldValue;
import Utils.Database;


public class Model {

    private Database db = new Database();
    private List<FormativeAction> formativeActions;

    public void loadFormativeActions() throws SQLException, ParseException {
        String query ="SELECT * FROM FormativeAction "
                    + "WHERE enrollmentEnd>datetime('now','localtime') "
                    + "GROUP BY FormativeAction.nameFa "
                    + "HAVING (SELECT COUNT(Enrollment.ID_fa) FROM Enrollment WHERE Enrollment.ID_fa=FormativeAction.ID_fa)<totalPlaces;";

        this.formativeActions = FormativeAction.get(query, db);
    }

    public List<FormativeAction> getFormativeActions(){
        return this.formativeActions;
    }

    public FormativeAction getFormativeAction(int n) {
        return this.formativeActions.get(n);
    }

    public Professional createProfessional(String name, String surname, String phone, String email) throws SQLException, InvalidFieldValue {
        if(!Professional.checkEmail(email)) throw new InvalidFieldValue("Email", email);
        else if(!Professional.checkPhone(phone)) throw new InvalidFieldValue("Phone", phone);

        Professional p = new Professional(name, surname, phone, email);

        return p;
    }

    public void doEnrollment(Professional p, Enrollment en) throws SQLException, ParseException {
        p.insert(db);
        en.setID_professional(p.getID());
        en.insert(db);
    }
}