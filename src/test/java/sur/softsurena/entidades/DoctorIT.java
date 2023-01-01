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
public class DoctorIT {
    
    public DoctorIT() {
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
     * Test of builder method, of class Doctor.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Doctor.DoctorBuilder expResult = null;
        Doctor.DoctorBuilder result = Doctor.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEspecialidad method, of class Doctor.
     */
    @Test
    public void testGetEspecialidad() {
        System.out.println("getEspecialidad");
        Doctor instance = null;
        String expResult = "";
        String result = instance.getEspecialidad();
        assertEquals(expResult, result);
    }

    /**
     * Test of getExecuatur method, of class Doctor.
     */
    @Test
    public void testGetExecuatur() {
        System.out.println("getExecuatur");
        Doctor instance = null;
        String expResult = "";
        String result = instance.getExecuatur();
        assertEquals(expResult, result);
    }
    
}
