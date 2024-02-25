package sur.softsurena.metodos;

import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Categoria;
import sur.softsurena.utilidades.FiltroBusqueda;
import sur.softsurena.entidades.Producto;
import sur.softsurena.utilidades.Resultados;
import static sur.softsurena.metodos.M_Producto.PRODUCTO_AGREGADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Producto.PRODUCTO__BORRADO__CORRECTAMENTE;
import static sur.softsurena.metodos.M_Producto.PRODUCTO__MODIFICADO__CORRECTAMENTE;
import static sur.softsurena.metodos.M_Producto.agregarProducto;
import static sur.softsurena.metodos.M_Producto.borrarProductoPorID;
import static sur.softsurena.metodos.M_Producto.existeCategoriaProductos;
import static sur.softsurena.metodos.M_Producto.existeProducto;
import static sur.softsurena.metodos.M_Producto.getProductos;
import static sur.softsurena.metodos.M_Producto.getProductosByCategoria;
import static sur.softsurena.metodos.M_Producto.modificarProducto;

public class M_ProductoNGTest {
    
    public M_ProductoNGTest() {
    }

    @BeforeClass
    public void setUpClass() throws Exception {
        Conexion.getInstance(
                "sysdba",
                "1",
                "BaseDeDatos.db",
                "localhost",
                "3050"
        );
        assertTrue("Error al conectarse...", Conexion.verificar().getEstado());
    }

    @AfterClass
    public void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test(
            enabled = true,
            description = "Test que verifica si el registro de seleccion de producto existe.",
            priority = 1
    
    )
    public void testGetProductos() {
        List<Producto> productos = getProductos(
                FiltroBusqueda
                        .builder()
                        .id(0)
                        .criterioBusqueda("^")
                        .build()
        );
        assertEquals(false, productos.isEmpty());
        Producto producto = productos.get(0);
        assertEquals(0, producto.getId().intValue());
        assertEquals(0, producto.getCategoria().getId_categoria().intValue());
        assertEquals("Seleccione un producto", producto.getDescripcion());
        assertEquals("0", producto.getCodigo());
        assertEquals(new BigDecimal("0.00"), producto.getExistencia());
        assertEquals(Boolean.TRUE, producto.getEstado());
    }

    @Test(
            enabled = true,
            description = "",
            priority = 1
    
    )
    public void testGetProductosByCategoria() {
        List<Producto> result = getProductosByCategoria(0, true);
        assertEquals(0, result.get(0).getId().intValue());
        assertEquals("Seleccione un producto", result.get(0).getDescripcion());
    }

    @Test(
            enabled = false,
            description = "",
            priority = 1
    
    )
    public void testBorrarProductoPorID() {
        Integer ID = null;
        Resultados expResult = null;
        Resultados result = M_Producto.borrarProductoPorID(ID);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 1
    )
    public void testAgregarProducto() {
        Producto producto = Producto
                .builder()
                .categoria(Categoria
                                .builder()
                                .id_categoria(0)
                                .build()
                )
                .codigo("Codigo Prueba")
                .descripcion("Descripcion Prueba")
                .imagenProducto("")
                .nota("Esta es una prueba del sistema.")
                .estado(Boolean.TRUE)
                .build();
        
        Resultados expResult = Resultados
                    .builder()
                    .id(-1)
                    .mensaje(PRODUCTO_AGREGADO_CORRECTAMENTE)
                    .cantidad(-1)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .build();
        
        Resultados result = agregarProducto(producto);
        
        assertEquals(expResult.getMensaje(), result.getMensaje());
        assertEquals(expResult.getIcono(), result.getIcono());
        
        Integer id_producto = result.getId();
        
        
        //Existe producto y categoria, TODO Analizar este metodo. 
        assertEquals(true, existeProducto("0"));
        assertEquals(true, existeProducto("Seleccione"));
        assertEquals(true, existeCategoriaProductos(0));
        
        //Modificando producto.
        producto = Producto
                .builder()
                .id(id_producto)
                .categoria(Categoria
                                .builder()
                                .id_categoria(0)
                                .build()
                )
                .codigo("Codigo Prueba 2")
                .descripcion("Descripcion Prueba 2")
                .imagenProducto("")
                .nota("Esta es una prueba del sistema. 2")
                .estado(Boolean.TRUE)
                .build();
        
        result = modificarProducto(producto);
        
        expResult = Resultados
                    .builder()
                    .mensaje(PRODUCTO__MODIFICADO__CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .build();
        
        assertEquals(expResult.getMensaje(), result.getMensaje());
        assertEquals(expResult.getIcono(), result.getIcono());
        
        //Borrar Producto
        
        result = borrarProductoPorID(id_producto);
        expResult = Resultados
                    .builder()
                    .mensaje(PRODUCTO__BORRADO__CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .build();
        assertEquals(expResult.getMensaje(), result.getMensaje());
        assertEquals(expResult.getIcono(), result.getIcono());
    }

    @Test(
            enabled = false,
            description = "",
            priority = 1
    
    )
    public void testModificarProducto() {
        Producto p = null;
        Resultados expResult = null;
        Resultados result = M_Producto.modificarProducto(p);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 1
    
    )
    public void testExisteCategoriaProductos() {
        int idCategoria = 0;
        boolean expResult = false;
        boolean result = M_Producto.existeCategoriaProductos(idCategoria);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 1
    
    )
    public void testExisteProducto() {
        String criterio = "";
        boolean expResult = false;
        boolean result = M_Producto.existeProducto(criterio);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 1
    
    )
    public void testGetPrecioProducto() {
        int idProducto = 0;
        BigDecimal expResult = null;
        BigDecimal result = M_Producto.getPrecioProducto(idProducto);
        assertEquals(result, expResult);
    }    
}
