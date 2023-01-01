package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
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
public class HeaderFacturaIT {
    
    public HeaderFacturaIT() {
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
     * Test of builder method, of class HeaderFactura.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        HeaderFactura.HeaderFacturaBuilder expResult = null;
        HeaderFactura.HeaderFacturaBuilder result = HeaderFactura.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdCliente method, of class HeaderFactura.
     */
    @Test
    public void testGetIdCliente() {
        System.out.println("getIdCliente");
        HeaderFactura instance = null;
        int expResult = 0;
        int result = instance.getIdCliente();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdTurno method, of class HeaderFactura.
     */
    @Test
    public void testGetIdTurno() {
        System.out.println("getIdTurno");
        HeaderFactura instance = null;
        int expResult = 0;
        int result = instance.getIdTurno();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEfectivo method, of class HeaderFactura.
     */
    @Test
    public void testGetEfectivo() {
        System.out.println("getEfectivo");
        HeaderFactura instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.getEfectivo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCambio method, of class HeaderFactura.
     */
    @Test
    public void testGetCambio() {
        System.out.println("getCambio");
        HeaderFactura instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.getCambio();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFecha method, of class HeaderFactura.
     */
    @Test
    public void testGetFecha() {
        System.out.println("getFecha");
        HeaderFactura instance = null;
        Date expResult = null;
        Date result = instance.getFecha();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHora method, of class HeaderFactura.
     */
    @Test
    public void testGetHora() {
        System.out.println("getHora");
        HeaderFactura instance = null;
        Time expResult = null;
        Time result = instance.getHora();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEstado method, of class HeaderFactura.
     */
    @Test
    public void testGetEstado() {
        System.out.println("getEstado");
        HeaderFactura instance = null;
        char expResult = ' ';
        char result = instance.getEstado();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserName method, of class HeaderFactura.
     */
    @Test
    public void testGetUserName() {
        System.out.println("getUserName");
        HeaderFactura instance = null;
        String expResult = "";
        String result = instance.getUserName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNombreTemp method, of class HeaderFactura.
     */
    @Test
    public void testGetNombreTemp() {
        System.out.println("getNombreTemp");
        HeaderFactura instance = null;
        String expResult = "";
        String result = instance.getNombreTemp();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCredito method, of class HeaderFactura.
     */
    @Test
    public void testGetCredito() {
        System.out.println("getCredito");
        HeaderFactura instance = null;
        Boolean expResult = null;
        Boolean result = instance.getCredito();
        assertEquals(expResult, result);
    }
    
}
