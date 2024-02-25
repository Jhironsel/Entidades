package sur.softsurena.metodos;

import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.utilidades.Resultados;
import static sur.softsurena.metodos.M_Role.ROL_ASIGNADO_A_USUARIO;

public class M_RoleNGTest {
    
    public M_RoleNGTest() {
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
            enabled = false,
            description = "",
            priority = 0
    )
    public void testComprobandoRol() {
        String userName = "";
        List expResult = null;
        List result = M_Role.comprobandoRol(userName);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testComprobandoRolesDisponibles() {
        String userName = "";
        List expResult = null;
        List result = M_Role.comprobandoRolesDisponibles(userName);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testGetRoles() {
        List expResult = null;
        List result = M_Role.getRoles();
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testSetRole() {
        String i_role = "";
        Resultados expResult = null;
        Resultados result = M_Role.setRole(i_role);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testDropRole() {
        String i_role = "";
        Resultados expResult = null;
        Resultados result = M_Role.dropRole(i_role);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testAsignarRol() {
        String procedimiento = "";
        String rol = "";
        boolean admin = false;
        boolean otorgar = false;
        Resultados expResult = null;
        Resultados result = M_Role.asignarRol(procedimiento, rol, admin, otorgar);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = true,
            description = "",
            priority = 0
    )
    public void testAsignarRolUsuario() {
        String rol = "RDB$ADMIN";
        String usuario = "SYSDBA";
        boolean admin = true;
        Resultados expResult = Resultados
                .builder()
                .mensaje(ROL_ASIGNADO_A_USUARIO)
                .build();
        Resultados result = M_Role.asignarRolUsuario(rol, usuario, admin);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testQuitarRolUsuario() {
        String rol = "";
        String usuario = "";
        Resultados expResult = null;
        Resultados result = M_Role.quitarRolUsuario(rol, usuario);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testCreateRole() {
        String rolee = "";
        Resultados expResult = null;
        Resultados result = M_Role.createRole(rolee);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testModificarRol() {
        String actual = "";
        String nuevo = "";
        Resultados expResult = null;
        Resultados result = M_Role.modificarRol(actual, nuevo);
        assertEquals(result, expResult);
    }
    
}
