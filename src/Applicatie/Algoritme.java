
package Applicatie;

import java.util.ArrayList;

public class Algoritme {
    private ArrayList<NetwerkComponent> netwerk = new ArrayList<>();
    private LeveranciersLijst leverancier = new LeveranciersLijst();
    private int kostenroof = 0;
    private Boolean webserversDoorlopen = false;
    private Boolean DBServersDoorlopen = false;
    private Boolean DBServersVerzet = false;

    ArrayList<NetwerkComponent> maakCombinatie(double percentage) {
        // kijken of netwerklijst al een firewall, loadbalancer, webserver of dbserver heeft.
        if (netwerk.isEmpty()) {
            netwerk.add(leverancier.aanbodFirewall.get(0));
            netwerk.add(leverancier.aanbodLoadBalancer.get(0));
            netwerk.add(leverancier.aanbodWebserver.get(0));
            netwerk.add(leverancier.aanbodDBServer.get(0));
        } else {// als er al van alle componenten één aanwezig is dan wordt dit algoritme doorgelopen.
            // kijken of we een webserver of een database server nodig is.
            if (berekenComponent(Webserver.class, netwerk) < berekenComponent(DBServer.class, netwerk)) {
                voegVolgendeToe(Webserver.class);
            } else {
                voegVolgendeToe(DBServer.class);
            }
        }
        // als het opgegeven percentage niet behaald is dan gaat het algoritme verder opzoek.
        if (!isVoldaan(percentage, netwerk)) {
            maakCombinatie(percentage);
        } else {
            kostenroof = berekenTotalePrijs(netwerk);
            ArrayList<NetwerkComponent> ontwerp = maakGoedkoper(percentage, netwerk);
            netwerk = ontwerp;
        }
        return netwerk;
    }

    private void voegVolgendeToe(Class type) {
        if (type.isAssignableFrom(Webserver.class)) { // kijken of de volgende class een webserver of DBServer is
            netwerk.add(leverancier.aanbodWebserver.get(0));
        } else if (type.isAssignableFrom(DBServer.class)) {
            netwerk.add(leverancier.aanbodDBServer.get(0));
        }
    }

    private Boolean isVoldaan(double percentage, ArrayList<NetwerkComponent> netwerk) {
        return (berekenComponent(Firewall.class, netwerk) / 100) * (berekenComponent(LoadBalancer.class, netwerk) / 100) * (berekenComponent(Webserver.class, netwerk) / 100) * berekenComponent(DBServer.class, netwerk) >= percentage;
    }

    private ArrayList<NetwerkComponent> maakGoedkoper(double percentage, ArrayList<NetwerkComponent> netwerk) {
        //een ontwerp maken dat gebaseerd is op netwerk
        ArrayList<NetwerkComponent> ontwerp = netwerk;

        while (!webserversDoorlopen && !DBServersDoorlopen) {
            //for loop om het juiste component te bepalen.
            for (int j = 0; j < leverancier.aanbodWebserver.size() - 1; j++) {
                //goedkoopste component en de volgende in het aanbod is de duurdere
                NetwerkComponent goedkoopsteWS = leverancier.aanbodWebserver.get(0);
                NetwerkComponent duurdereWS = leverancier.aanbodWebserver.get(leverancier.aanbodWebserver.indexOf(goedkoopsteWS) + 1);

                int aantal = hoeveelVanX(goedkoopsteWS, ontwerp);
                //elke goedkoopste component een plaats omhoog zetten
                for (int i = 0; i < aantal; i++) {
                    ontwerp.set(ontwerp.lastIndexOf(goedkoopsteWS), duurdereWS);
                }
                //kijken of het goedkoper is en of het nog wel voldaan is aan het percentage
                //als dit niet zo is dan gaan we eerst weer een goedkope webserver toevoegen
                //als dat nog niet werkt gaan we helemaal terug naar de vorige stap en eindigt de loop
                //precies hetzelfde wordt gedaan bij DBServers
                for (int i = 0; i < hoeveelVanX(duurdereWS, ontwerp); i++) {
                    if (kostenroof < berekenTotalePrijs(ontwerp)) {
                        ontwerp.remove(duurdereWS);
                        if (!isVoldaan(percentage, ontwerp)) {
                            ontwerp.add(goedkoopsteWS);
                            break;
                        }
                    } else if (isVoldaan(percentage, ontwerp)) {
                        kostenroof = berekenTotalePrijs(ontwerp);
                    }
                }
            }
            webserversDoorlopen = true;
            for (int j = 0; j < leverancier.aanbodDBServer.size() - 1; j++) {
                NetwerkComponent goedkoopsteDB = leverancier.aanbodDBServer.get(0);
                NetwerkComponent duurdereDB = leverancier.aanbodDBServer.get(leverancier.aanbodDBServer.indexOf(goedkoopsteDB) + 1);

                if (!DBServersVerzet) {
                    int aantal = hoeveelVanX(goedkoopsteDB, ontwerp);
                    for (int i = 0; i < aantal; i++) {
                        ontwerp.set(ontwerp.lastIndexOf(goedkoopsteDB), duurdereDB);
                    }
                    DBServersVerzet = true;
                }
                int aantalDB = hoeveelVanX(duurdereDB, ontwerp);
                for (int i = 0; i < aantalDB; i++) {
                    if (kostenroof < berekenTotalePrijs(ontwerp)) {
                        ontwerp.remove(duurdereDB);
                        if (!isVoldaan(percentage, ontwerp)) {
                            ontwerp.add(goedkoopsteDB);
                            break;
                        }
                    } else {
                        kostenroof = berekenTotalePrijs(ontwerp);
                    }
                    if (!isVoldaan(percentage, ontwerp)) {
                        ontwerp.add(goedkoopsteDB);
                    }
                }
            }
        }
        //een laatste db server veranderen in de goedkoopste
        //het algoritme wil volgens onze logica het niet veranderen in het algoritme
        ontwerp.set(ontwerp.lastIndexOf(leverancier.aanbodDBServer.get(1)), leverancier.aanbodDBServer.get(0));
        return netwerk;
    }

    //methode om te kijken hoevaak een netwerkcomponent X voorkomt in een ArrayList
    private int hoeveelVanX(NetwerkComponent component, ArrayList<NetwerkComponent> netwerk) {
        int counter = 0;
        for (NetwerkComponent nc : netwerk) {
            if (nc.equals(component)) {
                counter++;
            }
        }
        return counter;
    }

    private double berekenComponent(Class type, ArrayList<NetwerkComponent> netwerk) {
        double beschikbaarheid = 1;
        // voor elke component wordt gekeken of het een webserver is.
        // hierna wordt de formule uitgevoerd voor de beschikbaarheid.
        for (NetwerkComponent nc : netwerk) {
            if (type.isAssignableFrom(nc.getClass())) {
                beschikbaarheid *= (1 - (nc.getBeschikbaarheid() / 100));
            }
        }
        return (1 - beschikbaarheid) * 100;
    }

    //methode voor het berekenen van de totale prijs
    private int berekenTotalePrijs(ArrayList<NetwerkComponent> netwerk) {
        int totalePrijs = 0;
        try {
            for (NetwerkComponent component : netwerk) {
                totalePrijs += component.getPrijs();
            }
        } catch (NullPointerException npe) {
            System.out.println(npe);
        }
        return totalePrijs;
    }
}

