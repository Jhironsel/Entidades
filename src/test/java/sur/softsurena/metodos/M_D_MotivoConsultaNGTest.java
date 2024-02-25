package sur.softsurena.metodos;

import java.sql.ResultSet;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.D_MotivoConsulta;

/**
 *
 * @author jhironsel
 */
public class M_D_MotivoConsultaNGTest {

    public M_D_MotivoConsultaNGTest() {
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
    public void testBorrarDetalleMotivoConsulta() {
        D_MotivoConsulta dmc = null;
        M_D_MotivoConsulta instance = new M_D_MotivoConsulta();
        String expResult = "";
        String result = instance.borrarDetalleMotivoConsulta(dmc);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testAgregarDetallleConsulta() {
        D_MotivoConsulta dmc = null;
        String expResult = "";
        String result = M_D_MotivoConsulta.agregarDetallleConsulta(dmc);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetDetalleMotivo() {
        int idConsulta = 0;
        int turno = 0;
        ResultSet expResult = null;
        ResultSet result = M_D_MotivoConsulta.getDetalleMotivo(idConsulta, turno);
        assertEquals(result, expResult);
    }
}