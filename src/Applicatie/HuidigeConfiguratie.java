package Applicatie;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

class HuidigeConfiguratie {

    private ArrayList<NetwerkComponent> netwerkLijst;

    HuidigeConfiguratie() {
        this.netwerkLijst = new ArrayList<NetwerkComponent>();
    }

    ArrayList<NetwerkComponent> getNetwerkLijst() {
        return netwerkLijst;
    }

    void voegToe(NetwerkComponent component) {
        netwerkLijst.add(component);
    }

    void verwijderComponent(NetwerkComponent component) {
        netwerkLijst.remove(component);
    }

    //afronding want als we naar 5 plaatsen achter de comma gaan dan wordt er fout afgerond
    double berekenBeschikbaarheid() {
        return (berekenComponent(Firewall.class) / 100) * (berekenComponent(LoadBalancer.class) / 100) * (berekenComponent(Webserver.class) / 100) * berekenComponent(DBServer.class);
    }

    void Optimaliseer(double percentage) {
        Algoritme algoritme = new Algoritme();
        netwerkLijst = algoritme.maakCombinatie(percentage);
    }

    private double berekenComponent(Class type) {
        double beschikbaarheid = 1;
        // voor elke component wordt gekeken of het een webserver is.
        // hierna wordt de formule uitgevoerd voor de beschikbaarheid.
        for (NetwerkComponent nc : netwerkLijst) {
            if (type.isAssignableFrom(nc.getClass())) {
                beschikbaarheid *= (1 - (nc.getBeschikbaarheid() / 100));
            }
        }
        return (1 - beschikbaarheid) * 100;
    }

    // looped over alle items in de netwerklijst ArrayList en telt de prijs bij
    // elkaar op, returned dit als int
    String berekenTotalePrijs() {
        int totalePrijs = 0;
        try {
            for (NetwerkComponent component : netwerkLijst) {
                totalePrijs += component.getPrijs();
            }
        } catch (NullPointerException npe) {
            System.out.println(npe);
        }
        return totalePrijs + " Euro";
    }

    int dbTotalePrijs() {
        int totalePrijs = 0;
        try {
            for (NetwerkComponent component : netwerkLijst) {
                totalePrijs += component.getPrijs();
            }
        } catch (NullPointerException npe) {
            System.out.println(npe);
        }
        return totalePrijs;
    }

    void configuratieNaarDatabase(String query) {
        JDBC database = new JDBC();
        database.dataToevoegen(query);

    }

    void dataNaarNetwerk(ResultSet resultaat) throws SQLException {
        while (resultaat.next()) {
            String naam = resultaat.getString("Naam");
            int itemID = resultaat.getInt("itemID");
            double prijs = resultaat.getDouble("prijs");
            double beschikbaarheid = resultaat.getDouble("beschikbaarheid");
            String type = resultaat.getString("type");

            //type check
            if (type.equals("Webserver")) {
                Webserver server = new Webserver(itemID, naam, prijs, beschikbaarheid);
                netwerkLijst.add(server);
            } else if (type.equals("DBserver")) {
                DBServer database = new DBServer(itemID, naam, prijs, beschikbaarheid);
                netwerkLijst.add(database);
            } else if (type.equals("firewall")) {
                Firewall firewall = new Firewall(itemID, naam, prijs, beschikbaarheid);
                netwerkLijst.add(firewall);
            } else if (type.equals("loadbalancer")) {
                LoadBalancer loadbalancer = new LoadBalancer(itemID, naam, prijs, beschikbaarheid);
                netwerkLijst.add(loadbalancer);
            }
        }
    }
}
