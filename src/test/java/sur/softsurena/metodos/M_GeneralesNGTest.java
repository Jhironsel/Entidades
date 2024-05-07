package sur.softsurena.metodos;

import javax.swing.JOptionPane;
import lombok.Getter;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Generales;
import static sur.softsurena.metodos.M_Generales.ERROR_AL_ACTUALIZAR_LAS__GENERALES_EN_EL_S;
import static sur.softsurena.metodos.M_Generales.ERROR_AL_BORRAR_LAS_GENERALES_EN_EL_SISTE;
import static sur.softsurena.metodos.M_Generales.ERROR_AL_INSERTAR_GENERALES_EN_EL_SISTEMA;
import static sur.softsurena.metodos.M_Generales.GENERALES_ACTUALIZADA_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Generales.GENERALES_BORRADA_CORRECTAMENTE_DEL_SISTE;
import static sur.softsurena.metodos.M_Generales.GENERAL_INSERTADA_CORRECTAMENTE_EN_EL_SIS;
import sur.softsurena.utilidades.Resultado;

/**
 *
 * @author jhironsel
 */
@Getter
public class M_GeneralesNGTest {

    private final SoftAssert softAssert;
    private final M_PersonaNGTest persona;
    private String cedula;
    private int idGeneral;

    public M_GeneralesNGTest() {
        softAssert = new SoftAssert();
        persona = new M_PersonaNGTest();
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
            description = """
                          Prueba que me permite testear los dos metodos de la 
                          clase.
                          M_Generales.generarCedula() Nos genera una cedula DO.
                          M_Generales.cedula() Nos valida que la cedula sea 
                          valida.
                          """
    )
    public void testGenerarCedula() {
        String cedula2 = M_Generales.generarCedula();
        try {
            assertTrue(
                    M_Generales.cedula(cedula2),
                    "Cedula que trata de valida es incorrecta. %s"
                            .formatted(cedula2)
            );
        } catch (java.lang.AssertionError e) {
            fail(
                    "Cedula que trata de valida es incorrecta. %s"
                    .formatted(cedula2)
            );
        }
    }

    @Test(
            enabled = true,
            priority = 0,
            description = ""
    )
    public void testCedula() {
        softAssert.assertTrue(
                false,
                "Esta condición debería ser verdadera"
        );

        softAssert.assertEquals(
                1,
                2,
                "Los valores no coinciden"
        );

        //Deberia de ejecutarse al final de todas las pruebas.
        //softAssert.assertAll(); // Verifica todas las condiciones
    }

    @Test(
            enabled = true,
            priority = 0,
            description = """
                          Test que permite ingresar un registro de una general 
                          de una persona.
                          """
    )
    public void testAgregarEntidad() {
        persona.testAgregarEntidad();

        Resultado result = M_Generales.agregarEntidad(
                Generales
                        .builder()
                        .id_persona(persona.getIdPersona())
                        .cedula(M_Generales.generarCedula())
                        .id_tipo_sangre(0)
                        .estado_civil('X')
                        .build()
        );

        assertEquals(
                result.getMensaje(),
                GENERAL_INSERTADA_CORRECTAMENTE_EN_EL_SIS,
                ERROR_AL_INSERTAR_GENERALES_EN_EL_SISTEMA
        );

        assertEquals(
                result.getIcono(),
                JOptionPane.INFORMATION_MESSAGE,
                ERROR_AL_INSERTAR_GENERALES_EN_EL_SISTEMA
        );

        assertTrue(
                result.getEstado(),
                ERROR_AL_INSERTAR_GENERALES_EN_EL_SISTEMA
        );

    }

    @Test(
            enabled = true,
            priority = 1,
            description = """
                          Test que permite validar el id de la persona, y 
                          obtener la cedula de la persona.
                          """
    )
    public void testGetEntidad() {
        Generales result = M_Generales.getEntidad(persona.getIdPersona());

        assertNotNull(
                result,
                "Resultados de la consulta de generales es nula."
        );

        cedula = result.getCedula();
    }

    @Test(
            enabled = true,
            priority = 2,
            description = """
                          Test permite validar la cedula obtenida del sistema.
                          """
    )
    public void testGetEntidadByCedula() {
        //System.out.println("Cedula a consultar: ".concat(cedula));

        Generales result = M_Generales.getEntidadByCedula(cedula);

        assertEquals(
                result.getId_persona(),
                persona.getIdPersona(),
                "Los identificadores no son iguales."
        );
    }

    @Test(
            enabled = true,
            priority = 3,
            description = ""
    )
    public void testModificarEntidad() {
        Resultado result = M_Generales.modificarEntidad(
                Generales
                        .builder()
                        .id_persona(persona.getIdPersona())
                        .cedula(M_Generales.generarCedula())
                        .id_tipo_sangre(0)
                        .estado_civil('X')
                        .build()
        );

        assertEquals(
                result.getMensaje(),
                GENERALES_ACTUALIZADA_CORRECTAMENTE,
                ERROR_AL_ACTUALIZAR_LAS__GENERALES_EN_EL_S
        );

        assertEquals(
                result.getIcono(),
                JOptionPane.INFORMATION_MESSAGE,
                ERROR_AL_ACTUALIZAR_LAS__GENERALES_EN_EL_S
        );

        assertTrue(
                result.getEstado(),
                ERROR_AL_ACTUALIZAR_LAS__GENERALES_EN_EL_S
        );
    }

    @Test(
            enabled = true,
            priority = 4,
            description = ""
    )
    public void testBorrarEntidad() {
        
        Resultado result = M_Generales.borrarEntidad(persona.getIdPersona());
        
        assertEquals(
                result.getMensaje(), 
                GENERALES_BORRADA_CORRECTAMENTE_DEL_SISTE,
                ERROR_AL_BORRAR_LAS_GENERALES_EN_EL_SISTE
        );
        
        assertEquals(
                result.getIcono(), 
                JOptionPane.INFORMATION_MESSAGE,
                ERROR_AL_BORRAR_LAS_GENERALES_EN_EL_SISTE
        );
        
        assertTrue(
                result.getEstado(),
                ERROR_AL_BORRAR_LAS_GENERALES_EN_EL_SISTE
        );
        
        persona.testEliminarEntidad();
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testEliminarEntidad() {
        Integer id = null;
        Resultado expResult = null;
        Resultado result = M_Generales.eliminarEntidad(id);
        assertEquals(result, expResult);
    }

}
