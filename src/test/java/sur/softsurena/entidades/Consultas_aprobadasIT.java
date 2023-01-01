package sur.softsurena.entidades;

import java.math.BigDecimal;
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
public class Consultas_aprobadasIT {
    
    public Consultas_aprobadasIT() {
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
     * Test of agregarConsultaVerificada method, of class Consultas_aprobadas.
     */
    @Test
    public void testAgregarConsultaVerificada() {
        System.out.println("agregarConsultaVerificada");
        Consultas_aprobadas ca = null;
        String expResult = "";
        String result = Consultas_aprobadas.agregarConsultaVerificada(ca);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Consultas_aprobadas.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Consultas_aprobadas instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Consultas_aprobadas.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Consultas_aprobadas.Consultas_aprobadasBuilder expResult = null;
        Consultas_aprobadas.Consultas_aprobadasBuilder result = Consultas_aprobadas.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Consultas_aprobadas.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Consultas_aprobadas instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCodAutorizacion method, of class Consultas_aprobadas.
     */
    @Test
    public void testGetCodAutorizacion() {
        System.out.println("getCodAutorizacion");
        Consultas_aprobadas instance = null;
        String expResult = "";
        String result = instance.getCodAutorizacion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCosto method, of class Consultas_aprobadas.
     */
    @Test
    public void testGetCosto() {
        System.out.println("getCosto");
        Consultas_aprobadas instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.getCosto();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescuento method, of class Consultas_aprobadas.
     */
    @Test
    public void testGetDescuento() {
        System.out.println("getDescuento");
        Consultas_aprobadas instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.getDescuento();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsuario method, of class Consultas_aprobadas.
     */
    @Test
    public void testGetUsuario() {
        System.out.println("getUsuario");
        Consultas_aprobadas instance = null;
        String expResult = "";
        String result = instance.getUsuario();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotalCosto method, of class Consultas_aprobadas.
     */
    @Test
    public void testGetTotalCosto() {
        System.out.println("getTotalCosto");
        Consultas_aprobadas instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.getTotalCosto();
        assertEquals(expResult, result);
    }
    
}
