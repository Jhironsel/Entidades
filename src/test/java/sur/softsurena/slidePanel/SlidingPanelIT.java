package sur.softsurena.slidePanel;

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
public class SlidingPanelIT {
    
    public SlidingPanelIT() {
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
     * Test of reset method, of class SlidingPanel.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        SlidingPanel instance = null;
        instance.reset();
    }

    /**
     * Test of toggleState method, of class SlidingPanel.
     */
    @Test
    public void testToggleState() {
        System.out.println("toggleState");
        SlidingPanel instance = null;
        instance.toggleState();
    }
    
}
