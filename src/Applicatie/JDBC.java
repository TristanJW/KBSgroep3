/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Applicatie;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jascha
 */
public class JDBC {
    //driver naam en database url
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/NerdyGadgets";
     
    //credentials
    static final String USER = "MonitoringsApplicatie";
    static final String PASS = "VeiligWachtwoord";

    public static Connection connectieMaken(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return connection;
    }
    
    public ResultSet dataOphalen(String querry){
        ResultSet rs = null;
        try{
            Statement stmt = connectieMaken().createStatement();
            rs = stmt.executeQuery(querry);
        }catch(SQLException se){
            se.printStackTrace();
        }
        return rs;
    }
}
