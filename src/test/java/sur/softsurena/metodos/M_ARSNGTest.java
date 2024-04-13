package sur.softsurena.metodos;

import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import lombok.Getter;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.ARS;
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
    public void testAgregarArs() {
        Resultado result = M_ARS.agregarARS(
                ARS
                        .builder()
                        .descripcion(
                                "Prueba-".concat(
                                        M_Generales.generarCedula().substring(6, 11)
                                )
                        )
                        .covertura(
                                BigDecimal.valueOf(60.00)
                        )
                        .estado(Boolean.FALSE)
                        .build()
        );
        
        assertTrue(
                result.getEstado(),
                ERROR_AL_INSERTAR__SEGURO
        );

        assertTrue(
                result.getMensaje().equals(SEGURO_AGREGADO_CORRECTAMENTE)
                , ERROR_AL_INSERTAR__SEGURO
        );
        
        assertTrue(
                result.getIcono() == JOptionPane.INFORMATION_MESSAGE, 
                ERROR_AL_INSERTAR__SEGURO
        );
        
        id_ARS = result.getId();
        
        assertTrue(
                id_ARS > 0,
                ERROR_AL_INSERTAR__SEGURO
        );
    }
    
    @Test(
            enabled = true,
            description = "Test en cargado de verificar la consultas de las ARS del sistema.",
            priority = 1
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
            description = "Test para modificar las ars del sistema.",
            priority = 2
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
                result.getMensaje().equals(SEGURO_MODIFICADO_CORRECTAMENTE),
                ERROR_AL_MODIFICAR_SEGURO
        );
        
        assertTrue(
                result.getIcono() == JOptionPane.INFORMATION_MESSAGE,
                ERROR_AL_MODIFICAR_SEGURO
        );
        
        assertTrue(
                result.getEstado(), 
                ERROR_AL_MODIFICAR_SEGURO
        );
    }

    @Test(
            enabled = true,
            description = "Test en cargado de verificar la consultas de las ARS del sistema.",
            priority = 3
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
            description = "Test en cargado de verificar la consultas de las ARS del sistema.",
            priority = 4
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
            description = "",
            priority = 5
    )
    public void testBorrarSeguro() {
        Resultado result = M_ARS.borrarSeguro(id_ARS);
        assertTrue(
                result.getEstado(),
                ERROR_AL_BORRAR_ARS
        );
    }
    
    @Test(
            enabled = true,
            description = "Test en cargado de verificar la consultas de las ARS del sistema.",
            priority = 6
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
