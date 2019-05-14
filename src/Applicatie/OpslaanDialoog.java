/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Applicatie;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Trist
 */
public class OpslaanDialoog extends JDialog implements ActionListener {

    JTextField opslaannaam;
    JButton opslaanbutton;

    public OpslaanDialoog(JFrame frame1) {
        super(frame1, true);
        setLayout(null);
        setTitle("Opslaan");
        setSize(500, 150);

        opslaannaam = new JTextField("vul een naam in");
        opslaannaam.setBounds(25, 25, 290, 25);
        this.add(opslaannaam);

        opslaanbutton = new JButton("opslaan");
        opslaanbutton.setBounds(325, 25, 125, 25);
        opslaanbutton.addActionListener(this);
        this.add(opslaanbutton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
