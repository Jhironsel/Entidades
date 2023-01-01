package sur.softsurena.seguridadPatron;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
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
public class FigurePatternIT {
    
    public FigurePatternIT() {
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
     * Test of draw method, of class FigurePattern.
     */
    @Test
    public void testDraw() {
        System.out.println("draw");
        Graphics2D g2 = null;
        FigurePattern instance = new FigurePattern();
        instance.draw(g2);
    }

    /**
     * Test of getKey method, of class FigurePattern.
     */
    @Test
    public void testGetKey() {
        System.out.println("getKey");
        FigurePattern instance = new FigurePattern();
        int expResult = 0;
        int result = instance.getKey();
        assertEquals(expResult, result);
    }

    /**
     * Test of setKey method, of class FigurePattern.
     */
    @Test
    public void testSetKey() {
        System.out.println("setKey");
        int key = 0;
        FigurePattern instance = new FigurePattern();
        instance.setKey(key);
    }

    /**
     * Test of isSelected method, of class FigurePattern.
     */
    @Test
    public void testIsSelected() {
        System.out.println("isSelected");
        FigurePattern instance = new FigurePattern();
        boolean expResult = false;
        boolean result = instance.isSelected();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSelected method, of class FigurePattern.
     */
    @Test
    public void testSetSelected() {
        System.out.println("setSelected");
        boolean selected = false;
        FigurePattern instance = new FigurePattern();
        instance.setSelected(selected);
    }

    /**
     * Test of getArea method, of class FigurePattern.
     */
    @Test
    public void testGetArea() {
        System.out.println("getArea");
        FigurePattern instance = new FigurePattern();
        Rectangle expResult = null;
        Rectangle result = instance.getArea();
        assertEquals(expResult, result);
    }

    /**
     * Test of setArea method, of class FigurePattern.
     */
    @Test
    public void testSetArea() {
        System.out.println("setArea");
        Rectangle area = null;
        FigurePattern instance = new FigurePattern();
        instance.setArea(area);
    }

    /**
     * Test of getCenterPoint method, of class FigurePattern.
     */
    @Test
    public void testGetCenterPoint() {
        System.out.println("getCenterPoint");
        FigurePattern instance = new FigurePattern();
        Point expResult = null;
        Point result = instance.getCenterPoint();
        assertEquals(expResult, result);
    }

    /**
     * Test of insideArea method, of class FigurePattern.
     */
    @Test
    public void testInsideArea() {
        System.out.println("insideArea");
        Rectangle r = null;
        Point p = null;
        boolean expResult = false;
        boolean result = FigurePattern.insideArea(r, p);
        assertEquals(expResult, result);
    }
    
}
