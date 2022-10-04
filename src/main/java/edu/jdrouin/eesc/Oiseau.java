package edu.jdrouin.eesc;

import java.awt.*;

public class Oiseau extends Carre{

    protected int vitesseVertical;


//////////////////////////////////////
//           CONSTRUCT              //
//////////////////////////////////////
    public Oiseau(int x, int y) {
    super(x, y, 40);
    this.vitesseVertical = 0;
}


//////////////////////////////////////
//           METHODS                //
//////////////////////////////////////
    @Override
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillRect(x, y, largeur, largeur);
    }

     public void sauter() {

    }


    //////////////////////////////////////
//           GETTER SETTER          //
//////////////////////////////////////
    public int getVitesseVertical() {
        return vitesseVertical;
    }

    public void setVitesseVertical(int vitesseVertical) {
        this.vitesseVertical = vitesseVertical;
    }
}
