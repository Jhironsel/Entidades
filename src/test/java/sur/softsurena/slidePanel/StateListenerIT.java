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
public class StateListenerIT {
    
    public StateListenerIT() {
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
     * Test of toggleState method, of class StateListener.
     */
    @Test
    public void testToggleState() {
        System.out.println("toggleState");
        StateListener instance = new StateListenerImpl();
        instance.toggleState();
    }

    /**
     * Test of reset method, of class StateListener.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        StateListener instance = new StateListenerImpl();
        instance.reset();
    }

    public class StateListenerImpl implements StateListener {

        public void toggleState() {
        }

        public void reset() {
        }
    }
    
}
