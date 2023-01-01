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
public class ValidarCorreoTelIT {
    
    public ValidarCorreoTelIT() {
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
     * Test of correo method, of class ValidarCorreoTel.
     */
    @Test
    public void testCorreo() {
        System.out.println("correo");
        String correo = "";
        boolean expResult = false;
        boolean result = ValidarCorreoTel.correo(correo);
        assertEquals(expResult, result);
    }

    /**
     * Test of telefono method, of class ValidarCorreoTel.
     */
    @Test
    public void testTelefono() {
        System.out.println("telefono");
        String tel = "";
        boolean expResult = false;
        boolean result = ValidarCorreoTel.telefono(tel);
        assertEquals(expResult, result);
    }

    /**
     * Test of cedula method, of class ValidarCorreoTel.
     */
    @Test
    public void testCedula() {
        System.out.println("cedula");
        String tel = "";
        boolean expResult = false;
        boolean result = ValidarCorreoTel.cedula(tel);
        assertEquals(expResult, result);
    }
    
}
