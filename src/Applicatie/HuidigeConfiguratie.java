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

    public double berekenBeschikbaarheid(ArrayList<NetwerkComponent> netwerk) {
        // dit zijn de verschillende beschikbaarheden per component
        double bWS = 1;
        double bDB = 1;
        double bFW = 0;
        double bLB = 0;

        int counterWS = 0;
        int counterDB = 0;

        // kijken hoeveel webservers in het netwerk zitten
        for (NetwerkComponent nc : netwerk) {
            if (nc instanceof Webserver) {
                counterWS++;
            } else if (nc instanceof DBServer) {
                counterDB++;
            } else if (nc instanceof Firewall) {
                // bigdecimal is de placeholder om vanaf te ronden in de double.
                // decimalformat werkt niet omdat sommige decimalen te groot zijn.
                BigDecimal bdFW = new BigDecimal(nc.getBeschikbaarheid() / 100).setScale(5, RoundingMode.HALF_EVEN);
                bFW = bdFW.doubleValue();
            } else if (nc instanceof LoadBalancer) {
                // afronding
                BigDecimal bdLB = new BigDecimal(nc.getBeschikbaarheid() / 100).setScale(5, RoundingMode.HALF_EVEN);
                bLB = bdLB.doubleValue();
            }
        }

        // de array met doubles moet netzogroot zijn als het aantal webservers
        double beschikbaarheidWS[] = new double[counterWS];
        double beschikbaarheidDB[] = new double[counterDB];

        counterWS = 0;
        counterDB = 0;

        // de beschikbaarheidsperentages in de correcte arrays zetten
        for (NetwerkComponent nc : netwerk) {
            if (nc instanceof Webserver) {
                // afrondingen toelichting zie regel43
                BigDecimal bdWS = new BigDecimal(nc.getBeschikbaarheid() / 100).setScale(2, RoundingMode.HALF_EVEN);
                beschikbaarheidWS[counterWS] = (bdWS.doubleValue());
                counterWS++;
            } else if (nc instanceof DBServer) {
                BigDecimal bdDB = new BigDecimal(nc.getBeschikbaarheid() / 100).setScale(2, RoundingMode.HALF_EVEN);
                beschikbaarheidDB[counterDB] = (bdDB.doubleValue());
                counterDB++;
            }
        }

        // het echte beschikbaarheidspercentage uitrekenen voor fouttollerante servers
        for (int i = 0; i < beschikbaarheidWS.length; i++) {
            if (i == 0) {
                bWS = 1 - (1 - beschikbaarheidWS[i]);
            } else {
                bWS *= (1 - beschikbaarheidWS[i]);
            }
        }
        for (int i = 0; i < beschikbaarheidDB.length; i++) {
            if (i == 0) {
                bDB -= (1 - beschikbaarheidDB[i]);
            } else {
                bDB *= (1 - beschikbaarheidDB[i]);
            }
        }
        // het beschikbaarheidspercentage uitrekenen voor het hele netwerk;

        // *****testen*****
        System.out.println("Beschikbaarheid firewall: " + bFW);
        System.out.println("Beschikbaarheid loadbalancer: " + bLB);
        System.out.println("Beschikbaarheid Webservers: " + bWS);
        System.out.println("Beschikbaarheid Databaseservers: " + bDB);
        // *****testen*****

        BigDecimal bdBeschikbaarheid = new BigDecimal(bFW * bLB * bWS * 100).setScale(2, RoundingMode.DOWN);
        return bdBeschikbaarheid.doubleValue();
    }

    public void maakCombinatie(int aantal, double percentage) {
        // array met strings om een aantal aan te geven voor de configuratie
        String lijst[] = new String[aantal];

        // vragen aan jasper of een netwerk altijd moet bestaan uit een firewall en
        // loadbalancer
        // voor elk aanbod van netwerkcomponent wordt de naam in een array gezet.
        // deze naam wordt gebruikt om vervolgens het netwerkcomponent in een aanbod
        // ArrayList te zoeken.
        for (int i = 0; i < leverancier.aanbodFirewall.size(); i++) {
            lijst[0] = leverancier.aanbodFirewall.get(i).getNaam();
            netwerkLijst.add(zoekOpNaam(lijst[0], leverancier.aanbodFirewall));

            for (int j = 0; j < leverancier.aanbodLoadBalancer.size(); j++) {
                lijst[1] = leverancier.aanbodLoadBalancer.get(j).getNaam();
                netwerkLijst.add(zoekOpNaam(lijst[1], leverancier.aanbodLoadBalancer));
                // if(isVoldaan(percentage,netwerkLijst)){
                // break;
                // }
                for (int k = 0; k < leverancier.aanbodWebserver.size(); k++) {
                    lijst[2] = leverancier.aanbodWebserver.get(k).getNaam();
                    netwerkLijst.add(zoekOpNaam(lijst[2], leverancier.aanbodWebserver));
                    break;
                }
            }
        }
        System.out.println(berekenBeschikbaarheid(netwerkLijst));

    }

    private Boolean isErin(String s, ArrayList<NetwerkComponent> aanbod) {
        return netwerkLijst.contains(zoekOpNaam(s, aanbod));
    }

    public Boolean isVoldaan(double percentage, ArrayList<NetwerkComponent> aanbod) {
        return berekenBeschikbaarheid(aanbod) >= percentage;
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
}
