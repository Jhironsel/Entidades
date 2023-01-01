package sur.softsurena.entidades;

import java.sql.ResultSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jhironsel
 */
public class PadresIT {
    
    public PadresIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPadresRecuperar method, of class Padres.
     */
    @Test
    public void testGetPadresRecuperar() {
        System.out.println("getPadresRecuperar");
        String cedula = "";
        ResultSet expResult = null;
        ResultSet result = Padres.getPadresRecuperar(cedula);
        assertEquals(expResult, result);
    }

    /**
     * Test of agregarPadreMadre method, of class Padres.
     */
    @Test
    public void testAgregarPadreMadre() {
        System.out.println("agregarPadreMadre");
        Padres p = null;
        Resultados expResult = null;
        Resultados result = Padres.agregarPadreMadre(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of modificarPadre method, of class Padres.
     */
    @Test
    public void testModificarPadre() {
        System.out.println("modificarPadre");
        Padres p = null;
        String expResult = "";
        String result = Padres.modificarPadre(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of borrarPadre method, of class Padres.
     */
    @Test
    public void testBorrarPadre() {
        System.out.println("borrarPadre");
        String cedula = "";
        String expResult = "";
        String result = Padres.borrarPadre(cedula);
        assertEquals(expResult, result);
    }

    /**
     * Test of numeroPadres method, of class Padres.
     */
    @Test
    public void testNumeroPadres() {
        System.out.println("numeroPadres");
        boolean estado = false;
        int expResult = 0;
        int result = Padres.numeroPadres(estado);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPadreMadres method, of class Padres.
     */
    @Test
    public void testGetPadreMadres_int() {
        System.out.println("getPadreMadres");
        int idPadre = 0;
        ResultSet expResult = null;
        ResultSet result = Padres.getPadreMadres(idPadre);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPadreMadres method, of class Padres.
     */
    @Test
    public void testGetPadreMadres_0args() {
        System.out.println("getPadreMadres");
        ResultSet expResult = null;
        ResultSet result = Padres.getPadreMadres();
        assertEquals(expResult, result);
    }

    /**
     * Test of validarPadreMadre method, of class Padres.
     */
    @Test
    public void testValidarPadreMadre() {
        System.out.println("validarPadreMadre");
        String cedula = "";
        boolean expResult = false;
        boolean result = Padres.validarPadreMadre(cedula);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPadresActivoID method, of class Padres.
     */
    @Test
    public void testGetPadresActivoID() {
        System.out.println("getPadresActivoID");
        int idPadre = 0;
        ResultSet expResult = null;
        ResultSet result = Padres.getPadresActivoID(idPadre);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPadresActivo method, of class Padres.
     */
    @Test
    public void testGetPadresActivo_String_String() {
        System.out.println("getPadresActivo");
        String cedula = "";
        String sexo = "";
        ResultSet expResult = null;
        ResultSet result = Padres.getPadresActivo(cedula, sexo);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPadresActivo method, of class Padres.
     */
    @Test
    public void testGetPadresActivo_boolean() {
        System.out.println("getPadresActivo");
        boolean estado = false;
        ResultSet expResult = null;
        ResultSet result = Padres.getPadresActivo(estado);
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdMadrePadre method, of class Padres.
     */
    @Test
    public void testGetIdMadrePadre() {
        System.out.println("getIdMadrePadre");
        String cedula = "";
        Padres instance = null;
        int expResult = 0;
        int result = instance.getIdMadrePadre(cedula);
        assertEquals(expResult, result);
    }

    /**
     * Test of existePadre method, of class Padres.
     */
    @Test
    public void testExistePadre() {
        System.out.println("existePadre");
        String cedula = "";
        boolean estado = false;
        boolean expResult = false;
        boolean result = Padres.existePadre(cedula, estado);
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Padres.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Padres.PadresBuilder expResult = null;
        Padres.PadresBuilder result = Padres.builder();
        assertEquals(expResult, result);
    }
    
}
