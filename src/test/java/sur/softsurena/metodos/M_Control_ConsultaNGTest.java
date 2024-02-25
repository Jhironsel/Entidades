package sur.softsurena.metodos;

import java.sql.ResultSet;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Control_Consulta;

/**
 *
 * @author jhironsel
 */
public class M_Control_ConsultaNGTest {

    public M_Control_ConsultaNGTest() {
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
    public void testBorrarControlConsulta() {
        int idControlConsulta = 0;
        String expResult = "";
        String result = M_Control_Consulta.borrarControlConsulta(idControlConsulta);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testAgregarControlConsulta() {
        Control_Consulta controlConsulta = null;
        String expResult = "";
        String result = M_Control_Consulta.agregarControlConsulta(controlConsulta);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testModificarControlConsulta() {
        Control_Consulta controlConsulta = null;
        M_Control_Consulta instance = new M_Control_Consulta();
        String expResult = "";
        String result = instance.modificarControlConsulta(controlConsulta);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetFechaDoctores() {
        String fecha = "";
        boolean actual = false;
        ResultSet expResult = null;
        ResultSet result = M_Control_Consulta.getFechaDoctores(fecha, actual);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetHorario() {
        String idUsuario = "";
        ResultSet expResult = null;
        ResultSet result = M_Control_Consulta.getHorario(idUsuario);
        assertEquals(result, expResult);
    }

}