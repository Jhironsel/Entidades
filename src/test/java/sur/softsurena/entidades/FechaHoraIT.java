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
public class FechaHoraIT {
    
    public FechaHoraIT() {
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
     * Test of getFecha method, of class FechaHora.
     */
    @Test
    public void testGetFecha() {
        System.out.println("getFecha");
        FechaHora instance = new FechaHora();
        String expResult = "";
        String result = instance.getFecha();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHora method, of class FechaHora.
     */
    @Test
    public void testGetHora() {
        System.out.println("getHora");
        FechaHora instance = new FechaHora();
        String expResult = "";
        String result = instance.getHora();
        assertEquals(expResult, result);
    }
    
}
