package sur.softsurena.graficas;

import javax.swing.JPanel;
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
public class PesoParaLongitudIT {
    
    public PesoParaLongitudIT() {
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
     * Test of createDemoPanel method, of class PesoParaLongitud.
     */
    @Test
    public void testCreateDemoPanel() {
        System.out.println("createDemoPanel");
        PesoParaLongitud instance = null;
        JPanel expResult = null;
        JPanel result = instance.createDemoPanel();
        assertEquals(expResult, result);
    }
    
}
