package sur.softsurena.entidades;

import java.math.BigDecimal;
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

        Resultados<Object> result = Clientes.agregarCliente(cliente);

        idCliente = result.getId();

        assertEquals(result.getMensaje(), Clientes.CLIENTE__AGREGADO__CORRECTAMENTE);
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
        assertEquals(result.getMensaje(), Clientes.CLIENTE__MODIFICADO__CORRECTAMENTE);
    }

    @Test
    public void testExisteCliente() {
        Integer result = Clientes.existeCliente(cliente.getGenerales().getCedula());
        assertEquals(result, idCliente);
    }

    @Test
    public void testGetClientesCombo() {
        List<Clientes> result = Clientes.getClientesCombo();
        assertNotNull(result);
    }

    @Test
    public void testGetClientesTablaSB() {
        List<Clientes> result = Clientes.getClientesTablaSB(1, 10);
        assertNotNull(result);
    }

    @Test
    public void testGetClientesTablaSBCombo() {
        List<Clientes> result = Clientes.getClientesTablaSBCombo();
        assertNotNull(result);
    }

    @Test
    public void testGetClienteByID() {
        Clientes result = Clientes.getClienteByID(idCliente);
        assertNotNull(result);
    }

    @Test
    public void testGetClienteByIDCC() {
        Clientes result = Clientes.getClienteByIDCC(idCliente);
        assertNotNull(result);
    }

    @Test
    public void testGetClientesCC() {
        List<Clientes> result = Clientes.getClientesCC();
        assertNotNull(result);
    }

    @Test
    public void testAgregarClienteCC() {
        Clientes cliente2 = Clientes.builder().
                pnombre("Jhironsel TOTO").
                snombre("").
                apellidos("Diaz Almonte").
                sexo("M").
                correo(ContactosEmail.generarCorreo()).
                saldo(BigDecimal.valueOf(1500.34))
                .build();
        boolean expResult = false;
        boolean result = Clientes.agregarClienteCC(cliente2);
        assertEquals(result, expResult);
    }

    @Test
    public void testModificarClienteCC() {
        Clientes cliente2 = Clientes.builder().
                id_persona(176).
                pnombre("Jhironsel TOTA").
                snombre("").
                apellidos("Diaz Almonte").
                sexo("F").
                correo(ContactosEmail.generarCorreo()).
                saldo(BigDecimal.valueOf(500.34))
                .build();
        boolean expResult = false;
        boolean result = Clientes.modificarClienteCC(cliente2);
        assertEquals(result, expResult);
    }


    
//    @Test
//    public void testEliminarClienteCC() {
//        int idCliente = 0;
//        boolean expResult = false;
//        boolean result = Clientes.eliminarClienteCC(idCliente);
//        assertEquals(result, expResult);
//    }
    
    
    
//    @Test
//    public void testBorrarCliente() {
//        Resultados<Object> result = Clientes.borrarCliente(idCliente);
//        assertEquals(result.getMensaje(), Clientes.CLIENTE_BORRADO_CORRECTAMENTE);
//    }

}
