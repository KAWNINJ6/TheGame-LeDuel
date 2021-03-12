package appli;

import appli.Composants.Base;
import appli.Composants.Cartes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseTest {

    /**
     * Test qui permet de verifié si les premieres (au top) cartes sont bien
     * recupéré dans leurs piles respectifs (asc et desc)
     */
    @Test
    public void getPileAscEtDesc() {
        Cartes c1 = new Cartes();
        Base b1 = new Base(c1);

        assertEquals(1, b1.getCartePileAsc());
        assertEquals(60, b1.getCartePileDesc());
    }

    /**
     * Test qui verifie si les cartes sont bien posé (au top)
     * dans leurs piles respectifs
     */
    @Test
    public void setPileAscEtDesc() {
        Cartes c1 = new Cartes();
        Base b1 = new Base(c1);

        Integer a1 = 6;
        Integer d1 = 54;
        b1.setCartePileAsc(a1);
        b1.setCartePileDesc(d1);

        assertEquals(a1, b1.getCartePileAsc());
        assertEquals(d1, b1.getCartePileDesc());
    }

}