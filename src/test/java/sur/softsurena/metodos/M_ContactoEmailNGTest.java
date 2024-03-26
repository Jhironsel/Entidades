package sur.softsurena.metodos;

import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.ContactoEmail;
import sur.softsurena.utilidades.Resultado;

/**
 *
 * @author jhironsel
 */
public class M_ContactoEmailNGTest {

    private int idCorreo;
    private M_ClienteNGTest cliente;

    public M_ContactoEmailNGTest() {
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
            description = "Permita agregar un contacto y su correo"
    )
    public void testAgregarContactosEmail() {
        cliente = new M_ClienteNGTest();
        cliente.testAgregarCliente();
        
        Resultado result = M_ContactoEmail.agregarContactosEmail(
                ContactoEmail
                        .builder()
                        .id_persona(cliente.idCliente)
                        .email(M_ContactoEmail.generarCorreo())
                        .estado(Boolean.TRUE)
                        .por_defecto(Boolean.TRUE)
                        .build()
        );
        idCorreo = result.getId();
        assertTrue(result.getEstado());
    }
    
    @Test(
            enabled = false,
            priority = 1,
            description = "Modifica los contactos del sistema."
    )
    public void testModificarContactosEmail() {
        Resultado result = M_ContactoEmail.modificarContactosEmail(
                ContactoEmail
                        .builder()
                        .id(idCorreo)
                        .id_persona(cliente.idCliente)
                        .email(M_ContactoEmail.generarCorreo())
                        .estado(Boolean.TRUE)
                        .por_defecto(Boolean.FALSE)
                        .build()
        );
        
        assertTrue(
                result.getEstado(), 
                "Error al modificar contacto en el sistema."
        );
    }
    
    @Test(
            enabled = false,
            priority = 1,
            description = "Realizamos consulta y eliminamos cliente."
    )
    public void testGetCorreoByID() {
        List result = M_ContactoEmail.getCorreoByID(cliente.idCliente);
        assertFalse(result.isEmpty());
        
        cliente.testBorrarCliente();
        cliente.testBorrarCliente2();
    }


    @Test(
            enabled = true,
            priority = 0,
            description = "Test que genera un correo y valida si es correcto."
    )
    public void testGenerarCorreo() {
        String result = M_ContactoEmail.generarCorreo();
        assertTrue(
                M_ContactoEmail.correo(result),
                "Correo que cauda el error %s".formatted(result)
        );
    }

    @Test(
            enabled = true,
            priority = 0,
            description = "Verificamos si validan los correo correctamente."
    )
    public void testCorreo() {
        assertTrue(M_ContactoEmail.correo("correo@correo.com"));

        assertTrue(M_ContactoEmail.correo("correo111@correo.com"));

        assertTrue(M_ContactoEmail.correo("correo.123_123@correo.com"));

        assertFalse(M_ContactoEmail.correo("@correo111@correo.com"));

        assertFalse(M_ContactoEmail.correo("correo@correo.com123"));
    }
}
