package Applicatie;

import java.awt.FlowLayout;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;

public class Scherm extends JFrame {

    public Scherm() {

        {

            JFrame f = new JFrame("Applicatie");
            setLayout(new FlowLayout());

            MonitorPanel p1 = new MonitorPanel();
            ConfiguratiePanel p2 = new ConfiguratiePanel();
            ResultatenPanel p3 = new ResultatenPanel();

            JTabbedPane tp = new JTabbedPane();
            tp.setBounds(0, 0, 600, 600);

            tp.add("Monitoren", p1);
            tp.add("Configuratie", p2);
            tp.add("Resultaten", p3);

            f.add(tp);
            f.setSize(600, 600);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setLayout(null);
            f.setVisible(true);
        }
    }
}
