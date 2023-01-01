package sur.softsurena.entidades;

import java.awt.Component;
import javax.swing.JTable;
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
public class Celda_CheckBoxIT {
    
    public Celda_CheckBoxIT() {
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
     * Test of getCellEditorValue method, of class Celda_CheckBox.
     */
    @Test
    public void testGetCellEditorValue() {
        System.out.println("getCellEditorValue");
        Celda_CheckBox instance = new Celda_CheckBox();
        Object expResult = null;
        Object result = instance.getCellEditorValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTableCellEditorComponent method, of class Celda_CheckBox.
     */
    @Test
    public void testGetTableCellEditorComponent() {
        System.out.println("getTableCellEditorComponent");
        JTable table = null;
        Object value = null;
        boolean isSelected = false;
        int row = 0;
        int column = 0;
        Celda_CheckBox instance = new Celda_CheckBox();
        Component expResult = null;
        Component result = instance.getTableCellEditorComponent(table, value, isSelected, row, column);
        assertEquals(expResult, result);
    }

    /**
     * Test of stopCellEditing method, of class Celda_CheckBox.
     */
    @Test
    public void testStopCellEditing() {
        System.out.println("stopCellEditing");
        Celda_CheckBox instance = new Celda_CheckBox();
        boolean expResult = false;
        boolean result = instance.stopCellEditing();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTableCellRendererComponent method, of class Celda_CheckBox.
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
        Celda_CheckBox instance = new Celda_CheckBox();
        Component expResult = null;
        Component result = instance.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        assertEquals(expResult, result);
    }
    
}
