package edu.jdrouin.eesc;

import java.awt.*;

public class Oiseau extends Carre implements Deplacable{

    //-------------ATTRIBUTS---------------
    protected float vitesseVertical;
    protected final static int HAUTEUR_OISEAU = 40;


    //-------------CONSTRUCT---------------
    public Oiseau(int x, int y) {
        super(x, y, HAUTEUR_OISEAU);
        this.vitesseVertical = 0;
    }

    public Oiseau(int hauteurEcran) {
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
        dessin.setColor(couleur);
        dessin.fillRect(x, y, largeur, largeur);
    }

    @Override
    public int getCentreY() {
        return super.getCentreY();
    }

    public void sauter() {

    }
    @Override
    public void deplacer(){

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
