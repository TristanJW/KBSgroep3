package Applicatie;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class TekenPanel extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.drawRect(0, 0, 600, 470);
    }

    TekenPanel() {
        setLayout(null);
    }
}
    

