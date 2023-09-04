package sur.softsurena.conexion;

//import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;

/**
 *
 * @author jhironsel
 */
public class ConexionNGTest {

    private final String user = "jhironsel";
    private final String clave = "123uasd";
    private final String pathBaseDatos = "/home/jhironsel/BaseDatos/BaseDeDatos3.fdb";
    private final String dominio = "localhost";
    private final String puerto = "3050";

    public ConexionNGTest() {
        System.out.println("ConexionNGTest.<init>()");
        Conexion.getInstance(user, clave, pathBaseDatos, dominio, puerto);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("setUpClass()");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("tearDownClass()");
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("setUpMethod()");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("tearDownMethod()");
    }

//    /**
//     * Test of verificar method, of class Conexion.
//     */
//    @Test
//    public void testVerificar() {
//        System.out.println("verificar");
//        
//        assertEquals(Boolean.TRUE, Conexion.verificar());
//        
//        assertEquals(Conexion.USUARIO_LOGEADO, Conexion.alerta.getContentText());
//    }
}
