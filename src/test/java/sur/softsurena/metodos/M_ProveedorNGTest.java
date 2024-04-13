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
import sur.softsurena.entidades.Proveedor;
import sur.softsurena.utilidades.Resultado;

/**
 *
 * @author jhironsel
 */
@Getter
public class M_ProveedorNGTest {

    public M_ProveedorNGTest() {
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
    public void testAgregarProveedor() {
        String expResult = "";
        Resultado result = M_Proveedor.agregarProveedor(
                Proveedor
                        .builder()
                        .codigoProveedor("jkhskdhgkhjh23")
                        .build()
        );
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testModificarProveedor() {
        Proveedor p = null;
        String expResult = "";
        Resultado result = M_Proveedor.modificarProveedor(p);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetProveedor() {
        ResultSet expResult = null;
        ResultSet result = M_Proveedor.getProveedor();
        assertEquals(result, expResult);
    }

}