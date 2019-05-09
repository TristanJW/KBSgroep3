package Applicatie;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

public class ResultatenPanel extends JPanel {

    private LeveranciersLijst lijst;
    private HuidigeConfiguratie netwerk;

    public void berekenNieuweBeschikbaarheid() {
        //hier komt het algoritme die de beschikbaarheid berekent
    }

    GridBagConstraints gbc = new GridBagConstraints();

    public ResultatenPanel() { //LeveranciersLijst lijst in argument
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JButton Jb1 = new JButton("maak samenstelling");
        gbc.gridx = gbc.gridy = 0;
        gbc.gridwidth = gbc.gridheight = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = gbc.weighty = 20;
        add(Jb1, gbc);

//
//        gbc.insets = new Insets(5, 5, 5, 5);
//
//        // de buttons
//        JButton Jb1 = new JButton("maak samenstelling");
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//
//        add(Jb1, gbc);
//
//        JButton jb2 = new JButton("Opslaan");
//        gbc.gridx = 3;
//        gbc.gridy = 3;
//        add(jb2, gbc);
        // de labels (tekst in het programma)
//        JLabel lab2 = new JLabel("Geef het beschikbaarheidspercentage op");
//        this.add(lab2);
//        JLabel lab3 = new JLabel("Servernaam");
//        this.add(lab3);
//        JLabel lab4 = new JLabel("Aantal");
//        this.add(lab4);
//        JLabel lab5 = new JLabel("Prijs per stuk");
//        this.add(lab5);
//        JLabel lab6 = new JLabel("Prijs");
//        this.add(lab6);
//        JLabel lab7 = new JLabel("Totaalprijs");
//        this.add(lab7);
    }
}
