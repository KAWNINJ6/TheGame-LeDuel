package appli;

import appli.Composants.Cartes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartesTest {

    /**
     * Test qui permet de creer un paquet de carte et vérifie
     * qu'ils existent bien 60 cartes
     */
    @Test
    public void testCreationCartes(){
        Cartes c1 = new Cartes();

        assertEquals(60,c1.getNbCarte());
    }

    /**
     * Test qui verifie si une carte est bien recupéré lorsqu'on pioche
     */
    @Test
    public void testPiocher(){
        Cartes c1 = new Cartes();

        c1.prendreCarte();

        assertEquals(59,c1.getNbCarte());
    }

    /**
     * Test qui verifie si la premiére carte est bien recupéré
     */
    @Test
    public void testPiocherPrem(){
        Cartes c1 = new Cartes();

        assertEquals(1, c1.prendrePremCarte());
    }

    /**
     * Test qui verifie si la dernière carte est bien recupéré
     */
    @Test
    public void testPiocherDern(){
        Cartes c1 = new Cartes();

        assertEquals(60,c1.prendreDernCarte());
    }

    /**
     * Test qui verifie que le nombre de cartes dans un paquet de cartes
     * est cohérent
     */
    @Test
    public void testNbCarte(){
        Cartes c1 = new Cartes();

        assertEquals(60,c1.getNbCarte());
        c1.prendreCarte();
        assertEquals(59,c1.getNbCarte());
    }
}