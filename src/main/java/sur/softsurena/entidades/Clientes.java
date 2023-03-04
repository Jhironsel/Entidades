package sur.softsurena.entidades;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;
import static sur.softsurena.entidades.ContactosEmail.agregarContactosEmail;
import static sur.softsurena.entidades.ContactosTel.agregarContactosTel;
import static sur.softsurena.entidades.Direcciones.agregarDirecciones;

@Getter
@SuperBuilder
public class Clientes extends Personas {

    private static final Logger LOG = Logger.getLogger(Clientes.class.getName());

    public static final String CLIENTE__AGREGADO__CORRECTAMENTE = "Cliente Agregado Correctamente";
    public static final String ERROR_AL_INSERTAR__CLIENTE = "Error al insertar Cliente.";

    public static final String CLIENTE_NO_PUEDE_SER_BORRADO = "Cliente no puede ser borrado.";
    public static final String CLIENTE_BORRADO_CORRECTAMENTE = "Cliente borrado correctamente.";

    public static final String ERROR_AL__MODIFICAR__CLIENTE = "Error al Modificar Cliente.";
    public static final String CLIENTE__MODIFICADO__CORRECTAMENTE = "Cliente Modificado Correctamente.";

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
    public synchronized static Resultados agregarCliente(Clientes c) {
        Resultados r;

        final String INSERT
                = "SELECT V_ID FROM SP_INSERT_CLIENTE_SB (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)) {
            ps.setString(1, String.valueOf(c.getPersona()));
            ps.setString(2, c.getGenerales().getCedula());
            ps.setString(3, c.getPNombre());
            ps.setString(4, c.getSNombre());
            ps.setString(5, c.getApellidos());
            ps.setString(6, String.valueOf(c.getSexo()));
            ps.setDate(7, c.getFecha_nacimiento());
            ps.setBoolean(8, c.getEstado());
            ps.setString(9, String.valueOf(c.getGenerales().getEstado_civil()));
            try (ResultSet rs = ps.executeQuery();) {
                rs.next();

                int id = rs.getInt(1);

                if (!agregarContactosTel(id, c.getContactosTel())) {
                    r = Resultados.builder().
                            id(-1).
                            mensaje("Error al agregar contactos telefonico del cliente.").
                            cantidad(-1).build();
                    return r;
                }

                if (!agregarContactosEmail(id, c.getContactosEmail())) {
                    r = Resultados.builder().
                            id(-1).
                            mensaje("Error al agregar contactos correos electronicos del cliente.").
                            cantidad(-1).build();
                    return r;
                }

                if (!agregarDirecciones(id, c.getDireccion())) {
                    r = Resultados.builder().
                            id(-1).
                            mensaje("Error al agregar direcciones del cliente").
                            cantidad(-1).build();
                    return r;
                }

                r = Resultados.builder().
                        id(-1).
                        mensaje(CLIENTE__AGREGADO__CORRECTAMENTE).
                        cantidad(-1).build();

                return r;
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, "Error al insertar un cliente al sistema", ex);
                r = Resultados.builder().
                        id(-1).
                        mensaje(ERROR_AL_INSERTAR__CLIENTE).
                        cantidad(-1).build();
                return r;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al insertar un cliente al sistema", ex);
            r = Resultados.builder().
                    id(-1).
                    mensaje("Error al insertar Cliente...").
                    cantidad(-1).build();
            return r;
        }
    }

    public static boolean agregarClienteById(int id) {
        final String sql
                = "EXECUTE PROCEDURE SP_INSERT_PERSONA_CLIENTES_ID(?)";
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            cs.setInt(1, id);
            return cs.execute();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al insertar id del clliente.", ex);
            return false;
        }
    }

    /**
     * Metodo que permite modificar a los clientes del sistema de facturacion.
     *
     * @param c Este objeto se almacenan los numeros de contactos telefonicos.
     *
     * @return retorna un objecto de la clase resultado los cuales se envian lo
     * que es el mensaje, id y la cantidad de registro afetados.
     */
    public synchronized static Resultados modificarCliente(Clientes c) {
        final String UPDATE
                = "EXECUTE PROCEDURE SP_UPDATE_CLIENTE_SB(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement ps = getCnn().prepareStatement(UPDATE)) {
            ps.setInt(1, c.getId_persona());
            ps.setString(2, String.valueOf(c.getPersona()));
            ps.setString(3, c.getGenerales().getCedula());
            ps.setString(4, c.getPNombre());
            ps.setString(5, c.getSNombre());
            ps.setString(6, c.getApellidos());
            ps.setString(7, String.valueOf(c.getSexo()));
            ps.setDate(8, c.getFecha_nacimiento());
            ps.setBoolean(9, c.getEstado());
            ps.setString(10, String.valueOf(c.getGenerales().getEstado_civil()));
            int cant = ps.executeUpdate();
            return Resultados.
                    builder().
                    id(c.getId_persona()).
                    mensaje(CLIENTE__MODIFICADO__CORRECTAMENTE).
                    cantidad(cant).
                    build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados.builder().
                    id(-1).
                    mensaje(ERROR_AL__MODIFICAR__CLIENTE).
                    cantidad(-1).
                    build();
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
        final String DELETE = "EXECUTE PROCEDURE SP_DELETE_CLIENTE_SB (?);";

        try (PreparedStatement ps = getCnn().prepareStatement(DELETE)) {

            ps.setInt(1, idCliente);

            int c = ps.executeUpdate();//Cantidad de registros afectados.

            return Resultados.builder().
                    id(-1).
                    mensaje(CLIENTE_BORRADO_CORRECTAMENTE).
                    cantidad(c).build();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return Resultados.builder().
                    id(-1).
                    mensaje(CLIENTE_NO_PUEDE_SER_BORRADO).
                    cantidad(-1).build();
        }
    }

    /**
     * Metodo utilizado para llevar los comboBox de los componente de clientes.
     *
     * Nota: este metodo no deberia devolver un resultset.
     *
     * @return
     */
    public synchronized static List<Clientes> getClientesCombo() {
        final String sql
                = "SELECT ID, CEDULA, PNOMBRE, SNOMBRE, APELLIDOS, ESTADO "
                + "FROM GET_CLIENTES_SB "
                + "WHERE ESTADO";

        List<Clientes> clienteList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    clienteList.add(
                            Clientes.
                                    builder().
                                    id_persona(rs.getInt("ID")).
                                    generales(
                                            Generales.
                                                    builder().
                                                    cedula(rs.getString("CEDULA")).
                                                    build()
                                    ).
                                    pNombre(rs.getString("PNOMBRE")).
                                    sNombre(rs.getString("SNOMBRE")).
                                    apellidos(rs.getString("APELLIDOS"))
                                    .build()
                    );
                }

                return clienteList;
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo utilizado para saber si existe un cliente registrado por una
     * cedula de identidad en el sistema.
     *
     *
     * Metodo revisado y actualizado el 26 de abril 2022. Metodo revisado y
     * actualizado el 23 de noviembre 2022.
     *
     * @param cedula cedula del cliente utilizada para consultar la base de
     * datos.
     *
     * @return devuelve el id del cliente si existe la cedula registrada y -1 si
     * no se encuentra la cedula en el sistema.
     */
    public synchronized static Integer existeCliente(String cedula) {
        final String GET_ID_CLIENTE_BY_CEDULA
                = "SELECT ID_PERSONA AS ID "
                + "FROM V_GENERALES  "
                + "WHERE CEDULA LIKE TRIM(?);";
        try (PreparedStatement ps = getCnn().prepareStatement(
                GET_ID_CLIENTE_BY_CEDULA,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            ps.setString(1, cedula.trim());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("ID");
                }
                return -1;
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return -1;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return -1;
        }
    }

    /**
     * Metodo utilizado para presentar los datos en la tabla del formulario
     * clientes.
     *
     * @return Devuelve todos los datos realacionado con los clientes en la base
     * de datos.
     */
    public static synchronized List<Clientes> getClientesTablaSB() {
        final String sql
                = "SELECT ID, CEDULA, PERSONA, PNOMBRE, SNOMBRE, APELLIDOS, "
                + "     SEXO, FECHA_NACIMIENTO, FECHA_INGRESO, ESTADO "
                + "FROM GET_CLIENTES_SB; ";
        List<Clientes> clienteList = new ArrayList<>();
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    clienteList.add(
                            Clientes.
                                    builder().
                                    id_persona(rs.getInt("id")).
                                    persona(rs.getString("persona").charAt(0)).
                                    generales(Generales.
                                            builder().
                                            cedula(rs.getString("cedula")).
                                            build()
                                    ).
                                    pNombre(rs.getString("pnombre")).
                                    sNombre(rs.getString("snombre")).
                                    apellidos(rs.getString("apellidos")).
                                    sexo(rs.getString("sexo").charAt(0)).
                                    fecha_ingreso(rs.getDate("fecha_nacimiento")).
                                    fecha_ingreso(rs.getDate("fecha_Ingreso")).
                                    estado(rs.getBoolean("Estado")).
                                    build()
                    );
                }
                return clienteList;
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Consulta corta con solo 4 campos de la vista de GET_CLIENTES_SB, este
     * metodo su objetivo principal es llenar los combo box del sistema.
     *
     * @return Devolvemos una lista de cliente con solo el identificador, primer
     * nombre, segundo nombre, apellidos de la base de datos.
     */
    public static synchronized List<Clientes> getClientesTablaSBCombo() {
        final String sql
                = "SELECT ID, PNOMBRE, SNOMBRE, APELLIDOS "
                + "FROM GET_CLIENTES_SB";
        List<Clientes> clienteList = new ArrayList<>();
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    clienteList.add(Clientes.builder().
                            id_persona(rs.getInt("id")).
                            pNombre(rs.getString("pnombre")).
                            sNombre(rs.getString("snombre")).
                            apellidos(rs.getString("apellidos")).build());
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return clienteList;
    }

    /**
     * Obtenemos los datos basico de un cliente por una consulta por su ID.
     *
     * @param id identificador del cliente del sistema, la cual ayuda obtener
     * los registros de un usuario en expecifico.
     *
     * Consulta utilizada para presentar los datos en la tabla del formulario
     * clientes.
     *
     * @return Retorna un conjunto de datos del tipo resultSet.
     */
    public synchronized static Clientes getClienteByID(int id) {

        final String sql
                = "SELECT ID, CEDULA, PERSONA, PNOMBRE, SNOMBRE, APELLIDOS, SEXO, "
                + "     FECHA_NACIMIENTO, ESTADO_CIVIL, FECHA_INGRESO, ESTADO "
                + "FROM GET_PERSONAS_ID "
                + "WHERE ID = ?";

        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            ps.setInt(1, id);

            Clientes c = null;

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    c = Clientes.builder().
                            pNombre(rs.getString("PNOMBRE")).
                            sNombre(rs.getString("SNOMBRE")).
                            apellidos(rs.getString("APELLIDOS")).
                            fecha_nacimiento(rs.getDate("FECHA_NACIMIENTO")).
                            estado(rs.getBoolean("ESTADO")).
                            persona(rs.getString("PERSONA").charAt(0)).
                            sexo(rs.getString("SEXO").charAt(0)).
                            generales(Generales.builder().
                                    cedula(rs.getString("CEDULA")).
                                    estado_civil(rs.getString("ESTADO_CIVIL").charAt(0)).build()).build();
                }

            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
            return c;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Retorna el nombre del cliente completo.
     *
     * @return Retorna un String con la información del nombre del cliente.
     */
    @Override
    public String toString() {
        if (super.getPNombre() == null) {
            return super.getGenerales().getCedula();
        }
        return super.toString();
    }

}
