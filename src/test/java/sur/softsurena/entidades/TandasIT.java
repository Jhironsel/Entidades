package sur.softsurena.entidades;

import java.sql.Date;
import java.sql.ResultSet;
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
public class TandasIT {
    
    public TandasIT() {
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
     * Test of agregarTanda method, of class Tandas.
     */
    @Test
    public void testAgregarTanda() {
        System.out.println("agregarTanda");
        Tandas t = null;
        String expResult = "";
        String result = Tandas.agregarTanda(t);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTandas method, of class Tandas.
     */
    @Test
    public void testGetTandas_Integer_Integer() {
        System.out.println("getTandas");
        Integer edadMin = null;
        Integer edadMax = null;
        Tandas instance = null;
        ResultSet expResult = null;
        ResultSet result = instance.getTandas(edadMin, edadMax);
        assertEquals(expResult, result);
    }

    /**
     * Test of modificarTanda method, of class Tandas.
     */
    @Test
    public void testModificarTanda() {
        System.out.println("modificarTanda");
        Tandas t = null;
        Tandas instance = null;
        String expResult = "";
        String result = instance.modificarTanda(t);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTandas method, of class Tandas.
     */
    @Test
    public void testGetTandas_Integer() {
        System.out.println("getTandas");
        Integer id_Tanda = null;
        Tandas instance = null;
        ResultSet expResult = null;
        ResultSet result = instance.getTandas(id_Tanda);
        assertEquals(expResult, result);
    }

    /**
     * Test of getHorario method, of class Tandas.
     */
    @Test
    public void testGetHorario() {
        System.out.println("getHorario");
        Tandas instance = null;
        ResultSet expResult = null;
        ResultSet result = instance.getHorario();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Tandas.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Tandas instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Tandas.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Tandas.TandasBuilder expResult = null;
        Tandas.TandasBuilder result = Tandas.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_tanda method, of class Tandas.
     */
    @Test
    public void testGetId_tanda() {
        System.out.println("getId_tanda");
        Tandas instance = null;
        Integer expResult = null;
        Integer result = instance.getId_tanda();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAnno_inicial method, of class Tandas.
     */
    @Test
    public void testGetAnno_inicial() {
        System.out.println("getAnno_inicial");
        Tandas instance = null;
        Date expResult = null;
        Date result = instance.getAnno_inicial();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAnno_final method, of class Tandas.
     */
    @Test
    public void testGetAnno_final() {
        System.out.println("getAnno_final");
        Tandas instance = null;
        Date expResult = null;
        Date result = instance.getAnno_final();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHora_inicial method, of class Tandas.
     */
    @Test
    public void testGetHora_inicial() {
        System.out.println("getHora_inicial");
        Tandas instance = null;
        Time expResult = null;
        Time result = instance.getHora_inicial();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHora_final method, of class Tandas.
     */
    @Test
    public void testGetHora_final() {
        System.out.println("getHora_final");
        Tandas instance = null;
        Time expResult = null;
        Time result = instance.getHora_final();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLunes method, of class Tandas.
     */
    @Test
    public void testGetLunes() {
        System.out.println("getLunes");
        Tandas instance = null;
        Boolean expResult = null;
        Boolean result = instance.getLunes();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMartes method, of class Tandas.
     */
    @Test
    public void testGetMartes() {
        System.out.println("getMartes");
        Tandas instance = null;
        Boolean expResult = null;
        Boolean result = instance.getMartes();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMiercoles method, of class Tandas.
     */
    @Test
    public void testGetMiercoles() {
        System.out.println("getMiercoles");
        Tandas instance = null;
        Boolean expResult = null;
        Boolean result = instance.getMiercoles();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJueves method, of class Tandas.
     */
    @Test
    public void testGetJueves() {
        System.out.println("getJueves");
        Tandas instance = null;
        Boolean expResult = null;
        Boolean result = instance.getJueves();
        assertEquals(expResult, result);
    }

    /**
     * Test of getViernes method, of class Tandas.
     */
    @Test
    public void testGetViernes() {
        System.out.println("getViernes");
        Tandas instance = null;
        Boolean expResult = null;
        Boolean result = instance.getViernes();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSabados method, of class Tandas.
     */
    @Test
    public void testGetSabados() {
        System.out.println("getSabados");
        Tandas instance = null;
        Boolean expResult = null;
        Boolean result = instance.getSabados();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDomingos method, of class Tandas.
     */
    @Test
    public void testGetDomingos() {
        System.out.println("getDomingos");
        Tandas instance = null;
        Boolean expResult = null;
        Boolean result = instance.getDomingos();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCantidad_estudiantes method, of class Tandas.
     */
    @Test
    public void testGetCantidad_estudiantes() {
        System.out.println("getCantidad_estudiantes");
        Tandas instance = null;
        int expResult = 0;
        int result = instance.getCantidad_estudiantes();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEdad_minima method, of class Tandas.
     */
    @Test
    public void testGetEdad_minima() {
        System.out.println("getEdad_minima");
        Tandas instance = null;
        int expResult = 0;
        int result = instance.getEdad_minima();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEdad_maxima method, of class Tandas.
     */
    @Test
    public void testGetEdad_maxima() {
        System.out.println("getEdad_maxima");
        Tandas instance = null;
        int expResult = 0;
        int result = instance.getEdad_maxima();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCon_edad method, of class Tandas.
     */
    @Test
    public void testGetCon_edad() {
        System.out.println("getCon_edad");
        Tandas instance = null;
        Boolean expResult = null;
        Boolean result = instance.getCon_edad();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEstado method, of class Tandas.
     */
    @Test
    public void testGetEstado() {
        System.out.println("getEstado");
        Tandas instance = null;
        Boolean expResult = null;
        Boolean result = instance.getEstado();
        assertEquals(expResult, result);
    }
    
}
