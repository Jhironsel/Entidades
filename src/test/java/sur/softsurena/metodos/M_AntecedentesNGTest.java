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
import sur.softsurena.entidades.Antecedente;
import sur.softsurena.entidades.Consulta;
import static sur.softsurena.metodos.M_Antecedente.ANTECEDENTE_AGREGADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Antecedente.ANTECEDENTE_MODIFICADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Antecedente.BORRADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Antecedente.ERROR_AL_MODIFICAR_ANTECEDENTE;
import static sur.softsurena.metodos.M_Antecedente.agregarAntecedente;
import static sur.softsurena.metodos.M_Antecedente.getAntecedentes;
import static sur.softsurena.metodos.M_Antecedente.modificarAntecedente;
import sur.softsurena.utilidades.Resultado;

@Getter
public class M_AntecedentesNGTest {

    private int id_antecedente;
    private final M_PacienteNGTest paciente;
    private final M_Control_ConsultaNGTest controlConsulta;

    public M_AntecedentesNGTest() {
        paciente = new M_PacienteNGTest();
        controlConsulta = new M_Control_ConsultaNGTest();
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
            description = "Agrega un antecedente de un paciente al sistema.",
            priority = 0
    )
    public void testAgregarAntecedente() {
        paciente.testAgregarPaciente();
        controlConsulta.testAgregarControlConsulta();
        
        int id_consulta = M_Consulta.agregarConsulta(
                Consulta
                        .builder()
                        .id_persona(
                                paciente.getIdPaciente()
                        )
                        .id_control_consulta(
                                controlConsulta.getIdControlConsulta()
                        )
                        .turno(0)
                        .build()
        ).getId();

        Resultado result = agregarAntecedente(
                id_consulta,
                "Prueba de antecendetes"
        );

        assertEquals(
                result.getMensaje(),
                ANTECEDENTE_AGREGADO_CORRECTAMENTE,
                "No puede ser agregado el registro."
        );

        id_antecedente = result.getId();
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testModificarAntecedente() {
        //Se actualiza el registro
        Resultado resultado = modificarAntecedente(
                id_antecedente,
                "Actualizado"
        );

        assertTrue(
                resultado.getEstado(),
                ERROR_AL_MODIFICAR_ANTECEDENTE
        );

        assertTrue(
                resultado.getMensaje().equals(
                        ANTECEDENTE_MODIFICADO_CORRECTAMENTE
                ),
                ERROR_AL_MODIFICAR_ANTECEDENTE
        );

        assertTrue(
                resultado.getIcono() == JOptionPane.INFORMATION_MESSAGE,
                ERROR_AL_MODIFICAR_ANTECEDENTE
        );
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testGetAntecedentes() {
        int idPadre = 0;
        List<Antecedente> result = M_Antecedente.getAntecedentes(idPadre);
        assertTrue(result.isEmpty(), "La tabla de antecedentes contiene informacion.");

        //Consultado el registro
        List<Antecedente> lista = getAntecedentes(
                id_antecedente
        );

        assertNotNull(
                lista,
                "La tabla de antecedentes NO contiene informacion."
        );
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testBorrarAntecedente() {
        //Se borra el antecedente
        String borrado = M_Antecedente.borrarAntecedente(id_antecedente);
        assertEquals(
                borrado,
                BORRADO_CORRECTAMENTE,
                "El registro no pudo ser eliminado del sistema."
        );
        
        paciente.testBorrarPaciente();
        controlConsulta.testBorrarControlConsulta();
    }
}
