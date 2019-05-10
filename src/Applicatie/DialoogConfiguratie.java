/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Applicatie;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author edith
 */
public class DialoogConfiguratie extends JDialog implements ActionListener {
    private JButton optimaliseer;
    private JButton terug;
    private JTextField percentage;
    private JLabel error;
    
    public DialoogConfiguratie(JFrame frame) {
        super(frame, true);
        setTitle("Optimaliseren");
        setSize(400, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setLayout(null);
        
        JLabel percentageL = new JLabel("Gewenste beschikbaarheidspercentage");
        percentageL.setBounds(25, 25, 250, 25);
        add(percentageL);
        
        percentage = new JTextField("");
        percentage.setBounds(275, 25, 50, 25);
        add(percentage);
        
        JLabel procent = new JLabel("%");
        procent.setBounds(330, 25, 250, 25);
        add(procent);
        
        optimaliseer = new JButton("Optimaliseer");
        optimaliseer.setBounds(250, 75, 115, 25);
        optimaliseer.addActionListener(this);
        add(optimaliseer);
        
        terug = new JButton("Terug");
        terug.setBounds(25, 75, 75, 25);
        terug.addActionListener(this);
        add(terug);
        
        error = new JLabel("");
        error.setBounds(260, 50, 125, 20);
        
        add(error);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == terug) {
            System.out.println("test1");
            dispose();
        }
        else if(e.getSource() == optimaliseer) {
            try {
                int beschikbaarheid = Integer.parseInt(percentage.getText());
//                System.out.println(beschikbaarheid);
                dispose();
            } catch (NumberFormatException nfe) {
                error.setText("Voer een getal in");
//                System.out.println("Error");
            }


        }
    }
    


    
}
