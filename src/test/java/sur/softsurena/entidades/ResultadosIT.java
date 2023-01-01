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
public class ResultadosIT {
    
    public ResultadosIT() {
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
     * Test of builder method, of class Resultados.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Resultados.ResultadosBuilder expResult = null;
        Resultados.ResultadosBuilder result = Resultados.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Resultados.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Resultados instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMensaje method, of class Resultados.
     */
    @Test
    public void testGetMensaje() {
        System.out.println("getMensaje");
        Resultados instance = null;
        String expResult = "";
        String result = instance.getMensaje();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCantidad method, of class Resultados.
     */
    @Test
    public void testGetCantidad() {
        System.out.println("getCantidad");
        Resultados instance = null;
        Integer expResult = null;
        Integer result = instance.getCantidad();
        assertEquals(expResult, result);
    }
    
}
