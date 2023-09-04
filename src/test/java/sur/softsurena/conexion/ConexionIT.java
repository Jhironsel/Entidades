package sur.softsurena.conexion;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ConexionIT {
    
    final String user = "jhironsel";
    final String clave = "123uasd";
    final String pathBaseDatos = "/home/jhironsel/BaseDatos/BaseDeDatos3.fdb";
    final String dominio = "localhost";
    final String puerto = "3050"; 
    final Conexion conexion;
    
    public ConexionIT() {
        conexion = Conexion.getInstance(user, clave, pathBaseDatos, dominio, puerto);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
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
