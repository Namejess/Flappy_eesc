package edu.jdrouin.eesc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

class IHMImages extends JPanel {
    private static final long serialVersionUID = 1L;
    Image lune, terre;

    IHMImages() {
        try {
            lune = ImageIO.read(new File("marioTube.png"));
            terre = ImageIO.read(new File("marioTube.png"));
        }
        catch(IOException exc) {
            exc.printStackTrace();
        }
        setPreferredSize(new Dimension(200, 200));
        setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(lune, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(terre, 30, 30, this);
    }

}

public class AfficheImage {
    public static void main(String[] arg) {
        JFrame fenetre = new JFrame();
        fenetre.setContentPane(new IHMImages());
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.pack();
        fenetre.setLocation(100, 100);
        fenetre.setVisible(true);
    }
}