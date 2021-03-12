package appli;

import appli.Composants.Cartes;
import appli.Composants.Main;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    /**
     * Test qui verifie que le bon nombre de cartes est donné dans la main
     */
    @Test
    public void testMain()
    {
        Cartes c1 = new Cartes();
        Main m1 = new Main(c1);

        assertEquals(6, m1.getNbCarte());
    }

    /**
     * Test qui permet de savoir si on trouve bien la carte voulu, à l'indice demandé
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
     * Test qui verifie si une carte existe dans la main
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
     * Test qui vérifie si des cartes dans une main sont bien récupéré
     * grace au getter et que le setter foctionne bien
     *
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