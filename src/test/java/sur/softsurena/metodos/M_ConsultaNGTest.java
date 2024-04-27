package sur.softsurena.metodos;

import java.sql.ResultSet;
import lombok.Getter;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Consulta;
import sur.softsurena.utilidades.Resultado;

@Getter
public class M_ConsultaNGTest {
    
    public M_ConsultaNGTest() {
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
                "Error al conectarse...", 
                Conexion.verificar().getEstado()
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
            description = "",
            priority = 0
    )
    public void testAgregarConsulta() {
        String expResult = "";
        Resultado result = M_Consulta.agregarConsulta(
                Consulta
                        .builder()
                        .id_paciente(0)
                        .id_control_consulta(0)
                        .turno(0)
                        .build()
        );
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testGetConsulta() {
        String fecha = "";
        ResultSet expResult = null;
        ResultSet result = M_Consulta.getConsulta(fecha);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testGetControlConsulta() {
        String fecha = "";
        boolean expResult = false;
        boolean result = M_Consulta.getControlConsulta(fecha);
        assertEquals(result, expResult);
    }
}
