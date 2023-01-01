package sur.softsurena.entidades;

import java.io.File;
import java.sql.Date;
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
public class E_S_SYSIT {
    
    public E_S_SYSIT() {
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
     * Test of insertLogo method, of class E_S_SYS.
     */
    @Test
    public void testInsertLogo() {
        System.out.println("insertLogo");
        File file = null;
        boolean expResult = false;
        boolean result = E_S_SYS.insertLogo(file);
        assertEquals(expResult, result);
    }

    /**
     * Test of getLogo method, of class E_S_SYS.
     */
    @Test
    public void testGetLogo() {
        System.out.println("getLogo");
        String expResult = "";
        String result = E_S_SYS.getLogo();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class E_S_SYS.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        E_S_SYS.E_S_SYSBuilder expResult = null;
        E_S_SYS.E_S_SYSBuilder result = E_S_SYS.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getID_E_S_SYS method, of class E_S_SYS.
     */
    @Test
    public void testGetID_E_S_SYS() {
        System.out.println("getID_E_S_SYS");
        E_S_SYS instance = null;
        int expResult = 0;
        int result = instance.getID_E_S_SYS();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNombre method, of class E_S_SYS.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        E_S_SYS instance = null;
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTelefono method, of class E_S_SYS.
     */
    @Test
    public void testGetTelefono() {
        System.out.println("getTelefono");
        E_S_SYS instance = null;
        String expResult = "";
        String result = instance.getTelefono();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFCHI method, of class E_S_SYS.
     */
    @Test
    public void testGetFCHI() {
        System.out.println("getFCHI");
        E_S_SYS instance = null;
        Date expResult = null;
        Date result = instance.getFCHI();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFCHA method, of class E_S_SYS.
     */
    @Test
    public void testGetFCHA() {
        System.out.println("getFCHA");
        E_S_SYS instance = null;
        Date expResult = null;
        Date result = instance.getFCHA();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFCHV method, of class E_S_SYS.
     */
    @Test
    public void testGetFCHV() {
        System.out.println("getFCHV");
        E_S_SYS instance = null;
        Date expResult = null;
        Date result = instance.getFCHV();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIDMAC method, of class E_S_SYS.
     */
    @Test
    public void testGetIDMAC() {
        System.out.println("getIDMAC");
        E_S_SYS instance = null;
        String expResult = "";
        String result = instance.getIDMAC();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDireccion method, of class E_S_SYS.
     */
    @Test
    public void testGetDireccion() {
        System.out.println("getDireccion");
        E_S_SYS instance = null;
        String expResult = "";
        String result = instance.getDireccion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMensaje_footer method, of class E_S_SYS.
     */
    @Test
    public void testGetMensaje_footer() {
        System.out.println("getMensaje_footer");
        E_S_SYS instance = null;
        String expResult = "";
        String result = instance.getMensaje_footer();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFileLogo method, of class E_S_SYS.
     */
    @Test
    public void testGetFileLogo() {
        System.out.println("getFileLogo");
        E_S_SYS instance = null;
        File expResult = null;
        File result = instance.getFileLogo();
        assertEquals(expResult, result);
    }
    
}
