package appli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    @Test
    public void testDefaite()
    {
        Table t1 = new Table();

        assertTrue(t1.verifDefaiteJ1());
    }

}