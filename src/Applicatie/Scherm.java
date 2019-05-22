package Applicatie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Scherm extends JFrame implements ActionListener {

    ConfiguratiePanel p1 = new ConfiguratiePanel();
    MonitorPanel p2 = new MonitorPanel();
    OptimaliseringPanel p3 = new OptimaliseringPanel();

    JTabbedPane tp;

    public Scherm() {
        //p2.start(); //uncomment voor een werkend monitorpanel
        // actionlisteners
        p1.opslaanbutton.addActionListener(this);
        p1.laadbutton.addActionListener(this);
        p3.opslaanbutton.addActionListener(this);

        setTitle("Applicatie");

        tp = new JTabbedPane();

        tp.add("Configuratie", p1);
        tp.add("Monitoren", p2);
        tp.add("Optimaliseren", p3);

        add(tp);
        setSize(800, 650);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == p1.opslaanbutton) {
            OpslaanDialoog od1 = new OpslaanDialoog(this, p1);
            od1.setVisible(true);
        } else if (e.getSource() == p3.opslaanbutton) {
            OpslaanDialoog od1 = new OpslaanDialoog(this, p3);
            od1.setVisible(true);
        } else if (e.getSource() == p1.laadbutton) {
            LaadDialoog ld1 = new LaadDialoog(this, p1);
            ld1.setVisible(true);
            if (ld1.getOphalen()) {
                //als er een configuratie is geladen dan wordt alles in een nieuwe huidigecnfiguratie gezet
                try {
                    HuidigeConfiguratie geimporteerdNetwerk = new HuidigeConfiguratie();
                    //het resultaat van de sql querry in het nieuwe netwerk zetten
                    geimporteerdNetwerk.dataNaarNetwerk(ld1.getResultaat());

                    //het nieuwe geimporteerde netwerk meegeven aan het configuratiepanel
                    p1.netwerk = geimporteerdNetwerk;
                    p1.actionPerformed(e); // anders moeten we een keer apart klikken voordat het wordt getoont.
                } catch (SQLException ex) {
                    Logger.getLogger(Scherm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
