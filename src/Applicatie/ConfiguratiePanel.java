package Applicatie;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ConfiguratiePanel extends JPanel implements ActionListener {

    private HuidigeConfiguratie netwerk;

    JButton DBserverbutton = new JButton();
    JButton firewallbutton = new JButton();
    JButton loadbalancerbutton = new JButton();
    JButton webserverbutton = new JButton();

    public ConfiguratiePanel() {

        try {

            Image DBserver = ImageIO.read(getClass().getResource("dbserver.png")); // zorgt ervoor dat de png op imageicon geplaatst word
            DBserverbutton.setIcon(new ImageIcon(DBserver)); // plaats de imageicon in een button
            DBserverbutton.addActionListener(this); //zorgt ervoor dat de actionlistener kan zien of deze knop is geklikt met e.getsource

            Image firewall = ImageIO.read(getClass().getResource("firewall.png"));
            firewallbutton.setIcon(new ImageIcon(firewall));
            firewallbutton.addActionListener(this);

            Image loadbalancer = ImageIO.read(getClass().getResource("loadbalancer.png"));
            loadbalancerbutton.setIcon(new ImageIcon(loadbalancer));
            loadbalancerbutton.addActionListener(this);

            Image webserver = ImageIO.read(getClass().getResource("webserver.png"));
            webserverbutton.setIcon(new ImageIcon(webserver));
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
            add(dbserverlabel);
        } else if (e.getSource() == firewallbutton) {
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("firewall.png"));
            JLabel firewalllabel = new JLabel(imageIcon);
            add(firewalllabel);
        } else if (e.getSource() == loadbalancerbutton) {
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("loadbalancer.png"));
            JLabel loadbalancerlabel = new JLabel(imageIcon);
            add(loadbalancerlabel);
        } else if (e.getSource() == webserverbutton) {
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("webserver.png"));
            JLabel webserverlabel = new JLabel(imageIcon);
            add(webserverlabel);
        }
    }
}
