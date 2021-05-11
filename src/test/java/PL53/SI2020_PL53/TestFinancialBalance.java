package PL53.SI2020_PL53;

import org.junit.*;
import static org.junit.Assert.assertEquals;

import Utils.Database;

import CheckFinancialBalance.*;
import PL53.util.Date;
import PL53.util.DateTime;

import java.util.List;

/**
 * Testing of the financial balance check with JUnit4
 */
public class TestFinancialBalance {
	private static Database db=new Database();
	@Before
	public void setUp() {
		db.createDatabase(true);
		loadCleanDatabase(db); 
	}
	@After
	public void tearDown(){
	}
	
	public static void loadCleanDatabase(Database db) {
		db.executeBatch(new String[] {
				// Delete the teachers table and insert new data for the equivalence classes 
				"delete from teacher",
				"insert into teacher (ID_teacher, name, surname, phone, email, fiscalNumber) values (1000, 'Jose', 'Garcia Fanjul', '', '' , '23455656H')",
				"insert into teacher (ID_teacher, name, surname, phone, email, fiscalNumber) values (1001, 'Marcos', 'Gutierrez Alonso', '', '' , '78967856A')",
				"insert into teacher (ID_teacher, name, surname, phone, email, fiscalNumber) values (1002, 'Marek', 'Ruschulte', '', '' , '45667899D')",
				"insert into teacher (ID_teacher, name, surname, phone, email, fiscalNumber) values (1003, 'Vivien', 'Drescher', '', '' , '45345678G')",
				"insert into teacher (ID_teacher, name, surname, phone, email, fiscalNumber) values (1004, 'Silvia', 'Rodriguez', '', '' , '66756789F')",
				"insert into teacher (ID_teacher, name, surname, phone, email, fiscalNumber) values (1005, 'Rosalina', 'Alvarez', '', '' , '43214565G')",
				// Delete the formative action table and insert new data for the equivalence classes 
				"delete from formativeAction",
				"insert into formativeAction (ID_fa, nameFa, totalPlaces, objectives, mainContent, status, enrollmentStart, enrollmentEnd) values (1000, 'Initiation to scrum',	 6,	'some objctives', 'some main content', 'EXECUTED', '2021-01-01 00:00:00.0',	'2021-02-01 00:00:00.0')",
				"insert into formativeAction (ID_fa, nameFa, totalPlaces, objectives, mainContent, status, enrollmentStart, enrollmentEnd) values (1001, 'Initiation to agile',	10, 	'some objctives', 'some main content', 'ACTIVE', '2021-01-01 00:00:00.0',	'2021-05-08 00:00:00.0')",
				"insert into formativeAction (ID_fa, nameFa, totalPlaces, objectives, mainContent, status, enrollmentStart, enrollmentEnd) values (1002, 'JDBC',  		 4,  	'some objctives', 'some main content', 'CANCELLED',   '2021-03-01 00:00:00.0',	'2021-04-08 00:00:00.0')",
				"insert into formativeAction (ID_fa, nameFa, totalPlaces, objectives, mainContent, status, enrollmentStart, enrollmentEnd) values (1003, 'Java programming',	 5,	'some objctives', 'some main content', 'CANCELLED',   '2021-04-01 00:00:00.0',	'2021-04-28 00:00:00.0')",
				// Delete the fee table and insert new data for the equivalence classes 
				"delete from fee",
				"insert into fee (ID_fee, amount, category, ID_fa) values (1000, 30, 'Standard', 1000)",
				"insert into fee (ID_fee, amount, category, ID_fa) values (1001, 20, 'College Members', 1000)",
				"insert into fee (ID_fee, amount, category, ID_fa) values (1002, 30, 'Standard', 1001)",
				"insert into fee (ID_fee, amount, category, ID_fa) values (1003, 20, 'College Members', 1001)",
				"insert into fee (ID_fee, amount, category, ID_fa) values (1004, 30, 'Standard', 1002)",
				"insert into fee (ID_fee, amount, category, ID_fa) values (1005, 20, 'College Members', 1002)",
				"insert into fee (ID_fee, amount, category, ID_fa) values (1006, 30, 'Standard', 1003)",
				"insert into fee (ID_fee, amount, category, ID_fa) values (1007, 20, 'College Members', 1003)",
				// Delete the session table and insert new data for the equivalence classes 
				"delete from session",
				"insert into session (ID_session, ID_fa, location, duration, sessionStart) values (0, 1000, 'online', 2, '2021-02-03 16:00:00.0')",
				"insert into session (ID_session, ID_fa, location, duration, sessionStart) values (1, 1000, 'online', 2, '2021-02-04 17:00:00.0')",
				"insert into session (ID_session, ID_fa, location, duration, sessionStart) values (2, 1000, 'online', 2, '2021-02-05 18:00:00.0')",
				"insert into session (ID_session, ID_fa, location, duration, sessionStart) values (3, 1001, 'online', 3, '2021-05-10 14:00:00.0')",
				"insert into session (ID_session, ID_fa, location, duration, sessionStart) values (4, 1001, 'online', 3, '2021-05-11 14:00:00.0')",
				"insert into session (ID_session, ID_fa, location, duration, sessionStart) values (5, 1002, 'online', 3, '2021-04-10 16:00:00.0')",
				"insert into session (ID_session, ID_fa, location, duration, sessionStart) values (6, 1003, 'online', 4, '2021-05-01 09:00:00.0')",
				// Delete the teacher teaches table and insert new data for the equivalence classes 
				"delete from teacherTeaches",
				"insert into teacherTeaches (ID_teacher , ID_fa , remuneration) values (1000,1000, 50.0)",
				"insert into teacherTeaches (ID_teacher , ID_fa , remuneration) values (1001,1000, 50.0)",
				"insert into teacherTeaches (ID_teacher , ID_fa , remuneration) values (1002,1001, 50.0)",
				"insert into teacherTeaches (ID_teacher , ID_fa , remuneration) values (1003,1001, 50.0)",
				"insert into teacherTeaches (ID_teacher , ID_fa , remuneration) values (1004,1002, 50.0)",
				"insert into teacherTeaches (ID_teacher , ID_fa , remuneration) values (1005,1002, 50.0)",
				"insert into teacherTeaches (ID_teacher , ID_fa , remuneration) values (1000,1003, 50.0)",
				"insert into teacherTeaches (ID_teacher , ID_fa , remuneration) values (1001,1003, 50.0)",
				// Delete the professional table and insert new data for the equivalence classes 
				"delete from professional",
				"insert into professional (ID_professional, name, surname, phone, email) values (2000, 'Pablo', 'Gonzales', '+341762568901', 'pablo.gonzales@uniovi.es')",
				"insert into professional (ID_professional, name, surname, phone, email) values (2001, 'Juan', 'Fernandez', '+3413705978356', 'juan.fernandez@uniovi.es')",
				"insert into professional (ID_professional, name, surname, phone, email) values (2002, 'Carlo', 'Perez', '+3427889123675', 'carlo.perez@uniovi.es')",
				"insert into professional (ID_professional, name, surname, phone, email) values (2003, 'Alvaro', 'Lopez', '+3436789863729', 'alvaro.lopez@uniovi.es')",
				// Delete the enrollment table and insert new data for the equivalence classes 
				"delete from enrollment",
				"insert into enrollment (ID_fa, ID_professional, status, timeEn, category) values (1000, 2000, 'CONFIRMED', '2021-01-02 16:00:00.0', 'Standard')",
				"insert into enrollment (ID_fa, ID_professional, status, timeEn, category) values (1000, 2001, 'CANCELLED', '2021-01-03 17:00:00.0', 'College Members')",
				"insert into enrollment (ID_fa, ID_professional, status, timeEn, category) values (1000, 2002, 'CANCELLED', '2021-01-04 18:00:00.0', 'College Members')",
				"insert into enrollment (ID_fa, ID_professional, status, timeEn, category) values (1000, 2003, 'CANCELLED', '2021-01-05 22:00:00.0', 'College Members')",
				"insert into enrollment (ID_fa, ID_professional, status, timeEn, category) values (1001, 2001, 'RECEIVED', '2021-01-10 16:00:00.0', 'Standard')",
				"insert into enrollment (ID_fa, ID_professional, status, timeEn, category) values (1001, 2002, 'RECEIVED', '2021-01-12 10:00:00.0', 'College Members')",
				"insert into enrollment (ID_fa, ID_professional, status, timeEn, category) values (1002, 2000, 'CONFIRMED', '2021-03-02 16:00:00.0', 'Standard')",
				"insert into enrollment (ID_fa, ID_professional, status, timeEn, category) values (1002, 2003, 'CONFIRMED', '2021-03-03 16:00:00.0', 'College Members')",
				"insert into enrollment (ID_fa, ID_professional, status, timeEn, category) values (1003, 2000, 'RECEIVED', '2021-04-12 10:00:00.0', 'Standard')",
				"insert into enrollment (ID_fa, ID_professional, status, timeEn, category) values (1003, 2002, 'RECEIVED', '2021-04-13 10:00:00.0', 'College Members')",
				// Delete the invoice table and insert new data for the equivalence classes 
				"delete from invoice",
				"insert into invoice (Id_invoice , amount, dateIn , sender, receiver, address, fiscalNumber, ID_fa, ID_professional) values (4001, 30, '2021-01-02', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1000, 2001 )",
				"insert into invoice (Id_invoice , amount, dateIn , sender, receiver, address, fiscalNumber, ID_fa, ID_professional) values (4002, 20, '2021-01-03', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1000, 2001 )",
				"insert into invoice (Id_invoice , amount, dateIn , sender, receiver, address, fiscalNumber, ID_fa, ID_professional) values (4003, 20, '2021-01-04', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1000, 2002 )",
				"insert into invoice (Id_invoice , amount, dateIn , sender, receiver, address, fiscalNumber, ID_fa, ID_professional) values (4004, 20, '2021-01-05', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1000, 2003 )",
				"insert into invoice (Id_invoice , amount, dateIn , sender, receiver, address, fiscalNumber, ID_fa, ID_professional) values (4005, 30, '2021-01-10', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1001, 2001 )",
				"insert into invoice (Id_invoice , amount, dateIn , sender, receiver, address, fiscalNumber, ID_fa, ID_professional) values (4006, 20, '2021-01-12', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1001, 2002 )",
				"insert into invoice (Id_invoice , amount, dateIn , sender, receiver, address, fiscalNumber, ID_fa, ID_professional) values (4007, 30, '2021-03-02', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1002, 2000 )",
				"insert into invoice (Id_invoice , amount, dateIn , sender, receiver, address, fiscalNumber, ID_fa, ID_professional) values (4008, 20, '2021-03-03', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1002, 2003 )",
				"insert into invoice (Id_invoice , amount, dateIn , sender, receiver, address, fiscalNumber, ID_fa, ID_professional) values (4009, 30, '2021-04-12', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1003, 2000 )",
				"insert into invoice (Id_invoice , amount, dateIn , sender, receiver, address, fiscalNumber, ID_fa, ID_professional) values (4010, 20, '2021-04-13', 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1003, 2002 )",
				"insert into invoice (Id_invoice , amount, dateIn , sender, receiver, address, fiscalNumber, ID_fa, ID_professional) values (4011, 20, '2021-01-15', 'COIIPA', 'receiverName', 'address', 'fiscalNumber', 1000, 2001 )",
				"insert into invoice (Id_invoice , amount, dateIn , sender, receiver, address, fiscalNumber, ID_fa, ID_professional) values (4012, 10, '2021-01-31', 'COIIPA', 'receiverName', 'address', 'fiscalNumber', 1000, 2002 )",
				"insert into invoice (Id_invoice , amount, dateIn , sender, receiver, address, fiscalNumber, ID_fa, ID_professional) values (4013, 0, '2021-02-02', 'COIIPA', 'receiverName', 'address', 'fiscalNumber', 1000, 2003)",
				"insert into invoice (Id_invoice , amount, dateIn , sender, receiver, address, fiscalNumber, ID_fa, ID_professional) values (4014, 30, '2021-03-25', 'COIIPA', 'receiverName', 'address', 'fiscalNumber', 1002, 2000)",
				"insert into invoice (Id_invoice , amount, dateIn , sender, receiver, address, fiscalNumber, ID_fa, ID_professional) values (4015, 20, '2021-03-25', 'COIIPA', 'receiverName', 'address', 'fiscalNumber', 1002, 2003)",
				"insert into invoice (Id_invoice , amount, dateIn , sender, receiver, address, fiscalNumber, ID_fa, ID_professional) values (4016, 30, '2021-04-15', 'COIIPA', 'receiverName', 'address', 'fiscalNumber', 1003, 2000)",
				"insert into invoice (Id_invoice , amount, dateIn , sender, receiver, address, fiscalNumber, ID_fa, ID_professional) values (4017, 20, '2021-04-15', 'COIIPA', 'receiverName', 'address', 'fiscalNumber', 1003, 2002)",
				// Delete the payment table and insert new data for the equivalence classes 
				"delete from payment",
				"insert into payment (ID_payment, amount, datePay, confirmed, ID_invoice) values (3000, 30, '2021-01-02 17:00:00.0', true, 4001)",
				"insert into payment (ID_payment, amount, datePay, confirmed, ID_invoice) values (3001, 20, '2021-01-03 17:00:00.0', true, 4002)",
				"insert into payment (ID_payment, amount, datePay, confirmed, ID_invoice) values (3002, 20, '2021-01-04 17:00:00.0', true, 4003)",
				"insert into payment (ID_payment, amount, datePay, confirmed, ID_invoice) values (3003, 20, '2021-01-05 17:00:00.0', true, 4004)",
				"insert into payment (ID_payment, amount, datePay, confirmed, ID_invoice) values (3004, 35, '2021-03-02 17:00:00.0', true, 4007)",
				"insert into payment (ID_payment, amount, datePay, confirmed, ID_invoice) values (3005, 25, '2021-03-03 17:00:00.0', true, 4008)",
				"insert into payment (ID_payment, amount, datePay, confirmed, ID_invoice) values (3006, 10, '2021-04-12 17:00:00.0', false, 4009)",
				"insert into payment (ID_payment, amount, datePay, confirmed, ID_invoice) values (3007, 10, '2021-04-13 17:00:00.0', false, 4010)",
				"insert into payment (ID_payment, amount, datePay, confirmed, ID_invoice) values (3008, 20, '2021-01-15 17:00:00.0', true, 4011)",
				"insert into payment (ID_payment, amount, datePay, confirmed, ID_invoice) values (3009, 10, '2021-01-31 17:00:00.0', true, 4012)",
				"insert into payment (ID_payment, amount, datePay, confirmed, ID_invoice) values (3010, 0, '2021-02-02 17:00:00.0', true, 4013)",
				"insert into payment (ID_payment, amount, datePay, confirmed, ID_invoice) values (3011, 35, '2021-03-25 17:00:00.0', true, 4014)",
				"insert into payment (ID_payment, amount, datePay, confirmed, ID_invoice) values (3012, 25, '2021-03-25 17:00:00.0', true, 4015)",
				// Delete the invoice teacher table and insert new data for the equivalence classes 
				"delete from invoiceTeacher",
				"insert into invoiceTeacher (Id_invoice , amount, dateIn, sender, receiver, address, fiscalNumber, ID_fa, ID_teacher) values('4000', 50, '2021-01-02' , 'COIIPA', 'recieverName', 'address', 'fiscalNumber', 1000, 1000)",
				"insert into invoiceTeacher (Id_invoice , amount, dateIn, sender, receiver, address, fiscalNumber, ID_fa, ID_teacher) values('4001', 50, '2021-01-02' , 'COIIPA', 'recieverName', 'address', 'fiscalNumber', 1000, 1001)",
				"insert into invoiceTeacher (Id_invoice , amount, dateIn, sender, receiver, address, fiscalNumber, ID_fa, ID_teacher) values('4002', 50, '2021-02-02' , 'COIIPA', 'recieverName', 'address', 'fiscalNumber', 1001, 1002)",
				"insert into invoiceTeacher (Id_invoice , amount, dateIn, sender, receiver, address, fiscalNumber, ID_fa, ID_teacher) values('4003', 50, '2021-02-02' , 'COIIPA', 'recieverName', 'address', 'fiscalNumber', 1001, 1003)",
				"insert into invoiceTeacher (Id_invoice , amount, dateIn, sender, receiver, address, fiscalNumber, ID_fa, ID_teacher) values('4004', 50, '2021-03-02' , 'COIIPA', 'recieverName', 'address', 'fiscalNumber', 1002, 1004)",
				"insert into invoiceTeacher (Id_invoice , amount, dateIn, sender, receiver, address, fiscalNumber, ID_fa, ID_teacher) values('4005', 50, '2021-03-02' , 'COIIPA', 'recieverName', 'address', 'fiscalNumber', 1002, 1005)",
				"insert into invoiceTeacher (Id_invoice , amount, dateIn, sender, receiver, address, fiscalNumber, ID_fa, ID_teacher) values('4006', 50, '2021-04-02' , 'COIIPA', 'recieverName', 'address', 'fiscalNumber', 1003, 1000)",
				"insert into invoiceTeacher (Id_invoice , amount, dateIn, sender, receiver, address, fiscalNumber, ID_fa, ID_teacher) values('4007', 50, '2021-04-02' , 'COIIPA', 'recieverName', 'address', 'fiscalNumber', 1003, 1001)",
				"insert into invoiceTeacher (Id_invoice , amount, dateIn, sender, receiver, address, fiscalNumber, ID_fa, ID_teacher) values('4008', 50, '2021-03-27' , 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1002, 1004)",
				"insert into invoiceTeacher (Id_invoice , amount, dateIn, sender, receiver, address, fiscalNumber, ID_fa, ID_teacher) values('4009', 50, '2021-03-27' , 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1002, 1005)",
				"insert into invoiceTeacher (Id_invoice , amount, dateIn, sender, receiver, address, fiscalNumber, ID_fa, ID_teacher) values('4010', 50, '2021-04-17' , 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1003, 1000)",
				"insert into invoiceTeacher (Id_invoice , amount, dateIn, sender, receiver, address, fiscalNumber, ID_fa, ID_teacher) values('4011', 50, '2021-04-17' , 'senderName', 'COIIPA', 'address', 'fiscalNumber', 1003, 1001)",
				// Delete the payment teacher table and insert new data for the equivalence classes 
				"delete from paymentTeacher",
				"insert into paymentTeacher (ID_payment, amount, datePay,  confirmed, ID_invoice) values (3000, 50, '2021-01-02 00:00:00.0 ', true, '4000')",
				"insert into paymentTeacher (ID_payment, amount, datePay,  confirmed, ID_invoice) values (3001, 50, '2021-01-02 00:00:00.0 ', true, '4001')",
				"insert into paymentTeacher (ID_payment, amount, datePay,  confirmed, ID_invoice) values (3002, 55, '2021-02-02 00:00:00.0 ', true, '4002')",
				"insert into paymentTeacher (ID_payment, amount, datePay,  confirmed, ID_invoice) values (3003, 55, '2021-02-02 00:00:00.0 ', true, '4003')",
				"insert into paymentTeacher (ID_payment, amount, datePay,  confirmed, ID_invoice) values (3004, 30, '2021-02-02 00:00:00.0 ', true, '4006')",
				"insert into paymentTeacher (ID_payment, amount, datePay,  confirmed, ID_invoice) values (3005, 30, '2021-02-02 00:00:00.0 ', true, '4007')",
				"insert into paymentTeacher (ID_payment, amount, datePay,  confirmed, ID_invoice) values (3006, 0, '2021-04-02 00:00:00.0 ', true, '4008')",
				"insert into paymentTeacher (ID_payment, amount, datePay,  confirmed, ID_invoice) values (3007, 0, '2021-03-27 00:00:00.0 ', true, '4009')",
			});
	}
	
	
	///////////////////////////////////////// Test case 1 (no status filter)////////////////////////////////////////////
	/**
	 * Compute the financial balances and total balance for all the formative actions that are listed in the database 
	 * that have at least one session in the current year 
	 * Covers test case 1 
	 * With the setUp database, it covers all the equivalence classes regarding the database (1, 2, 3, 4).
	 */
	@Test
	public void testFinancialBalances() {
		// Initialize the model to check financial balances 
		CheckFinancialBalance.Model model=new CheckFinancialBalance.Model();
		
		// Financial balances per formative action 
		// Get the list of financial balances for the default filter configuration (within the current year, no status filter)
		List<FinancialBalance> fB=model.getListFinancialBalance(new Date(1,1,2021), new Date(31,12,2021), null);
		// Check if the resulting lists contains 4 financial balance elements like expected
		assertEquals("the number of financial balances is incorrect",4,fB.size());
		
		// Check if results of the first formative action match the expected values
		assertEquals("Initiation to scrum", fB.get(0).getName());
		assertEquals("EXECUTED", fB.get(0).getStatus());
		assertEquals(new DateTime(0, 16, 3, 2, 2021).toString(), fB.get(0).getFirstSession().toString()); 
		assertEquals(new DateTime(0, 18, 5, 2, 2021).toString(), fB.get(0).getLastSession().toString());
		assertEquals("60.0", Float.toString(fB.get(0).getIncomeEstimated()));
		assertEquals("100.0", Float.toString(fB.get(0).getExpensesEstimated()));
		assertEquals("-40.0", Float.toString(fB.get(0).getBalanceEstimated()));
		assertEquals("60.0", Float.toString(fB.get(0).getIncomeConfirmed()));
		assertEquals("100.0", Float.toString(fB.get(0).getExpensesConfirmed()));
		assertEquals("-40.0", Float.toString(fB.get(0).getBalanceConfirmed()));
		
		// Check if results of the second formative action match the expected values
		assertEquals("Initiation to agile", fB.get(1).getName());
		assertEquals("ACTIVE", fB.get(1).getStatus());
		assertEquals(new DateTime(0, 13, 10, 5, 2021).toString(), fB.get(1).getFirstSession().toString()); 
 		assertEquals(new DateTime(0, 13, 11, 5, 2021).toString(), fB.get(1).getLastSession().toString()); 
		assertEquals("50.0", Float.toString(fB.get(1).getIncomeEstimated()));
		assertEquals("100.0", Float.toString(fB.get(1).getExpensesEstimated()));
		assertEquals("-50.0", Float.toString(fB.get(1).getBalanceEstimated()));
 		assertEquals("0.0", Float.toString(fB.get(1).getIncomeConfirmed()));
		assertEquals("110.0", Float.toString(fB.get(1).getExpensesConfirmed()));
		assertEquals("-110.0", Float.toString(fB.get(1).getBalanceConfirmed()));
		
		// Check if results of the third formative action match the expected values
		assertEquals("JDBC", fB.get(2).getName());
		assertEquals("CANCELLED", fB.get(2).getStatus());
		assertEquals(new DateTime(0, 15, 10, 4, 2021).toString(), fB.get(2).getFirstSession().toString()); 
		assertEquals(new DateTime(0, 15, 10, 4, 2021).toString(), fB.get(2).getLastSession().toString()); 
		assertEquals("0.0", Float.toString(fB.get(2).getIncomeEstimated()));
		assertEquals("0.0", Float.toString(fB.get(2).getExpensesEstimated()));
		assertEquals("0.0", Float.toString(fB.get(2).getBalanceEstimated()));
		assertEquals("0.0", Float.toString(fB.get(2).getIncomeConfirmed()));
		assertEquals("0.0", Float.toString(fB.get(2).getExpensesConfirmed()));
		assertEquals("0.0", Float.toString(fB.get(2).getBalanceConfirmed()));
		
		// Check if results of the fourth formative action match the expected values
		assertEquals("Java programming", fB.get(3).getName());
		assertEquals("CANCELLED", fB.get(3).getStatus());
		assertEquals(new DateTime(0, 8, 1, 5, 2021).toString(), fB.get(3).getFirstSession().toString());
		assertEquals(new DateTime(0, 8, 1, 5, 2021).toString(), fB.get(3).getLastSession().toString());
		assertEquals("0.0", Float.toString(fB.get(3).getIncomeEstimated()));
		assertEquals("0.0", Float.toString(fB.get(3).getExpensesEstimated()));
		assertEquals("0.0", Float.toString(fB.get(3).getBalanceEstimated()));
		assertEquals("20.0", Float.toString(fB.get(3).getIncomeConfirmed()));
		assertEquals("60.0", Float.toString(fB.get(3).getExpensesConfirmed()));
		assertEquals("-40.0", Float.toString(fB.get(3).getBalanceConfirmed()));
		
		// Total Balance 
		// Get the total financial balance over all formative actions 
		List<TotalBalance> tB=model.getListTotalBalance(new Date(1,1,2021), new Date(31,12,2021), null);
		
		// Check if the resulting lists contains 4 financial balance elements like expected
		assertEquals("the number of total balances is incorrect",1,tB.size());
		
		// Check if the results for the total balance match the expected values
		assertEquals("110.0", Float.toString(tB.get(0).getTotalIncomeEstimated()));
		assertEquals("200.0", Float.toString(tB.get(0).getTotalExpensesEstimated()));
		assertEquals("80.0", Float.toString(tB.get(0).getTotalIncomeConfirmed()));
		assertEquals("270.0", Float.toString(tB.get(0).getTotalExpensesConfirmed()));
	}	
		
