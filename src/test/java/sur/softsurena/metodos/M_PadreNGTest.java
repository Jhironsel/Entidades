package sur.softsurena.metodos;

import java.sql.ResultSet;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.entidades.Padres;
import sur.softsurena.utilidades.Resultado;

/**
 *
 * @author jhironsel
 */
public class M_PadreNGTest {

    public M_PadreNGTest() {
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
    public void testGetPadresRecuperar() {
        String cedula = "";
        ResultSet expResult = null;
        ResultSet result = M_Padre.getPadresRecuperar(cedula);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testAgregarPadreMadre() {
        Padres p = null;
        Resultado expResult = null;
        Resultado result = M_Padre.agregarPadreMadre(p);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testModificarPadre() {
        Padres p = null;
        String expResult = "";
        String result = M_Padre.modificarPadre(p);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testBorrarPadre() {
        String cedula = "";
        String expResult = "";
        String result = M_Padre.borrarPadre(cedula);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testNumeroPadres() {
        boolean estado = false;
        int expResult = 0;
        int result = M_Padre.numeroPadres(estado);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetPadreMadres_int() {
        int idPadre = 0;
        ResultSet expResult = null;
        ResultSet result = M_Padre.getPadreMadres(idPadre);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetPadreMadres_0args() {
        ResultSet expResult = null;
        ResultSet result = M_Padre.getPadreMadres();
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testValidarPadreMadre() {
        String cedula = "";
        boolean expResult = false;
        boolean result = M_Padre.validarPadreMadre(cedula);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetPadresActivoID() {
        int idPadre = 0;
        ResultSet expResult = null;
        ResultSet result = M_Padre.getPadresActivoID(idPadre);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetPadresActivo_String_String() {
        String cedula = "";
        String sexo = "";
        ResultSet expResult = null;
        ResultSet result = M_Padre.getPadresActivo(cedula, sexo);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetPadresActivo_boolean() {
        boolean estado = false;
        ResultSet expResult = null;
        ResultSet result = M_Padre.getPadresActivo(estado);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGetIdMadrePadre() {
        String cedula = "";
        M_Padre instance = new M_Padre();
        int expResult = 0;
        int result = instance.getIdMadrePadre(cedula);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testExistePadre() {
        String cedula = "";
        boolean estado = false;
        boolean expResult = false;
        boolean result = M_Padre.existePadre(cedula, estado);
        assertEquals(result, expResult);
    }
}