package Applicatie;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Scherm extends JFrame implements ActionListener {
    ConfiguratiePanel p1 = new ConfiguratiePanel();
    MonitorPanel p2 = new MonitorPanel();
    OptimaliseringPanel p3 = new OptimaliseringPanel();

    public Scherm() {
        // actionlisteners
        p1.opslaanbutton.addActionListener(this);
        p1.laadbutton.addActionListener(this);
        p1.doorgaanbutton.addActionListener(this);



        setTitle("Applicatie");


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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == p1.opslaanbutton) {
            OpslaanDialoog od1 = new OpslaanDialoog();
            System.out.println("test1");
        } else if (e.getSource() == p1.laadbutton) {
//            LaadDialoog ld1 = new LaadDialoog();
            System.out.println("test2");
        } else if (e.getSource() == p1.doorgaanbutton) {
            System.out.println("test3");
        }
    }
}
