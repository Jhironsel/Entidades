package sur.softsurena.metodos;

import java.sql.ResultSet;
import java.util.List;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.entidades.Tanda;
import sur.softsurena.utilidades.Resultado;

public class M_TandaNGTest {
    
    public M_TandaNGTest() {
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
            description = "",
            priority = 0
    )
    public void testAgregarTanda() {
        Tanda t = null;
        String expResult = "";
        Resultado result = M_Tanda.agregarTanda(t);
        assertEquals(result, expResult);
    }

    @Test(            
            enabled = false,
            description = "",
            priority = 0
    )
    public void testGetTandas_Integer_Integer() {
        Integer edadMin = null;
        Integer edadMax = null;
        ResultSet expResult = null;
        List<Tanda> result = M_Tanda.getTandas(edadMin, edadMax);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testModificarTanda() {
        Tanda t = null;
        String expResult = "";
        Resultado result = M_Tanda.modificarTanda(t);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testGetTandas_Integer() {
        Integer id_Tanda = null;
        ResultSet expResult = null;
        ResultSet result = M_Tanda.getTandas(id_Tanda);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testGetHorario() {
        ResultSet expResult = null;
        ResultSet result = M_Tanda.getHorario();
        assertEquals(result, expResult);
    }
    
}
