package sur.softsurena.metodos;

import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.ContactoEmail;

/**
 *
 * @author jhironsel
 */
public class M_ContactosEmailNGTest {
    
    public M_ContactosEmailNGTest() {
    }

    @BeforeClass
    public void setUpClass() throws Exception {
        Conexion.getInstance(
                "sysdba",
                "1",
                "BaseDeDatos.db",
                "localhost",
                "3050"
        );
        assertTrue("Error al conectarse...", Conexion.verificar().getEstado());
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        Conexion.getCnn().close();
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
    public void testAgregarContactosEmail() {
        int id = 0;
        List<ContactoEmail> contactos = null;
        boolean expResult = false;
        boolean result = M_ContactoEmail.agregarContactosEmail(id, contactos);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testModificarContactosEmail() {
        int id = 0;
        List<ContactoEmail> contactos = null;
        boolean expResult = false;
        boolean result = M_ContactoEmail.modificarContactosEmail(id, contactos);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetCorreoByID() {
        int id = 0;
        List expResult = null;
        List result = M_ContactoEmail.getCorreoByID(id);
        assertEquals(result, expResult);
    }
    
}
