package sur.softsurena.metodos;

import java.sql.ResultSet;
import java.util.Date;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
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
import static sur.softsurena.metodos.M_Paciente.PACIENTE_AGREGADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Paciente.PACIENTE_BORRADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Paciente.PACIENTE_MODIFICADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Paciente.agregarPaciente;
import static sur.softsurena.metodos.M_Paciente.borrarPaciente;
import static sur.softsurena.metodos.M_Paciente.modificarPaciente;
import sur.softsurena.utilidades.Resultado;
import sur.softsurena.utilidades.Utilidades;

public class M_PacienteNGTest {
    
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
        assertTrue("Error al conectarse...", Conexion.verificar().getEstado());
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
        //Prueba de Insersion de paciente.
        Asegurado asegurado = Asegurado
                .builder()
                .id_ars(0)
                .no_nss(generarTelMovil().substring(9, 16))
                .build();

        Generales generales = Generales
                .builder()
                .cedula(generarCedula())
                .id_tipo_sangre(0)
                .build();

        Resultado result = agregarPaciente(
                Paciente
                        .builder()
                        .idPadre(0)
                        .idMadre(0)
                        .asegurado(asegurado)
                        .generales(generales)
                        .pnombre("Ciliosther")
                        .snombre("")
                        .apellidos("Diaz Liriano")
                        .sexo('F')
                        .estado(Boolean.TRUE)
                        .fecha_nacimiento(Utilidades.javaDateToSqlDate(new Date()))
                        .build()
        );

        assertEquals(PACIENTE_AGREGADO_CORRECTAMENTE, result.toString());

        //Prueba de Actualizacion de paciente.
        asegurado = Asegurado
                .builder()
                .id_ars(0)
                .no_nss(generarTelMovil().substring(9, 16))
                .estado(Boolean.FALSE)
                .build();

        generales = Generales
                .builder()
                .cedula(generarCedula())
                .id_tipo_sangre(0)
                .build();

        int idPaciente = result.getId();

        Paciente pUpdate = Paciente
                .builder()
                .id_persona(idPaciente)
                .idPadre(0)
                .idMadre(0)
                .asegurado(asegurado)
                .generales(generales)
                .pnombre("Michael")
                .snombre("")
                .apellidos("Orozco")
                .sexo('M')
                .estado(Boolean.TRUE)
                .fecha_nacimiento(
                        Utilidades.javaDateToSqlDate(new Date())
                )
                .build();

        result = modificarPaciente(pUpdate);

        assertEquals(PACIENTE_MODIFICADO_CORRECTAMENTE, result.toString());

        //Borrar el cliente. 
        result = borrarPaciente(idPaciente);
        assertEquals(PACIENTE_BORRADO_CORRECTAMENTE, result.toString());
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testModificarPaciente() {
        Paciente p = null;
        Resultado expResult = null;
        Resultado result = M_Paciente.modificarPaciente(p);
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
}
