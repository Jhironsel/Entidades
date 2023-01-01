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
public class SexoIT {
    
    public SexoIT() {
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
     * Test of toString method, of class Sexo.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Sexo instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Sexo.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Sexo.SexoBuilder expResult = null;
        Sexo.SexoBuilder result = Sexo.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAbreviatura method, of class Sexo.
     */
    @Test
    public void testGetAbreviatura() {
        System.out.println("getAbreviatura");
        Sexo instance = null;
        char expResult = ' ';
        char result = instance.getAbreviatura();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSexo method, of class Sexo.
     */
    @Test
    public void testGetSexo() {
        System.out.println("getSexo");
        Sexo instance = null;
        String expResult = "";
        String result = instance.getSexo();
        assertEquals(expResult, result);
    }
    
}
