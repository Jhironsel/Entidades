package sur.softsurena.enums;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jhironsel
 */
public class DiasIT {
    
    public DiasIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of values method, of class Dias.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        Dias[] expResult = null;
        Dias[] result = Dias.values();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of valueOf method, of class Dias.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String string = "";
        Dias expResult = null;
        Dias result = Dias.valueOf(string);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAbreviatura method, of class Dias.
     */
    @Test
    public void testGetAbreviatura() {
        System.out.println("getAbreviatura");
        Dias instance = null;
        String expResult = "";
        String result = instance.getAbreviatura();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMinuscula method, of class Dias.
     */
    @Test
    public void testGetMinuscula() {
        System.out.println("getMinuscula");
        Dias instance = null;
        String expResult = "";
        String result = instance.getMinuscula();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOracion method, of class Dias.
     */
    @Test
    public void testGetOracion() {
        System.out.println("getOracion");
        Dias instance = null;
        String expResult = "";
        String result = instance.getOracion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrden method, of class Dias.
     */
    @Test
    public void testGetOrden() {
        System.out.println("getOrden");
        Dias instance = null;
        int expResult = 0;
        int result = instance.getOrden();
        assertEquals(expResult, result);
    }
    
}
