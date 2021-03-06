package Applicatie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ConfiguratiePanel extends JPanel implements ActionListener {

    HuidigeConfiguratie netwerk;
    private LeveranciersLijst leverancier = new LeveranciersLijst();

    private ArrayList<NetwerkComponent> alletypescomponenten;

    //// BUTTONS ////
    private JButton dbserverbutton = new JButton();
    private JButton firewallbutton = new JButton();
    private JButton loadbalancerbutton = new JButton();
    private JButton webserverbutton = new JButton();
    //// BUTTONS ////
    JButton opslaanbutton = new JButton();
    JButton laadbutton = new JButton();
    private JButton leegmaakbutton = new JButton();
    //// LABELS ////
    private JLabel totaleprijslabel;
    private JLabel totaleuptimelabel;
    //// DROPDOWNS ////
    private JComboBox dbserverdropdown = new JComboBox(leverancier.zoekNaam("dbserver", leverancier.aanbodDBServer));
    private JComboBox firewalldropdown = new JComboBox(leverancier.zoekNaam("firewall", leverancier.aanbodFirewall));
    private JComboBox loadbalancerdropdown = new JComboBox(leverancier.zoekNaam("loadbalancer", leverancier.aanbodLoadBalancer));
    private JComboBox webserverdropdown = new JComboBox(leverancier.zoekNaam("webserver", leverancier.aanbodWebserver));

    private TekenPanel tekenp = new TekenPanel(); //deel waarin de aangeklikte componenten komen binnen de zwarte rand

    ConfiguratiePanel() {
        netwerk = new HuidigeConfiguratie();
        setLayout(null);

        //// LABELS ////
        JLabel hoofdtekst = new JLabel("Klik op de knoppen om toe te voegen | Klik op een toegevoegd component om te verwijderen");
        JLabel dbserverlabel = new JLabel("DBserver");
        JLabel firewalllabel = new JLabel("Firewall");
        JLabel loadbalancerlabel = new JLabel("Loadbalancer");
        JLabel webserverlabel = new JLabel("Webserver");
        totaleprijslabel = new JLabel(netwerk.berekenTotalePrijs());
        totaleuptimelabel = new JLabel("Geen beschikbaarheid");

        //panel waarin de aangeklikte componenten komen te staan binnen de zwarte rechthoek
        tekenp.setSize(500, 400);
        tekenp.setBounds(150, 25, 605, 500);
        add(tekenp);

        try {
            Image DBserver = ImageIO.read(getClass().getResource("resources/dbserver.png")); // zorgt ervoor dat de png op imageicon geplaatst word
            dbserverbutton.setIcon(new ImageIcon(DBserver)); // plaats de imageicon in een button
            dbserverbutton.addActionListener(this); //zorgt ervoor dat de actionlistener kan zien of deze knop is geklikt met e.getsource
            dbserverbutton.setBounds(5, 25, 135, 80);

            Image firewall = ImageIO.read(getClass().getResource("resources/firewall.png"));
            firewallbutton.setIcon(new ImageIcon(firewall));
            firewallbutton.setBounds(5, 150, 135, 80);
            firewallbutton.addActionListener(this);

            Image loadbalancer = ImageIO.read(getClass().getResource("resources/loadbalancer.png"));
            loadbalancerbutton.setIcon(new ImageIcon(loadbalancer));
            loadbalancerbutton.setBounds(5, 275, 135, 80);
            loadbalancerbutton.addActionListener(this);

            Image webserver = ImageIO.read(getClass().getResource("resources/webserver.png"));
            webserverbutton.setIcon(new ImageIcon(webserver));
            webserverbutton.setBounds(5, 400, 135, 80);
            webserverbutton.addActionListener(this);

            // locaties van combo boxes (dropdowns)
            dbserverdropdown.setBounds(5, 105, 135, 20);
            firewalldropdown.setBounds(5, 230, 135, 20);
            loadbalancerdropdown.setBounds(5, 355, 135, 20);
            webserverdropdown.setBounds(5, 480, 135, 20);

            // zet de labels en buttons op de locatie (x-as, y-as, breedte, hoogte)
            dbserverlabel.setBounds(40, 10, 100, 10);
            firewalllabel.setBounds(40, 135, 100, 10);
            loadbalancerlabel.setBounds(30, 260, 100, 10);
            webserverlabel.setBounds(40, 385, 100, 10);
            hoofdtekst.setBounds(200, 5, 600, 15);
            totaleprijslabel.setBounds(50, 530, 400, 15);
            totaleuptimelabel.setBounds(50, 545, 400, 15);
            add(totaleprijslabel);
            add(totaleuptimelabel);

            //voegt de 3 buttons onderaan toe met de locatie
            opslaanbutton.setBounds(600, 550, 150, 20);
            laadbutton.setBounds(425, 550, 150, 20);
            leegmaakbutton.setBounds(250, 550, 150, 20);
            opslaanbutton.setText("Opslaan");
            add(opslaanbutton);
            laadbutton.setText("Configuratie laden");
            add(laadbutton);
            leegmaakbutton.setText("Leeg maken");
            add(leegmaakbutton);

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
            leegmaakbutton.addActionListener(this);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void tekenComponenten(HuidigeConfiguratie netwerk) {
        int xcordsdbserver = 5;
        int xcordsfirewall = 5;
        int xcordsloadbalancer = 5;
        int xcordswebserver = 5;
        tekenp.removeAll();
        setAlletypescomponenten();

        for (NetwerkComponent component : alletypescomponenten) {
            if (component instanceof DBServer) {
                JLabel dbserverAantalLabel = new JLabel(String.valueOf(aantalVanX(component))); // methode voor aantal van X , X is een type DBServer
                ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("resources/dbserver.png")); // zorgt ervoor dat de png op imageicon geplaatst word
                JLabel dbserverImage = new JLabel(imageIcon); // maakt een label met de imageicon die hierboven aangemaakt is
                JLabel dbservertekstlabel = new JLabel(component.getNaam()); // tekst onder het icon dat weergeeft welke type server het is

                dbserverImage.setBounds(xcordsdbserver, 0, 100, 80); //xcords zijn geinitialiseerd boven aan de class, dit geeft de coordinaten voor de label die geplaatst word als je op een knop drukt
                dbservertekstlabel.setBounds(xcordsdbserver + 10, 75, 100, 15); //xcords zijn geinitialiseerd boven aan de class, dit geeft de coordinaten voor de label die geplaatst word als je op een knop drukt
                dbserverAantalLabel.setBounds(xcordsdbserver, 10, 100, 80);
                xcordsdbserver += 85; // elke keer als er op een knop word gedrukt gaat de volgende afbeelding 75 pixels naar de zijkant zodat het netjes op een rijtje staat

                dbserverImage.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        netwerk.verwijderComponent(component);
                        tekenComponenten(netwerk);
                    }
                });
                tekenp.add(dbserverAantalLabel);
                tekenp.add(dbserverImage);
                tekenp.add(dbservertekstlabel);
            }
            if (component instanceof Firewall) {
                JLabel firewallAantalLabel = new JLabel(String.valueOf(aantalVanX(component)));
                ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("resources/firewall.png"));
                JLabel firewallImage = new JLabel(imageIcon);
                JLabel firewalltekstlabel = new JLabel(component.getNaam());
                firewallAantalLabel.setBounds(xcordsfirewall, 135, 100, 80);
                firewallImage.setBounds(xcordsfirewall, 125, 100, 80);
                firewalltekstlabel.setBounds(xcordsfirewall + 25, 195, 100, 15);
                xcordsfirewall += 85;

                firewallImage.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        netwerk.verwijderComponent(component);
                        tekenComponenten(netwerk);
                    }
                });
                tekenp.add(firewallAantalLabel);
                tekenp.add(firewallImage);
                tekenp.add(firewalltekstlabel);
            }
            if (component instanceof LoadBalancer) {
                JLabel loadbalancerAantalLabel = new JLabel(String.valueOf(aantalVanX(component)));
                ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("resources/loadbalancer.png"));
                JLabel loadbalancerImage = new JLabel(imageIcon);
                JLabel loadbalancertekstlabel = new JLabel(component.getNaam());
                loadbalancerAantalLabel.setBounds(xcordsloadbalancer, 260, 100, 80);
                loadbalancerImage.setBounds(xcordsloadbalancer, 250, 100, 80);
                loadbalancertekstlabel.setBounds(xcordsloadbalancer + 20, 320, 100, 20);
                xcordsloadbalancer += 85;

                loadbalancerImage.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        netwerk.verwijderComponent(component);
                        tekenComponenten(netwerk);
                    }
                });
                tekenp.add(loadbalancerAantalLabel);
                tekenp.add(loadbalancerImage);
                tekenp.add(loadbalancertekstlabel);
            }
            if (component instanceof Webserver) {
                JLabel webserverAantalLabel = new JLabel(String.valueOf(aantalVanX(component)));
                ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("resources/webserver.png"));
                JLabel webserverImage = new JLabel(imageIcon);
                JLabel webservertekstlabel = new JLabel(component.getNaam());
                webserverAantalLabel.setBounds(xcordswebserver, 385, 100, 80);
                webserverImage.setBounds(xcordswebserver, 375, 100, 80);
                webservertekstlabel.setBounds(xcordswebserver + 15, 440, 100, 20);
                xcordswebserver += 85;
                webserverImage.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        netwerk.verwijderComponent(component);
                        tekenComponenten(netwerk);
                    }
                });
                tekenp.add(webserverAantalLabel);
                tekenp.add(webserverImage);
                tekenp.add(webservertekstlabel);
            }

            totaleprijslabel.setText(netwerk.berekenTotalePrijs()); // update de totale prijs
            if (netwerk.berekenBeschikbaarheid() == 0) {
                totaleuptimelabel.setText("Geen beschikbaarheid");
            } else {
                totaleuptimelabel.setText(netwerk.berekenBeschikbaarheid() + " %");// update de totale uptime
            }
        }
        repaint();
    }

    private void setAlletypescomponenten() {
        alletypescomponenten = new ArrayList<>();
        for (NetwerkComponent component : netwerk.getNetwerkLijst()) {
            if (!heeftArrayX(component, alletypescomponenten)) {
                alletypescomponenten.add(component);
            }
        }
    }

    private Boolean heeftArrayX(NetwerkComponent component, ArrayList<NetwerkComponent> netwerk) {
        boolean heeftArrayX = false;
        for (NetwerkComponent nc : netwerk) {
            //zit er al in
            if (nc.getNaam().equals(component.getNaam())) {
                heeftArrayX = true;
                break;
            }
        }
        return heeftArrayX;
    }

    private int aantalVanX(NetwerkComponent component) {
        int counter = 0;
        for (NetwerkComponent nc : netwerk.getNetwerkLijst()) {
            if (nc.getNaam().equals(component.getNaam())) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dbserverbutton) { // e.getSource==buttonnaam kijkt naar of deze button geklikt is, zo ja dan runt de code van deze if, zo nee gaat het naar de volgende else if.
            netwerk.voegToe(LeveranciersLijst.aanbodDBServer.get(dbserverdropdown.getSelectedIndex())); // voegt het component toe aan de array met de configuratie
        } else if (e.getSource() == firewallbutton) {
            netwerk.voegToe(LeveranciersLijst.aanbodFirewall.get(firewalldropdown.getSelectedIndex())); // voegt het component toe aan de array met de configuratie
        } else if (e.getSource() == loadbalancerbutton) {
            netwerk.voegToe(LeveranciersLijst.aanbodLoadBalancer.get(loadbalancerdropdown.getSelectedIndex())); // voegt het component toe aan de array met de configuratie
        } else if (e.getSource() == webserverbutton) {
            netwerk.voegToe(LeveranciersLijst.aanbodWebserver.get(webserverdropdown.getSelectedIndex())); // voegt het component toe aan de array met de configuratie
        } else if (e.getSource() == leegmaakbutton) {
            netwerk.getNetwerkLijst().clear();
            tekenp.removeAll();
            totaleprijslabel.setText("0 Euro");
            totaleuptimelabel.setText("Geen beschikbaarheid");
            repaint();
        }

        // het volgende wordt op iedere button klik uitgevoerd en hoeft dus niet steeds in iedere IF herhaalt te worden (lijkt me?)
        tekenComponenten(netwerk);
    }
}
