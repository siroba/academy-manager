package CloseFormativeAction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Entities.FormativeAction;
import Utils.Database;

public class testModel {
	private static Database db = new Database();
	
	@Before
	public void setUp() {
		db.createDatabase(true);
		loadCleanDatabase(db);
	}
	
	@After
	public void tearDown() {
		
	}
	
	public static void loadCleanDatabase(Database db) {
//		db.loadDatabase();
		db.executeBatch(new String[] {
				"delete from FormativeAction; ",
				"delete from Session; ",
				"delete from Fee; ",
				"delete from Professional; ",
				"delete from Enrollment; ",
				"delete from Invoice; ",
				"delete from Payment; ",
				"delete from Teacher;",
				"delete from TeacherTeaches;",
				"delete from InvoiceTeacher;",
				"delete from PaymentTeacher;",
				
				"insert into FormativeAction(ID_fa, nameFa, totalPlaces, objectives, mainContent, status, enrollmentStart, enrollmentEnd) values\n"
				+ "	(1000, 'Initiation to scrum',	 6,	'some objctives', 'some main content', 'ACTIVE', '2021-01-01 00:00:00.0',	'2021-02-01 00:00:00.0'),\n"
				+ "	(1001, 'Initiation to agile',	10,	'some objctives', 'some main content', 'DELAYED', '2021-01-01 00:00:00.0',	'2021-02-08 00:00:00.0'),\n"
				+ "	(1002, 'JDBC',  		 		4, 	'some objctives', 'some main content', 'ACTIVE', '2021-03-01 00:00:00.0',	'2021-04-08 00:00:00.0'),\n"
				+ "	(1003, 'Java programming',	 	5,  'some objctives', 'some main content', 'ACTIVE', '2021-04-01 00:00:00.0',	'2021-04-28 00:00:00.0'),\n"
				+ "	(1004, 'Java testing',	 		2,  'some objctives', 'some main content', 'EXECUTED', '2021-01-01 00:00:00.0',	'2021-02-01 00:00:00.0')\n",
				
				"insert into Session(ID_session, ID_fa, location, duration, sessionStart) values\n"
				+ "	(0, 1000, 'online', 2, '2021-02-03 15:00:00.0'),\n"
				+ "	(1, 1000, 'online', 2, '2021-02-04 15:00:00.0'),\n"
				+ "	(2, 1000, 'online', 2, '2021-02-05 15:00:00.0'),\n"
				+ "	(3, 1001, 'online', 3, '2022-02-10 09:00:00.0'),\n"
				+ "	(4, 1001, 'online', 3, '2022-02-11 09:00:00.0'),\n"
				+ "	(5, 1002, 'online', 3, '2021-04-10 12:00:00.0'),\n"
				+ "	(6, 1002, 'online', 3, '2022-04-10 12:00:00.0'),\n"
				+ "	(7, 1003, 'online', 4, '2021-05-01 10:00:00.0'),\n"
				+ "	(8, 1004, 'online', 1, '2021-03-01 10:00:00.0');\n",

				"insert into Fee(ID_fee, amount, category, ID_fa) values\n"
				+ "	(1000, 15, 'Standard', 1000),\n"
				+ "	(1001, 10, 'Standard', 1001),\n"
				+ "	(1002, 20, 'Standard', 1002),\n"
				+ "	(1003, 30, 'Standard', 1003),\n"
				+ "	(1004, 25, 'College Members', 1003),\n"
				+ "	(1005, 20, 'UniOvi Members', 1003),\n"
				+ "	(1006, 70, 'Standard', 1004);\n",

				"insert into Professional(ID_professional, name, surname, phone, email) values\n"
				+ "	(2000, 'Pablo',	'Gonzales', 		'+341762568901', 	'pablo.gonzales@uniovi.es'),\n"
				+ "	(2001, 'Juan',		'Fernandez', 		'+3413705978356', 	'juan.fernandez@uniovi.es'),\n"
				+ "	(2002, 'Carlo', 	'Perez', 		'+3427889123675', 	'carlo.perez@uniovi.es'),\n"
				+ "	(2003, 'Alvaro', 	'Lopez', 		'+3436789863729', 	'alvaro.lopez@uniovi.es'),\n"
				+ "	(2004, 'Maria', 	'Meier', 		'+491755676536', 	'maria.meier@rub.de'),\n"
				+ "	(2005, 'Paul', 	'Bauer', 		'+4917064578933', 	'paul.bauer@rub.de');\n",

				"insert into Enrollment(ID_fa, ID_professional, status, timeEn, category) values\n"
				+ "	(1000, 2000, 'CONFIRMED', '2021-01-02 16:00:00.0', 'Standard'),\n"
				+ "	(1000, 2001, 'CONFIRMED', '2021-01-04 17:00:00.0', 'Standard'),\n"
				+ "	(1000, 2002, 'CONFIRMED', '2021-01-07 18:00:00.0', 'Standard'),\n"
				+ "	(1000, 2003, 'CONFIRMED', '2021-01-10 19:00:00.0', 'Standard'),\n"
				+ "	(1000, 2004, 'CONFIRMED', '2021-01-13 10:00:00.0', 'Standard'),\n"
				+ "	(1000, 2005, 'CONFIRMED', '2021-01-16 13:00:00.0', 'Standard'),\n"
				+ "	(1001, 2000, 'CONFIRMED', '2021-01-02 12:00:00.0', 'Standard'),\n"
				+ "	(1001, 2001, 'CONFIRMED', '2021-01-13 14:00:00.0', 'Standard'),\n"
				+ "	(1001, 2002, 'CONFIRMED', '2021-01-23 22:00:00.0', 'Standard'),\n"
				+ "	(1001, 2003, 'CONFIRMED', '2021-02-03 20:00:00.0', 'Standard'),\n"
				+ "	(1001, 2004, 'CONFIRMED', '2021-02-01 08:00:00.0', 'Standard'),\n"
				+ "	(1002, 2000, 'CONFIRMED', '2021-03-02 12:00:00.0', 'Standard'),\n"
				+ "	(1002, 2005, 'RECEIVED',  '2021-03-05 08:00:00.0', 'Standard'),\n"
				+ "	(1002, 2001, 'CONFIRMED', '2021-03-05 08:00:00.0', 'Standard');\n",

				"insert into Invoice (Id_invoice , amount, dateIn , sender, receiver, address, fiscalNumber, ID_fa, ID_professional) values\n"
				+ "		   (4000, 15, '2021-01-02', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1000, 2000 ),\n"
				+ "        (4001, 15, '2021-01-04', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1000, 2001 ),\n"
				+ "        (4002, 15, '2021-01-07', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1000, 2002 ),\n"
				+ "        (4003, 15, '2021-01-11', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1000, 2003 ),\n"
				+ "        (4004, 15, '2021-01-14', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1000, 2004 ),\n"
				+ "        (4005, 15, '2021-01-16', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1000, 2005 ),\n"
				+ "        (4006, 10, '2021-01-02', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1001, 2000 ),\n"
				+ "        (4007, 10, '2021-01-13', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1001, 2001 ),\n"
				+ "        (4008, 10, '2021-01-24', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1001, 2002 ),\n"
				+ "        (4009, 10, '2021-02-04', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1001, 2003 ),\n"
				+ "        (4010, 10, '2021-02-01', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1001, 2004 ),\n"
				+ "        (4011, 20, '2021-03-22', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1002, 2000 ),\n"
				+ "        (4012, 20, '2021-03-03', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1002, 2005 ),\n"
				+ "        (4013, 20, '2021-03-05', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1002, 2001 );\n",

				"insert into Payment(ID_payment, amount, datePay, confirmed, ID_invoice) values\n"
				+ "	(3000, 15, '2021-01-02 17:00:00.0', true, 4000),\n"
				+ "	(3001, 15, '2021-01-04 18:00:00.0', true, 4001),\n"
				+ "	(3002, 15, '2021-01-07 20:00:00.0', true, 4002),\n"
				+ "	(3003, 15, '2021-01-11 12:00:00.0', true, 4003),\n"
				+ "	(3004, 15, '2021-01-14 13:00:00.0', true, 4004),\n"
				+ "	(3005, 15, '2021-01-16 14:00:00.0', true, 4005),\n"
				+ "	(3006, 10, '2021-01-02 18:00:00.0', true, 4006),\n"
				+ "	(3007, 5,  '2021-01-13 15:00:00.0', false, 4007),\n"
				+ "	(3008, 10, '2021-01-24 08:00:00.0', true, 4008),\n"
				+ "	(3009, 10, '2021-02-04 10:00:00.0', true, 4009),\n"
				+ "	(3010, 10, '2021-02-01 15:00:00.0', true, 4010),\n"
				+ "	(3011, 30, '2021-03-22 13:00:00.0', false, 4011),\n"
				+ "	(3012, 30, '2021-03-03 13:00:00.0', false, 4012),\n"
				+ "	(3013, 20, '2021-03-05 10:00:00.0', true,  4013)\n",

				"insert into Teacher (ID_teacher, name, surname, phone, email, fiscalNumber) values\n"
				+ "	(1000, \"Jose\", \"Garcia Fanjul\", \"\", \"\", \"23455656H\"),\n"
				+ "	(1001, \"Marcos\", \"Gutierrez Alonso\", \"\", \"\", \"78967856A\"),\n"
				+ "	(1002, \"Marek\", \"Ruschulte\", \"\", \"\", \"45667899D\"),\n"
				+ "	(1003, \"Vivien\", \"Drescher\", \"\", \"\", \"45345678G\"),\n"
				+ "	(1004, \"Silvia\", \"Rodriguez\", \"\", \"\", \"66756789F\"),\n"
				+ "	(1005, \"Rosalina\", \"Alvarez\", \"\", \"\", \"43214565G\");\n",

				"insert into TeacherTeaches (ID_teacher , ID_fa , remuneration) values\n"
				+ "	(1000,1000, 50.0),\n"
				+ "	(1001,1000, 50.0),\n"
				+ "	(1002,1001, 60.0),\n"
				+ "	(1003,1001, 60.0),\n"
				+ "	(1004,1002, 100.0),\n"
				+ "	(1005,1003, 80.0),\n"
				+ "	(1005,1004, 80.0);\n",

				"insert into InvoiceTeacher (Id_invoice , amount, dateIn, sender, receiver, address, fiscalNumber, ID_fa, ID_teacher) values\n"
				+ "\n"
				+ "	('4000', 50, '2021-01-02' , 'COIIPA', 'recieverName', 'address', 'fiscalNumber', 1000, 1000),\n"
				+ "	('4001', 50, '2021-01-02' , 'COIIPA', 'recieverName', 'address', 'fiscalNumber', 1000, 1001),\n"
				+ "	('4002', 60, '2021-01-03 ', 'COIIPA', 'recieverName', 'address', 'fiscalNumber', 1001, 1002),\n"
				+ "	('4003', 60, '2021-01-03 ', 'COIIPA', 'recieverName', 'address', 'fiscalNumber', 1001, 1003),\n"
				+ "	('4004', 100, '2021-01-04 ', 'COIIPA', 'recieverName', 'address', 'fiscalNumber',  1002, 1004),\n"
				+ "	('4005', 80, '2021-05-04 ', 'COIIPA', 'Rosalina',      'address', 'fiscalNumber',  1003, 1005),\n"
				+ "	('4006', 80, '2021-05-04 ', 'COIIPA', 'Rosalina',      'address', 'fiscalNumber',  1004, 1005);\n",

				"insert into PaymentTeacher( ID_payment, amount, datePay,  confirmed, ID_invoice) values\n"
				+ "	(3000, 50, '2021-01-02 00:00:00.0 ', true, '4000'),\n"
				+ "	(3001, 50, '2021-01-04 00:00:00.0 ', true, '4001'),\n"
				+ "	(3002, 70, '2021-01-07 00:00:00.0 ', false, '4002'),\n"
				+ "	(3003, 120, '2021-05-04 00:00:00.0 ', true, '4004'),\n"
				+ "	(3004, 70, '2021-05-04 00:00:00.0 ', true, '4005');"
		});
	}
	
