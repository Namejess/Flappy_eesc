package edu.jdrouin.eesc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Oiseau extends Carre implements Deplacable{

    //-------------ATTRIBUTS---------------
    protected float vitesseVertical;
    protected final static int HAUTEUR_OISEAU = 40;
    final BufferedImage imageFlappy = ImageIO.read(new File("C:\\CDA CCI\\Module Java\\Flappy_eesc\\src\\main\\resources\\PNG\\flappy.png"));



    //-------------CONSTRUCT---------------
    public Oiseau(int x, int y) throws IOException {
        super(x, y, HAUTEUR_OISEAU);
        this.vitesseVertical = 0;
    }

    public Oiseau(int hauteurEcran) throws IOException {
        super(50, 0, HAUTEUR_OISEAU);
        reinitialiser(0, hauteurEcran);
        this.vitesseVertical = 0;
    }


    //-------------METHODS---------------


    public void reinitialiser (int largeurEcran, int hauteurEcran){
        y = hauteurEcran / 2 - HAUTEUR_OISEAU / 2;
        vitesseVertical = 0;

    }

    @Override
    public void dessiner(Graphics2D dessin) {
//        dessin.setColor(couleur);
//        dessin.fillRect(x, y, largeur, largeur);
        dessin.drawImage(imageFlappy, x,y, largeur, largeur, null);

    }

    @Override
    public int getCentreY() {
        return super.getCentreY();
    }

    public void sauter() {
        vitesseVertical = 2;
    }
    @Override
    public void deplacer(int largeurEcran, int hauteurEcran){

        //Correction de la gravité
        // Si la vitesse est comprise en -0,1 et -0,9 on augmente légèrement la gravité
        if (vitesseVertical % 10 != 0 && vitesseVertical < 0){
            y -= vitesseVertical - 0.5f;
        } else {
            y -= vitesseVertical;
        }

        vitesseVertical -= 0.05f;

        if(y < 0){
            vitesseVertical = Math.abs(vitesseVertical) *- 1;
        }
    }

    //-------------GETTER/SETTER---------------

    public float getVitesseVertical() {
        return vitesseVertical;
    }

    public void setVitesseVertical(float vitesseVertical) {
        this.vitesseVertical = vitesseVertical;
    }
}
