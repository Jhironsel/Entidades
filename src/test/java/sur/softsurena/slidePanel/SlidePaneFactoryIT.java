package sur.softsurena.slidePanel;

import java.awt.Image;
import javax.swing.JComponent;
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
public class SlidePaneFactoryIT {
    
    public SlidePaneFactoryIT() {
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
     * Test of getInstance method, of class SlidePaneFactory.
     */
    @Test
    public void testGetInstance_boolean() {
        System.out.println("getInstance");
        boolean isGroup = false;
        SlidePaneFactory expResult = null;
        SlidePaneFactory result = SlidePaneFactory.getInstance(isGroup);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInstance method, of class SlidePaneFactory.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        SlidePaneFactory expResult = null;
        SlidePaneFactory result = SlidePaneFactory.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class SlidePaneFactory.
     */
    @Test
    public void testAdd_JComponent() {
        System.out.println("add");
        JComponent slideComponent = null;
        SlidePaneFactory instance = null;
        instance.add(slideComponent);
    }

    /**
     * Test of add method, of class SlidePaneFactory.
     */
    @Test
    public void testAdd_JComponent_String() {
        System.out.println("add");
        JComponent slideComponent = null;
        String title = "";
        SlidePaneFactory instance = null;
        instance.add(slideComponent, title);
    }

    /**
     * Test of add method, of class SlidePaneFactory.
     */
    @Test
    public void testAdd_3args() {
        System.out.println("add");
        JComponent slideComponent = null;
        String title = "";
        Image imageIcon = null;
        SlidePaneFactory instance = null;
        instance.add(slideComponent, title, imageIcon);
    }

    /**
     * Test of add method, of class SlidePaneFactory.
     */
    @Test
    public void testAdd_4args() {
        System.out.println("add");
        JComponent slideComponent = null;
        String title = "";
        Image imageIcon = null;
        boolean isExpand = false;
        SlidePaneFactory instance = null;
        instance.add(slideComponent, title, imageIcon, isExpand);
    }
    
}
