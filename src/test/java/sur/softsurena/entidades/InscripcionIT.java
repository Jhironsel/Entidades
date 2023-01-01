package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.Date;
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
public class InscripcionIT {
    
    public InscripcionIT() {
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
     * Test of getId method, of class Inscripcion.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Inscripcion instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_estudiante method, of class Inscripcion.
     */
    @Test
    public void testGetId_estudiante() {
        System.out.println("getId_estudiante");
        Inscripcion instance = null;
        int expResult = 0;
        int result = instance.getId_estudiante();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_padre_tutor method, of class Inscripcion.
     */
    @Test
    public void testGetId_padre_tutor() {
        System.out.println("getId_padre_tutor");
        Inscripcion instance = null;
        int expResult = 0;
        int result = instance.getId_padre_tutor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_tanda method, of class Inscripcion.
     */
    @Test
    public void testGetId_tanda() {
        System.out.println("getId_tanda");
        Inscripcion instance = null;
        int expResult = 0;
        int result = instance.getId_tanda();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPago method, of class Inscripcion.
     */
    @Test
    public void testGetPago() {
        System.out.println("getPago");
        Inscripcion instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.getPago();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFecha_inscripcion method, of class Inscripcion.
     */
    @Test
    public void testGetFecha_inscripcion() {
        System.out.println("getFecha_inscripcion");
        Inscripcion instance = null;
        Date expResult = null;
        Date result = instance.getFecha_inscripcion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdUsuario method, of class Inscripcion.
     */
    @Test
    public void testGetIdUsuario() {
        System.out.println("getIdUsuario");
        Inscripcion instance = null;
        String expResult = "";
        String result = instance.getIdUsuario();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Inscripcion.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Inscripcion instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
