package sur.softsurena.entidades;

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
public class OpcionIT {
    
    public OpcionIT() {
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
     * Test of getValor method, of class Opcion.
     */
    @Test
    public void testGetValor() {
        System.out.println("getValor");
        Opcion instance = null;
        String expResult = "";
        String result = instance.getValor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescripcion method, of class Opcion.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        Opcion instance = null;
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Opcion.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Opcion instance = null;
        Integer expResult = null;
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Opcion.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Opcion instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
