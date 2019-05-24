package Applicatie;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class JDBC {
    //driver naam en database url
    private static final String DB_URL = "jdbc:mysql://localhost/NerdyGadgets?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    //credentials
    private static final String USER = "MonitoringsApplicatie";
    private static final String PASS = "VeiligWachtwoord";

    //de methode om een connectie te maken naar de SQL database
    private static Connection connectieMaken() {
        //declaratie anders kunnen we niks returnen
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    //de methode om een sql querry makkelijk uit te voeren
    static ResultSet dataOphalen(String querry) {
        //declaratie anders kan er niks worden gereturnt
        ResultSet rs = null;
        try {
            Statement stmt = connectieMaken().createStatement(); //
            rs = stmt.executeQuery(querry);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return rs;
    }

    //methode voor invoegen data in db
    static void dataToevoegen(String querry) {
        try {
            Statement stmt = connectieMaken().createStatement(); //
            stmt.execute(querry);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
