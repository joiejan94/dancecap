package capstone;

import members.Person;
import members.Student;
import members.Style;
import static spark.Spark.*;
import spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("mysql username and password: ");
    	String user = sc.next();
    	String pass = sc.next();
    	Database db = new Database("jdbc:mysql://localhost/",user,pass);
        get("/", (request, response) -> "welcome");
        get("/members", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            try {
              db.connect();
              db.createDatabase();
//              Person human = new Person("Kimberly Iervoline",new Style[]{Style.HIPHOP},701346198);
              db.addPerson(701346198, "Kimberly Iervoline", Style.HIPHOP);
              
              ArrayList<String> students = new ArrayList<String>();
              ArrayList<String> performers = new ArrayList<String>();
              ResultSet rs_st = db.getStatement().executeQuery("SELECT distinct name, year, style "
              		+ "FROM dancecap.Student s join dancecap.Person p where s.id = p.id;");


              while (rs_st.next()) {
                students.add( "name: " + rs_st.getString("name") + ", year: " + rs_st.getString("year") + ", Style: " + rs_st.getString("style"));
              }

              ResultSet rs_per = db.getStatement().executeQuery("SELECT distinct name, dance "
                		+ "FROM dancecap.Performer perf join dancecap.Person p where perf.id = p.id;");
              
              while (rs_per.next()) {
                  performers.add( "name: " + rs_per.getString("name") + ", dance: " + rs_per.getString("dance"));

              }
              attributes.put("students", students);
              attributes.put("performers", performers);
              return new ModelAndView(attributes, "members.html");
            } catch (Exception e) {
              attributes.put("message", "There was an error: " + e);
              System.out.println("SQLException: " + e.getMessage());
              return new ModelAndView(attributes, "error.ftl");
            } finally {
              if (db.getConnection() != null) {
            	  db.disconnect();
              }
            }
          }, new FreeMarkerEngine());
        

        get("/performance", (req, res) -> "Performance");
        get("/academics", (req, res) -> "Acedemics");

        post("/testingform.php", (req, res) -> {
        	Map<String, Object> attributes = new HashMap<>();
            try {
              return new ModelAndView(attributes, "testingform.php");
            } catch (Exception e) {
              attributes.put("message", "There was an error: " + e);
              return new ModelAndView(attributes, "error.ftl");
            } finally {

              }
          }, new FreeMarkerEngine());
        
        //this can be removed later, I just want to use my own page for testing
        get("/test_jeff", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            try {
              db.connect();
              db.createDatabase();
//              Student student = new Student("Jeff Volz", new Style[]{Style.BALLET, Style.TAP}, 701557689, new String[]{"Comp E"}, 2016);
//              db.addStudent(701581208, "Johanna Jan", "Computer Science", 2016, Style.JAZZ);
//              db.addStudent(student);
              ResultSet rs = db.getStatement().executeQuery("SELECT distinct name, style ,major FROM Student");

              ArrayList<String> output = new ArrayList<String>();
              while (rs.next()) {
                output.add( "Students: " + rs.getString("name") + ", Style: " + rs.getString("style") + ", Major: " + rs.getString("major"));
              }

              attributes.put("results", output);
              return new ModelAndView(attributes, "test_jeff.html");
            } catch (Exception e) {
              attributes.put("message", "There was an error: " + e);
              System.out.println("SQLException: " + e.getMessage());
              return new ModelAndView(attributes, "error.ftl");
            } finally {
              if (db.getConnection() != null) {
            	  db.disconnect();
              }
            }
          }, new FreeMarkerEngine());
        
//      get("/db", (req, res) -> {
//      Connection connection = null;
//      Map<String, Object> attributes = new HashMap<>();
//      try {
//        connection = DriverManager.getConnection("jdbc:mysql://localhost/",
//      		  "root","cheetahs");
//                            
//
//        Statement stmt = connection.createStatement();
//        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS dancecap");
//        stmt.executeUpdate("USE dancecap");
//        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `dancecap`.`Student` (" +
//      		  "`id` INT NOT NULL," +
//      		  "`name` VARCHAR(45) NOT NULL," +
//      		  "`major` VARCHAR(45) NULL," +
//      		  "`year` INT NULL," +
//      		  "`style` ENUM('contemp','ballet','jazz','tap','hh','other') NOT NULL," +
//      		  "PRIMARY KEY (`id`, `style`))");
//        ResultSet rs = stmt.executeQuery("SELECT distinct name, style FROM Student");
//
//        ArrayList<String> output = new ArrayList<String>();
//        while (rs.next()) {
//          output.add( "Students: " + rs.getString("name") + ", Style: " + rs.getString("style"));
//        }
//
//        attributes.put("results", output);
//        return new ModelAndView(attributes, "db.ftl");
//      } catch (Exception e) {
//        attributes.put("message", "There was an error: " + e);
//        System.out.println("SQLException: " + e.getMessage());
//        return new ModelAndView(attributes, "error.ftl");
//      } finally {
//        if (connection != null) try{connection.close();} catch(SQLException e){}
//      }
//    }, new FreeMarkerEngine());
    }
}

class test {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
