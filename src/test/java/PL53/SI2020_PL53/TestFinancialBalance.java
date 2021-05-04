package PL53.SI2020_PL53;

import org.junit.*;
import static org.junit.Assert.assertEquals;

import org.junit.rules.ExpectedException;

import com.fasterxml.jackson.core.JsonProcessingException;

import Utils.Database;
import giis.demo.tkrun.*;
import giis.demo.util.*;

import CheckFinancialBalance.*;
import PL53.util.Date;

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
				"delete from teacher",
				"insert into teacher()",
				"delete from formativeAction",
				"insert into formativeAction(1000, 'Initiation to scrum',	 6,	'some objctives', 'some main content', 'EXECUTED', '2021-01-01 00:00:00.0',	'2021-02-01 00:00:00.0')",
				"insert into formativeAction(1001, 'Initiation to agile',	10, 	'some objctives', 'some main content', 'ACTIVE', '2021-01-01 00:00:00.0',	'2021-02-08 00:00:00.0')",
				"insert into formativeAction(1002, 'JDBC',  		 4,  	'some objctives', 'some main content', 'CANCELLED',   '2021-03-01 00:00:00.0',	'2021-04-08 00:00:00.0')",
				"insert into formativeAction(1003, 'Java programming',	 5,	'some objctives', 'some main content', 'ACTIVE',   '2021-04-01 00:00:00.0',	'2021-04-28 00:00:00.0')",
				"delete from fee",
				"insert into fee()",
				"insert into fee()",
				"insert into fee()",
				"insert into fee()",
				"delete from session",
				"insert into session()",
				"delete from teacherTeacehr",
				"insert into teacherTeaches()",
				"delete from professional",
				"insert into professional()",
				"insert into professional()",
				"insert into professional()",
				"insert into professional()",
				"delete from enrollment",
				"insert into enrollment()",
				"insert into enrollment()",
				"insert into enrollment()",
				"insert into enrollment()",
				"insert into enrollment()",
				"insert into enrollment()",
				"insert into enrollment()",
				"insert into enrollment()",
				"insert into enrollment()",
				"insert into enrollment()",
				"delete from movement",
				"insert into movement()",
				"insert into movement()",
				"insert into movement()",
				"insert into movement()",
				"insert into movement()",
				"insert into movement()",
				"insert into movement()",
				"insert into movement()",
				"insert into movement()",
				"insert into movement()",
				"delete from payment",
				"insert into payment()",
				"insert into payment()",
				"insert into payment()",
				"insert into payment()",
				"insert into payment()",
				"insert into payment()",
				"insert into payment()",
				"insert into payment()",
				"delete from movementTeacher",
				"insert into movementTeacher()",
				"delete from paymentTeacher",
				"insert into paymentTeacher()",
				
			});
	}
	
	/**
	 * Check the list of races that the user sees at the time of registration for different registration phases:
	 * Must show all races excluding past races, indicating Open in which registration is possible;
	 With the setUp database, it covers the five equivalence classes related to Date of registration: * (races 100 to 104)
	 * (races 100 to 104)
	 */
	@Test
	public void testCarrerasActivasList() {
		CheckFinancialBalance.Model model=new CheckFinancialBalance.Model();
		List<FinancialBalance> fB=model.getListFinancialBalance(new Date(1,1,2021), new Date(31,12,2021), null);
		//Display should show all the runs of the DB except the first one which is passed, the last one should not indicate open.
		assertEquals("the number of financial balances is incorrect",4,fB.size());
		//the race list contains a one-dimensional array of objects
		assertEquals("12", fB.get(0).getName());
		assertEquals("12", fB.get(0).getStatus());
		assertEquals("12", fB.get(0).getFirstSession());
		assertEquals("12", fB.get(0).getLastSession());
		assertEquals("12", fB.get(0).getIncomeConfirmed());
		assertEquals("12", fB.get(0).getExpensesConfirmed());
		assertEquals("12", fB.get(0).getBalanceConfirmed());
		assertEquals("12", fB.get(0).getIncomeEstimated());
		assertEquals("12", fB.get(0).getExpensesEstimated());
		assertEquals("12", fB.get(0).getBalanceEstimated());
		
		assertEquals("12", fB.get(0).getName());
		assertEquals("12", fB.get(0).getStatus());
		assertEquals("12", fB.get(0).getFirstSession());
		assertEquals("12", fB.get(0).getLastSession());
		assertEquals("12", fB.get(0).getIncomeConfirmed());
		assertEquals("12", fB.get(0).getExpensesConfirmed());
		assertEquals("12", fB.get(0).getBalanceConfirmed());
		assertEquals("12", fB.get(0).getIncomeEstimated());
		assertEquals("12", fB.get(0).getExpensesEstimated());
		assertEquals("12", fB.get(0).getBalanceEstimated());
		
		assertEquals("12", fB.get(0).getName());
		assertEquals("12", fB.get(0).getStatus());
		assertEquals("12", fB.get(0).getFirstSession());
		assertEquals("12", fB.get(0).getLastSession());
		assertEquals("12", fB.get(0).getIncomeConfirmed());
		assertEquals("12", fB.get(0).getExpensesConfirmed());
		assertEquals("12", fB.get(0).getBalanceConfirmed());
		assertEquals("12", fB.get(0).getIncomeEstimated());
		assertEquals("12", fB.get(0).getExpensesEstimated());
		assertEquals("12", fB.get(0).getBalanceEstimated());
		
		assertEquals("12", fB.get(0).getName());
		assertEquals("12", fB.get(0).getStatus());
		assertEquals("12", fB.get(0).getFirstSession());
		assertEquals("12", fB.get(0).getLastSession());
		assertEquals("12", fB.get(0).getIncomeConfirmed());
		assertEquals("12", fB.get(0).getExpensesConfirmed());
		assertEquals("12", fB.get(0).getBalanceConfirmed());
		assertEquals("12", fB.get(0).getIncomeEstimated());
		assertEquals("12", fB.get(0).getExpensesEstimated());
		assertEquals("12", fB.get(0).getBalanceEstimated());
	}

	/**
	 * No invalid classes have been determined in the design, but to complete the test, the following will be checked
	 * the validity of the received parameters.
	 * In getListaCarreras there is only one date, which could be null if it is invoked incorrectly.
	 * This serves as an example of how to test with JUnit3 when the desired behavior is an exception.
	 */
	@Test(expected=ApplicationException.class)
	public void testCarrerasActivasException1() {
		CarrerasModel inscr=new CarrerasModel();
		inscr.getListaCarreras(null);
	}
	/**
	 * Use of rules, to check also the messages of the exceptions.
	 */
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	@Test
	public void testCarrerasActivasException2() {
		CarrerasModel inscr=new CarrerasModel();
		thrown.expect(ApplicationException.class);
		thrown.expectMessage("La fecha de inscripcion no puede ser nula");
		inscr.getListaCarreras(null);
	}

	/**
	 * Determination of the discount or percentage surcharge according to the date of enrollment.
	 * (Covers the classes valid for the Percentage discount:
	 * 3 related to registration date and 1 related to the valid race id )
	 */
	@Test
	public void testPorcentajeDescuentoRecargoValidas() {
		//Reuse the setUp for the tests from the list of races shown to the user.
		//using a date and different races that will cover valid classes
		Date fecha=Util.isoStringToDate("2016-11-10");
		CarrerasModel inscr=new CarrerasModel();
		assertEquals(-30,inscr.getDescuentoRecargo(103,fecha));
		assertEquals(0,inscr.getDescuentoRecargo(102,fecha));
		assertEquals(+50,inscr.getDescuentoRecargo(101,fecha));
		//Since the limit values at the two extremes of the ranges have not been tested, 
		//add cases for this (phase 1 and 2, upper end)
		assertEquals(-30,inscr.getDescuentoRecargo(103,Util.isoStringToDate("2016-11-15")));
		assertEquals(0,inscr.getDescuentoRecargo(102,Util.isoStringToDate("2016-11-19")));
	}
	
	/**
	 * Determination of the discount or percentage surcharge according to the date of enrollment. 
	 * (Covers invalid classes, to which the date validation should be added).
	 * It could be done in three methods similar to the previous ones or in one method with three similar parts.
	 * To avoid duplication of code, a generic method invoked from the three tests is used.
	 * (uses the ExpectedException rule defined above)
	 */
	@Test public void testPorcentajeDescuentoRecargoInvalidaCarreraFinalizada() {
		porcentajeDescuentoRecargoInvalidas(100,"No es posible la inscripcion en esta fecha");
	}
	@Test public void testPorcentajeDescuentoRecargoInvalidaCarreraAntesInscripcion() {
		porcentajeDescuentoRecargoInvalidas(104,"No es posible la inscripcion en esta fecha");
	}
	@Test public void testPorcentajeDescuentoRecargoInvalidaCarreraNoExiste() {
		porcentajeDescuentoRecargoInvalidas(99,"Id de carrera no encontrado: 99");
	}
	public void porcentajeDescuentoRecargoInvalidas(long idCarrera, String message) {
		Date fecha=Util.isoStringToDate("2016-11-10");
		CarrerasModel inscr=new CarrerasModel();
		thrown.expect(RuntimeException.class);
		thrown.expectMessage(message);
		inscr.getDescuentoRecargo(idCarrera,fecha);
	}

}
