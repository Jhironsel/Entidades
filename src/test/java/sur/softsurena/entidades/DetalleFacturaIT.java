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
public class DetalleFacturaIT {
    
    public DetalleFacturaIT() {
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
     * Test of agregarDetalleFactura method, of class DetalleFactura.
     */
    @Test
    public void testAgregarDetalleFactura() {
        System.out.println("agregarDetalleFactura");
        Facturas f = null;
        Integer expResult = null;
        Integer result = DetalleFactura.agregarDetalleFactura(f);
        assertEquals(expResult, result);
    }

    /**
     * Test of getBuscarTemporal method, of class DetalleFactura.
     */
    @Test
    public void testGetBuscarTemporal() {
        System.out.println("getBuscarTemporal");
        Integer idFactura = null;
        ResultSet expResult = null;
        ResultSet result = DetalleFactura.getBuscarTemporal(idFactura);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFacturasDetalladas method, of class DetalleFactura.
     */
    @Test
    public void testGetFacturasDetalladas() {
        System.out.println("getFacturasDetalladas");
        String idFactura = "";
        ResultSet expResult = null;
        ResultSet result = DetalleFactura.getFacturasDetalladas(idFactura);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFacturasDetalladaPorCliente method, of class DetalleFactura.
     */
    @Test
    public void testGetFacturasDetalladaPorCliente_String() {
        System.out.println("getFacturasDetalladaPorCliente");
        String idCliente = "";
        ResultSet expResult = null;
        ResultSet result = DetalleFactura.getFacturasDetalladaPorCliente(idCliente);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFacturasDetalladaPorCliente method, of class DetalleFactura.
     */
    @Test
    public void testGetFacturasDetalladaPorCliente_String_int() {
        System.out.println("getFacturasDetalladaPorCliente");
        String idCliente = "";
        int idFactura = 0;
        ResultSet expResult = null;
        ResultSet result = DetalleFactura.getFacturasDetalladaPorCliente(idCliente, idFactura);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class DetalleFactura.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        DetalleFactura instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class DetalleFactura.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        DetalleFactura.DetalleFacturaBuilder expResult = null;
        DetalleFactura.DetalleFacturaBuilder result = DetalleFactura.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdLinea method, of class DetalleFactura.
     */
    @Test
    public void testGetIdLinea() {
        System.out.println("getIdLinea");
        DetalleFactura instance = null;
        int expResult = 0;
        int result = instance.getIdLinea();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdProducto method, of class DetalleFactura.
     */
    @Test
    public void testGetIdProducto() {
        System.out.println("getIdProducto");
        DetalleFactura instance = null;
        int expResult = 0;
        int result = instance.getIdProducto();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescripcion method, of class DetalleFactura.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        DetalleFactura instance = null;
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrecio method, of class DetalleFactura.
     */
    @Test
    public void testGetPrecio() {
        System.out.println("getPrecio");
        DetalleFactura instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.getPrecio();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCantidad method, of class DetalleFactura.
     */
    @Test
    public void testGetCantidad() {
        System.out.println("getCantidad");
        DetalleFactura instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.getCantidad();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotal method, of class DetalleFactura.
     */
    @Test
    public void testGetTotal() {
        System.out.println("getTotal");
        DetalleFactura instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.getTotal();
        assertEquals(expResult, result);
    }
    
}
