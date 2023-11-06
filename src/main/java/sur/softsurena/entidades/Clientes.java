package sur.softsurena.entidades;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Date;
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

    private final BigDecimal totalFacturado;
    private final BigDecimal totalDeuda;
    private final int cantidadFacturado;
    private final Date fechaUltimaCompra;
    private final String correo;
    private final BigDecimal saldo;
    private final String masculino;
    private final String femenino;

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
    public synchronized static Resultados<Object> agregarCliente(Clientes c) {
        Resultados<Object> r;

        final String INSERT
                = "SELECT V_ID FROM SP_INSERT_CLIENTE_SB (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)) {
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
                    r = Resultados.builder().
                            id(-1).
                            mensaje("Error al agregar contactos telefonico del cliente.").
                            cantidad(-1).build();
                    return r;
                }

                if (!agregarContactosEmail(id_persona, c.getContactosEmail())) {
                    r = Resultados.builder().
                            id(-1).
                            mensaje("Error al agregar contactos correos electronicos del cliente.").
                            cantidad(-1).build();
                    return r;
                }

                if (!agregarDirecciones(id_persona, c.getDirecciones())) {
                    r = Resultados.builder().
                            id(-1).
                            mensaje("Error al agregar direcciones del cliente").
                            cantidad(-1).build();
                    return r;
                }

                r = Resultados.builder().
                        id(id_persona).
                        mensaje(CLIENTE__AGREGADO__CORRECTAMENTE).
                        cantidad(-1).
                        build();

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

    /**
     *
     * @param id
     * @return
     */
    public static boolean agregarClienteById(int id) {
        final String sql
                = "EXECUTE PROCEDURE SP_INSERT_PERSONA_CLIENTES_ID(?)";
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            cs.setInt(1, id);
            return cs.execute();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al insertar id del clliente.", ex);
            return false;
        }
    }

    /**
     * Este metodo es utilizado en una aplicacion web llamado Control de
     * Clientes.
     *
     * @return
     */
    public static boolean agregarClienteCC(Clientes cliente) {
        final String sql
                = "EXECUTE PROCEDURE SP_INSERT_CLIENTE_CC(?,?,?,?,?,?)";
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            cs.setString(1, cliente.getPnombre());
            cs.setString(2, cliente.getSnombre());
            cs.setString(3, cliente.getApellidos());
            cs.setString(4, cliente.getSexo());
            cs.setString(5, cliente.getCorreo());
            cs.setBigDecimal(6, cliente.getSaldo());
            return cs.execute();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al insertar id del clliente.", ex);
            return false;
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
     * Metodo que permite modificar a los clientes del sistema de facturacion.
     *
     * @param cliente Este objeto se almacenan los numeros de contactos
     * telefonicos.
     *
     * @return retorna un objecto de la clase resultado los cuales se envian lo
     * que es el mensaje, id y la cantidad de registro afetados.
     */
    public synchronized static Resultados<Object> modificarCliente(Clientes cliente) {
        final String UPDATE
                = "EXECUTE PROCEDURE SP_UPDATE_CLIENTE_SB(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement ps = getCnn().prepareStatement(UPDATE)) {
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

            cliente.getDirecciones().stream().forEach(clienteList -> {
                
            });

            cliente.getContactosEmail().stream().forEach(correoList -> {

            });

            cliente.getContactosTel().stream().forEach(telefonoList -> {

            });

            //Cantidad de registros afectados.
            int cant = ps.executeUpdate();

            return Resultados.
                    builder().
                    id(cliente.getId_persona()).
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
                = "SELECT ID_PERSONA "
                + "FROM SP_SELECT_GENERALES "
                + "WHERE CEDULA LIKE TRIM(?);";
        try (PreparedStatement ps = getCnn().prepareStatement(
                GET_ID_CLIENTE_BY_CEDULA,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            ps.setString(1, cedula.trim());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("ID_PERSONA");
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
     * Metodo utilizado para llenar los comboBox de los componente de clientes.
     * 
     * Dicho metodo entrega un lista de atributos: 
     *  1) ID
     *  2) CEDULA
     *  3) PNOMBRE -> Primer nombre
     *  4) SNOMBRE -> Segundo nombre
     *  5) APELLIDOS
     *
     * @return
     */
    public synchronized static List<Clientes> getClientesCombo() {
        final String sql
                = "SELECT ID, CEDULA, PNOMBRE, SNOMBRE, APELLIDOS "
                + "FROM SP_GET_CLIENTES_SB "
                + "WHERE ESTADO "
                + "ORDER BY 1";

        List<Clientes> clienteList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
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
                                    pnombre(rs.getString("PNOMBRE")).
                                    snombre(rs.getString("SNOMBRE")).
                                    apellidos(rs.getString("APELLIDOS"))
                                    .build()
                    );
                }

                return clienteList;
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo utilizado para presentar los datos en la tabla del formulario
     * clientes.
     *
     * @param nPaginaNro
     * @param nCantidadFilas
     * @return Devuelve todos los datos realacionado con los clientes en la base
     * de datos.
     */
    public static synchronized List<Clientes> getClientesTablaSB(
            String criterioBusqueda, Integer nPaginaNro, Integer nCantidadFilas) {
        final String sql
                = "SELECT ID, CEDULA, PERSONA, PNOMBRE, SNOMBRE, APELLIDOS, "
                + "     SEXO, FECHA_NACIMIENTO, FECHA_INGRESO, ESTADO "
                + "FROM SP_SELECT_GET_CLIENTES_SB (?) "
                + "WHERE ID > 0 "
                + "ROWS (? - 1) * ? + 1 TO (? + (1 - 1)) * ?;";
        
        List<Clientes> clienteList = new ArrayList<>();
        
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            ps.setString(1, criterioBusqueda.strip());
            ps.setInt(2, nPaginaNro);
            ps.setInt(3, nCantidadFilas);
            ps.setInt(4, nPaginaNro);
            ps.setInt(5, nCantidadFilas);
            
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    clienteList.add(
                            Clientes.
                                    builder().
                                    id_persona(rs.getInt("ID")).
                                    persona(rs.getString("PERSONA").charAt(0)).
                                    generales(Generales.
                                            builder().
                                            cedula(rs.getString("CEDULA")).
                                            build()
                                    ).
                                    pnombre(rs.getString("PNOMBRE")).
                                    snombre(rs.getString("SNOMBRE")).
                                    apellidos(rs.getString("APELLIDOS")).
                                    sexo(rs.getString("SEXO")).
                                    fecha_ingreso(rs.getDate("FECHA_NACIMIENTO")).
                                    fecha_ingreso(rs.getDate("FECHA_INGRESO")).
                                    estado(rs.getBoolean("ESTADO")).
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
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    clienteList.add(Clientes.builder().
                            id_persona(rs.getInt("id")).
                            pnombre(rs.getString("pnombre")).
                            snombre(rs.getString("snombre")).
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
                            pnombre(rs.getString("PNOMBRE")).
                            snombre(rs.getString("SNOMBRE")).
                            apellidos(rs.getString("APELLIDOS")).
                            fecha_nacimiento(rs.getDate("FECHA_NACIMIENTO")).
                            fecha_ingreso(rs.getDate("FECHA_INGRESO")).
                            estado(rs.getBoolean("ESTADO")).
                            persona(rs.getString("PERSONA").charAt(0)).
                            sexo(rs.getString("SEXO")).
                            generales(
                                    Generales.builder().
                                            cedula(rs.getString("CEDULA")).
                                            estado_civil(rs.getString("ESTADO_CIVIL").charAt(0)).
                                            build()).
                            build();
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
     * Este metodo es utilizado para consultar los cliente del proyecto web 
     * llamado control cliente.
     * 
     * @param id Identificador del cliente.
     * @return Devuelve un objecto de la clase Clientes.
     */
    public synchronized static Clientes getClienteByIDCC(int id) {
        final String sql
                = "SELECT PERSONA, PNOMBRE, SNOMBRE, APELLIDOS, TRIM(SEXO) AS SEXO, CORREO, SALDO "
                + "FROM GET_CLIENTES_CC "
                + "WHERE ID = ?";
        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {
            ps.setInt(1, id);
            Clientes c = null;
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    c = Clientes.builder().
                            id_persona(id).
                            persona(rs.getString("PERSONA").charAt(0)).
                            pnombre(rs.getString("PNOMBRE")).
                            snombre(rs.getString("SNOMBRE")).
                            apellidos(rs.getString("APELLIDOS")).
                            sexo(rs.getString("SEXO")).
                            correo(rs.getString("CORREO")).
                            saldo(rs.getBigDecimal("saldo")).
                            build();
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
     * Metodo que nos
     * @return
     */
    public synchronized static List<Clientes> getClientesCC() {
        final String sql
                = "SELECT ID, PNOMBRE, SNOMBRE, APELLIDOS, TRIM(SEXO) AS SEXO, CORREO, SALDO "
                + "FROM GET_CLIENTES_CC ";

        List<Clientes> clienteList = new ArrayList<>();

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {

                    clienteList.add(
                            Clientes.
                                    builder().
                                    id_persona(rs.getInt("ID")).
                                    pnombre(rs.getString("PNOMBRE")).
                                    snombre(rs.getString("SNOMBRE")).
                                    apellidos(rs.getString("APELLIDOS")).
                                    sexo(rs.getString("SEXO").strip()).
                                    correo(rs.getString("Correo")).
                                    saldo(rs.getBigDecimal("SALDO")).
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
     *
     * @return
     */
    public static boolean modificarClienteCC(Clientes cliente) {
        final String sql
                = "EXECUTE PROCEDURE SP_UPDATE_CLIENTE_CC(?,?,?,?,?,?,?)";
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            cs.setInt(1, cliente.getId_persona());
            cs.setString(2, cliente.getPnombre());
            cs.setString(3, cliente.getSnombre());
            cs.setString(4, cliente.getApellidos());
            cs.setString(5, cliente.getSexo());
            cs.setString(6, cliente.getCorreo());
            cs.setBigDecimal(7, cliente.getSaldo());
            return cs.execute();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al modificar el clliente.", ex);
            return false;
        }
    }

    /**
     *
     * @param idCliente
     * @return
     */
    public static boolean eliminarClienteCC(int idCliente) {
        final String sql
                = "EXECUTE PROCEDURE SP_DELETE_CLIENTE_CC (?)";
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            cs.setInt(1, idCliente);
            return cs.execute();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al borrar el clliente.", ex);
            return false;
        }
    }

    /**
     * Retorna el nombre del cliente completo.
     *
     * @return Retorna un String con la información del nombre del cliente.
     */
    @Override
    public String toString() {
        if (super.getPnombre() == null) {
            return super.getGenerales().getCedula();
        }
        return super.toString();
    }

}
