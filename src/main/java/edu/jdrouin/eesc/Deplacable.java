package edu.jdrouin.eesc;

public interface Deplacable {
    void deplacer(int largeurEcran, int hauteurEcran) throws InterruptedException;

    void reinitialiser(int largeurEcran, int hauteurEcran);

}

