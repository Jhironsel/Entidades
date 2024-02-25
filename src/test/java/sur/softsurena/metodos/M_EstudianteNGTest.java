package sur.softsurena.metodos;

import java.sql.ResultSet;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Estudiante;
import sur.softsurena.entidades.Inscripcion;
import sur.softsurena.utilidades.Resultados;

/**
 *
 * @author jhironsel
 */
public class M_EstudianteNGTest {

    public M_EstudianteNGTest() {
    }

    @BeforeClass
    public void setUpClass() throws Exception {
        Conexion.getInstance(
                "sysdba",
                "1",
                "BaseDeDatos.db",
                "localhost",
                "3050"
        );
        assertTrue(
                Conexion.verificar().getEstado(), 
                "Error al conectarse..."
        );
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        Conexion.getCnn().close();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testAgregarEstudiante() {
        Estudiante e = null;
        Resultados expResult = null;
        Resultados result = M_Estudiante.agregarEstudiante(e);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetEstudiante() {
        String matricula = "";
        ResultSet expResult = null;
        ResultSet result = M_Estudiante.getEstudiante(matricula);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testModificarEstudiante() {
        Estudiante e = null;
        String expResult = "";
        String result = M_Estudiante.modificarEstudiante(e);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testInscribirEstudiante() {
        Inscripcion i = null;
        String expResult = "";
        String result = M_Estudiante.inscribirEstudiante(i);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testPPagoMensualidad() {
        String idUsuario = "";
        String pago = "";
        String matricula = "";
        String fechaPago = "";
        M_Estudiante.pPagoMensualidad(idUsuario, pago, matricula, fechaPago);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetMensualidad() {
        String matricula = "";
        String periodo = "";
        ResultSet expResult = null;
        ResultSet result = M_Estudiante.getMensualidad(matricula, periodo);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testExisteEstudiante() {
        String matricula = "";
        boolean expResult = false;
        boolean result = M_Estudiante.existeEstudiante(matricula);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testEstadoEstudiante() {
        String matricula = "";
        boolean expResult = false;
        boolean result = M_Estudiante.estadoEstudiante(matricula);
        assertEquals(result, expResult);
    }

}