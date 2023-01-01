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
public class PesoParaEstaturaIT {
    
    public PesoParaEstaturaIT() {
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
     * Test of createDemoPanel method, of class PesoParaEstatura.
     */
    @Test
    public void testCreateDemoPanel() {
        System.out.println("createDemoPanel");
        PesoParaEstatura instance = null;
        JPanel expResult = null;
        JPanel result = instance.createDemoPanel();
        assertEquals(expResult, result);
    }
    
}
