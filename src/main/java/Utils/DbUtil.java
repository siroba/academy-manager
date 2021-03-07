package Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;


/**
 * Utility methods to simplify the queries performed in the classes.
 * that implement the business logic:
 * It is implemented as an abstract class so that the derived class implements the details * related to the connection and to the database structure to be created, and at the same time
 * The derived class implements the details related to the connection and to the database structure to be created, and at the same time
 * can use the methods defined here.
 *
 <br>Most of the utility methods use apache commons-dbutils which handles all the handling of the resultsets, their mapping, and their * mapping.
 <br>Most of the utility methods use apache commons-dbutils which handles all the handling of resultsets, their mapping to objects and exceptions allowing a much cleaner code.
 * in the business layer classes and DAOs.
 */
public abstract class DbUtil {
	/** Obtaining the connection url to be implemented in the subclass */
	public abstract String getUrl();

	/** Obtains a connection object for this database */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(getUrl());
	}

	//Documentacion de apache dbutils:
	//https://commons.apache.org/proper/commons-dbutils/apidocs/index.html
	//https://commons.apache.org/proper/commons-dbutils/examples.html

	/**
	 Executes a sql query with the specified parameters mapping the result to a list of objects of the class specified in pojoClass; /** * Runs a sql query with the specified parameters mapping the result into a list of objects
	 * of the class specified in pojoClass;
	 * Uses apache commons-dbutils to perform the mapping and the handling of the other aspects of jdbc
	 */
	public <T> List<T> executeQueryPojo(Class<T> pojoClass, String sql, Object... params) {
		Connection conn=null;
		try {
			conn=this.getConnection();
			BeanListHandler<T> beanListHandler=new BeanListHandler<>(pojoClass);
			QueryRunner runner=new QueryRunner();
			return runner.query(conn, sql, beanListHandler, params);
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}


	/**
	 * Execute a sql query with the specified parameters mapping the result into a list of object arrays;
	 * Uses apache commons-dbutils to perform the mapping and the handling of the other aspects of jdbc
	 */
	public List<Object[]> executeQueryArray(String sql, Object... params) {
		Connection conn=null;
		try {
			conn=this.getConnection();
			//As there is no class specified to perform the mapping, it uses the ArrayListHandler
			ArrayListHandler beanListHandler=new ArrayListHandler();
			QueryRunner runner=new QueryRunner();
			return runner.query(conn, sql, beanListHandler, params);
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	/**
	 * Execute a sql update query with the specified parameters
	 */
	public void executeUpdateQuery(String sql, Object... params) {
		Connection conn=null;
		try {
			conn=this.getConnection();
			//As there is no class specified to perform the mapping, it uses the ArrayListHandler
			PreparedStatement pstmt=conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				if (params[i] instanceof String){
					pstmt.setString(i+1, String.valueOf(params[i]));
				}
				else if (Integer.class.isInstance(params[i])) {
					pstmt.setInt(i+1, (int) params[i]);

				}
				else if (Float.class.isInstance(params[i])) {
					pstmt.setFloat(i+1, (float) params[i]);

				}
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	public List<Map<String,Object>> executeQueryMap(String sql, Object... params) {
		Connection conn=null;
		try {
			conn=this.getConnection();
			MapListHandler beanListHandler=new MapListHandler();
			QueryRunner runner=new QueryRunner();
			return runner.query(conn, sql, beanListHandler, params);
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}
	/**
	 * Execute an update sql statement with the specified parameters;
	 * Uses apache commons-dbutils to handle all aspects of jdbc; * /** * Uses apache commons-dbutils to handle all aspects of jdbc
	 */
	public void executeUpdate(String sql, Object... params) {
		Connection conn=null;
		try {
			conn=this.getConnection();
			QueryRunner runner=new QueryRunner();
			runner.update(conn, sql, params);
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	/**
	 * Simple method to execute all sql statements found in a file, taking into account:
	 * <br/>- Each statement MUST end in ; and may occupy several lines.
	 <br/>- Line comments (--) are allowed.
	 <br/>- All drop statements are executed at the beginning,
	 <br/>- Failures are ignored in case the table does not exist (only for drop).
	 */
	public void executeScript(String fileName) {
		List<String> lines;
		try {
			lines=Files.readAllLines(Paths.get(fileName));
		} catch (IOException e) {
			throw new ApplicationException(e);
		}
		//separate the sql statements into two lists, one for drop and one for the rest as they will be executed differently.
		List<String> batchUpdate=new ArrayList<>();
		List<String> batchDrop=new ArrayList<>();
		StringBuilder previousLines=new StringBuilder(); //stores lines before the separator (;)
		for (String line : lines) {
			line=line.trim();
			if (line.length()==0 || line.startsWith("--")) //ignore empty lines line comments
				continue;
			if (line.endsWith(";")) {
				String sql=previousLines.toString()+line;
				//separates drop from the rest
				if (line.toLowerCase().startsWith("drop"))
					batchDrop.add(sql);
				else
					batchUpdate.add(sql);
				//new line
				previousLines=new StringBuilder();
			} else {
				previousLines.append(line+" ");
			}
		}
		//Execute all sentences, drop first (if any)
		if (!batchDrop.isEmpty())
			this.executeBatchNoFail(batchDrop);
		if (!batchUpdate.isEmpty())
			this.executeBatch(batchUpdate);
	}
	/**
	 * Execute a set of sql update statements in a single batch.
	 */
	public void executeBatch(String[] sqls) {
		executeBatch(Arrays.asList(sqls));
	}
	/**
	 * Execute a set of sql update statements in a single batch.
	 */
	public void executeBatch(List<String> sqls) {
		try (Connection cn=DriverManager.getConnection(getUrl());
			Statement stmt = cn.createStatement()) {
				for (String sql : sqls)
					stmt.addBatch(sql);
				stmt.executeBatch();
		} catch (SQLException e) {
			// Do not let exceptions pass (do not just leave the code auto-generated by Eclipse with printStackTrace).
			throw new UnexpectedException(e);
		}
	}
	/**
	 * Execute a set of sql update statements in a single batch, without causing exception when execution fails.
	 * (normally used to delete tables from the database, which would fail if they don't exist)
	 */
	public void executeBatchNoFail(List<String> sqls) {
		try (Connection cn=DriverManager.getConnection(getUrl());
			Statement stmt = cn.createStatement()) {
				for (String sql : sqls)
					executeWithoutException(stmt,sql);
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		}
	}
	private void executeWithoutException(Statement stmt, String sql) {
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			//does not intentionally cause exception
		}
	}

}
