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
public class EstudiantesIT {
    
    public EstudiantesIT() {
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
     * Test of agregarEstudiante method, of class Estudiantes.
     */
    @Test
    public void testAgregarEstudiante() {
        System.out.println("agregarEstudiante");
        Estudiantes e = null;
        Estudiantes instance = null;
        Resultados expResult = null;
        Resultados result = instance.agregarEstudiante(e);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEstudiante method, of class Estudiantes.
     */
    @Test
    public void testGetEstudiante() {
        System.out.println("getEstudiante");
        String matricula = "";
        Estudiantes instance = null;
        ResultSet expResult = null;
        ResultSet result = instance.getEstudiante(matricula);
        assertEquals(expResult, result);
    }

    /**
     * Test of modificarEstudiante method, of class Estudiantes.
     */
    @Test
    public void testModificarEstudiante() {
        System.out.println("modificarEstudiante");
        Estudiantes e = null;
        Estudiantes instance = null;
        String expResult = "";
        String result = instance.modificarEstudiante(e);
        assertEquals(expResult, result);
    }

    /**
     * Test of inscribirEstudiante method, of class Estudiantes.
     */
    @Test
    public void testInscribirEstudiante() {
        System.out.println("inscribirEstudiante");
        Inscripcion i = null;
        Estudiantes instance = null;
        String expResult = "";
        String result = instance.inscribirEstudiante(i);
        assertEquals(expResult, result);
    }

    /**
     * Test of pPagoMensualidad method, of class Estudiantes.
     */
    @Test
    public void testPPagoMensualidad() {
        System.out.println("pPagoMensualidad");
        String idUsuario = "";
        String pago = "";
        String matricula = "";
        String fechaPago = "";
        Estudiantes instance = null;
        instance.pPagoMensualidad(idUsuario, pago, matricula, fechaPago);
    }

    /**
     * Test of getMensualidad method, of class Estudiantes.
     */
    @Test
    public void testGetMensualidad() {
        System.out.println("getMensualidad");
        String matricula = "";
        String periodo = "";
        Estudiantes instance = null;
        ResultSet expResult = null;
        ResultSet result = instance.getMensualidad(matricula, periodo);
        assertEquals(expResult, result);
    }

    /**
     * Test of existeEstudiante method, of class Estudiantes.
     */
    @Test
    public void testExisteEstudiante() {
        System.out.println("existeEstudiante");
        String buscar = "";
        Estudiantes instance = null;
        boolean expResult = false;
        boolean result = instance.existeEstudiante(buscar);
        assertEquals(expResult, result);
    }

    /**
     * Test of estadoEstudiante method, of class Estudiantes.
     */
    @Test
    public void testEstadoEstudiante() {
        System.out.println("estadoEstudiante");
        String matricula = "";
        Estudiantes instance = null;
        boolean expResult = false;
        boolean result = instance.estadoEstudiante(matricula);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Estudiantes.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Estudiantes instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Estudiantes.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Estudiantes.EstudiantesBuilder expResult = null;
        Estudiantes.EstudiantesBuilder result = Estudiantes.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMatricula method, of class Estudiantes.
     */
    @Test
    public void testGetMatricula() {
        System.out.println("getMatricula");
        Estudiantes instance = null;
        String expResult = "";
        String result = instance.getMatricula();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdPadre method, of class Estudiantes.
     */
    @Test
    public void testGetIdPadre() {
        System.out.println("getIdPadre");
        Estudiantes instance = null;
        Integer expResult = null;
        Integer result = instance.getIdPadre();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdMadre method, of class Estudiantes.
     */
    @Test
    public void testGetIdMadre() {
        System.out.println("getIdMadre");
        Estudiantes instance = null;
        Integer expResult = null;
        Integer result = instance.getIdMadre();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdTutor method, of class Estudiantes.
     */
    @Test
    public void testGetIdTutor() {
        System.out.println("getIdTutor");
        Estudiantes instance = null;
        Integer expResult = null;
        Integer result = instance.getIdTutor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJcb_parentesco method, of class Estudiantes.
     */
    @Test
    public void testGetJcb_parentesco() {
        System.out.println("getJcb_parentesco");
        Estudiantes instance = null;
        Integer expResult = null;
        Integer result = instance.getJcb_parentesco();
        assertEquals(expResult, result);
    }
    
}
