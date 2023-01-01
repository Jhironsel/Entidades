package sur.softsurena.entidades;

import java.io.File;
import javax.swing.ImageIcon;
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
public class HuellasIT {
    
    public HuellasIT() {
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
     * Test of getId method, of class Huellas.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Huellas instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTipoDedo method, of class Huellas.
     */
    @Test
    public void testGetTipoDedo() {
        System.out.println("getTipoDedo");
        Huellas instance = null;
        String expResult = "";
        String result = instance.getTipoDedo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPathImagen method, of class Huellas.
     */
    @Test
    public void testGetPathImagen() {
        System.out.println("getPathImagen");
        Huellas instance = null;
        File expResult = null;
        File result = instance.getPathImagen();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImagen method, of class Huellas.
     */
    @Test
    public void testGetImagen() {
        System.out.println("getImagen");
        Huellas instance = null;
        ImageIcon expResult = null;
        ImageIcon result = instance.getImagen();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Huellas.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Huellas instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
