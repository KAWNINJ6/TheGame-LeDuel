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

        assertEquals(1, b1.getPileAsc());
        assertEquals(60, b1.getPileDesc());
    }

}