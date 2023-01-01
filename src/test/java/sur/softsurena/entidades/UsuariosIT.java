package sur.softsurena.entidades;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jhironsel
 */
public class UsuariosIT {
    
    public UsuariosIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of cambioClave method, of class Usuarios.
     */
    @Test
    public void testCambioClave() {
        System.out.println("cambioClave");
        String clave = "";
        boolean expResult = false;
        boolean result = Usuarios.cambioClave(clave);
        assertEquals(expResult, result);
    }

    /**
     * Test of borrarUsuario method, of class Usuarios.
     */
    @Test
    public void testBorrarUsuario_String_boolean() {
        System.out.println("borrarUsuario");
        String loginName = "";
        boolean estado = false;
        String expResult = "";
        String result = Usuarios.borrarUsuario(loginName, estado);
        assertEquals(expResult, result);
    }

    /**
     * Test of borrarUsuario method, of class Usuarios.
     */
    @Test
    public void testBorrarUsuario_String_String() {
        System.out.println("borrarUsuario");
        String idUsuario = "";
        String rol = "";
        String expResult = "";
        String result = Usuarios.borrarUsuario(idUsuario, rol);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsuarioActual method, of class Usuarios.
     */
    @Test
    public void testGetUsuarioActual() {
        System.out.println("getUsuarioActual");
        Usuarios expResult = null;
        Usuarios result = Usuarios.getUsuarioActual();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsuario method, of class Usuarios.
     */
    @Test
    public void testGetUsuario() {
        System.out.println("getUsuario");
        String userName = "";
        Usuarios expResult = null;
        Usuarios result = Usuarios.getUsuario(userName);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsuariosActivo method, of class Usuarios.
     */
    @Test
    public void testGetUsuariosActivo() {
        System.out.println("getUsuariosActivo");
        ResultSet expResult = null;
        ResultSet result = Usuarios.getUsuariosActivo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsuarios method, of class Usuarios.
     */
    @Test
    public void testGetUsuarios() {
        System.out.println("getUsuarios");
        List<Usuarios> expResult = null;
        List<Usuarios> result = Usuarios.getUsuarios();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCajerosActivos method, of class Usuarios.
     */
    @Test
    public void testGetCajerosActivos() {
        System.out.println("getCajerosActivos");
        ResultSet expResult = null;
        ResultSet result = Usuarios.getCajerosActivos();
        assertEquals(expResult, result);
    }

    /**
     * Test of comprobandoRol method, of class Usuarios.
     */
    @Test
    public void testComprobandoRol() {
        System.out.println("comprobandoRol");
        String userName = "";
        ArrayList<String> expResult = null;
        ArrayList<String> result = Usuarios.comprobandoRol(userName);
        assertEquals(expResult, result);
    }

    /**
     * Test of agregarModificarUsuario method, of class Usuarios.
     */
    @Test
    public void testAgregarModificarUsuario() {
        System.out.println("agregarModificarUsuario");
        Usuarios u = null;
        String sql = "";
        String expResult = "";
        String result = Usuarios.agregarModificarUsuario(u, sql);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCreadorUsuario method, of class Usuarios.
     */
    @Test
    public void testGetCreadorUsuario() {
        System.out.println("getCreadorUsuario");
        String idUsuario = "";
        String expResult = "";
        String result = Usuarios.getCreadorUsuario(idUsuario);
        assertEquals(expResult, result);
    }

    /**
     * Test of existeUsuarioByUserName method, of class Usuarios.
     */
    @Test
    public void testExisteUsuarioByUserName() {
        System.out.println("existeUsuarioByUserName");
        String userName = "";
        boolean expResult = false;
        boolean result = Usuarios.existeUsuarioByUserName(userName);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Usuarios.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Usuarios instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Usuarios.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Usuarios.UsuariosBuilder expResult = null;
        Usuarios.UsuariosBuilder result = Usuarios.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getClave method, of class Usuarios.
     */
    @Test
    public void testGetClave() {
        System.out.println("getClave");
        Usuarios instance = null;
        String expResult = "";
        String result = instance.getClave();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescripcion method, of class Usuarios.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        Usuarios instance = null;
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAdministrador method, of class Usuarios.
     */
    @Test
    public void testGetAdministrador() {
        System.out.println("getAdministrador");
        Usuarios instance = null;
        Boolean expResult = null;
        Boolean result = instance.getAdministrador();
        assertEquals(expResult, result);
    }
    
}
