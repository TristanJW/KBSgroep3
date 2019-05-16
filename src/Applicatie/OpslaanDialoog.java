/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Applicatie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author Trist
 */
public class OpslaanDialoog extends JDialog implements ActionListener {
    private HuidigeConfiguratie netwerk = new HuidigeConfiguratie();
    private ConfiguratiePanel configuratiepanel;
    JDBC database = new JDBC();
    LocalDate datum = LocalDate.now(ZoneId.of("Europe/Amsterdam"));
    JTextField opslaannaam;
    JButton ODopslaanbutton;
    JTabbedPane tp;

    public OpslaanDialoog(JFrame frame1) {
        super(frame1, true);
        setLayout(null);
        setTitle("Opslaan");
        setSize(500, 150);

        opslaannaam = new JTextField("vul een naam in");
        opslaannaam.setBounds(25, 25, 290, 25);
        this.add(opslaannaam);

        ODopslaanbutton = new JButton("opslaan");
        ODopslaanbutton.setBounds(325, 25, 125, 25);
        ODopslaanbutton.addActionListener(this);
        this.add(ODopslaanbutton);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {      
        if (e.getSource() == ODopslaanbutton) {
            //hoogste netwerkID ophalen, zodat de primary key steeds 1 hoger wordt
            ResultSet resultaat = database.dataOphalen("SELECT MAX(netwerkID) FROM netwerk");
                try {
                    while (resultaat.next()) {
                    //hoogste netwerkID opslaan als een int    
                    int hoogsteID = resultaat.getInt("MAX(netwerkID)");
                    hoogsteID++;
                    netwerk.configuratieNaarDatabase("INSERT INTO netwerk (netwerkID, datum, beschikbaarheidspercentage, naam, prijs) VALUES (" + hoogsteID + ", \"" + datum + "\"," + netwerk.berekenBeschikbaarheid() + ", \"" + opslaannaam.getText()+ "\"," + netwerk.dbTotalePrijs() + ")");
                    }
                }
                catch(Exception ex) {
                    System.out.println(ex);
                }
            System.out.println("Opslaan");
            dispose();
        } else {
            }

    }
}
