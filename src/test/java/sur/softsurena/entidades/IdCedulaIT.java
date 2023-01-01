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
public class IdCedulaIT {
    
    public IdCedulaIT() {
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
     * Test of getId method, of class IdCedula.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        IdCedula instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class IdCedula.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        IdCedula instance = null;
        instance.setId(id);
    }

    /**
     * Test of getCedula method, of class IdCedula.
     */
    @Test
    public void testGetCedula() {
        System.out.println("getCedula");
        IdCedula instance = null;
        String expResult = "";
        String result = instance.getCedula();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCedula method, of class IdCedula.
     */
    @Test
    public void testSetCedula() {
        System.out.println("setCedula");
        String cedula = "";
        IdCedula instance = null;
        instance.setCedula(cedula);
    }

    /**
     * Test of toString method, of class IdCedula.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        IdCedula instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
