package sur.softsurena.seguridadPatron;

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
public class UnlockPanelIT {
    
    public UnlockPanelIT() {
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
     * Test of getMyKey method, of class UnlockPanel.
     */
    @Test
    public void testGetMyKey() {
        System.out.println("getMyKey");
        UnlockPanel instance = null;
        String expResult = "";
        String result = instance.getMyKey();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMyKey method, of class UnlockPanel.
     */
    @Test
    public void testSetMyKey() {
        System.out.println("setMyKey");
        String myKey = "";
        UnlockPanel instance = null;
        instance.setMyKey(myKey);
    }

    /**
     * Test of paintComponent method, of class UnlockPanel.
     */
    @Test
    public void testPaintComponent() {
        System.out.println("paintComponent");
        Graphics g = null;
        UnlockPanel instance = null;
        instance.paintComponent(g);
    }

    /**
     * Test of mouseClicked method, of class UnlockPanel.
     */
    @Test
    public void testMouseClicked() {
        System.out.println("mouseClicked");
        MouseEvent e = null;
        UnlockPanel instance = null;
        instance.mouseClicked(e);
    }

    /**
     * Test of mousePressed method, of class UnlockPanel.
     */
    @Test
    public void testMousePressed() {
        System.out.println("mousePressed");
        MouseEvent e = null;
        UnlockPanel instance = null;
        instance.mousePressed(e);
    }

    /**
     * Test of mouseReleased method, of class UnlockPanel.
     */
    @Test
    public void testMouseReleased() {
        System.out.println("mouseReleased");
        MouseEvent e = null;
        UnlockPanel instance = null;
        instance.mouseReleased(e);
    }

    /**
     * Test of mouseEntered method, of class UnlockPanel.
     */
    @Test
    public void testMouseEntered() {
        System.out.println("mouseEntered");
        MouseEvent e = null;
        UnlockPanel instance = null;
        instance.mouseEntered(e);
    }

    /**
     * Test of mouseExited method, of class UnlockPanel.
     */
    @Test
    public void testMouseExited() {
        System.out.println("mouseExited");
        MouseEvent e = null;
        UnlockPanel instance = null;
        instance.mouseExited(e);
    }

    /**
     * Test of mouseDragged method, of class UnlockPanel.
     */
    @Test
    public void testMouseDragged() {
        System.out.println("mouseDragged");
        MouseEvent e = null;
        UnlockPanel instance = null;
        instance.mouseDragged(e);
    }

    /**
     * Test of mouseMoved method, of class UnlockPanel.
     */
    @Test
    public void testMouseMoved() {
        System.out.println("mouseMoved");
        MouseEvent e = null;
        UnlockPanel instance = null;
        instance.mouseMoved(e);
    }

    /**
     * Test of checkUnlockPattern method, of class UnlockPanel.
     */
    @Test
    public void testCheckUnlockPattern() {
        System.out.println("checkUnlockPattern");
        UnlockPanel instance = null;
        instance.checkUnlockPattern();
    }
    
}
