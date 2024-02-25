package sur.softsurena.metodos;

import java.util.List;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;

public class M_MunicipioNGTest {
    
    public M_MunicipioNGTest() {
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
            enabled = true,
            description = "",
            priority = 0
    )
    public void testGetMunicipio() {
        int id_provincia = 0;
        List result = M_Municipio.getMunicipio(id_provincia);
        assertNotNull(result, "La tabla de municipio esta vacia");
        id_provincia = 1;
        result = M_Municipio.getMunicipio(id_provincia);
        assertNotNull(result, "La tabla de municipio esta vacia");
    }
    
}
