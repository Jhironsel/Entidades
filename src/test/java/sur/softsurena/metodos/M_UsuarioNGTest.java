package sur.softsurena.metodos;

import java.util.ArrayList;
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
import sur.softsurena.entidades.Role;
import sur.softsurena.entidades.Usuario;

public class M_UsuarioNGTest {

    public M_UsuarioNGTest() {
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
    public void testCambioClave() {
        String usuario = "sysdba";
        String clave = "1";
        boolean result = M_Usuario.cambioClave(usuario, clave);
        assertTrue("La contraseña no fue cambiada. ", result);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testAgregarUsuario() {
        Resultados expResult = null;
        List<Role> roles = new ArrayList();
        
        roles.add(
                Role
                        .builder()
                        .roleName("CAJERO")
                        .conAdmin(false)
                        .build()
        );
        
        roles.add(
                Role
                        .builder()
                        .roleName("SECRETARIA")
                        .conAdmin(false)
                        .build()
        );
        
        roles.add(
                Role
                        .builder()
                        .roleName("RRHH")
                        .conAdmin(false)
                        .build()
        );
        
        Resultados result = M_Usuario.agregarUsuario(
                Usuario
                        .builder()
                        .user_name("Prueba")
                        .clave("1")
                        .pnombre("PNombre")
                        .snombre("SNombre")
                        .apellidos("Apellidos")
                        .administrador(Boolean.FALSE)
                        .descripcion("Es un usuario de prueba.")
                        .tags("(PRUEBA='Una prueba del sistema', Otra='4352.4', ultima='234')")
                        .roles(roles)
                        .build()
        );
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testBorrarUsuario() {
        String loginName = "";
        Resultados expResult = null;
        Resultados result = M_Usuario.borrarUsuario(loginName);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testGetUsuarioActual() {
        Usuario expResult = null;
        Usuario result = M_Usuario.getUsuarioActual();
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testGetUsuario() {
        String userName = "";
        Usuario expResult = null;
        Usuario result = M_Usuario.getUsuario(userName);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testGetUsuarios() {
        List expResult = null;
        List result = M_Usuario.getUsuarios();
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testGetNombresUsuarios() {
        List expResult = null;
        List result = M_Usuario.getNombresUsuarios();
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testModificarUsuario() {
        Usuario u = null;
        Resultados expResult = null;
        Resultados result = M_Usuario.modificarUsuario(u);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testExisteUsuarioByUserName() {
        String userName = "";
        boolean expResult = false;
        boolean result = M_Usuario.existeUsuarioByUserName(userName);
        assertEquals(result, expResult);
    }
}
