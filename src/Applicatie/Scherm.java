package Applicatie;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Scherm extends JFrame {

    public Scherm() {

        {

            JFrame f = new JFrame("Applicatie");
            f.setLayout(new BorderLayout());
            
            MonitorPanel p1 = new MonitorPanel();
            ConfiguratiePanel p2 = new ConfiguratiePanel();
            ResultatenPanel p3 = new ResultatenPanel();

            JTabbedPane tp = new JTabbedPane();
            tp.setBounds(0, 0, 300, 300);

            tp.add("Monitoren", p1);
            tp.add("Configuratie", p2);
            tp.add("Resultaten", p3);
            
            f.add(tp);
            f.setSize(600, 600);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setVisible(true);
        }
    }
}
