package sur.softsurena.graficas;

import javax.swing.JPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
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
public class LongitudAlturaParaEdadNino0a5annoIT {
    
    public LongitudAlturaParaEdadNino0a5annoIT() {
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
     * Test of createChart method, of class LongitudAlturaParaEdadNino0a5anno.
     */
    @Test
    public void testCreateChart() {
        System.out.println("createChart");
        XYDataset paramXYDataset = null;
        LongitudAlturaParaEdadNino0a5anno instance = null;
        JFreeChart expResult = null;
        JFreeChart result = instance.createChart(paramXYDataset);
        assertEquals(expResult, result);
    }

    /**
     * Test of createDemoPanel method, of class LongitudAlturaParaEdadNino0a5anno.
     */
    @Test
    public void testCreateDemoPanel() {
        System.out.println("createDemoPanel");
        LongitudAlturaParaEdadNino0a5anno instance = null;
        JPanel expResult = null;
        JPanel result = instance.createDemoPanel();
        assertEquals(expResult, result);
    }
    
}
