package sur.softsurena.utilidades;

import java.awt.Component;
import java.io.File;
import java.util.Date;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JasperPrint;
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
public class UtilidadesIT {
    
    public UtilidadesIT() {
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
     * Test of clickOnKey method, of class Utilidades.
     */
    @Test
    public void testClickOnKey() {
        System.out.println("clickOnKey");
        AbstractButton button = null;
        String actionName = "";
        int key = 0;
        Utilidades.clickOnKey(button, actionName, key);
    }

    /**
     * Test of selectTextoAll method, of class Utilidades.
     */
    @Test
    public void testSelectTextoAll() {
        System.out.println("selectTextoAll");
        JTextField txt = null;
        int inicio = 0;
        Utilidades.selectTextoAll(txt, inicio);
    }

    /**
     * Test of extractPrintImage method, of class Utilidades.
     */
    @Test
    public void testExtractPrintImage() {
        System.out.println("extractPrintImage");
        String filePath = "";
        JasperPrint print = null;
        Utilidades.extractPrintImage(filePath, print);
    }

    /**
     * Test of controlDouble method, of class Utilidades.
     */
    @Test
    public void testControlDouble() {
        System.out.println("controlDouble");
        Object longValue = null;
        double expResult = 0.0;
        double result = Utilidades.controlDouble(longValue);
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of copyFileUsingFileChannels method, of class Utilidades.
     */
    @Test
    public void testCopyFileUsingFileChannels() {
        System.out.println("copyFileUsingFileChannels");
        String source = "";
        String name = "";
        Utilidades.copyFileUsingFileChannels(source, name);
    }

    /**
     * Test of isNumerc method, of class Utilidades.
     */
    @Test
    public void testIsNumerc() {
        System.out.println("isNumerc");
        String cadena = "";
        boolean expResult = false;
        boolean result = Utilidades.isNumerc(cadena);
        assertEquals(expResult, result);
    }

    /**
     * Test of isNumercFloat method, of class Utilidades.
     */
    @Test
    public void testIsNumercFloat() {
        System.out.println("isNumercFloat");
        String cadena = "";
        boolean expResult = false;
        boolean result = Utilidades.isNumercFloat(cadena);
        assertEquals(expResult, result);
    }

    /**
     * Test of javaDateToSqlDate method, of class Utilidades.
     */
    @Test
    public void testJavaDateToSqlDate() {
        System.out.println("javaDateToSqlDate");
        Date date = null;
        java.sql.Date expResult = null;
        java.sql.Date result = Utilidades.javaDateToSqlDate(date);
        assertEquals(expResult, result);
    }

    /**
     * Test of formatDate method, of class Utilidades.
     */
    @Test
    public void testFormatDate() {
        System.out.println("formatDate");
        Date fecha = null;
        String tipo = "";
        String expResult = "";
        String result = Utilidades.formatDate(fecha, tipo);
        assertEquals(expResult, result);
    }

    /**
     * Test of objectToDouble method, of class Utilidades.
     */
    @Test
    public void testObjectToDouble() {
        System.out.println("objectToDouble");
        Object Obj = null;
        double expResult = 0.0;
        double result = Utilidades.objectToDouble(Obj);
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of objectToInt method, of class Utilidades.
     */
    @Test
    public void testObjectToInt() {
        System.out.println("objectToInt");
        Object Obj = null;
        int expResult = 0;
        int result = Utilidades.objectToInt(Obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of priceWithDecimal method, of class Utilidades.
     */
    @Test
    public void testPriceWithDecimal_double() {
        System.out.println("priceWithDecimal");
        double price = 0.0;
        String expResult = "";
        String result = Utilidades.priceWithDecimal(price);
        assertEquals(expResult, result);
    }

    /**
     * Test of limpiarPantalla method, of class Utilidades.
     */
    @Test
    public void testLimpiarPantalla() {
        System.out.println("limpiarPantalla");
        Utilidades.limpiarPantalla();
    }

    /**
     * Test of showTooltip method, of class Utilidades.
     */
    @Test
    public void testShowTooltip() {
        System.out.println("showTooltip");
        Component component = null;
        Utilidades.showTooltip(component);
    }

    /**
     * Test of imagenEncode64 method, of class Utilidades.
     */
    @Test
    public void testImagenEncode64() {
        System.out.println("imagenEncode64");
        File file = null;
        String expResult = "";
        String result = Utilidades.imagenEncode64(file);
        assertEquals(expResult, result);
    }

    /**
     * Test of imagenDecode64 method, of class Utilidades.
     */
    @Test
    public void testImagenDecode64() {
        System.out.println("imagenDecode64");
        String imagen64 = "";
        ImageIcon expResult = null;
        ImageIcon result = Utilidades.imagenDecode64(imagen64);
        assertEquals(expResult, result);
    }

    /**
     * Test of objectToDate method, of class Utilidades.
     */
    @Test
    public void testObjectToDate() {
        System.out.println("objectToDate");
        Object obj = null;
        Date expResult = null;
        Date result = Utilidades.objectToDate(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of objectToTime method, of class Utilidades.
     */
    @Test
    public void testObjectToTime() {
        System.out.println("objectToTime");
        Object obj = null;
        Date expResult = null;
        Date result = Utilidades.objectToTime(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of Persona method, of class Utilidades.
     */
    @Test
    public void testPersona() {
        System.out.println("Persona");
        int index = 0;
        char expResult = ' ';
        char result = Utilidades.Persona(index);
        assertEquals(expResult, result);
    }

    /**
     * Test of eliminarComas method, of class Utilidades.
     */
    @Test
    public void testEliminarComas() {
        System.out.println("eliminarComas");
        String cadena = "";
        String expResult = "";
        String result = Utilidades.eliminarComas(cadena);
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToDate method, of class Utilidades.
     */
    @Test
    public void testStringToDate() {
        System.out.println("stringToDate");
        String fecha = "";
        Date expResult = null;
        Date result = Utilidades.stringToDate(fecha);
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToDate2 method, of class Utilidades.
     */
    @Test
    public void testStringToDate2() {
        System.out.println("stringToDate2");
        String fecha = "";
        Date expResult = null;
        Date result = Utilidades.stringToDate2(fecha);
        assertEquals(expResult, result);
    }

    /**
     * Test of Redondear method, of class Utilidades.
     */
    @Test
    public void testRedondear() {
        System.out.println("Redondear");
        double numero = 0.0;
        int digitos = 0;
        float expResult = 0.0F;
        float result = Utilidades.Redondear(numero, digitos);
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of Redondear2 method, of class Utilidades.
     */
    @Test
    public void testRedondear2() {
        System.out.println("Redondear2");
        String numero = "";
        int digitos = 0;
        String expResult = "";
        String result = Utilidades.Redondear2(numero, digitos);
        assertEquals(expResult, result);
    }

    /**
     * Test of priceWithDecimal method, of class Utilidades.
     */
    @Test
    public void testPriceWithDecimal_Double() {
        System.out.println("priceWithDecimal");
        Double price = null;
        String expResult = "";
        String result = Utilidades.priceWithDecimal(price);
        assertEquals(expResult, result);
    }

    /**
     * Test of priceWithoutDecimal method, of class Utilidades.
     */
    @Test
    public void testPriceWithoutDecimal() {
        System.out.println("priceWithoutDecimal");
        Double price = null;
        String expResult = "";
        String result = Utilidades.priceWithoutDecimal(price);
        assertEquals(expResult, result);
    }

    /**
     * Test of priceToString method, of class Utilidades.
     */
    @Test
    public void testPriceToString() {
        System.out.println("priceToString");
        Float price = null;
        String expResult = "";
        String result = Utilidades.priceToString(price);
        assertEquals(expResult, result);
    }

    /**
     * Test of repararColumnaTable method, of class Utilidades.
     */
    @Test
    public void testRepararColumnaTable() {
        System.out.println("repararColumnaTable");
        JTable table = null;
        Utilidades.repararColumnaTable(table);
    }

    /**
     * Test of columnasCheckBox method, of class Utilidades.
     */
    @Test
    public void testColumnasCheckBox() {
        System.out.println("columnasCheckBox");
        JTable tblTabla = null;
        int[] indexColumna = null;
        Utilidades.columnasCheckBox(tblTabla, indexColumna);
    }
    
}
