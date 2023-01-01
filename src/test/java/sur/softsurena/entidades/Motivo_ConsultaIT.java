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
public class Motivo_ConsultaIT {
    
    public Motivo_ConsultaIT() {
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
     * Test of borrarMotivoConsulta method, of class Motivo_Consulta.
     */
    @Test
    public void testBorrarMotivoConsulta() {
        System.out.println("borrarMotivoConsulta");
        Motivo_Consulta mc = null;
        String expResult = "";
        String result = Motivo_Consulta.borrarMotivoConsulta(mc);
        assertEquals(expResult, result);
    }

    /**
     * Test of agregarMotivo method, of class Motivo_Consulta.
     */
    @Test
    public void testAgregarMotivo() {
        System.out.println("agregarMotivo");
        String m = "";
        boolean expResult = false;
        boolean result = Motivo_Consulta.agregarMotivo(m);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMotivo method, of class Motivo_Consulta.
     */
    @Test
    public void testGetMotivo() {
        System.out.println("getMotivo");
        ResultSet expResult = null;
        ResultSet result = Motivo_Consulta.getMotivo();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Motivo_Consulta.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Motivo_Consulta.Motivo_ConsultaBuilder expResult = null;
        Motivo_Consulta.Motivo_ConsultaBuilder result = Motivo_Consulta.builder();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Motivo_Consulta.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Motivo_Consulta instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescripcion method, of class Motivo_Consulta.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        Motivo_Consulta instance = null;
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
    }
    
}
