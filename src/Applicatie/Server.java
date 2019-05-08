package Applicatie;

public abstract class Server extends NetwerkComponent{
  private int processorBelasting;
  private int schijfruimte;

public Server(String naam, double prijs, double beschikbaarheidspercentage, int processorBelasting, int schijfruimte) {
   // super(naam, prijs, beschikbaarheidspercentage);
    this.processorBelasting = processorBelasting;
    this.schijfruimte = schijfruimte;
}  

    public int getProcessorBelasting() {
        return processorBelasting;
    }

    public int getSchijfruimte() {
        return schijfruimte;
    }



}
