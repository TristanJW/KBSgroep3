package Applicatie;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ConfiguratiePanel extends JPanel implements ActionListener {

    private HuidigeConfiguratie netwerk;
    JButton DBserverbutton = new JButton();
    JButton firewallbutton = new JButton();
    JButton loadbalancerbutton = new JButton();
    JButton webserverbutton = new JButton();

    JLabel DBserverimage = new JLabel("dbserver.png");
    JLabel firewallimage = new JLabel("firewall.png");
    JLabel loadbalancerimage = new JLabel("loadbalancer.png");
    JLabel webserverimage = new JLabel("webserver.png");

    public ConfiguratiePanel() {

        try {

            Image DBserver = ImageIO.read(getClass().getResource("dbserver.png"));
            DBserverbutton.setIcon(new ImageIcon(DBserver));
            DBserverbutton.addActionListener(this);

            Image firewall = ImageIO.read(getClass().getResource("firewall.png"));
            firewallbutton.setIcon(new ImageIcon(firewall));
            firewallbutton.addActionListener(this);

            Image loadbalancer = ImageIO.read(getClass().getResource("loadbalancer.png"));
            loadbalancerbutton.setIcon(new ImageIcon(loadbalancer));
            loadbalancerbutton.addActionListener(this);

            Image webserver = ImageIO.read(getClass().getResource("webserver.png"));
            webserverbutton.setIcon(new ImageIcon(webserver));
            webserverbutton.addActionListener(this);

            add(DBserverbutton);
            add(firewallbutton);
            add(loadbalancerbutton);
            add(webserverbutton);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == DBserverbutton) {
            System.out.println("test");

            System.out.println("test");
        } else if (e.getSource() == firewallbutton) {
            System.out.println("test2");
        } else if (e.getSource() == loadbalancerbutton) {
            System.out.println("test3");
        } else if (e.getSource() == webserverbutton) {
            System.out.println("test4");
        }
    }
}
