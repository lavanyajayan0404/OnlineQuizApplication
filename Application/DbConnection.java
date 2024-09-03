 package Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DbConnection {
static	String url="jdbc:mysql://localhost:3304/quiz-app";
static	String username="root";
static	String password="7626";

public static Connection getConnection() throws SQLException {	
	Connection connection=DriverManager.getConnection(url,username,password);
	return connection;
}
}
