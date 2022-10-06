import edu.jdrouin.eesc.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Flappy extends Canvas implements KeyListener {


    final BufferedImage imageFondEcran = ImageIO.read(new File("C:\\CDA CCI\\Module Java\\Flappy_eesc\\src\\main\\resources\\PNG\\fondEcran.png"));

    protected static int largeurEcran = 800;
    protected static int hauteurEcran = 600;

    protected boolean pause = false;
    protected Font font1 = new Font("Arial", Font.BOLD, 40);

    protected Oiseau oiseau;
    protected Tuyau tuyau;

    protected ArrayList<Deplacable> listeDeplacable = new ArrayList<>();
    protected ArrayList<Sprite> listeSprite = new ArrayList<>();

    protected ArrayList<Tuyau> listeTuyaux = new ArrayList<>();

    public Flappy() throws InterruptedException, IOException {

        JFrame fenetre = new JFrame("Flappy");
        //On récupère le panneau de la fenetre principale
        JPanel panneau = (JPanel) fenetre.getContentPane();


        //On définie la hauteur / largeur de l'écran
        panneau.setPreferredSize(new Dimension(largeurEcran, this.hauteurEcran));
        setBounds(0, 0, this.largeurEcran,this.hauteurEcran);

        //On ajoute cette classe (qui hérite de Canvas) comme composant du panneau principal
        panneau.add(this);


        fenetre.pack();
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.requestFocus();
        fenetre.addKeyListener(this);

        //Label du compteur
        JLabel counterLabel;
        Font font1 = new Font("Arial", Font.BOLD, 80);
        counterLabel = new JLabel("Test");
//        counterLabel.setBounds(0, 0, largeurEcran /2 ,hauteurEcran / 2);
        counterLabel.setVerticalAlignment(JLabel.CENTER);
        panneau.add(counterLabel);



        //On indique que le raffraichissement de l'ecran doit être fait manuellement.
        this.createBufferStrategy(2);
        this.setIgnoreRepaint(true);
        this.setFocusable(false);



        this.demarrer();
    }
    public void initialiser() throws IOException {

        pause = false;

        //si c'est la première initialisation
        if(oiseau == null) {
            oiseau = new Oiseau(hauteurEcran);
//            Nuage nuage = new Nuage(largeurEcran,  hauteurEcran);

            listeDeplacable.add(oiseau);
//            listeDeplacable.add(nuage);
            listeSprite.add(oiseau);
//            listeSprite.add(nuage);

            //ajout tuyaux
            for(int j = 0; j < 5; j ++) {
                tuyau = new Tuyau(200, hauteurEcran, largeurEcran);
                listeTuyaux.add(tuyau);
                listeDeplacable.add(tuyau);
                listeSprite.add(tuyau);
            }

            //ajout nuages
            for(int i = 0; i < 100; i ++){
                Nuage nuage = new Nuage(largeurEcran,  hauteurEcran);
                listeDeplacable.add(nuage);
                listeSprite.add(nuage);
            }

            //Affichage


        } else {
            for(Deplacable deplacable : listeDeplacable) {
                deplacable.reinitialiser(largeurEcran,hauteurEcran);
            }
        }
    }

    public void demarrer() throws InterruptedException, IOException {

        long indexFrame = 0;

        initialiser();

        while(true) {

            indexFrame ++;
            Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();

            //-----------------------------
            //reset dessin
//            dessin.setColor(Color.CYAN);
//            dessin.fillRect(0,0,largeurEcran,hauteurEcran);

            dessin.drawImage(imageFondEcran, 0, 0 , largeurEcran, hauteurEcran, null);

            //Affichage

            dessin.setColor(Color.BLACK);
            dessin.setFont(font1);
            dessin.drawString(
                    String.valueOf(font1),
                    largeurEcran - 100,
                    50
            );

            for(Sprite sprite : listeSprite) {
                    sprite.dessiner(dessin);
            }


            if(!pause) {
                //-----si jamais l'oiseau est tombé par terre ---
                if (oiseau.getY() > hauteurEcran - oiseau.getLargeur()) {
                    System.out.println("perdu");
                    pause = true;
                } else {
                    //----sinon si le jeu continu ----
//                    oiseau.deplacer();
//                    tuyau.deplacer();


                    for(Deplacable deplacable : listeDeplacable) {
                        deplacable.deplacer(largeurEcran, hauteurEcran);
                    }

                    for(Tuyau tuyau : listeTuyaux){
                        if (tuyau.getX() > largeurEcran / 2) {
                            tuyau.dessiner(dessin);
                            tuyau.deplacer(largeurEcran, hauteurEcran);

                        } else {
                            tuyau.deplacer(largeurEcran, hauteurEcran);

                        }
                    }

                    if(Sprite.testCollision(oiseau,tuyau)){
                        System.out.println("perdu");
                        pause = true;
                    }


                }

            } else {
                dessin.setColor(new Color(0,0,0,0.1f));
                dessin.fillRect(0,0,largeurEcran,hauteurEcran);
            }

            //-----------------------------
            dessin.dispose();
            getBufferStrategy().show();
            Thread.sleep(1000 / 200);
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
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
            oiseau.setVitesseVertical(2);
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            try {
                initialiser();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_P){
            //inverser un booléen
            pause = !pause;
        }
    }
}
