/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Applicatie;

import static Applicatie.ConfiguratiePanel.xcordsdbserver;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author edith
 */
public class Tekenpanel extends JPanel {
    private ConfiguratiePanel configuratiePanel;
    private boolean dbserver = false;
    
    public void paintComponent(Graphics g) {
              super.paintComponent(g);
              g.setColor(Color.black);
              g.drawRect(0, 0, 600, 470);
    }
  
    public Tekenpanel (ConfiguratiePanel configuratiePanel) {
        setLayout(null);
//    if(configuratiePanel.dbserver == true) {
//        System.out.println("test");
//            configuratiePanel.netwerk.voegToe(LeveranciersLijst.aanbodDBServer.get(configuratiePanel.dbserverdropdown.getSelectedIndex())); // voegt het component toe aan de array met de configuratie
//
//            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("resources/dbserver.png")); // zorgt ervoor dat de png op imageicon geplaatst word
//            JLabel dbserverlabel = new JLabel(imageIcon); // maakt een label met de imageicon die hierboven aangemaakt is
//            JLabel dbservertekstlabel = new JLabel(LeveranciersLijst.aanbodDBServer.get(configuratiePanel.dbserverdropdown.getSelectedIndex()).getNaam()); // tekst onder het icon dat weergeeft welke type server het is
//            dbserverlabel.setBounds(xcordsdbserver, 25, 100, 80); //xcords zijn geinitialiseerd boven aan de class, dit geeft de coordinaten voor de label die geplaatst word als je op een knop drukt
//            dbservertekstlabel.setBounds(xcordsdbserver + 10, 95, 100, 15); //xcords zijn geinitialiseerd boven aan de class, dit geeft de coordinaten voor de label die geplaatst word als je op een knop drukt
//            xcordsdbserver = configuratiePanel.xcordsdbserver + 75; // elke keer als er op een knop word gedrukt gaat de volgende afbeelding 75 pixels naar de zijkant zodat het netjes op een rijtje staat
//            add(dbserverlabel);
//            add(dbservertekstlabel);
//        } else {
//        System.out.println("niks");
//    }
    }
}
    

