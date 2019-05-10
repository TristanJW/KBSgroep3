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

    public double berekenBeschikbaarheid(LeveranciersLijst leverancier) {
        int echteBeschikbaarheid = 0;

        ArrayList<NetwerkComponent> combinatie = new ArrayList();
//        ArrayList<NetwerkComponent> aanbod = leverancier.getAanbod();

        //combinatie.add(aanbod);
        return echteBeschikbaarheid;
    }

}
