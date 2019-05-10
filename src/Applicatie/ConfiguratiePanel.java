package Applicatie;

import java.awt.Image;
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

    JButton DBserverbutton = new JButton();
    JButton firewallbutton = new JButton();
    JButton loadbalancerbutton = new JButton();
    JButton webserverbutton = new JButton();

    JButton opslaanbutton = new JButton();
    JButton laadbutton = new JButton();
    JButton doorgaanbutton = new JButton();

    JLabel DBserverimage = new JLabel();
    JLabel firewalllimage = new JLabel();
    JLabel loadbalancerimage = new JLabel();
    JLabel webserverimage = new JLabel();

    JLabel hoofdtekst = new JLabel("Klik op de componenten die gebruikt moeten worden");
    JLabel DBserverlabel = new JLabel("DBserver");
    JLabel firewalllabel = new JLabel("Firewall");
    JLabel loadbalancerlabel = new JLabel("Loadbalancer");
    JLabel webserverlabel = new JLabel("Webserver");

//    JComboBox dbservercombo = new JComboBox(leverancier.zoekNaam("dbserver", leverancier.aanbodDBServer));
//    JComboBox firewallcombo = new JComboBox(leverancier.zoekNaam("firewall", leverancier.aanbodDBServer));
//    JComboBox loadbalancercombo = new JComboBox(leverancier.zoekNaam("loadbalancer", leverancier.aanbodDBServer));
//    JComboBox webservercombo = new JComboBox(leverancier.zoekNaam("webserver", leverancier.aanbodDBServer));
    public ConfiguratiePanel() {
        setLayout(null);

        try {

            Image DBserver = ImageIO.read(getClass().getResource("dbserver.png")); // zorgt ervoor dat de png op imageicon geplaatst word
            DBserverbutton.setIcon(new ImageIcon(DBserver)); // plaats de imageicon in een button
            DBserverbutton.addActionListener(this); //zorgt ervoor dat de actionlistener kan zien of deze knop is geklikt met e.getsource
            DBserverbutton.setBounds(25, 25, 100, 100);

            Image firewall = ImageIO.read(getClass().getResource("firewall.png"));
            firewallbutton.setIcon(new ImageIcon(firewall));
            firewallbutton.setBounds(25, 150, 100, 100);
            firewallbutton.addActionListener(this);

            Image loadbalancer = ImageIO.read(getClass().getResource("loadbalancer.png"));
            loadbalancerbutton.setIcon(new ImageIcon(loadbalancer));
            loadbalancerbutton.setBounds(25, 275, 100, 100);
            loadbalancerbutton.addActionListener(this);

            Image webserver = ImageIO.read(getClass().getResource("webserver.png"));
            webserverbutton.setIcon(new ImageIcon(webserver));
            webserverbutton.setBounds(25, 400, 100, 100);
            webserverbutton.addActionListener(this);

            // zet de labels en buttons op de locatie (x-as, y-as, breedte, hoogte)
            DBserverlabel.setBounds(45, 85, 100, 100);
            firewalllabel.setBounds(50, 210, 100, 100);
            loadbalancerlabel.setBounds(40, 335, 100, 100);
            webserverlabel.setBounds(45, 460, 100, 100);
            hoofdtekst.setBounds(250, -30, 400, 100);

            //voegt de 3 buttons onderaan toe met de locatie
            opslaanbutton.setBounds(200, 550, 150, 20);
            laadbutton.setBounds(400, 550, 150, 20);
            doorgaanbutton.setBounds(600, 550, 150, 20);
            opslaanbutton.setText("Opslaan");
            opslaanbutton.addActionListener(this);
            add(opslaanbutton);
            laadbutton.setText("Configuratie laden");
            laadbutton.addActionListener(this);
            add(laadbutton);
            doorgaanbutton.setText("Doorgaan");
            doorgaanbutton.addActionListener(this);
            add(doorgaanbutton);

            // de hoofdtekst bovenaan die toegevoegd word
            add(hoofdtekst);

            // de component buttons die worden toegevoegd met de labels (namen) eronder
            add(DBserverbutton);
            add(DBserverlabel);
            add(firewallbutton);
            add(firewalllabel);
            add(loadbalancerbutton);
            add(loadbalancerlabel);
            add(webserverbutton);
            add(webserverlabel);

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == DBserverbutton) { // e.getSource==buttonnaam kijkt naar of deze button geklikt is, zo ja dan runt de code van deze if, zo nee gaat het naar de volgende else if.

            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("dbserver.png")); // zorgt ervoor dat de png op imageicon geplaatst word
            JLabel dbserverlabel = new JLabel(imageIcon); // maakt een label met de imageicon die hierboven aangemaakt is
            dbserverlabel.setBounds(xcordsdbserver, 25, 100, 100); //xcords zijn geinitialiseerd boven aan de class, dit geeft de coordinaten voor de label die geplaatst word als je op een knop drukt
            xcordsdbserver = this.xcordsdbserver + 75; // elke keer als er op een knop word gedrukt gaat de volgende afbeelding 75 pixels naar de zijkant zodat het netjes op een rijtje staat
            add(dbserverlabel);
            add(DBserverimage);
            repaint(); //zorgt ervoor dat als je op de knop klikt de afbeelding realtime word upgedate waardoor je niet hoeft te refreshen om de afbeelding op het scherm te krijgen
        } else if (e.getSource() == firewallbutton) {

            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("firewall.png"));
            JLabel firewalllabel = new JLabel(imageIcon);
            firewalllabel.setBounds(xcordsfirewall, 150, 100, 100);
            xcordsfirewall = this.xcordsfirewall + 75;
            add(firewalllabel);
            add(firewalllimage);
            repaint();
        } else if (e.getSource() == loadbalancerbutton) {

            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("loadbalancer.png"));
            JLabel loadbalancerlabel = new JLabel(imageIcon);
            loadbalancerlabel.setBounds(xcordsloadbalancer, 275, 100, 100);
            xcordsloadbalancer = this.xcordsloadbalancer + 75;
            add(loadbalancerlabel);
            add(loadbalancerimage);
            repaint();
        } else if (e.getSource() == webserverbutton) {

            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("webserver.png"));
            JLabel webserverlabel = new JLabel(imageIcon);
            webserverlabel.setBounds(xcordswebserver, 400, 100, 100);
            xcordswebserver = this.xcordswebserver + 75;
            add(webserverlabel);
            add(webserverimage);
            repaint();
        } else if (e.getSource() == opslaanbutton) {
//            JDBC database = new JDBC();
//            database.dataOphalen("INSERT INTO"); // moet nog gedaan worden!
            System.out.println("test1");
        } else if (e.getSource() == laadbutton) {
//            JDBC database = new JDBC();
//            database.dataOphalen("SELECT * FROM deplaats waar alle configuraties opgeslagen zijn"); // moet nog gedaan worden!
            System.out.println("test2");
        } else if (e.getSource() == doorgaanbutton) {
            System.out.println("test3");
        }
    }
}
