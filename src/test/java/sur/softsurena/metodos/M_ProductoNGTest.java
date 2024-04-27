package sur.softsurena.metodos;

import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import lombok.Getter;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Categoria;
import sur.softsurena.entidades.Producto;
import static sur.softsurena.metodos.M_Producto.ERROR_AL__INSERTAR__PRODUCTO;
import static sur.softsurena.metodos.M_Producto.ERROR_AL__MODIFICAR__PRODUCTO;
import static sur.softsurena.metodos.M_Producto.OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_EL__PR;
import static sur.softsurena.metodos.M_Producto.PRODUCTO_AGREGADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Producto.PRODUCTO__BORRADO__CORRECTAMENTE;
import static sur.softsurena.metodos.M_Producto.PRODUCTO__MODIFICADO__CORRECTAMENTE;
import static sur.softsurena.metodos.M_Producto.agregarProducto;
import static sur.softsurena.metodos.M_Producto.getProductos;
import static sur.softsurena.metodos.M_Producto.getProductosByCategoria;
import static sur.softsurena.metodos.M_Producto.modificarProducto;
import sur.softsurena.utilidades.FiltroBusqueda;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.imagenEncode64;

@Getter
public class M_ProductoNGTest {

    private static Integer id_producto;
    private static Producto producto, producto2;

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
        assertTrue(
                Conexion.verificar().getEstado(),
                "Error al conectarse..."
        );

        M_CategoriaNGTest.testAgregarCategoria();
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        Conexion.getCnn().close();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        producto = Producto
                .builder()
                .id(id_producto)
                .categoria(
                        Categoria
                                .builder()
                                .id_categoria(
                                        M_CategoriaNGTest.idCategoria1
                                )
                                .build()
                )
                .codigo(
                        M_ContactoTel.generarTelMovil().substring(8, 16)
                )
                .descripcion(
                        "Descripcion Prueba %s".formatted(
                                M_ContactoTel.generarTelMovil().substring(8, 16)
                        )
                )
                .imagenProducto(
                        imagenEncode64(
                                new File("Imagenes/ImagenPrueba.png")
                        )
                )
                .nota("Esta es una prueba del sistema.")
                .estado(Boolean.TRUE)
                .build();

