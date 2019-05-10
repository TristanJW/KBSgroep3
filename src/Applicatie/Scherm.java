package Applicatie;

import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Scherm extends JFrame {

    public Scherm() {

        setTitle("Applicatie");
        MonitorPanel p2 = new MonitorPanel();
        ConfiguratiePanel p1 = new ConfiguratiePanel();

        JTabbedPane tp = new JTabbedPane();

        tp.add("Configuratie", p1);
        tp.add("Monitoren", p2);

        add(tp);
        setSize(800, 650);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

    }
}
