package appli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartesTest {

    /**
     * Test que permet de creer un packet des cartes et regardes combient de cartes sont dans le packet
     */
    @Test
    public void testCreationCartes(){
        Cartes c1 = new Cartes();

        assertEquals(60,c1.getNbCarte());
    }

    /**
     * Test que permet de retirer une carte de le packet et de la metre dans la main et regardes combient de cartes sont dans le packet
     */
    @Test
    public void testPiocher(){
        Cartes c1 = new Cartes();

        c1.prendreCarte();

        assertEquals(59,c1.getNbCarte());
    }

    /**
     * Test que permet la premier carte de le packet (1)
     */
    @Test
    public void testPiocherPrem(){
        Cartes c1 = new Cartes();

        assertEquals(1, c1.prendrePremCarte());
    }

    /**
     * Test que permet la dernier carte de le packet (60)
     */
    @Test
    public void testPiocherDern(){
        Cartes c1 = new Cartes();

        assertEquals(60,c1.prendreDernCarte());
    }

    /**
     * Test que permet de savoir combien de carte sont dans le packets en retirent quelques unes
     */
    @Test
    public void testNbCarte(){
        Cartes c1 = new Cartes();

        assertEquals(60,c1.getNbCarte());
        c1.prendreCarte();
        assertEquals(59,c1.getNbCarte());
    }
}