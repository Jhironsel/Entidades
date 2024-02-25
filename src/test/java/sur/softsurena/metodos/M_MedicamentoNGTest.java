package sur.softsurena.metodos;

import java.sql.ResultSet;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Medicamento;

/**
 *
 * @author jhironsel
 */
public class M_MedicamentoNGTest {

    public M_MedicamentoNGTest() {
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
    public void testModificarMedicamento() {
        Medicamento m = null;
        String expResult = "";
        String result = M_Medicamento.modificarMedicamento(m);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetMedicamentoActivo() {
        List expResult = null;
        List result = M_Medicamento.getMedicamentoActivo();
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetMedicamentoFoto() {
        String idMedicamento = "";
        ResultSet expResult = null;
        ResultSet result = M_Medicamento.getMedicamentoFoto(idMedicamento);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetMedicamento() {
        boolean estado = false;
        ResultSet expResult = null;
        ResultSet result = M_Medicamento.getMedicamento(estado);
        assertEquals(result, expResult);
    }

}