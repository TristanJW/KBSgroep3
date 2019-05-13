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
    private JButton terug;
    private JTextField percentage;
    private JLabel error;

    public void berekenNieuweBeschikbaarheid() {
        //hier komt het algoritme die de beschikbaarheid berekent
    }

    public OptimaliseringPanel() { //LeveranciersLijst lijst; ?

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
        optimaliseer.setBounds(250, 75, 115, 25);
        optimaliseer.addActionListener((ActionListener) this);
        add(optimaliseer);

        terug = new JButton("Terug");
        terug.setBounds(25, 75, 75, 25);
        terug.addActionListener(this);
        add(terug);

        error = new JLabel("");
        error.setBounds(260, 50, 125, 20);

        add(error);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == terug) {
            System.out.println("test1");

        } else if (e.getSource() == optimaliseer) {
            try {
                int beschikbaarheid = Integer.parseInt(percentage.getText());
                System.out.println(beschikbaarheid);

            } catch (NumberFormatException nfe) {
                error.setText("Voer een getal in");
                System.out.println("Error");
            }

        }
    }

}
