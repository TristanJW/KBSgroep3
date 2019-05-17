/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Applicatie;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Trist
 */
public class LaadDialoog extends JDialog implements ActionListener {

    JButton laadbutton;
    int ycords = 50;
    static boolean isUitgevoerd = false;

    public LaadDialoog(JFrame frame1) {
        super(frame1, true);
        setLayout(null);
        setTitle("Laad configuratie");
        setSize(700, 750);
        Font font = new Font("", Font.BOLD, 12);

        JLabel cfnaam = new JLabel("Naam");
        cfnaam.setFont(font);
        cfnaam.setBounds(15, 0, 50, 25);
        add(cfnaam);

        JLabel cfdatum = new JLabel("Datum");
        cfdatum.setFont(font);
        cfdatum.setBounds(100, 0, 50, 25);
        add(cfdatum);

        JLabel cfprijs = new JLabel("Prijs");
        cfprijs.setFont(font);
        cfprijs.setBounds(250, 0, 50, 25);
        add(cfprijs);

        JLabel cfbeschikbaarheidspercentage = new JLabel("Beschikbaarheidspercentage");
        cfbeschikbaarheidspercentage.setFont(font);
        cfbeschikbaarheidspercentage.setBounds(330, 0, 200, 25);
        add(cfbeschikbaarheidspercentage);

        dataImplementeren();
    }

    public void dataImplementeren() {
        //query voor het ophalen van alle opgeslagen configuraties
        JDBC database = new JDBC();
        ResultSet resultaat = database.dataOphalen("SELECT * From netwerk");
        try {
            //while (gaat alle resultaten door van de query hierboven)
            while (resultaat.next()) {
                String naam = resultaat.getString("naam");
                String datum = resultaat.getString("datum");
                String prijs = resultaat.getString("prijs");
                String beschikbaarheidspercentage = resultaat.getString("beschikbaarheidspercentage");

                JLabel naamlabel = new JLabel(naam);
                naamlabel.setBounds(15, ycords, 100, 25);
                add(naamlabel);

                JLabel datumlabel = new JLabel(datum);
                datumlabel.setBounds(100, ycords, 100, 25);
                add(datumlabel);

                JLabel prijslabel = new JLabel(prijs);
                prijslabel.setBounds(250, ycords, 100, 25);
                add(prijslabel);

                JLabel beschikbaarheidspercentagelabel = new JLabel(beschikbaarheidspercentage);
                beschikbaarheidspercentagelabel.setBounds(400, ycords, 50, 25);
                add(beschikbaarheidspercentagelabel);

                laadbutton = new JButton("laad");
                laadbutton.setBounds(550, ycords, 125, 25);
                laadbutton.addActionListener(this);
                this.add(laadbutton);

                // zorgt ervoor dat elk nieuw label iets naar onder gaat zodat het elkaar niet overlapt
                ycords += 50;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == laadbutton) {
//            JDBC database = new JDBC();
//            ResultSet resultaat2 = database.dataOphalen("SELECT * From component WHERE xxx ");
//            for (Object object : col) {
//                // loop door de componenten die van de samenstelling zijn (moet ze dus ophalen) maar het moet eerst kunnen zien welke button geklikt is
//            }
            System.out.println("test");
        }
    }
}
