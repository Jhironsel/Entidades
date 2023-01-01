package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
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
public class DeudasIT {
    
    public DeudasIT() {
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
     * Test of getDeudas method, of class Deudas.
     */
    @Test
    public void testGetDeudas() {
        System.out.println("getDeudas");
        List<Deudas> expResult = null;
        List<Deudas> result = Deudas.getDeudas();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeudaClientes method, of class Deudas.
     */
    @Test
    public void testGetDeudaClientes() {
        System.out.println("getDeudaClientes");
        ResultSet expResult = null;
        ResultSet result = Deudas.getDeudaClientes();
        assertEquals(expResult, result);
    }

    /**
     * Test of modificarDeuda method, of class Deudas.
     */
    @Test
    public void testModificarDeuda() {
        System.out.println("modificarDeuda");
        int idDeuda = 0;
        String op = "";
        String expResult = "";
        String result = Deudas.modificarDeuda(idDeuda, op);
        assertEquals(expResult, result);
    }

    /**
     * Test of pagoDeuda method, of class Deudas.
     */
    @Test
    public void testPagoDeuda() {
        System.out.println("pagoDeuda");
        int idDeuda = 0;
        int idTurno = 0;
        BigDecimal monto = null;
        Boolean expResult = null;
        Boolean result = Deudas.pagoDeuda(idDeuda, idTurno, monto);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertDeudas method, of class Deudas.
     */
    @Test
    public void testInsertDeudas() {
        System.out.println("insertDeudas");
        Deudas miDeuda = null;
        boolean expResult = false;
        boolean result = Deudas.insertDeudas(miDeuda);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeudaClientesEstado method, of class Deudas.
     */
    @Test
    public void testGetDeudaClientesEstado() {
        System.out.println("getDeudaClientesEstado");
        String estado = "";
        ResultSet expResult = null;
        ResultSet result = Deudas.getDeudaClientesEstado(estado);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeudaCliente method, of class Deudas.
     */
    @Test
    public void testGetDeudaCliente() {
        System.out.println("getDeudaCliente");
        String idCliente = "";
        ResultSet expResult = null;
        ResultSet result = Deudas.getDeudaCliente(idCliente);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeudaClienteExterna method, of class Deudas.
     */
    @Test
    public void testGetDeudaClienteExterna() {
        System.out.println("getDeudaClienteExterna");
        String idDeuda = "";
        ResultSet expResult = null;
        ResultSet result = Deudas.getDeudaClienteExterna(idDeuda);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPagoDeudasExterna method, of class Deudas.
     */
    @Test
    public void testGetPagoDeudasExterna() {
        System.out.println("getPagoDeudasExterna");
        int idDeuda = 0;
        ResultSet expResult = null;
        ResultSet result = Deudas.getPagoDeudasExterna(idDeuda);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPagoDeudas method, of class Deudas.
     */
    @Test
    public void testGetPagoDeudas() {
        System.out.println("getPagoDeudas");
        int idFactura = 0;
        ResultSet expResult = null;
        ResultSet result = Deudas.getPagoDeudas(idFactura);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeudaActual method, of class Deudas.
     */
    @Test
    public void testGetDeudaActual() {
        System.out.println("getDeudaActual");
        String idCliente = "";
        BigDecimal expResult = null;
        BigDecimal result = Deudas.getDeudaActual(idCliente);
        assertEquals(expResult, result);
    }

    /**
     * Test of sumaMontoPagoDeudaExterna method, of class Deudas.
     */
    @Test
    public void testSumaMontoPagoDeudaExterna() {
        System.out.println("sumaMontoPagoDeudaExterna");
        int idDeuda = 0;
        BigDecimal expResult = null;
        BigDecimal result = Deudas.sumaMontoPagoDeudaExterna(idDeuda);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Deudas.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Deudas instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Deudas.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Deudas.DeudasBuilder expResult = null;
        Deudas.DeudasBuilder result = Deudas.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_deuda method, of class Deudas.
     */
    @Test
    public void testGetId_deuda() {
        System.out.println("getId_deuda");
        Deudas instance = null;
        int expResult = 0;
        int result = instance.getId_deuda();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_cliente method, of class Deudas.
     */
    @Test
    public void testGetId_cliente() {
        System.out.println("getId_cliente");
        Deudas instance = null;
        int expResult = 0;
        int result = instance.getId_cliente();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_factura method, of class Deudas.
     */
    @Test
    public void testGetId_factura() {
        System.out.println("getId_factura");
        Deudas instance = null;
        int expResult = 0;
        int result = instance.getId_factura();
        assertEquals(expResult, result);
    }

    /**
     * Test of getConcepto method, of class Deudas.
     */
    @Test
    public void testGetConcepto() {
        System.out.println("getConcepto");
        Deudas instance = null;
        String expResult = "";
        String result = instance.getConcepto();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMonto method, of class Deudas.
     */
    @Test
    public void testGetMonto() {
        System.out.println("getMonto");
        Deudas instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.getMonto();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFecha method, of class Deudas.
     */
    @Test
    public void testGetFecha() {
        System.out.println("getFecha");
        Deudas instance = null;
        Date expResult = null;
        Date result = instance.getFecha();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHora method, of class Deudas.
     */
    @Test
    public void testGetHora() {
        System.out.println("getHora");
        Deudas instance = null;
        Time expResult = null;
        Time result = instance.getHora();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEstadoDeuda method, of class Deudas.
     */
    @Test
    public void testGetEstadoDeuda() {
        System.out.println("getEstadoDeuda");
        Deudas instance = null;
        char expResult = ' ';
        char result = instance.getEstadoDeuda();
        assertEquals(expResult, result);
    }
    
}
