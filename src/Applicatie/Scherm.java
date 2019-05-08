package Applicatie;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class Scherm extends JFrame {

    public Scherm() {

        {

            JFrame f = new JFrame();

            ResultatenPanel p1 = new ResultatenPanel();
            MonitorPanel p2 = new MonitorPanel();
            JPanel p3 = new JPanel();

            JTabbedPane tp = new JTabbedPane();
            tp.setBounds(0, 0, 600, 600);

            tp.add("Resultaten", p1);
            tp.add("Monitoren", p2);
            tp.add("Configuratie", p3);

            f.add(tp);
            f.setSize(600, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setLayout(null);
            f.setVisible(true);
        }
    }
}
