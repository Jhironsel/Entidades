package sur.softsurena.entidades;

import java.awt.Component;
import java.awt.TexturePaint;
import java.net.URL;
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
public class PanelConFondoIT {
    
    public PanelConFondoIT() {
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
     * Test of carga method, of class PanelConFondo.
     */
    @Test
    public void testCarga() {
        System.out.println("carga");
        URL s = null;
        Component c = null;
        TexturePaint expResult = null;
        TexturePaint result = PanelConFondo.carga(s, c);
        assertEquals(expResult, result);
    }
    
}
