package sur.softsurena.metodos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;
import sur.softsurena.entidades.Cliente;
import sur.softsurena.entidades.ContactoEmail;
import sur.softsurena.entidades.ContactoTel;
import sur.softsurena.entidades.Direccion;
import sur.softsurena.entidades.Distrito_municipal;
import sur.softsurena.entidades.Generales;
import sur.softsurena.entidades.Municipio;
import sur.softsurena.entidades.Provincia;
import static sur.softsurena.metodos.M_Cliente.CLIENTE_BORRADO_CORRECTAMENTE;
import static sur.softsurena.metodos.M_Cliente.CLIENTE__AGREGADO__CORRECTAMENTE;
import static sur.softsurena.metodos.M_Cliente.CLIENTE__MODIFICADO__CORRECTAMENTE;
import static sur.softsurena.metodos.M_Cliente.agregarCliente;
import static sur.softsurena.metodos.M_Cliente.agregarClienteById;
import static sur.softsurena.metodos.M_Cliente.borrarCliente;
import static sur.softsurena.metodos.M_Cliente.getClientes;
import static sur.softsurena.metodos.M_Cliente.modificarCliente;
import static sur.softsurena.metodos.M_ContactoEmail.generarCorreo;
import static sur.softsurena.metodos.M_ContactoTel.generarTelMovil;
import static sur.softsurena.metodos.M_Generales.generarCedula;
import sur.softsurena.utilidades.FiltroBusqueda;
import sur.softsurena.utilidades.Resultados;

public class M_ClienteNGTest {

    private Integer idCliente;
    private final List<ContactoTel> contactosTels = new ArrayList<>();
    private final List<ContactoEmail> contactosEmails = new ArrayList<>();
    private final List<Direccion> direccion = new ArrayList<>();
    private Cliente cliente;

    public M_ClienteNGTest() {
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
            enabled = false,
            description = "Nos permite agregar clientes al sistema de fecturacion.",
            priority = 0
    )
    public void testAgregarCliente() {
        contactosTels.add(ContactoTel.builder().
                accion('i').
                telefono(generarTelMovil()).
                tipo("Movil").build()
        );
        contactosTels.add(ContactoTel.builder().
                accion('i').
                telefono(generarTelMovil()).
                tipo("Telefono").build()
        );
        contactosEmails.add(ContactoEmail.builder().
                email(generarCorreo()).
                build()
        );
        contactosEmails.add(ContactoEmail.builder().
                email(generarCorreo()).
                build()
        );
        direccion.add(Direccion
                        .builder()
                        .provincia(Provincia
                                        .builder()
                                        .id(27)
                                        .build()
                        )
                        .municipio(Municipio
                                        .builder()
                                        .id(118)
                                        .build()
                        )
                        .distrito_municipal(Distrito_municipal
                                        .builder()
                                        .id(0)
                                        .build()
                        )
                        .direccion("Calle 27 de Febrero")
                        .build()
        );
        cliente = Cliente
                .builder()
                .persona('F')
                .generales(
                        Generales
                                .builder()
                                .estado_civil('S')
                                .cedula(generarCedula())
                                .build()
                )
                .pnombre("Jhironsel")
                .snombre("")
                .apellidos("Diaz Almonte")
                .sexo('F')
                .fecha_nacimiento(new Date(0))
                .estado(Boolean.TRUE)
                .contactosTel(contactosTels)
                .contactosEmail(contactosEmails)
                .direcciones(direccion)
                .build();

        Resultados result = agregarCliente(cliente);

        idCliente = result.getId();

        assertEquals(CLIENTE__AGREGADO__CORRECTAMENTE, result.toString(), CLIENTE__AGREGADO__CORRECTAMENTE);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testAgregarClienteById() {
        int id = 0;
        Resultados expResult = null;
        Resultados result = agregarClienteById(id);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testBorrarCliente() {
        int idCliente = 0;
        Resultados expResult = null;
        Resultados result = borrarCliente(idCliente);
        assertEquals(result, expResult);
    }

    @Test(
            enabled = false,
            description = "",
            priority = 0
    )
    public void testModificarCliente() {
        contactosTels.add(ContactoTel
                        .builder()
                        .accion('i')
                        .telefono(generarTelMovil())
                        .tipo("Movil")
                        .build()
        );

        contactosTels.add(ContactoTel
                        .builder()
                        .accion('i')
                        .telefono(generarTelMovil())
                        .tipo("Telefono")
                        .build()
        );

        contactosEmails.add(ContactoEmail
                        .builder()
                        .email(generarCorreo())
                        .build()
        );

        contactosEmails.add(ContactoEmail
                        .builder()
                        .email(generarCorreo())
                        .build()
        );
        direccion.add(Direccion
                        .builder()
                        .provincia(Provincia
                                        .builder()
                                        .id(27)
                                        .build()
                        )
                        .municipio(Municipio
                                        .builder()
                                        .id(118)
                                        .build()
                        )
                        .distrito_municipal(Distrito_municipal
                                        .builder()
                                        .id(0)
                                        .build()
                        )
                        .direccion("Calle 27 de Febrero")
                        .build()
        );
        cliente = Cliente
                .builder()
                .id_persona(idCliente)
                .persona('F')
                .generales(
                        Generales
                                .builder()
                                .estado_civil('S')
                                .cedula(generarCedula())
                                .build()
                )
                .pnombre("Jhironsel M")
                .snombre("")
                .apellidos("Diaz Almonte")
                .sexo('M')
                .fecha_nacimiento(new Date(0))
                .estado(Boolean.FALSE)
                .contactosTel(contactosTels)
                .contactosEmail(contactosEmails)
                .direcciones(direccion)
                .build();

        Resultados result = modificarCliente(cliente);
        assertEquals(
                CLIENTE__MODIFICADO__CORRECTAMENTE,
                result.toString(),
                CLIENTE__MODIFICADO__CORRECTAMENTE
        );

        result = borrarCliente(idCliente);
        assertEquals(
                CLIENTE_BORRADO_CORRECTAMENTE,
                result.toString(),
                CLIENTE_BORRADO_CORRECTAMENTE
        );
    }

    @Test(
            enabled = true,
            description = "",
            priority = 0
    )
    public void testGetClientes() {
        List<Cliente> clientes = getClientes(
                FiltroBusqueda
                        .builder()
                        .id(0)
                        .criterioBusqueda("^")
                        .build()
        );
        assertEquals(clientes.size(), 1);
        
        Cliente clienteLocal = clientes.get(0);
        
        assertEquals("000-0000000-0", clienteLocal.getGenerales().getCedula());
        assertEquals(Character.valueOf('J'), clienteLocal.getPersona());
        assertEquals("GENERICO", clienteLocal.getPnombre());
        assertEquals("", clienteLocal.getSnombre());
        assertEquals("", clienteLocal.getApellidos());
        assertEquals(Character.valueOf('X'), clienteLocal.getSexo());
        assertEquals("2000-01-01", clienteLocal.getFecha_nacimiento().toString());
        assertEquals(Character.valueOf('X'), clienteLocal.getGenerales().getEstado_civil());
        assertEquals(Boolean.TRUE, clienteLocal.getEstado());
        
        clientes = getClientes(
                FiltroBusqueda
                        .builder()
                        .criterioBusqueda("999-9999999-9")
                        .build()
        );
        
        System.out.println("Cliente: "+clientes);
    }
    
}
