package Applicatie;

import java.util.ArrayList;
import java.sql.*;

public class LeveranciersLijst{
    private static ArrayList<NetwerkComponent> aanbod;

    public LeveranciersLijst() {
        aanbod = new ArrayList<>(); //initalisatie
        
        //netwerkcomponenten maken die de leverancier heeft
        //we gebruiken hiervoor een sql querry aan de hand van het type wordt bepaalt welke constructor
        //wordt toegepast
        try{
            ResultSet resultaat = JDBC.dataOphalen("SELECT Naam, prijs, beschikbaarheid, type FROM leverancierslijst");
            while(resultaat.next()){
                
            String naam = resultaat.getString("Naam");
            double prijs = resultaat.getDouble("prijs");
            double beschikbaarheid = resultaat.getDouble("beschikbaarheid");
            String type = resultaat.getString("type");
                
                //type check
                if (type.equals("Webserver")){
                    Webserver server = new Webserver(naam,prijs,beschikbaarheid);
                    aanbod.add(server);
                }else if (type.equals("DBServer")){
                    DBServer database = new DBServer(naam,prijs,beschikbaarheid);
                    aanbod.add(database);    
                }else if (type.equals("firewall")){
                    Firewall firewall = new Firewall(naam,prijs,beschikbaarheid);
                    aanbod.add(firewall);
                }else if (type.equals("loadbalancer")){
                    LoadBalancer loadbalancer = new LoadBalancer(naam,prijs,beschikbaarheid);
                    aanbod.add(loadbalancer);
                }
            }    
        }catch(SQLException se){
            se.getStackTrace();
        }   
    }  

    public static ArrayList<NetwerkComponent> getAanbod() {
        return aanbod;
    }
   
}
