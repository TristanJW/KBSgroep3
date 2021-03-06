package Applicatie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class OptimaliseringPanel extends JPanel implements ActionListener {

    HuidigeConfiguratie netwerk = new HuidigeConfiguratie();
    private JButton optimaliseer;
    JButton opslaanbutton;
    private JTextField percentage;
    private JLabel error;
    private JLabel errorBP;
    private JPanel panellabels = new JPanel();

    OptimaliseringPanel() {
        panellabels.setSize(500, 400);
        panellabels.setBounds(50, 50, 500, 500);
        panellabels.setLayout(null);
        add(panellabels);

        setLayout(null);

        //melding die weergeven wordt als er een getal > 99.99 wordt ingevoerd
        errorBP = new JLabel("voer een getal tussen 0 en 99.99 in");
        errorBP.setBounds(200, 0, 200, 25);

        //melding die weergeven wordt als er geen getal wordt ingevoerd
        error = new JLabel("voer een getal in");
        error.setBounds(200, 0, 100, 25);

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
        optimaliseer.addActionListener(this);
        add(optimaliseer);

        opslaanbutton = new JButton("Opslaan");
        opslaanbutton.setBounds(500, 25, 115, 25);
        add(opslaanbutton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == optimaliseer) {
            panellabels.removeAll();
            repaint();
            try {
                //ingevulde percentage ophalen en in het algoritme invoeren
                double gewenstepercentage = Double.parseDouble(percentage.getText());
                if (gewenstepercentage > 99.99 || gewenstepercentage < 0) {
                    panellabels.add(errorBP);
                    repaint();
                } else {
                    netwerk.Optimaliseer(gewenstepercentage);
                    //netwerkcomponenten weergeven op het scherm
                    int xcords = 0;
                    for (NetwerkComponent nc : netwerk.getNetwerkLijst()) {
                        JLabel naam = new JLabel(nc.getNaam());
                        naam.setBounds(25, xcords, 115, 25);
                        panellabels.add(naam);
                        xcords += 15;
                        repaint();
                    }
                    //prijs op het scherm tonen
                    JLabel prijs = new JLabel(netwerk.berekenTotalePrijs());
                    prijs.setBounds(375, 0, 115, 25);
                    panellabels.add(prijs);

                    //beschikbaarheidspercentage op het scherm tonen
                    double beschikbaarheid = netwerk.berekenBeschikbaarheid();
                    DecimalFormat df = new DecimalFormat("#.##");
                    JLabel beschikbaarheidspercentage = new JLabel(df.format(beschikbaarheid) + " %");
                    beschikbaarheidspercentage.setBounds(375, 15, 115, 25);
                    panellabels.add(beschikbaarheidspercentage);
                    error.setVisible(false);
                }
            } catch (Exception ex) {
                repaint();
                panellabels.add(error);
            }
        }
    }
}
