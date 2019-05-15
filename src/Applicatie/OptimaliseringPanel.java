package Applicatie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class OptimaliseringPanel extends JPanel implements ActionListener {

    private LeveranciersLijst lijst;
    private HuidigeConfiguratie netwerk;

    private JButton optimaliseer;
    JButton opslaanbutton;
    private JTextField percentage;
    private JLabel error;

    public void berekenNieuweBeschikbaarheid() {
        //hier komt het algoritme die de beschikbaarheid berekent
    }

    public OptimaliseringPanel() { //LeveranciersLijst lijst; moet hier nog in? denk ik xxx tristan xxx

        setLayout(null);

        JLabel percentageL = new JLabel("Gewenste beschikbaarheidspercentage");
        percentageL.setBounds(25, 25, 250, 25);
        add(percentageL);

        percentage = new JTextField("");
        percentage.setBounds(275, 25, 50, 25);
        add(percentage);

        JLabel procent = new JLabel("%");
        procent.setBounds(330, 25, 250, 25);
        add(procent);

        optimaliseer = new JButton("Optimaliseer");
        optimaliseer.setBounds(375, 25, 115, 25);
        optimaliseer.addActionListener((ActionListener) this);
        add(optimaliseer);

        opslaanbutton = new JButton("Opslaan");
        opslaanbutton.setBounds(500, 25, 115, 25);
        add(opslaanbutton);

        optimaliseer = new JButton("Optimaliseer en doorgaan"); //even kijken of we deze knop willen of niet
        optimaliseer.setBounds(375, 75, 240, 25);
        optimaliseer.addActionListener((ActionListener) this);
        add(optimaliseer);

        error = new JLabel("");
        error.setBounds(260, 50, 125, 20);
        add(error);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
