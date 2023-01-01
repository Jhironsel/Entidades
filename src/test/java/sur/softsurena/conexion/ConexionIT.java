package sur.softsurena.conexion;

import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author jhironsel
 */
public class ConexionIT {

    final static String USER = "jhironsel";
    final static String CLAVE = "123uasd";
    final static String ROLE = "RDB$ADMIN";
    final static String PATH = "/home/jhironsel/BaseDatos/BaseDeDatos3.fdb";
    final static String DOMINIO = "localhost";
    final static String PUERTO = "3050";
    static Conexion conexion;

    public ConexionIT() {
        conexion = Conexion.getInstance(USER, CLAVE, ROLE, PATH, DOMINIO, PUERTO);
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
     * Test of getCnn method, of class Conexion.
     */
    @Test
    public void testGetCnn() {
        System.out.println("getCnn");
        Connection expResult = null;
        Connection result = Conexion.getCnn();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of setCnn method, of class Conexion.
     */
    @Test
    public void testSetCnn() {
        System.out.println("setCnn");
        Connection cnn = conexion.getCnn();
        Conexion.setCnn(cnn);
    }

    /**
     * Test of getInstance method, of class Conexion.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");

        Conexion expResult = null;
        Conexion result = conexion;
        assertNotEquals(expResult, result);
    }

    @Test
    public void testVerificar() {
        System.out.println("verificar");
        Conexion instance = conexion;
        Boolean expResult = Boolean.FALSE;
        Boolean result = instance.verificar();
        assertEquals(expResult, result);
    }

}
