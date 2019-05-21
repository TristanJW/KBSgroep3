package Applicatie;

import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //  Scherm scherm1 = new Scherm();
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

        HuidigeConfiguratie netwerk = new HuidigeConfiguratie();
        Algoritme algoritme = new Algoritme();
        LeveranciersLijst leverancier = new LeveranciersLijst();
//        netwerk.voegToe(leverancier.aanbodFirewall.get(0));
//        netwerk.voegToe(leverancier.aanbodLoadBalancer.get(0));
//        netwerk.voegToe(leverancier.aanbodWebserver.get(0));
//        netwerk.voegToe(leverancier.aanbodDBServer.get(1));
//        
//        for(int i =0; i < 4; i++){
//            netwerk.voegToe(leverancier.aanbodWebserver.get(1));
//        }
//        
//        for(int i =0; i < 3; i++){
//            netwerk.voegToe(leverancier.aanbodDBServer.get(0));
//        }
//        
//        for(NetwerkComponent nc: netwerk.getNetwerkLijst()){
//            System.out.print(nc.getNaam() + " ");
//        }
//        
//        System.out.println(netwerk.berekenBeschikbaarheid() + " " + netwerk.berekenTotalePrijs());
//        System.out.println("webservers: " + netwerk.berekenComponent(Webserver.class));
//        System.out.println("DBServers: " + netwerk.berekenComponent(DBServer.class));
        
        algoritme.maakCombinatie(99.99);
    }
}