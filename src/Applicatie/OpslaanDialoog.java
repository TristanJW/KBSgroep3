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
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;

/**
 *
 * @author Trist
 */
public class OpslaanDialoog extends JDialog implements ActionListener {

    public OpslaanDialoog(JFrame frame1) {
        super(frame1, true);
        setTitle("Opslaan");
        setSize(300, 150);
        setVisible(true);

        JButton opslaanbutton = new JButton();
        opslaanbutton.setBounds(0, 0, 50, 50);
        opslaanbutton.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
