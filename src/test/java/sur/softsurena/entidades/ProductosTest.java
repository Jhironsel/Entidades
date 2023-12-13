package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sur.softsurena.conexion.Conexion;

public class ProductosTest {
    
    public ProductosTest() {
        Conexion.getInstance(
                "sysdba",
                "1",
                "BaseDeDatos.db",
                "localhost",
                "3050"
        );
        if (Conexion.verificar().getEstado()) {
            System.out.println("Contraseña correcta...");
        } else {
            System.out.println("Error en la clave...");
        }
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
     * Test of getProductos method, of class Productos.
     */
    @Test
    public void testGetProductos() {
        
        List<Productos> productos = Productos.getProductos(
                FiltroBusqueda
                        .builder()
                        .id(0)
                        .criterioBusqueda("^")
                        .build()
        );
        assertEquals(false, productos.isEmpty());
        Productos producto = productos.get(0);
        assertEquals(0, producto.getId().intValue());
        assertEquals(0, producto.getCategoria().getId_categoria().intValue());
        assertEquals("Seleccione un producto", producto.getDescripcion());
        assertEquals("0", producto.getCodigo());
        assertEquals(new BigDecimal("0.00"), producto.getExistencia());
        assertEquals(Boolean.TRUE, producto.getEstado());
    }

    /**
     * Test of getProductosByCategoria method, of class Productos.
     */
//    @Test
//    public void testGetProductosByCategoria() {
//        List<Productos> expResult = null;
//        List<Productos> result = Productos.getProductosByCategoria(0, true);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of borrarProductoPorID method, of class Productos.
//     */
//    @Test
//    public void testBorrarProductoPorID() {
//        Integer ID = null;
//        Resultados expResult = null;
//        Resultados result = Productos.borrarProductoPorID(ID);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of agregarProducto method, of class Productos.
//     */
//    @Test
//    public void testAgregarProducto() {
//        Productos p = null;
//        Resultados expResult = null;
//        Resultados result = Productos.agregarProducto(p);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of modificarProducto method, of class Productos.
//     */
//    @Test
//    public void testModificarProducto() {
//        Productos p = null;
//        Resultados expResult = null;
//        Resultados result = Productos.modificarProducto(p);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of existeCategoriaProductos method, of class Productos.
//     */
//    @Test
//    public void testExisteCategoriaProductos() {
//        int idCategoria = 0;
//        boolean expResult = false;
//        boolean result = Productos.existeCategoriaProductos(idCategoria);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of existeProducto method, of class Productos.
//     */
//    @Test
//    public void testExisteProducto() {
//        String criterio = "";
//        boolean expResult = false;
//        boolean result = Productos.existeProducto(criterio);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getPrecioProducto method, of class Productos.
//     */
//    @Test
//    public void testGetPrecioProducto() {
//        int idProducto = 0;
//        BigDecimal expResult = null;
//        BigDecimal result = Productos.getPrecioProducto(idProducto);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of generarProducto method, of class Productos.
//     */
//    @Test
//    public void testGenerarProducto() {
//        String expResult = "";
//        String result = Productos.generarProducto();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of generarCodigoBarra method, of class Productos.
//     */
//    @Test
//    public void testGenerarCodigoBarra() {
//        String expResult = "";
//        String result = Productos.generarCodigoBarra();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of toString method, of class Productos.
//     */
//    @Test
//    public void testToString() {
//        Productos instance = null;
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of builder method, of class Productos.
//     */
//    @Test
//    public void testBuilder() {
//        Productos.ProductosBuilder expResult = null;
//        Productos.ProductosBuilder result = Productos.builder();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getId method, of class Productos.
//     */
//    @Test
//    public void testGetId() {
//        Productos instance = null;
//        Integer expResult = null;
//        Integer result = instance.getId();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getCategoria method, of class Productos.
//     */
//    @Test
//    public void testGetCategoria() {
//        Productos instance = null;
//        Categorias expResult = null;
//        Categorias result = instance.getCategoria();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getCodigo method, of class Productos.
//     */
//    @Test
//    public void testGetCodigo() {
//        Productos instance = null;
//        String expResult = "";
//        String result = instance.getCodigo();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getDescripcion method, of class Productos.
//     */
//    @Test
//    public void testGetDescripcion() {
//        Productos instance = null;
//        String expResult = "";
//        String result = instance.getDescripcion();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getPathImagen method, of class Productos.
//     */
//    @Test
//    public void testGetPathImagen() {
//        Productos instance = null;
//        File expResult = null;
//        File result = instance.getPathImagen();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getImagenProducto method, of class Productos.
//     */
//    @Test
//    public void testGetImagenProducto() {
//        Productos instance = null;
//        String expResult = "";
//        String result = instance.getImagenProducto();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getNota method, of class Productos.
//     */
//    @Test
//    public void testGetNota() {
//        Productos instance = null;
//        String expResult = "";
//        String result = instance.getNota();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getFechaCreacion method, of class Productos.
//     */
//    @Test
//    public void testGetFechaCreacion() {
//        Productos instance = null;
//        Date expResult = null;
//        Date result = instance.getFechaCreacion();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getEstado method, of class Productos.
//     */
//    @Test
//    public void testGetEstado() {
//        Productos instance = null;
//        Boolean expResult = null;
//        Boolean result = instance.getEstado();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getUserName method, of class Productos.
//     */
//    @Test
//    public void testGetUserName() {
//        Productos instance = null;
//        String expResult = "";
//        String result = instance.getUserName();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getRol method, of class Productos.
//     */
//    @Test
//    public void testGetRol() {
//        Productos instance = null;
//        String expResult = "";
//        String result = instance.getRol();
//        assertEquals(expResult, result);
//    }
    
}
