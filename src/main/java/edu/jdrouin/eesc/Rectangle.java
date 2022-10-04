package edu.jdrouin.eesc;

import java.awt.*;

public abstract class Rectangle extends Carre {

    protected int hauteur;

//////////////////////////////////////
//           CONSTRUCT              //
//////////////////////////////////////
    public Rectangle(int x, int y, Color couleur) {
        super(x, y, couleur);
    }

//////////////////////////////////////
//           METHODS                //
//////////////////////////////////////

//////////////////////////////////////
//           GETTER SETTER          //
//////////////////////////////////////
    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }
}
