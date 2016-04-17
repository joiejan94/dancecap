package capstone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import members.Student;
import members.Style;

public class Database {
	
	String dbURL;
	String dbUser;
	String dbPass;
	
	Connection connection;
	Statement statement;
	
	public Database(String url, String user, String password) {
		setDbURL(url);
		setDbUser(user);
		setDbPassword(password);
		connect();
	}
	
	public void connect() {
		try {
            connection = DriverManager.getConnection(getDbURL(),getDbUser(),getDbPass());
		}
		catch (Exception e) {
            System.out.println("SQLException in connect(): " + e.getMessage()); 
		}
	}
	public void disconnect() {
		try {
			connection.close();
		}
		catch (Exception e) {
			System.out.println("SQLException in disconnect(): " + e.getMessage());
		}

	}
	
	public boolean createDatabase() {
		try {             
			statement = connection.createStatement();
			statement.executeUpdate("CREATE DATABASE IF NOT EXISTS dancecap");
			statement.executeUpdate("USE dancecap");
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS `dancecap`.`Student` (" +
          		  "`id` INT NOT NULL," +
          		  "`name` VARCHAR(45) NOT NULL," +
          		  "`major` VARCHAR(45) NULL," +
          		  "`year` INT NULL," +
          		  "`style` ENUM('HIPHOP', 'JAZZ', 'BALLET', 'CONTEMPORARY', 'MODERN', 'TAP', 'THEATRE', 'OTHER') NOT NULL," +
          		  "PRIMARY KEY (`id`, `style`))");
            return true;
		}
		catch (Exception e) {
            System.out.println("SQLException in createDatabase(): " + e.getMessage());
            return false;
		}
	}

	public boolean addStudent(int id, String name, String major, int year, Style style) {
		try {
			statement.executeUpdate("INSERT INTO `dancecap`.`Student` (`id`, `name`, `major`, `year`, `style`)"
					+ " VALUES ('" + id + "', '" + name + "', '" + major + "', '" + year + "', '" + style.toString() + "');");
		return true;
		}
		catch (Exception e) {
            System.out.println("SQLException in addStudent(): " + e.getMessage());
            return false;
		}
		
	}
	
	public boolean addStudent(Student stu) {
		try {
			String majors = "";
			for (String maj: stu.getMajor()) {
				majors += ", "+ maj;
			}
			for (Style st: stu.getStyle()) {
				statement.executeUpdate("INSERT INTO `dancecap`.`Student` (`id`, `name`, `major`, `year`, `style`)"
						+ " VALUES ('" + stu.getID() + "', '" + stu.getName() + "', '" + majors + "', '" + stu.getYear() + "', '" + st.toString() + "');");
			}
		return true;
		}
		catch (Exception e) {
            System.out.println("SQLException in addStudent(): " + e.getMessage());
            return false;
		}
		
	}
	
	public Connection getConnection() {
		return connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public String getDbURL() {
		return dbURL;
	}

	public String getDbUser() {
		return dbUser;
	}

	public String getDbPass() {
		return dbPass;
	}
	
	public void setDbURL(String url) {
		dbURL = url;
	}
	
	public void setDbUser(String user) {
		dbUser = user;
	}
	
	public void setDbPassword(String pass) {
		dbPass = pass;
	}

	

}
