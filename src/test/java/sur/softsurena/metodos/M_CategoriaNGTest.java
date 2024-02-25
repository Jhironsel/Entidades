package sur.softsurena.metodos;

import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Categoria;
import sur.softsurena.utilidades.Resultados;

/**
 *
 * @author jhironsel
 */
public class M_CategoriaNGTest {
    
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
        assertTrue("Error al conectarse...", Conexion.verificar().getEstado());
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
            enabled = false,
            description = "",
            priority = 0
    )
    public void testAgregarCategoria() {
        Categoria c = null;
        Resultados expResult = null;
        Resultados result = M_Categoria.agregarCategoria(c);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testModificarCategoria() {
        Categoria c = null;
        Resultados expResult = null;
        Resultados result = M_Categoria.modificarCategoria(c);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testBorrarCategoria() {
        int idCategoria = 0;
        Resultados expResult = null;
        Resultados result = M_Categoria.borrarCategoria(idCategoria);
        assertEquals(result, expResult);
    }


    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testGetCategorias() {
        List expResult = null;
        List result = M_Categoria.getCategorias(true, true);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testGetCategoriaActivas() {
        List expResult = null;
        List result = M_Categoria.getCategoriaActivas();
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testExisteCategoria() {
        String descripcion = "";
        Boolean expResult = null;
        Boolean result = M_Categoria.existeCategoria(descripcion);
        assertEquals(result, expResult);
    }
    
}
