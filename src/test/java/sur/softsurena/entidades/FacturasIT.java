package sur.softsurena.entidades;

import java.sql.ResultSet;
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
public class FacturasIT {
    
    public FacturasIT() {
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
     * Test of getFacturas method, of class Facturas.
     */
    @Test
    public void testGetFacturas() {
        System.out.println("getFacturas");
        List<Facturas> expResult = null;
        List<Facturas> result = Facturas.getFacturas();
        assertEquals(expResult, result);
    }

    /**
     * Test of borrarFactura method, of class Facturas.
     */
    @Test
    public void testBorrarFactura() {
        System.out.println("borrarFactura");
        int id = 0;
        String expResult = "";
        String result = Facturas.borrarFactura(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of agregarFacturaNombre method, of class Facturas.
     */
    @Test
    public void testAgregarFacturaNombre() {
        System.out.println("agregarFacturaNombre");
        Facturas f = null;
        Integer expResult = null;
        Integer result = Facturas.agregarFacturaNombre(f);
        assertEquals(expResult, result);
    }

    /**
     * Test of modificarFactura method, of class Facturas.
     */
    @Test
    public void testModificarFactura() {
        System.out.println("modificarFactura");
        Facturas f = null;
        boolean expResult = false;
        boolean result = Facturas.modificarFactura(f);
        assertEquals(expResult, result);
    }

    /**
     * Test of getReporteFacturas method, of class Facturas.
     */
    @Test
    public void testGetReporteFacturas() {
        System.out.println("getReporteFacturas");
        String filtro = "";
        ResultSet expResult = null;
        ResultSet result = Facturas.getReporteFacturas(filtro);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFacturasNombreClientes method, of class Facturas.
     */
    @Test
    public void testGetFacturasNombreClientes() {
        System.out.println("getFacturasNombreClientes");
        int idFactura = 0;
        ResultSet expResult = null;
        ResultSet result = Facturas.getFacturasNombreClientes(idFactura);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Facturas.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Facturas instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Facturas.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Facturas.FacturasBuilder expResult = null;
        Facturas.FacturasBuilder result = Facturas.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Facturas.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Facturas instance = null;
        Integer expResult = null;
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHeaderFactura method, of class Facturas.
     */
    @Test
    public void testGetHeaderFactura() {
        System.out.println("getHeaderFactura");
        Facturas instance = null;
        HeaderFactura expResult = null;
        HeaderFactura result = instance.getHeaderFactura();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDetalleFactura method, of class Facturas.
     */
    @Test
    public void testGetDetalleFactura() {
        System.out.println("getDetalleFactura");
        Facturas instance = null;
        List<DetalleFactura> expResult = null;
        List<DetalleFactura> result = instance.getDetalleFactura();
        assertEquals(expResult, result);
    }
    
}
