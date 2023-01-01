
import java.awt.event.FocusEvent;
import javax.swing.event.DocumentEvent;
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
public class TextPromptIT {
    
    public TextPromptIT() {
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
     * Test of changeAlpha method, of class TextPrompt.
     */
    @Test
    public void testChangeAlpha_float() {
        System.out.println("changeAlpha");
        float alpha = 0.0F;
        TextPrompt instance = null;
        instance.changeAlpha(alpha);
    }

    /**
     * Test of changeAlpha method, of class TextPrompt.
     */
    @Test
    public void testChangeAlpha_int() {
        System.out.println("changeAlpha");
        int alpha = 0;
        TextPrompt instance = null;
        instance.changeAlpha(alpha);
    }

    /**
     * Test of changeStyle method, of class TextPrompt.
     */
    @Test
    public void testChangeStyle() {
        System.out.println("changeStyle");
        int style = 0;
        TextPrompt instance = null;
        instance.changeStyle(style);
    }

    /**
     * Test of getShow method, of class TextPrompt.
     */
    @Test
    public void testGetShow() {
        System.out.println("getShow");
        TextPrompt instance = null;
        TextPrompt.Show expResult = null;
        TextPrompt.Show result = instance.getShow();
        assertEquals(expResult, result);
    }

    /**
     * Test of setShow method, of class TextPrompt.
     */
    @Test
    public void testSetShow() {
        System.out.println("setShow");
        TextPrompt.Show show = null;
        TextPrompt instance = null;
        instance.setShow(show);
    }

    /**
     * Test of getShowPromptOnce method, of class TextPrompt.
     */
    @Test
    public void testGetShowPromptOnce() {
        System.out.println("getShowPromptOnce");
        TextPrompt instance = null;
        boolean expResult = false;
        boolean result = instance.getShowPromptOnce();
        assertEquals(expResult, result);
    }

    /**
     * Test of setShowPromptOnce method, of class TextPrompt.
     */
    @Test
    public void testSetShowPromptOnce() {
        System.out.println("setShowPromptOnce");
        boolean showPromptOnce = false;
        TextPrompt instance = null;
        instance.setShowPromptOnce(showPromptOnce);
    }

    /**
     * Test of focusGained method, of class TextPrompt.
     */
    @Test
    public void testFocusGained() {
        System.out.println("focusGained");
        FocusEvent e = null;
        TextPrompt instance = null;
        instance.focusGained(e);
    }

    /**
     * Test of focusLost method, of class TextPrompt.
     */
    @Test
    public void testFocusLost() {
        System.out.println("focusLost");
        FocusEvent e = null;
        TextPrompt instance = null;
        instance.focusLost(e);
    }

    /**
     * Test of insertUpdate method, of class TextPrompt.
     */
    @Test
    public void testInsertUpdate() {
        System.out.println("insertUpdate");
        DocumentEvent e = null;
        TextPrompt instance = null;
        instance.insertUpdate(e);
    }

    /**
     * Test of removeUpdate method, of class TextPrompt.
     */
    @Test
    public void testRemoveUpdate() {
        System.out.println("removeUpdate");
        DocumentEvent e = null;
        TextPrompt instance = null;
        instance.removeUpdate(e);
    }

    /**
     * Test of changedUpdate method, of class TextPrompt.
     */
    @Test
    public void testChangedUpdate() {
        System.out.println("changedUpdate");
        DocumentEvent e = null;
        TextPrompt instance = null;
        instance.changedUpdate(e);
    }
    
}
