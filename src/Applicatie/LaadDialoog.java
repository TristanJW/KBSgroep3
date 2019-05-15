/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Applicatie;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Trist
 */
public class LaadDialoog extends JDialog implements ActionListener {
    //configuratie en optimaliserenpanel opslaan knop moet hieraan gelinked worden

    JButton laadbutton;

    public LaadDialoog(JFrame frame1) {
        super(frame1, true);
        setLayout(null);
        setTitle("Opslaan");
        setSize(500, 500);

        laadbutton = new JButton("laad configuratie");
        laadbutton.setBounds(325, 25, 125, 25);
        laadbutton.addActionListener(this);
        this.add(laadbutton);

        JDBC database = new JDBC();
        ResultSet resultaat = database.dataOphalen("SELECT naam, datum, prijs, beschikbaarheidspercentage FROM netwerk");
        try {
            while (resultaat.next()) {
                String naam = resultaat.getString("naam");
                String datum = resultaat.getString("datum");
                int prijs = resultaat.getInt("prijs");
                int beschikbaarheidspercentage = resultaat.getInt("beschikbaarheidspercentage");

                System.out.println(" naam:" + naam + " datum:" + datum + " prijs:" + prijs + " beschikbaarheidspercentage:" + beschikbaarheidspercentage);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == laadbutton) {

        }

    }
}
