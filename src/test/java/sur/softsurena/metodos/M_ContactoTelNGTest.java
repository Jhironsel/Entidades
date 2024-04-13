package sur.softsurena.metodos;

import java.util.List;
import lombok.Getter;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.ContactoTel;
import sur.softsurena.utilidades.Resultado;

/**
 *
 * @author jhironsel
 */
@Getter
public class M_ContactoTelNGTest {
    
    public M_ContactoTelNGTest() {
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
        assertTrue(
                Conexion.verificar().getEstado(),
                "Error al conectarse..."
        );
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
        Resultado result = M_ContactoTel.agregarContactosTel(
                ContactoTel.builder().build()
        );
        assertTrue(
                result.getEstado(), 
                "Error al agregar un contacto al sistema."
        );
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testModificarContactosTel() {
        Resultado result = M_ContactoTel.modificarContactoTel(
                ContactoTel
                        .builder()
                        .build()
        );
        assertTrue(
                result.getEstado(), 
                "Test de Modificar Contacto Tel. falló."
        );
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
