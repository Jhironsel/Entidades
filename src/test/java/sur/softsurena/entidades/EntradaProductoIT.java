package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.Date;
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
public class EntradaProductoIT {
    
    public EntradaProductoIT() {
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
     * Test of agregarProductoEntrada method, of class EntradaProducto.
     */
    @Test
    public void testAgregarProductoEntrada() {
        System.out.println("agregarProductoEntrada");
        EntradaProducto e = null;
        boolean expResult = false;
        boolean result = EntradaProducto.agregarProductoEntrada(e);
        assertEquals(expResult, result);
    }

    /**
     * Test of agregarProductoSalida method, of class EntradaProducto.
     */
    @Test
    public void testAgregarProductoSalida() {
        System.out.println("agregarProductoSalida");
        int IDENTRADA_PRODUCTO = 0;
        int numero = 0;
        String cencepto = "";
        String idProducto = "";
        double entrada = 0.0;
        String idUsuario = "";
        EntradaProducto instance = null;
        boolean expResult = false;
        boolean result = instance.agregarProductoSalida(IDENTRADA_PRODUCTO, numero, cencepto, idProducto, entrada, idUsuario);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEntradaProducto method, of class EntradaProducto.
     */
    @Test
    public void testGetEntradaProducto() {
        System.out.println("getEntradaProducto");
        int mes = 0;
        int year = 0;
        ResultSet expResult = null;
        ResultSet result = EntradaProducto.getEntradaProducto(mes, year);
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class EntradaProducto.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        EntradaProducto.EntradaProductoBuilder expResult = null;
        EntradaProducto.EntradaProductoBuilder result = EntradaProducto.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class EntradaProducto.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        EntradaProducto instance = null;
        Integer expResult = null;
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdProvedor method, of class EntradaProducto.
     */
    @Test
    public void testGetIdProvedor() {
        System.out.println("getIdProvedor");
        EntradaProducto instance = null;
        Integer expResult = null;
        Integer result = instance.getIdProvedor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCod_factura method, of class EntradaProducto.
     */
    @Test
    public void testGetCod_factura() {
        System.out.println("getCod_factura");
        EntradaProducto instance = null;
        String expResult = "";
        String result = instance.getCod_factura();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLinea method, of class EntradaProducto.
     */
    @Test
    public void testGetLinea() {
        System.out.println("getLinea");
        EntradaProducto instance = null;
        int expResult = 0;
        int result = instance.getLinea();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdProducto method, of class EntradaProducto.
     */
    @Test
    public void testGetIdProducto() {
        System.out.println("getIdProducto");
        EntradaProducto instance = null;
        int expResult = 0;
        int result = instance.getIdProducto();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEntrada method, of class EntradaProducto.
     */
    @Test
    public void testGetEntrada() {
        System.out.println("getEntrada");
        EntradaProducto instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.getEntrada();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFechaVecimiento method, of class EntradaProducto.
     */
    @Test
    public void testGetFechaVecimiento() {
        System.out.println("getFechaVecimiento");
        EntradaProducto instance = null;
        Date expResult = null;
        Date result = instance.getFechaVecimiento();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEstado method, of class EntradaProducto.
     */
    @Test
    public void testGetEstado() {
        System.out.println("getEstado");
        EntradaProducto instance = null;
        Boolean expResult = null;
        Boolean result = instance.getEstado();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdUsuairo method, of class EntradaProducto.
     */
    @Test
    public void testGetIdUsuairo() {
        System.out.println("getIdUsuairo");
        EntradaProducto instance = null;
        String expResult = "";
        String result = instance.getIdUsuairo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRol method, of class EntradaProducto.
     */
    @Test
    public void testGetRol() {
        System.out.println("getRol");
        EntradaProducto instance = null;
        String expResult = "";
        String result = instance.getRol();
        assertEquals(expResult, result);
    }
    
}
