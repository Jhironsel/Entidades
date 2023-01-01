package sur.softsurena.entidades;

import java.io.File;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
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
public class MedicamentosIT {
    
    public MedicamentosIT() {
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
     * Test of modificarMedicamento method, of class Medicamentos.
     */
    @Test
    public void testModificarMedicamento() {
        System.out.println("modificarMedicamento");
        Medicamentos m = null;
        String expResult = "";
        String result = Medicamentos.modificarMedicamento(m);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMedicamentoActivo method, of class Medicamentos.
     */
    @Test
    public void testGetMedicamentoActivo() {
        System.out.println("getMedicamentoActivo");
        ResultSet expResult = null;
        ResultSet result = Medicamentos.getMedicamentoActivo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMedicamentoFoto method, of class Medicamentos.
     */
    @Test
    public void testGetMedicamentoFoto() {
        System.out.println("getMedicamentoFoto");
        String idMedicamento = "";
        ResultSet expResult = null;
        ResultSet result = Medicamentos.getMedicamentoFoto(idMedicamento);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMedicamento method, of class Medicamentos.
     */
    @Test
    public void testGetMedicamento() {
        System.out.println("getMedicamento");
        boolean estado = false;
        ResultSet expResult = null;
        ResultSet result = Medicamentos.getMedicamento(estado);
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Medicamentos.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Medicamentos.MedicamentosBuilder expResult = null;
        Medicamentos.MedicamentosBuilder result = Medicamentos.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Medicamentos.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Medicamentos instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId_proveedor method, of class Medicamentos.
     */
    @Test
    public void testGetId_proveedor() {
        System.out.println("getId_proveedor");
        Medicamentos instance = null;
        int expResult = 0;
        int result = instance.getId_proveedor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescripcion method, of class Medicamentos.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        Medicamentos instance = null;
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPathImagen method, of class Medicamentos.
     */
    @Test
    public void testGetPathImagen() {
        System.out.println("getPathImagen");
        Medicamentos instance = null;
        File expResult = null;
        File result = instance.getPathImagen();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImagen method, of class Medicamentos.
     */
    @Test
    public void testGetImagen() {
        System.out.println("getImagen");
        Medicamentos instance = null;
        ImageIcon expResult = null;
        ImageIcon result = instance.getImagen();
        assertEquals(expResult, result);
    }

    /**
     * Test of isEstado method, of class Medicamentos.
     */
    @Test
    public void testIsEstado() {
        System.out.println("isEstado");
        Medicamentos instance = null;
        boolean expResult = false;
        boolean result = instance.isEstado();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsuario method, of class Medicamentos.
     */
    @Test
    public void testGetUsuario() {
        System.out.println("getUsuario");
        Medicamentos instance = null;
        String expResult = "";
        String result = instance.getUsuario();
        assertEquals(expResult, result);
    }
    
}
