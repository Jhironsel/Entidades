package sur.softsurena.metodos;

import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.JOptionPane;
import lombok.Getter;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Control_Consulta;
import static sur.softsurena.metodos.M_Control_Consulta.CONSULTA_MODIFICADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Control_Consulta.CONTROL_CONSULTA_AGREGADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Control_Consulta.CONTROL__CONSULTA_BORRADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Control_Consulta.ERROR_AL_AGREGAR__CONTROL__CONSULTA_AL_SIST;
import static sur.softsurena.metodos.M_Control_Consulta.ERROR_AL_BORRAR_CONTROL_DE_LA_CONSULTA;
import static sur.softsurena.metodos.M_Control_Consulta.ERROR_AL_MODIFICAR_CONSULTA;
import sur.softsurena.utilidades.Resultado;

/**
 *
 * @author jhironsel
 */
@Getter
public class M_Control_ConsultaNGTest {

    private int idControlConsulta;

    public M_Control_ConsultaNGTest() {
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
            description = "Te que permite registrar un control de una consulta."
    )
    public void testAgregarControlConsulta() {

        Resultado result = M_Control_Consulta.agregarControlConsulta(
                controlConsulta()
        );

        assertEquals(
                result,
                Resultado
                    .builder()
                    .mensaje(CONTROL_CONSULTA_AGREGADO_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build(),
                ERROR_AL_AGREGAR__CONTROL__CONSULTA_AL_SIST
        );


        idControlConsulta = result.getId();
    }

    @Test(
            enabled = true,
            priority = 1,
            description = ""
    )
    public void testModificarControlConsulta() {
        Resultado result = M_Control_Consulta.modificarControlConsulta(controlConsulta());
        assertEquals(
                result,
                Resultado
                        .builder()
                        .mensaje(CONSULTA_MODIFICADO_CORRECTAMENTE)
                        .icono(JOptionPane.INFORMATION_MESSAGE)
                        .estado(Boolean.TRUE)
                        .build(),
                ERROR_AL_MODIFICAR_CONSULTA
        );
    }

    @Test(
            enabled = false,
            priority = 2,
            description = ""
    )
    public void testGetFechaDoctores() {
        String fecha = "";
        boolean actual = false;
        ResultSet expResult = null;
        ResultSet result = M_Control_Consulta.getFechaDoctores(fecha, actual);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 2,
            description = ""
    )
    public void testGetHorario() {
        String idUsuario = "";
        ResultSet expResult = null;
        ResultSet result = M_Control_Consulta.getHorario(idUsuario);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = true,
            priority = 5,
            description = "Prueba que elimina una consulta del sistema."
    )
    public void testBorrarControlConsulta() {
        Resultado result = M_Control_Consulta.borrarControlConsulta(idControlConsulta);

        assertEquals(
                result,
                Resultado
                        .builder()
                        .mensaje(CONTROL__CONSULTA_BORRADO_CORRECTAMENTE)
                        .icono(JOptionPane.INFORMATION_MESSAGE)
                        .estado(Boolean.TRUE)
                        .build(),
                ERROR_AL_BORRAR_CONTROL_DE_LA_CONSULTA
        );
    }

    private Control_Consulta controlConsulta() {
        Calendar horaInicial = Calendar.getInstance();
        horaInicial.set(
                Calendar.HOUR_OF_DAY,
                11
        );

        Calendar horaFinal = Calendar.getInstance();
        horaFinal.set(
                Calendar.HOUR_OF_DAY,
                13
        );

        return Control_Consulta
                .builder()
                .id(idControlConsulta)
                .user_name("SYSDBA")
                .cantidad(3)
                .dia("LU")
                .inicial(
                        new java.sql.Time(
                                horaInicial.getTimeInMillis()
                        )
                )
                .finall(
                        new java.sql.Time(
                                horaFinal.getTimeInMillis()
                        )
                )
                .estado(Boolean.TRUE)
                .build();
    }

}
