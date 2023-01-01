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
public class ProveedoresIT {
    
    public ProveedoresIT() {
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
     * Test of agregarProveedor method, of class Proveedores.
     */
    @Test
    public void testAgregarProveedor() {
        System.out.println("agregarProveedor");
        Proveedores p = null;
        String expResult = "";
        String result = Proveedores.agregarProveedor(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of modificarProveedor method, of class Proveedores.
     */
    @Test
    public void testModificarProveedor() {
        System.out.println("modificarProveedor");
        Proveedores p = null;
        String expResult = "";
        String result = Proveedores.modificarProveedor(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of getProveedor method, of class Proveedores.
     */
    @Test
    public void testGetProveedor() {
        System.out.println("getProveedor");
        ResultSet expResult = null;
        ResultSet result = Proveedores.getProveedor();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Proveedores.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Proveedores instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Proveedores.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Proveedores.ProveedoresBuilder expResult = null;
        Proveedores.ProveedoresBuilder result = Proveedores.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCodigoProveedor method, of class Proveedores.
     */
    @Test
    public void testGetCodigoProveedor() {
        System.out.println("getCodigoProveedor");
        Proveedores instance = null;
        String expResult = "";
        String result = instance.getCodigoProveedor();
        assertEquals(expResult, result);
    }
    
}
