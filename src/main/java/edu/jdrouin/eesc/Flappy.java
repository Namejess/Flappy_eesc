package edu.jdrouin.eesc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Flappy extends Canvas implements KeyListener {

    protected int largeurEcran = 600;
    protected int hauteurEcran = 600;

    protected boolean pause = false;
    protected Oiseau oiseau;
    protected Tuyau tuyau;

    protected ArrayList<Deplacable> listeDeplacable = new ArrayList<>();
    protected ArrayList<Sprite> listeSprite = new ArrayList<>();

    public Flappy() throws InterruptedException {
        JFrame fenetre = new JFrame("Flappy");
        //On récupère le panneau de la fenetre principale
        JPanel panneau = (JPanel) fenetre.getContentPane();
        //On définie la hauteur / largeur de l'écran
        panneau.setPreferredSize(new Dimension(largeurEcran, hauteurEcran));
        setBounds(0, 0, largeurEcran,hauteurEcran);
        //On ajoute cette classe (qui hérite de Canvas) comme composant du panneau principal
        panneau.add(this);

        fenetre.pack();
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.requestFocus();
        fenetre.addKeyListener(Flappy.this);

        //On indique que le raffraichissement de l'ecran doit être fait manuellement.
        createBufferStrategy(2);
        setIgnoreRepaint(true);
        this.setFocusable(false);

        demarrer();
    }

    //-------------METHODS---------------

    public void initialiser() {

        pause = false;
        //--------Si 1er init---------
        if (oiseau == null) {
            oiseau = new Oiseau(hauteurEcran);
//            oiseau.setVitesseVertical(-1);

            tuyau = new Tuyau(200,
                    hauteurEcran,
                    largeurEcran);
            Nuage nuage = new Nuage(largeurEcran, hauteurEcran);

            listeDeplacable.add(oiseau);
            listeDeplacable.add(tuyau);
            listeDeplacable.add(nuage);

            listeSprite.add(tuyau);
            listeSprite.add(oiseau);
            listeSprite.add(nuage);


        } else {
            for (Deplacable deplacable : listeDeplacable){
                deplacable.reinitialiser(largeurEcran, hauteurEcran);
            }

        }

    }

    public void demarrer() throws InterruptedException {

        long indexFrame = 0;

        initialiser();

        while(true) {

            indexFrame ++;
            Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();

            //---------reset dessin------------------
            dessin.setColor(Color.WHITE);
            dessin.fillRect(0,
                    0,
                    largeurEcran,
                    hauteurEcran);

            for (Sprite sprite : listeSprite){
                sprite.dessiner(dessin);
            }

            if (!pause) {
                //------Si jamais l'oiseau est tombé par terre--------
                if (oiseau.getY() > hauteurEcran - oiseau.getLargeur()) {
                    System.out.println("Perdu");
                    pause = true;
                } else {
                    //------Sinon si le jeu continue---------
                    for (Deplacable deplacable : listeDeplacable) {
                        deplacable.deplacer();
                    }

                }

            } else {
                dessin.setColor(new Color(0, 0, 0, 0.4f));
                dessin.fillRect(0, 0, largeurEcran, hauteurEcran);
            }
                //-----------------------------
                dessin.dispose();
                getBufferStrategy().show();
                Thread.sleep(1000 / 60);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Flappy();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            oiseau.setVitesseVertical(2.8f);
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            initialiser();
        }

        if(e.getKeyCode() == KeyEvent.VK_P){
            //inverser un boolean
            pause = !pause;
        }

    }
}