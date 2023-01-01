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
public class GastosIT {
    
    public GastosIT() {
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
     * Test of agregarGastosPorTurno method, of class Gastos.
     */
    @Test
    public void testAgregarGastosPorTurno() {
        System.out.println("agregarGastosPorTurno");
        Gastos gasto = null;
        boolean expResult = false;
        boolean result = Gastos.agregarGastosPorTurno(gasto);
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Gastos.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Gastos.GastosBuilder expResult = null;
        Gastos.GastosBuilder result = Gastos.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Gastos.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Gastos instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_turno method, of class Gastos.
     */
    @Test
    public void testGetId_turno() {
        System.out.println("getId_turno");
        Gastos instance = null;
        int expResult = 0;
        int result = instance.getId_turno();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescripcion method, of class Gastos.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        Gastos instance = null;
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMonto method, of class Gastos.
     */
    @Test
    public void testGetMonto() {
        System.out.println("getMonto");
        Gastos instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.getMonto();
        assertEquals(expResult, result);
    }
    
}
