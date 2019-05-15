/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Applicatie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
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
            JDBC database = new JDBC();
            ResultSet resultaat = database.dataOphalen("INSERT INTO xxx VALUES xxx");
            dispose();
        }

    }
}
