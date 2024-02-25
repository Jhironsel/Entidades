package sur.softsurena.metodos;

import java.sql.Date;
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
public class M_BaseDeDatosNGTest {

    public M_BaseDeDatosNGTest() {
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
    public void testPathBaseDeDatos() {
        String expResult = "";
        String result = M_BaseDeDatos.pathBaseDeDatos();
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testPeriodoMaquina() {
        int expResult = 0;
        int result = M_BaseDeDatos.periodoMaquina();
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testSetLicencia() {
        Date fecha = null;
        String idMaquina = "";
        String clave1 = "";
        String clave2 = "";
        boolean expResult = false;
        boolean result = M_BaseDeDatos.setLicencia(fecha, idMaquina, clave1, clave2);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testCantidadRegistros() {
        String tabla = "";
        int expResult = 0;
        int result = M_BaseDeDatos.cantidadRegistros(tabla);
        assertEquals(result, expResult);
    }

}