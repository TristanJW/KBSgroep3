package Applicatie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ConfiguratiePanel extends JPanel implements ActionListener {

    private HuidigeConfiguratie netwerk = new HuidigeConfiguratie();
    private LeveranciersLijst leverancier = new LeveranciersLijst();

    static int xcordsdbserver = 125;
    static int xcordsfirewall = 125;
    static int xcordsloadbalancer = 125;
    static int xcordswebserver = 125;

    //// BUTTONS ////
    JButton dbserverbutton = new JButton();
    JButton firewallbutton = new JButton();
    JButton loadbalancerbutton = new JButton();
    JButton webserverbutton = new JButton();
    //// BUTTONS ////
    JButton opslaanbutton = new JButton();
    JButton laadbutton = new JButton();
    JButton doorgaanbutton = new JButton();
    //// LABELS ////
    JLabel hoofdtekst = new JLabel("Klik op de componenten die gebruikt moeten worden");
    JLabel dbserverlabel = new JLabel("DBserver");
    JLabel firewalllabel = new JLabel("Firewall");
    JLabel loadbalancerlabel = new JLabel("Loadbalancer");
    JLabel webserverlabel = new JLabel("Webserver");
    JLabel totaleprijslabel = new JLabel(netwerk.berekenTotalePrijs());
    JLabel totaleuptimelabel = new JLabel(netwerk.berekenBeschikbaarheid(netwerk.returnConfig()) + " %");
    //// DROPDOWNS ////
    JComboBox dbserverdropdown = new JComboBox(leverancier.zoekNaam("dbserver", leverancier.aanbodDBServer));
    JComboBox firewalldropdown = new JComboBox(leverancier.zoekNaam("firewall", leverancier.aanbodFirewall));
    JComboBox loadbalancerdropdown = new JComboBox(leverancier.zoekNaam("loadbalancer", leverancier.aanbodLoadBalancer));
    JComboBox webserverdropdown = new JComboBox(leverancier.zoekNaam("webserver", leverancier.aanbodWebserver));

    public ConfiguratiePanel() {
        setLayout(null);
//        Scrollbar ranger = new Scrollbar(Scrollbar.HORIZONTAL, 30, 20, 0, 500);
//        ranger.setBounds(100, 575, 700, 700);
//        this.add(ranger);

        try {
            Image DBserver = ImageIO.read(getClass().getResource("resources/dbserver.png")); // zorgt ervoor dat de png op imageicon geplaatst word
            dbserverbutton.setIcon(new ImageIcon(DBserver)); // plaats de imageicon in een button
            dbserverbutton.addActionListener(this); //zorgt ervoor dat de actionlistener kan zien of deze knop is geklikt met e.getsource
            dbserverbutton.setBounds(25, 25, 100, 80);

            Image firewall = ImageIO.read(getClass().getResource("resources/firewall.png"));
            firewallbutton.setIcon(new ImageIcon(firewall));
            firewallbutton.setBounds(25, 150, 100, 80);
            firewallbutton.addActionListener(this);

            Image loadbalancer = ImageIO.read(getClass().getResource("resources/loadbalancer.png"));
            loadbalancerbutton.setIcon(new ImageIcon(loadbalancer));
            loadbalancerbutton.setBounds(25, 275, 100, 80);
            loadbalancerbutton.addActionListener(this);

            Image webserver = ImageIO.read(getClass().getResource("resources/webserver.png"));
            webserverbutton.setIcon(new ImageIcon(webserver));
            webserverbutton.setBounds(25, 400, 100, 80);
            webserverbutton.addActionListener(this);

            // locaties van combo boxes (dropdowns)
            dbserverdropdown.setBounds(25, 105, 100, 20);
            firewalldropdown.setBounds(25, 230, 100, 20);
            loadbalancerdropdown.setBounds(25, 355, 100, 20);
            webserverdropdown.setBounds(25, 480, 100, 20);

            // zet de labels en buttons op de locatie (x-as, y-as, breedte, hoogte)
            dbserverlabel.setBounds(45, 10, 100, 10);
            firewalllabel.setBounds(50, 135, 100, 10);
            loadbalancerlabel.setBounds(40, 260, 100, 10);
            webserverlabel.setBounds(45, 385, 100, 10);
            hoofdtekst.setBounds(250, 5, 400, 15);
            totaleprijslabel.setBounds(50, 530, 400, 15);
            totaleuptimelabel.setBounds(50, 545, 400, 15);
            add(totaleprijslabel);
            add(totaleuptimelabel);

            //voegt de 3 buttons onderaan toe met de locatie
            opslaanbutton.setBounds(200, 550, 150, 20);
            laadbutton.setBounds(400, 550, 150, 20);
            doorgaanbutton.setBounds(600, 550, 150, 20);
            opslaanbutton.setText("Opslaan");
            add(opslaanbutton);
            laadbutton.setText("Configuratie laden");
            add(laadbutton);
            doorgaanbutton.setText("Doorgaan");
            add(doorgaanbutton);

            // de hoofdtekst bovenaan die toegevoegd word
            add(hoofdtekst);

            // add comboboxes (dropdowna)
            add(dbserverdropdown);
            add(firewalldropdown);
            add(loadbalancerdropdown);
            add(webserverdropdown);
            // de component buttons die worden toegevoegd met de labels (namen) eronder
            add(dbserverlabel);
            add(dbserverbutton);
            add(firewalllabel);
            add(firewallbutton);
            add(loadbalancerlabel);
            add(loadbalancerbutton);
            add(webserverlabel);
            add(webserverbutton);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //todo dit moet beter kunnen????????!!!!!!!!!???????????????
        if (e.getSource() == dbserverbutton) { // e.getSource==buttonnaam kijkt naar of deze button geklikt is, zo ja dan runt de code van deze if, zo nee gaat het naar de volgende else if.
            netwerk.voegToe(LeveranciersLijst.aanbodDBServer.get(dbserverdropdown.getSelectedIndex())); // voegt het component toe aan de array met de configuratie

            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("resources/dbserver.png")); // zorgt ervoor dat de png op imageicon geplaatst word
            JLabel dbserverlabel = new JLabel(imageIcon); // maakt een label met de imageicon die hierboven aangemaakt is
            JLabel dbservertekstlabel = new JLabel(LeveranciersLijst.aanbodDBServer.get(dbserverdropdown.getSelectedIndex()).getNaam()); // tekst onder het icon dat weergeeft welke type server het is
            dbserverlabel.setBounds(xcordsdbserver, 25, 100, 80); //xcords zijn geinitialiseerd boven aan de class, dit geeft de coordinaten voor de label die geplaatst word als je op een knop drukt
            dbservertekstlabel.setBounds(xcordsdbserver + 10, 95, 100, 15); //xcords zijn geinitialiseerd boven aan de class, dit geeft de coordinaten voor de label die geplaatst word als je op een knop drukt
            xcordsdbserver = this.xcordsdbserver + 75; // elke keer als er op een knop word gedrukt gaat de volgende afbeelding 75 pixels naar de zijkant zodat het netjes op een rijtje staat
            add(dbserverlabel);
            add(dbservertekstlabel);

//            System.out.println(netwerk.berekenBeschikbaarheid(netwerk.returnConfig()));
//            netwerk.printConfiguratie(); // todo remove when done testing
        } else if (e.getSource() == firewallbutton) {
            netwerk.voegToe(LeveranciersLijst.aanbodFirewall.get(firewalldropdown.getSelectedIndex())); // voegt het component toe aan de array met de configuratie

            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("resources/firewall.png"));
            JLabel firewalllabel = new JLabel(imageIcon);
            JLabel firewalltekstlabel = new JLabel(LeveranciersLijst.aanbodFirewall.get(firewalldropdown.getSelectedIndex()).getNaam());
            firewalllabel.setBounds(xcordsfirewall, 150, 100, 80);
            firewalltekstlabel.setBounds(xcordsfirewall + 25, 220, 100, 15);
            xcordsfirewall = this.xcordsfirewall + 75;
            add(firewalllabel);
            add(firewalltekstlabel);
        } else if (e.getSource() == loadbalancerbutton) {
            netwerk.voegToe(LeveranciersLijst.aanbodLoadBalancer.get(loadbalancerdropdown.getSelectedIndex())); // voegt het component toe aan de array met de configuratie

            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("resources/loadbalancer.png"));
            JLabel loadbalancerlabel = new JLabel(imageIcon);
            JLabel loadbalancertekstlabel = new JLabel(LeveranciersLijst.aanbodLoadBalancer.get(loadbalancerdropdown.getSelectedIndex()).getNaam());
            loadbalancerlabel.setBounds(xcordsloadbalancer, 275, 100, 80);
            loadbalancertekstlabel.setBounds(xcordsloadbalancer + 15, 345, 70, 20); // todo fix tekst die half afgehakt is
            xcordsloadbalancer = this.xcordsloadbalancer + 75;
            add(loadbalancerlabel);
            add(loadbalancertekstlabel);
        } else if (e.getSource() == webserverbutton) {
            netwerk.voegToe(LeveranciersLijst.aanbodWebserver.get(webserverdropdown.getSelectedIndex())); // voegt het component toe aan de array met de configuratie

            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("resources/webserver.png"));
            JLabel webserverImage = new JLabel(imageIcon);
            JLabel webservertekstlabel = new JLabel(LeveranciersLijst.aanbodWebserver.get(webserverdropdown.getSelectedIndex()).getNaam());
            webserverImage.setBounds(xcordswebserver, 400, 100, 80);
            webservertekstlabel.setBounds(xcordswebserver + 15, 465, 100, 20);
            xcordswebserver = this.xcordswebserver + 75;
            add(webserverImage);
            add(webservertekstlabel);
        }
        // het volgende wordt op iedere button klik uitgevoerd en hoeft dus niet steeds in iedere IF herhaalt te worden (lijkt me?)
        totaleprijslabel.setText(netwerk.berekenTotalePrijs()); // update de totale prijs
        System.out.println(netwerk.returnConfig());
        totaleuptimelabel.setText(netwerk.berekenBeschikbaarheid(netwerk.returnConfig()) + " %"); // update de totale uptime
        repaint(); //zorgt ervoor dat als je op de knop klikt de afbeelding realtime word upgedate waardoor je niet hoeft te refreshen om de afbeelding op het scherm te krijgen
    }
}
