package sur.softsurena.metodos;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.entidades.Mensaje;

/**
 *
 * @author jhironsel
 */
public class M_MensajeNGTest {

    public M_MensajeNGTest() {
    }

    @BeforeClass
    public void setUpClass() throws Exception {
    }

    @AfterClass
    public void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test(
            enabled = false, 
            priority = 0,
            description = ""
    )
    public void testExisteMensaje() {
        Mensaje mensaje = null;
        M_Mensaje instance = new M_Mensaje();
        boolean expResult = false;
        boolean result = instance.existeMensaje(mensaje);
        assertEquals(result, expResult);
    }

}