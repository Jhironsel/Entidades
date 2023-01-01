package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.ResultSet;
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
public class ARSIT {
    
    public ARSIT() {
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
     * Test of getARS method, of class ARS.
     */
    @Test
    public void testGetARS() {
        System.out.println("getARS");
        ResultSet expResult = null;
        ResultSet result = ARS.getARS();
        assertEquals(expResult, result);
    }

    /**
     * Test of borrarSeguro method, of class ARS.
     */
    @Test
    public void testBorrarSeguro() {
        System.out.println("borrarSeguro");
        int idARS = 0;
        String expResult = "";
        String result = ARS.borrarSeguro(idARS);
        assertEquals(expResult, result);
    }

    /**
     * Test of agregarSeguro method, of class ARS.
     */
    @Test
    public void testAgregarSeguro() {
        System.out.println("agregarSeguro");
        ARS a = null;
        String expResult = "";
        String result = ARS.agregarSeguro(a);
        assertEquals(expResult, result);
    }

    /**
     * Test of modificarSeguro method, of class ARS.
     */
    @Test
    public void testModificarSeguro() {
        System.out.println("modificarSeguro");
        ARS ars = null;
        String expResult = "";
        String result = ARS.modificarSeguro(ars);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTipoSeguro method, of class ARS.
     */
    @Test
    public void testGetTipoSeguro() {
        System.out.println("getTipoSeguro");
        ResultSet expResult = null;
        ResultSet result = ARS.getTipoSeguro();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class ARS.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ARS instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class ARS.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        ARS.ARSBuilder expResult = null;
        ARS.ARSBuilder result = ARS.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class ARS.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        ARS instance = null;
        Integer expResult = null;
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescripcion method, of class ARS.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        ARS instance = null;
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCovertura method, of class ARS.
     */
    @Test
    public void testGetCovertura() {
        System.out.println("getCovertura");
        ARS instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.getCovertura();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEstado method, of class ARS.
     */
    @Test
    public void testGetEstado() {
        System.out.println("getEstado");
        ARS instance = null;
        Boolean expResult = null;
        Boolean result = instance.getEstado();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserName method, of class ARS.
     */
    @Test
    public void testGetUserName() {
        System.out.println("getUserName");
        ARS instance = null;
        String expResult = "";
        String result = instance.getUserName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRol method, of class ARS.
     */
    @Test
    public void testGetRol() {
        System.out.println("getRol");
        ARS instance = null;
        String expResult = "";
        String result = instance.getRol();
        assertEquals(expResult, result);
    }
    
}
