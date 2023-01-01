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
public class Codigo_PostalIT {
    
    public Codigo_PostalIT() {
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
     * Test of getCodigoPostal method, of class Codigo_Postal.
     */
    @Test
    public void testGetCodigoPostal() {
        System.out.println("getCodigoPostal");
        int id_provincia = 0;
        ResultSet expResult = null;
        ResultSet result = Codigo_Postal.getCodigoPostal(id_provincia);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Codigo_Postal.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Codigo_Postal instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Codigo_Postal.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Codigo_Postal.Codigo_PostalBuilder expResult = null;
        Codigo_Postal.Codigo_PostalBuilder result = Codigo_Postal.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Codigo_Postal.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Codigo_Postal instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdProvincia method, of class Codigo_Postal.
     */
    @Test
    public void testGetIdProvincia() {
        System.out.println("getIdProvincia");
        Codigo_Postal instance = null;
        int expResult = 0;
        int result = instance.getIdProvincia();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLocalidad method, of class Codigo_Postal.
     */
    @Test
    public void testGetLocalidad() {
        System.out.println("getLocalidad");
        Codigo_Postal instance = null;
        String expResult = "";
        String result = instance.getLocalidad();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCodigo_postal method, of class Codigo_Postal.
     */
    @Test
    public void testGetCodigo_postal() {
        System.out.println("getCodigo_postal");
        Codigo_Postal instance = null;
        int expResult = 0;
        int result = instance.getCodigo_postal();
        assertEquals(expResult, result);
    }
    
}
