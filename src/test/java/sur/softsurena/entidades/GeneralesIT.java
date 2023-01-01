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
public class GeneralesIT {
    
    public GeneralesIT() {
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
     * Test of builder method, of class Generales.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Generales.GeneralesBuilder expResult = null;
        Generales.GeneralesBuilder result = Generales.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCedula method, of class Generales.
     */
    @Test
    public void testGetCedula() {
        System.out.println("getCedula");
        Generales instance = null;
        String expResult = "";
        String result = instance.getCedula();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_tipo_sangre method, of class Generales.
     */
    @Test
    public void testGetId_tipo_sangre() {
        System.out.println("getId_tipo_sangre");
        Generales instance = null;
        int expResult = 0;
        int result = instance.getId_tipo_sangre();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEstado_civil method, of class Generales.
     */
    @Test
    public void testGetEstado_civil() {
        System.out.println("getEstado_civil");
        Generales instance = null;
        char expResult = ' ';
        char result = instance.getEstado_civil();
        assertEquals(expResult, result);
    }
    
}
