package Applicatie;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ConfiguratiePanel extends JPanel implements ActionListener {

    private HuidigeConfiguratie netwerk;

    static int xcordsdbserver = 125;
    static int xcordsfirewall = 125;
    static int xcordsloadbalancer = 125;
    static int xcordswebserver = 125;

    JButton DBserverbutton = new JButton();
    JButton firewallbutton = new JButton();
    JButton loadbalancerbutton = new JButton();
    JButton webserverbutton = new JButton();

    JLabel DBserver = new JLabel("DBserver");
    JLabel firewall = new JLabel("Firewall");
    JLabel loadbalancer = new JLabel("Loadbalancer");
    JLabel webserver = new JLabel("Webserver");

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

            //voegt alle buttons toe aan de panel
            add(DBserverbutton);

            add(firewallbutton);

            add(loadbalancerbutton);

            add(webserverbutton);

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == DBserverbutton) { // e.getSource==buttonnaam kijkt naar of deze button geklikt is, zo ja dan runt de code van deze if, zo nee gaat het naar de volgende else if.

            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("dbserver.png")); // zorgt ervoor dat de png op imageicon geplaatst word
            JLabel dbserverlabel = new JLabel(imageIcon); // maakt een label met de imageicon die hierboven aangemaakt is
            dbserverlabel.setBounds(xcordsdbserver, 25, 100, 100);
            xcordsdbserver = this.xcordsdbserver + 75;
            add(dbserverlabel);
            add(DBserver);
            repaint();
        } else if (e.getSource() == firewallbutton) {

            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("firewall.png"));
            JLabel firewalllabel = new JLabel(imageIcon);
            firewalllabel.setBounds(xcordsfirewall, 150, 100, 100);
            xcordsfirewall = this.xcordsfirewall + 75;
            add(firewalllabel);
            add(firewall);
            repaint();
        } else if (e.getSource() == loadbalancerbutton) {

            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("loadbalancer.png"));
            JLabel loadbalancerlabel = new JLabel(imageIcon);
            loadbalancerlabel.setBounds(xcordsloadbalancer, 275, 100, 100);
            xcordsloadbalancer = this.xcordsloadbalancer + 75;
            add(loadbalancerlabel);
            add(loadbalancer);
            repaint();
        } else if (e.getSource() == webserverbutton) {

            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("webserver.png"));
            JLabel webserverlabel = new JLabel(imageIcon);
            webserverlabel.setBounds(xcordswebserver, 400, 100, 100);
            xcordswebserver = this.xcordswebserver + 75;
            add(webserverlabel);
            add(webserver);
            repaint();
        }
    }
}
