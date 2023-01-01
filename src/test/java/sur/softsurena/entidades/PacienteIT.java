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
public class PacienteIT {
    
    public PacienteIT() {
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
     * Test of modificarPaciente method, of class Paciente.
     */
    @Test
    public void testModificarPaciente() {
        System.out.println("modificarPaciente");
        Paciente p = null;
        String expResult = "";
        String result = Paciente.modificarPaciente(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of agregarPaciente method, of class Paciente.
     */
    @Test
    public void testAgregarPaciente() {
        System.out.println("agregarPaciente");
        Paciente p = null;
        String expResult = "";
        String result = Paciente.agregarPaciente(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of borrarPaciente method, of class Paciente.
     */
    @Test
    public void testBorrarPaciente() {
        System.out.println("borrarPaciente");
        String cedula = "";
        String expResult = "";
        String result = Paciente.borrarPaciente(cedula);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSexoPaciente method, of class Paciente.
     */
    @Test
    public void testGetSexoPaciente() {
        System.out.println("getSexoPaciente");
        int idPaciente = 0;
        String expResult = "";
        String result = Paciente.getSexoPaciente(idPaciente);
        assertEquals(expResult, result);
    }

    /**
     * Test of existePaciente method, of class Paciente.
     */
    @Test
    public void testExistePaciente() {
        System.out.println("existePaciente");
        String cedula = "";
        boolean expResult = false;
        boolean result = Paciente.existePaciente(cedula);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPacienteActivoID method, of class Paciente.
     */
    @Test
    public void testGetPacienteActivoID() {
        System.out.println("getPacienteActivoID");
        int idPaciente = 0;
        ResultSet expResult = null;
        ResultSet result = Paciente.getPacienteActivoID(idPaciente);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDatosNacimiento method, of class Paciente.
     */
    @Test
    public void testGetDatosNacimiento() {
        System.out.println("getDatosNacimiento");
        int id = 0;
        ResultSet expResult = null;
        ResultSet result = Paciente.getDatosNacimiento(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdPaciente method, of class Paciente.
     */
    @Test
    public void testGetIdPaciente() {
        System.out.println("getIdPaciente");
        String cedula = "";
        int expResult = 0;
        int result = Paciente.getIdPaciente(cedula);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPacienteActivo method, of class Paciente.
     */
    @Test
    public void testGetPacienteActivo_boolean() {
        System.out.println("getPacienteActivo");
        boolean estado = false;
        ResultSet expResult = null;
        ResultSet result = Paciente.getPacienteActivo(estado);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPacienteActivo method, of class Paciente.
     */
    @Test
    public void testGetPacienteActivo_3args() {
        System.out.println("getPacienteActivo");
        String filtro = "";
        String fecha = "";
        int idControlConsulta = 0;
        ResultSet expResult = null;
        ResultSet result = Paciente.getPacienteActivo(filtro, fecha, idControlConsulta);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPacienteActivo2 method, of class Paciente.
     */
    @Test
    public void testGetPacienteActivo2() {
        System.out.println("getPacienteActivo2");
        String filtro = "";
        String fecha = "";
        int idControlConsulta = 0;
        ResultSet expResult = null;
        ResultSet result = Paciente.getPacienteActivo2(filtro, fecha, idControlConsulta);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPacienteActivo3 method, of class Paciente.
     */
    @Test
    public void testGetPacienteActivo3() {
        System.out.println("getPacienteActivo3");
        String filtro = "";
        String fecha = "";
        int idControlConsulta = 0;
        ResultSet expResult = null;
        ResultSet result = Paciente.getPacienteActivo3(filtro, fecha, idControlConsulta);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Paciente.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Paciente instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Paciente.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Paciente.PacienteBuilder expResult = null;
        Paciente.PacienteBuilder result = Paciente.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdPadre method, of class Paciente.
     */
    @Test
    public void testGetIdPadre() {
        System.out.println("getIdPadre");
        Paciente instance = null;
        int expResult = 0;
        int result = instance.getIdPadre();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdMadre method, of class Paciente.
     */
    @Test
    public void testGetIdMadre() {
        System.out.println("getIdMadre");
        Paciente instance = null;
        int expResult = 0;
        int result = instance.getIdMadre();
        assertEquals(expResult, result);
    }
    
}
