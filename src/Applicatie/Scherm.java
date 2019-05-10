package Applicatie;

import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Scherm extends JFrame {

    public Scherm() {

        setTitle("Applicatie");
        MonitorPanel p1 = new MonitorPanel();
        ConfiguratiePanel p2 = new ConfiguratiePanel();
        ResultatenPanel p3 = new ResultatenPanel();

        JTabbedPane tp = new JTabbedPane();

        tp.add("Monitoren", p1);
        tp.add("Configuratie", p2);
        tp.add("Resultaten", p3);

        add(tp);
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

    }
}
