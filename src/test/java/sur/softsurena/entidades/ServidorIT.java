package sur.softsurena.entidades;

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
public class ServidorIT {
    
    public ServidorIT() {
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
     * Test of builder method, of class Servidor.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Servidor.ServidorBuilder expResult = null;
        Servidor.ServidorBuilder result = Servidor.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getConServidor method, of class Servidor.
     */
    @Test
    public void testGetConServidor() {
        System.out.println("getConServidor");
        Servidor instance = null;
        Boolean expResult = null;
        Boolean result = instance.getConServidor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUriServidor method, of class Servidor.
     */
    @Test
    public void testGetUriServidor() {
        System.out.println("getUriServidor");
        Servidor instance = null;
        String expResult = "";
        String result = instance.getUriServidor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getConPuerto method, of class Servidor.
     */
    @Test
    public void testGetConPuerto() {
        System.out.println("getConPuerto");
        Servidor instance = null;
        Boolean expResult = null;
        Boolean result = instance.getConPuerto();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPuerto method, of class Servidor.
     */
    @Test
    public void testGetPuerto() {
        System.out.println("getPuerto");
        Servidor instance = null;
        String expResult = "";
        String result = instance.getPuerto();
        assertEquals(expResult, result);
    }

    /**
     * Test of getConIpServidor method, of class Servidor.
     */
    @Test
    public void testGetConIpServidor() {
        System.out.println("getConIpServidor");
        Servidor instance = null;
        Boolean expResult = null;
        Boolean result = instance.getConIpServidor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIpServidor1 method, of class Servidor.
     */
    @Test
    public void testGetIpServidor1() {
        System.out.println("getIpServidor1");
        Servidor instance = null;
        String expResult = "";
        String result = instance.getIpServidor1();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIpServidor2 method, of class Servidor.
     */
    @Test
    public void testGetIpServidor2() {
        System.out.println("getIpServidor2");
        Servidor instance = null;
        String expResult = "";
        String result = instance.getIpServidor2();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIpServidor3 method, of class Servidor.
     */
    @Test
    public void testGetIpServidor3() {
        System.out.println("getIpServidor3");
        Servidor instance = null;
        String expResult = "";
        String result = instance.getIpServidor3();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIpServidor4 method, of class Servidor.
     */
    @Test
    public void testGetIpServidor4() {
        System.out.println("getIpServidor4");
        Servidor instance = null;
        String expResult = "";
        String result = instance.getIpServidor4();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPathBaseDatos method, of class Servidor.
     */
    @Test
    public void testGetPathBaseDatos() {
        System.out.println("getPathBaseDatos");
        Servidor instance = null;
        String expResult = "";
        String result = instance.getPathBaseDatos();
        assertEquals(expResult, result);
    }
    
}
