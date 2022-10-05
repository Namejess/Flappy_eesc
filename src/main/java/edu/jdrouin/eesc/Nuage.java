package edu.jdrouin.eesc;

import java.awt.*;

public class Nuage extends Rectangle implements Deplacable {


    //-------------CONSTRUCT---------------
    public Nuage(int largeurEcran, int hauteurEcran) {
        super(0, 0, 0, 0, new Color(0, 0,0,0.05f));
        reinitialiser(largeurEcran, hauteurEcran);
    }

    //-------------METHODS---------------
    @Override
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillRect(x, y , largeur, hauteur);
    }

    @Override
    public void deplacer() {
        x--;
    }

    @Override
    public void reinitialiser(int largeurEcran, int hauteurEcran) {
        x = largeurEcran;
        y = 100;
        largeur = 100;
        hauteur = 20;
    }
}
