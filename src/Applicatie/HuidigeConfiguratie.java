package Applicatie;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.math.*;

public class HuidigeConfiguratie {

    private ArrayList<NetwerkComponent> netwerkLijst;
    private LeveranciersLijst leverancier = new LeveranciersLijst();

    public HuidigeConfiguratie() {
        this.netwerkLijst = new ArrayList<NetwerkComponent>();
    }

    public void voegToe(NetwerkComponent component) {
        netwerkLijst.add(component);
    }

    // tijdelijke methode die ik even nodig had in ConfiguratiePanel todo remove if not needed anymore
    public ArrayList<NetwerkComponent> returnConfig() {
        return netwerkLijst;
    }

    public double berekenBeschikbaarheid() {
        return (berekenFirewall() / 100) * (berekenLoadbalancer() / 100) * (berekenWebservers() / 100) * berekenDBservers();
    }

    public void maakCombinatie(double percentage) {
        // array met strings om een aantal aan te geven voor de configuratie
        String lijst[] = new String[8];
        double hoogstepercentage = 0; // het hoogste beschikbaarheidspercentage tot nu toe.
        double goedkoopste = 0; // het goedkoopste tot nu toe.

        // vragen aan jasper of een netwerk altijd moet bestaan uit een firewall en
        // loadbalancer
        // voor elk aanbod van netwerkcomponent wordt de naam in een array gezet.
        // deze naam wordt gebruikt om vervolgens het netwerkcomponent in een aanbod
        // ArrayList te zoeken.

        //combinatie toevoegen aan het netwerk zodat we de beschikbaarheid kunnen berekenen.
        voegToe(leverancier.aanbodFirewall.get(0));
        voegToe(leverancier.aanbodLoadBalancer.get(0));
        voegToe(leverancier.aanbodWebserver.get(0));
        voegToe(leverancier.aanbodDBServer.get(0));
        
        
        if (!isVoldaan(percentage, netwerkLijst)) {
            //als het opgeggeven percentage nog niet bereikt is voegen we servers toe.
            while (hoogstepercentage < percentage) {
                if (berekenWebservers() < berekenDBservers()) {
                    netwerkLijst.add(leverancier.aanbodWebserver.get(2));
                } else {
                    netwerkLijst.add(leverancier.aanbodDBServer.get(0));
                }
                
                hoogstepercentage = berekenBeschikbaarheid();
                break;
            }
        }
    }

    private Boolean isErin(String s, ArrayList<NetwerkComponent> aanbod) {
        return netwerkLijst.contains(zoekOpNaam(s, aanbod));
    }

    public Boolean isVoldaan(double percentage, ArrayList<NetwerkComponent> aanbod) {
        return berekenBeschikbaarheid() >= percentage;
    }

    public double berekenWebservers() {
        double beschikbaarheid = 1;

        //voor elke component wordt gekeken of het een webserver is.
        //hierna wordt de formule uitgevoerd voor de beschikbaarheid.
        
        for (NetwerkComponent nc : netwerkLijst) {
            if (nc instanceof Webserver) {
                    beschikbaarheid *= (1-(nc.getBeschikbaarheid()/100));
                }
        }
        return (1-beschikbaarheid)* 100;
    }

    public double berekenDBservers() {
        double beschikbaarheid = 1;

        //voor elke component wordt gekeken of het een DBServer is.
        //hierna wordt de formule uitgevoerd voor de beschikbaarheid.
        for (NetwerkComponent nc : netwerkLijst) {
            if (nc instanceof DBServer) {
                    beschikbaarheid *= (1-(nc.getBeschikbaarheid()/100));
            }
        }
        return (1-beschikbaarheid)* 100;

    }

    public double berekenFirewall() {
        double beschikbaarheid = 0;

        for (NetwerkComponent nc : netwerkLijst) {
            if (nc instanceof Firewall) {
                beschikbaarheid = nc.getBeschikbaarheid();
                break;
            }
        }
        return beschikbaarheid;
    }

    public double berekenLoadbalancer() {
        double beschikbaarheid = 0;

        for (NetwerkComponent nc : netwerkLijst) {
            if (nc instanceof LoadBalancer) {
                beschikbaarheid = nc.getBeschikbaarheid();
                break;
            }
        }
        return beschikbaarheid;
    }

    public NetwerkComponent zoekOpNaam(String term, ArrayList<NetwerkComponent> aanbod) {
        // zoekterm
        term = term.toLowerCase();
        NetwerkComponent apparaat = null;

        // voor elke entry in de arraylist kijken of de naam, bijvoorbeeld HALW9001
        // gelijk is
        // als dit zo is wordt de hele class ervan terug gegeven.
        for (int i = 0; i < aanbod.size(); i++) {
            if (aanbod.get(i).getNaam().toLowerCase().equals(term)) {
                apparaat = aanbod.get(i);
            }
        }
        return apparaat;
    }

    // looped over alle items in de netwerklijst ArrayList en telt de prijs bij elkaar op, returned dit als int
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

    public void configuratieNaarDatabase() {
        for (NetwerkComponent component : netwerkLijst) {
            System.out.println(component);
        }
    }
}