	///////////////////////////////////////// Test case 2 - Filter status = Active ////////////////////////////////////////
	/**
	 * Compute the financial balances and total balance for all the formative actions that are listed in the database 
	 * that have at least one session in the current year and have status "ACTIVE"
	 * Covers test case 2 
	 * With the setUp database, it covers all the equivalence classes regarding the database (1, 2, 3, 4).
	 */
	@Test
	public void testFinancialBalancesStatusActive() {
		// Initialize the model to check financial balances 
		CheckFinancialBalance.Model model=new CheckFinancialBalance.Model();
		
		// Financial Balance per formative action 
		// Get the list of financial balances within the current year that have the status "ACTIVE"
		List<FinancialBalance> fB=model.getListFinancialBalance(new Date(1,1,2021), new Date(31,12,2021), "ACTIVE");
		// Check if the resulting lists contains 1 financial balance elements like expected
		assertEquals("the number of financial balances is incorrect", 1, fB.size());
		
		// Check if results of the formative action match the expected values
		assertEquals("Initiation to agile", fB.get(0).getName());
		assertEquals("ACTIVE", fB.get(0).getStatus());
		assertEquals(new DateTime(0, 13, 10, 5, 2021).toString(), fB.get(0).getFirstSession().toString()); 
 		assertEquals(new DateTime(0, 13, 11, 5, 2021).toString(), fB.get(0).getLastSession().toString());
		assertEquals("50.0", Float.toString(fB.get(0).getIncomeEstimated()));
		assertEquals("100.0", Float.toString(fB.get(0).getExpensesEstimated()));
		assertEquals("-50.0", Float.toString(fB.get(0).getBalanceEstimated()));
		assertEquals("0.0", Float.toString(fB.get(0).getIncomeConfirmed()));
		assertEquals("110.0", Float.toString(fB.get(0).getExpensesConfirmed()));
		assertEquals("-110.0", Float.toString(fB.get(0).getBalanceConfirmed()));
		
		// Total balance
		// Get the total financial balance over all formative actions that have the status "ACTIVE"
		List<TotalBalance> tB=model.getListTotalBalance(new Date(1,1,2021), new Date(31,12,2021), "ACTIVE");
		
		// Check if the resulting lists contains 1 total balance element like expected
		assertEquals("the number of total balances is incorrect",1,tB.size());
		
		// Check if the results for the total balance match the expected values
		assertEquals("50.0", Float.toString(tB.get(0).getTotalIncomeEstimated()));
		assertEquals("100.0", Float.toString(tB.get(0).getTotalExpensesEstimated()));
		assertEquals("0.0", Float.toString(tB.get(0).getTotalIncomeConfirmed()));
		assertEquals("110.0", Float.toString(tB.get(0).getTotalExpensesConfirmed()));
	}	
	

