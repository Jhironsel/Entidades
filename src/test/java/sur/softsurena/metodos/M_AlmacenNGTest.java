package sur.softsurena.metodos;

import java.util.List;
import javax.swing.JOptionPane;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Almacen;
import sur.softsurena.utilidades.Resultados;
import static sur.softsurena.metodos.M_Almacen.ALMACEN_AGREGADO_CORRECTAMENTE;

public class M_AlmacenNGTest {

    public M_AlmacenNGTest() {
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
        assertTrue("Error al conectarse...", Conexion.verificar().getEstado());
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
    public void testGetAlmacenesList() {
        List result = M_Almacen.getAlmacenesList(0, "^");
        assertNotEquals(result, null, "La tabla de almacen esta vacia.");

        result = M_Almacen.getAlmacenesList(-1, "Seleccione");
        assertNotEquals(result, null, "La tabla de almacen esta vacia.");
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testAgregarAlmacen() {
        Resultados expResult = Resultados
                .builder()
                .mensaje(ALMACEN_AGREGADO_CORRECTAMENTE)
                .icono(JOptionPane.INFORMATION_MESSAGE)
                .estado(Boolean.TRUE)
                .build();
        
        Resultados result = M_Almacen.agregarAlmacen(Almacen
                .builder()
                .nombre("Registro prueba")
                .ubicacion("Debe de describir la ubicacion del almacen.")
                .estado(Boolean.TRUE)
                .build()
        );
        assertEquals(result.getMensaje(), expResult.getMensaje());
        assertEquals(result.getIcono(), expResult.getIcono());
        assertEquals(result.getEstado(), expResult.getEstado());
    }
}
