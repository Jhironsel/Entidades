package sur.softsurena.metodos;

import java.math.BigDecimal;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.ARS;
import static sur.softsurena.metodos.M_ARS.ERROR_AL_BORRAR_ARS;
import static sur.softsurena.metodos.M_ARS.ERROR_AL_CONSULTAR_LA_VISTA_V_ARS_DEL;
import static sur.softsurena.metodos.M_ARS.ERROR_AL_INSERTAR__SEGURO;
import static sur.softsurena.metodos.M_ARS.ERROR_AL_MODIFICAR_SEGURO;
import sur.softsurena.utilidades.FiltroBusqueda;
import sur.softsurena.utilidades.Resultado;

public class M_ARSNGTest {

    private int id_ARS;

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
            description = "Test que verifica que un ARS puede ser insertardo en el sistema.",
            priority = 0
    )
    public void testAgregarSeguro() {
        Resultado result = M_ARS.agregarSeguro(
                ARS
                        .builder()
                        .descripcion("Senasa2")
                        .covertura(BigDecimal.valueOf(60.00))
                        .estado(Boolean.FALSE)
                        .build()
        );
        assertTrue(
                result.getEstado(),
                ERROR_AL_INSERTAR__SEGURO
        );

        id_ARS = result.getId();
        
        List result2 = M_ARS.getARS(
                FiltroBusqueda
                        .builder()
                        .estado(Boolean.FALSE)
                        .build()
        );
        assertFalse(
                result2.isEmpty(),
                ERROR_AL_CONSULTAR_LA_VISTA_V_ARS_DEL
        );
    }
    
    @Test(
            enabled = true,
            description = "Test para modificar las ars del sistema.",
            priority = 1
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
        assertTrue(
                result.getEstado(), 
                ERROR_AL_MODIFICAR_SEGURO
        );
        
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
            description = "Test en cargado de verificar la consultas de las ARS del sistema.",
            priority = 2
    )
    public void testGetARS() {
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
            description = "",
            priority = 3
    )
    public void testBorrarSeguro() {
        Resultado result = M_ARS.borrarSeguro(id_ARS);
        assertTrue(
                result.getEstado(),
                ERROR_AL_BORRAR_ARS
        );
    }
}
