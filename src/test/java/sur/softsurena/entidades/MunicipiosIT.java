package sur.softsurena.entidades;

import RSMaterialComponent.RSComboBox;
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
public class MunicipiosIT {
    
    public MunicipiosIT() {
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
     * Test of getMunicipio method, of class Municipios.
     */
    @Test
    public void testGetMunicipio() {
        System.out.println("getMunicipio");
        int id_provincia = 0;
        RSComboBox jcbMunicipios = null;
        Municipios.getMunicipio(id_provincia, jcbMunicipios);
    }

    /**
     * Test of toString method, of class Municipios.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Municipios instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Municipios.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Municipios.MunicipiosBuilder expResult = null;
        Municipios.MunicipiosBuilder result = Municipios.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Municipios.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Municipios instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNombre method, of class Municipios.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Municipios instance = null;
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdProvincia method, of class Municipios.
     */
    @Test
    public void testGetIdProvincia() {
        System.out.println("getIdProvincia");
        Municipios instance = null;
        int expResult = 0;
        int result = instance.getIdProvincia();
        assertEquals(expResult, result);
    }
    
}
