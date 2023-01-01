package sur.softsurena.utilidades;

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
public class ArchivoIT {
    
    public ArchivoIT() {
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
     * Test of crearArchivo method, of class Archivo.
     */
    @Test
    public void testCrearArchivo() {
        System.out.println("crearArchivo");
        String ruta = "";
        Boolean expResult = null;
        Boolean result = Archivo.crearArchivo(ruta);
        assertEquals(expResult, result);
    }

    /**
     * Test of escribirArchivo method, of class Archivo.
     */
    @Test
    public void testEscribirArchivo() {
        System.out.println("escribirArchivo");
        String ruta = "";
        String contenido = "";
        Boolean expResult = null;
        Boolean result = Archivo.escribirArchivo(ruta, contenido);
        assertEquals(expResult, result);
    }

    /**
     * Test of leerArchivo method, of class Archivo.
     */
    @Test
    public void testLeerArchivo() {
        System.out.println("leerArchivo");
        String nombreArchivo = "";
        Boolean expResult = null;
        Boolean result = Archivo.leerArchivo(nombreArchivo);
        assertEquals(expResult, result);
    }

    /**
     * Test of anexarArchivo method, of class Archivo.
     */
    @Test
    public void testAnexarArchivo() {
        System.out.println("anexarArchivo");
        String ruta = "";
        String anexo = "";
        Boolean expResult = null;
        Boolean result = Archivo.anexarArchivo(ruta, anexo);
        assertEquals(expResult, result);
    }
    
}
