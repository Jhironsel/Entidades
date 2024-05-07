package sur.softsurena.metodos;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JOptionPane;
import lombok.Getter;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.D_Factura;
import sur.softsurena.entidades.Factura;
import sur.softsurena.entidades.HeaderFactura;
import static sur.softsurena.metodos.M_Factura.ERROR_AL_INSERTAR_FACTURA_AL_SISTEMA;
import static sur.softsurena.metodos.M_Factura.FACTURA_INGRESADA_CORRECTAMENTE_AL_SISTEM;
import static sur.softsurena.metodos.M_Factura.FACTURA__BORRADA__CORRECTAMENTE;
import static sur.softsurena.metodos.M_Factura.OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_LA__FA;
import sur.softsurena.utilidades.Resultado;

/**
 *
 * @author jhironsel
 */
@Getter
public class M_FacturaNGTest {

    private int id_factura = -1;

    public M_FacturaNGTest() {
    }
    
    //--------------------------------------------------------------------------
    
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
            enabled = true,
            priority = 0,
            description = """
                          Verificamos que no existe registros de facturas en 
                          el sitema.
                          """
    )
    public void testGetFacturas() {
        assertTrue(
                M_Factura.getFacturas().isEmpty(),
                "Existe registros de facturas en el sistema."
        );
    }
    
    //--------------------------------------------------------------------------
    @Test(
            enabled = true,
            priority = 0,
            description = ""
    )
    public void testGetTemporales() {
        List result = M_Factura.getTemporales();
        assertTrue(
                result.isEmpty(), 
                "La lista de facturas temporales se encuentra con registros."
        );
    }

    //--------------------------------------------------------------------------
    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testAgregarFacturaNombre() {
        
        List<D_Factura> listDFactura = 
                List.of(
                        D_Factura
                                .builder()
                                .idLinea(0)
                                .idProducto(0)
                                .precio(BigDecimal.TEN)
                                .cantidad(BigDecimal.ONE)
                                .build(),
                        D_Factura
                                .builder()
                                .idLinea(0)
                                .idProducto(0)
                                .precio(BigDecimal.TEN)
                                .cantidad(BigDecimal.ONE)
                                .build(),
                        D_Factura
                                .builder()
                                .idLinea(0)
                                .idProducto(0)
                                .precio(BigDecimal.TEN)
                                .cantidad(BigDecimal.ONE)
                                .build()
                );
        
        Resultado result = M_Factura.agregarFacturaNombre(
                Factura
                        .builder()
                        .headerFactura(
                                HeaderFactura
                                        .builder()
                                        .id_persona(0)
                                        .idContactoTel(0)
                                        .idContactoDireccion(0)
                                        .idContactoEmail(0)
                                        .idTurno(0)
                                        .total(BigDecimal.TEN)
                                        .efectivo(BigDecimal.TEN)
                                        .estadoFactura('i')
                                        .build()
                        ).detalleFactura(listDFactura)
                        .build()
        );

        assertEquals(
                result.getMensaje(),
                FACTURA_INGRESADA_CORRECTAMENTE_AL_SISTEM,
                ERROR_AL_INSERTAR_FACTURA_AL_SISTEMA
        );

        assertEquals(
                result.getIcono(),
                JOptionPane.INFORMATION_MESSAGE,
                ERROR_AL_INSERTAR_FACTURA_AL_SISTEMA
        );

        assertTrue(
                result.getEstado(),
                ERROR_AL_INSERTAR_FACTURA_AL_SISTEMA
        );
        
        assertTrue(
                result.getId() > 0,
                ERROR_AL_INSERTAR_FACTURA_AL_SISTEMA
        );
        
        id_factura = result.getId();
        
    }

    //--------------------------------------------------------------------------
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

    //--------------------------------------------------------------------------
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

    //--------------------------------------------------------------------------
    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetFacturasNombreClientes() {
        if(id_factura < 0) return;
        ResultSet expResult = null;
        ResultSet result = M_Factura.getFacturasNombreClientes(id_factura);
        assertEquals(result, expResult);
    }

    //--------------------------------------------------------------------------
    @Test(
            enabled = true,
            priority = 6,
            description = """
                          Test que permite eliminar una factura del sistema.
                          """
    )
    public void testBorrarFactura() {
        if(id_factura < 0) return;
        Resultado result = M_Factura.borrarFactura(id_factura);

        assertEquals(
                result.getMensaje(),
                FACTURA__BORRADA__CORRECTAMENTE,
                OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_LA__FA
        );

        assertEquals(
                result.getIcono(),
                JOptionPane.INFORMATION_MESSAGE,
                OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_LA__FA
        );

        assertTrue(
                result.getEstado(),
                OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_LA__FA
        );
    }

}
