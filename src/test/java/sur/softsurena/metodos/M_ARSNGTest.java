package sur.softsurena.metodos;

import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import lombok.Getter;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.ARS;
import static sur.softsurena.metodos.M_ARS.BORRADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_ARS.ERROR_AL_BORRAR_ARS;
import static sur.softsurena.metodos.M_ARS.ERROR_AL_CONSULTAR_LA_VISTA_V_ARS_DEL;
import static sur.softsurena.metodos.M_ARS.ERROR_AL_INSERTAR__SEGURO;
import static sur.softsurena.metodos.M_ARS.ERROR_AL_MODIFICAR_SEGURO;
import static sur.softsurena.metodos.M_ARS.SEGURO_AGREGADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_ARS.SEGURO_MODIFICADO_CORRECTAMENTE;
import sur.softsurena.utilidades.FiltroBusqueda;
import sur.softsurena.utilidades.Resultado;

@Getter
public class M_ARSNGTest {

    private Integer id_ARS;

    public M_ARSNGTest() {
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
            priority = 0,
            description = """
                          Test que verifica que un ARS puede ser insertardo en
                          el sistema.
                          """
    )
    public void testAgregarArs() {
        Resultado result = M_ARS.agregarARS(
                ARS
                        .builder()
                        .descripcion(
                                "TestARS_".concat(
                                        M_Generales.generarCedula().substring(6, 11)
                                )
                        )
                        .covertura(
                                BigDecimal.valueOf(60.00)
                        )
                        .estado(Boolean.FALSE)
                        .build()
        );

        assertEquals(
                result,
                Resultado
                        .builder()
                        .mensaje(SEGURO_AGREGADO_CORRECTAMENTE)
                        .icono(JOptionPane.INFORMATION_MESSAGE)
                        .estado(Boolean.TRUE)
                        .build(),
                ERROR_AL_INSERTAR__SEGURO
        );

        assertTrue(
                result.getId() > 0,
                ERROR_AL_INSERTAR__SEGURO
        );

        id_ARS = result.getId();
    }

    @Test(
            enabled = true,
            priority = 1,
            description = """
                          Test en cargado de verificar la consultas de las 
                          ARS del sistema.
                          """
    )
    public void testGetARS() {
        List result = M_ARS.getARS(
                FiltroBusqueda
                        .builder()
                        .estado(Boolean.FALSE)
                        .build()
        );

        assertFalse(
                result.isEmpty(),
                ERROR_AL_CONSULTAR_LA_VISTA_V_ARS_DEL
        );

    }

    @Test(
            enabled = true,
            priority = 2,
            description = "Test para modificar las ars del sistema."
    )
    public void testModificarSeguro() {
        Resultado result = M_ARS.modificarSeguro(
                ARS
                        .builder()
                        .id(id_ARS)
                        .descripcion("Senasa3")
                        .covertura(BigDecimal.valueOf(50.00))
                        .estado(Boolean.TRUE)
                        .build()
        );

        assertEquals(
                result,
                Resultado
                    .builder()
                    .mensaje(SEGURO_MODIFICADO_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build(),
                ERROR_AL_MODIFICAR_SEGURO
        );
    }

    @Test(
            enabled = true,
            priority = 3,
            description = "Test en cargado de verificar la consultas de las "
                    + "ARS del sistema."
    )
    public void testGetARS2() {
        List result2 = M_ARS.getARS(
                FiltroBusqueda
                        .builder()
                        .estado(Boolean.TRUE)
                        .build()
        );
        assertFalse(
                result2.isEmpty(),
                ERROR_AL_CONSULTAR_LA_VISTA_V_ARS_DEL
        );

    }

    @Test(
            enabled = true,
            priority = 4,
            description = """
                          Test en cargado de verificar la consultas de las
                          ARS del sistema.
                          """
    )
    public void testGetARS3() {
        List result = M_ARS.getARS(
                FiltroBusqueda
                        .builder()
                        .build()
        );
        
        assertFalse(
                result.isEmpty(),
                ERROR_AL_CONSULTAR_LA_VISTA_V_ARS_DEL
        );
    }

    @Test(
            enabled = true,
            priority = 5,
            description = """
                          Test que permite eliminar un registro de ars del
                          sistema.
                          """
    )
    public void testBorrarSeguro() {
        
        Resultado result = M_ARS.borrarSeguro(id_ARS);
                
        assertEquals(
                result, 
                Resultado
                    .builder()
                    .mensaje(BORRADO_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build(),
                ERROR_AL_BORRAR_ARS
        );
        
    }

    @Test(
            enabled = true,
            priority = 6,
            description = """
                          Test en cargado de verificar la consultas de las 
                          ARS del sistema este vacia.
                          """
    )
    public void testGetARS4() {
        List result = M_ARS.getARS(
                FiltroBusqueda
                        .builder()
                        .build()
        );

        assertTrue(
                result.isEmpty(),
                ERROR_AL_CONSULTAR_LA_VISTA_V_ARS_DEL
        );
    }
}
