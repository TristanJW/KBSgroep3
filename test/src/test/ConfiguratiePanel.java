/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author edith
 */
public class ConfiguratiePanel {
    import javax.swing.*;

public class ConfiguratiePanel extends JPanel{
    private HuidigeConfiguratie netwerk;
    
    public ConfiguratiePanel() {
        JPanel newPanel = new JPanel();
        
        JLabel titel = new JLabel("Selecteer de componenten de gebruikt moeten worden.");
        
        //namen componenten
        JLabel firewall = new JLabel ("firewall pfSense"); //naam ophalen uit database
        JLabel loadbalancer = new JLabel ("DBloadbalancer"); //naam ophalen uit database
        JLabel DBserver1 = new JLabel ("DBserver HAL9001DB"); //naam ophalen uit database
        JLabel DBserver2 = new JLabel ("DBserver HAL9002DB"); //naam ophalen uit database
        JLabel DBserver3 = new JLabel ("DBserver HAL9003DB"); //naam ophalen uit database
        JLabel webserver1 = new JLabel ("webserver HAL9001W"); //naam ophalen uit database
        JLabel webserver2 = new JLabel ("webserver HAL9002W"); //naam ophalen uit database
        JLabel webserver3 = new JLabel ("webserver HAL9003W"); //naam ophalen uit database
        
        //comboboxen aantal van de componenten
        int[] aantal = new int [] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
 }
    
}

}
