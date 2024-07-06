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
import sur.softsurena.abstracta.Persona;
import sur.softsurena.conexion.Conexion;
import static sur.softsurena.metodos.M_Persona.ERROR_ACTUALIZAR_PERSONA_EN_EL_SISTEMA;
import static sur.softsurena.metodos.M_Persona.ERROR_AL_CONSULTAR_LA_VISTA_V_PERSONAS_DE;
import static sur.softsurena.metodos.M_Persona.ERROR_AL_ELIMINAR_REGISTROS_CODIGO_S;
import static sur.softsurena.metodos.M_Persona.ERROR_AL_REGISTRAR_PERSONA_AL_SISTEMA;
import static sur.softsurena.metodos.M_Persona.PERSONA_ACTUALIZADA_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Persona.REGISTRO_DE_PERSONA_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Persona.REGISTRO_DE_PERSONA_ELIMINADO_CORRECTAMEN;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.javaDateToSqlDate;
import static sur.softsurena.utilidades.Utilidades.stringToDate;

/**
 *
 * @author jhironsel
 */
@Getter
public class M_PersonaNGTest {

    private int idPersona;

    public M_PersonaNGTest() {
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

    //--------------------------------------------------------------------------
    @Test(
            enabled = true,
            priority = 0,
            description = """
                          Prueba que permite insertar una persona al sistema.
                          y obtener su ID en la variable idPersona.
                          """
    )
    public void testAgregarEntidad() {

        Resultado result = M_Persona.agregarEntidad(
                persona()
        );

        assertEquals(
                result,
                Resultado
                        .builder()
                        .mensaje(REGISTRO_DE_PERSONA_CORRECTAMENTE)
                        .icono(JOptionPane.INFORMATION_MESSAGE)
                        .estado(Boolean.TRUE)
                        .build(),
                ERROR_AL_REGISTRAR_PERSONA_AL_SISTEMA
        );

        assertTrue(
                result.getId() > 0,
                ERROR_AL_REGISTRAR_PERSONA_AL_SISTEMA.formatted(result.getId())
        );

        idPersona = result.getId();

    }

    //--------------------------------------------------------------------------
    @Test(
            enabled = true,
            priority = 1,
            description = """
                          Prueba que permite actualizar un registros del sistema
                          previamente insertado.
                          """
    )
    public void testModificarEntidad() {

        Resultado result = M_Persona.modificarEntidad(persona());

        assertEquals(
                result,
                Resultado
                        .builder()
                        .mensaje(PERSONA_ACTUALIZADA_CORRECTAMENTE)
                        .icono(JOptionPane.INFORMATION_MESSAGE)
                        .estado(Boolean.TRUE)
                        .build(),
                ERROR_ACTUALIZAR_PERSONA_EN_EL_SISTEMA
        );
    }

    //--------------------------------------------------------------------------
    @Test(
            enabled = true,
            priority = 2,
            description = """
                          Test que permite obtener un registro de la base de 
                          datos del sistema.
                          """
    )
    public void testGetEntidad() {
        Persona result = M_Persona.getEntidad(idPersona);
        assertNotNull(
                result,
                "Registros no encontrado en el sistema. CODIGO: [ %s ]".formatted(idPersona)
        );

        result = M_Persona.getEntidad(0);
        assertNotNull(
                result,
                "Registros de CLIENTE GENERICO NO ENCONTRADO."
        );
    }

    //--------------------------------------------------------------------------
    @Test(
            enabled = true,
            priority = 3,
            description = """
                          Test que permite obtener todos los registros del 
                          sistema.
                          """
    )
    public void testGetListEntidad() {
        List result = M_Persona.getListEntidad();
        assertFalse(
                result.isEmpty(),
                ERROR_AL_CONSULTAR_LA_VISTA_V_PERSONAS_DE
        );
    }

    //--------------------------------------------------------------------------
    @Test(
            enabled = true,
            priority = 4,
            description = """
                          Test que permite eliminar el registro del sistema de 
                          la tabla de Persona.
                          """
    )
    public void testEliminarEntidad() {

        Resultado result = M_Persona.eliminarEntidad(idPersona);

        assertEquals(
                result,
                Resultado
                        .builder()
                        .mensaje(REGISTRO_DE_PERSONA_ELIMINADO_CORRECTAMEN)
                        .icono(JOptionPane.INFORMATION_MESSAGE)
                        .estado(Boolean.TRUE)
                        .build(),
                ERROR_AL_ELIMINAR_REGISTROS_CODIGO_S.formatted(idPersona)
        );
    }

    private Persona persona() {
        return Persona
                .builder()
                .id_persona(idPersona)
                .persona('J')
                .pnombre("Jhadiel")
                .snombre("Jhoandry")
                .apellidos("Diaz Paniagua")
                .sexo('M')
                .fecha_nacimiento(
                        javaDateToSqlDate(
                                stringToDate("23.06.2017", "dd.MM.yyyy")
                        )
                )
                .estado(Boolean.FALSE)
                .build();
    }

}
