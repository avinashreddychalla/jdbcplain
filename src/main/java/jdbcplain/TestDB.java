package jdbcplain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDB {

	private static final String DB_CONNECTION = "jdbc:mysql://mydb.cz5te665b1io.us-east-1.rds.amazonaws.com:3306/achalla";
	private static final String DB_USER = "achalla";
	private static final String DB_PASSWORD = "Ravela072";

	public static void main(String[] args) throws SQLException {

		System.out.println("-------- MySQL JDBC Connection Testing ------------");

		
		selectRecordsFromDbUserTable();

	}

	private static void selectRecordsFromDbUserTable() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;

		String selectTableSQL = "SELECT emplyee_id, full_names from Employee";

		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(selectTableSQL);

			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);

			while (rs.next()) {

				String emplyeId = rs.getString("emplyee_id");
				String emplyeName = rs.getString("full_names");

				System.out.println("userid : " + emplyeId);
				System.out.println("username : " + emplyeName);

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}

	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}

}
