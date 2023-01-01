package sur.softsurena.entidades;

import java.sql.ResultSet;
import java.sql.Timestamp;
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
public class D_guia_vigilanciaIT {
    
    public D_guia_vigilanciaIT() {
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
     * Test of agregarGuiaVigilancia method, of class D_guia_vigilancia.
     */
    @Test
    public void testAgregarGuiaVigilancia() {
        System.out.println("agregarGuiaVigilancia");
        int idGVD = 0;
        int idPaciente = 0;
        String expResult = "";
        String result = D_guia_vigilancia.agregarGuiaVigilancia(idGVD, idPaciente);
        assertEquals(expResult, result);
    }

    /**
     * Test of getGuiaDesarrollo method, of class D_guia_vigilancia.
     */
    @Test
    public void testGetGuiaDesarrollo() {
        System.out.println("getGuiaDesarrollo");
        int idPaciente = 0;
        boolean cero = false;
        ResultSet expResult = null;
        ResultSet result = D_guia_vigilancia.getGuiaDesarrollo(idPaciente, cero);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class D_guia_vigilancia.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        D_guia_vigilancia instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class D_guia_vigilancia.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        D_guia_vigilancia.D_guia_vigilanciaBuilder expResult = null;
        D_guia_vigilancia.D_guia_vigilanciaBuilder result = D_guia_vigilancia.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class D_guia_vigilancia.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        D_guia_vigilancia instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_paciente method, of class D_guia_vigilancia.
     */
    @Test
    public void testGetId_paciente() {
        System.out.println("getId_paciente");
        D_guia_vigilancia instance = null;
        int expResult = 0;
        int result = instance.getId_paciente();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFecha method, of class D_guia_vigilancia.
     */
    @Test
    public void testGetFecha() {
        System.out.println("getFecha");
        D_guia_vigilancia instance = null;
        Timestamp expResult = null;
        Timestamp result = instance.getFecha();
        assertEquals(expResult, result);
    }
    
}
