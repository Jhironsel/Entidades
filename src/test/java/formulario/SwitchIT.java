package formulario;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
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
public class SwitchIT {
    
    public SwitchIT() {
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
     * Test of paintComponent method, of class Switch.
     */
    @Test
    public void testPaintComponent() {
        System.out.println("paintComponent");
        Graphics g = null;
        Switch instance = new Switch();
        instance.paintComponent(g);
    }

    /**
     * Test of isOnOff method, of class Switch.
     */
    @Test
    public void testIsOnOff() {
        System.out.println("isOnOff");
        Switch instance = new Switch();
        boolean expResult = false;
        boolean result = instance.isOnOff();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOnOff method, of class Switch.
     */
    @Test
    public void testSetOnOff() {
        System.out.println("setOnOff");
        boolean OnOff = false;
        Switch instance = new Switch();
        instance.setOnOff(OnOff);
    }

    /**
     * Test of getBackgroundColor method, of class Switch.
     */
    @Test
    public void testGetBackgroundColor() {
        System.out.println("getBackgroundColor");
        Switch instance = new Switch();
        Color expResult = null;
        Color result = instance.getBackgroundColor();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBackgroundColor method, of class Switch.
     */
    @Test
    public void testSetBackgroundColor() {
        System.out.println("setBackgroundColor");
        Color backgroundColor = null;
        Switch instance = new Switch();
        instance.setBackgroundColor(backgroundColor);
    }

    /**
     * Test of getButtonColor method, of class Switch.
     */
    @Test
    public void testGetButtonColor() {
        System.out.println("getButtonColor");
        Switch instance = new Switch();
        Color expResult = null;
        Color result = instance.getButtonColor();
        assertEquals(expResult, result);
    }

    /**
     * Test of setButtonColor method, of class Switch.
     */
    @Test
    public void testSetButtonColor() {
        System.out.println("setButtonColor");
        Color buttonColor = null;
        Switch instance = new Switch();
        instance.setButtonColor(buttonColor);
    }

    /**
     * Test of mouseClicked method, of class Switch.
     */
    @Test
    public void testMouseClicked() {
        System.out.println("mouseClicked");
        MouseEvent e = null;
        Switch instance = new Switch();
        instance.mouseClicked(e);
    }

    /**
     * Test of mousePressed method, of class Switch.
     */
    @Test
    public void testMousePressed() {
        System.out.println("mousePressed");
        MouseEvent e = null;
        Switch instance = new Switch();
        instance.mousePressed(e);
    }

    /**
     * Test of mouseReleased method, of class Switch.
     */
    @Test
    public void testMouseReleased() {
        System.out.println("mouseReleased");
        MouseEvent e = null;
        Switch instance = new Switch();
        instance.mouseReleased(e);
    }

    /**
     * Test of mouseEntered method, of class Switch.
     */
    @Test
    public void testMouseEntered() {
        System.out.println("mouseEntered");
        MouseEvent e = null;
        Switch instance = new Switch();
        instance.mouseEntered(e);
    }

    /**
     * Test of mouseExited method, of class Switch.
     */
    @Test
    public void testMouseExited() {
        System.out.println("mouseExited");
        MouseEvent e = null;
        Switch instance = new Switch();
        instance.mouseExited(e);
    }
    
}
