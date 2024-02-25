package sur.softsurena.metodos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Cliente;
import sur.softsurena.utilidades.FiltroBusqueda;
import sur.softsurena.entidades.Generales;
import sur.softsurena.utilidades.Resultados;
import static sur.softsurena.metodos.M_ContactoEmail.agregarContactosEmail;
import static sur.softsurena.metodos.M_ContactoEmail.modificarContactosEmail;
import static sur.softsurena.metodos.M_ContactoTel.agregarContactosTel;
import static sur.softsurena.metodos.M_ContactoTel.modificarContactosTel;
import static sur.softsurena.metodos.M_Direccion.agregarModificarDirecciones;

public class M_Cliente {
    private static final Logger LOG = Logger.getLogger(M_Cliente.class.getName());
    
    public static final String CLIENTE__AGREGADO__CORRECTAMENTE = "Cliente Agregado Correctamente";
    public static final String ERROR_AL_INSERTAR__CLIENTE = "Error al insertar Cliente.";

    public static final String CLIENTE_BORRADO_CORRECTAMENTE = "Cliente borrado correctamente.";
    public static final String CLIENTE_NO_PUEDE_SER_BORRADO = "Cliente no puede ser borrado.";

    public static final String CLIENTE__MODIFICADO__CORRECTAMENTE = "Cliente Modificado Correctamente.";
    public static final String ERROR_AL__MODIFICAR__CLIENTE = "Error al Modificar Cliente.";
    
    /**
     * Metodos utilizado para agregar los clientes en el sistema, el cual es
     * utilizado para agregar los contactos de este.
     *
     * @param c Es el objecto de la clase cliente que contiene los metodos
     * necesario para obtener los datos del cliente.
     *
     * @return devuelve el resultado de la operacion a realizar, la cual este
     * objecto puede mostrar el identificador del cliente, el mensaje de la
     * operacion y la cantidad de registros afectados.
     */
    public synchronized static Resultados agregarCliente(Cliente c) {
        final String sql
                = "SELECT V_ID FROM SP_INSERT_CLIENTE_SB (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setString(1, String.valueOf(c.getPersona()));
            ps.setString(2, c.getGenerales().getCedula());
            ps.setString(3, c.getPnombre());
            ps.setString(4, c.getSnombre());
            ps.setString(5, c.getApellidos());
            ps.setString(6, String.valueOf(c.getSexo()));
            ps.setDate(7, c.getFecha_nacimiento());
            ps.setBoolean(8, c.getEstado());
            ps.setString(9, String.valueOf(c.getGenerales().getEstado_civil()));
            
            try (ResultSet rs = ps.executeQuery();) {

                rs.next();

                int id_persona = rs.getInt(1);

                if (!agregarContactosTel(id_persona, c.getContactosTel())) {
                    return Resultados
                            .builder()
                            .id(-1)
                            .mensaje("Error al agregar contactos telefonico del cliente.")
                            .cantidad(-1)
                            .icono(JOptionPane.ERROR_MESSAGE)
                            .build();
                }

                if (!agregarContactosEmail(id_persona, c.getContactosEmail())) {
                    return Resultados
                            .builder()
                            .id(-1)
                            .mensaje("Error al agregar contactos correos electronicos del cliente.")
                            .cantidad(-1)
                            .icono(JOptionPane.ERROR_MESSAGE)
                            .build();
                }

                if (!agregarModificarDirecciones(id_persona, c.getDirecciones())) {
                    return Resultados
                            .builder()
                            .id(-1)
                            .mensaje("Error al agregar direcciones del cliente")
                            .cantidad(-1)
                            .icono(JOptionPane.ERROR_MESSAGE)
                            .build();
                }

                return Resultados
                        .builder()
                        .id(id_persona)
                        .mensaje(CLIENTE__AGREGADO__CORRECTAMENTE)
                        .cantidad(-1)
                        .icono(JOptionPane.INFORMATION_MESSAGE)
                        .build();
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al insertar un cliente al sistema", ex);
            return Resultados
                    .builder()
                    .id(-1)
                    .mensaje("Error al insertar Cliente...")
                    .cantidad(-1)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .build();
        }
    }
    
    /**
     * Permite agregar un cliente ya registrado en la tabla de personas a
     * persona cliente.
     *
     * @param id
     * @return
     */
    public synchronized static Resultados agregarClienteById(int id) {
        final String sql
                = "EXECUTE PROCEDURE SP_INSERT_PERSONA_CLIENTES_ID(?)";
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            cs.setInt(1, id);
            boolean estado = cs.execute();

            return Resultados
                    .builder()
                    .estado(estado)
                    .mensaje(CLIENTE__AGREGADO__CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al insertar id del clliente.", ex);
            return Resultados
                    .builder()
                    .estado(Boolean.TRUE)
                    .mensaje(ERROR_AL_INSERTAR__CLIENTE)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .build();
        }
    }

    /**
     * Este procedimiento tiene la habilidad de borrar los registros de las
     * vistas siguiente: V_CONTACTS_TEL, V_CONTACTS_EMAIL, V_CLIENTES,
     * V_DIRECCIONES, V_GENERALES y V_PERSONAS.
     *
     * Para eliminar un cliente, no debe de tener registros en el sistema.
     *
     * @param idCliente
     * @return
     */
    public synchronized static Resultados borrarCliente(int idCliente) {
        final String sql = "EXECUTE PROCEDURE SP_DELETE_CLIENTE_SB (?);";

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setInt(1, idCliente);

            int c = ps.executeUpdate();//Cantidad de registros afectados.

            return Resultados
                    .builder()
                    .id(-1)
                    .mensaje(CLIENTE_BORRADO_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .cantidad(c)
                    .build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados
                    .builder()
                    .id(-1)
                    .mensaje(CLIENTE_NO_PUEDE_SER_BORRADO)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .cantidad(-1)
                    .build();
        }
    }

