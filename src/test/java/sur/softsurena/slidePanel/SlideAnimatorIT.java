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
public class SlideAnimatorIT {
    
    public SlideAnimatorIT() {
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
     * Test of Dispose method, of class SlideAnimator.
     */
    @Test
    public void testDispose() {
        System.out.println("Dispose");
        SlideAnimator instance = new SlideAnimator();
        instance.Dispose();
    }

    /**
     * Test of initDesktopBounds method, of class SlideAnimator.
     */
    @Test
    public void testInitDesktopBounds() {
        System.out.println("initDesktopBounds");
        SlideAnimator instance = new SlideAnimator();
        instance.initDesktopBounds();
    }

    /**
     * Test of setContents method, of class SlideAnimator.
     */
    @Test
    public void testSetContents() {
        System.out.println("setContents");
        SlideAnimator instance = new SlideAnimator();
        instance.setContents();
    }

    /**
     * Test of showAt method, of class SlideAnimator.
     */
    @Test
    public void testShowAt() {
        System.out.println("showAt");
        SlideAnimator instance = new SlideAnimator();
        instance.showAt();
    }
    
}
