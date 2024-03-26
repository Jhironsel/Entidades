package sur.softsurena.metodos;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.ARS;
import sur.softsurena.entidades.Asegurado;
import sur.softsurena.entidades.Generales;
import sur.softsurena.entidades.Paciente;
import static sur.softsurena.metodos.M_ARS.agregarSeguro;
import static sur.softsurena.metodos.M_ContactoTel.generarTelMovil;
import static sur.softsurena.metodos.M_Generales.generarCedula;
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

public class M_PacienteNGTest {

    private Resultado result;
    private int id_ars;
    private int idPaciente;

    public M_PacienteNGTest() {
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
            description = "",
            priority = 0
    )
    public void testAgregarPaciente() {
        result = agregarSeguro(
                ARS
                        .builder()
                        .descripcion(
                                "ARS PRUEBA "
                                        .concat(
                                                generarTelMovil().substring(11, 16)
                                        )
                        )
                        .covertura(BigDecimal.TEN)
                        .estado(Boolean.TRUE)
                        .build()
        );

        id_ars = result.getId();

        //TODO Crear los padres y la madre del paciente.
        result = agregarPaciente(
                Paciente
                        .builder()
                        .idPadre(0)
                        .idMadre(0)
                        .asegurado(
                                Asegurado
                                        .builder()
                                        .id_ars(id_ars)
                                        .no_nss(generarTelMovil().substring(9, 16))
                                        .build()
                        )
                        .generales(
                                Generales
                                        .builder()
                                        .cedula(generarCedula())
                                        .id_tipo_sangre(0)
                                        .build()
                        )
                        .pnombre("Ciliosther")
                        .snombre("")
                        .apellidos("Diaz Liriano")
                        .sexo('F')
                        .estado(Boolean.TRUE)
                        .fecha_nacimiento(Utilidades.javaDateToSqlDate(new Date()))
                        .build()
        );

        assertTrue(
                result.getEstado(),
                ERROR_AL_INSERTAR_PACIENTE
        );

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

        idPaciente = result.getId();
    }

    @Test(
            enabled = true,
            description = "",
            priority = 1
    )
    public void testModificarPaciente() {
        result = modificarPaciente(
                Paciente
                        .builder()
                        .id_persona(idPaciente)
                        .idPadre(0)
                        .idMadre(0)
                        .asegurado(
                                Asegurado
                                        .builder()
                                        .id_ars(id_ars)
                                        .no_nss(generarTelMovil().substring(9, 16))
                                        .estado(Boolean.FALSE)
                                        .build()
                        )
                        .generales(
                                Generales
                                        .builder()
                                        .cedula(generarCedula())
                                        .id_tipo_sangre(0)
                                        .build()
                        )
                        .pnombre("Michael")
                        .snombre("")
                        .apellidos("Orozco")
                        .sexo('M')
                        .estado(Boolean.TRUE)
                        .fecha_nacimiento(
                                Utilidades.javaDateToSqlDate(new Date())
                        )
                        .build()
        );

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
                ERROR_AL_MODIFICAR_PACIENTE);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testGetSexoPaciente() {
        int idPaciente = 0;
        String expResult = "";
        String result = M_Paciente.getSexoPaciente(idPaciente);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testExistePaciente() {
        String cedula = "";
        boolean expResult = false;
        boolean result = M_Paciente.existePaciente(cedula);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testGetPacienteActivoID() {
        int idPaciente = 0;
        ResultSet expResult = null;
        Paciente result = M_Paciente.getPacienteActivoID(idPaciente);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testGetDatosNacimiento() {
        int id = 0;
        ResultSet expResult = null;
        ResultSet result = M_Paciente.getDatosNacimiento(id);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testGetIdPaciente() {
        String cedula = "";
        int expResult = 0;
        int result = M_Paciente.getIdPaciente(cedula);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
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
            priority = 0
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
            priority = 0
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
            priority = 0
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
            description = "",
            priority = 0
    )
    public void testBorrarPaciente() {
        int id = 0;
        Resultado expResult = null;
        Resultado result = M_Paciente.borrarPaciente(id);
        assertEquals(result, expResult);

        //Borrar el cliente. 
        result = borrarPaciente(idPaciente);
        assertEquals(PACIENTE_BORRADO_CORRECTAMENTE, result.toString());
    }
}
