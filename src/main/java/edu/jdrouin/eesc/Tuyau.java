package edu.jdrouin.eesc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tuyau extends Rectangle implements Deplacable{

protected int distanceEntreTuyau;
    protected BufferedImage imageTuyau;

    //-------------CONSTRUCTS---------------

    public Tuyau(int hauteur, int hauteurEcran,  int largeurEcran, int distanceEntreTuyau, BufferedImage imageTuyau) throws IOException {
        super(largeurEcran, hauteurEcran - hauteur, 100, hauteur);
        this.distanceEntreTuyau = distanceEntreTuyau;
        reinitialiser(largeurEcran, hauteurEcran);
        this.imageTuyau=imageTuyau;
    }



    //-------------METHODS---------------

    @Override
    public void dessiner(Graphics2D dessin) {
//        dessin.setColor(couleur);
//        dessin.fillRect(x, y, largeur, hauteur);
         dessin.drawImage(imageTuyau, x,y, largeur, hauteur, null);

    }

    @Override
    public void deplacer(int largeurEcran, int hauteurEcran) {
        x --;
        if(x < -largeur){
            x=largeurEcran;
        }
    }

    @Override
    public void reinitialiser(int largeurEcran, int hauteurEcran) {

        x = largeurEcran + distanceEntreTuyau;
    }

    public void reinitialiser (int largeurEcran, int hauteurEcran, int distanceEntreTuyau){
//          x = largeurEcran;


//        if (Math.random() < largeurEcran && Math.random() > hauteurEcran){
//            y = hauteurEcran;
//        } else {
//        x = (int)(Math.random() * largeurEcran );
//        }
    }

    public boolean activer (int largeurEcran){
            return (x > largeurEcran / 2);
    }




    //-------------GETTER/SETTER---------------


}
