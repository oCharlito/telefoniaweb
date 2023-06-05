package telefoniaweb.utils;
/**
 *
 * @author Charlito
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
  private static String url = "jdbc:mysql://localhost:3306/provaweb"; 
  private static String user = "root";
  private static String passaword = "";
  
 public static Connection getConnection(String password){
    
          Connection connection = null;
          try {  
          Class.forName("com.mysql.cj.jdbc.Driver");          
          connection = DriverManager.getConnection(url, user, password);
      } catch ( SQLException ex) {
          ex.printStackTrace();          
      } catch (ClassNotFoundException ex) {
          Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
      }          
        return connection;
    }
 }
