package Applicatie;

public abstract class Server extends NetwerkComponent{
  private int processorBelasting;
  private int schijfruimte;

    public Server(String naam, double prijs, double beschikbaarheidspercentage) {
        super(naam, prijs, beschikbaarheidspercentage);
    }  

    public int getProcessorBelasting() {
        return processorBelasting;
    }

    public int getSchijfruimte() {
        return schijfruimte;
    }



}
