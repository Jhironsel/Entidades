package sur.softsurena.metodos;

import java.math.BigDecimal;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.ARS;
import static sur.softsurena.metodos.M_ARS.BORRADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_ARS.SEGURO_AGREGADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_ARS.SEGURO_MODIFICADO_CORRECTAMENTE;
import sur.softsurena.utilidades.Resultados;

public class M_ARSNGTest {

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
            description = "",
            priority = 0
    )
    public void testGetARS() {
        List result = M_ARS.getARS();
        assertNotNull(
                result, 
                "La tabla de ARS esta vacia."
        );
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testBorrarSeguro() {

    }

    @Test(
            enabled = true,
            description = "",
            priority = 0
    )
    public void testAgregarSeguro() {
        ARS ars = ARS
                .builder()
                .descripcion("Senasa2")
                .covertura(BigDecimal.valueOf(60.00))
                .estado(Boolean.FALSE)
                .build();

        Resultados result = M_ARS.agregarSeguro(ars);

        assertEquals(result.getMensaje(), SEGURO_AGREGADO_CORRECTAMENTE);

        result = M_ARS.borrarSeguro(result.getId());
        assertEquals(result.getMensaje(), BORRADO_CORRECTAMENTE);
    }

    @Test(
            enabled = true,
            description = "",
            priority = 0
    )
    public void testModificarSeguro() {
        String result = M_ARS.modificarSeguro(
                ARS
                        .builder()
                        .id(1)
                        .descripcion("Senasa")
                        .covertura(BigDecimal.valueOf(60.00))
                        .estado(Boolean.TRUE)
                        .build()
        ).getMensaje();

        assertEquals(result, SEGURO_MODIFICADO_CORRECTAMENTE);
    }

    @Test(
            enabled = true,
            description = "",
            priority = 0
    )
    public void testGetTipoSeguro() {
        List<ARS> result = M_ARS.getTipoSeguro();
        assertNotNull(result, "La tabla de ARS esta vacia.");

        ARS ars = ARS
                .builder()
                .id(0)
                .descripcion("Seleccione una ARS")
                .build();
        assertListContainsObject(result, ars, "La tabla de ARS no contiene registro 0");

    }
}
