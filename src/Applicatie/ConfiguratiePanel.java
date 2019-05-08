package Applicatie;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

public class ConfiguratiePanel extends JPanel {

    private HuidigeConfiguratie netwerk;

    public ConfiguratiePanel() {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout());

        //namen componenten
        JLabel firewall = new JLabel ("firewall");
        JLabel loadbalancer = new JLabel("DBloadbalancer");
        JLabel DBserver1 = new JLabel("DBserver HAL9001DB");
        JLabel DBserver2 = new JLabel("DBserver HAL9002DB");
        JLabel DBserver3 = new JLabel("DBserver HAL9003DB");
        JLabel webserver1 = new JLabel("webserver HAL9001W");
        JLabel webserver2 = new JLabel("webserver HAL9002W");
        JLabel webserver3 = new JLabel("webserver HAL9003W");

        this.add(firewall);
        this.add(loadbalancer);
        newPanel.add(DBserver1);
        newPanel.add(DBserver2);
        newPanel.add(DBserver3);
        newPanel.add(webserver1);
        newPanel.add(webserver2);
        newPanel.add(webserver3);

        //spinner aantal van de componenten
        JSpinner spinnerLoadbalancer = new JSpinner();

        //prijs per jaar
        JLabel prijsPerJaar = new JLabel("Prijs per jaar");
        JLabel prijsFirewall = new JLabel("€2000,-");
        JLabel prijsLoadbalancer = new JLabel("€2000,-");
        JLabel prijsDBserver1 = new JLabel("€5100,-");
        JLabel prijsDBserver2 = new JLabel("€7700,-");
        JLabel prijsDBserver3 = new JLabel("€12200,-");
        JLabel prijsWebserver1 = new JLabel("€2200,-");
        JLabel prijsWebserver2 = new JLabel("€3200,-");
        JLabel prijsWebserver3 = new JLabel("€5100,-");

        newPanel.add(prijsPerJaar);
        newPanel.add(prijsFirewall);
        newPanel.add(prijsLoadbalancer);
        newPanel.add(prijsDBserver1);
        newPanel.add(prijsDBserver2);
        newPanel.add(prijsDBserver3);
        newPanel.add(prijsWebserver1);
        newPanel.add(prijsWebserver2);
        newPanel.add(prijsWebserver3);

        //beschikbaarheidspercentage
        JLabel bp = new JLabel("Beschikbaarheidspercentage");
        JLabel bpFirewall = new JLabel("99.9999%");
        JLabel bpLoadbalancer = new JLabel("99.999%");
        JLabel bpDBserver1 = new JLabel("90%");
        JLabel bpDBserver2 = new JLabel("95%");
        JLabel bpDBserver3 = new JLabel("98%");
        JLabel bpWebserver1 = new JLabel("80%");
        JLabel bpWebserver2 = new JLabel("90%");
        JLabel bpWebserver3 = new JLabel("95%");

        newPanel.add(bp);
        newPanel.add(bpFirewall);
        newPanel.add(bpLoadbalancer);
        newPanel.add(bpDBserver1);
        newPanel.add(bpDBserver2);
        newPanel.add(bpDBserver3);
        newPanel.add(bpWebserver1);
        newPanel.add(bpWebserver2);
        newPanel.add(bpWebserver3);

        //knop opslaan
        JButton opslaan = new JButton("Opslaan");
        newPanel.add(opslaan);
        setBounds(5, 5, 280, 50);

    }

    public ConfiguratiePanel(HuidigeConfiguratie netwerk) {
        this.netwerk = netwerk;

    }

}
//        JTextArea jt = new JTextArea("hallo ", 10, 10);
//
//        this.add(jt);
//
//        JLabel lab1 = new JLabel("Test123", JLabel.LEFT);
//        add(lab1);
