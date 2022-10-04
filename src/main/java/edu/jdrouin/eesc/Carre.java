package edu.jdrouin.eesc;

import java.awt.*;

public abstract class Carre extends Sprite {

    protected int largeur;


//////////////////////////////////////
//           CONSTRUCT              //
//////////////////////////////////////
    public Carre(int x, int y, Color couleur) {
        super(x, y, couleur);
        this.largeur = largeur;
    }

    public Carre(int x, int y, Color couleur, int largeur) {
        super(x, y, couleur);

    }

//////////////////////////////////////
//           METHODS                //
//////////////////////////////////////



//////////////////////////////////////
//           GETTER SETTER          //
//////////////////////////////////////

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }
}
