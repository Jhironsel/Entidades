package sur.softsurena.entidades;

import java.io.File;
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
public class CategoriasIT {
    
    public CategoriasIT() {
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
     * Test of agregarCategoria method, of class Categorias.
     */
    @Test
    public void testAgregarCategoria() {
        System.out.println("agregarCategoria");
        Categorias c = null;
        Resultados expResult = null;
        Resultados result = Categorias.agregarCategoria(c);
        assertEquals(expResult, result);
    }

    /**
     * Test of modificarCategoria method, of class Categorias.
     */
    @Test
    public void testModificarCategoria() {
        System.out.println("modificarCategoria");
        Categorias c = null;
        String expResult = "";
        String result = Categorias.modificarCategoria(c);
        assertEquals(expResult, result);
    }

    /**
     * Test of borrarCategoria method, of class Categorias.
     */
    @Test
    public void testBorrarCategoria() {
        System.out.println("borrarCategoria");
        int idCategoria = 0;
        String expResult = "";
        String result = Categorias.borrarCategoria(idCategoria);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCategirias method, of class Categorias.
     */
    @Test
    public void testGetCategirias() {
        System.out.println("getCategirias");
        JComboBox cbCategoria = null;
        Categorias.getCategirias(cbCategoria);
    }

    /**
     * Test of getCategorias method, of class Categorias.
     */
    @Test
    public void testGetCategorias() {
        System.out.println("getCategorias");
        List<Categorias> expResult = null;
        List<Categorias> result = Categorias.getCategorias();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCategoriaActivas method, of class Categorias.
     */
    @Test
    public void testGetCategoriaActivas() {
        System.out.println("getCategoriaActivas");
        List<Categorias> expResult = null;
        List<Categorias> result = Categorias.getCategoriaActivas();
        assertEquals(expResult, result);
    }

    /**
     * Test of existeCategoria method, of class Categorias.
     */
    @Test
    public void testExisteCategoria() {
        System.out.println("existeCategoria");
        String descripcion = "";
        boolean expResult = false;
        boolean result = Categorias.existeCategoria(descripcion);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Categorias.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Categorias instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Categorias.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Object o = null;
        Categorias instance = null;
        int expResult = 0;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Categorias.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Categorias.CategoriasBuilder expResult = null;
        Categorias.CategoriasBuilder result = Categorias.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Categorias.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Categorias instance = null;
        Integer expResult = null;
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescripcion method, of class Categorias.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        Categorias instance = null;
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPathImage method, of class Categorias.
     */
    @Test
    public void testGetPathImage() {
        System.out.println("getPathImage");
        Categorias instance = null;
        File expResult = null;
        File result = instance.getPathImage();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImage_texto method, of class Categorias.
     */
    @Test
    public void testGetImage_texto() {
        System.out.println("getImage_texto");
        Categorias instance = null;
        String expResult = "";
        String result = instance.getImage_texto();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFecha_creacion method, of class Categorias.
     */
    @Test
    public void testGetFecha_creacion() {
        System.out.println("getFecha_creacion");
        Categorias instance = null;
        Date expResult = null;
        Date result = instance.getFecha_creacion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEstado method, of class Categorias.
     */
    @Test
    public void testGetEstado() {
        System.out.println("getEstado");
        Categorias instance = null;
        Boolean expResult = null;
        Boolean result = instance.getEstado();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdUsuario method, of class Categorias.
     */
    @Test
    public void testGetIdUsuario() {
        System.out.println("getIdUsuario");
        Categorias instance = null;
        String expResult = "";
        String result = instance.getIdUsuario();
        assertEquals(expResult, result);
    }
    
}
