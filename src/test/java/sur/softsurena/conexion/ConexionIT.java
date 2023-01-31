package sur.softsurena.conexion;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sur.softsurena.conexion.Conexion;

public class ConexionIT {
    
    final String user = "jhironsel";
    final String clave = "123uasd";
    final String role = "None";
    final String pathBaseDatos = "/home/jhironsel/BaseDatos/BaseDeDatos3.fdb";
    final String dominio = "localhost";
    final String puerto = "3050"; 
    final Conexion conexion;
    
    public ConexionIT() {
        conexion = Conexion.getInstance(user, clave, role, pathBaseDatos, dominio, puerto);
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
     * Test of getInstance method, of class Conexion.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        assertNotNull(conexion);
    }
    
    /**
     * Test of verificar method, of class Conexion.
     */
    @Test
    public void testVerificar() {
        System.out.println("verificar");
        
        Boolean expResult = Boolean.TRUE;
        Boolean result = conexion.verificar();
        assertEquals(expResult, result);
    }
    
}
