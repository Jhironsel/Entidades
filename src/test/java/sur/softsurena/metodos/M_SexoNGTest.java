package sur.softsurena.metodos;

import lombok.Getter;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Clase que realiza los test de los sexo de las personas. Comprueba que los 
 * sexo estan definidos en el sistema. 
 * 
 * @author jhironsel
 */
@Getter
public class M_SexoNGTest {

    public M_SexoNGTest() {
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
            description = "Test que verifica la lista de sexo de los componente JComboBox."
    )
    public void testGetSexoList() {
        assertFalse(
                M_Sexo.getSexoList().isEmpty(), 
                "La lista de Sexo no cargo correctamente."
        );
    }

}