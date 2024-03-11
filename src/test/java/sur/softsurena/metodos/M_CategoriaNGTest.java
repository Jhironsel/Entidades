package sur.softsurena.metodos;

import java.io.File;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Categoria;
import static sur.softsurena.metodos.M_Categoria.OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_LA__CA;
import static sur.softsurena.metodos.M_Categoria.borrarCategoria;
import static sur.softsurena.metodos.M_Producto.generarCodigoBarra;
import sur.softsurena.utilidades.Resultado;

/**
 *
 * @author jhironsel
 */
public class M_CategoriaNGTest {

    public static int idCategoria1, idCategoria2;
    public static String descripcion1, descripcion2;

    public M_CategoriaNGTest() {
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
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        Conexion.getCnn().close();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test(
            enabled = true,
            description = "Permite registrar una categoria al sistema.",
            priority = 0
    )
    public static void testAgregarCategoria() {
        Resultado result = M_Categoria.agregarCategoria(
                Categoria
                        .builder()
                        .descripcion(generarCodigoBarra())
                        .pathImage(new File("Imagenes/ImagenPrueba.png"))
                        .estado(Boolean.TRUE)
                        .build()
        );
        
        assertTrue(
                result.getEstado(), 
                "Problemas en el registro de la categoria."
        );
        idCategoria1 = result.getId();
        
        
        result = M_Categoria.agregarCategoria(
                Categoria
                        .builder()
                        .descripcion(generarCodigoBarra())
                        .pathImage(null)
                        .estado(Boolean.FALSE)
                        .build()
        );
        
        assertTrue(
                result.getEstado(), 
                "Problemas en el registro de la categoria."
        );
        
        idCategoria2 = result.getId();
    }

    @Test(
            enabled = true,
            description = "Permite modificar una categoria del sistema.",
            priority = 1
    )
    public void testModificarCategoria() {
        descripcion1 = generarCodigoBarra();
        Resultado result = M_Categoria.modificarCategoria(Categoria
                        .builder()
                        .id_categoria(idCategoria1)
                        .descripcion(descripcion1)
                        .pathImage(null)
                        .estado(Boolean.FALSE)
                        .build()
        );
        assertTrue(
                result.getEstado(), 
                "No pudo ser modificado el registro de las categorias."
        );
        
        descripcion2 = generarCodigoBarra();
        result = M_Categoria.modificarCategoria(Categoria
                        .builder()
                        .id_categoria(idCategoria2)
                        .descripcion(descripcion2)
                        .pathImage(new File("Imagenes/ImagenPrueba.png"))
                        .estado(Boolean.TRUE)
                        .build()
        );
        assertTrue(
                result.getEstado(), 
                "No pudo ser modificado el registro de las categorias."
        );
    }
    
    @Test(
            enabled = true,
            description = "Pruebas de consultas a las categorias con o sin fotos.",
            priority = 2
    )
    public void testGetCategorias() {
        List result = M_Categoria.getCategorias(null, true);
        assertNotNull(
                result, 
                "Error al obtener todas las categorias con fotos."
        );
        
        result = M_Categoria.getCategorias(null, false);
        assertNotNull(
                result, 
                "Error al obtener todas las categorias sin fotos."
        );
        
        result = M_Categoria.getCategorias(true, true);
        assertNotNull(
                result, 
                "Error al obtener las categorias activas y con foto."
        );
        
        result = M_Categoria.getCategorias(true, false);
        assertNotNull(
                result, 
                "Error al obtener las categorias activas y sin foto."
        );
        
        result = M_Categoria.getCategorias(false, true);
        assertNotNull(
                result, 
                "Error al obtener las categorias inactivas y con foto."
        );
        
        result = M_Categoria.getCategorias(false, false);
        assertNotNull(
                result, 
                "Error al obtener las categorias inactivas y sin foto."
        );
    }

    @Test(
            enabled = true,
            description = "Permite consultar las categorias que contienen registros activos en producto.",
            priority = 3
    )
    public void testGetCategoriaActivas() {
        List result = M_Categoria.getCategoriaActivas();
        assertNotNull(
                result, 
                "La tabla no contiene datos para ser consultada."
        );
    }

    @Test(
            enabled = true,
            description = "Permite verificar que una categoria esta registrada en el sistema.",
            priority = 4
    )
    public static void testExisteCategoria() {
        
        Boolean result = M_Categoria.existeCategoria(descripcion1);
        assertTrue(
                result, 
                "No existe registros de pruebas en la consulta."
        );
        
        result = M_Categoria.existeCategoria(descripcion2);
        assertTrue(
                result, 
                "No existe registros de pruebas en la consulta."
        );
    }

    @Test(
            enabled = true,
            description = "Permite eliminar una gategoria del sistema.",
            priority = 5
    )
    public static void testBorrarCategoria() {
        Resultado result = borrarCategoria(idCategoria1);
        assertTrue(
                result.getEstado(), 
                OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_LA__CA.formatted(idCategoria1)
        );
        
        result = borrarCategoria(idCategoria2);
        assertTrue(
                result.getEstado(), 
                OCURRIO_UN_ERROR_AL_INTENTAR_BORRAR_LA__CA.formatted(idCategoria2)
        );
    }
}
