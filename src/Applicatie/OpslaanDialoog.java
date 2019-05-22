package Applicatie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class OpslaanDialoog extends JDialog implements ActionListener {
    private HuidigeConfiguratie netwerk;
    private int hoogsteID;
    JDBC database = new JDBC();
    LocalDate datum = LocalDate.now(ZoneId.of("Europe/Amsterdam"));
    JTextField opslaannaam;
    JButton ODopslaanbutton;

    public OpslaanDialoog(JFrame frame1) {
        super(frame1, true);
        setLayout(null);
        setTitle("Opslaan");
        setSize(500, 150);
        opslaannaam = new JTextField("vul een naam in");
        opslaannaam.setBounds(25, 25, 290, 25);
        this.add(opslaannaam);

        ODopslaanbutton = new JButton("opslaan");
        ODopslaanbutton.setBounds(325, 25, 125, 25);
        ODopslaanbutton.addActionListener(this);
        this.add(ODopslaanbutton);
    }

    public OpslaanDialoog(JFrame frame1, ConfiguratiePanel config1) {
        this(frame1);
        netwerk = config1.netwerk;
    }

    public OpslaanDialoog(JFrame frame1, OptimaliseringPanel config1) {
        this(frame1);
        netwerk = config1.netwerk;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ODopslaanbutton) {
            //hoogste netwerkID ophalen, zodat de primary key steeds 1 hoger wordt
            ResultSet resultaat = database.dataOphalen("SELECT MAX(netwerkID) FROM netwerk");
            try {
                while (resultaat.next()) {
                    //hoogste netwerkID opslaan als een int    
                    hoogsteID = resultaat.getInt("MAX(netwerkID)");
                    hoogsteID++;
                    netwerk.configuratieNaarDatabase("INSERT INTO netwerk (netwerkID, datum, beschikbaarheidspercentage, naam, prijs) VALUES (" + hoogsteID + ", \"" + datum + "\"," + netwerk.berekenBeschikbaarheid() + ", \"" + opslaannaam.getText() + "\"," + netwerk.dbTotalePrijs() + ")");
                }
                for (NetwerkComponent component : netwerk.getNetwerkLijst()) {
                    netwerk.configuratieNaarDatabase("insert into netwerkregel (netwerkID, itemID) VALUES (" + hoogsteID + "," + component.getItemID() + ");");
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
            dispose();
        }
    }
}
