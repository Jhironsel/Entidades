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
import sur.softsurena.entidades.Codigo_Postal;
import sur.softsurena.entidades.Direccion;
import sur.softsurena.entidades.Distrito_municipal;
import sur.softsurena.entidades.Municipio;
import sur.softsurena.entidades.Provincia;
import static sur.softsurena.metodos.M_Direccion.DIRECCION_AGREGADA_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Direccion.DIRECCION_DE_CONTACTO_ACTUALIZADA_CORRECT;
import static sur.softsurena.metodos.M_Direccion.ERROR_AL_ACTUALIZAR_LA_DIRECCION_DEL_CONT;
import static sur.softsurena.metodos.M_Direccion.ERROR_AL_BORRAR_EL_REGISTRO_DE_LA_DIRECCI;
import static sur.softsurena.metodos.M_Direccion.ERROR_AL_INSERTAR_DIRECCION;
import static sur.softsurena.metodos.M_Direccion.REGISTRO_DE_LA_DIRECCION_BORRADO_CORRECTA;
import static sur.softsurena.metodos.M_Direccion.agregarDireccion;
import static sur.softsurena.metodos.M_Direccion.getDireccionByID;
import sur.softsurena.utilidades.Resultado;

@Getter
public class M_DireccionNGTest {

    private int id_direccion;
    private M_PersonaNGTest persona;

    public M_DireccionNGTest() {
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
                          Metodo que permite agregar un direcion de una persona.
                          """
    )
    public void testAgregarDireccion() {
        persona.testAgregarEntidad();
        Resultado result = agregarDireccion(
                Direccion
                        .builder()
                        .id_persona(persona.getIdPersona())
                        .provincia(
                                Provincia
                                        .builder()
                                        .id(0)
                                        .build()
                        )
                        .municipio(
                                Municipio
                                        .builder()
                                        .id(0)
                                        .build()
                        )
                        .distrito_municipal(Distrito_municipal
                                .builder()
                                .id(0)
                                .build()
                        )
                        .direccion("Insercion de prueba.")
                        .por_defecto(Boolean.TRUE)
                        .build()
        );

        assertEquals(
                result.getMensaje(),
                DIRECCION_AGREGADA_CORRECTAMENTE,
                ERROR_AL_INSERTAR_DIRECCION
        );

        assertEquals(
                result.getIcono(),
                JOptionPane.INFORMATION_MESSAGE,
                ERROR_AL_INSERTAR_DIRECCION
        );

        assertTrue(
                result.getEstado(),
                ERROR_AL_INSERTAR_DIRECCION
        );

        id_direccion = result.getId();

    }//FIN

    @Test(
            enabled = true,
            description = "",
            priority = 1
    )
    public void testGetDireccionByID() {
        int id_persona = 0;
        List result = getDireccionByID(id_persona);
        assertTrue(
                result.isEmpty(),
                "La lista contiene datos"
        );
    }

    @Test(
            enabled = true,
            priority = 1,
            description = ""
    )
    public void testModificarDireccion() {

        Resultado result = M_Direccion.modificarDireccion(
                Direccion
                        .builder()
                        .id(id_direccion)
                        .id_persona(persona.getIdPersona())
                        .provincia(
                                Provincia
                                        .builder()
                                        .id(2)
                                        .build()
                        )
                        .municipio(
                                Municipio
                                        .builder()
                                        .id(12)
                                        .build()
                        )
                        .distrito_municipal(
                                Distrito_municipal
                                        .builder()
                                        .id(23)
                                        .build()
                        )
                        .codigo_postal(
                                Codigo_Postal
                                        .builder()
                                        .id(0)
                                        .build()
                        )
                        .direccion("Insercion de prueba 2.")
                        .por_defecto(Boolean.TRUE)
                        .estado(Boolean.FALSE)
                        .build()
        );

        assertEquals(
                result.getMensaje(),
                DIRECCION_DE_CONTACTO_ACTUALIZADA_CORRECT,
                ERROR_AL_ACTUALIZAR_LA_DIRECCION_DEL_CONT
        );

        assertEquals(
                result.getIcono(),
                JOptionPane.INFORMATION_MESSAGE,
                ERROR_AL_ACTUALIZAR_LA_DIRECCION_DEL_CONT
        );

        assertTrue(
                result.getEstado(),
                ERROR_AL_ACTUALIZAR_LA_DIRECCION_DEL_CONT
        );
    }

    @Test(
            enabled = true,
            priority = 2,
            description = """
                          Test que realiza la eliminacion de un registros de 
                          direccion de un contacto del sistema.
                          """
    )
    public void testBorrarDireccion() {

        Resultado result = M_Direccion.borrarDireccion(id_direccion);

        assertTrue(
                result.getEstado(),
                ERROR_AL_BORRAR_EL_REGISTRO_DE_LA_DIRECCI
        );

        assertEquals(
                result.getMensaje(),
                REGISTRO_DE_LA_DIRECCION_BORRADO_CORRECTA,
                ERROR_AL_BORRAR_EL_REGISTRO_DE_LA_DIRECCI
        );

        assertEquals(
                result.getIcono(),
                JOptionPane.INFORMATION_MESSAGE,
                ERROR_AL_BORRAR_EL_REGISTRO_DE_LA_DIRECCI
        );

        persona.testEliminarEntidad();
    }
}
