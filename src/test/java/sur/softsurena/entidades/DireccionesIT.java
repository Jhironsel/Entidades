package sur.softsurena.entidades;

import RSMaterialComponent.RSComboBox;
import java.sql.Date;
import java.util.List;
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
public class DireccionesIT {
    
    public DireccionesIT() {
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
     * Test of llenarCodigoPostal method, of class Direcciones.
     */
    @Test
    public void testLlenarCodigoPostal() {
        System.out.println("llenarCodigoPostal");
        int id_provincia = 0;
        RSComboBox jcbCodigoPostal = null;
        Direcciones.llenarCodigoPostal(id_provincia, jcbCodigoPostal);
    }

    /**
     * Test of agregarDirecciones method, of class Direcciones.
     */
    @Test
    public void testAgregarDirecciones() {
        System.out.println("agregarDirecciones");
        int id = 0;
        List<Direcciones> direcciones = null;
        boolean expResult = false;
        boolean result = Direcciones.agregarDirecciones(id, direcciones);
        assertEquals(expResult, result);
    }

    /**
     * Test of modificarDirecciones method, of class Direcciones.
     */
    @Test
    public void testModificarDirecciones() {
        System.out.println("modificarDirecciones");
        int id = 0;
        List<Direcciones> direcciones = null;
        boolean expResult = false;
        boolean result = Direcciones.modificarDirecciones(id, direcciones);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDireccionByID method, of class Direcciones.
     */
    @Test
    public void testGetDireccionByID() {
        System.out.println("getDireccionByID");
        int id_persona = 0;
        List<Direcciones> expResult = null;
        List<Direcciones> result = Direcciones.getDireccionByID(id_persona);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Direcciones.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Direcciones instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Direcciones.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Direcciones.DireccionesBuilder expResult = null;
        Direcciones.DireccionesBuilder result = Direcciones.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Direcciones.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Direcciones instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_persona method, of class Direcciones.
     */
    @Test
    public void testGetId_persona() {
        System.out.println("getId_persona");
        Direcciones instance = null;
        int expResult = 0;
        int result = instance.getId_persona();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAccion method, of class Direcciones.
     */
    @Test
    public void testGetAccion() {
        System.out.println("getAccion");
        Direcciones instance = null;
        char expResult = ' ';
        char result = instance.getAccion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getProvincia method, of class Direcciones.
     */
    @Test
    public void testGetProvincia() {
        System.out.println("getProvincia");
        Direcciones instance = null;
        Provincias expResult = null;
        Provincias result = instance.getProvincia();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMunicipio method, of class Direcciones.
     */
    @Test
    public void testGetMunicipio() {
        System.out.println("getMunicipio");
        Direcciones instance = null;
        Municipios expResult = null;
        Municipios result = instance.getMunicipio();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDistrito_municipal method, of class Direcciones.
     */
    @Test
    public void testGetDistrito_municipal() {
        System.out.println("getDistrito_municipal");
        Direcciones instance = null;
        Distritos_municipales expResult = null;
        Distritos_municipales result = instance.getDistrito_municipal();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCodigo_postal method, of class Direcciones.
     */
    @Test
    public void testGetCodigo_postal() {
        System.out.println("getCodigo_postal");
        Direcciones instance = null;
        Codigo_Postal expResult = null;
        Codigo_Postal result = instance.getCodigo_postal();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDireccion method, of class Direcciones.
     */
    @Test
    public void testGetDireccion() {
        System.out.println("getDireccion");
        Direcciones instance = null;
        String expResult = "";
        String result = instance.getDireccion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFecha method, of class Direcciones.
     */
    @Test
    public void testGetFecha() {
        System.out.println("getFecha");
        Direcciones instance = null;
        Date expResult = null;
        Date result = instance.getFecha();
        assertEquals(expResult, result);
    }
    
}
