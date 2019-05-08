package Applicatie;

import javax.swing.*;
import java.awt.*;

public class ResultatenPanel extends JPanel {

    private LeveranciersLijst lijst;
    private HuidigeConfiguratie netwerk;

    public void berekenNieuweBeschikbaarheid() {
        //hier komt het algoritme die de beschikbaarheid berekent
    }

    public ResultatenPanel() { //LeveranciersLijst lijst in argument
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // de buttons
        JButton Jb1 = new JButton("maak samenstelling");
//        this.add(Jb1);
        JButton jb2 = new JButton("Opslaan");
        this.add(jb2);

        // de labels (tekst in het programma)
        JLabel lab2 = new JLabel("Geef het beschikbaarheidspercentage op");
        this.add(lab2);
        JLabel lab3 = new JLabel("Servernaam");
        this.add(lab3);
        JLabel lab4 = new JLabel("Aantal");
        this.add(lab4);
        JLabel lab5 = new JLabel("Prijs per stuk");
        this.add(lab5);
        JLabel lab6 = new JLabel("Prijs");
        this.add(lab6);
        JLabel lab7 = new JLabel("Totaalprijs");
        this.add(lab7);

        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(Jb1, c);

        setBounds(5, 5, 280, 50);

    }
}