	///////////////////////////////////////// Test case 3 - Filter status = Executed //////////////////////////////////////
	/**
	 * Compute the financial balances and total balance for all the formative actions that are listed in the database 
	 * that have at least one session in the current year and have status "EXECUTED"
	 * Covers test case 3
	 * With the setUp database, it covers all the equivalence classes regarding the database (1, 2, 3, 4).
	 */
	@Test
	public void testFinancialBalancesStatusExecuted() {
		// Initialize the model to check financial balances 
		CheckFinancialBalance.Model model=new CheckFinancialBalance.Model();
		
		// Financial balances per formative action 
		// Get the list of financial balances within the current year that have the status "EXECUTED"
		List<FinancialBalance> fB=model.getListFinancialBalance(new Date(1,1,2021), new Date(31,12,2021), "EXECUTED");
		// Check if the resulting lists contains 1 financial balance element like expected
		assertEquals("the number of financial balances is incorrect", 1, fB.size());
		
		// Check if results of the formative action match the expected values
		assertEquals("Initiation to scrum", fB.get(0).getName());
		assertEquals("EXECUTED", fB.get(0).getStatus());
		assertEquals(new DateTime(0, 16, 3, 2, 2021).toString(), fB.get(0).getFirstSession().toString()); 
		assertEquals(new DateTime(0, 18, 5, 2, 2021).toString(), fB.get(0).getLastSession().toString());
		assertEquals("60.0", Float.toString(fB.get(0).getIncomeEstimated()));
		assertEquals("100.0", Float.toString(fB.get(0).getExpensesEstimated()));
		assertEquals("-40.0", Float.toString(fB.get(0).getBalanceEstimated()));
		assertEquals("60.0", Float.toString(fB.get(0).getIncomeConfirmed()));
		assertEquals("100.0", Float.toString(fB.get(0).getExpensesConfirmed()));
		assertEquals("-40.0", Float.toString(fB.get(0).getBalanceConfirmed()));
		
		// Get the total financial balance over all formative actions that have the status "EXECUTED"
		List<TotalBalance> tB=model.getListTotalBalance(new Date(1,1,2021), new Date(31,12,2021), "EXECUTED");
		
		// Check if the resulting lists contains 1 total balance element like expected
		assertEquals("the number of total balances is incorrect",1,tB.size());
		
		// Check if the results for the total balance match the expected values
		assertEquals("60.0", Float.toString(tB.get(0).getTotalIncomeEstimated()));
		assertEquals("100.0", Float.toString(tB.get(0).getTotalExpensesEstimated()));
		assertEquals("60.0", Float.toString(tB.get(0).getTotalIncomeConfirmed()));
		assertEquals("100.0", Float.toString(tB.get(0).getTotalExpensesConfirmed()));
	}	
		
