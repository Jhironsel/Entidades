package sur.softsurena.metodos;

import java.sql.ResultSet;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.EntradaProducto;

/**
 *
 * @author jhironsel
 */
public class M_Entrada_ProductoNGTest {

    public M_Entrada_ProductoNGTest() {
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
    public void testAgregarProductoEntrada() {
        EntradaProducto e = null;
        boolean expResult = false;
        boolean result = M_Entrada_Producto.agregarProductoEntrada(e);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testAgregarProductoSalida() {
        int IDENTRADA_PRODUCTO = 0;
        int numero = 0;
        String cencepto = "";
        String idProducto = "";
        double entrada = 0.0;
        String idUsuario = "";
        M_Entrada_Producto instance = new M_Entrada_Producto();
        boolean expResult = false;
        boolean result = instance.agregarProductoSalida(IDENTRADA_PRODUCTO, numero, cencepto, idProducto, entrada, idUsuario);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetEntradaProducto() {
        int mes = 0;
        int year = 0;
        ResultSet expResult = null;
        ResultSet result = M_Entrada_Producto.getEntradaProducto(mes, year);
        assertEquals(result, expResult);
    }

}