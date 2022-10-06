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

    //-------------ATTRIBUTS---------------
    protected static int largeurEcran = 800;
    protected static int hauteurEcran = 600;

    protected boolean pause = false;
    protected boolean perdu = false;
    protected Font font1 = new Font("Arial", Font.BOLD, 40);

    protected Oiseau oiseau;
    protected Tuyau tuyau;

    protected long ordreApparition;


    protected ArrayList<Deplacable> listeDeplacable = new ArrayList<>();
    protected ArrayList<Sprite> listeSprite = new ArrayList<>();
    protected ArrayList<Tuyau> listeTuyaux = new ArrayList<>();

    final BufferedImage imageFondEcran = ImageIO.read(new File("src\\main\\resources\\PNG\\fondEcran.png"));
    protected final BufferedImage imageTuyauA = ImageIO.read(new File("src\\main\\resources\\PNG\\marioTube2.png"));
    protected  final BufferedImage imageTuyauB = ImageIO.read(new File("src\\main\\resources\\PNG\\marioTube.png"));

    //-------------CANVA---------------

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

        //On indique que le raffraichissement de l'ecran doit être fait manuellement.
        this.createBufferStrategy(2);
        this.setIgnoreRepaint(true);
        this.setFocusable(false);


        this.demarrer();
    }


    //-------------INIT---------------

    public void initialiser() throws IOException {


        pause = false;
        perdu = false;

        //si c'est la première initialisation
        if(oiseau == null) {
            oiseau = new Oiseau(hauteurEcran);
            listeDeplacable.add(oiseau);
            listeSprite.add(oiseau);

        int nombreTuyau = 4;
        int distanceEntreTuyau = (largeurEcran+100) / nombreTuyau;
        //ajout tuyaux
        for(int ordreApparition = 0; ordreApparition < nombreTuyau; ordreApparition ++) {
            Tuyau tuyauA = new Tuyau(200, hauteurEcran, largeurEcran - 100, ordreApparition * distanceEntreTuyau, imageTuyauA);
            Tuyau tuyauB = new Tuyau(200, 200, largeurEcran - 100, ordreApparition * distanceEntreTuyau, imageTuyauB);


            listeTuyaux.add(tuyauA);
            listeDeplacable.add(tuyauA);
            listeSprite.add(tuyauA);

            listeTuyaux.add(tuyauB);
            listeDeplacable.add(tuyauB);
            listeSprite.add(tuyauB);

        }

        //ajout nuages
        for(int i  = 0; i < 15; i ++){
            Nuage nuage = new Nuage(largeurEcran,  hauteurEcran);
            listeDeplacable.add(nuage);
            listeSprite.add(nuage);
        }

        } else {
            for(Deplacable deplacable : listeDeplacable) {
                deplacable.reinitialiser(largeurEcran,hauteurEcran);
            }
        }
    }

    //-------------DEMARRAGE---------------


    public void demarrer() throws InterruptedException, IOException {

        long points = 0;

        initialiser();

        while(true) {

            Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();
            dessin.drawImage(imageFondEcran, 0, 0 , largeurEcran, hauteurEcran, null);

            for(Sprite sprite : listeSprite) {
                sprite.dessiner(dessin);
            }

            if(!perdu) {

                if (!pause) {

                    points++;
                    //-----si jamais l'oiseau est tombé par terre ---
                    if (oiseau.getY() > hauteurEcran - oiseau.getLargeur()) {
                        perdu = true;

                    } else {
                        //----sinon si le jeu continu ----

                        for (Deplacable deplacable : listeDeplacable) {
                            deplacable.deplacer(largeurEcran, hauteurEcran);
                        }

                        for (Tuyau tuyau : listeTuyaux) {
                            if (Sprite.testCollision(oiseau, tuyau)) {
                                System.out.println("perdu");
                                perdu = true;
                            }
                        }
                    }

                } else {
                    dessin.setColor(new Color(0, 0, 0, 0.1f));
                    dessin.fillRect(0, 0, largeurEcran, hauteurEcran);
                }

                //Affichage compteur
                dessin.setColor(Color.BLACK);
                dessin.setFont(font1);
                dessin.drawString(
                        String.valueOf(points),
                        largeurEcran - 100,
                        50
                );

            } else {
                dessin.setColor(Color.YELLOW);
                dessin.setFont(new Font("Arial", Font.BOLD, 60));

                int largeurText =  dessin.getFontMetrics().stringWidth("PERDU");

                dessin.drawString(
                        String.valueOf("PERDU"),
                        largeurEcran / 2 - largeurText / 2,
                        hauteurEcran / 2
                );

            }

            //-----------------------------
            dessin.dispose();
            getBufferStrategy().show();
            Thread.sleep(1000 / 200);
        }
    }

    //-------------MAIN---------------
    //--------------------------------


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
