package edu.jdrouin.eesc;

import java.awt.*;

public class Oiseau extends Carre{

    protected int vitesseVertical;
    protected final static int HAUTEUR_OISEAU = 40;


//////////////////////////////////////
//           CONSTRUCT              //
//////////////////////////////////////
    public Oiseau(int x, int y) {
        super(x, y, HAUTEUR_OISEAU);
        this.vitesseVertical = 0;
    }

    public Oiseau(int hauteurEcran) {
        super(50, hauteurEcran / 2 - HAUTEUR_OISEAU / 2, HAUTEUR_OISEAU);
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

    @Override
    public int getCentreY() {
        return super.getCentreY();
    }

    public void sauter() {

    }

    public void deplacement(){
        y -= vitesseVertical;
        vitesseVertical -= 0.5f;
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
