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
        JPanel p = new JPanel();

        JTextArea jt = new JTextArea("please write something ", 10, 10);

        this.add(jt);

        JLabel lab1 = new JLabel("User Name", JLabel.RIGHT);
        setBounds(5, 5, 280, 50);
        setLayout(new FlowLayout());

        add(lab1);

    }
}
