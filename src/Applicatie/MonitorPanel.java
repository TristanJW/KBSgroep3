package Applicatie;

import java.awt.Font;
import javax.swing.*;
import java.sql.*;

public class MonitorPanel extends JPanel implements Runnable {
    private Thread t;

    public MonitorPanel() {
        setLayout(null);
    }

    @Override
    public void run() {
        String SSHUsername = "student";
        String SSHPassword = "achmed459"; //todo ssh keys? oid
        JDBC database = new JDBC();
        while (true) {
            int y = 0;
            int x = 25;
            int aantalcomponenten = 0;
            removeAll();
            ResultSet resultaat = database.dataOphalen("SELECT componentNaam, IPv4 FROM component"); //query die uitgevoerd wordt in de databasse
            try { //todo add server to db button
                while (resultaat.next()) {
                    //tweede kolom //todo meer op scherm passen
                    if (aantalcomponenten == 4) {
                        x = +400;
                        y = 0;
                    }
                    String naam = resultaat.getString("componentNaam"); //naam uit de database opslaan in een variabele
                    String IPv4 = resultaat.getString("IPv4");

                    JLabel componentNaam = new JLabel(naam);
                    componentNaam.setFont(new Font("Comic Sans MS", Font.BOLD, 18)); //todo fontje fixen
                    componentNaam.setBounds(x, y, 150, 25);
                    add(componentNaam);

                    JLabel beschikbaarheid = new JLabel("Beschikbaarheidspercentage:");
                    beschikbaarheid.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
                    beschikbaarheid.setBounds(x, y + 15, 300, 25);
                    add(beschikbaarheid);

                    JLabel beschikbaar = new JLabel("Tijd beschikbaar:");
                    beschikbaar.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
                    beschikbaar.setBounds(x, y + 30, 200, 25);
                    add(beschikbaar);

                    if (naam.equals("pfSense")) { // todo OS tabel in DB, en iF statement op os doen ubuntu/freebsd
                        String output = SSHConnectie.SSHConnect(SSHUsername, IPv4, SSHPassword, "top -d 2 | grep 'CPU:' | awk '{print $10}'");
                        String output_sub = output.trim().substring(0, output.length() - 2);
                        JLabel processor = new JLabel("Processorbelasting:" + (100 - Math.round(Double.parseDouble(output_sub))) + "%"); // we halen de idle cpu % op, en dat halen we van 100 af voor cpu%
                        processor.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
                        processor.setBounds(x, y + 45, 200, 25);
                        add(processor);
                    } else {
                        JLabel processor = new JLabel("Processorbelasting:" + SSHConnectie.SSHConnect(SSHUsername, IPv4, SSHPassword, "cat <(grep 'cpu ' /proc/stat) <(sleep 1 && grep 'cpu ' /proc/stat) | awk -v RS=\"\" '{print ($13-$2+$15-$4)*100/($13-$2+$15-$4+$16-$5) \"%\"}'"));//todo afronden
                        processor.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
                        processor.setBounds(x, y + 45, 200, 25);
                        add(processor);
                    }
                    if (naam.equals("pfSense")) {
                        JLabel diskruimte = new JLabel("Diskruimte:" + SSHConnectie.SSHConnect(SSHUsername, IPv4, SSHPassword, "df -h | grep '/dev/gpt/pfSense' | awk '{print $5}'"));
                        diskruimte.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
                        diskruimte.setBounds(x, y + 60, 200, 25);
                        add(diskruimte);
                    } else {
                        JLabel diskruimte = new JLabel("Diskruimte:" + SSHConnectie.SSHConnect(SSHUsername, IPv4, SSHPassword, "df -h --output=pcent --total | awk 'END {print $1}'"));
                        diskruimte.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
                        diskruimte.setBounds(x, y + 60, 200, 25);
                        add(diskruimte);
                    }
                    y += 100;
                    aantalcomponenten++;
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
            repaint();
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread(this, "test1");
            t.start();
        }
    }
}
