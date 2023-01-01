package sur.softsurena.entidades;

import java.sql.ResultSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jhironsel
 */
public class Datos_nacimientoIT {
    
    public Datos_nacimientoIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of agregarDatosNacimiento method, of class Datos_nacimiento.
     */
    @Test
    public void testAgregarDatosNacimiento() {
        System.out.println("agregarDatosNacimiento");
        Datos_nacimiento dato = null;
        Datos_nacimiento instance = null;
        String expResult = "";
        String result = instance.agregarDatosNacimiento(dato);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAlturaPeso method, of class Datos_nacimiento.
     */
    @Test
    public void testGetAlturaPeso() {
        System.out.println("getAlturaPeso");
        int idPaciente = 0;
        ResultSet expResult = null;
        ResultSet result = Datos_nacimiento.getAlturaPeso(idPaciente);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPCefalico method, of class Datos_nacimiento.
     */
    @Test
    public void testGetPCefalico() {
        System.out.println("getPCefalico");
        int idPaciente = 0;
        ResultSet expResult = null;
        ResultSet result = Datos_nacimiento.getPCefalico(idPaciente);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPesoKG method, of class Datos_nacimiento.
     */
    @Test
    public void testGetPesoKG() {
        System.out.println("getPesoKG");
        int idPaciente = 0;
        ResultSet expResult = null;
        ResultSet result = Datos_nacimiento.getPesoKG(idPaciente);
        assertEquals(expResult, result);
    }

    /**
     * Test of getLongitudOEstatura method, of class Datos_nacimiento.
     */
    @Test
    public void testGetLongitudOEstatura() {
        System.out.println("getLongitudOEstatura");
        int idPaciente = 0;
        ResultSet expResult = null;
        ResultSet result = Datos_nacimiento.getLongitudOEstatura(idPaciente);
        assertEquals(expResult, result);
    }

    /**
     * Test of getLongitudPeso method, of class Datos_nacimiento.
     */
    @Test
    public void testGetLongitudPeso() {
        System.out.println("getLongitudPeso");
        int idPaciente = 0;
        ResultSet expResult = null;
        ResultSet result = Datos_nacimiento.getLongitudPeso(idPaciente);
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Datos_nacimiento.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Datos_nacimiento.Datos_nacimientoBuilder expResult = null;
        Datos_nacimiento.Datos_nacimientoBuilder result = Datos_nacimiento.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdPaciente method, of class Datos_nacimiento.
     */
    @Test
    public void testGetIdPaciente() {
        System.out.println("getIdPaciente");
        Datos_nacimiento instance = null;
        int expResult = 0;
        int result = instance.getIdPaciente();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFecha method, of class Datos_nacimiento.
     */
    @Test
    public void testGetFecha() {
        System.out.println("getFecha");
        Datos_nacimiento instance = null;
        String expResult = "";
        String result = instance.getFecha();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPesoNacimiento method, of class Datos_nacimiento.
     */
    @Test
    public void testGetPesoNacimiento() {
        System.out.println("getPesoNacimiento");
        Datos_nacimiento instance = null;
        String expResult = "";
        String result = instance.getPesoNacimiento();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAltura method, of class Datos_nacimiento.
     */
    @Test
    public void testGetAltura() {
        System.out.println("getAltura");
        Datos_nacimiento instance = null;
        String expResult = "";
        String result = instance.getAltura();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPC method, of class Datos_nacimiento.
     */
    @Test
    public void testGetPC() {
        System.out.println("getPC");
        Datos_nacimiento instance = null;
        String expResult = "";
        String result = instance.getPC();
        assertEquals(expResult, result);
    }

    /**
     * Test of isCesarea method, of class Datos_nacimiento.
     */
    @Test
    public void testIsCesarea() {
        System.out.println("isCesarea");
        Datos_nacimiento instance = null;
        boolean expResult = false;
        boolean result = instance.isCesarea();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTiempoGestacion method, of class Datos_nacimiento.
     */
    @Test
    public void testGetTiempoGestacion() {
        System.out.println("getTiempoGestacion");
        Datos_nacimiento instance = null;
        String expResult = "";
        String result = instance.getTiempoGestacion();
        assertEquals(expResult, result);
    }
    
}
