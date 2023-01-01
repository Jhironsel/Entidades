package sur.softsurena.entidades;

import java.awt.Graphics;
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
public class DesktopConFondoIT {
    
    public DesktopConFondoIT() {
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
     * Test of setImagen method, of class DesktopConFondo.
     */
    @Test
    public void testSetImagen() {
        System.out.println("setImagen");
        String nombreImagen = "";
        DesktopConFondo instance = new DesktopConFondo();
        instance.setImagen(nombreImagen);
    }

    /**
     * Test of paint method, of class DesktopConFondo.
     */
    @Test
    public void testPaint() {
        System.out.println("paint");
        Graphics g = null;
        DesktopConFondo instance = new DesktopConFondo();
        instance.paint(g);
    }
    
}
