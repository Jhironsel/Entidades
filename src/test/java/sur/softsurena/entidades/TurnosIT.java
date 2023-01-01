package sur.softsurena.entidades;

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
public class TurnosIT {
    
    public TurnosIT() {
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
     * Test of idTurnoActivo method, of class Turnos.
     */
    @Test
    public void testIdTurnoActivo() {
        System.out.println("idTurnoActivo");
        String userName = "";
        int expResult = 0;
        int result = Turnos.idTurnoActivo(userName);
        assertEquals(expResult, result);
    }

    /**
     * Test of usuarioTurnoActivo method, of class Turnos.
     */
    @Test
    public void testUsuarioTurnoActivo() {
        System.out.println("usuarioTurnoActivo");
        String userName = "";
        boolean expResult = false;
        boolean result = Turnos.usuarioTurnoActivo(userName);
        assertEquals(expResult, result);
    }

    /**
     * Test of habilitarTurno method, of class Turnos.
     */
    @Test
    public void testHabilitarTurno() {
        System.out.println("habilitarTurno");
        String idUsuario = "";
        boolean expResult = false;
        boolean result = Turnos.habilitarTurno(idUsuario);
        assertEquals(expResult, result);
    }

    /**
     * Test of cerrarTurno method, of class Turnos.
     */
    @Test
    public void testCerrarTurno() {
        System.out.println("cerrarTurno");
        String idUsuario = "";
        boolean expResult = false;
        boolean result = Turnos.cerrarTurno(idUsuario);
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Turnos.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Turnos.TurnosBuilder expResult = null;
        Turnos.TurnosBuilder result = Turnos.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Turnos.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Turnos instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFecha_inicio method, of class Turnos.
     */
    @Test
    public void testGetFecha_inicio() {
        System.out.println("getFecha_inicio");
        Turnos instance = null;
        Date expResult = null;
        Date result = instance.getFecha_inicio();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHora_inicio method, of class Turnos.
     */
    @Test
    public void testGetHora_inicio() {
        System.out.println("getHora_inicio");
        Turnos instance = null;
        Time expResult = null;
        Time result = instance.getHora_inicio();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFecha_final method, of class Turnos.
     */
    @Test
    public void testGetFecha_final() {
        System.out.println("getFecha_final");
        Turnos instance = null;
        Date expResult = null;
        Date result = instance.getFecha_final();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHora_final method, of class Turnos.
     */
    @Test
    public void testGetHora_final() {
        System.out.println("getHora_final");
        Turnos instance = null;
        Time expResult = null;
        Time result = instance.getHora_final();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEstado method, of class Turnos.
     */
    @Test
    public void testGetEstado() {
        System.out.println("getEstado");
        Turnos instance = null;
        Boolean expResult = null;
        Boolean result = instance.getEstado();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdUsuario method, of class Turnos.
     */
    @Test
    public void testGetIdUsuario() {
        System.out.println("getIdUsuario");
        Turnos instance = null;
        String expResult = "";
        String result = instance.getIdUsuario();
        assertEquals(expResult, result);
    }
    
}
