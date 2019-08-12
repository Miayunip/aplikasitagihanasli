/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Login;

/**
 *
 * @author Acer
 */

// import some needed library
import java.sql.*;

public class LoginModel {
    // Declaring a few variables
    private Connection conn = null;
    
    private static final String DB_URL = "jdbc:mysql://localhost/penagihan";
    private static final String DB_USER = "root";
    private static final String DB_PASS = ""; // Password sql saya
    
    private String uName;
    private String pWord;
    private String level;
    
    // Declaring method for opening connection to DB
    private void openConnection() {
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Connection Success!");
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    
    // Declaring method for closing connection to DB
    private void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    
    // Declaring method for check who is try to login
    public boolean checking(String uName, String pWord, String level) {
        openConnection();
        
        String query = "SELECT * FROM `user` WHERE username = " + "'" + uName + "'";
        
        try {
            Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                this.uName = rs.getString("username");
                this.pWord = rs.getString("password");
                this.level = rs.getString("level");
            }
            
            System.out.println("Initiating success!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        closeConnection();
        
        if (pWord.equals(this.pWord) && level.equalsIgnoreCase(this.level)) {
            return true;
        } else {
            return false;
        }
    }
}
