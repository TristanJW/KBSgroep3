package Applicatie;

public abstract class NetwerkComponent{
    private String naam;
    private double prijs;
    private double beschikbaarheidspercentage;
    private int onlineTijd;
    private String Ipv4Adres;
    private String Ipv6adres;
    
    public NetwerkComponent(String naam, double prijs, double beschikbaarheidspercentage){
        this.naam = naam;
        this.prijs = prijs;
        this.beschikbaarheidspercentage = beschikbaarheidspercentage;
    }

    public void setOnlineTijd(int onlineTijd) {
        this.onlineTijd = onlineTijd;
    }

    public void setIpv4Adres(String Ipv4Adres) {
        this.Ipv4Adres = Ipv4Adres;
    }

    public void setIpv6adres(String Ipv6adres) {
        this.Ipv6adres = Ipv6adres;
    }
    
    public String getNaam(){
        return naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public String getClassNaam(){
        return this.getClass().getSimpleName();
    } 
    
    public double getBeschikbaarheid(){
        return this.beschikbaarheidspercentage;
    }
}
