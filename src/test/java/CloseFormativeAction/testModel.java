package CloseFormativeAction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
		db.loadDatabase();
	}
	
	@Test
	public void testLALA() {
		
	}
}
