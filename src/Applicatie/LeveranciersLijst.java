package Applicatie;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.sql.*;
import java.text.AttributedString;

public class LeveranciersLijst {
private String naampercentage;
    static ArrayList<NetwerkComponent> aanbodWebserver;
    static ArrayList<NetwerkComponent> aanbodDBServer;
    static ArrayList<NetwerkComponent> aanbodLoadBalancer;
    static ArrayList<NetwerkComponent> aanbodFirewall;

    public LeveranciersLijst() {
        aanbodWebserver = new ArrayList<>();
        aanbodDBServer = new ArrayList<>();
        aanbodLoadBalancer = new ArrayList<>();
        aanbodFirewall = new ArrayList<>();

        //netwerkcomponenten maken die de leverancier heeft
        //we gebruiken hiervoor een sql querry aan de hand van het type wordt bepaalt welke constructor
        //wordt toegepast
        try {
            ResultSet resultaat = JDBC.dataOphalen("SELECT itemID, Naam, prijs, beschikbaarheid, type FROM leverancierslijst");
            while (resultaat.next()) {

                String naam = resultaat.getString("Naam");
                int itemID = resultaat.getInt("itemID");
                double prijs = resultaat.getDouble("prijs");
                double beschikbaarheid = resultaat.getDouble("beschikbaarheid");
                String type = resultaat.getString("type");

                //type check
                if (type.equals("Webserver")) {
                    Webserver server = new Webserver(itemID, naam, prijs, beschikbaarheid);
                    aanbodWebserver.add(server);
                } else if (type.equals("DBserver")) {
                    DBServer database = new DBServer(itemID, naam, prijs, beschikbaarheid);
                    aanbodDBServer.add(database);
                } else if (type.equals("firewall")) {
                    Firewall firewall = new Firewall(itemID, naam, prijs, beschikbaarheid);
                    aanbodFirewall.add(firewall);
                } else if (type.equals("loadbalancer")) {
                    LoadBalancer loadbalancer = new LoadBalancer(itemID, naam, prijs, beschikbaarheid);
                    aanbodLoadBalancer.add(loadbalancer);
                }
            }
        } catch (SQLException se) {
            se.getStackTrace();
        }
    }

    public String[] zoekNaam(String term, ArrayList<NetwerkComponent> lijst) {
        int counter = 0;
        for (NetwerkComponent nc : lijst) {
            if (nc.getClassNaam().toLowerCase().equals(term)) {
                counter++;
            }
        }
        String[] namen = new String[counter];
        counter = 0;
        for (NetwerkComponent nc : lijst) {
            if (nc.getClassNaam().toLowerCase().equals(term)) {
                namen[counter] = nc.getNaam() + " " + nc.getBeschikbaarheid() + "%";
                counter++;
            }
        }
        return namen;
    }

}
