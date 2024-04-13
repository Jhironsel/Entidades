package sur.softsurena.metodos;

import java.math.BigDecimal;
import java.sql.ResultSet;
import lombok.Getter;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Deuda;
import sur.softsurena.utilidades.Resultado;

/**
 *
 * @author jhironsel
 */
@Getter
public class M_DeudaNGTest {

    public M_DeudaNGTest() {
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
            enabled = true,
            priority = 0,
            description = ""
    )
    public void testGetDeudas() {
        assertTrue(
                M_Deuda.getDeudas().isEmpty(), 
                "La tabla de deuda no esta vacia."
        );
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testModificarDeuda() {
        int idDeuda = 0;
        String op = "";
        Resultado expResult = null;
        Resultado result = M_Deuda.modificarDeuda(idDeuda, op);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testPagoDeuda() {
        int idDeuda = 0;
        int idTurno = 0;
        BigDecimal monto = null;
        Boolean expResult = null;
        Boolean result = M_Deuda.pagoDeuda(idDeuda, idTurno, monto);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testInsertDeudas() {
        Deuda miDeuda = null;
        boolean expResult = false;
        boolean result = M_Deuda.insertDeudas(miDeuda);
        assertEquals(result, expResult);
    }
    
    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetDeudaClientesEstado() {
        String estado = "";
        ResultSet expResult = null;
        ResultSet result = M_Deuda.getDeudaClientesEstado(estado);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetDeudaCliente() {
        String idCliente = "";
        ResultSet expResult = null;
        ResultSet result = M_Deuda.getDeudaCliente(idCliente);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetDeudaClienteExterna() {
        String idDeuda = "";
        ResultSet expResult = null;
        ResultSet result = M_Deuda.getDeudaClienteExterna(idDeuda);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetPagoDeudasExterna() {
        int idDeuda = 0;
        ResultSet expResult = null;
        ResultSet result = M_Deuda.getPagoDeudasExterna(idDeuda);
        assertEquals(result, expResult);
    }
    
    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetPagoDeudas() {
        int idFactura = 0;
        ResultSet expResult = null;
        ResultSet result = M_Deuda.getPagoDeudas(idFactura);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetDeudaActual() {
        String idCliente = "";
        BigDecimal expResult = null;
        BigDecimal result = M_Deuda.getDeudaActual(idCliente);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testSumaMontoPagoDeudaExterna() {
        int idDeuda = 0;
        BigDecimal expResult = null;
        BigDecimal result = M_Deuda.sumaMontoPagoDeudaExterna(idDeuda);
        assertEquals(result, expResult);
    }

}