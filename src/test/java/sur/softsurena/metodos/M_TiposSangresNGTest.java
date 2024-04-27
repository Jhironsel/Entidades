package sur.softsurena.metodos;

import lombok.Getter;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;

/**
 * 
 * @author jhironsel
 */
@Getter
public class M_TiposSangresNGTest {

    public M_TiposSangresNGTest() {
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

    /**
     * Test of getTipoSangre method, of class M_TiposSangres.
     */
    @Test(
            enabled = true,
            priority = 0,
            description = """
                          Metodo que permite obtener de la base de datos los 
                          tipos de sangre que existen en el sistema, para ser 
                           asignado a los pacientes.
                          """
    )
    public void testGetTipoSangre() {
        assertFalse(
                M_TiposSangres.getTipoSangre().isEmpty(), 
                "La lista de tipo de sangre no carga correctamente."
        );
    }

}