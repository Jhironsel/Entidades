package sur.softsurena.metodos;

import lombok.Getter;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Gasto;
import sur.softsurena.utilidades.Resultado;

@Getter
public class M_GastoNGTest {
    
    public M_GastoNGTest() {
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
    public void testAgregarGastosPorTurno() {
        Gasto gasto = null;
        Resultado expResult = null;
        Resultado result = M_Gasto.agregarGastosPorTurno(gasto);
        assertEquals(result, expResult);
    }
    
}
