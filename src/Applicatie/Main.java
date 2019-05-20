package Applicatie;

public class Main {

    public static void main(String[] args) {
        Scherm scherm1 = new Scherm();
//
//        JDBC database = new JDBC();
//
//        ResultSet resultaat = database.dataOphalen("SELECT * From leverancierslijst");
//        try {
//            while (resultaat.next()) {
//                int id = resultaat.getInt("Itemid");
//                String naam = resultaat.getString("naam");
//
//                System.out.println("ID:" + id + " naam:" + naam);
//            }
//        } catch (SQLException se) {
//            se.printStackTrace();
//        }
//
//        LeveranciersLijst leverancier = new LeveranciersLijst();
//        ArrayList<NetwerkComponent> servers = leverancier.aanbodWebserver;
//
//        for (NetwerkComponent s : leverancier.aanbodWebserver) {
//            System.out.println(s.getNaam());
//        }

        HuidigeConfiguratie netwerk = new HuidigeConfiguratie();
        Algoritme algoritme = new Algoritme(netwerk);
        algoritme.maakCombinatie(99.99);
        System.out.println(netwerk.berekenTotalePrijs());
    }
}
