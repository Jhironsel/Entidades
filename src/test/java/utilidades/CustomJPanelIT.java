package utilidades;

import java.awt.Graphics;
import java.awt.Image;
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
public class CustomJPanelIT {
    
    public CustomJPanelIT() {
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
     * Test of getCustomPanel method, of class CustomJPanel.
     */
    @Test
    public void testGetCustomPanel() {
        System.out.println("getCustomPanel");
        int num = 0;
        int x = 0;
        int y = 0;
        CustomJPanel expResult = null;
        CustomJPanel result = CustomJPanel.getCustomPanel(num, x, y);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrevio method, of class CustomJPanel.
     */
    @Test
    public void testGetPrevio() {
        System.out.println("getPrevio");
        CustomJPanel instance = null;
        Image expResult = null;
        Image result = instance.getPrevio();
        assertEquals(expResult, result);
    }

    /**
     * Test of isOpaque method, of class CustomJPanel.
     */
    @Test
    public void testIsOpaque() {
        System.out.println("isOpaque");
        CustomJPanel instance = null;
        boolean expResult = false;
        boolean result = instance.isOpaque();
        assertEquals(expResult, result);
    }

    /**
     * Test of paintComponent method, of class CustomJPanel.
     */
    @Test
    public void testPaintComponent() {
        System.out.println("paintComponent");
        Graphics g = null;
        CustomJPanel instance = null;
        instance.paintComponent(g);
    }
    
}
