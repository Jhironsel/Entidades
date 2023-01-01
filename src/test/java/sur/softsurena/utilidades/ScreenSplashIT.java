package sur.softsurena.utilidades;

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
public class ScreenSplashIT {
    
    public ScreenSplashIT() {
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
     * Test of animar method, of class ScreenSplash.
     */
    @Test
    public void testAnimar() {
        System.out.println("animar");
        ScreenSplash instance = new ScreenSplash();
        instance.animar();
    }

    /**
     * Test of animar2 method, of class ScreenSplash.
     */
    @Test
    public void testAnimar2() {
        System.out.println("animar2");
        ScreenSplash instance = new ScreenSplash();
        instance.animar2();
    }
    
}
