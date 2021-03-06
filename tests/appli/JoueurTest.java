package appli;

import org.junit.jupiter.api.Test;

import static appli.NomJoueur.NORD;
import static appli.NomJoueur.SUD;
import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {

    /**
     * Verifie le bon fonctionnement du nommage des joueurs
     * + la méthode getNom();
     */
    @Test
    public void testMethodJoueur()
    {
        Joueur j1 = new Joueur();
        Joueur j2 = new Joueur();

        assertEquals(NORD, j1.getNom());
        assertEquals(SUD, j2.getNom());
    }

    /**
     * Verifie si les methodes de pose de carte fonctionnent
     * + la méthode aCetteCarteEnMain();
     * + la méthode getPile();
     *
     * @return          le joueur aprés avoir poser deux cartes
     */
    @Test
    public Joueur testPoseCartePile()
    {
        Joueur joueur = new Joueur();
        Integer i1 = 0, i2 = 0;

        for (int i = 0; i < 60; ++i) {
            i1 = i;

            if (joueur.aCetteCarteEnMain(i1)) {
                for (int y = i + 1; y < 60; ++y) {
                    i2 = y;

                    if (joueur.aCetteCarteEnMain(i2)) {
                        break;
                    }
                }
                break;
            }
        }

        joueur.poseCartePileAsc(i1);
        joueur.poseCartePileDesc(i2);

        assertEquals(i1, joueur.getPile('^'));
        assertEquals(i2, joueur.getPile('v'));

        return joueur;
    }

    /**
     * test la methode nbDeCarteEnMain(); si celle-ci
     * donne le bon nombre de cartes
     */
    @Test
    public void testNbDeCarteEnMain()
    {
        Joueur joueur = testPoseCartePile();
        assertEquals(4, joueur.nbDeCarteEnMain());
    }

    /**
     * Verifie le nombre de carte dans la pioche
     * après avoir retirer 2 cartes
     */
    @Test
    public void testNbDeCarteDansPioche()
    {
        Joueur joueur = testPoseCartePile();
        joueur.remplirMain();
        assertEquals(50, joueur.nbDeCarteDansPioche());
    }

    /**
     * Verifie que la main est remplie après avoir
     * appeler la methode remplirMainComplet();
     */
    @Test
    public void testRemplirMainComplet()
    {
        Joueur joueur = testPoseCartePile();
        assertEquals(4, joueur.nbDeCarteEnMain());

        joueur.remplirMainComplet();
        assertEquals(6, joueur.nbDeCarteEnMain());
    }

    /**
     * Verifie que la main est remplie après avoir
     * appeler la methode remplirMain();
     */
    @Test
    public void testRemplirMain()
    {
        Joueur joueur = testPoseCartePile();
        assertEquals(4, joueur.nbDeCarteEnMain());

        joueur.remplirMainComplet();
        assertEquals(6, joueur.nbDeCarteEnMain());
    }
}