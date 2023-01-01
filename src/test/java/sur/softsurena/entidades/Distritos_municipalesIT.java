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
public class Distritos_municipalesIT {
    
    public Distritos_municipalesIT() {
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
     * Test of getDistritosMunicipales method, of class Distritos_municipales.
     */
    @Test
    public void testGetDistritosMunicipales() {
        System.out.println("getDistritosMunicipales");
        int id_municipio = 0;
        RSComboBox jcbDistritoMunicipal = null;
        Distritos_municipales.getDistritosMunicipales(id_municipio, jcbDistritoMunicipal);
    }

    /**
     * Test of toString method, of class Distritos_municipales.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Distritos_municipales instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Distritos_municipales.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Distritos_municipales.Distritos_municipalesBuilder expResult = null;
        Distritos_municipales.Distritos_municipalesBuilder result = Distritos_municipales.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Distritos_municipales.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Distritos_municipales instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNombre method, of class Distritos_municipales.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Distritos_municipales instance = null;
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdMunicipio method, of class Distritos_municipales.
     */
    @Test
    public void testGetIdMunicipio() {
        System.out.println("getIdMunicipio");
        Distritos_municipales instance = null;
        int expResult = 0;
        int result = instance.getIdMunicipio();
        assertEquals(expResult, result);
    }
    
}
