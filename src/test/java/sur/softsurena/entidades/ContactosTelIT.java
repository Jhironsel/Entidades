package sur.softsurena.entidades;

import java.sql.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
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
public class ContactosTelIT {
    
    public ContactosTelIT() {
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
     * Test of agregarContactosTel method, of class ContactosTel.
     */
    @Test
    public void testAgregarContactosTel() {
        System.out.println("agregarContactosTel");
        int id = 0;
        List<ContactosTel> contactos = null;
        boolean expResult = false;
        boolean result = ContactosTel.agregarContactosTel(id, contactos);
        assertEquals(expResult, result);
    }

    /**
     * Test of modificarContactosTel method, of class ContactosTel.
     */
    @Test
    public void testModificarContactosTel() {
        System.out.println("modificarContactosTel");
        int id = 0;
        List<ContactosTel> contactos = null;
        boolean expResult = false;
        boolean result = ContactosTel.modificarContactosTel(id, contactos);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTelefonoByID method, of class ContactosTel.
     */
    @Test
    public void testGetTelefonoByID() {
        System.out.println("getTelefonoByID");
        int id = 0;
        DefaultTableModel expResult = null;
        DefaultTableModel result = ContactosTel.getTelefonoByID(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class ContactosTel.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ContactosTel instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class ContactosTel.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        ContactosTel.ContactosTelBuilder expResult = null;
        ContactosTel.ContactosTelBuilder result = ContactosTel.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class ContactosTel.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        ContactosTel instance = null;
        Integer expResult = null;
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_persona method, of class ContactosTel.
     */
    @Test
    public void testGetId_persona() {
        System.out.println("getId_persona");
        ContactosTel instance = null;
        int expResult = 0;
        int result = instance.getId_persona();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAccion method, of class ContactosTel.
     */
    @Test
    public void testGetAccion() {
        System.out.println("getAccion");
        ContactosTel instance = null;
        char expResult = ' ';
        char result = instance.getAccion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTelefono method, of class ContactosTel.
     */
    @Test
    public void testGetTelefono() {
        System.out.println("getTelefono");
        ContactosTel instance = null;
        String expResult = "";
        String result = instance.getTelefono();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTipo method, of class ContactosTel.
     */
    @Test
    public void testGetTipo() {
        System.out.println("getTipo");
        ContactosTel instance = null;
        String expResult = "";
        String result = instance.getTipo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFecha method, of class ContactosTel.
     */
    @Test
    public void testGetFecha() {
        System.out.println("getFecha");
        ContactosTel instance = null;
        Date expResult = null;
        Date result = instance.getFecha();
        assertEquals(expResult, result);
    }
    
}
