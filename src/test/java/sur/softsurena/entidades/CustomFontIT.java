package sur.softsurena.entidades;

import java.awt.Font;
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
public class CustomFontIT {
    
    public CustomFontIT() {
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
     * Test of MyFont method, of class CustomFont.
     */
    @Test
    public void testMyFont() {
        System.out.println("MyFont");
        int estilo = 0;
        float tamanio = 0.0F;
        CustomFont instance = null;
        Font expResult = null;
        Font result = instance.MyFont(estilo, tamanio);
        assertEquals(expResult, result);
    }
    
}
