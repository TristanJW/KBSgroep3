package Applicatie;

public abstract class Server extends NetwerkComponent {

    public Server(int itemID, String naam, double prijs, double beschikbaarheidspercentage) {
        super(itemID, naam, prijs, beschikbaarheidspercentage);
    }


}
