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
        
        return (berekenFirewall()/100)*(berekenLoadbalancer()/100)*(berekenWebservers()/100)*berekenDBservers();
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
        
        if (!isVoldaan(percentage,netwerkLijst)){
            
                //als het opgeggeven percentage nog niet bereikt is voegen we servers toe.
                while(hoogstepercentage < percentage){
                    if(berekenWebservers() < berekenDBservers()){
                        netwerkLijst.add(leverancier.aanbodWebserver.get(0));
                    }else {
                        netwerkLijst.add(leverancier.aanbodDBServer.get(0));
                    }
                    hoogstepercentage = berekenBeschikbaarheid(netwerkLijst);
                    System.out.println(hoogstepercentage);
                    
                    for(NetwerkComponent nc : netwerkLijst){
                        System.out.println(nc.getNaam());
                    }
                    break;
                }
                
            }
        }

    private Boolean isErin(String s, ArrayList<NetwerkComponent> aanbod) {
        return netwerkLijst.contains(zoekOpNaam(s, aanbod));
    }

    public Boolean isVoldaan(double percentage, ArrayList<NetwerkComponent> aanbod) {
        return berekenBeschikbaarheid(aanbod) >= percentage;
    }
    
    public double berekenWebservers(){
        double beschikbaarheid = 1;
        
        //voor elke component wordt gekeken of het een webserver is.
        //hierna wordt de formule uitgevoerd voor de beschikbaarheid.
        for (NetwerkComponent nc: netwerkLijst){
                if(nc instanceof Webserver){
                 BigDecimal bdWS = new BigDecimal(nc.getBeschikbaarheid() / 100).setScale(2, RoundingMode.HALF_EVEN);

                 if(beschikbaarheid ==1 ){
                     beschikbaarheid = 1-(1-bdWS.doubleValue());
                 }else {
                     beschikbaarheid*=bdWS.doubleValue();
                 }
            }
        }
        return beschikbaarheid*100;
    }
    
    public double berekenDBservers(){
        double beschikbaarheid = 1;
        
        //voor elke component wordt gekeken of het een DBServer is.
        //hierna wordt de formule uitgevoerd voor de beschikbaarheid.
        for (NetwerkComponent nc: netwerkLijst){
            if(nc instanceof DBServer){
                BigDecimal bdWS = new BigDecimal(nc.getBeschikbaarheid()/100).setScale(2, RoundingMode.HALF_EVEN);

                if(beschikbaarheid ==1 ){
                    beschikbaarheid = 1-(1-bdWS.doubleValue());
                }else {
                    beschikbaarheid*=bdWS.doubleValue();
                }
            }
        }
        return beschikbaarheid*100;
        
    }
    
    public double berekenFirewall(){
        double beschikbaarheid = 0;
        
        for(NetwerkComponent nc : netwerkLijst){
            if(nc instanceof Firewall){
                beschikbaarheid = nc.getBeschikbaarheid();
            }
        }
        return beschikbaarheid;
    }
    
    public double berekenLoadbalancer(){
        double beschikbaarheid = 0;
        
        for(NetwerkComponent nc : netwerkLijst){
            if (nc instanceof LoadBalancer){
                beschikbaarheid = nc.getBeschikbaarheid();
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
}
