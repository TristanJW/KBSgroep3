package Applicatie;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

public class ConfiguratiePanel extends JPanel {
    
    private HuidigeConfiguratie netwerk;

    public ConfiguratiePanel() {
        JPanel newPanel = new JPanel();
        
        //GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.weightx = 1.0;
//        gbc.weighty = 0.5;
        
        //afstand tussen labels en buttons
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        //namen componenten
        JLabel firewall = new JLabel ("firewall");
        gbc.gridy ++;       
        this.add(firewall, gbc);
        
        JLabel loadbalancer = new JLabel("DBloadbalancer");
        gbc.gridy++;
        this.add(loadbalancer, gbc);
        
        JLabel DBserver1 = new JLabel("DBserver HAL9001DB");
        gbc.gridy ++;
        this.add(DBserver1, gbc);
        
        JLabel DBserver2 = new JLabel("DBserver HAL9002DB");
        gbc.gridy ++;
        this.add(DBserver2, gbc);
        
        JLabel DBserver3 = new JLabel("DBserver HAL9003DB");
        gbc.gridy++;
        this.add(DBserver3, gbc);
        
        JLabel webserver1 = new JLabel("webserver HAL9001W");
        gbc.gridy++;
        this.add(webserver1, gbc);
        
        JLabel webserver2 = new JLabel("webserver HAL9002W");
        gbc.gridy ++;
        this.add(webserver2, gbc);
        
        JLabel webserver3 = new JLabel("webserver HAL9003W");
        gbc.gridy ++;
        this.add(webserver3, gbc);
        
        //nieuwe kolom
        gbc.gridx = 1;
        gbc.gridy = 0;
        
        //spinner aantal van de componenten
        JLabel aantal = new JLabel("Aantal");
        this.add(aantal, gbc);
        
        //beginwaarde, minimuwaarde, maximumwaarde en steps spinner
        SpinnerModel value = new SpinnerNumberModel(1, 0, 10, 1);
        
        JSpinner spinnerLoadbalancer = new JSpinner(value);
        gbc.gridy++;
        this.add(spinnerLoadbalancer, gbc);

        //nieuwe kolom
        gbc.gridx = 2;
        gbc.gridy = 0;
        
        //prijs per jaar toevoegen
        JLabel prijsPerJaar = new JLabel("Prijs per jaar");
        gbc.gridx ++;
        this.add(prijsPerJaar, gbc);
        
        JLabel prijsFirewall = new JLabel("€2000,-");
        gbc.gridy ++;
        this.add(prijsFirewall, gbc);
        
        JLabel prijsLoadbalancer = new JLabel("€2000,-");
        gbc.gridy ++;
        this.add(prijsLoadbalancer, gbc);
        
        JLabel prijsDBserver1 = new JLabel("€5100,-");
        gbc.gridy ++;
        this.add(prijsDBserver1, gbc);
        
        JLabel prijsDBserver2 = new JLabel("€7700,-");
        gbc.gridy ++;
        this.add(prijsDBserver2, gbc);
        
        JLabel prijsDBserver3 = new JLabel("€12200,-");
        gbc.gridy ++;
        this.add(prijsDBserver3, gbc);
        
        JLabel prijsWebserver1 = new JLabel("€2200,-");
        gbc.gridy++;
        this.add(prijsWebserver1, gbc);
        
        JLabel prijsWebserver2 = new JLabel("€3200,-");
        gbc.gridy ++;
        this.add(prijsWebserver2, gbc);
        
        JLabel prijsWebserver3 = new JLabel("€5100,-");
        gbc.gridy ++;
        this.add(prijsWebserver3, gbc);
        
        //nieuwe kolom
        gbc.gridx = 3;
        gbc.gridy = 0;
        
        //beschikbaarheidspercentage
        JLabel bp = new JLabel("Beschikbaarheidspercentage");
        gbc.gridx++;
        this.add(bp, gbc);
        
        JLabel bpFirewall = new JLabel("99.9999%");
        gbc.gridy++;
        this.add(bpFirewall, gbc);
        
        JLabel bpLoadbalancer = new JLabel("99.999%");
        gbc.gridy++;
        this.add(bpLoadbalancer, gbc);
        
        JLabel bpDBserver1 = new JLabel("90%");
        gbc.gridy++;
        this.add(bpDBserver1, gbc);
        
        JLabel bpDBserver2 = new JLabel("95%");
        gbc.gridy++;
        this.add(bpDBserver2, gbc);
        
        JLabel bpDBserver3 = new JLabel("98%");
        gbc.gridy++;
        this.add(bpDBserver3, gbc);
        
        JLabel bpWebserver1 = new JLabel("80%");
        gbc.gridy++;
        this.add(bpWebserver1, gbc);
        
        JLabel bpWebserver2 = new JLabel("90%");
        gbc.gridy++;
        this.add(bpWebserver2, gbc);
        
        JLabel bpWebserver3 = new JLabel("95%");
        gbc.gridy++;
        this.add(bpWebserver3, gbc);
        
        //knop opslaan
        JButton opslaan = new JButton("Opslaan");
        gbc.gridy = 10;
        gbc.gridx = 3;
        this.add(opslaan, gbc);
        
        setBounds(5, 5, 280, 50);


    }

    public ConfiguratiePanel(HuidigeConfiguratie netwerk) {
        this.netwerk = netwerk;

    }

}
