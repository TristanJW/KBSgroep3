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
import javax.swing.JTextField;

/**
 *
 * @author Trist
 */
public class LaadDialoog extends JDialog implements ActionListener {

    JButton laadbutton;
    JButton verwijderbutton;
    JTextField input1;
    int ycords = 85;
    int itemID;

    int queryinput;

    JLabel error = new JLabel("voer een geldig configuratie ID in");

    public LaadDialoog(JFrame frame1) {
        super(frame1, true);
        setLayout(null);
        setTitle("Laad configuratie");
        setSize(600, 750);
        Font font = new Font("", Font.BOLD, 12);

        error.setBounds(5, 25, 200, 25);

        add(error);
        error.setVisible(false);

        JLabel cfid = new JLabel("ID");
        cfid.setFont(font);
        cfid.setBounds(5, 45, 50, 25);
        add(cfid);

        JLabel cfnaam = new JLabel("Naam");
        cfnaam.setFont(font);
        cfnaam.setBounds(25, 45, 50, 25);
        add(cfnaam);

        JLabel cfdatum = new JLabel("Datum");
        cfdatum.setFont(font);
        cfdatum.setBounds(100, 45, 50, 25);
        add(cfdatum);

        JLabel cfprijs = new JLabel("Prijs");
        cfprijs.setFont(font);
        cfprijs.setBounds(250, 45, 50, 25);
        add(cfprijs);

        JLabel cfbeschikbaarheidspercentage = new JLabel("Beschikbaarheidspercentage");
        cfbeschikbaarheidspercentage.setFont(font);
        cfbeschikbaarheidspercentage.setBounds(330, 45, 200, 25);
        add(cfbeschikbaarheidspercentage);

        input1 = new JTextField("");
        input1.setBounds(5, 5, 400, 25);
        add(input1);

        laadbutton = new JButton("laad");
        laadbutton.setBounds(410, 2, 125, 20);
        laadbutton.addActionListener(this);
        this.add(laadbutton);

        verwijderbutton = new JButton("delete");
        verwijderbutton.setBounds(410, 28, 125, 20);
        verwijderbutton.addActionListener(this);
        this.add(verwijderbutton);

        dataImplementeren();
    }

    public void dataImplementeren() {
        //query voor het ophalen van alle opgeslagen configuraties
        JDBC database = new JDBC();
        ResultSet resultaat = database.dataOphalen("SELECT * From netwerk");
        try {
            //while (gaat alle resultaten door van de query hierboven)
            while (resultaat.next()) {
                String netwerkID = resultaat.getString("netwerkID");
                String naam = resultaat.getString("naam");
                String datum = resultaat.getString("datum");
                String prijs = resultaat.getString("prijs");
                String beschikbaarheidspercentage = resultaat.getString("beschikbaarheidspercentage");

                JLabel netwerkIDlabel = new JLabel(netwerkID);
                netwerkIDlabel.setBounds(5, ycords, 100, 25);
                add(netwerkIDlabel);

                JLabel naamlabel = new JLabel(naam);
                naamlabel.setBounds(25, ycords, 100, 25);
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
            try {
                queryinput = Integer.parseInt(input1.getText());
                JDBC database = new JDBC();
                ResultSet resultaat = database.dataOphalen("SELECT naam From leverancierslijst JOIN netwerkregel ON netwerkregel.itemID = leverancierslijst.itemID WHERE netwerkregel.netwerkID =" + queryinput);
                while (resultaat.next()) {
                    String naam = resultaat.getString("naam");
                    System.out.println(naam); // van alles krijg ik nu 1 terug, werkt niet zit in de select query
                    // hier moet de geladen configuratie dus de labels uitprinten op het configuratiescherm
                }
                dispose();
            } catch (Exception ex) {
                error.setVisible(true);
            }

        } else if (e.getSource() == verwijderbutton) {
            try {
                queryinput = Integer.parseInt(input1.getText());
                JDBC database = new JDBC();
                database.dataToevoegen("DELETE FROM netwerkregel WHERE netwerkID =" + queryinput);
                database.dataToevoegen("DELETE FROM netwerk WHERE netwerkID =" + queryinput);
            } catch (Exception ex) {
                error.setVisible(true);
            }
        }
        repaint();
    }
}
