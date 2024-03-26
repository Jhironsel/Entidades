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
import sur.softsurena.entidades.Factura;
import static sur.softsurena.metodos.M_Factura.OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_LA__FA;
import sur.softsurena.utilidades.Resultado;

/**
 *
 * @author jhironsel
 */
public class M_FacturaNGTest {

    private int id_factura;

    public M_FacturaNGTest() {
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
    public void testGetFacturas() {
        List expResult = null;
        List result = M_Factura.getFacturas();
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testBorrarFactura() {
        Resultado result = M_Factura.borrarFactura(id_factura);
        assertTrue(
                result.getEstado(),
                OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_LA__FA
        );
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testAgregarFacturaNombre() {
        Factura f = null;
        Integer expResult = null;
        Integer result = M_Factura.agregarFacturaNombre(f);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testModificarFactura() {
        Factura f = null;
        boolean expResult = false;
        boolean result = M_Factura.modificarFactura(f);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetReporteFacturas() {
        String filtro = "";
        ResultSet expResult = null;
        ResultSet result = M_Factura.getReporteFacturas(filtro);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetFacturasNombreClientes() {
        int idFactura = 0;
        ResultSet expResult = null;
        ResultSet result = M_Factura.getFacturasNombreClientes(idFactura);
        assertEquals(result, expResult);
    }

}
