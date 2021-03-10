package Utils;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;

import org.apache.commons.dbutils.DbUtils;

/**
 * Encapsulates JDBC access data, read configuration, and database scripts for creation and loading.
 * and database scripts for creation and loading.
 */
public class Database extends DbUtil {
	//Location of configuration and database loading files
	private static final String APP_PROPERTIES = "src/main/resources/application.properties";
	private static final String SQL_SCHEMA = "src/main/resources/schema.sql";
	private static final String SQL_LOAD = "src/main/resources/data.sql";
	//database parameters read from application.properties (local database without user/password)
	private String driver;
	private String url;
	private static boolean databaseCreated=false;

	/**
	 * Creates an instance, reading the driver and url parameters from application.properties
	 */
	public Database() {
		Properties prop=new Properties();
		try {
			prop.load(new FileInputStream(APP_PROPERTIES));
		} catch (IOException e) {
			throw new ApplicationException(e);
		}
		driver=prop.getProperty("datasource.driver");
		url=prop.getProperty("datasource.url");
		if (driver==null || url==null)
			throw new ApplicationException("Configuracion de driver y/o url no encontrada en application.properties");
		DbUtils.loadDriver(driver);
	}
	public String getUrl() {
		return url;
	}
	/** 
	 * Creation of a clean database from the schema.sql script in src/main/properties
	 * (if onlyOnce=true it will only run the script the first time.
	 */
	public void createDatabase(boolean onlyOnce) {
		//actua como singleton si onlyOnce=true: solo la primera vez que se instancia para mejorar rendimiento en pruebas
		if (!databaseCreated || !onlyOnce) { 
			executeScript(SQL_SCHEMA);
			databaseCreated=true; //NOSONAR
		}
	}
	/** 
	 * Initial data load from data.sql script in src/main/properties
	 * (if onlyOnce=true it will only run the script the first time.
	 */
	public void loadDatabase() {
		executeScript(SQL_LOAD);
	}
	
}
