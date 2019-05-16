package Applicatie;

import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
          Scherm scherm1 = new Scherm();
//
//        JDBC database = new JDBC();
//
//        ResultSet resultaat = database.dataOphalen("SELECT * From leverancierslijst");
//        try {
//            while (resultaat.next()) {
//                int id = resultaat.getInt("Itemid");
//                String naam = resultaat.getString("naam");
//
//                System.out.println("ID:" + id + " naam:" + naam);
//            }
//        } catch (SQLException se) {
//            se.printStackTrace();
//        }
//
//        LeveranciersLijst leverancier = new LeveranciersLijst();
//        ArrayList<NetwerkComponent> servers = leverancier.aanbodWebserver;
//
//        for (NetwerkComponent s : leverancier.aanbodWebserver) {
//            System.out.println(s.getNaam());
//        }
            JDBC database = new JDBC();

                database.dataToevoegen("insert into netwerk (netwerkID, datum, beschikbaarheidspercentage, naam, prijs) values (12, \"2019-02-14\", 30, \"tesgdst\", 50)");

        HuidigeConfiguratie netwerk = new HuidigeConfiguratie();
        netwerk.maakCombinatie(99.99);
    }
}