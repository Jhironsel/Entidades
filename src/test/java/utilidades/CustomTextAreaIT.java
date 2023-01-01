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
public class CustomTextAreaIT {
    
    public CustomTextAreaIT() {
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
     * Test of getPrevio method, of class CustomTextArea.
     */
    @Test
    public void testGetPrevio() {
        System.out.println("getPrevio");
        CustomTextArea instance = new CustomTextArea();
        Image expResult = null;
        Image result = instance.getPrevio();
        assertEquals(expResult, result);
    }

    /**
     * Test of isOpaque method, of class CustomTextArea.
     */
    @Test
    public void testIsOpaque() {
        System.out.println("isOpaque");
        CustomTextArea instance = new CustomTextArea();
        boolean expResult = false;
        boolean result = instance.isOpaque();
        assertEquals(expResult, result);
    }

    /**
     * Test of paintComponent method, of class CustomTextArea.
     */
    @Test
    public void testPaintComponent() {
        System.out.println("paintComponent");
        Graphics g = null;
        CustomTextArea instance = new CustomTextArea();
        instance.paintComponent(g);
    }
    
}
