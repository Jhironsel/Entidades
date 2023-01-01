package sur.softsurena.entidades;

import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JTable;
import javax.swing.SortOrder;
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
public class DefaultTableCellHeaderRendererIT {
    
    public DefaultTableCellHeaderRendererIT() {
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
     * Test of setHorizontalTextPosition method, of class DefaultTableCellHeaderRenderer.
     */
    @Test
    public void testSetHorizontalTextPosition() {
        System.out.println("setHorizontalTextPosition");
        int textPosition = 0;
        DefaultTableCellHeaderRenderer instance = new DefaultTableCellHeaderRenderer();
        instance.setHorizontalTextPosition(textPosition);
    }

    /**
     * Test of getTableCellRendererComponent method, of class DefaultTableCellHeaderRenderer.
     */
    @Test
    public void testGetTableCellRendererComponent() {
        System.out.println("getTableCellRendererComponent");
        JTable table = null;
        Object value = null;
        boolean isSelected = false;
        boolean hasFocus = false;
        int row = 0;
        int column = 0;
        DefaultTableCellHeaderRenderer instance = new DefaultTableCellHeaderRenderer();
        Component expResult = null;
        Component result = instance.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        assertEquals(expResult, result);
    }

    /**
     * Test of getColumnSortOrder method, of class DefaultTableCellHeaderRenderer.
     */
    @Test
    public void testGetColumnSortOrder() {
        System.out.println("getColumnSortOrder");
        JTable table = null;
        int column = 0;
        SortOrder expResult = null;
        SortOrder result = DefaultTableCellHeaderRenderer.getColumnSortOrder(table, column);
        assertEquals(expResult, result);
    }

    /**
     * Test of paintComponent method, of class DefaultTableCellHeaderRenderer.
     */
    @Test
    public void testPaintComponent() {
        System.out.println("paintComponent");
        Graphics g = null;
        DefaultTableCellHeaderRenderer instance = new DefaultTableCellHeaderRenderer();
        instance.paintComponent(g);
    }
    
}
