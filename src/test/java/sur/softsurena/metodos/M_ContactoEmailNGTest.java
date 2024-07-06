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
import static sur.softsurena.metodos.M_ContactoEmail.CONTACTO_BORRADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_ContactoEmail.CORREO_AGREGADO_O_MODIFICADO_CORRECTAMENT;
import static sur.softsurena.metodos.M_ContactoEmail.EL_CONTACTO_DE_CORREO_FUE_ACTUALIZADO;
import static sur.softsurena.metodos.M_ContactoEmail.ERROR_AL_AGREGAR_O_MODIFICAR_CORREO;
import static sur.softsurena.metodos.M_ContactoEmail.ERROR_AL_BORRAR_EL_CONTACTO_DE_CORREO_DEL;
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
    private final M_PersonaNGTest persona;

    public M_ContactoEmailNGTest() {
        persona = new M_PersonaNGTest();
    }

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

        persona.testAgregarEntidad();

        Resultado result = M_ContactoEmail.agregarContactosEmail(
                ContactoEmail
                        .builder()
                        .id_persona(persona.getIdPersona())
                        .email(M_ContactoEmail.generarCorreo())
                        .estado(Boolean.TRUE)
                        .por_defecto(Boolean.TRUE)
                        .build()
        );

        assertEquals(
                result,
                Resultado
                        .builder()
                        .mensaje(CORREO_AGREGADO_O_MODIFICADO_CORRECTAMENT)
                        .icono(JOptionPane.INFORMATION_MESSAGE)
                        .estado(Boolean.TRUE)
                        .build(),
                ERROR_AL_AGREGAR_O_MODIFICAR_CORREO
        );

        idCorreo = result.getId();

        assertTrue(
                idCorreo >= 0,
                ERROR_AL_AGREGAR_O_MODIFICAR_CORREO
        );
    }

    @Test(
            enabled = true,
            priority = 1,
            description = """
                          Modifica los contactos del sistema.
                          """
    )
    public void testModificarContactosEmail() {
        Resultado result = M_ContactoEmail.modificarContactosEmail(
                ContactoEmail
                        .builder()
                        .id(idCorreo)
                        .id_persona(persona.getIdPersona())
                        .email(M_ContactoEmail.generarCorreo())
                        .estado(Boolean.TRUE)
                        .por_defecto(Boolean.FALSE)
                        .build()
        );

        assertEquals(
                result,
                Resultado
                        .builder()
                        .mensaje(EL_CONTACTO_DE_CORREO_FUE_ACTUALIZADO)
                        .icono(JOptionPane.INFORMATION_MESSAGE)
                        .estado(Boolean.TRUE)
                        .build(),
                ERROR_AL_EJECUTAR_EL___DEL_SISTEMA
        );
    }

    @Test(
            enabled = true,
            priority = 2,
            description = "Realizamos consulta y eliminamos cliente."
    )
    public void testGetCorreoByID() {
        List result = M_ContactoEmail.getCorreoByID(
                persona.getIdPersona()
        );

        assertFalse(
                result.isEmpty(),
                ERROR_AL_CONSULTAR_LA_VISTA_DE_V_CONTACTO
        );

        assertNotNull(
                result,
                "Consulta no puede devolver nulo."
        );

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

    @Test(
            enabled = true,
            priority = 3,
            description = """
                          Verificamos si validan los correo correctamente.
                          """
    )
    public void testBorrarContactoEmail() {
        Resultado result = M_ContactoEmail.borrarContactoEmail(idCorreo);

        assertEquals(
                result,
                Resultado
                        .builder()
                        .mensaje(CONTACTO_BORRADO_CORRECTAMENTE)
                        .icono(JOptionPane.INFORMATION_MESSAGE)
                        .estado(Boolean.TRUE)
                        .build(),
                ERROR_AL_BORRAR_EL_CONTACTO_DE_CORREO_DEL
        );
        persona.testEliminarEntidad();
    }
}
