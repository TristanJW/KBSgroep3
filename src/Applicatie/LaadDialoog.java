/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Applicatie;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Trist
 */
public class LaadDialoog extends JDialog implements ActionListener {
    //configuratie en optimaliserenpanel opslaan knop moet hieraan gelinked worden

    private JButton optimaliseer;
    private JButton terug;
    private JTextField percentage;
    private JLabel error;

    public LaadDialoog(JFrame frame1) {
        super(frame1, true);
        setTitle("Laad configuratie");
        setLayout(new FlowLayout());
        setSize(300, 150);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
