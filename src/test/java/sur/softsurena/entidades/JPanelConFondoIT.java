package sur.softsurena.entidades;

import java.awt.Graphics;
import java.awt.Image;
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
public class JPanelConFondoIT {
    
    public JPanelConFondoIT() {
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
     * Test of setImagen method, of class JPanelConFondo.
     */
    @Test
    public void testSetImagen_String() {
        System.out.println("setImagen");
        String nombreImagen = "";
        JPanelConFondo instance = new JPanelConFondo();
        instance.setImagen(nombreImagen);
    }

    /**
     * Test of setImagen method, of class JPanelConFondo.
     */
    @Test
    public void testSetImagen_Image() {
        System.out.println("setImagen");
        Image nuevaImagen = null;
        JPanelConFondo instance = new JPanelConFondo();
        instance.setImagen(nuevaImagen);
    }

    /**
     * Test of paint method, of class JPanelConFondo.
     */
    @Test
    public void testPaint() {
        System.out.println("paint");
        Graphics g = null;
        JPanelConFondo instance = new JPanelConFondo();
        instance.paint(g);
    }
    
}
