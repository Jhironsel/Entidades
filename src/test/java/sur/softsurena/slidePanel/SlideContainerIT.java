package sur.softsurena.slidePanel;

import java.awt.Graphics;
import java.awt.Insets;
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
public class SlideContainerIT {
    
    public SlideContainerIT() {
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
     * Test of getInsets method, of class SlideContainer.
     */
    @Test
    public void testGetInsets() {
        System.out.println("getInsets");
        SlideContainer instance = new SlideContainer();
        Insets expResult = null;
        Insets result = instance.getInsets();
        assertEquals(expResult, result);
    }

    /**
     * Test of paintComponent method, of class SlideContainer.
     */
    @Test
    public void testPaintComponent() {
        System.out.println("paintComponent");
        Graphics g = null;
        SlideContainer instance = new SlideContainer();
        instance.paintComponent(g);
    }
    
}
