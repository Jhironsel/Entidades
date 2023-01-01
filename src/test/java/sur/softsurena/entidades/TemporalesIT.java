package sur.softsurena.entidades;

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
public class TemporalesIT {
    
    public TemporalesIT() {
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
     * Test of getTemporales method, of class Temporales.
     */
    @Test
    public void testGetTemporales() {
        System.out.println("getTemporales");
        ResultSet expResult = null;
        ResultSet result = Temporales.getTemporales();
        assertEquals(expResult, result);
    }
    
}
