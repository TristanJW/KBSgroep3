package Applicatie;

import java.util.ArrayList;

public class HuidigeConfiguratie {

    private ArrayList<NetwerkComponent> netwerkLijst;

    public HuidigeConfiguratie() {
        this.netwerkLijst = new ArrayList<NetwerkComponent>();
    }

    public void voegToe(NetwerkComponent component) {
        netwerkLijst.add(component);
    }

    public double berekenBeschikbaarheid() {
        return 0; //3. hier komt de berekening voor de beschikbaarheid
    }

}
