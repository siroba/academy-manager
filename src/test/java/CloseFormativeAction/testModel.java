package CloseFormativeAction;

import org.junit.After;
import org.junit.Before;

import Utils.Database;

public class testModel {
	private static Database db = new Database();
	
	@Before
	public void setUp() {
		db.createDatabase(true);
	}
	
	@After
	public void tearDown() {
		
	}
	
	public static void loadCleanDatabase(Database db) {
		db.executeBatch(new String[] {
				"delete from FormativeAction",
				"insert into FormativeAction (ID_fa, nameFa, totalPlaces, objectives, mainContent, status, enrollmentStart, enrollmentEnd) values\n"
				+ "	(1000, 'Initiation to scrum',	6,	'some objctives', 'some main content', 'EXECUTED', '2021-01-01 00:00:00.0',	'2021-02-01 00:00:00.0'),"
				+ "	(1001, 'Initiation to agile',	10, 'some objctives', 'some main content', 'EXECUTED', '2021-01-01 00:00:00.0',	'2021-02-08 00:00:00.0'),"
				+ "	(1002, 'JDBC',  		 		4,  'some objctives', 'some main content', 'ACTIVE',   '2021-03-01 00:00:00.0',	'2021-04-08 00:00:00.0'),"
				+ "	(1003, 'Java programming',	 	5,	'some objctives', 'some main content', 'ACTIVE',   '2021-04-01 00:00:00.0',	'2021-04-28 00:00:00.0');"
		});
	}
}
