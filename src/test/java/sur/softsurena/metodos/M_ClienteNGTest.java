package sur.softsurena.metodos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import lombok.Getter;
import static org.testng.Assert.*;
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
import static sur.softsurena.metodos.M_Cliente.CLIENTE_NO_PUEDE_SER_BORRADO;
import static sur.softsurena.metodos.M_Cliente.CLIENTE__AGREGADO__CORRECTAMENTE;
import static sur.softsurena.metodos.M_Cliente.CLIENTE__MODIFICADO__CORRECTAMENTE;
import static sur.softsurena.metodos.M_Cliente.ERROR_AL_INSERTAR__CLIENTE;
import static sur.softsurena.metodos.M_Cliente.ERROR_AL__MODIFICAR__CLIENTE;
import static sur.softsurena.metodos.M_Cliente.agregarCliente;
import static sur.softsurena.metodos.M_Cliente.agregarClienteById;
import static sur.softsurena.metodos.M_Cliente.borrarCliente;
import static sur.softsurena.metodos.M_Cliente.getClientes;
import static sur.softsurena.metodos.M_Cliente.modificarCliente;
import static sur.softsurena.metodos.M_ContactoEmail.generarCorreo;
import static sur.softsurena.metodos.M_ContactoTel.generarTelMovil;
import static sur.softsurena.metodos.M_Generales.generarCedula;
import sur.softsurena.utilidades.FiltroBusqueda;
import sur.softsurena.utilidades.Resultado;

@Getter
public class M_ClienteNGTest {

    public Integer idCliente = -1;
    private final List<ContactoTel> contactosTels = new ArrayList<>();
    private final List<ContactoEmail> contactosEmails = new ArrayList<>();
    private final List<Direccion> direccion = new ArrayList<>();
    private Cliente cliente = null;

