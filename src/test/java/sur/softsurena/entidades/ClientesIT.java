package sur.softsurena.entidades;

import java.sql.ResultSet;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
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
public class ClientesIT {
    
    public ClientesIT() {
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
     * Test of agregarCliente method, of class Clientes.
     */
    @Test
    public void testAgregarCliente() {
        System.out.println("agregarCliente");
        Clientes c = null;
        List<ContactosTel> ct = null;
        List<ContactosEmail> ce = null;
        Resultados expResult = null;
        Resultados result = Clientes.agregarCliente(c, ct, ce);
        assertEquals(expResult, result);
    }

    /**
     * Test of modificarCliente method, of class Clientes.
     */
    @Test
    public void testModificarCliente() {
        System.out.println("modificarCliente");
        Clientes c = null;
        List<ContactosTel> ct = null;
        List<ContactosEmail> ce = null;
        Resultados expResult = null;
        Resultados result = Clientes.modificarCliente(c, ct, ce);
        assertEquals(expResult, result);
    }

    /**
     * Test of borrarCliente method, of class Clientes.
     */
    @Test
    public void testBorrarCliente() {
        System.out.println("borrarCliente");
        int idCliente = 0;
        boolean estado = false;
        Resultados expResult = null;
        Resultados result = Clientes.borrarCliente(idCliente, estado);
        assertEquals(expResult, result);
    }

    /**
     * Test of getClientesCombo method, of class Clientes.
     */
    @Test
    public void testGetClientesCombo() {
        System.out.println("getClientesCombo");
        ResultSet expResult = null;
        ResultSet result = Clientes.getClientesCombo();
        assertEquals(expResult, result);
    }

    /**
     * Test of existeCliente method, of class Clientes.
     */
    @Test
    public void testExisteCliente() {
        System.out.println("existeCliente");
        String cedula = "";
        Integer expResult = null;
        Integer result = Clientes.existeCliente(cedula);
        assertEquals(expResult, result);
    }

    /**
     * Test of getClientesTablaSB method, of class Clientes.
     */
    @Test
    public void testGetClientesTablaSB() {
        System.out.println("getClientesTablaSB");
        DefaultTableModel expResult = null;
        DefaultTableModel result = Clientes.getClientesTablaSB();
        assertEquals(expResult, result);
    }

    /**
     * Test of getClientesTablaSBCombo method, of class Clientes.
     */
    @Test
    public void testGetClientesTablaSBCombo() {
        System.out.println("getClientesTablaSBCombo");
        JComboBox<Clientes> cmbCliente = null;
        Clientes.getClientesTablaSBCombo(cmbCliente);
    }

    /**
     * Test of getClienteByID method, of class Clientes.
     */
    @Test
    public void testGetClienteByID() {
        System.out.println("getClienteByID");
        int id = 0;
        Clientes expResult = null;
        Clientes result = Clientes.getClienteByID(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getClientesFiltrados method, of class Clientes.
     */
    @Test
    public void testGetClientesFiltrados() {
        System.out.println("getClientesFiltrados");
        String criterio = "";
        String filtro = "";
        ResultSet expResult = null;
        ResultSet result = Clientes.getClientesFiltrados(criterio, filtro);
        assertEquals(expResult, result);
    }

    /**
     * Test of getClientesCobros method, of class Clientes.
     */
    @Test
    public void testGetClientesCobros() {
        System.out.println("getClientesCobros");
        ResultSet expResult = null;
        ResultSet result = Clientes.getClientesCobros();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCobrosClientesFactura method, of class Clientes.
     */
    @Test
    public void testGetCobrosClientesFactura() {
        System.out.println("getCobrosClientesFactura");
        String idCliente = "";
        ResultSet expResult = null;
        ResultSet result = Clientes.getCobrosClientesFactura(idCliente);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeudaClientes method, of class Clientes.
     */
    @Test
    public void testGetDeudaClientes() {
        System.out.println("getDeudaClientes");
        String estado = "";
        ResultSet expResult = null;
        ResultSet result = Clientes.getDeudaClientes(estado);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Clientes.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Clientes instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of builder method, of class Clientes.
     */
    @Test
    public void testBuilder() {
        System.out.println("builder");
        Clientes.ClientesBuilder expResult = null;
        Clientes.ClientesBuilder result = Clientes.builder();
        assertEquals(expResult, result);
    }
    
}
