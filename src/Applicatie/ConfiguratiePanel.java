package Applicatie;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ConfiguratiePanel extends JPanel {
    
    private HuidigeConfiguratie netwerk;

    public ConfiguratiePanel() {
    JButton button = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("resources/water.bmp"));
            button.setIcon(new ImageIcon(img));
    }   catch (Exception ex) {
        System.out.println(ex);
    }
    }
}

