package sur.softsurena.entidades;

import java.sql.Timestamp;
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
public class RecetasIT {
    
    public RecetasIT() {
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
     * Test of agregarReceta method, of class Recetas.
     */
    @Test
    public void testAgregarReceta() {
        System.out.println("agregarReceta");
        int idPaciente = 0;
        int idConsulta = 0;
        int expResult = 0;
        int result = Recetas.agregarReceta(idPaciente, idConsulta);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Recetas.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Recetas instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Recetas.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Recetas.RecetasBuilder expResult = null;
        Recetas.RecetasBuilder result = Recetas.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Recetas.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Recetas instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdConsulta method, of class Recetas.
     */
    @Test
    public void testGetIdConsulta() {
        System.out.println("getIdConsulta");
        Recetas instance = null;
        int expResult = 0;
        int result = instance.getIdConsulta();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFecha method, of class Recetas.
     */
    @Test
    public void testGetFecha() {
        System.out.println("getFecha");
        Recetas instance = null;
        Timestamp expResult = null;
        Timestamp result = instance.getFecha();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsuario method, of class Recetas.
     */
    @Test
    public void testGetUsuario() {
        System.out.println("getUsuario");
        Recetas instance = null;
        String expResult = "";
        String result = instance.getUsuario();
        assertEquals(expResult, result);
    }
    
}
