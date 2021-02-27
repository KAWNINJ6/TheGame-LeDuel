package appli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseTest {

    /**
     * Test qui permet de placer les premieres cartes dans ces respectifs bases (asc et des)
     */
    @Test
    public void getPileAscEtDesc() {
        Cartes c1 = new Cartes();
        Base b1 = new Base(c1);

        assertEquals(1, b1.getCartePileAsc());
        assertEquals(60, b1.getCartePileDesc());
    }

    /**
     * Test qui permet de placer des cartes dans ces respectifs bases (asc et des)
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