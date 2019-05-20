package Applicatie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HuidigeConfiguratie {

    private ArrayList<NetwerkComponent> netwerkLijst;

    public HuidigeConfiguratie() {
        this.netwerkLijst = new ArrayList<NetwerkComponent>();
    }

    public ArrayList<NetwerkComponent> getNetwerkLijst() {
        return netwerkLijst;
    }

    public void voegToe(NetwerkComponent component) {
        netwerkLijst.add(component);
    }

    public void verwijderComponent(NetwerkComponent component) {
        netwerkLijst.remove(component);
    }

    public void vervang(NetwerkComponent component, int index) {
        netwerkLijst.set(index, component);
    }

    public double berekenBeschikbaarheid() {
        return (berekenComponent(Firewall.class) / 100) * (berekenComponent(LoadBalancer.class) / 100) * (berekenComponent(Webserver.class) / 100) * berekenComponent(DBServer.class);
    }

    public double berekenComponent(Class type) {
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
    public String berekenTotalePrijs() {
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

    public int dbTotalePrijs() {
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

    public void configuratieNaarDatabase(String query) {
        JDBC database = new JDBC();
        database.dataToevoegen(query);

    }

    public void dataNaarNetwerk(ResultSet resultaat) throws SQLException {
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
                System.out.println(server.getNaam() + "toegevoegd");
            } else if (type.equals("DBserver")) {
                DBServer database = new DBServer(itemID, naam, prijs, beschikbaarheid);
                netwerkLijst.add(database);
                System.out.println(database.getNaam() + "toegevoegd");
            } else if (type.equals("firewall")) {
                Firewall firewall = new Firewall(itemID, naam, prijs, beschikbaarheid);
                netwerkLijst.add(firewall);
                System.out.println(firewall.getNaam() + "toegevoegd");
            } else if (type.equals("loadbalancer")) {
                LoadBalancer loadbalancer = new LoadBalancer(itemID, naam, prijs, beschikbaarheid);
                netwerkLijst.add(loadbalancer);
                System.out.println(loadbalancer.getNaam() + "toegevoegd");
            }
        }
    }
}