	@Test
	public void testCloseActiveAllCorrect() {
		Model m = new Model();
		List<String> list = m.getListFormativeAction();
		try {
			// Extra test if all selected FormativeActions are Active or Delayed
			for (String string : list) {
				assertTrue(
						FormativeAction.getOne("select * from FormativeAction where nameFa = '" + string + "'", db).getStatus().toString().equals("ACTIVE") || 
						FormativeAction.getOne("select * from FormativeAction where nameFa = '" + string + "'", db).getStatus().toString().equals("DELAYED")
						);
			}
			
			// Get the formative action Initiation to scrum because its requirements correspond to this test
			FormativeAction fa = FormativeAction.getOne("select * from FormativeAction where nameFa = 'Initiation to scrum'", db);
			assertEquals("ACTIVE", fa.getStatus().toString());

			// Test if all payments from professionals are correct
			// In this case equal to the expected
			int pIncomeConfirmed = m.getSumIncomeConfirmedProfessional(fa.getName());
			int pIncomeEstimated = m.getSumIncomeExpectedProfessional(fa.getName());
			assertEquals(pIncomeConfirmed, pIncomeEstimated);
			
			// Test if all payments to Teacher are correct
			// In this case equal to the expected
			int tPaymentConfirmed = m.getPaymentTeacherConfirmed(fa.getName());
			int tPaymentEstimated = m.getPaymentTeacherExpected(fa.getName());
			assertEquals(tPaymentConfirmed, tPaymentEstimated);
			
			// Test that all session are celebrated
			Date date = m.getDateOfLastSession(fa.getName());
			assertTrue(date.before(new Date()));
			
			//Close formative action and check if status of the corresponding formative action is changed in database
			m.closeFormativeAction(fa.getName());
			assertTrue(FormativeAction.getOne("select * from FormativeAction where nameFa = '" + fa.getName() + "'", db).getStatus().toString().equals("EXECUTED"));
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testDelayedToFewToFew() {
		Model m = new Model();
		try {
			// Get the formative action Initiation to agile because its requirements correspond to this test
			FormativeAction fa = FormativeAction.getOne("select * from FormativeAction where nameFa = 'Initiation to agile'", db);
			assertEquals("DELAYED", fa.getStatus().toString());

			// TEst if all payments from professionals are correct
			int pIncomeConfirmed = m.getSumIncomeConfirmedProfessional(fa.getName());
			int pIncomeEstimated = m.getSumIncomeExpectedProfessional(fa.getName());
			assertTrue(pIncomeConfirmed == 45);
			assertTrue(pIncomeConfirmed < pIncomeEstimated);
			
			// Test if all payments to Teacher are correct
			int tPaymentConfirmed = m.getPaymentTeacherConfirmed(fa.getName());
			int tPaymentEstimated = m.getPaymentTeacherExpected(fa.getName());
			assertTrue(tPaymentConfirmed == 0);
			assertTrue(tPaymentConfirmed < tPaymentEstimated);
			
			//Test if not sessions are celebrated
			Date date = m.getDateOfLastSession(fa.getName());
			assertTrue(date.after(new Date()));
			
			//Close formative action and check if status of the corresponding formative action is changed in database
			m.closeFormativeAction(fa.getName());
			assertTrue(FormativeAction.getOne("select * from FormativeAction where nameFa = '" + fa.getName() + "'", db).getStatus().toString().equals("EXECUTED"));
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidToHighToHigh() {
		Model m = new Model();
		try {			
			// Get the formative action JDBC because its requirements correspond to this test
			FormativeAction fa = FormativeAction.getOne("select * from FormativeAction where nameFa = 'JDBC'", db);
			assertEquals("ACTIVE", fa.getStatus().toString());

			// Test if all payments from professionals are correct
			// In this case 80€ therefore more than expected through enrollments and fees 
			int pIncomeConfirmed = m.getSumIncomeConfirmedProfessional(fa.getName());
			int pIncomeEstimated = m.getSumIncomeExpectedProfessional(fa.getName());
			assertTrue(pIncomeConfirmed == 80);
			assertTrue(pIncomeConfirmed > pIncomeEstimated);
			
			// Test if all payments to Teacher are correct
			// In this case the Teacher should have got to more than the remuneration
			int tPaymentConfirmed = m.getPaymentTeacherConfirmed(fa.getName());
			int tPaymentEstimated = m.getPaymentTeacherExpected(fa.getName());
			assertTrue(tPaymentConfirmed > tPaymentEstimated);
			
			//Test if not sessions are celebrated
			Date date = m.getDateOfLastSession(fa.getName());
			assertTrue(date.after(new Date()));
			
			//Close formative action and check if status of the corresponding formative action is changed in database
			m.closeFormativeAction(fa.getName());
			assertTrue(FormativeAction.getOne("select * from FormativeAction where nameFa = '" + fa.getName() + "'", db).getStatus().toString().equals("EXECUTED"));
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
	}
}
