package sur.softsurena.entidades;

import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;

public class DireccionesNGTest {

    public DireccionesNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        final String user = "sysdba";
        final String clave = "1";
        final String pathBaseDatos = "BaseDeDatos.db";
        final String dominio = "localhost";
        final String puerto = "3050";
        Conexion.getInstance(user, clave, pathBaseDatos, dominio, puerto)
                .verificar();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of agregarDirecciones method, of class Direcciones.
     */
    @Test
    public void testAgregarDirecciones() {
        int id_persona = 0;
        List<Direcciones> direcciones = new ArrayList<>();
        direcciones.add(
                Direcciones
                        .builder()
                        .provincia(
                                Provincias
                                        .builder()
                                        .id(0)
                                        .build()
                        )
                        .municipio(
                                Municipios
                                        .builder()
                                        .id(0)
                                        .build()
                        )
                        .distrito_municipal(
                                Distritos_municipales
                                        .builder()
                                        .id(0)
                                        .build()
                        )
                        .direccion("Insercion de prueba.")
                        .build()
        );
        direcciones.add(
                Direcciones
                        .builder()
                        .provincia(
                                Provincias
                                        .builder()
                                        .id(2)
                                        .build()
                        )
                        .municipio(
                                Municipios
                                        .builder()
                                        .id(12)
                                        .build()
                        )
                        .distrito_municipal(
                                Distritos_municipales
                                        .builder()
                                        .id(2)
                                        .build()
                        )
                        .direccion("Insercion de prueba 2.")
                        .build()
        );

        boolean expResult = true;

        boolean result = Direcciones.agregarDirecciones(id_persona, direcciones);

        assertEquals(result, expResult);
    }

    /**
     * Test of modificarDireccion method, of class Direcciones.
     */
    @Test
    public void testModificarDireccion() {
        int id = 0;
        Direcciones direccion = Direcciones
                .builder()
                .id_persona(0)
                .provincia(
                        Provincias
                                .builder()
                                .id(0)
                                .build()
                )
                .municipio(
                        Municipios
                                .builder()
                                .id(0)
                                .build()
                )
                .distrito_municipal(
                        Distritos_municipales
                                .builder()
                                .id(0)
                                .build()
                )
                .estado(Boolean.FALSE)
                .por_defecto(Boolean.FALSE)
                .direccion("Modificacion de prueba.")
                .build();
        boolean expResult = true;
        boolean result = Direcciones.modificarDireccion(id, direccion);
        assertEquals(result, expResult);
    }
//
//    /**
//     * Test of getDireccionByID method, of class Direcciones.
//     */
//    @Test
//    public void testGetDireccionByID() {
//        int id_persona = 0;
//        List expResult = null;
//        List result = Direcciones.getDireccionByID(id_persona);
//        assertEquals(result, expResult);
//    }
//
//    /**
//     * Test of toString method, of class Direcciones.
//     */
//    @Test
//    public void testToString() {
//        Direcciones instance = null;
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(result, expResult);
//    }
//
//    /**
//     * Test of getId method, of class Direcciones.
//     */
//    @Test
//    public void testGetId() {
//        Direcciones instance = null;
//        int expResult = 0;
//        int result = instance.getId();
//        assertEquals(result, expResult);
//    }
//
//    /**
//     * Test of getId_persona method, of class Direcciones.
//     */
//    @Test
//    public void testGetId_persona() {
//        Direcciones instance = null;
//        int expResult = 0;
//        int result = instance.getId_persona();
//        assertEquals(result, expResult);
//    }
//
//    /**
//     * Test of getProvincia method, of class Direcciones.
//     */
//    @Test
//    public void testGetProvincia() {
//        Direcciones instance = null;
//        Provincias expResult = null;
//        Provincias result = instance.getProvincia();
//        assertEquals(result, expResult);
//    }
//
//    /**
//     * Test of getMunicipio method, of class Direcciones.
//     */
//    @Test
//    public void testGetMunicipio() {
//        Direcciones instance = null;
//        Municipios expResult = null;
//        Municipios result = instance.getMunicipio();
//        assertEquals(result, expResult);
//    }
//
//    /**
//     * Test of getDistrito_municipal method, of class Direcciones.
//     */
//    @Test
//    public void testGetDistrito_municipal() {
//        Direcciones instance = null;
//        Distritos_municipales expResult = null;
//        Distritos_municipales result = instance.getDistrito_municipal();
//        assertEquals(result, expResult);
//    }
//
//    /**
//     * Test of getCodigo_postal method, of class Direcciones.
//     */
//    @Test
//    public void testGetCodigo_postal() {
//        Direcciones instance = null;
//        Codigo_Postal expResult = null;
//        Codigo_Postal result = instance.getCodigo_postal();
//        assertEquals(result, expResult);
//    }
//
//    /**
//     * Test of getDireccion method, of class Direcciones.
//     */
//    @Test
//    public void testGetDireccion() {
//        Direcciones instance = null;
//        String expResult = "";
//        String result = instance.getDireccion();
//        assertEquals(result, expResult);
//    }
//
//    /**
//     * Test of getFecha method, of class Direcciones.
//     */
//    @Test
//    public void testGetFecha() {
//        Direcciones instance = null;
//        Date expResult = null;
//        Date result = instance.getFecha();
//        assertEquals(result, expResult);
//    }
//
//    /**
//     * Test of getEstado method, of class Direcciones.
//     */
//    @Test
//    public void testGetEstado() {
//        Direcciones instance = null;
//        Boolean expResult = null;
//        Boolean result = instance.getEstado();
//        assertEquals(result, expResult);
//    }
//
//    /**
//     * Test of getPor_defecto method, of class Direcciones.
//     */
//    @Test
//    public void testGetPor_defecto() {
//        Direcciones instance = null;
//        Boolean expResult = null;
//        Boolean result = instance.getPor_defecto();
//        assertEquals(result, expResult);
//    }
//
//    /**
//     * Test of getAccion method, of class Direcciones.
//     */
//    @Test
//    public void testGetAccion() {
//        Direcciones instance = null;
//        char expResult = ' ';
//        char result = instance.getAccion();
//        assertEquals(result, expResult);
//    }

}
