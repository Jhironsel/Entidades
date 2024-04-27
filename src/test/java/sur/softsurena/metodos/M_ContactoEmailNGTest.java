package sur.softsurena.metodos;

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
import sur.softsurena.entidades.ContactoEmail;
import static sur.softsurena.metodos.M_ContactoEmail.EL_CONTACTO_DE_CORREO_FUE_ACTUALIZADO;
import static sur.softsurena.metodos.M_ContactoEmail.ERROR_AL_CONSULTAR_LA_VISTA_DE_V_CONTACTO;
import static sur.softsurena.metodos.M_ContactoEmail.ERROR_AL_EJECUTAR_EL___DEL_SISTEMA;
import sur.softsurena.utilidades.Resultado;

/**
 *
 * @author jhironsel
 */
@Getter
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
                        .id_persona(cliente.getIdCliente())
                        .email(M_ContactoEmail.generarCorreo())
                        .estado(Boolean.TRUE)
                        .por_defecto(Boolean.TRUE)
                        .build()
        );
        idCorreo = result.getId();
        assertTrue(result.getEstado());
    }
    
    @Test(
            enabled = true,
            priority = 1,
            description = "Modifica los contactos del sistema."
    )
    public void testModificarContactosEmail() {
        Resultado result = M_ContactoEmail.modificarContactosEmail(
                ContactoEmail
                        .builder()
                        .id(idCorreo)
                        .id_persona(cliente.getIdCliente())
                        .email(M_ContactoEmail.generarCorreo())
                        .estado(Boolean.TRUE)
                        .por_defecto(Boolean.FALSE)
                        .build()
        );
        
        assertTrue(
                result.getEstado(), 
                ERROR_AL_EJECUTAR_EL___DEL_SISTEMA
        );
        
        assertEquals(
                result.getIcono(),
                JOptionPane.INFORMATION_MESSAGE,
                ERROR_AL_EJECUTAR_EL___DEL_SISTEMA
        );
        
        assertEquals(
                result.getMensaje(),
                EL_CONTACTO_DE_CORREO_FUE_ACTUALIZADO, 
                ERROR_AL_EJECUTAR_EL___DEL_SISTEMA
        );
    }
    
    @Test(
            enabled = true,
            priority = 2,
            description = "Realizamos consulta y eliminamos cliente."
    )
    public void testGetCorreoByID() {
        List result = M_ContactoEmail.getCorreoByID(cliente.getIdCliente());
        
        assertFalse(
                result.isEmpty(), 
                ERROR_AL_CONSULTAR_LA_VISTA_DE_V_CONTACTO
        );
        
        assertNotNull(
                result,
                "Consulta no puede devolver nulo."
        );
        
        cliente.testBorrarCliente();
        //cliente.testBorrarCliente2();
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
                "Correo que causa el error %s".formatted(result)
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
