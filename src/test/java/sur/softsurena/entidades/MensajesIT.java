package sur.softsurena.entidades;

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
public class MensajesIT {
    
    public MensajesIT() {
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
     * Test of existeMensaje method, of class Mensajes.
     */
    @Test
    public void testExisteMensaje() {
        System.out.println("existeMensaje");
        String idUsuario = "";
        Mensajes instance = new Mensajes();
        boolean expResult = false;
        boolean result = instance.existeMensaje(idUsuario);
        assertEquals(expResult, result);
    }
    
}
