package Applicatie;

public abstract class NetwerkComponent {
    private String naam;
    private int itemID;
    private double prijs;
    private double beschikbaarheidspercentage;

    public NetwerkComponent(int itemID, String naam, double prijs, double beschikbaarheidspercentage) {
        this.itemID = itemID;
        this.naam = naam;
        this.prijs = prijs;
        this.beschikbaarheidspercentage = beschikbaarheidspercentage;
    }

    public String getNaam() {
        return naam;
    }

    public int getItemID() {
        return itemID;
    }

    public double getPrijs() {
        return prijs;
    }

    public String getClassNaam() {
        return this.getClass().getSimpleName();
    }

    public double getBeschikbaarheid() {
        return this.beschikbaarheidspercentage;
    }
}
