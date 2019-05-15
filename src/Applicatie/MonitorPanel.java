package Applicatie;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Scrollbar;
import javax.swing.*;
import java.sql.*;

public class MonitorPanel extends JPanel {
    private HuidigeConfiguratie netwerk;
    int y = 0;
    int x = 25;
    int aantalcomponenten = 0;
    
    public MonitorPanel(){
//        JPanel panel = new JPanel();
//        JScrollPane scrollPane = new JScrollPane();
//    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//    scrollPane.getViewport().add(panel);
//    scrollPane.getViewport().setPreferredSize(panel.getPreferredSize());         

    
        setLayout(null);
        JDBC database = new JDBC();
        
        ResultSet resultaat = database.dataOphalen("select * from leverancierslijst"); //query die uitgevoerd wordt in de databasse

        try {
            while (resultaat.next()) {
                //tweede kolom 
                if(aantalcomponenten == 4) {
                    x =+ 400;
                    y = 0;
                }

                int itemID = resultaat.getInt("itemID");
                String naam = resultaat.getString("naam"); //naam uit de database opslaan in een variabele
                
                JLabel componentNaam = new JLabel(naam);
                componentNaam.setFont(new Font("UIManager.getDefaults().getFont(\"TabbedPane.font\")", Font.BOLD, 18));
                componentNaam.setBounds(x+50, y, 150, 25);
                add(componentNaam);
                
                JLabel beschikbaarheid = new JLabel("Beschikbaarheidspercentage:");
                beschikbaarheid.setFont(new Font("UIManager.getDefaults().getFont(\"TabbedPane.font\")", Font.PLAIN, 14));
                beschikbaarheid.setBounds(x, 15+y, 300, 25);
                add(beschikbaarheid);
                
                JLabel beschikbaar = new JLabel("Tijd beschikbaar:");
                beschikbaar.setFont(new Font("UIManager.getDefaults().getFont(\"TabbedPane.font\")", Font.PLAIN, 14));
                beschikbaar.setBounds(x, 30+y, 200, 25);
                add(beschikbaar);
                
                JLabel processor = new JLabel("Processorbelasting:");
                processor.setFont(new Font("UIManager.getDefaults().getFont(\"TabbedPane.font\")", Font.PLAIN, 14));
                processor.setBounds(x, 45+y, 200, 25);
                add(processor);
                

                if(itemID != 1 && itemID != 2){
                    
//                System.out.println("test");
                JLabel diskruimte = new JLabel("Diskruimte:");
                diskruimte.setFont(new Font("UIManager.getDefaults().getFont(\"TabbedPane.font\")", Font.PLAIN, 14));
                diskruimte.setBounds(x, 60+y, 200, 25);
                add(diskruimte);
                }
                y += 100;
                aantalcomponenten++;
//                System.out.println(naam);
//                System.out.println(itemID);
//                System.out.println(aantalcomponenten);
                        
            }
            
        } catch (SQLException se) {
            se.printStackTrace();
        }
 
    }
}
