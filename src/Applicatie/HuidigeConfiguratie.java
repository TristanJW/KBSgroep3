package Applicatie;

import java.util.ArrayList;

public class HuidigeConfiguratie {

    private ArrayList<NetwerkComponent> netwerkLijst;
    private LeveranciersLijst leverancier = new LeveranciersLijst();

    public HuidigeConfiguratie() {
        this.netwerkLijst = new ArrayList<NetwerkComponent>();
    }

    public void voegToe(NetwerkComponent component) {
        netwerkLijst.add(component);
    }
    
    public void vervang(NetwerkComponent component, int index){
        netwerkLijst.set(index,component);
    }

    // tijdelijke methode die ik even nodig had in ConfiguratiePanel todo remove if not needed anymore
    public ArrayList<NetwerkComponent> returnConfig() {
        return netwerkLijst;
    }

    public double berekenBeschikbaarheid() {
        return (berekenFirewall() / 100) * (berekenLoadbalancer() / 100) * (berekenWebservers() / 100) * berekenDBservers();
    }

    public void maakCombinatie(double percentage) {
        //kijken of netwerklijst al een firewall, loadbalancer, webserver of dbserver heeft.
        if(netwerkLijst.isEmpty()){
            voegToe(leverancier.aanbodFirewall.get(0));
            voegToe(leverancier.aanbodLoadBalancer.get(0));
            voegToe(leverancier.aanbodWebserver.get(0));
            voegToe(leverancier.aanbodDBServer.get(0));
        }else{// als er al van alle componenten één aanwezig is dan wordt dit algoritme doorgelopen.
            
            if(berekenWebservers() < berekenDBservers()){ // kijken of we een webserver of een database server nodig is.
                if(!isLaatste(Webserver.class)){ // kijken of er een vertakking plaats vind
                    int plaatsVanWebserver = vindLaatsteInstantie(Webserver.class);
                    NetwerkComponent component = leverancier.aanbodWebserver.get(leverancier.aanbodWebserver.indexOf(netwerkLijst.get(plaatsVanWebserver))+1);
                    vervang(component, plaatsVanWebserver);
                }else{// als het het laatste webserver van de aanbod lijst is dan komt er een vertakking
                    int plaatsVanWebserver = vindLaatsteInstantie(Webserver.class);
                    NetwerkComponent component = leverancier.aanbodWebserver.get(0);
                    vervang(component,plaatsVanWebserver);
                    voegToe(leverancier.aanbodWebserver.get(0));
                }
            }else { // voeg een DBServer toe
                if(!isLaatste(DBServer.class)){
                    int plaatsVanDBServer = vindLaatsteInstantie(DBServer.class);
                    //nieuwe component is het volgende component in de aanbodlijst
                    NetwerkComponent component = leverancier.aanbodDBServer.get(leverancier.aanbodDBServer.indexOf(netwerkLijst.get(plaatsVanDBServer))+1);
                    vervang(component, plaatsVanDBServer);
                }else{
                    int plaatsVanDBServer = vindLaatsteInstantie(DBServer.class);
                    NetwerkComponent component = leverancier.aanbodDBServer.get(leverancier.aanbodDBServer.indexOf(netwerkLijst.get(plaatsVanDBServer)));
                    vervang(component,plaatsVanDBServer);
                    voegToe(leverancier.aanbodDBServer.get(0));
                }
            }
        }
        for(NetwerkComponent nc: netwerkLijst){
                        System.out.print(nc.getNaam() + " ");
                    }
        System.out.print(berekenBeschikbaarheid());
        System.out.println();
        //als het opgegeven percentage niet behaald is dan gaat het algoritme verder opzoek.
        if(!isVoldaan(percentage)){
           maakCombinatie(percentage);
        }else {
            
        }
        
        //kijk welke type servers moeten worden toegevoegd
        //voldoet het aan het percentage
    }
    
    private int vindLaatsteInstantie(Class type){
        int index = 0;
        //voor elke instatie kijken of het van Type is dan wordt de index het index nummer van het netwerkcomponent
        for(NetwerkComponent nc: netwerkLijst){
            if(type.isAssignableFrom(nc.getClass())){
                index = netwerkLijst.indexOf(nc);
            }
        }
        return index;
    }
    
    //kijken of het laatste instantie in de netwerklijst gelijk staat aan het laatste instantie van de aanbodlijst
    private Boolean isLaatste(Class type){
        Boolean isLaatst = null;
        
        //als de laatste instantie van een netwerkcomponent bijvoorbeeld een webserver op de duurste server staat.
        //return true
        if(netwerkLijst.get(vindLaatsteInstantie(type)) instanceof Webserver){
            isLaatst = netwerkLijst.get(vindLaatsteInstantie(type)) == leverancier.aanbodWebserver.get(leverancier.aanbodWebserver.size()-1);
        }else if(netwerkLijst.get(vindLaatsteInstantie(type)) instanceof DBServer){
            isLaatst = netwerkLijst.get(vindLaatsteInstantie(type)) == leverancier.aanbodDBServer.get(leverancier.aanbodDBServer.size()-1);
        }
        return isLaatst;
    }
    
    private Boolean isErin(String s, ArrayList<NetwerkComponent> aanbod) {
        return netwerkLijst.contains(zoekOpNaam(s, aanbod));
    }

    public Boolean isVoldaan(double percentage) {
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
        double beschikbaarheid = 1;

        for (NetwerkComponent nc : netwerkLijst) {
            if (nc instanceof Firewall) {
                beschikbaarheid *= (1-(nc.getBeschikbaarheid()/100));
            }
        }
        return (1-beschikbaarheid)*100;
    }

    public double berekenLoadbalancer() {
        double beschikbaarheid = 1;

        for (NetwerkComponent nc : netwerkLijst) {
            if (nc instanceof LoadBalancer) {
                beschikbaarheid *= (1-(nc.getBeschikbaarheid()/100));
            }
        }
        return (1-beschikbaarheid)*100;
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
