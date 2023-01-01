package sur.softsurena.entidades;

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
public class DetalleMotivoConsultaIT {
    
    public DetalleMotivoConsultaIT() {
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
     * Test of borrarDetalleMotivoConsulta method, of class DetalleMotivoConsulta.
     */
    @Test
    public void testBorrarDetalleMotivoConsulta() {
        System.out.println("borrarDetalleMotivoConsulta");
        DetalleMotivoConsulta dmc = null;
        DetalleMotivoConsulta instance = null;
        String expResult = "";
        String result = instance.borrarDetalleMotivoConsulta(dmc);
        assertEquals(expResult, result);
    }

    /**
     * Test of agregarDetallleConsulta method, of class DetalleMotivoConsulta.
     */
    @Test
    public void testAgregarDetallleConsulta() {
        System.out.println("agregarDetallleConsulta");
        DetalleMotivoConsulta dmc = null;
        String expResult = "";
        String result = DetalleMotivoConsulta.agregarDetallleConsulta(dmc);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDetalleMotivo method, of class DetalleMotivoConsulta.
     */
    @Test
    public void testGetDetalleMotivo() {
        System.out.println("getDetalleMotivo");
        int idConsulta = 0;
        int turno = 0;
        ResultSet expResult = null;
        ResultSet result = DetalleMotivoConsulta.getDetalleMotivo(idConsulta, turno);
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class DetalleMotivoConsulta.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        DetalleMotivoConsulta.DetalleMotivoConsultaBuilder expResult = null;
        DetalleMotivoConsulta.DetalleMotivoConsultaBuilder result = DetalleMotivoConsulta.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdConsulta method, of class DetalleMotivoConsulta.
     */
    @Test
    public void testGetIdConsulta() {
        System.out.println("getIdConsulta");
        DetalleMotivoConsulta instance = null;
        int expResult = 0;
        int result = instance.getIdConsulta();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTurno method, of class DetalleMotivoConsulta.
     */
    @Test
    public void testGetTurno() {
        System.out.println("getTurno");
        DetalleMotivoConsulta instance = null;
        int expResult = 0;
        int result = instance.getTurno();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdMotivoConsulta method, of class DetalleMotivoConsulta.
     */
    @Test
    public void testGetIdMotivoConsulta() {
        System.out.println("getIdMotivoConsulta");
        DetalleMotivoConsulta instance = null;
        int expResult = 0;
        int result = instance.getIdMotivoConsulta();
        assertEquals(expResult, result);
    }
    
}
