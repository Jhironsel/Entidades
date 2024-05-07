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
import static sur.softsurena.metodos.M_Role.ERROR_AL_ASIGNAR_ROL;
import static sur.softsurena.metodos.M_Role.ERROR_AL_CONSULTAR_LOS_ROLES_DEL_SISTEMA;
import static sur.softsurena.metodos.M_Role.ROL_ASIGNADO_A_USUARIO;
import sur.softsurena.utilidades.Resultado;

@Getter
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

//    @Test(
//            enabled = true,
//            priority = 0,
//            description = """
//                          Permite verificar los roles del sistema del usuario 
//                          sysdba.
//                          """
//    )
//    public void testComprobandoRol() {
//        List result = M_Role.comprobandoRol("sysdba");
//        
//        assertFalse(
//                result.isEmpty(), 
//                ERROR_AL_CONSULTAR_LA_VISTA_GET_ROL_DEL_S
//        );
//    }

    @Test(
            enabled = true,
            priority = 0,
            description = """
                          Permite validar que los roles del sysdba se encuentran
                          activos y disponibles.
                          """
    )
    public void testComprobandoRolesDisponibles() {
        final String userName = "sysdba";
        
        List result = M_Role.comprobandoRolesDisponibles(userName, true);
        
        assertFalse(
                result.isEmpty(), 
                ERROR_AL_CONSULTAR_LOS_ROLES_DEL_SISTEMA
        );
        
        result = M_Role.comprobandoRolesDisponibles(userName, false);
        
        assertTrue(
                result.isEmpty(), 
                ERROR_AL_CONSULTAR_LOS_ROLES_DEL_SISTEMA
        );
    }

    @Test(
            enabled = true,
            priority = 0,
            description = """
                          Test que verifica que la tabla de consulta roles este 
                          vacia.
                          """
    )
    public void testGetRoles() {
        List result = M_Role.getRoles();
        assertFalse(
                result.isEmpty(),
                "Existen registros de roles en el sistema."
        );
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testSetRole() {
        String i_role = "";
        Resultado expResult = null;
        Resultado result = M_Role.setRole(i_role);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testDropRole() {
        String i_role = "";
        Resultado expResult = null;
        Resultado result = M_Role.dropRole(i_role);
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
        Resultado expResult = null;
        Resultado result = M_Role.asignarRol(procedimiento, rol, admin, otorgar);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = true,
            priority = 0,
            description = """
                          
                          """
    )
    public void testAsignarRolUsuario() {
        String rol = "RDB$ADMIN";
        String usuario = "SYSDBA";
        boolean admin = true;
        
        Resultado result = M_Role.asignarRolUsuario(rol, usuario, admin);
        
        assertEquals(
                result.getMensaje(), 
                ROL_ASIGNADO_A_USUARIO,
                ERROR_AL_ASIGNAR_ROL
        );
        
        assertEquals(
                result.getIcono(), 
                JOptionPane.INFORMATION_MESSAGE,
                ERROR_AL_ASIGNAR_ROL
        );
        
        assertTrue(
                result.getEstado(),
                ERROR_AL_ASIGNAR_ROL
        );
        
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testQuitarRolUsuario() {
        String rol = "";
        String usuario = "";
        Resultado expResult = null;
        Resultado result = M_Role.quitarRolUsuario(rol, usuario);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testCreateRole() {
        String rolee = "";
        Resultado expResult = null;
        Resultado result = M_Role.createRole(rolee);
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
        Resultado expResult = null;
        Resultado result = M_Role.modificarRol(actual, nuevo);
        assertEquals(result, expResult);
    }

}
