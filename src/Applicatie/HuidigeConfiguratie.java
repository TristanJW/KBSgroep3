package Applicatie;

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
}

