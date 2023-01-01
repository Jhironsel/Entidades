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
public class EncabezadoIT {
    
    public EncabezadoIT() {
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
     * Test of getId method, of class Encabezado.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Encabezado instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNombreEmpresa method, of class Encabezado.
     */
    @Test
    public void testGetNombreEmpresa() {
        System.out.println("getNombreEmpresa");
        Encabezado instance = null;
        String expResult = "";
        String result = instance.getNombreEmpresa();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDireccionEmpresa method, of class Encabezado.
     */
    @Test
    public void testGetDireccionEmpresa() {
        System.out.println("getDireccionEmpresa");
        Encabezado instance = null;
        String expResult = "";
        String result = instance.getDireccionEmpresa();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTelefonoEmpresa method, of class Encabezado.
     */
    @Test
    public void testGetTelefonoEmpresa() {
        System.out.println("getTelefonoEmpresa");
        Encabezado instance = null;
        String expResult = "";
        String result = instance.getTelefonoEmpresa();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMensajeEmpresa method, of class Encabezado.
     */
    @Test
    public void testGetMensajeEmpresa() {
        System.out.println("getMensajeEmpresa");
        Encabezado instance = null;
        String expResult = "";
        String result = instance.getMensajeEmpresa();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Encabezado.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = null;
        Encabezado instance = null;
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of canEqual method, of class Encabezado.
     */
    @Test
    public void testCanEqual() {
        System.out.println("canEqual");
        Object other = null;
        Encabezado instance = null;
        boolean expResult = false;
        boolean result = instance.canEqual(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Encabezado.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Encabezado instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Encabezado.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Encabezado instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
