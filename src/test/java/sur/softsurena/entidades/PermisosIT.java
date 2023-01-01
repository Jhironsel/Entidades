package sur.softsurena.entidades;

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
public class PermisosIT {
    
    public PermisosIT() {
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
     * Test of toString method, of class Permisos.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Permisos instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Permisos.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Permisos.PermisosBuilder expResult = null;
        Permisos.PermisosBuilder result = Permisos.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getConsultar method, of class Permisos.
     */
    @Test
    public void testGetConsultar() {
        System.out.println("getConsultar");
        Permisos instance = null;
        Boolean expResult = null;
        Boolean result = instance.getConsultar();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInsertar method, of class Permisos.
     */
    @Test
    public void testGetInsertar() {
        System.out.println("getInsertar");
        Permisos instance = null;
        Boolean expResult = null;
        Boolean result = instance.getInsertar();
        assertEquals(expResult, result);
    }

    /**
     * Test of getActualizar method, of class Permisos.
     */
    @Test
    public void testGetActualizar() {
        System.out.println("getActualizar");
        Permisos instance = null;
        Boolean expResult = null;
        Boolean result = instance.getActualizar();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBorrar method, of class Permisos.
     */
    @Test
    public void testGetBorrar() {
        System.out.println("getBorrar");
        Permisos instance = null;
        Boolean expResult = null;
        Boolean result = instance.getBorrar();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVista method, of class Permisos.
     */
    @Test
    public void testGetVista() {
        System.out.println("getVista");
        Permisos instance = null;
        String expResult = "";
        String result = instance.getVista();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLoginUser method, of class Permisos.
     */
    @Test
    public void testGetLoginUser() {
        System.out.println("getLoginUser");
        Permisos instance = null;
        String expResult = "";
        String result = instance.getLoginUser();
        assertEquals(expResult, result);
    }
    
}