    public M_ClienteNGTest() {
        cliente();
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
        cliente();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test(
            enabled = true,
            description = "Nos permite agregar un cliente al sistema de fecturacion.",
            priority = 0
    )
    public void testAgregarCliente() {
        Resultado result = agregarCliente(cliente);

        idCliente = result.getId();
        assertTrue(
                result.getEstado(),
                ERROR_AL_INSERTAR__CLIENTE
                        .concat(" Cliente con el ID: ")
                        .concat(idCliente.toString())
        );
        
        assertTrue(
                result.getMensaje().equals(CLIENTE__AGREGADO__CORRECTAMENTE), 
                ERROR_AL_INSERTAR__CLIENTE
                        .concat(" Cliente con el ID: ")
                        .concat(idCliente.toString())
        );
        
        assertTrue(
                result.getIcono() == JOptionPane.INFORMATION_MESSAGE,
                ERROR_AL_INSERTAR__CLIENTE
                        .concat(" Cliente con el ID: ")
                        .concat(idCliente.toString())
        );
        
        assertTrue(
                result.getId() > 0,
                ERROR_AL_INSERTAR__CLIENTE
                        .concat(" Cliente con el ID: ")
                        .concat(idCliente.toString())
        );
    }

    @Test(
            enabled = true,
            description = "Nos permite modificar un cliente al sistema de facturacion.",
            priority = 1
    )
    public void testModificarCliente() {
        Resultado result = modificarCliente(cliente);
        
        assertTrue(
                result.getMensaje().equals(CLIENTE__MODIFICADO__CORRECTAMENTE),
                ERROR_AL__MODIFICAR__CLIENTE
        );
        
        assertTrue(
                result.getIcono() == JOptionPane.INFORMATION_MESSAGE,
                ERROR_AL__MODIFICAR__CLIENTE
        );
        
        assertTrue(
                result.getEstado(),
                ERROR_AL__MODIFICAR__CLIENTE
        );
    }

    @Test(
            enabled = true,
            description = "Eliminamos registros del cliente de la tabla PERSONAS_CLIENTES",
            priority = 2
    )
    public void testBorrarCliente() {
        Resultado result = borrarCliente(idCliente);
        
        assertTrue(
                result.getMensaje().equals(CLIENTE_BORRADO_CORRECTAMENTE),
                CLIENTE_NO_PUEDE_SER_BORRADO
        );
        
        assertTrue(
                result.getIcono() == JOptionPane.INFORMATION_MESSAGE,
                CLIENTE_NO_PUEDE_SER_BORRADO
        );
        
        assertTrue(
                result.getEstado(),
                CLIENTE_NO_PUEDE_SER_BORRADO
        );
    }

    @Test(
            enabled = true,
            description = "",
            priority = 3
    )
    public void testAgregarClienteById() {
        Resultado result = agregarClienteById(idCliente);
        
        assertTrue(
                result.getMensaje().equals(CLIENTE__AGREGADO__CORRECTAMENTE),
                ERROR_AL_INSERTAR__CLIENTE
        );
        
        assertTrue(
                result.getIcono() == JOptionPane.INFORMATION_MESSAGE, 
                ERROR_AL_INSERTAR__CLIENTE
        );
        
        assertTrue(
                result.getEstado(), 
                ERROR_AL_INSERTAR__CLIENTE
        );
    }

    @Test(
            enabled = true,
            description = "Eliminamos registros del cliente de la tabla PERSONAS_CLIENTES",
            priority = 4
    )
    public void testBorrarCliente2() {
        Resultado result = borrarCliente(idCliente);
        assertTrue(
                result.getMensaje().equals(CLIENTE_BORRADO_CORRECTAMENTE),
                CLIENTE_NO_PUEDE_SER_BORRADO
        );
        
        assertTrue(
                result.getIcono() == JOptionPane.INFORMATION_MESSAGE,
                CLIENTE_NO_PUEDE_SER_BORRADO
        );
        
        assertTrue(
                result.getEstado(),
                CLIENTE_NO_PUEDE_SER_BORRADO
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
                        .criterioBusqueda("000-0000000-0")
                        .build()
        );

        assertFalse(
                clientes.isEmpty(),
                "La lista no esta vacia..."
        );
    }

    private void cliente() {
        contactosTels.add(
                ContactoTel
                        .builder()
                        .id_persona(idCliente)
                        .telefono(generarTelMovil())
                        .tipo("Movil")
                        .estado(Boolean.TRUE)
                        .por_defecto(Boolean.TRUE)
                        .build()
        );
        contactosTels.add(
                ContactoTel
                        .builder()
                        .id_persona(idCliente)
                        .telefono(generarTelMovil())
                        .tipo("Telefono")
                        .estado(Boolean.TRUE)
                        .por_defecto(Boolean.TRUE)
                        .build()
        );
        contactosEmails.add(
                ContactoEmail
                        .builder()
                        .id_persona(idCliente)
                        .email(generarCorreo())
                        .estado(Boolean.TRUE)
                        .por_defecto(Boolean.TRUE)
                        .build()
        );
        contactosEmails.add(
                ContactoEmail
                        .builder()
                        .id_persona(idCliente)
                        .email(generarCorreo())
                        .estado(Boolean.TRUE)
                        .por_defecto(Boolean.TRUE)
                        .build()
        );
        direccion.add(
                Direccion
                        .builder()
                        .id_persona(idCliente)
                        .provincia(
                                Provincia
                                        .builder()
                                        .id(27)
                                        .build()
                        )
                        .municipio(
                                Municipio
                                        .builder()
                                        .id(118)
                                        .build()
                        )
                        .distrito_municipal(
                                Distrito_municipal
                                        .builder()
                                        .id(0)
                                        .build()
                        )
                        .direccion("Calle 27 de Febrero")
                        .estado(Boolean.TRUE)
                        .por_defecto(Boolean.TRUE)
                        .build()
        );
        
        direccion.add(
                Direccion
                        .builder()
                        .id_persona(idCliente)
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
                        .distrito_municipal(
                                Distrito_municipal
                                        .builder()
                                        .id(0)
                                        .build()
                        )
                        .direccion("Calle 27 de Febrero")
                        .estado(Boolean.TRUE)
                        .por_defecto(Boolean.TRUE)
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
    }

}
