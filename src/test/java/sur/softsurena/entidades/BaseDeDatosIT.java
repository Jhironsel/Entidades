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
public class BaseDeDatosIT {
    
    public BaseDeDatosIT() {
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
     * Test of pathBaseDeDatos method, of class BaseDeDatos.
     */
    @Test
    public void testPathBaseDeDatos() {
        System.out.println("pathBaseDeDatos");
        String expResult = "";
        String result = BaseDeDatos.pathBaseDeDatos();
        assertEquals(expResult, result);
    }

    /**
     * Test of metaBaseDatos method, of class BaseDeDatos.
     */
    @Test
    public void testMetaBaseDatos() {
        System.out.println("metaBaseDatos");
        ResultSet expResult = null;
        ResultSet result = BaseDeDatos.metaBaseDatos();
        assertEquals(expResult, result);
    }

    /**
     * Test of periodoMaquina method, of class BaseDeDatos.
     */
    @Test
    public void testPeriodoMaquina() {
        System.out.println("periodoMaquina");
        int expResult = 0;
        int result = BaseDeDatos.periodoMaquina();
        assertEquals(expResult, result);
    }

    /**
     * Test of existeIdMaquina method, of class BaseDeDatos.
     */
    @Test
    public void testExisteIdMaquina() {
        System.out.println("existeIdMaquina");
        String idMaquina = "";
        boolean expResult = false;
        boolean result = BaseDeDatos.existeIdMaquina(idMaquina);
        assertEquals(expResult, result);
    }

    /**
     * Test of setLicencia method, of class BaseDeDatos.
     */
    @Test
    public void testSetLicencia() {
        System.out.println("setLicencia");
        Date fecha = null;
        String idMaquina = "";
        String clave1 = "";
        String clave2 = "";
        boolean expResult = false;
        boolean result = BaseDeDatos.setLicencia(fecha, idMaquina, clave1, clave2);
        assertEquals(expResult, result);
    }

    /**
     * Test of cantidadRegistros method, of class BaseDeDatos.
     */
    @Test
    public void testCantidadRegistros() {
        System.out.println("cantidadRegistros");
        String tabla = "";
        int expResult = 0;
        int result = BaseDeDatos.cantidadRegistros(tabla);
        assertEquals(expResult, result);
    }
    
}
