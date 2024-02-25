package sur.softsurena.metodos;

import java.util.List;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.entidades.D_Receta;

/**
 *
 * @author jhironsel
 */
public class M_D_RecetaNGTest {

    public M_D_RecetaNGTest() {
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
    public void testAgregarRecetaDetalle() {
        System.out.println("agregarRecetaDetalle");
        List<D_Receta> dr = null;
        M_D_Receta.agregarRecetaDetalle(dr);
    }

}