	//////////////////////////////////////// Test case 4 - Filter status = Cancelled ///////////////////////////////////////
	/**
	 * Compute the financial balances and total balance for all the formative actions that are listed in the database 
	 * that have at least one session in the current year and have status "CANCELLED"
	 * Covers test case 34
	 * With the setUp database, it covers all the equivalence classes regarding the database (1, 2, 3, 4).
	 */
	@Test
	public void testFinancialBalancesStatusCancelled() {
		// Initialize the model to check financial balances 
		CheckFinancialBalance.Model model=new CheckFinancialBalance.Model();
		
		// Financial balances per formative action 
		// Get the list of financial balances within the current year that have the status "CANCELLED"
		List<FinancialBalance> fB=model.getListFinancialBalance(new Date(1,1,2021), new Date(31,12,2021), "CANCELLED");
		// Check if the resulting lists contains 2 financial balance elements like expected
		assertEquals("the number of financial balances is incorrect", 2, fB.size());
		
		// Check if results of the first formative action match the expected values
		assertEquals("JDBC", fB.get(0).getName());
		assertEquals("CANCELLED", fB.get(0).getStatus());
		assertEquals(new DateTime(0, 15, 10, 4, 2021).toString(), fB.get(0).getFirstSession().toString()); 
		assertEquals(new DateTime(0, 15, 10, 4, 2021).toString(), fB.get(0).getLastSession().toString()); 
		assertEquals("0.0", Float.toString(fB.get(0).getIncomeEstimated()));
		assertEquals("0.0", Float.toString(fB.get(0).getExpensesEstimated()));
		assertEquals("0.0", Float.toString(fB.get(0).getBalanceEstimated()));
		assertEquals("0.0", Float.toString(fB.get(0).getIncomeConfirmed()));
		assertEquals("0.0", Float.toString(fB.get(0).getExpensesConfirmed()));
		assertEquals("0.0", Float.toString(fB.get(0).getBalanceConfirmed()));
		
		// Check if results of the second formative action match the expected values
		assertEquals("Java programming", fB.get(1).getName());
		assertEquals("CANCELLED", fB.get(1).getStatus());
		assertEquals(new DateTime(0, 8, 1, 5, 2021).toString(), fB.get(1).getFirstSession().toString());
		assertEquals(new DateTime(0, 8, 1, 5, 2021).toString(), fB.get(1).getLastSession().toString());
		assertEquals("0.0", Float.toString(fB.get(1).getIncomeEstimated()));
		assertEquals("0.0", Float.toString(fB.get(1).getExpensesEstimated()));
		assertEquals("0.0", Float.toString(fB.get(1).getBalanceEstimated()));
		assertEquals("20.0", Float.toString(fB.get(1).getIncomeConfirmed()));
		assertEquals("60.0", Float.toString(fB.get(1).getExpensesConfirmed()));
		assertEquals("-40.0", Float.toString(fB.get(1).getBalanceConfirmed()));
		
		// Total balance 
		// Get the total financial balance over all formative actions that have the status "CANCELLED"
		List<TotalBalance> tB=model.getListTotalBalance(new Date(1,1,2021), new Date(31,12,2021), "CANCELLED");
		
		// Check if the resulting list contains 1 total balance element like expected
		assertEquals("the number of total balances is incorrect",1,tB.size());
		
		// Check if the results for the total balance match the expected values
		assertEquals("0.0", Float.toString(tB.get(0).getTotalIncomeEstimated()));
		assertEquals("0.0", Float.toString(tB.get(0).getTotalExpensesEstimated()));
		assertEquals("20.0", Float.toString(tB.get(0).getTotalIncomeConfirmed()));
		assertEquals("60.0", Float.toString(tB.get(0).getTotalExpensesConfirmed()));
	}		
}
