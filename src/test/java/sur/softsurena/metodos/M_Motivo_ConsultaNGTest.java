package sur.softsurena.metodos;

import java.sql.ResultSet;
import lombok.Getter;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Motivo_Consulta;

/**
 *
 * @author jhironsel
 */
@Getter
public class M_Motivo_ConsultaNGTest {

    public M_Motivo_ConsultaNGTest() {
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
    public void testBorrarMotivoConsulta() {
        Motivo_Consulta mc = null;
        String expResult = "";
        String result = M_Motivo_Consulta.borrarMotivoConsulta(mc);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testAgregarMotivo() {
        String m = "";
        boolean expResult = false;
        boolean result = M_Motivo_Consulta.agregarMotivo(m);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetMotivo() {
        ResultSet expResult = null;
        ResultSet result = M_Motivo_Consulta.getMotivo();
        assertEquals(result, expResult);
    }

}