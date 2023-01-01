package sur.softsurena.jfilechooser;

import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
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
public class ImagePreviewIT {
    
    public ImagePreviewIT() {
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
     * Test of loadImage method, of class ImagePreview.
     */
    @Test
    public void testLoadImage() {
        System.out.println("loadImage");
        ImagePreview instance = null;
        instance.loadImage();
    }

    /**
     * Test of propertyChange method, of class ImagePreview.
     */
    @Test
    public void testPropertyChange() {
        System.out.println("propertyChange");
        PropertyChangeEvent e = null;
        ImagePreview instance = null;
        instance.propertyChange(e);
    }

    /**
     * Test of paint method, of class ImagePreview.
     */
    @Test
    public void testPaint() {
        System.out.println("paint");
        Graphics g = null;
        ImagePreview instance = null;
        instance.paint(g);
    }
    
}
