package sur.softsurena.metodos;

import java.util.List;
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
public class M_Carton_BingoNGTest {

    public M_Carton_BingoNGTest() {
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
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testCheckBingo() {
        boolean expResult = false;
        boolean result = M_Carton_Bingo.checkBingo();
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGenerarCarton() {
        List expResult = null;
        List result = M_Carton_Bingo.generarCarton();
        assertEquals(result, expResult);
    }
    @Test(
            enabled = false,
            priority = 0,
            description = ""
    )
    public void testGenerarCarton_int() {
        int cantidad = 0;
        Boolean expResult = null;
        Boolean result = M_Carton_Bingo.generarCarton(cantidad);
        assertEquals(result, expResult);
    }

}