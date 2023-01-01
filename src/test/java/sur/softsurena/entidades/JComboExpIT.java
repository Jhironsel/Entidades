package sur.softsurena.entidades;

import javax.swing.JButton;
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
public class JComboExpIT {
    
    public JComboExpIT() {
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
     * Test of createArrowButton method, of class JComboExp.
     */
    @Test
    public void testCreateArrowButton() {
        System.out.println("createArrowButton");
        JComboExp instance = new JComboExp();
        JButton expResult = null;
        JButton result = instance.createArrowButton();
        assertEquals(expResult, result);
    }
    
}
