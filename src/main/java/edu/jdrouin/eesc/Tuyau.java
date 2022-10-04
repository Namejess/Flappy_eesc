package edu.jdrouin.eesc;

import java.awt.*;

public class Tuyau extends Rectangle{

//////////////////////////////////////
//           CONSTRUCT              //
//////////////////////////////////////

    public Tuyau(int hauteur, int hauteurEcran, int largeurEcran) {
        super(largeurEcran - 100, hauteurEcran - hauteur, 100, hauteur);
    }


//////////////////////////////////////
//           METHODS                //
//////////////////////////////////////
    @Override
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillRect(x, y, largeur, hauteur);
    }

//////////////////////////////////////
//           GETTER SETTER          //
//////////////////////////////////////


}
