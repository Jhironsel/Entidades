package sur.softsurena.metodos;

import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author jhironsel
 */
public class M_Distrito_MunicipalNGTest {

    public M_Distrito_MunicipalNGTest() {
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
    public void testGetDistritosMunicipales() {
        int id_municipio = 0;
        List expResult = null;
        List result = M_Distrito_Municipal.getDistritosMunicipales(id_municipio);
        assertEquals(result, expResult);
    }

}