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
public class ContactosEmailIT {
    
    public ContactosEmailIT() {
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
     * Test of agregarContactosEmail method, of class ContactosEmail.
     */
    @Test
    public void testAgregarContactosEmail() {
        System.out.println("agregarContactosEmail");
        int id = 0;
        List<ContactosEmail> contactos = null;
        boolean expResult = false;
        boolean result = ContactosEmail.agregarContactosEmail(id, contactos);
        assertEquals(expResult, result);
    }

    /**
     * Test of modificarContactosEmail method, of class ContactosEmail.
     */
    @Test
    public void testModificarContactosEmail() {
        System.out.println("modificarContactosEmail");
        int id = 0;
        List<ContactosEmail> contactos = null;
        boolean expResult = false;
        boolean result = ContactosEmail.modificarContactosEmail(id, contactos);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCorreoByID method, of class ContactosEmail.
     */
    @Test
    public void testGetCorreoByID() {
        System.out.println("getCorreoByID");
        int id = 0;
        DefaultTableModel expResult = null;
        DefaultTableModel result = ContactosEmail.getCorreoByID(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class ContactosEmail.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ContactosEmail instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class ContactosEmail.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        ContactosEmail.ContactosEmailBuilder expResult = null;
        ContactosEmail.ContactosEmailBuilder result = ContactosEmail.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class ContactosEmail.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        ContactosEmail instance = null;
        Integer expResult = null;
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_persona method, of class ContactosEmail.
     */
    @Test
    public void testGetId_persona() {
        System.out.println("getId_persona");
        ContactosEmail instance = null;
        int expResult = 0;
        int result = instance.getId_persona();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAccion method, of class ContactosEmail.
     */
    @Test
    public void testGetAccion() {
        System.out.println("getAccion");
        ContactosEmail instance = null;
        char expResult = ' ';
        char result = instance.getAccion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class ContactosEmail.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        ContactosEmail instance = null;
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFecha method, of class ContactosEmail.
     */
    @Test
    public void testGetFecha() {
        System.out.println("getFecha");
        ContactosEmail instance = null;
        Date expResult = null;
        Date result = instance.getFecha();
        assertEquals(expResult, result);
    }
    
}
