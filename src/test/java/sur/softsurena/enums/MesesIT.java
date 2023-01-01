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
public class MesesIT {
    
    public MesesIT() {
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
     * Test of values method, of class Meses.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        Meses[] expResult = null;
        Meses[] result = Meses.values();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of valueOf method, of class Meses.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String string = "";
        Meses expResult = null;
        Meses result = Meses.valueOf(string);
        assertEquals(expResult, result);
    }

    /**
     * Test of main method, of class Meses.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Meses.main(args);
    }

    /**
     * Test of getAbreviatura method, of class Meses.
     */
    @Test
    public void testGetAbreviatura() {
        System.out.println("getAbreviatura");
        Meses instance = null;
        String expResult = "";
        String result = instance.getAbreviatura();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMinuscula method, of class Meses.
     */
    @Test
    public void testGetMinuscula() {
        System.out.println("getMinuscula");
        Meses instance = null;
        String expResult = "";
        String result = instance.getMinuscula();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOracion method, of class Meses.
     */
    @Test
    public void testGetOracion() {
        System.out.println("getOracion");
        Meses instance = null;
        String expResult = "";
        String result = instance.getOracion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrden method, of class Meses.
     */
    @Test
    public void testGetOrden() {
        System.out.println("getOrden");
        Meses instance = null;
        int expResult = 0;
        int result = instance.getOrden();
        assertEquals(expResult, result);
    }
    
}
