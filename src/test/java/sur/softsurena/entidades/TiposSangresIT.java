package sur.softsurena.entidades;

import java.util.List;
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
public class TiposSangresIT {
    
    public TiposSangresIT() {
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
     * Test of getTipoSangre method, of class TiposSangres.
     */
    @Test
    public void testGetTipoSangre() {
        System.out.println("getTipoSangre");
        List<TiposSangres> expResult = null;
        List<TiposSangres> result = TiposSangres.getTipoSangre();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class TiposSangres.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        TiposSangres instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class TiposSangres.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        TiposSangres.TiposSangresBuilder expResult = null;
        TiposSangres.TiposSangresBuilder result = TiposSangres.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class TiposSangres.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        TiposSangres instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescripcion method, of class TiposSangres.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        TiposSangres instance = null;
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
    }
    
}
