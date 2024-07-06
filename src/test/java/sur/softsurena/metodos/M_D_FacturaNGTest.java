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
import sur.softsurena.entidades.Factura;
import sur.softsurena.utilidades.Resultado;

/**
 *
 * @author jhironsel
 */
@Getter
public class M_D_FacturaNGTest {

    public M_D_FacturaNGTest() {
    }

    //--------------------------------------------------------------------------
    @BeforeClass
    public void setUpClass() throws Exception {
        Conexion.getInstance(
                "sysdba",
                "1",
                "SoftSurena.db",
                "localhost",
                "3050"
        );
        assertTrue(
                Conexion.verificar().getEstado(),
                "Error al conectarse..."
        );
    }

    //--------------------------------------------------------------------------
    @AfterClass
    public void tearDownClass() throws Exception {
        Conexion.getCnn().close();
    }

    //--------------------------------------------------------------------------
    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    //--------------------------------------------------------------------------
    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    //--------------------------------------------------------------------------
    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testAgregarDetalleFactura() {
        Factura f = null;
        Integer expResult = null;
        Resultado result = M_D_Factura.agregarDetalleFactura(
                0, f.getDetalleFactura()
        );
        assertEquals(result, expResult);
    }
    
    //--------------------------------------------------------------------------

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetBuscarTemporal() {
        Integer idFactura = null;
        ResultSet expResult = null;
        ResultSet result = M_D_Factura.getBuscarTemporal(idFactura);
        assertEquals(result, expResult);
    }
    
    //--------------------------------------------------------------------------

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetFacturasDetalladas() {
        String idFactura = "";
        ResultSet expResult = null;
        ResultSet result = M_D_Factura.getFacturasDetalladas(idFactura);
        assertEquals(result, expResult);
    }
    
    //--------------------------------------------------------------------------

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetFacturasDetalladaPorCliente_String() {
        String idCliente = "";
        ResultSet expResult = null;
        ResultSet result = M_D_Factura.getFacturasDetalladaPorCliente(idCliente);
        assertEquals(result, expResult);
    }
    
    //--------------------------------------------------------------------------

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetFacturasDetalladaPorCliente_String_int() {
        String idCliente = "";
        int idFactura = 0;
        ResultSet expResult = null;
        ResultSet result = M_D_Factura.getFacturasDetalladaPorCliente(idCliente, idFactura);
        assertEquals(result, expResult);
    }
}
