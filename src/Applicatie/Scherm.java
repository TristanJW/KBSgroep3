package Applicatie;

import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Scherm extends JFrame {

    public Scherm() {

        setTitle("Applicatie");
        ConfiguratiePanel p1 = new ConfiguratiePanel();
        MonitorPanel p2 = new MonitorPanel();
        OptimaliseringPanel p3 = new OptimaliseringPanel();

        JTabbedPane tp = new JTabbedPane();

        tp.add("Configuratie", p1);
        tp.add("Monitoren", p2);
        tp.add("Optimaliseren", p3);

        add(tp);
        setSize(800, 650);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

    }
}
