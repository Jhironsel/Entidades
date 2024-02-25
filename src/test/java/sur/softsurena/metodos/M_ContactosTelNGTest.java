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
import sur.softsurena.entidades.ContactoTel;

/**
 *
 * @author jhironsel
 */
public class M_ContactosTelNGTest {
    
    public M_ContactosTelNGTest() {
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
    public void testAgregarContactosTel() {
        int id_persona = 0;
        List<ContactoTel> contactos = null;
        boolean expResult = false;
        boolean result = M_ContactoTel.agregarContactosTel(id_persona, contactos);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testModificarContactosTel() {
        int id = 0;
        List<ContactoTel> contactos = null;
        boolean expResult = false;
        boolean result = M_ContactoTel.modificarContactosTel(id, contactos);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetTelefonoByID() {
        int id = 0;
        List expResult = null;
        List result = M_ContactoTel.getTelefonoByID(id);
        assertEquals(result, expResult);
    }
    
}
