package Applicatie;

import java.util.ArrayList;
import java.math.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

public class HuidigeConfiguratie {

    private ArrayList<NetwerkComponent> netwerkLijst;
    private LeveranciersLijst leverancier = new LeveranciersLijst();

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
        return (berekenFirewall() / 100) * (berekenLoadbalancer() / 100) * (berekenWebservers() / 100) * berekenDBservers();
    }

    public void maakCombinatie(double percentage) {
        // kijken of netwerklijst al een firewall, loadbalancer, webserver of dbserver heeft.
        if (netwerkLijst.isEmpty()) {
            voegToe(leverancier.aanbodFirewall.get(0));
            voegToe(leverancier.aanbodLoadBalancer.get(0));
            voegToe(leverancier.aanbodWebserver.get(0));
            voegToe(leverancier.aanbodDBServer.get(0));
        } else {// als er al van alle componenten één aanwezig is dan wordt dit algoritme doorgelopen.
           if (berekenWebservers() < berekenDBservers()) { // kijken of we een webserver of een database server nodig is.
                voegVolgendeToe(Webserver.class);
                // 1 > 2 > 1,1 > 3 > 2,1 > 2,2 > 1,1,1 > 2,1,1 > 2,3 > 1,1,1,1 > 3,2 > 3,1,1 > 3,3 > 1,1,1,1,1
           }else {
               voegVolgendeToe(DBServer.class);
           }
        }
        for (NetwerkComponent nc : netwerkLijst) {
            System.out.print(nc.getNaam() + " ");
        }
        System.out.print(berekenBeschikbaarheid()+ " ");
        System.out.print(berekenTotalePrijs());
        System.out.println();
        // als het opgegeven percentage niet behaald is dan gaat het algoritme verder opzoek.
        if (!isVoldaan(percentage)) {
            maakCombinatie(percentage);
        }
    }
    
    //methode om de goedkoopste volgende component te bepalen
    public void goedkoopsteVolgendeOptie(ArrayList<NetwerkComponent> aanbod){
        
    }
    
    private void voegVolgendeToe(Class type){
        int[] posities = welkePositie(type); // array met alle posities van Webserver of DBServer
        if(type.isAssignableFrom(Webserver.class)){ // kijken of de volgende class een webserver of DBServer is
            if(!isAllesLaatste(posities)){ // kijken of alle waardes op de max staan
                for(int i=0; i < posities.length; i++){
                    if(!isLaatste(netwerkLijst.get(posities[i]))){ // als de huidige positie niet op max staat dan wordt de huidige vervangen door de volgende entry
                        vervang(leverancier.aanbodWebserver.get(leverancier.aanbodWebserver.indexOf(netwerkLijst.get(posities[i]))+1),posities[i]);
                        break; // we willen niet dat alles direct wordt toegevoegd
                    }
                }
            }else{// als alle waardes op max staan vind er vertakking plaats
                for(int i =0; i < posities.length; i++){ // alle huidige waardes op het laagst zetten.
                    vervang(leverancier.aanbodWebserver.get(0), posities[i]);
                }
                voegToe(leverancier.aanbodWebserver.get(0)); // naar de volgende tak
            }
        }else if (type.isAssignableFrom(DBServer.class)){
            if(!isAllesLaatste(posities)){
                for(int i=0; i < posities.length; i++){
                    if(!isLaatste(netwerkLijst.get(posities[i]))){
                        vervang(leverancier.aanbodDBServer.get(leverancier.aanbodDBServer.indexOf(netwerkLijst.get(posities[i]))+1),posities[i]);
                        break;
                    }
                }
            }else{
                for(int i =0; i < posities.length; i++){
                    vervang(leverancier.aanbodDBServer.get(0), posities[i]);
                }
                voegToe(leverancier.aanbodDBServer.get(0));
            }
        }
    }

    private int[] welkePositie(Class type){
        //kijken hoe groot de array moet worden
        int aantal = 0;
        for(NetwerkComponent nc: netwerkLijst){
            if(type.isAssignableFrom(nc.getClass())){
                aantal++;
            }
        }
        int[] posities = new int[aantal];
        aantal = 0; // houd de positie van de array bij
        int counter = 0; // telt het aantal posities
        for(NetwerkComponent nc : netwerkLijst){
            if(type.isAssignableFrom(nc.getClass())){
                posities[aantal]=counter;
                aantal++;
            }
            counter++;
        }
        return posities;
    }
    
    private Boolean isAllesLaatste(int[] posities){
        Boolean isAllesLaatste = false;
        for(int i=0; i< posities.length; i++){
            if(netwerkLijst.get(posities[i]) instanceof Webserver){
                if(isLaatste(netwerkLijst.get(posities[i]))){
                    isAllesLaatste = true;
                }else {
                    isAllesLaatste = false;
                    break;
                }
            }else if (netwerkLijst.get(posities[i]) instanceof DBServer){
                if(isLaatste(netwerkLijst.get(posities[i]))){
                    isAllesLaatste = true;
                }else {
                    isAllesLaatste = false;
                    break;
                }
            }
        }
        return isAllesLaatste;
    }

    // kijken of een object de laatste van het aanbod van servers is.
    private Boolean isLaatste(NetwerkComponent component) {
        Boolean isLaatste = false;
            if(component instanceof Webserver){
                isLaatste = component == leverancier.aanbodWebserver.get(leverancier.aanbodWebserver.size()-1);
            }else if (component instanceof DBServer){
                isLaatste = component == leverancier.aanbodDBServer.get(leverancier.aanbodDBServer.size()-1);
            }
        return isLaatste;
    }

    public Boolean isVoldaan(double percentage) {
        return berekenBeschikbaarheid() >= percentage;
    }

    public double berekenWebservers() {
        double beschikbaarheid = 1;

        // voor elke component wordt gekeken of het een webserver is.
        // hierna wordt de formule uitgevoerd voor de beschikbaarheid.

        for (NetwerkComponent nc : netwerkLijst) {
            if (nc instanceof Webserver) {
                beschikbaarheid *= (1 - (nc.getBeschikbaarheid() / 100));
            }
        }
        return (1 - beschikbaarheid) * 100;
    }

    public double berekenDBservers() {
        double beschikbaarheid = 1;

        // voor elke component wordt gekeken of het een DBServer is.
        // hierna wordt de formule uitgevoerd voor de beschikbaarheid.
        for (NetwerkComponent nc : netwerkLijst) {
            if (nc instanceof DBServer) {
                beschikbaarheid *= (1 - (nc.getBeschikbaarheid() / 100));
            }
        }
        return (1 - beschikbaarheid) * 100;

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

