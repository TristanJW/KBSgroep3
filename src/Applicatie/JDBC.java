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
    static final String DB_URL = "jdbc:mysql://localhost/NerdyGadgets";
     
    //credentials
    static final String USER = "MonitoringsApplicatie";
    static final String PASS = "VeiligWachtwoord";

    //de methode om een connectie te maken naar de SQL database
    public static Connection connectieMaken(){
        //declaratie anders kunnen we niks returnen
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return connection;
    }
    
    //de methode om een sql querry makkelijk uit te voeren
    public static ResultSet dataOphalen(String querry){
        //declaratie anders kan er niks worden gereturnt
        ResultSet rs = null;
        try{
            Statement stmt = connectieMaken().createStatement(); //
            rs = stmt.executeQuery(querry);
        }catch(SQLException se){
            se.printStackTrace();
        }
        return rs;
    }

    //methode voor invoegen data in db
    public static void dataToevoegen(String querry){
        try{
            Statement stmt = connectieMaken().createStatement(); //
            stmt.execute(querry);
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
}
