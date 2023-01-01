package sur.softsurena.entidades;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.swing.ImageIcon;
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
public class MetricasIT {
    
    public MetricasIT() {
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
     * Test of agregarMetricas method, of class Metricas.
     */
    @Test
    public void testAgregarMetricas() {
        System.out.println("agregarMetricas");
        Metricas m = null;
        Metricas.agregarMetricas(m);
    }

    /**
     * Test of toString method, of class Metricas.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Metricas instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Metricas.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Metricas.MetricasBuilder expResult = null;
        Metricas.MetricasBuilder result = Metricas.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Metricas.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Metricas instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdConsulta method, of class Metricas.
     */
    @Test
    public void testGetIdConsulta() {
        System.out.println("getIdConsulta");
        Metricas instance = null;
        int expResult = 0;
        int result = instance.getIdConsulta();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFecha method, of class Metricas.
     */
    @Test
    public void testGetFecha() {
        System.out.println("getFecha");
        Metricas instance = null;
        Timestamp expResult = null;
        Timestamp result = instance.getFecha();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPesoKG method, of class Metricas.
     */
    @Test
    public void testGetPesoKG() {
        System.out.println("getPesoKG");
        Metricas instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.getPesoKG();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEstaturaM method, of class Metricas.
     */
    @Test
    public void testGetEstaturaM() {
        System.out.println("getEstaturaM");
        Metricas instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.getEstaturaM();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEscefalo method, of class Metricas.
     */
    @Test
    public void testGetEscefalo() {
        System.out.println("getEscefalo");
        Metricas instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.getEscefalo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEnf_detect method, of class Metricas.
     */
    @Test
    public void testGetEnf_detect() {
        System.out.println("getEnf_detect");
        Metricas instance = null;
        String expResult = "";
        String result = instance.getEnf_detect();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHallazgosPositivo method, of class Metricas.
     */
    @Test
    public void testGetHallazgosPositivo() {
        System.out.println("getHallazgosPositivo");
        Metricas instance = null;
        String expResult = "";
        String result = instance.getHallazgosPositivo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdDiagnostico method, of class Metricas.
     */
    @Test
    public void testGetIdDiagnostico() {
        System.out.println("getIdDiagnostico");
        Metricas instance = null;
        String expResult = "";
        String result = instance.getIdDiagnostico();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTx method, of class Metricas.
     */
    @Test
    public void testGetTx() {
        System.out.println("getTx");
        Metricas instance = null;
        String expResult = "";
        String result = instance.getTx();
        assertEquals(expResult, result);
    }

    /**
     * Test of getComplemento method, of class Metricas.
     */
    @Test
    public void testGetComplemento() {
        System.out.println("getComplemento");
        Metricas instance = null;
        String expResult = "";
        String result = instance.getComplemento();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImagen method, of class Metricas.
     */
    @Test
    public void testGetImagen() {
        System.out.println("getImagen");
        Metricas instance = null;
        ImageIcon expResult = null;
        ImageIcon result = instance.getImagen();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImagenPath method, of class Metricas.
     */
    @Test
    public void testGetImagenPath() {
        System.out.println("getImagenPath");
        Metricas instance = null;
        File expResult = null;
        File result = instance.getImagenPath();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsuario method, of class Metricas.
     */
    @Test
    public void testGetUsuario() {
        System.out.println("getUsuario");
        Metricas instance = null;
        String expResult = "";
        String result = instance.getUsuario();
        assertEquals(expResult, result);
    }
    
}
