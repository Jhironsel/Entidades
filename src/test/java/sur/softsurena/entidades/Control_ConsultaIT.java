package sur.softsurena.entidades;

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
public class Control_ConsultaIT {
    
    public Control_ConsultaIT() {
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
     * Test of borrarControlConsulta method, of class Control_Consulta.
     */
    @Test
    public void testBorrarControlConsulta() {
        System.out.println("borrarControlConsulta");
        int idControlConsulta = 0;
        String expResult = "";
        String result = Control_Consulta.borrarControlConsulta(idControlConsulta);
        assertEquals(expResult, result);
    }

    /**
     * Test of agregarControlConsulta method, of class Control_Consulta.
     */
    @Test
    public void testAgregarControlConsulta() {
        System.out.println("agregarControlConsulta");
        Control_Consulta cc = null;
        String expResult = "";
        String result = Control_Consulta.agregarControlConsulta(cc);
        assertEquals(expResult, result);
    }

    /**
     * Test of modificarControlConsulta method, of class Control_Consulta.
     */
    @Test
    public void testModificarControlConsulta() {
        System.out.println("modificarControlConsulta");
        Control_Consulta miCC = null;
        Control_Consulta instance = null;
        String expResult = "";
        String result = instance.modificarControlConsulta(miCC);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFechaDoctores method, of class Control_Consulta.
     */
    @Test
    public void testGetFechaDoctores() {
        System.out.println("getFechaDoctores");
        String fecha = "";
        boolean actual = false;
        ResultSet expResult = null;
        ResultSet result = Control_Consulta.getFechaDoctores(fecha, actual);
        assertEquals(expResult, result);
    }

    /**
     * Test of getHorario method, of class Control_Consulta.
     */
    @Test
    public void testGetHorario() {
        System.out.println("getHorario");
        String idUsuario = "";
        ResultSet expResult = null;
        ResultSet result = Control_Consulta.getHorario(idUsuario);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Control_Consulta.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Control_Consulta instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Control_Consulta.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Control_Consulta.Control_ConsultaBuilder expResult = null;
        Control_Consulta.Control_ConsultaBuilder result = Control_Consulta.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Control_Consulta.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Control_Consulta instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUser_name method, of class Control_Consulta.
     */
    @Test
    public void testGetUser_name() {
        System.out.println("getUser_name");
        Control_Consulta instance = null;
        String expResult = "";
        String result = instance.getUser_name();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCantidad method, of class Control_Consulta.
     */
    @Test
    public void testGetCantidad() {
        System.out.println("getCantidad");
        Control_Consulta instance = null;
        int expResult = 0;
        int result = instance.getCantidad();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDia method, of class Control_Consulta.
     */
    @Test
    public void testGetDia() {
        System.out.println("getDia");
        Control_Consulta instance = null;
        String expResult = "";
        String result = instance.getDia();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInicial method, of class Control_Consulta.
     */
    @Test
    public void testGetInicial() {
        System.out.println("getInicial");
        Control_Consulta instance = null;
        Time expResult = null;
        Time result = instance.getInicial();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFinall method, of class Control_Consulta.
     */
    @Test
    public void testGetFinall() {
        System.out.println("getFinall");
        Control_Consulta instance = null;
        Time expResult = null;
        Time result = instance.getFinall();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEstado method, of class Control_Consulta.
     */
    @Test
    public void testGetEstado() {
        System.out.println("getEstado");
        Control_Consulta instance = null;
        Boolean expResult = null;
        Boolean result = instance.getEstado();
        assertEquals(expResult, result);
    }
    
}
