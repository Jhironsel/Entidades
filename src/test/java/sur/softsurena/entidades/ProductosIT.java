package sur.softsurena.entidades;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
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
public class ProductosIT {
    
    public ProductosIT() {
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
     * Test of buscarProducto method, of class Productos.
     */
    @Test
    public void testBuscarProducto() {
        System.out.println("buscarProducto");
        String criterio = "";
        ResultSet expResult = null;
        ResultSet result = Productos.buscarProducto(criterio);
        assertEquals(expResult, result);
    }

    /**
     * Test of getProductos method, of class Productos.
     */
    @Test
    public void testGetProductos() {
        System.out.println("getProductos");
        List<Productos> expResult = null;
        List<Productos> result = Productos.getProductos();
        assertEquals(expResult, result);
    }

    /**
     * Test of borrarProductoPorID method, of class Productos.
     */
    @Test
    public void testBorrarProductoPorID() {
        System.out.println("borrarProductoPorID");
        int id = 0;
        String expResult = "";
        String result = Productos.borrarProductoPorID(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of borrarProductoPorCodigo method, of class Productos.
     */
    @Test
    public void testBorrarProductoPorCodigo() {
        System.out.println("borrarProductoPorCodigo");
        String codigo = "";
        String expResult = "";
        String result = Productos.borrarProductoPorCodigo(codigo);
        assertEquals(expResult, result);
    }

    /**
     * Test of agregarProducto method, of class Productos.
     */
    @Test
    public void testAgregarProducto() {
        System.out.println("agregarProducto");
        Productos p = null;
        Resultados expResult = null;
        Resultados result = Productos.agregarProducto(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of modificarProducto method, of class Productos.
     */
    @Test
    public void testModificarProducto() {
        System.out.println("modificarProducto");
        Productos p = null;
        String expResult = "";
        String result = Productos.modificarProducto(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of existeCategoriaProductos method, of class Productos.
     */
    @Test
    public void testExisteCategoriaProductos() {
        System.out.println("existeCategoriaProductos");
        int idCategoria = 0;
        boolean expResult = false;
        boolean result = Productos.existeCategoriaProductos(idCategoria);
        assertEquals(expResult, result);
    }

    /**
     * Test of existeProducto method, of class Productos.
     */
    @Test
    public void testExisteProducto() {
        System.out.println("existeProducto");
        String codigo = "";
        boolean expResult = false;
        boolean result = Productos.existeProducto(codigo);
        assertEquals(expResult, result);
    }

    /**
     * Test of getProductoById method, of class Productos.
     */
    @Test
    public void testGetProductoById() {
        System.out.println("getProductoById");
        Integer idProducto = null;
        String codigo = "";
        ResultSet expResult = null;
        ResultSet result = Productos.getProductoById(idProducto, codigo);
        assertEquals(expResult, result);
    }

    /**
     * Test of getProducto method, of class Productos.
     */
    @Test
    public void testGetProducto() {
        System.out.println("getProducto");
        int id = 0;
        Productos expResult = null;
        Productos result = Productos.getProducto(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getProductosCriterios method, of class Productos.
     */
    @Test
    public void testGetProductosCriterios() {
        System.out.println("getProductosCriterios");
        int tipo = 0;
        String criterio = "";
        boolean image = false;
        ResultSet expResult = null;
        ResultSet result = Productos.getProductosCriterios(tipo, criterio, image);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrecioProducto method, of class Productos.
     */
    @Test
    public void testGetPrecioProducto() {
        System.out.println("getPrecioProducto");
        int idProducto = 0;
        BigDecimal expResult = null;
        BigDecimal result = Productos.getPrecioProducto(idProducto);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Productos.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Productos instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Productos.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Productos.ProductosBuilder expResult = null;
        Productos.ProductosBuilder result = Productos.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Productos.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Productos instance = null;
        Integer expResult = null;
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdCategoria method, of class Productos.
     */
    @Test
    public void testGetIdCategoria() {
        System.out.println("getIdCategoria");
        Productos instance = null;
        int expResult = 0;
        int result = instance.getIdCategoria();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDesc_categoria method, of class Productos.
     */
    @Test
    public void testGetDesc_categoria() {
        System.out.println("getDesc_categoria");
        Productos instance = null;
        String expResult = "";
        String result = instance.getDesc_categoria();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImagen_categoria method, of class Productos.
     */
    @Test
    public void testGetImagen_categoria() {
        System.out.println("getImagen_categoria");
        Productos instance = null;
        String expResult = "";
        String result = instance.getImagen_categoria();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCodigo method, of class Productos.
     */
    @Test
    public void testGetCodigo() {
        System.out.println("getCodigo");
        Productos instance = null;
        String expResult = "";
        String result = instance.getCodigo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescripcion method, of class Productos.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        Productos instance = null;
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPathImagen method, of class Productos.
     */
    @Test
    public void testGetPathImagen() {
        System.out.println("getPathImagen");
        Productos instance = null;
        File expResult = null;
        File result = instance.getPathImagen();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImagenTexto method, of class Productos.
     */
    @Test
    public void testGetImagenTexto() {
        System.out.println("getImagenTexto");
        Productos instance = null;
        String expResult = "";
        String result = instance.getImagenTexto();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNota method, of class Productos.
     */
    @Test
    public void testGetNota() {
        System.out.println("getNota");
        Productos instance = null;
        String expResult = "";
        String result = instance.getNota();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFechaCreacion method, of class Productos.
     */
    @Test
    public void testGetFechaCreacion() {
        System.out.println("getFechaCreacion");
        Productos instance = null;
        Date expResult = null;
        Date result = instance.getFechaCreacion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEstado method, of class Productos.
     */
    @Test
    public void testGetEstado() {
        System.out.println("getEstado");
        Productos instance = null;
        Boolean expResult = null;
        Boolean result = instance.getEstado();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdUsuario method, of class Productos.
     */
    @Test
    public void testGetIdUsuario() {
        System.out.println("getIdUsuario");
        Productos instance = null;
        String expResult = "";
        String result = instance.getIdUsuario();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRol method, of class Productos.
     */
    @Test
    public void testGetRol() {
        System.out.println("getRol");
        Productos instance = null;
        String expResult = "";
        String result = instance.getRol();
        assertEquals(expResult, result);
    }
    
}
