package Applicatie;

import javax.swing.*;
import java.sql.*;

public class MonitorPanel extends JPanel implements Runnable {
    private Thread t;
    private JDBC database = new JDBC();
    private Boolean dataOpgehaald = false;

    private String[] processors;
    private String[] diskruimtes;
    private String[] namen;
    private String[] beschikbaartijden;
    private String[] beschikbaarheden;

    private JLabel processor;
    private JLabel diskruimte;
    private JLabel componentNaam;
    private JLabel tijdBeschikbaar;
    private JLabel isBeschikbaar;

    public MonitorPanel() {
        setLayout(null);
        int y = 0;
        int x = 20;
        int aantalcomponenten = 0;

        try {
            ResultSet resultaat = database.dataOphalen("SELECT componentNaam FROM component"); //query die uitgevoerd wordt in de databasse
            while (resultaat.next()) {
                //tweede kolom
                if (aantalcomponenten == 4) {
                    x = +400;
                    y = 0;
                }
                String naam = resultaat.getString("componentNaam"); //naam uit de database opslaan in een variabele

                componentNaam = new JLabel(naam);
                componentNaam.setBounds(x, y, 150, 25);
                add(componentNaam);

                tijdBeschikbaar = new JLabel("Beschikbaar: ophalen...");
                tijdBeschikbaar.setBounds(x, y + 15, 300, 25);
                add(tijdBeschikbaar);

                isBeschikbaar = new JLabel("Tijd beschikbaar: ophalen...");
                isBeschikbaar.setBounds(x, y + 30, 300, 25);
                add(isBeschikbaar);

                processor = new JLabel("Processorbelasting: ophalen...");
                processor.setBounds(x, y + 45, 200, 25);
                add(processor);

                diskruimte = new JLabel("Diskruimte: ophalen...");
                diskruimte.setBounds(x, y + 60, 200, 25);
                add(diskruimte);

                y += 100;
                aantalcomponenten++;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        namen = new String[aantalcomponenten];
        processors = new String[aantalcomponenten];
        diskruimtes = new String[aantalcomponenten];
        beschikbaartijden = new String[aantalcomponenten];
        beschikbaarheden = new String[aantalcomponenten];
    }

    @Override
    public void run() {
        String SSHUsername = "student";
        String SSHPassword = "achmed459";
        while (true) {
            int y = 0;
            int x = 20;
            int aantalcomponenten = 0;
            ResultSet resultaat = database.dataOphalen("SELECT OS, componentNaam, IPv4, IPv6 FROM component"); //query die uitgevoerd wordt in de databasse
            if (!dataOpgehaald) {
                try {
                    while (resultaat.next()) {
                        String naam = resultaat.getString("componentNaam"); //naam uit de database opslaan in een variabele
                        String os = resultaat.getString("OS");
                        String IPv6 = resultaat.getString("IPv6");

                        namen[aantalcomponenten] = naam;
                        if (SSHConnectie.SSHConnect(SSHUsername, IPv6, SSHPassword, "ls -l").equals("Error...")) {
                            beschikbaarheden[aantalcomponenten] = "Beschikbaar: ❌";
                            // op null zetten zodat de vorige waardes uit de array gaan en niet meer geprint worden
                            beschikbaartijden[aantalcomponenten] = null;
                            processors[aantalcomponenten] = null;
                            diskruimtes[aantalcomponenten] = null;
                        } else {
                            beschikbaarheden[aantalcomponenten] = "Beschikbaar: ✅";
                            beschikbaartijden[aantalcomponenten] = ("Beschikbaar voor:") + (SSHConnectie.SSHConnect(SSHUsername, IPv6, SSHPassword, "uptime | awk -F',' '{print $1}'").trim().substring(11));
                            if (os.equals("FreeBSD")) {
                                String output = SSHConnectie.SSHConnect(SSHUsername, IPv6, SSHPassword, "top -d 2 | grep 'CPU:' | awk '{print $10}'");
                                String output_sub = output.trim().substring(0, output.length() - 2);
                                processors[aantalcomponenten] = ("Processorbelasting: " + (100 - Math.round(Double.parseDouble(output_sub))) + "%"); // we halen de idle cpu % op, en dat halen we van 100 af voor cpu%
                                diskruimtes[aantalcomponenten] = ("Diskruimte: " + SSHConnectie.SSHConnect(SSHUsername, IPv6, SSHPassword, "df -h | grep '/dev/gpt/pfSense' | awk '{print $5}'"));
                            } else if (os.equals("Ubuntu")) {
                                processors[aantalcomponenten] = ("Processorbelasting: " + SSHConnectie.SSHConnect(SSHUsername, IPv6, SSHPassword, "cat <(grep 'cpu ' /proc/stat) <(sleep 1 && grep 'cpu ' /proc/stat) | awk -v RS=\"\" '{print ($13-$2+$15-$4)*100/($13-$2+$15-$4+$16-$5) \"%\"}'"));
                                diskruimtes[aantalcomponenten] = ("Diskruimte: " + SSHConnectie.SSHConnect(SSHUsername, IPv6, SSHPassword, "df -h --output=pcent --total | awk 'END {print $1}'"));
                            }
                        }
                        aantalcomponenten++;
                        repaint();
                    }
                } catch (SQLException se) {
                    se.printStackTrace();
                }
                dataOpgehaald = true;
            } else {
                removeAll();

                for (int i = 0; i < processors.length; i++) {
                    if (aantalcomponenten == 4) {
                        x = +400;
                        y = 0;
                    }
                    componentNaam = new JLabel(namen[aantalcomponenten]);
                    componentNaam.setBounds(x, y, 150, 25);
                    add(componentNaam);

                    isBeschikbaar = new JLabel(beschikbaarheden[i]);
                    isBeschikbaar.setBounds(x, y + 15, 300, 25);
                    add(isBeschikbaar);

                    tijdBeschikbaar = new JLabel(beschikbaartijden[i]);
                    tijdBeschikbaar.setBounds(x, y + 30, 300, 25);
                    add(tijdBeschikbaar);

                    processor = new JLabel(processors[i]);
                    processor.setBounds(x, y + 45, 200, 25);
                    add(processor);

                    diskruimte = new JLabel(diskruimtes[i]);
                    diskruimte.setBounds(x, y + 60, 200, 25);
                    add(diskruimte);

                    y += 100;
                    aantalcomponenten++;
                    repaint();
                }
                dataOpgehaald = false;
            }
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread(this, "test1");
            t.start();
        }
    }

}
