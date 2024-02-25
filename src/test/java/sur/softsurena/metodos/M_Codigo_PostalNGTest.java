package sur.softsurena.metodos;

import java.util.List;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author jhironsel
 */
public class M_Codigo_PostalNGTest {
    
    public M_Codigo_PostalNGTest() {
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

    /**
     * Test of getCodigoPostal method, of class M_Codigo_Postal.
     */
    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetCodigoPostal() {
        int id_provincia = 0;
        List expResult = null;
        List result = M_Codigo_Postal.getCodigoPostal(id_provincia);
        assertEquals(result, expResult);
    }
    
}
