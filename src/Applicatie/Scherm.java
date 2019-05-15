package Applicatie;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Scherm extends JFrame implements ActionListener {

    ConfiguratiePanel p1 = new ConfiguratiePanel();
    MonitorPanel p2 = new MonitorPanel();
    OptimaliseringPanel p3 = new OptimaliseringPanel();

    JTabbedPane tp;

    public Scherm() {
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
        if (e.getSource() == p1.opslaanbutton || e.getSource() == p3.opslaanbutton) {
            OpslaanDialoog od1 = new OpslaanDialoog(this);
            od1.setVisible(true);
        } else if (e.getSource() == p1.laadbutton) {
            LaadDialoog ld1 = new LaadDialoog(this);
            ld1.setVisible(true);
        }
    }
}
