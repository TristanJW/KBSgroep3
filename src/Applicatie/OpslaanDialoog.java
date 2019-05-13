/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Applicatie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;

/**
 *
 * @author Trist
 */
public class OpslaanDialoog extends JDialog implements ActionListener {

    String dialoog1;

    public OpslaanDialoog() {
        String dialoog1 = JOptionPane.showInputDialog("vul een naam in");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (JOptionPane.OK_OPTION == 0) {
            System.out.println("TESTTESTTEST");
        }

    }

}
