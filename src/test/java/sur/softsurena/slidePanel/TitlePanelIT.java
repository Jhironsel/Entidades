package sur.softsurena.slidePanel;

import java.awt.Color;
import java.awt.Graphics;
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
public class TitlePanelIT {
    
    public TitlePanelIT() {
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
     * Test of getWidth method, of class TitlePanel.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        TitlePanel instance = null;
        int expResult = 0;
        int result = instance.getWidth();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHeight method, of class TitlePanel.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        TitlePanel instance = null;
        int expResult = 0;
        int result = instance.getHeight();
        assertEquals(expResult, result);
    }

    /**
     * Test of paintComponent method, of class TitlePanel.
     */
    @Test
    public void testPaintComponent() {
        System.out.println("paintComponent");
        Graphics g = null;
        TitlePanel instance = null;
        instance.paintComponent(g);
    }

    /**
     * Test of setStartColor method, of class TitlePanel.
     */
    @Test
    public void testSetStartColor() {
        System.out.println("setStartColor");
        Color color = null;
        TitlePanel instance = null;
        instance.setStartColor(color);
    }

    /**
     * Test of setEndColor method, of class TitlePanel.
     */
    @Test
    public void testSetEndColor() {
        System.out.println("setEndColor");
        Color pressedColor = null;
        TitlePanel instance = null;
        instance.setEndColor(pressedColor);
    }

    /**
     * Test of getStartColor method, of class TitlePanel.
     */
    @Test
    public void testGetStartColor() {
        System.out.println("getStartColor");
        TitlePanel instance = null;
        Color expResult = null;
        Color result = instance.getStartColor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEndColor method, of class TitlePanel.
     */
    @Test
    public void testGetEndColor() {
        System.out.println("getEndColor");
        TitlePanel instance = null;
        Color expResult = null;
        Color result = instance.getEndColor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTitle method, of class TitlePanel.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        TitlePanel instance = null;
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTitle method, of class TitlePanel.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        String title = "";
        TitlePanel instance = null;
        instance.setTitle(title);
    }

    /**
     * Test of toggleState method, of class TitlePanel.
     */
    @Test
    public void testToggleState() {
        System.out.println("toggleState");
        boolean state = false;
        TitlePanel instance = null;
        instance.toggleState(state);
    }
    
}
