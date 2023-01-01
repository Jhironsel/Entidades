package sur.softsurena.entidades;

import RSMaterialComponent.RSComboBox;
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
public class ProvinciasIT {
    
    public ProvinciasIT() {
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
     * Test of getProvincias method, of class Provincias.
     */
    @Test
    public void testGetProvincias() {
        System.out.println("getProvincias");
        RSComboBox jcbProvincias = null;
        Provincias.getProvincias(jcbProvincias);
    }

    /**
     * Test of toString method, of class Provincias.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Provincias instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Provincias.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Provincias.ProvinciasBuilder expResult = null;
        Provincias.ProvinciasBuilder result = Provincias.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Provincias.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Provincias instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNombre method, of class Provincias.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Provincias instance = null;
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test of getZona method, of class Provincias.
     */
    @Test
    public void testGetZona() {
        System.out.println("getZona");
        Provincias instance = null;
        String expResult = "";
        String result = instance.getZona();
        assertEquals(expResult, result);
    }
    
}
