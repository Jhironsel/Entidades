package sur.softsurena.jfilechooser;

import java.io.File;
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
public class ImageFilterIT {
    
    public ImageFilterIT() {
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
     * Test of accept method, of class ImageFilter.
     */
    @Test
    public void testAccept() {
        System.out.println("accept");
        File f = null;
        ImageFilter instance = new ImageFilter();
        boolean expResult = false;
        boolean result = instance.accept(f);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class ImageFilter.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        ImageFilter instance = new ImageFilter();
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }
    
}
