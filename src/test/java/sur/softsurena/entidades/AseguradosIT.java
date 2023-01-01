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
public class AseguradosIT {
    
    public AseguradosIT() {
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
     * Test of toString method, of class Asegurados.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Asegurados instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Asegurados.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Asegurados.AseguradosBuilder expResult = null;
        Asegurados.AseguradosBuilder result = Asegurados.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_ars method, of class Asegurados.
     */
    @Test
    public void testGetId_ars() {
        System.out.println("getId_ars");
        Asegurados instance = null;
        int expResult = 0;
        int result = instance.getId_ars();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNo_nss method, of class Asegurados.
     */
    @Test
    public void testGetNo_nss() {
        System.out.println("getNo_nss");
        Asegurados instance = null;
        String expResult = "";
        String result = instance.getNo_nss();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEstado method, of class Asegurados.
     */
    @Test
    public void testGetEstado() {
        System.out.println("getEstado");
        Asegurados instance = null;
        Boolean expResult = null;
        Boolean result = instance.getEstado();
        assertEquals(expResult, result);
    }
    
}
