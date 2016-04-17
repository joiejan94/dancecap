package capstone;

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

public class Main {
	
    public static void main(String[] args) {
    	Database db = new Database("jdbc:mysql://localhost/","root","cheetahs");
        get("/", (request, response) -> "welcome");
        get("/members", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            try {
              db.connect();
              db.createDatabase();
              
              Student student = new Student("Robbie McNew", new Style[]{Style.JAZZ, Style.HIPHOP}, 123456789, new String[]{"Chem E","Mech E"}, 2016);
//              db.addStudent(701581208, "Johanna Jan", "Computer Science", 2016, Style.JAZZ);
              db.addStudent(student);
              ResultSet rs = db.getStatement().executeQuery("SELECT distinct name, style,major FROM Student");

              ArrayList<String> output = new ArrayList<String>();
              while (rs.next()) {
                output.add( "Students: " + rs.getString("name") + ", Style: " + rs.getString("style") + ", Major: " + rs.getString("major"));
              }

              attributes.put("results", output);
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
