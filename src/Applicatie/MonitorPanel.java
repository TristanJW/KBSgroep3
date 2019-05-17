package Applicatie;

import java.awt.Font;
import javax.swing.*;
import java.sql.*;

public class MonitorPanel extends JPanel {
    int y = 0;
    int x = 25;
    int aantalcomponenten = 0;

    public MonitorPanel() {
        String SSHUsername = "student";
        String SSHPassword = "achmed459";

        setLayout(null);
        JDBC database = new JDBC();

        ResultSet resultaat = database.dataOphalen("SELECT * FROM component"); //query die uitgevoerd wordt in de databasse
        try {
            while (resultaat.next()) {
                //tweede kolom
                if (aantalcomponenten == 4) {
                    x = +400;
                    y = 0;
                }
                int itemID = resultaat.getInt("itemID");
                String naam = resultaat.getString("componentNaam"); //naam uit de database opslaan in een variabele
                String IPv4 = resultaat.getString("IPv4");

                JLabel componentNaam = new JLabel(naam);
                componentNaam.setFont(new Font("dwjfhjeksj3", Font.BOLD, 18));
                componentNaam.setBounds(x, y, 150, 25);
                add(componentNaam);

                JLabel beschikbaarheid = new JLabel("Beschikbaarheidspercentage:");
                beschikbaarheid.setFont(new Font("a", Font.PLAIN, 14));
                beschikbaarheid.setBounds(x, y + 15, 300, 25);
                add(beschikbaarheid);

                JLabel beschikbaar = new JLabel("Tijd beschikbaar:");
                beschikbaar.setFont(new Font("UIManager.getDefaults().getFont(\"TabbedPane.font\")", Font.PLAIN, 14));
                beschikbaar.setBounds(x, y + 30, 200, 25);
                add(beschikbaar);

//                JLabel processor = new JLabel("Processorbelasting:" + SSHConnectie.SSHConnectie(SSHUsername, IPv4, SSHPassword, "top -b -n1 | grep \"Cpu(s)\" | awk '{print $2 + $4}'") + "%");
//                processor.setFont(new Font("UIManager.getDefaults().getFont(\"TabbedPane.font\")", Font.PLAIN, 14));
//                processor.setBounds(x, y + 45, 200, 25);
//                add(processor);
//
//
//                JLabel diskruimte = new JLabel("Diskruimte:" + SSHConnectie.SSHConnectie(SSHUsername, IPv4, SSHPassword, "df -h --output=pcent --total | awk 'END {print $1}'"));
//                diskruimte.setFont(new Font("UIManager.getDefaults().getFont(\"TabbedPane.font\")", Font.PLAIN, 14));
//                diskruimte.setBounds(x, y + 60, 200, 25);
//                add(diskruimte);

                y += 100;
                aantalcomponenten++;
            }
            repaint();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
