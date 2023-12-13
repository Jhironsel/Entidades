package sur.softsurena.entidades;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;

public class ClientesNGTest {

    private Integer idCliente;
    private final List<ContactosTel> contactosTels = new ArrayList<>();
    private final List<ContactosEmail> contactosEmails = new ArrayList<>();
    private final List<Direcciones> direccion = new ArrayList<>();
    private Clientes cliente;

    public ClientesNGTest() {
        Conexion.getInstance(
                "sysdba",
                "1",
                "BaseDeDatos.db",
                "localhost",
                "3050"
        );
        if (Conexion.verificar().getEstado()) {
            System.out.println("Contraseña correcta...");
        } else {
            System.out.println("Error en la clave...");
        }
    }

    @Test
    public void testAgregarCliente() {
        contactosTels.add(ContactosTel.builder().
                accion('i').
                telefono(ContactosTel.generarTelMovil()).
                tipo("Movil").build()
        );
        contactosTels.add(ContactosTel.builder().
                accion('i').
                telefono(ContactosTel.generarTelMovil()).
                tipo("Telefono").build()
        );
        contactosEmails.add(ContactosEmail.builder().
                email(ContactosEmail.generarCorreo()).
                build()
        );
        contactosEmails.add(ContactosEmail.builder().
                email(ContactosEmail.generarCorreo()).
                build()
        );
        direccion.add(
                Direcciones
                        .builder()
                        .provincia(
                                Provincias
                                        .builder()
                                        .id(27)
                                        .build()
                        )
                        .municipio(
                                Municipios
                                        .builder()
                                        .id(118)
                                        .build()
                        )
                        .distrito_municipal(
                                Distritos_municipales
                                        .builder()
                                        .id(0)
                                        .build()
                        )
                        .direccion("Calle 27 de Febrero")
                        .build()
        );
        cliente = Clientes
                .builder()
                .persona('F')
                .generales(
                        Generales
                                .builder()
                                .estado_civil('S')
                                .cedula(Generales.generarCedula())
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

        Resultados result = Clientes.agregarCliente(cliente);

        idCliente = result.getId();

        assertEquals(Clientes.CLIENTE__AGREGADO__CORRECTAMENTE, result.toString());
    }

    @Test
    public void testModificarCliente() {

        contactosTels.add(
                ContactosTel
                        .builder()
                        .accion('i')
                        .telefono(ContactosTel.generarTelMovil())
                        .tipo("Movil")
                        .build()
        );

        contactosTels.add(
                ContactosTel
                        .builder()
                        .accion('i')
                        .telefono(ContactosTel.generarTelMovil())
                        .tipo("Telefono")
                        .build()
        );

        contactosEmails.add(
                ContactosEmail
                        .builder()
                        .email(ContactosEmail.generarCorreo())
                        .build()
        );

        contactosEmails.add(
                ContactosEmail
                        .builder()
                        .email(ContactosEmail.generarCorreo())
                        .build()
        );
        direccion.add(
                Direcciones
                        .builder()
                        .provincia(
                                Provincias
                                        .builder()
                                        .id(27)
                                        .build()
                        )
                        .municipio(
                                Municipios
                                        .builder()
                                        .id(118)
                                        .build()
                        )
                        .distrito_municipal(
                                Distritos_municipales
                                        .builder()
                                        .id(0)
                                        .build()
                        )
                        .direccion("Calle 27 de Febrero")
                        .build()
        );
        cliente = Clientes
                .builder()
                .id_persona(idCliente)
                .persona('F')
                .generales(
                        Generales
                                .builder()
                                .estado_civil('S')
                                .cedula(Generales.generarCedula())
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

        Resultados result = Clientes.modificarCliente(cliente);
        assertEquals(Clientes.CLIENTE__MODIFICADO__CORRECTAMENTE, result.toString());
        
        result = Clientes.borrarCliente(idCliente);
        assertEquals(Clientes.CLIENTE_BORRADO_CORRECTAMENTE, result.toString());
    }

    @Test
    public void testExisteCliente() {
        List<Clientes> clientes = Clientes.getClientes(
                FiltroBusqueda
                        .builder()
                        .id(0)
                        .criterioBusqueda("^")
                        .build()
        );
        assertEquals(clientes.size(), 1);
        
        Clientes clienteLocal = clientes.get(0);
        
        assertEquals("000-0000000-0", clienteLocal.getGenerales().getCedula());
        assertEquals('J', clienteLocal.getPersona());
        assertEquals("GENERICO", clienteLocal.getPnombre());
        assertEquals("", clienteLocal.getSnombre());
        assertEquals("", clienteLocal.getApellidos());
        assertEquals('X', clienteLocal.getSexo());
        assertEquals("2000-01-01", clienteLocal.getFecha_nacimiento().toString());
        assertEquals(Character.valueOf('X'), clienteLocal.getGenerales().getEstado_civil());
        assertEquals(Boolean.TRUE, clienteLocal.getEstado());
    }
} 