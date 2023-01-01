package sur.softsurena.jfilechooser;

import java.io.File;
import javax.swing.Icon;
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
public class ImageFileViewIT {
    
    public ImageFileViewIT() {
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
     * Test of getName method, of class ImageFileView.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        File f = null;
        ImageFileView instance = new ImageFileView();
        String expResult = "";
        String result = instance.getName(f);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class ImageFileView.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        File f = null;
        ImageFileView instance = new ImageFileView();
        String expResult = "";
        String result = instance.getDescription(f);
        assertEquals(expResult, result);
    }

    /**
     * Test of isTraversable method, of class ImageFileView.
     */
    @Test
    public void testIsTraversable() {
        System.out.println("isTraversable");
        File f = null;
        ImageFileView instance = new ImageFileView();
        Boolean expResult = null;
        Boolean result = instance.isTraversable(f);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTypeDescription method, of class ImageFileView.
     */
    @Test
    public void testGetTypeDescription() {
        System.out.println("getTypeDescription");
        File f = null;
        ImageFileView instance = new ImageFileView();
        String expResult = "";
        String result = instance.getTypeDescription(f);
        assertEquals(expResult, result);
    }

    /**
     * Test of getIcon method, of class ImageFileView.
     */
    @Test
    public void testGetIcon() {
        System.out.println("getIcon");
        File f = null;
        ImageFileView instance = new ImageFileView();
        Icon expResult = null;
        Icon result = instance.getIcon(f);
        assertEquals(expResult, result);
    }
    
}
