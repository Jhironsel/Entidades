package sur.softsurena.metodos;

import java.util.List;
import lombok.Getter;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Turno;
import sur.softsurena.utilidades.Resultado;

/**
 *
 * @author jhironsel
 */
@Getter
public class M_TurnoNGTest {

    public M_TurnoNGTest() {
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
            priority = 0,
            description = ""
    )
    public void testTurnosActivoByUsuario() {
        String userName = "";
        Turno expResult = null;
        Turno result = M_Turno.turnosActivoByUsuario(userName);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testUsuarioTurnoActivo() {
        String userName = "";
        boolean expResult = false;
        boolean result = M_Turno.usuarioTurnoActivo(userName);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetTurnosActivos() {
        List expResult = null;
        List result = M_Turno.getTurnosActivos();
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetTurnosByUserName() {
        String userName = "";
        List expResult = null;
        List result = M_Turno.getTurnosByUserName(userName);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testHabilitarTurno() {
        int id_almacen = 0;
        String idUsuario = "";
        Resultado expResult = null;
        Resultado result = M_Turno.habilitarTurno(id_almacen, idUsuario);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testCerrarTurno() {
        Integer idTurno = null;
        boolean expResult = false;
        boolean result = M_Turno.cerrarTurno(idTurno);
        assertEquals(result, expResult);
    }
}
