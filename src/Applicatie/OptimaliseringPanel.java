package Applicatie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public class OptimaliseringPanel extends JPanel implements ActionListener {

    private LeveranciersLijst lijst;
    HuidigeConfiguratie netwerk = new HuidigeConfiguratie();
    JButton optimaliseer;
    JButton opslaanbutton;
    private JTextField percentage;
    JLabel error;

    public void berekenNieuweBeschikbaarheid() {
        //hier komt het algoritme die de beschikbaarheid berekent
    }

    public OptimaliseringPanel() { //LeveranciersLijst lijst; moet hier nog in? denk ik xxx tristan xxx
        
        setLayout(null);
        
        error = new JLabel("voer een getal in");
        error.setBounds(260, 50, 100, 25);
        add(error);
        error.setVisible(false);
                
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
            try {
                //ingevulde percentage ophalen en in het algoritme invoeren
                HuidigeConfiguratie netwerk = new HuidigeConfiguratie();
                int gewenstepercentage = Integer.parseInt(percentage.getText());
                System.out.println(gewenstepercentage);
                netwerk.Optimaliseer(gewenstepercentage);
                //netwerkcomponenten weergeven op het scherm
                int xcords = 50;
                  for (NetwerkComponent nc : netwerk.getNetwerkLijst()) {
                    JLabel naam = new JLabel(nc.getNaam());
                    naam.setBounds(25, xcords, 115, 25);
                    add(naam);
                    xcords += 15;
                    repaint();
                  }
                //prijs op het scherm tonen
                JLabel prijs = new JLabel(netwerk.berekenTotalePrijs());
                prijs.setBounds(350, 50, 115, 25);
                add(prijs);
                //beschikbaarheidspercentage op het scherm tonen
                String beschikbaarheid = netwerk.berekenBeschikbaarheid() + " %";
                JLabel beschikbaarheidspercentage = new JLabel(beschikbaarheid);
                beschikbaarheidspercentage.setBounds(350, 65, 115, 25);
                add(beschikbaarheidspercentage);
                System.out.println(netwerk.berekenTotalePrijs());  
                error.setVisible(false);
            } catch (Exception ex) {
                error.setVisible(true);
                repaint();
            }          
        }
    }

}
