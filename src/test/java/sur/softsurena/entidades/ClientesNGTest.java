package sur.softsurena.entidades;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import sur.softsurena.conexion.Conexion;

public class ClientesNGTest {

    private Integer idCliente;
    private final List<ContactosTel> contactosTels = new ArrayList<>();
    private final List<ContactosEmail> contactosEmails = new ArrayList<>();
    private final List<Direcciones> direccion = new ArrayList<>();
    private Clientes cliente;

    public ClientesNGTest() {
        Conexion.getInstance("sysdba", "1",
                "/home/jhironsel/BaseDatos/BaseDeDatos4.fdb",
                "localhost", "3050");
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
        direccion.add(Direcciones.builder().
                provincia(Provincias.builder().id(27).build()).
                municipio(Municipios.builder().id(118).build()).
                distrito_municipal(Distritos_municipales.builder().id(0).build()).
                direccion("Calle 27 de Febrero").
                build()
        );
        cliente = Clientes.builder().
                persona('F').
                generales(
                        Generales.builder().
                                estado_civil('S').
                                cedula(Generales.generarCedula()).
                                build()
                ).
                pnombre("Jhironsel").
                snombre("").
                apellidos("Diaz Almonte").
                sexo("M").
                fecha_nacimiento(new Date(0)).
                estado(Boolean.TRUE).
                contactosTel(contactosTels).
                contactosEmail(contactosEmails).
                direcciones(direccion)
                .build();

        Resultados result = Clientes.agregarCliente(cliente);

        idCliente = result.getId();

        assertEquals(result.toString(), Clientes.CLIENTE__AGREGADO__CORRECTAMENTE);
    }

    @Test
    public void testAgregarClienteById() {
        boolean result = Clientes.agregarClienteById(idCliente);
        assertFalse(result);
    }

    @Test
    public void testModificarCliente() {

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
        direccion.add(Direcciones.builder().
                provincia(Provincias.builder().id(27).build()).
                municipio(Municipios.builder().id(118).build()).
                distrito_municipal(Distritos_municipales.builder().id(0).build()).
                direccion("Calle 27 de Febrero").
                build()
        );
        cliente = Clientes.builder().
                id_persona(idCliente).
                persona('F').
                generales(
                        Generales.builder().
                                estado_civil('S').
                                cedula(Generales.generarCedula()).
                                build()
                ).
                pnombre("Jhironsel M").
                snombre("").
                apellidos("Diaz Almonte").
                sexo("M").
                fecha_nacimiento(new Date(0)).
                estado(Boolean.TRUE).
                contactosTel(contactosTels).
                contactosEmail(contactosEmails).
                direcciones(direccion)
                .build();

        Resultados<Object> result = Clientes.modificarCliente(cliente);
        assertEquals(result.toString(), Clientes.CLIENTE__MODIFICADO__CORRECTAMENTE);
    }

//    @Test
//    public void testExisteCliente() {
//        Integer result = Clientes.existeCliente(cliente.getGenerales().getCedula());
//        assertEquals(result, idCliente);
//    }
    
//    @Test
//    public void testBorrarCliente() {
//        Resultados<Object> result = Clientes.borrarCliente(idCliente);
//        assertEquals(result.toString(), Clientes.CLIENTE_BORRADO_CORRECTAMENTE);
//    }

}
