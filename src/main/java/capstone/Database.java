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
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS `dancecap`.`Person` (" +
					"`id` INT NOT NULL," +
					"`name` VARCHAR(45) NOT NULL," + 
					"`style` ENUM('JAZZ','HIPHOP','CONTEMPORARY','MODERN','THEATRE','TAP','BALLET','OTHER') NULL," +
					"PRIMARY KEY (`id`));");
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS`dancecap`.`Student` (" +
					"`id` INT NOT NULL," +
					"`year` INT NOT NULL," +
					"`major` VARCHAR(45) NULL," +
					"PRIMARY KEY (`id`)," +
					"CONSTRAINT `id_student`" +
					"FOREIGN KEY (`id`) REFERENCES `dancecap`.`Person` (`id`) " +
					"ON DELETE CASCADE" +
					" ON UPDATE CASCADE);");
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS `dancecap`.`Performer` (" +
					"`id` INT NOT NULL," +
					"`dance` VARCHAR(45) NOT NULL," +
					"PRIMARY KEY (`dance`, `id`)," + 
					"INDEX `id_idx` (`id` ASC)," +
					"CONSTRAINT `id_performer`" +
					"FOREIGN KEY (`id`) REFERENCES `dancecap`.`Person` (`id`) " +
					"ON DELETE CASCADE" + 
					" ON UPDATE CASCADE);");
            return true;
		}
		catch (Exception e) {
            System.out.println("SQLException in createDatabase(): " + e.getMessage());
            return false;
		}
	}

	public boolean addPerson(int id, String name, Style style) {
		try {

			statement.executeUpdate("INSERT INTO `dancecap`.`Person` (`id`, `name`, `style`)"
					+ " VALUES ('" + id + "', '" + name + "', '" + style.toString() + "');");
		return true;
		}
		catch (Exception e) {
            System.out.println("SQLException in addPerson(): " + e.getMessage());
            return false;
		}
		
	}
//	
//	public boolean addStudent(Student stu) {
//		try {
//			String majors = "";
//			for (String maj: stu.getMajor()) {
//				majors += "   "+ maj;
//			}
//			for (Style st: stu.getStyle()) {
//				statement.executeUpdate("INSERT INTO `dancecap`.`Student` (`id`, `name`, `major`, `year`, `style`)"
//						+ " VALUES ('" + stu.getID() + "', '" + stu.getName() + "', '" + majors + "', '" + stu.getYear() + "', '" + st.toString() + "');");
//			}
//		return true;
//		}
//		catch (Exception e) {
//            System.out.println("SQLException in addStudent(): " + e.getMessage());
//            return false;
//		}
//		
//	}
	
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
