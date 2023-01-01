package sur.softsurena.entidades;

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
public class ConsultasIT {
    
    public ConsultasIT() {
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
     * Test of agregarConsulta method, of class Consultas.
     */
    @Test
    public void testAgregarConsulta() {
        System.out.println("agregarConsulta");
        Consultas c = null;
        Consultas instance = null;
        String expResult = "";
        String result = instance.agregarConsulta(c);
        assertEquals(expResult, result);
    }

    /**
     * Test of getConsulta method, of class Consultas.
     */
    @Test
    public void testGetConsulta() {
        System.out.println("getConsulta");
        String fecha = "";
        ResultSet expResult = null;
        ResultSet result = Consultas.getConsulta(fecha);
        assertEquals(expResult, result);
    }

    /**
     * Test of getControlConsulta method, of class Consultas.
     */
    @Test
    public void testGetControlConsulta() {
        System.out.println("getControlConsulta");
        String fecha = "";
        Consultas instance = null;
        boolean expResult = false;
        boolean result = instance.getControlConsulta(fecha);
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Consultas.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Consultas.ConsultasBuilder expResult = null;
        Consultas.ConsultasBuilder result = Consultas.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Consultas.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Consultas instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_motivo_consulta method, of class Consultas.
     */
    @Test
    public void testGetId_motivo_consulta() {
        System.out.println("getId_motivo_consulta");
        Consultas instance = null;
        int expResult = 0;
        int result = instance.getId_motivo_consulta();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_control_consulta method, of class Consultas.
     */
    @Test
    public void testGetId_control_consulta() {
        System.out.println("getId_control_consulta");
        Consultas instance = null;
        int expResult = 0;
        int result = instance.getId_control_consulta();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFecha method, of class Consultas.
     */
    @Test
    public void testGetFecha() {
        System.out.println("getFecha");
        Consultas instance = null;
        Date expResult = null;
        Date result = instance.getFecha();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTurno method, of class Consultas.
     */
    @Test
    public void testGetTurno() {
        System.out.println("getTurno");
        Consultas instance = null;
        int expResult = 0;
        int result = instance.getTurno();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEstado method, of class Consultas.
     */
    @Test
    public void testGetEstado() {
        System.out.println("getEstado");
        Consultas instance = null;
        Boolean expResult = null;
        Boolean result = instance.getEstado();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsuario method, of class Consultas.
     */
    @Test
    public void testGetUsuario() {
        System.out.println("getUsuario");
        Consultas instance = null;
        String expResult = "";
        String result = instance.getUsuario();
        assertEquals(expResult, result);
    }
    
}
