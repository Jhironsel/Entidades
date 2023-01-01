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
public class EstadoCivilIT {
    
    public EstadoCivilIT() {
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
     * Test of toString method, of class EstadoCivil.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        EstadoCivil instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class EstadoCivil.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        EstadoCivil.EstadoCivilBuilder expResult = null;
        EstadoCivil.EstadoCivilBuilder result = EstadoCivil.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAbreviatura method, of class EstadoCivil.
     */
    @Test
    public void testGetAbreviatura() {
        System.out.println("getAbreviatura");
        EstadoCivil instance = null;
        char expResult = ' ';
        char result = instance.getAbreviatura();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEstadoCivil method, of class EstadoCivil.
     */
    @Test
    public void testGetEstadoCivil() {
        System.out.println("getEstadoCivil");
        EstadoCivil instance = null;
        String expResult = "";
        String result = instance.getEstadoCivil();
        assertEquals(expResult, result);
    }
    
}
