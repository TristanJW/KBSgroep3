package Applicatie;

import java.util.ArrayList;
import java.sql.*;

public class LeveranciersLijst {

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
            ResultSet resultaat = JDBC.dataOphalen("SELECT Naam, prijs, beschikbaarheid, type FROM leverancierslijst");
            while (resultaat.next()) {

                String naam = resultaat.getString("Naam");
                double prijs = resultaat.getDouble("prijs");
                double beschikbaarheid = resultaat.getDouble("beschikbaarheid");
                String type = resultaat.getString("type");

                //type check
                if (type.equals("Webserver")) {
                    Webserver server = new Webserver(naam, prijs, beschikbaarheid);
                    aanbodWebserver.add(server);
                } else if (type.equals("DBserver")) {
                    DBServer database = new DBServer(naam, prijs, beschikbaarheid);
                    aanbodDBServer.add(database);
                } else if (type.equals("firewall")) {
                    Firewall firewall = new Firewall(naam, prijs, beschikbaarheid);
                    aanbodFirewall.add(firewall);
                } else if (type.equals("loadbalancer")) {
                    LoadBalancer loadbalancer = new LoadBalancer(naam, prijs, beschikbaarheid);
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
                namen[counter] = nc.getNaam();
                counter++;
            }
        }
        return namen;
    }

}
