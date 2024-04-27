package sur.softsurena.metodos;

import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import lombok.Getter;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Asegurado;
import sur.softsurena.entidades.Generales;
import sur.softsurena.entidades.Paciente;
import static sur.softsurena.metodos.M_ContactoTel.generarTelMovil;
import static sur.softsurena.metodos.M_Generales.generarCedula;
import static sur.softsurena.metodos.M_Paciente.ERROR_AL_BORRAR_PACIENTE;
import static sur.softsurena.metodos.M_Paciente.ERROR_AL_CONSULTAR_EL_SEXO_DE_UN_PACIENTE;
import static sur.softsurena.metodos.M_Paciente.ERROR_AL_CONSULTAR_LA_CEDULA_DEL_PACIENTE;
import static sur.softsurena.metodos.M_Paciente.ERROR_AL_CONSULTAR_LA_VISTA_GET_PACIENTES;
import static sur.softsurena.metodos.M_Paciente.ERROR_AL_INSERTAR_PACIENTE;
import static sur.softsurena.metodos.M_Paciente.ERROR_AL_MODIFICAR_PACIENTE;
import static sur.softsurena.metodos.M_Paciente.PACIENTE_AGREGADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Paciente.PACIENTE_BORRADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Paciente.PACIENTE_MODIFICADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Paciente.agregarPaciente;
import static sur.softsurena.metodos.M_Paciente.borrarPaciente;
import static sur.softsurena.metodos.M_Paciente.modificarPaciente;
import sur.softsurena.utilidades.Resultado;
import sur.softsurena.utilidades.Utilidades;

@Getter
public class M_PacienteNGTest {

    private M_ARSNGTest ars;
    private Integer idPaciente = -1;
    private String cedulaPaciente;
    private Paciente paciente;

    public M_PacienteNGTest() {
        ars = new M_ARSNGTest();
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
            enabled = false,
            description = "Test que realiza el ingreso de un paciente al sistema.",
            priority = 0
    )
    public void testAgregarPaciente() {
        ars.testAgregarArs();
        
        cedulaPaciente = generarCedula();
        generarPaciente();
        
        Resultado result = agregarPaciente(paciente);

        assertEquals(
                result.getMensaje(),
                PACIENTE_AGREGADO_CORRECTAMENTE,
                ERROR_AL_INSERTAR_PACIENTE
        );

        assertEquals(
                result.getIcono(),
                JOptionPane.INFORMATION_MESSAGE,
                ERROR_AL_INSERTAR_PACIENTE
        );

        assertTrue(
                result.getEstado(),
                ERROR_AL_INSERTAR_PACIENTE
        );

        idPaciente = result.getId();

        assertTrue(
                idPaciente > 0,
                ERROR_AL_INSERTAR_PACIENTE
        );
    }

    @Test(
            enabled = false,
            description = "",
            priority = 1
    )
    public void testModificarPaciente() {
        cedulaPaciente = generarCedula();
        generarPaciente();
        
        Resultado result = modificarPaciente(paciente);

        assertTrue(
                result.getEstado(),
                ERROR_AL_MODIFICAR_PACIENTE
        );

        assertEquals(
                result.getIcono(),
                JOptionPane.INFORMATION_MESSAGE,
                ERROR_AL_MODIFICAR_PACIENTE
        );

        assertEquals(
                result.getMensaje(),
                PACIENTE_MODIFICADO_CORRECTAMENTE,
                ERROR_AL_MODIFICAR_PACIENTE
        );
    }

    @Test(
            enabled = false,
            description = "",
            priority = 2
    )
    public void testGetSexoPaciente() {
        assertTrue(
                M_Paciente.getSexoPaciente(idPaciente).equals("M"),
                ERROR_AL_CONSULTAR_EL_SEXO_DE_UN_PACIENTE
        );
    }

    @Test(
            enabled = false,
            description = "",
            priority = 2
    )
    public void testExistePaciente() {

        Resultado result = M_Paciente.existePaciente(cedulaPaciente);

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
    public void testGetPacienteActivoID() {
        Paciente result = M_Paciente.getPacienteActivoID(idPaciente);

        assertNotNull(
                result,
                ERROR_AL_CONSULTAR_LA_VISTA_GET_PACIENTES
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
            enabled = false,
            description = "Test que permite eliminar un paciente ya creado.",
            priority = 3
    )
    public void testBorrarPaciente() {
        Resultado result = borrarPaciente(idPaciente);

        assertEquals(
                result.getMensaje(),
                PACIENTE_BORRADO_CORRECTAMENTE,
                ERROR_AL_BORRAR_PACIENTE
        );

        assertEquals(
                result.getIcono(),
                JOptionPane.INFORMATION_MESSAGE,
                ERROR_AL_BORRAR_PACIENTE
        );

        assertTrue(
                result.getEstado(),
                ERROR_AL_BORRAR_PACIENTE
        );

        ars.testBorrarSeguro();
    }

    public synchronized void generarPaciente() {        
        paciente = Paciente
                .builder()
                .id_persona(idPaciente)
                .pnombre("Paciente Prueba")
                .snombre("SNombre Prueba")
                .apellidos("Apellidos Prueba")
                .sexo('M')
                .fecha_nacimiento(
                        Utilidades.javaDateToSqlDate(
                                new Date()
                        )
                )
                .estado(Boolean.TRUE)
                .generales(
                        Generales
                                .builder()
                                .cedula(cedulaPaciente)
                                .id_tipo_sangre(0)
                                .build()
                )
                .asegurado(
                        Asegurado
                                .builder()
                                .id_ars(ars.getId_ARS())
                                .no_nss(generarTelMovil().substring(9, 16))
                                .estado(Boolean.TRUE)
                                .build()
                )
                .build();
    }

}
