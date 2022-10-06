package edu.jdrouin.eesc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tuyau extends Rectangle implements Deplacable{


    final BufferedImage imageTuyau = ImageIO.read(new File("C:\\CDA CCI\\Module Java\\Flappy_eesc\\src\\main\\resources\\PNG\\marioTube.png"));

    //-------------CONSTRUCTS---------------

    public Tuyau(int hauteur, int hauteurEcran, int largeurEcran) throws IOException {
        super(largeurEcran - 100, hauteurEcran - hauteur, 100, hauteur);
    }


    //-------------METHODS---------------

    @Override
    public void dessiner(Graphics2D dessin) {
//        dessin.setColor(couleur);
//        dessin.fillRect(x, y, largeur, hauteur);
         dessin.drawImage(imageTuyau, x,y, largeur, hauteur, null);

    }

    @Override
    public void dessiner2(Graphics2D dessin) {
//        dessin.setColor(couleur);
//        dessin.fillRect(x, y, largeur, hauteur);
        dessin.drawImage(imageTuyau, x,y, largeur, hauteur, null);

    }


    @Override
    public void deplacer(int largeurEcran, int hauteurEcran) {
        x --;
        if(x < -largeur){
            reinitialiser(largeurEcran, hauteurEcran);
            x+= largeurEcran;
        }
        if (Math.random() < largeurEcran && Math.random() > hauteurEcran) {
            y--;
        }
    }


    public void reinitialiser (int largeurEcran, int hauteurEcran){
//          x = largeurEcran;

        y = (int)(Math.random() * (hauteurEcran / 2));
//        if (Math.random() < largeurEcran && Math.random() > hauteurEcran){
//            y = hauteurEcran;
//        } else {
//        x = (int)(Math.random() * largeurEcran );
//        }
    }




    //-------------GETTER/SETTER---------------


}
