package sur.softsurena.entidades;

import java.sql.Date;
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
public class AntecedentesIT {
    
    public AntecedentesIT() {
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
     * Test of borrarAntecedente method, of class Antecedentes.
     */
    @Test
    public void testBorrarAntecedente() {
        System.out.println("borrarAntecedente");
        int idAntecedente = 0;
        String expResult = "";
        String result = Antecedentes.borrarAntecedente(idAntecedente);
        assertEquals(expResult, result);
    }

    /**
     * Test of agregarAntecedente method, of class Antecedentes.
     */
    @Test
    public void testAgregarAntecedente() {
        System.out.println("agregarAntecedente");
        int id = 0;
        String antecedente = "";
        String expResult = "";
        String result = Antecedentes.agregarAntecedente(id, antecedente);
        assertEquals(expResult, result);
    }

    /**
     * Test of modificarAntecedente method, of class Antecedentes.
     */
    @Test
    public void testModificarAntecedente() {
        System.out.println("modificarAntecedente");
        int idAntecedente = 0;
        String descrpcion = "";
        Antecedentes instance = null;
        String expResult = "";
        String result = instance.modificarAntecedente(idAntecedente, descrpcion);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAntecedentes method, of class Antecedentes.
     */
    @Test
    public void testGetAntecedentes() {
        System.out.println("getAntecedentes");
        int idPadre = 0;
        ResultSet expResult = null;
        ResultSet result = Antecedentes.getAntecedentes(idPadre);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Antecedentes.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Antecedentes instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Antecedentes.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Antecedentes.AntecedentesBuilder expResult = null;
        Antecedentes.AntecedentesBuilder result = Antecedentes.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Antecedentes.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Antecedentes instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdDoctor method, of class Antecedentes.
     */
    @Test
    public void testGetIdDoctor() {
        System.out.println("getIdDoctor");
        Antecedentes instance = null;
        int expResult = 0;
        int result = instance.getIdDoctor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFecha method, of class Antecedentes.
     */
    @Test
    public void testGetFecha() {
        System.out.println("getFecha");
        Antecedentes instance = null;
        Date expResult = null;
        Date result = instance.getFecha();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescripcion method, of class Antecedentes.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        Antecedentes instance = null;
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserName method, of class Antecedentes.
     */
    @Test
    public void testGetUserName() {
        System.out.println("getUserName");
        Antecedentes instance = null;
        String expResult = "";
        String result = instance.getUserName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRol method, of class Antecedentes.
     */
    @Test
    public void testGetRol() {
        System.out.println("getRol");
        Antecedentes instance = null;
        String expResult = "";
        String result = instance.getRol();
        assertEquals(expResult, result);
    }
    
}
