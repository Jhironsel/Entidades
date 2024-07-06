package sur.softsurena.metodos;

import java.sql.ResultSet;
import javax.swing.JOptionPane;
import lombok.Getter;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static sur.softsurena.metodos.M_D_Guia_Vigilancia.GUIA_DE__DESARROLLO_AGREGADA_CORRECTAMENTE;
import sur.softsurena.utilidades.Resultado;

/**
 *
 * @author jhironsel
 */
@Getter
public class M_D_Guia_VigilanciaNGTest {

    public M_D_Guia_VigilanciaNGTest() {
    }

    @BeforeClass
    public void setUpClass() throws Exception {
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
    public void testAgregarGuiaVigilancia() {
        //TODO Obtener los valores de siguiente.
        int idGVD = 0;
        int idPaciente = 0;
        Resultado agregarGuiaVigilancia = M_D_Guia_Vigilancia.update(idGVD, idPaciente);
        assertEquals(
                agregarGuiaVigilancia, 
                Resultado
                    .builder()
                    .mensaje(GUIA_DE__DESARROLLO_AGREGADA_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build()
        );
    }
}