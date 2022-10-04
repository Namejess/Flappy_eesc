package edu.jdrouin.eesc;

import java.awt.*;

public class Oiseau extends Carre{

    protected int vitesseVertical;

//////////////////////////////////////
//           CONSTRUCT              //
//////////////////////////////////////
    public Oiseau(int x, int y, Color couleur) {
        super(x, y, couleur);
    }

//////////////////////////////////////
//           METHODS                //
//////////////////////////////////////
    @Override
    public void dessiner(Graphics2D dessin) {

    }

     public void sauter() {

    }

    public Oiseau(int x, int y, Color couleur, int vitesseVertical) {
        super(x, y, couleur);
        this.vitesseVertical = vitesseVertical;
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
