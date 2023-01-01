package sur.softsurena.entidades;

import java.sql.Date;
import java.util.List;
import javax.swing.JComboBox;
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
public class PersonasIT {
    
    public PersonasIT() {
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
     * Test of llenarPersona method, of class Personas.
     */
    @Test
    public void testLlenarPersona() {
        System.out.println("llenarPersona");
        JComboBox jcbPersona = null;
        Personas.llenarPersona(jcbPersona);
    }

    /**
     * Test of llenarSexo method, of class Personas.
     */
    @Test
    public void testLlenarSexo() {
        System.out.println("llenarSexo");
        JComboBox jcbSexo = null;
        Personas.llenarSexo(jcbSexo);
    }

    /**
     * Test of llenarEstadoCivil method, of class Personas.
     */
    @Test
    public void testLlenarEstadoCivil() {
        System.out.println("llenarEstadoCivil");
        JComboBox jcbEstadoCivil = null;
        Personas.llenarEstadoCivil(jcbEstadoCivil);
    }

    /**
     * Test of llenarTipoSangre method, of class Personas.
     */
    @Test
    public void testLlenarTipoSangre() {
        System.out.println("llenarTipoSangre");
        JComboBox jcbTipoSangre = null;
        Personas.llenarTipoSangre(jcbTipoSangre);
    }

    /**
     * Test of toString method, of class Personas.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Personas instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_persona method, of class Personas.
     */
    @Test
    public void testGetId_persona() {
        System.out.println("getId_persona");
        Personas instance = null;
        int expResult = 0;
        int result = instance.getId_persona();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPersona method, of class Personas.
     */
    @Test
    public void testGetPersona() {
        System.out.println("getPersona");
        Personas instance = null;
        char expResult = ' ';
        char result = instance.getPersona();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPNombre method, of class Personas.
     */
    @Test
    public void testGetPNombre() {
        System.out.println("getPNombre");
        Personas instance = null;
        String expResult = "";
        String result = instance.getPNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSNombre method, of class Personas.
     */
    @Test
    public void testGetSNombre() {
        System.out.println("getSNombre");
        Personas instance = null;
        String expResult = "";
        String result = instance.getSNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test of getApellidos method, of class Personas.
     */
    @Test
    public void testGetApellidos() {
        System.out.println("getApellidos");
        Personas instance = null;
        String expResult = "";
        String result = instance.getApellidos();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSexo method, of class Personas.
     */
    @Test
    public void testGetSexo() {
        System.out.println("getSexo");
        Personas instance = null;
        char expResult = ' ';
        char result = instance.getSexo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFecha_nacimiento method, of class Personas.
     */
    @Test
    public void testGetFecha_nacimiento() {
        System.out.println("getFecha_nacimiento");
        Personas instance = null;
        Date expResult = null;
        Date result = instance.getFecha_nacimiento();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFecha_ingreso method, of class Personas.
     */
    @Test
    public void testGetFecha_ingreso() {
        System.out.println("getFecha_ingreso");
        Personas instance = null;
        Date expResult = null;
        Date result = instance.getFecha_ingreso();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFecha_hora_ultima_update method, of class Personas.
     */
    @Test
    public void testGetFecha_hora_ultima_update() {
        System.out.println("getFecha_hora_ultima_update");
        Personas instance = null;
        Date expResult = null;
        Date result = instance.getFecha_hora_ultima_update();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEstado method, of class Personas.
     */
    @Test
    public void testGetEstado() {
        System.out.println("getEstado");
        Personas instance = null;
        Boolean expResult = null;
        Boolean result = instance.getEstado();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUser_name method, of class Personas.
     */
    @Test
    public void testGetUser_name() {
        System.out.println("getUser_name");
        Personas instance = null;
        String expResult = "";
        String result = instance.getUser_name();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRol method, of class Personas.
     */
    @Test
    public void testGetRol() {
        System.out.println("getRol");
        Personas instance = null;
        String expResult = "";
        String result = instance.getRol();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGenerales method, of class Personas.
     */
    @Test
    public void testGetGenerales() {
        System.out.println("getGenerales");
        Personas instance = null;
        Generales expResult = null;
        Generales result = instance.getGenerales();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAsegurado method, of class Personas.
     */
    @Test
    public void testGetAsegurado() {
        System.out.println("getAsegurado");
        Personas instance = null;
        Asegurados expResult = null;
        Asegurados result = instance.getAsegurado();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDireccion method, of class Personas.
     */
    @Test
    public void testGetDireccion() {
        System.out.println("getDireccion");
        Personas instance = null;
        List<Direcciones> expResult = null;
        List<Direcciones> result = instance.getDireccion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getContactosEmail method, of class Personas.
     */
    @Test
    public void testGetContactosEmail() {
        System.out.println("getContactosEmail");
        Personas instance = null;
        List<ContactosEmail> expResult = null;
        List<ContactosEmail> result = instance.getContactosEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getContactosTel method, of class Personas.
     */
    @Test
    public void testGetContactosTel() {
        System.out.println("getContactosTel");
        Personas instance = null;
        List<ContactosTel> expResult = null;
        List<ContactosTel> result = instance.getContactosTel();
        assertEquals(expResult, result);
    }

    public class PersonasImpl extends Personas {

        public PersonasImpl() {
            super(null);
        }
    }
    
}
