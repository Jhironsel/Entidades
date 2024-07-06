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
import sur.softsurena.entidades.Paciente;
import static sur.softsurena.metodos.M_Paciente.ERROR_AL_BORRAR_PACIENTE;
import static sur.softsurena.metodos.M_Paciente.ERROR_AL_CONSULTAR_EL_SEXO_DE_UN_PACIENTE;
import static sur.softsurena.metodos.M_Paciente.ERROR_AL_CONSULTAR_LA_CEDULA_DEL_PACIENTE;
import static sur.softsurena.metodos.M_Paciente.ERROR_AL_CONSULTAR_LA_VISTA_GET_PACIENTES;
import static sur.softsurena.metodos.M_Paciente.ERROR_AL_INSERTAR_PACIENTE;
import static sur.softsurena.metodos.M_Paciente.ERROR_AL_MODIFICAR_PACIENTE;
import static sur.softsurena.metodos.M_Paciente.PACIENTE_AGREGADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Paciente.PACIENTE_BORRADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Paciente.PACIENTE_MODIFICADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Paciente.getListEntidad;
import sur.softsurena.utilidades.Resultado;

@Getter
public class M_PacienteNGTest {

    private M_PersonaNGTest persona;
    private Paciente paciente;

    public M_PacienteNGTest() {
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
            description = "Test que realiza el ingreso de un paciente al sistema.",
            priority = 0
    )
    public void testAgregarEntidad() {
        persona.testAgregarEntidad();

        generarPaciente();

        Resultado result = M_Paciente.agregarEntidad(paciente);

        assertEquals(
                result,
                Resultado
                        .builder()
                        .mensaje(PACIENTE_AGREGADO_CORRECTAMENTE)
                        .icono(JOptionPane.INFORMATION_MESSAGE)
                        .estado(Boolean.TRUE)
                        .build(),
                ERROR_AL_INSERTAR_PACIENTE
        );

        assertTrue(
                persona.getIdPersona() > 0,
                ERROR_AL_INSERTAR_PACIENTE
        );
    }

    @Test(
            enabled = true,
            description = "",
            priority = 1
    )
    public void testModificarEntidad() {
        generarPaciente();

        Resultado result = M_Paciente.modificarEntidad(paciente);

        assertEquals(
                result,
                Resultado
                        .builder()
                        .mensaje(PACIENTE_MODIFICADO_CORRECTAMENTE)
                        .icono(JOptionPane.INFORMATION_MESSAGE)
                        .estado(Boolean.TRUE)
                        .build(),
                ERROR_AL_MODIFICAR_PACIENTE
        );
    }

    @Test(
            enabled = true,
            description = "",
            priority = 2
    )
    public void testGetEntidad() {
        Paciente result = M_Paciente.getEntidad(persona.getIdPersona());

        assertNotNull(
                result,
                ERROR_AL_CONSULTAR_LA_VISTA_GET_PACIENTES
        );
    }

    @Test(
            enabled = true,
            priority = 2,
            description = """
                          Test que verifica que la tabla de paciente contiene 
                          registros en el sistema.
                          """
    )
    public void testGetListEntidad() {
        List<Paciente> result = getListEntidad();
        assertFalse(
                result.isEmpty(),
                "Se encuentra registros en la lista de paciente."
        );
    }

    @Test(
            enabled = true,
            description = "",
            priority = 2
    )
    public void testGetSexoPaciente() {
        assertTrue(
                M_Paciente.getSexoPaciente(persona.getIdPersona()).equals("M"),
                ERROR_AL_CONSULTAR_EL_SEXO_DE_UN_PACIENTE
        );
    }

    @Test(
            enabled = false,
            description = "",
            priority = 2
    )
    public void testExistePaciente() {

        Resultado result = M_Paciente.existePaciente("");

        assertTrue(
                result.getEstado(),
                ERROR_AL_CONSULTAR_LA_CEDULA_DEL_PACIENTE
        );
    }

    @Test(
            enabled = false,
            description = "",
            priority = 2
    )
    public void testGetPacienteActivo_boolean() {
        boolean estado = false;
        ResultSet expResult = null;
        ResultSet result = M_Paciente.getPacienteActivo(estado);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 2
    )
    public void testGetPacienteActivo_3args() {
        String filtro = "";
        String fecha = "";
        int idControlConsulta = 0;
        ResultSet expResult = null;
        ResultSet result = M_Paciente.getPacienteActivo(filtro, fecha, idControlConsulta);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 2
    )
    public void testGetPacienteActivo2() {
        String filtro = "";
        String fecha = "";
        int idControlConsulta = 0;
        ResultSet expResult = null;
        ResultSet result = M_Paciente.getPacienteActivo2(filtro, fecha, idControlConsulta);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 2
    )
    public void testGetPacienteActivo3() {
        String filtro = "";
        String fecha = "";
        int idControlConsulta = 0;
        ResultSet expResult = null;
        ResultSet result = M_Paciente.getPacienteActivo3(filtro, fecha, idControlConsulta);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = true,
            description = "Test que permite eliminar un paciente ya creado.",
            priority = 3
    )
    public void testEliminarEntidad() {
        Resultado result = M_Paciente.eliminarEntidad(persona.getIdPersona());

        assertEquals(
                result,
                Resultado
                        .builder()
                        .mensaje(PACIENTE_BORRADO_CORRECTAMENTE)
                        .icono(JOptionPane.INFORMATION_MESSAGE)
                        .estado(Boolean.TRUE)
                        .build(),
                ERROR_AL_BORRAR_PACIENTE.formatted(persona.getIdPersona())
        );

        persona.testEliminarEntidad();
    }

    public synchronized void generarPaciente() {
        paciente = Paciente
                .builder()
                .id_persona(persona.getIdPersona())
                .pesoNacimiento(BigDecimal.TEN)
                .altura(new BigDecimal("14.98"))
                .perimetroCefalico(new BigDecimal(8.5d))
                .cesarea(Boolean.FALSE)
                .tiempoGestacion(8)
                .build();
    }
}
