
package Applicatie;

public class Algoritme {
    private HuidigeConfiguratie netwerk;
    private LeveranciersLijst leverancier = new LeveranciersLijst();
    
    public Algoritme(HuidigeConfiguratie netwerk){
        this.netwerk = netwerk;
    }
    
    public void maakCombinatie(double percentage) {
        // kijken of netwerklijst al een firewall, loadbalancer, webserver of dbserver heeft.
        if (netwerk.getNetwerkLijst().isEmpty()) {
            netwerk.voegToe(leverancier.aanbodFirewall.get(0));
            netwerk.voegToe(leverancier.aanbodLoadBalancer.get(0));
            netwerk.voegToe(leverancier.aanbodWebserver.get(0));
            netwerk.voegToe(leverancier.aanbodDBServer.get(0));
        }else {// als er al van alle componenten één aanwezig is dan wordt dit algoritme doorgelopen.
           if (netwerk.berekenComponent(Webserver.class) < netwerk.berekenComponent(DBServer.class)) { // kijken of we een webserver of een database server nodig is.
                voegVolgendeToe(Webserver.class);
                // 1 > 2 > 1,1 > 3 > 2,1 > 2,2 > 1,1,1 > 2,1,1 > 2,3 > 1,1,1,1 > 3,2 > 3,1,1 > 3,3 > 1,1,1,1,1
           }else {
               voegVolgendeToe(DBServer.class);
           }
        }
        // als het opgegeven percentage niet behaald is dan gaat het algoritme verder opzoek.
        if (!isVoldaan(percentage)) {
            maakCombinatie(percentage);
        }
    }
    
    private void voegVolgendeToe(Class type){
        int[] posities = welkePositie(type); // array met alle posities van Webserver of DBServer
        if(type.isAssignableFrom(Webserver.class)){ // kijken of de volgende class een webserver of DBServer is
            if(!isAllesLaatste(posities)){ // kijken of alle waardes op de max staan
                for(int i=0; i < posities.length; i++){
                    if(!isLaatste(netwerk.getNetwerkLijst().get(posities[i]))){ // als de huidige positie niet op max staat dan wordt de huidige vervangen door de volgende entry
                        netwerk.vervang(leverancier.aanbodWebserver.get(leverancier.aanbodWebserver.indexOf(netwerk.getNetwerkLijst().get(posities[i]))+1),posities[i]);
                        break; // we willen niet dat alles direct wordt toegevoegd
                    }
                }
            }else{// als alle waardes op max staan vind er vertakking plaats
                for(int i =0; i < posities.length; i++){ // alle huidige waardes op het laagst zetten.
                    netwerk.vervang(leverancier.aanbodWebserver.get(0), posities[i]);
                }
                netwerk.voegToe(leverancier.aanbodWebserver.get(0)); // naar de volgende tak
            }
        }else if (type.isAssignableFrom(DBServer.class)){
            if(!isAllesLaatste(posities)){
                for(int i=0; i < posities.length; i++){
                    if(!isLaatste(netwerk.getNetwerkLijst().get(posities[i]))){
                        netwerk.vervang(leverancier.aanbodDBServer.get(leverancier.aanbodDBServer.indexOf(netwerk.getNetwerkLijst().get(posities[i]))+1),posities[i]);
                        break;
                    }
                }
            }else{
                for(int i =0; i < posities.length; i++){
                    netwerk.vervang(leverancier.aanbodDBServer.get(0), posities[i]);
                }
                netwerk.voegToe(leverancier.aanbodDBServer.get(0));
            }
        }
    }
    
     public Boolean isVoldaan(double percentage) {
        return netwerk.berekenBeschikbaarheid() >= percentage;
    }
     
     //deze methode geeft alle posities van x in netwerklijst weer.
    //waarbij x het type server is
    private int[] welkePositie(Class type){
        //kijken hoe groot de array moet worden
        int aantal = 0;
        for(NetwerkComponent nc: netwerk.getNetwerkLijst()){
            if(type.isAssignableFrom(nc.getClass())){
                aantal++;
            }
        }
        int[] posities = new int[aantal];
        aantal = 0; // houd de positie van de array bij
        int counter = 0; // telt het aantal posities
        for(NetwerkComponent nc : netwerk.getNetwerkLijst()){
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
            if(netwerk.getNetwerkLijst().get(posities[i]) instanceof Webserver){
                if(isLaatste(netwerk.getNetwerkLijst().get(posities[i]))){
                    isAllesLaatste = true;
                }else {
                    isAllesLaatste = false;
                    break;
                }
            }else if (netwerk.getNetwerkLijst().get(posities[i]) instanceof DBServer){
                if(isLaatste(netwerk.getNetwerkLijst().get(posities[i]))){
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

}