        producto2 = Producto
                .builder()
                .id(id_producto)
                .categoria(
                        Categoria
                                .builder()
                                .id_categoria(
                                        M_CategoriaNGTest.idCategoria2
                                )
                                .build()
                )
                .codigo(
                        M_ContactoTel.generarTelMovil().substring(8, 16)
                )
                .descripcion(
                        "Descripcion Prueba %s".formatted(
                                M_ContactoTel.generarTelMovil().substring(8, 16)
                        )
                )
                .imagenProducto(
                        imagenEncode64(
                                new File("Imagenes/ImagenPrueba.png")
                        )
                )
                .nota("Esta es una prueba del sistema.")
                .estado(Boolean.TRUE)
                .build();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        producto = null;
    }

    @Test(
            enabled = true,
            description = """
                          Test que verifica si el registro de seleccion de 
                          producto existentes.
                          """,
            priority = 0
    )
    public void testGetProductos() {
        List<Producto> productos = getProductos(
                FiltroBusqueda
                        .builder()
                        .build()
        );

        assertTrue(
                productos.isEmpty(),
                "La encontraron registros en la tabla de producto."
        );

        productos = getProductos(
                FiltroBusqueda
                        .builder()
                        .estado(Boolean.TRUE)
                        .build()
        );
        assertTrue(
                productos.isEmpty(),
                "La encontraron registros en la tabla de producto."
        );

        productos = getProductos(
                FiltroBusqueda
                        .builder()
                        .estado(Boolean.FALSE)
                        .build()
        );
        assertTrue(
                productos.isEmpty(),
                "La encontraron registros en la tabla de producto."
        );

        productos = getProductos(
                FiltroBusqueda
                        .builder()
                        .filas(Boolean.FALSE)
                        .build()
        );
        assertTrue(
                productos.isEmpty(),
                "La encontraron registros en la tabla de producto."
        );

        productos = getProductos(
                FiltroBusqueda
                        .builder()
                        .filas(Boolean.TRUE)
                        .nPaginaNro(1)
                        .nCantidadFilas(20)
                        .build()
        );
        assertTrue(
                productos.isEmpty(),
                "La encontraron registros en la tabla de producto."
        );
    }

    @Test(
            enabled = true,
            description = """
                          Test que verifica que se puede obtener la descripcion 
                          y la imagen de una categoria.
                          """,
            priority = 1
    )
    public void testGetProductosByCategoria() {
        List<Producto> result = getProductosByCategoria(0, null);
        assertTrue(
                result.isEmpty(),
                "Se obtuvo resultados en la consulta."
        );

        result = getProductosByCategoria(0, true);
        assertTrue(
                result.isEmpty(),
                "Se obtuvo resultados en la consulta."
        );

        result = getProductosByCategoria(0, false);
        assertTrue(
                result.isEmpty(),
                "Se obtuvo resultados en la consulta."
        );
    }

    @Test(
            enabled = true,
            description = "Test encargada de agregar producto al sistema.",
            priority = 2
    )
    public void testAgregarProducto() {
        Resultado resultado = agregarProducto(producto);

        assertTrue(
                resultado.getEstado(),
                ERROR_AL__INSERTAR__PRODUCTO
        );

        assertEquals(
                resultado.getIcono(),
                JOptionPane.INFORMATION_MESSAGE,
                ERROR_AL__INSERTAR__PRODUCTO
        );

        assertEquals(
                resultado.getMensaje(),
                PRODUCTO_AGREGADO_CORRECTAMENTE,
                ERROR_AL__INSERTAR__PRODUCTO
        );

        id_producto = resultado.getId();

        assertThrows(() -> {
            Resultado result = agregarProducto(producto2);

            if (!result.getEstado()) {
                throw new SQLException(ERROR_AL__INSERTAR__PRODUCTO);
            }

            assertFalse(
                    result.getEstado(),
                    ERROR_AL__INSERTAR__PRODUCTO
            );

            assertNotEquals(
                    result.getIcono(),
                    JOptionPane.INFORMATION_MESSAGE,
                    ERROR_AL__INSERTAR__PRODUCTO
            );

            assertNotEquals(
                    result.getMensaje(),
                    PRODUCTO_AGREGADO_CORRECTAMENTE,
                    ERROR_AL__INSERTAR__PRODUCTO
            );
        });
    }

    @Test(
            enabled = true,
            description = """
                          Test que verifica que se puede obtener la descripcion 
                          y la imagen de una categoria.
                          """,
            priority = 3
    )
    public void testGetProductosByCategoria2() {
        List<Producto> result = getProductosByCategoria(
                M_CategoriaNGTest.idCategoria1,
                null
        );

        assertFalse(
                result.isEmpty(),
                "Se obtuvo resultados en la consulta."
        );

        result = getProductosByCategoria(
                M_CategoriaNGTest.idCategoria1,
                true
        );
        assertFalse(
                result.isEmpty(),
                "Se obtuvo resultados en la consulta."
        );

        result = getProductosByCategoria(
                M_CategoriaNGTest.idCategoria1,
                false
        );

        assertTrue(
                result.isEmpty(),
                "Se obtuvo resultados en la consulta."
        );

        result = getProductosByCategoria(
                M_CategoriaNGTest.idCategoria2,
                null
        );

        assertTrue(
                result.isEmpty(),
                "Se obtuvo resultados en la consulta."
        );

        result = getProductosByCategoria(
                M_CategoriaNGTest.idCategoria2,
                true
        );

        assertTrue(
                result.isEmpty(),
                "Se obtuvo resultados en la consulta."
        );

        result = getProductosByCategoria(
                M_CategoriaNGTest.idCategoria2,
                false
        );

        assertTrue(
                result.isEmpty(),
                "Se obtuvo resultados en la consulta."
        );
    }

    @Test(
            enabled = true,
            description = "Test encargado de modificar el producto del sistema. ",
            priority = 3
    )
    public void testModificarProducto() {
        Resultado result = modificarProducto(producto);

        assertTrue(
                result.getEstado(),
                ERROR_AL__MODIFICAR__PRODUCTO
        );

        assertEquals(
                result.getMensaje(),
                PRODUCTO__MODIFICADO__CORRECTAMENTE,
                ERROR_AL__MODIFICAR__PRODUCTO
        );

        assertEquals(
                result.getIcono(),
                JOptionPane.INFORMATION_MESSAGE,
                ERROR_AL__MODIFICAR__PRODUCTO
        );

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

    @Test(
            enabled = true,
            description = "",
            priority = 10
    )
    public void testBorrarProductoPorID() {
        Resultado result = M_Producto.borrarProductoPorID(id_producto);

        assertTrue(
                result.getEstado(),
                OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_EL__PR
        );

        assertEquals(
                result.getIcono(),
                JOptionPane.INFORMATION_MESSAGE,
                OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_EL__PR
        );

        assertEquals(
                result.getMensaje(),
                PRODUCTO__BORRADO__CORRECTAMENTE,
                OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_EL__PR
        );

        M_Categoria.borrarCategoria(
                M_CategoriaNGTest.idCategoria1
        );

        M_Categoria.borrarCategoria(
                M_CategoriaNGTest.idCategoria2
        );
    }
}
