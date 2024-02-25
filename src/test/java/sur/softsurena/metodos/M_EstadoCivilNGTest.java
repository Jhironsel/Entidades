package sur.softsurena.metodos;

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
public class M_EstadoCivilNGTest {

    public M_EstadoCivilNGTest() {
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
            enabled = true,
            priority = 0,
            description = "Verifica que los estados civiles de las personas esten bien definidos en el sistema."
    )
    public void testGetEstadoCivilList() {
        assertFalse(
                M_EstadoCivil.getEstadoCivilList().isEmpty(), 
                "La lista de estado civil está vacia."
        );
    }

}