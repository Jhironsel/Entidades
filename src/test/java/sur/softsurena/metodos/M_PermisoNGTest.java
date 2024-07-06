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
import sur.softsurena.utilidades.Resultado;

/**
 *
 * @author jhironsel
 */
@Getter
public class M_PermisoNGTest {

    public M_PermisoNGTest() {
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
    public void testGetPermisosAsignados() {
        String rol = "";
        List expResult = null;
        List result = M_Permiso.getPermisosAsignados(rol);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetPermisosDisponibles() {
        String rol = "";
        List expResult = null;
        List result = M_Permiso.getPermisosDisponibles(rol);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testQuitarPermisoAdminRole() {
        String rol = "";
        String usuario = "";
        Resultado expResult = null;
        Resultado result = M_Permiso.quitarPermisoAdminRole(rol, usuario);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testQuitarPermisoAdminProcedimiento() {
        String procedimiento = "";
        String rol = "";
        Resultado expResult = null;
        Resultado result = M_Permiso.quitarPermisoAdminProcedimiento(procedimiento, rol);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testAgregarPermisoAdminProcedimiento() {
        String procedimiento = "";
        String rol = "";
        Resultado expResult = null;
        Resultado result = M_Permiso.agregarPermisoAdminProcedimiento(procedimiento, rol);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testAgregarPermisoAdminRole() {
        String role = "";
        String usuario = "";
        Resultado expResult = null;
        Resultado result = M_Permiso.agregarPermisoAdminRole(role, usuario);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testBorrarPermisoAdminProcedimiento() {
        String procedimiento = "";
        String rol = "";
        Resultado expResult = null;
        Resultado result = M_Permiso.borrarPermisoAdminProcedimiento(procedimiento, rol);
        assertEquals(result, expResult);
    }
}