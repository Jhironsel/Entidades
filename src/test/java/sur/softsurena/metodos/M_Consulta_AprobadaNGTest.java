package sur.softsurena.metodos;

import java.math.BigDecimal;
import javax.swing.JOptionPane;
import lombok.Getter;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Consulta_Aprobada;
import static sur.softsurena.metodos.M_Consulta_Aprobada.CONSULTA__APROBADA_CORRECTAMENTE;
import sur.softsurena.utilidades.Resultado;

/**
 *
 * @author jhironsel
 */
@Getter
public class M_Consulta_AprobadaNGTest {

    public M_Consulta_AprobadaNGTest() {
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
            enabled = false,
            priority = 0,
            description = """
                          Esta prueba esta en desarrollo.
                          """
    )
    public void testAgregarConsultaVerificada() {
        
        Resultado result = M_Consulta_Aprobada.agregarConsultaAprovada(
                Consulta_Aprobada
                        .builder()
                        .codAutorizacion(
                                M_ContactoTel.generarTelMovil().substring(4, 11)
                        )
                        .costo(BigDecimal.TEN)
                        .descuento(BigDecimal.TEN)
                        .build()
        );
        
        assertEquals(
                result, 
                Resultado
                        .builder()
                        .mensaje(CONSULTA__APROBADA_CORRECTAMENTE)
                        .icono(JOptionPane.INFORMATION_MESSAGE)
                        .estado(Boolean.TRUE)
                        .build()
        );
    }

}
