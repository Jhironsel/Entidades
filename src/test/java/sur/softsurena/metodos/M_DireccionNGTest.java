package sur.softsurena.metodos;

import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Direccion;
import sur.softsurena.entidades.Distrito_municipal;
import sur.softsurena.entidades.Municipio;
import sur.softsurena.entidades.Provincia;
import static sur.softsurena.metodos.M_Direccion.ERROR_AL_INSERTAR_O_MODIFICAR_DIRECCION;
import static sur.softsurena.metodos.M_Direccion.agregarDireccion;
import static sur.softsurena.metodos.M_Direccion.getDireccionByID;
import sur.softsurena.utilidades.Resultado;

public class M_DireccionNGTest {
    
    public M_DireccionNGTest() {
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
            enabled = false,
            description = "Metodo que permite agregar o modicar un direcion de los clientes.",
            priority = 1
    
    )
    public void testAgregarModificarDirecciones() {

        Resultado result = agregarDireccion(
                Direccion
                        .builder()
                        .provincia(
                                Provincia
                                        .builder()
                                        .id(0)
                                        .build()
                        )
                        .municipio(
                                Municipio
                                        .builder()
                                        .id(0)
                                        .build()
                        )
                        .distrito_municipal(Distrito_municipal
                                        .builder()
                                        .id(0)
                                        .build()
                        )
                        .direccion("Insercion de prueba.")
                        .build()
        );

        assertTrue(
                result.getEstado(), 
                ERROR_AL_INSERTAR_O_MODIFICAR_DIRECCION
        );
        
        
        result = agregarDireccion(
                Direccion
                        .builder()
                        .provincia(
                                Provincia
                                        .builder()
                                        .id(2)
                                        .build()
                        )
                        .municipio(
                                Municipio
                                        .builder()
                                        .id(12)
                                        .build()
                        )
                        .distrito_municipal(Distrito_municipal
                                        .builder()
                                        .id(23)
                                        .build()
                        )
                        .direccion("Insercion de prueba 2.")
                        .build()
        );

        assertTrue(
                result.getEstado(),
                ERROR_AL_INSERTAR_O_MODIFICAR_DIRECCION
        );
    }

    @Test(
            enabled = true,
            description = "",
            priority = 1
    
    )
    public void testGetDireccionByID() {
        int id_persona = 0;
        List result = getDireccionByID(id_persona);
        assertTrue(
                result.isEmpty(), 
                "La lista contiene datos"
        );
    }
    
}
