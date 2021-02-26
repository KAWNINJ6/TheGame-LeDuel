package appli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    /**
     *
     */
    @Test
    public void getNbCartesdansMain(){ //Test que permet de voir combien de cartes sont dans la main.
        Cartes c1 = new Cartes();
        Main m1 = new Main(c1);
        assertEquals(6, m1.getNbCarte());
    }

    /**
     * Test que permet de trouver une carte dans la main
     */
    @Test
    public void trvCarte(){
        Cartes c1 = new Cartes();
        Main m1 = new Main(c1);
        Integer i1 = m1.getMain(1);
        m1.setMain(i1);
        assertEquals(5,m1.trvCarte(i1));
    }

    /**
     * Test que permet de voir si une carte existe dans la main
     */
    @Test
    public void carteExiste(){
        Cartes c1 = new Cartes();
        Main m1 = new Main(c1);
        m1.getMain(1);
        m1.setMain(5);
        assertEquals(true,m1.carteExiste(5));
    }

    /**
     * Test que permet de voir placer er de retirer des cartes de la main et de voir
     * combien de cartes sont dans la main
     */
    @Test
    public void getAndSetMain(){
        Cartes c1 = new Cartes();
        Main m1 = new Main(c1);
        m1.getMain(1);
        assertEquals(5, m1.getNbCarte());
        m1.setMain(6);
        assertEquals(6, m1.getNbCarte());
    }
}