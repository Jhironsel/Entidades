package sur.softsurena.metodos;

import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Almacen;
import sur.softsurena.utilidades.Resultados;

public class M_AlmacenNGTest {

    private int idAlmacen, idAlmacen2;

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
            description = "Permite verificar si las tabla estan vacia.",
            priority = 0
    )
    public void testGetAlmacenesList() {
        List result = M_Almacen.getAlmacenesList(0, "^+-*/");
        assertTrue(
                result.isEmpty(), 
                "La tabla de almacen NO esta vacia."
        );

        result = M_Almacen.getAlmacenesList(-1, "*");
        assertTrue(
                result.isEmpty(), 
                "La tabla de almacen NO esta vacia."
        );
    }

    @Test(
            enabled = true,
            description = "Realiza el proceso de registro de un almacen de prueba.",
            priority = 1
    )
    public void testAgregarAlmacen() {
        Resultados result = M_Almacen.agregarAlmacen(
                Almacen
                        .builder()
                        .nombre("Registro prueba")
                        .ubicacion("Debe de describir la ubicacion del almacen.")
                        .estado(Boolean.TRUE)
                        .build()
        );

        assertNotNull(result, "Error al registrar almacen al sistema.");

        idAlmacen = result.getId();

        result = M_Almacen.agregarAlmacen(Almacen
                .builder()
                .nombre("Texto de prueba")
                .ubicacion("Debe de describir la ubicacion del almacen.")
                .estado(Boolean.FALSE)
                .build()
        );

        assertNotNull(result, "Error al registrar almacen 2 al sistema.");

        idAlmacen2 = result.getId();
    }

    @Test(
            enabled = true,
            description = "Permite verificar si las tabla estan vacia.",
            priority = 2
    )
    public void testGetAlmacenes2List() {
        List result = M_Almacen.getAlmacenesList(idAlmacen, "^+-*/");
        assertFalse(result.isEmpty(), "La tabla de almacen NO esta vacia.");

        result = M_Almacen.getAlmacenesList(idAlmacen2, "Seleccione");
        assertFalse(result.isEmpty(), "La tabla de almacen NO esta vacia.");

        result = M_Almacen.getAlmacenesList(-1, "Registro");
        assertFalse(result.isEmpty(), "La tabla de almacen NO esta vacia.");

        result = M_Almacen.getAlmacenesList(-1, "Texto");
        assertFalse(result.isEmpty(), "La tabla de almacen NO esta vacia.");
    }

    @Test(
            enabled = true,
            description = "Prueba que actualiza el registro de los almacenes del sistema.",
            priority = 3
    )
    public void testActualizarAlmacen() {

        Resultados result = M_Almacen.actualizarAlmacen(
                Almacen
                        .builder()
                        .id(idAlmacen)
                        .nombre("Registro ha sido actualizado")
                        .ubicacion("Ha sido movido.")
                        .estado(Boolean.FALSE)
                        .build()
        );
        assertNotNull(result);
    }

    @Test(
            enabled = true,
            description = "Prueba que elimina el registro de los almacenes del sistema.",
            priority = 4
    )
    public void testEliminarAlmacen() {
        Resultados result = M_Almacen.eliminarAlmacen(idAlmacen);
        assertNotNull(result, "No pudo eliminarse el registro del almacen.");

        result = M_Almacen.eliminarAlmacen(idAlmacen2);
        assertNotNull(result, "No pudo eliminarse el registro del almacen 2.");
    }
}
