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
public class TipoPersonaIT {
    
    public TipoPersonaIT() {
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
     * Test of toString method, of class TipoPersona.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        TipoPersona instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class TipoPersona.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        TipoPersona.TipoPersonaBuilder expResult = null;
        TipoPersona.TipoPersonaBuilder result = TipoPersona.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPersona method, of class TipoPersona.
     */
    @Test
    public void testGetPersona() {
        System.out.println("getPersona");
        TipoPersona instance = null;
        String expResult = "";
        String result = instance.getPersona();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAbreviatura method, of class TipoPersona.
     */
    @Test
    public void testGetAbreviatura() {
        System.out.println("getAbreviatura");
        TipoPersona instance = null;
        char expResult = ' ';
        char result = instance.getAbreviatura();
        assertEquals(expResult, result);
    }
    
}
