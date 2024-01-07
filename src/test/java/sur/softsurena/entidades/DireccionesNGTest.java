package sur.softsurena.entidades;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
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
        Resultados<Object> verificar = Conexion.getInstance(user, clave, pathBaseDatos, dominio, puerto).verificar();
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
                                        .id(23)
                                        .build()
                        )
                        .direccion("Insercion de prueba 2.")
                        .build()
        );

        boolean expResult = true;

        //boolean result = Direcciones.agregarDirecciones(id_persona, direcciones);

        //assertEquals(result, expResult);
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
        //boolean result = Direcciones.modificarDireccion(id, direccion);
        //assertEquals(result, expResult);
    }

    /**
     * Test of agregarModificarDirecciones method, of class Direcciones.
     */
    @Test
    public void testAgregarModificarDirecciones() {
        System.out.println("agregarModificarDirecciones");
        Integer id_persona = null;
        List<Direcciones> direcciones = null;
        boolean expResult = false;
        boolean result = Direcciones.agregarModificarDirecciones(id_persona, direcciones);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDireccionByID method, of class Direcciones.
     */
    @Test
    public void testGetDireccionByID() {
        System.out.println("getDireccionByID");
        int id_persona = 0;
        List expResult = null;
        List result = Direcciones.getDireccionByID(id_persona);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Direcciones.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Direcciones instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of builder method, of class Direcciones.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Direcciones.DireccionesBuilder expResult = null;
        Direcciones.DireccionesBuilder result = Direcciones.builder();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Direcciones.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Direcciones instance = null;
        Integer expResult = null;
        Integer result = instance.getId();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId_persona method, of class Direcciones.
     */
    @Test
    public void testGetId_persona() {
        System.out.println("getId_persona");
        Direcciones instance = null;
        Integer expResult = null;
        Integer result = instance.getId_persona();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProvincia method, of class Direcciones.
     */
    @Test
    public void testGetProvincia() {
        System.out.println("getProvincia");
        Direcciones instance = null;
        Provincias expResult = null;
        Provincias result = instance.getProvincia();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMunicipio method, of class Direcciones.
     */
    @Test
    public void testGetMunicipio() {
        System.out.println("getMunicipio");
        Direcciones instance = null;
        Municipios expResult = null;
        Municipios result = instance.getMunicipio();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDistrito_municipal method, of class Direcciones.
     */
    @Test
    public void testGetDistrito_municipal() {
        System.out.println("getDistrito_municipal");
        Direcciones instance = null;
        Distritos_municipales expResult = null;
        Distritos_municipales result = instance.getDistrito_municipal();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCodigo_postal method, of class Direcciones.
     */
    @Test
    public void testGetCodigo_postal() {
        System.out.println("getCodigo_postal");
        Direcciones instance = null;
        Codigo_Postal expResult = null;
        Codigo_Postal result = instance.getCodigo_postal();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDireccion method, of class Direcciones.
     */
    @Test
    public void testGetDireccion() {
        System.out.println("getDireccion");
        Direcciones instance = null;
        String expResult = "";
        String result = instance.getDireccion();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFecha method, of class Direcciones.
     */
    @Test
    public void testGetFecha() {
        System.out.println("getFecha");
        Direcciones instance = null;
        Date expResult = null;
        Date result = instance.getFecha();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEstado method, of class Direcciones.
     */
    @Test
    public void testGetEstado() {
        System.out.println("getEstado");
        Direcciones instance = null;
        Boolean expResult = null;
        Boolean result = instance.getEstado();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPor_defecto method, of class Direcciones.
     */
    @Test
    public void testGetPor_defecto() {
        System.out.println("getPor_defecto");
        Direcciones instance = null;
        Boolean expResult = null;
        Boolean result = instance.getPor_defecto();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccion method, of class Direcciones.
     */
    @Test
    public void testGetAccion() {
        System.out.println("getAccion");
        Direcciones instance = null;
        Character expResult = null;
        Character result = instance.getAccion();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
