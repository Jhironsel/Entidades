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
public class M_Distrito_MunicipalNGTest {

    public M_Distrito_MunicipalNGTest() {
    }

    @BeforeClass
    public void setUpClass() throws Exception {
        Conexion.getInstance(
                "sysdba",
                "1",
                "SoftSurena.db",
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
            priority = 0,
            description = """
                          Permite verificar la consultas a la bd de los datos de
                          los distritos municipales de rd. 
                          """
    )
    public void testGetDistritosMunicipales() {
        List result = M_Distrito_Municipal.getDistritosMunicipales(0);
        assertFalse(
                result.isEmpty(),
                "Registro principal no existe."
        );

        result = M_Distrito_Municipal.getDistritosMunicipales(27);
        assertFalse(
                result.isEmpty(),
                "Registros Distritos municipales de San Juan no encontrado."
        );
    }

}