    /**
     * Metodo que permite modificar a los clientes del sistema de facturacion.
     *
     * @param cliente Este objeto se almacenan los numeros de contactos
     * telefonicos.
     *
     * @return retorna un objecto de la clase resultado los cuales se envian lo
     * que es el mensaje, id y la cantidad de registro afetados.
     */
    public synchronized static Resultados modificarCliente(Cliente cliente) {
        final String sql
                = "EXECUTE PROCEDURE SP_UPDATE_CLIENTE_SB(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {
            //Atributos del cliente
            ps.setInt(1, cliente.getId_persona());
            ps.setString(2, String.valueOf(cliente.getPersona()));
            ps.setString(3, cliente.getGenerales().getCedula());
            ps.setString(4, cliente.getPnombre());
            ps.setString(5, cliente.getSnombre());
            ps.setString(6, cliente.getApellidos());
            ps.setString(7, String.valueOf(cliente.getSexo()));
            ps.setDate(8, cliente.getFecha_nacimiento());
            ps.setBoolean(9, cliente.getEstado());
            ps.setString(10, String.valueOf(cliente.getGenerales().getEstado_civil()));

            agregarModificarDirecciones(cliente.getId_persona(), cliente.getDirecciones());
            
            modificarContactosEmail(cliente.getId_persona(), cliente.getContactosEmail());

            modificarContactosTel(cliente.getId_persona(), cliente.getContactosTel());

            //Cantidad de registros afectados.
            int cant = ps.executeUpdate();

            return Resultados
                    .builder()
                    .id(cliente.getId_persona())
                    .mensaje(CLIENTE__MODIFICADO__CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .cantidad(cant)
                    .build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados
                    .builder()
                    .id(-1)
                    .mensaje(ERROR_AL__MODIFICAR__CLIENTE)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .cantidad(-1)
                    .build();
        }
    }

    /**
     * Metodo utilizado para presentar los datos en la tabla del formulario
     * clientes.
     *
     *
     * @param filtro
     *
     * @return Devuelve todos los datos realacionado con los clientes en la base
     * de datos.
     */
    public static synchronized List<Cliente> getClientes(FiltroBusqueda filtro) {
        final String sql
                = "SELECT ID, CEDULA, PERSONA, PNOMBRE, SNOMBRE, APELLIDOS, "
                + "          SEXO, FECHA_NACIMIENTO, FECHA_INGRESO, ESTADO, ESTADO_CIVIL "
                + "FROM GET_CLIENTES_SB "
                + "WHERE ID = ? OR "
                + "UPPER(TRIM(CEDULA)) STARTING WITH UPPER(TRIM(?)) OR "
                + "UPPER(TRIM(PNOMBRE)) STARTING WITH UPPER(TRIM(?)) OR "
                + "UPPER(TRIM(SNOMBRE)) STARTING WITH UPPER(TRIM(?)) OR "
                + "UPPER(TRIM(APELLIDOS)) STARTING WITH UPPER(TRIM(?)) "
                + (Objects.isNull(filtro.getEstado()) ? "" : (filtro.getEstado() ? " AND ESTADO " : " AND ESTADO IS FALSE "))
                + " ORDER BY ID "
                + (Objects.isNull(filtro.getFilas()) ? "" : (filtro.getFilas() ? "ROWS (? - 1) * ? + 1 TO (? + (1 - 1)) * ? " : ""));

        List<Cliente> clienteList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            //Parametros para el identificador
            ps.setInt(1, (Objects.isNull(filtro.getId()) ? -1 : filtro.getId()));

            //Parametros para el criterio de busquedas
            ps.setString(2, (Objects.isNull(filtro.getCriterioBusqueda()) ? "" : filtro.getCriterioBusqueda()));
            ps.setString(3, (Objects.isNull(filtro.getCriterioBusqueda()) ? "" : filtro.getCriterioBusqueda()));
            ps.setString(4, (Objects.isNull(filtro.getCriterioBusqueda()) ? "" : filtro.getCriterioBusqueda()));
            ps.setString(5, (Objects.isNull(filtro.getCriterioBusqueda()) ? "" : filtro.getCriterioBusqueda()));

            if (!Objects.isNull(filtro.getFilas()) && filtro.getFilas()) {
                //Parametros para la paginacion de contenido de las tablas.
                ps.setInt(6, filtro.getNPaginaNro());
                ps.setInt(7, filtro.getNCantidadFilas());
                ps.setInt(8, filtro.getNPaginaNro());
                ps.setInt(9, filtro.getNCantidadFilas());
            }

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    clienteList.add(Cliente.
                                    builder().
                                    id_persona(rs.getInt("ID")).
                                    persona(rs.getString("PERSONA").charAt(0)).
                                    generales(Generales.
                                            builder().
                                            cedula(rs.getString("CEDULA")).
                                            estado_civil(rs.getString("ESTADO_CIVIL").charAt(0)).
                                            build()
                                    ).
                                    pnombre(rs.getString("PNOMBRE")).
                                    snombre(rs.getString("SNOMBRE")).
                                    apellidos(rs.getString("APELLIDOS")).
                                    sexo(rs.getString("SEXO").charAt(0)).
                                    fecha_nacimiento(rs.getDate("FECHA_NACIMIENTO")).
                                    fecha_ingreso(rs.getDate("FECHA_INGRESO")).
                                    estado(rs.getBoolean("ESTADO")).
                                    build()
                    );
                }
                return clienteList;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
