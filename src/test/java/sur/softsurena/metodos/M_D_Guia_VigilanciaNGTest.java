package sur.softsurena.metodos;

import java.sql.ResultSet;
import lombok.Getter;
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
@Getter
public class M_D_Guia_VigilanciaNGTest {

    public M_D_Guia_VigilanciaNGTest() {
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
    public void testAgregarGuiaVigilancia() {
        int idGVD = 0;
        int idPaciente = 0;
        String expResult = "";
        String result = M_D_Guia_Vigilancia.agregarGuiaVigilancia(idGVD, idPaciente);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetGuiaDesarrollo() {
        int idPaciente = 0;
        boolean cero = false;
        ResultSet expResult = null;
        ResultSet result = M_D_Guia_Vigilancia.getGuiaDesarrollo(idPaciente, cero);
        assertEquals(result, expResult);
    }

}