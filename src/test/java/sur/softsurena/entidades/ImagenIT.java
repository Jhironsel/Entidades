package sur.softsurena.entidades;

import javax.swing.ImageIcon;
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
public class ImagenIT {
    
    public ImagenIT() {
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
     * Test of getImagen method, of class Imagen.
     */
    @Test
    public void testGetImagen_String() {
        System.out.println("getImagen");
        String idUsuario = "";
        ImageIcon expResult = null;
        ImageIcon result = Imagen.getImagen(idUsuario);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Imagen.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Imagen instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Imagen.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Imagen.ImagenBuilder expResult = null;
        Imagen.ImagenBuilder result = Imagen.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImagen method, of class Imagen.
     */
    @Test
    public void testGetImagen_0args() {
        System.out.println("getImagen");
        Imagen instance = null;
        ImageIcon expResult = null;
        ImageIcon result = instance.getImagen();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNombre method, of class Imagen.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Imagen instance = null;
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }
    
}
