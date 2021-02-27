package appli;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    /**
     * Test que permet de voir combien de cartes sont dans la main.
     */
    @Test
    public void testGetNbCartesdansMain()
    {
        Cartes c1 = new Cartes();
        Main m1 = new Main(c1);

        assertEquals(6, m1.getNbCarte());
    }

    /**
     * Test que permet de trouver une carte dans la main
     */
    @Test
    public void testTrvCarte()
    {
        Cartes c1 = new Cartes();
        Main m1 = new Main(c1);
        Integer i1 = m1.prendreCarte(1);

        m1.setCarte(i1);

        assertEquals(5,m1.trvIdxCarte(i1));
    }

    /**
     * Test que permet de voir si une carte existe dans la main
     */
    @Test
    public void testCarteExiste()
    {
        Cartes c1 = new Cartes();
        Main m1 = new Main(c1);

        m1.prendreCarte(1);
        m1.setCarte(5);

        assertTrue(m1.carteExiste(5));
    }

    /**
     * Test que permet de voir placer er de retirer des cartes de la main et de voir
     * combien de cartes sont dans la main
     */
    @Test
    public void testGetAndSetMain()
    {
        Cartes c1 = new Cartes();
        Main m1 = new Main(c1);
        Integer i1 = 12;

        m1.prendreCarte(0);
        assertEquals(5, m1.getNbCarte());

        m1.setCarte(i1);
        assertEquals(6, m1.getNbCarte());
    }
}