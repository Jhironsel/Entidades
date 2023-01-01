package sur.softsurena.entidades;

import java.math.BigDecimal;
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
public class D_RecetasIT {
    
    public D_RecetasIT() {
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
     * Test of agregarRecetaDetalle method, of class D_Recetas.
     */
    @Test
    public void testAgregarRecetaDetalle() {
        System.out.println("agregarRecetaDetalle");
        List<D_Recetas> dr = null;
        D_Recetas.agregarRecetaDetalle(dr);
    }

    /**
     * Test of toString method, of class D_Recetas.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        D_Recetas instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class D_Recetas.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        D_Recetas.D_RecetasBuilder expResult = null;
        D_Recetas.D_RecetasBuilder result = D_Recetas.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_receta method, of class D_Recetas.
     */
    @Test
    public void testGetId_receta() {
        System.out.println("getId_receta");
        D_Recetas instance = null;
        int expResult = 0;
        int result = instance.getId_receta();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLinea method, of class D_Recetas.
     */
    @Test
    public void testGetLinea() {
        System.out.println("getLinea");
        D_Recetas instance = null;
        int expResult = 0;
        int result = instance.getLinea();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_Medicamento method, of class D_Recetas.
     */
    @Test
    public void testGetId_Medicamento() {
        System.out.println("getId_Medicamento");
        D_Recetas instance = null;
        int expResult = 0;
        int result = instance.getId_Medicamento();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCantidad method, of class D_Recetas.
     */
    @Test
    public void testGetCantidad() {
        System.out.println("getCantidad");
        D_Recetas instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.getCantidad();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDetalleDosis method, of class D_Recetas.
     */
    @Test
    public void testGetDetalleDosis() {
        System.out.println("getDetalleDosis");
        D_Recetas instance = null;
        String expResult = "";
        String result = instance.getDetalleDosis();
        assertEquals(expResult, result);
    }
    
}
