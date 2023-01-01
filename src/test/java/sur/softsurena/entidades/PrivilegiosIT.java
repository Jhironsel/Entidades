package sur.softsurena.entidades;

import java.sql.ResultSet;
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
public class PrivilegiosIT {
    
    public PrivilegiosIT() {
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
     * Test of getRoles method, of class Privilegios.
     */
    @Test
    public void testGetRoles() {
        System.out.println("getRoles");
        ResultSet expResult = null;
        ResultSet result = Privilegios.getRoles();
        assertEquals(expResult, result);
    }

    /**
     * Test of privilegioTabla method, of class Privilegios.
     */
    @Test
    public void testPrivilegioTabla() {
        System.out.println("privilegioTabla");
        Privilegios p = null;
        boolean expResult = false;
        boolean result = Privilegios.privilegioTabla(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of privilegioCampo method, of class Privilegios.
     */
    @Test
    public void testPrivilegioCampo() {
        System.out.println("privilegioCampo");
        Privilegios p = null;
        boolean expResult = false;
        boolean result = Privilegios.privilegioCampo(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Privilegios.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Privilegios instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Privilegios.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Privilegios.PrivilegiosBuilder expResult = null;
        Privilegios.PrivilegiosBuilder result = Privilegios.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUser_name method, of class Privilegios.
     */
    @Test
    public void testGetUser_name() {
        System.out.println("getUser_name");
        Privilegios instance = null;
        String expResult = "";
        String result = instance.getUser_name();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrivilegio method, of class Privilegios.
     */
    @Test
    public void testGetPrivilegio() {
        System.out.println("getPrivilegio");
        Privilegios instance = null;
        char expResult = ' ';
        char result = instance.getPrivilegio();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNombre_relacion method, of class Privilegios.
     */
    @Test
    public void testGetNombre_relacion() {
        System.out.println("getNombre_relacion");
        Privilegios instance = null;
        String expResult = "";
        String result = instance.getNombre_relacion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNombre_campo method, of class Privilegios.
     */
    @Test
    public void testGetNombre_campo() {
        System.out.println("getNombre_campo");
        Privilegios instance = null;
        String expResult = "";
        String result = instance.getNombre_campo();
        assertEquals(expResult, result);
    }
    
}
