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
        setLayout(new FlowLayout());
        JTextArea jt = new JTextArea("please write something ", 10, 10);
        this.add(jt);
        this.add(p);
    }
}
