package sur.softsurena.entidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;
import static sur.softsurena.entidades.ContactosEmail.agregarContactosEmail;
import static sur.softsurena.entidades.ContactosEmail.modificarContactosEmail;
import static sur.softsurena.entidades.ContactosTel.agregarContactosTel;
import static sur.softsurena.entidades.ContactosTel.modificarContactosTel;
import static sur.softsurena.entidades.Direcciones.agregarDirecciones;
import static sur.softsurena.entidades.Direcciones.modificarDirecciones;
import sur.softsurena.utilidades.Utilidades;

@Getter
@SuperBuilder
public class Clientes extends Personas {

    private static final Logger LOG = Logger.getLogger(Clientes.class.getName());

    /**
     * Consulta SQL utilizada para agregar clientes al sistema.
     *
     * Nota: Este Query fue actualizado el dia 16 de agosto 2022.
     *
     */
    public final static String INSERT
            = "SELECT p.V_ID FROM SP_INSERT_CLIENTE_SB (?, ?, ?, ?, ?, ?, ?, ?, ?) p;";

    /**
     * Consulta de SQL utilizada para actualizar los clientes del sistema.
     *
     * Nota: Este Query fue actualizado el dia 17 de agosto 2022.
     */
    public final static String UPDATE //Se Utiliza
            = "EXECUTE PROCEDURE SP_UPDATE_CLIENTE_SB(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    /**
     * Para eliminar un cliente, no debe de tener registros en el sistema.
     *
     * Nota:
     */
    public static String DELETE //No se Utiliza
            = "EXECUTE PROCEDURE SP_DELETE_CLIENTE_SB (?, ?);";

    /**
     * Consulta utilizada para obtener los clientes del sistema ya sean que
     * esten activo o inactivo.
     *
     * Uso: 1) Se llena una tabla de cliente en el formulario
     * frmDetalleFacturaClientes.
     *
     */
//    public static String GET_CLIENTES
//            = "SELECT ID, CEDULA, PNOMBRE, SNOMBRE, APELLIDOS FROM GET_CLIENTES";
    /**
     * Consulta que nos permite
     */
    public static String GET_CLIENTES_ESTADO_SB
            = "SELECT r.ID, r.CEDULA, r.ESTADO FROM GET_CLIENTES_ESTADO_SB r";

    /**
     * Este query es utilizado para validar las cedulas en el sistema.
     * Permitiendo comprobar si existe o no cedulas de los clientes nada mas.
     *
     * La vista GET_CLIENTES_GENERALES solo consulta los clientes en la vistas
     * de _CLIENTES unidas a la vista V_GENERALES.
     *
     */
    public static String GET_ID_CLIENTE_BY_CEDULA
            = "SELECT COALESCE(ID, -1) "
            + "FROM GET_CLIENTES_GENERALES "
            + "WHERE TRIM(CEDULA) LIKE TRIM(?);";

    /**
     * Consulta utilizada para presentar los datos en la tabla del formulario
     * clientes.
     */
    public static String GET_CLIENTES_SB
            = "SELECT r.ID, r.CEDULA, r.PERSONA, r.PNOMBRE, r.SNOMBRE, r.APELLIDOS, r.SEXO, "
            + "     r.FECHA_NACIMIENTO, r.ESTADO_CIVIL, r.FECHA_INGRESO, r.ESTADO "
            + "FROM GET_CLIENTES_SB r";

    /**
     * Consulta corta con solo 4 campos de la vista de GET_CLIENTES_SB
     */
    public static String GET_CLIENTES_SB_COMBO
            = "SELECT r.ID, r.PNOMBRE, r.SNOMBRE, r.APELLIDOS FROM GET_CLIENTES_SB r";

    /**
     * Metodos utilizado para agregar los clientes en el sistema, el cual es
     * utilizado para agregar los contactos de este.
     *
     * @param c Es el objecto de la clase cliente que contiene los metodos
     * necesario para obtener los datos del cliente.
     *
     * @param ct Es el objecto que nos permite agregar los tipos de contactos de
     * los clientes.
     *
     * @param ce
     *
     * @return
     */
    public synchronized static Resultados agregarCliente(Clientes c,
            List<ContactosTel> ct, List<ContactosEmail> ce) {
        Resultados r;
        try (PreparedStatement ps = getCnn().prepareStatement(INSERT)) {
            ps.setString(1, c.getPersona() + "");
            ps.setString(2, c.getGenerales().getCedula());
            ps.setString(3, c.getPNombre());
            ps.setString(4, c.getSNombre());
            ps.setString(5, c.getApellidos());
            ps.setString(6, c.getSexo() + "");
            ps.setDate(7, c.getFecha_nacimiento());
            ps.setBoolean(8, c.getEstado());
            ps.setString(9, c.getGenerales().getEstado_civil() + "");

            try (ResultSet rs = ps.executeQuery();) {
                rs.next();

                int id = rs.getInt(1);

                if (!agregarContactosTel(id, ct)) {
                    r = Resultados.builder().
                            id(-1).
                            mensaje("Error al agregar contactos telefonico del cliente.").
                            cantidad(-1).build();
                    return r;
                }

                if (!agregarContactosEmail(id, ce)) {
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
                        mensaje("Cliente Agregado Correctamente").
                        cantidad(-1).build();

                return r;
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, "Error al insertar un cliente al sistema", ex);
                r = Resultados.builder().
                        id(-1).
                        mensaje("Error al insertar Cliente...").
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
     * Metodo que permite modificar a los clientes del sistema de facturacion.
     *
     * @param c Este objeto se almacenan los numeros de contactos telefonicos.
     *
     * @param cc En este objeto se almacenan los correos electronicos del
     * cliente.
     *
     * @return retorna un objecto de la clase resultado los cuales se envian lo
     * que es el mensaje, id y la cantidad de registro afetados.
     */
    public synchronized static Resultados modificarCliente(Clientes c,
            List<ContactosTel> ct, List<ContactosEmail> ce) {

        Resultados r;
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

            if (!modificarContactosTel(c.getId_persona(), ct)) {
                r = Resultados.builder().
                        id(-1).
                        mensaje("Error al modificar contactos telefonico del cliente.").
                        cantidad(-1).build();
                return r;
            }

            if (!modificarContactosEmail(c.getId_persona(), ce)) {
                r = Resultados.builder().
                        id(-1).
                        mensaje("Error al agregar contactos correos electronicos del cliente.").
                        cantidad(-1).build();
                return r;
            }

            if (!modificarDirecciones(c.getId_persona(), c.getDireccion())) {
                r = Resultados.builder().
                        id(-1).
                        mensaje("Error al agregar direcciones del cliente").
                        cantidad(-1).build();
                return r;
            }

            r = Resultados.builder().
                    id(-1).
                    mensaje("Cliente Modificado Correctamente").
                    cantidad(cant).build();

            return r;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            r = Resultados.builder().
                    id(-1).
                    mensaje("Error al Modificar Cliente...").
                    cantidad(-1).build();
            return r;
        }
    }

    /**
     *
     * @param idCliente
     * @param estado
     * @return
     */
    public synchronized static Resultados borrarCliente(int idCliente,
            boolean estado) {
        Resultados r;//Resultados obtenidos del metodos.
        try (PreparedStatement ps = getCnn().prepareStatement(DELETE)) {
            ps.setInt(1, idCliente);
            ps.setBoolean(2, estado);

            int c = ps.executeUpdate();//Cantidad de registros afectados.

            r = Resultados.builder().
                    id(-1).
                    mensaje("Cliente borrado correctamente.").
                    cantidad(c).build();

            return r;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            r = Resultados.builder().
                    id(-1).
                    mensaje("Cliente no puede ser borrado").
                    cantidad(-1).build();
            return r;
        }
    }

    /**
     * Listado de clientes de la base de datos, obtenidos de la vista
     * GET_CLIENTES_SB, donde los clientes para mostrarse deben estar activo.
     *
     * @param filtro
     * @param criterio
     * @return
     */
    public synchronized static ResultSet getClientesCombo() {
        try (PreparedStatement ps = getCnn().prepareStatement(GET_CLIENTES_ESTADO_SB)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo utilizado para saber si existe un cliente registrado por una
     * cedula de identidad en el sistema.
     *
     * Metodo revisado y actualizado el 26 de abril 2022. Metodo revisado y
     * actualizado el 23 de noviembre 2022.
     *
     *
     * @param cedula cedula del cliente utilizada para consultar la base de
     * datos.
     *
     * @return devuelve el id del cliente si existe la cedula registrada y -1 si
     * no se encuentra la cedula en el sistema.
     */
    public synchronized static Integer existeCliente(String cedula) {
        try (PreparedStatement ps = getCnn().prepareStatement(
                GET_ID_CLIENTE_BY_CEDULA,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            ps.setString(1, cedula.trim());

            try (ResultSet rs = ps.executeQuery()) {
                return rs.getInt("ID");
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
     * @return Devuelve todos los datos realacionado con los clientes en la base
     * de datos.
     */
    public static synchronized DefaultTableModel getClientesTablaSB() {
        String titulos[] = {"Cedulas", "Persona", "Primer Nombre", "Segundo Nombre",
            "Apellidos", "Sexo", "Fecha nacimiento", "Fecha Ingreso", "Estado"};

        try (PreparedStatement ps = getCnn().prepareStatement(
                GET_CLIENTES_SB,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            Object registro[] = new Object[titulos.length];
            DefaultTableModel dtmClientes = new DefaultTableModel(null, titulos);

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Generales g = Generales.builder().
                            cedula(rs.getString("cedula")).build();

                    Clientes p = Clientes.builder().
                            id_persona(rs.getInt("id")).
                            generales(g).build();
                    registro[0] = p;
                    registro[1] = rs.getString("persona").equalsIgnoreCase("j") ? "JURÍDICA" : "FÍSICA";
                    registro[2] = rs.getString("pnombre");
                    registro[3] = rs.getString("snombre");
                    registro[4] = rs.getString("apellidos");
                    registro[5] = rs.getString("sexo").equalsIgnoreCase("M") ? "MASCULINO" : "FEMENINO";
                    registro[6] = Utilidades.formatDate(rs.getDate("fecha_nacimiento"), "dd/MM/yyyy");
                    registro[7] = Utilidades.formatDate(rs.getDate("fecha_Ingreso"), "dd/MM/yyyy");
                    registro[8] = rs.getBoolean("Estado");
                    dtmClientes.addRow(registro);
                }

                return dtmClientes;
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
     * @param cmbCliente
     */
    public static synchronized void getClientesTablaSBCombo(JComboBox<Clientes> cmbCliente) {

        try (PreparedStatement ps = getCnn().prepareStatement(
                GET_CLIENTES_SB_COMBO,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    cmbCliente.addItem(Clientes.builder().
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

        final String GET_CLIENTES_SB_BY_ID
                = "SELECT r.ID, r.CEDULA, r.PERSONA, r.PNOMBRE, r.SNOMBRE, r.APELLIDOS, r.SEXO, "
                + "     r.FECHA_NACIMIENTO, r.ESTADO_CIVIL, r.FECHA_INGRESO, r.ESTADO "
                + "FROM GET_CLIENTES_SB r "
                + "WHERE r.ID = ?";

        try (PreparedStatement ps = getCnn().prepareStatement(GET_CLIENTES_SB_BY_ID)) {

            ps.setInt(1, id);

            Clientes c = null;
            Generales g = null;

            try (ResultSet rs = ps.executeQuery();) {
                rs.next();
                g = Generales.builder().
                        cedula(rs.getString("CEDULA")).
                        estado_civil(rs.getString("ESTADO_CIVIL").charAt(0)).build();

                c = Clientes.builder().
                        pNombre(rs.getString("PNOMBRE")).
                        sNombre(rs.getString("SNOMBRE")).
                        apellidos(rs.getString("APELLIDOS")).
                        fecha_nacimiento(rs.getDate("FECHA_NACIMIENTO")).
                        estado(rs.getBoolean("ESTADO")).
                        persona(rs.getString("PERSONA").charAt(0)).
                        sexo(rs.getString("SEXO").charAt(0)).
                        generales(g).build();

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
     *
     * @param criterio
     * @param filtro
     * @return
     */
    public synchronized static ResultSet getClientesFiltrados(String criterio,
            String filtro) {
        String sql = "SELECT r.IDCLIENTE, r.NOMBRES, r.APELLIDOS, D.MONTO "
                + "FROM Tabla_Clientes r "
                + "INNER JOIN TABLA_DEUDAS D ON r.IDCLIENTE like D.IDCLIENTE "
                + filtro;
        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {

            if (criterio != null) {
                ps.setString(1, criterio);
            }

            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    public synchronized static ResultSet getClientesCobros() {
        try (PreparedStatement ps = getCnn().prepareStatement(
                "SELECT r.IDCLIENTE, (r.NOMBRES||' '||r.APELLIDOS) as nombre "
                + "FROM TABLA_CLIENTES r "
                + "WHERE r.DEUDAACTUAL > 0")) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param idCliente
     * @return
     */
    public synchronized static ResultSet getCobrosClientesFactura(String idCliente) {
        final String sql = "SELECT r.IDFACTURA, CAST(sum(d.CANTIDAD * d.PRECIO) as DECIMAL(15,2)) as Total, "
                + "       r.FECHA, r.ESTADO "
                + "FROM TABLA_FACTURAS r "
                + "JOIN TABLA_DETALLEFACTURA d ON d.IDFACTURA = r.IDFACTURA "
                + "WHERE r.IDCLIENTE like ? and r.ESTADO in('c', 'a') "
                + "GROUP BY r.IDFACTURA, r.FECHA, r.ESTADO";
        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {
            ps.setString(1, idCliente);
            return ps.executeQuery();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * 
     * @param estado
     * @return 
     */
    public synchronized static ResultSet getDeudaClientes(String estado) {
        final String sql = "SELECT SUM(r.MONTO), case r.ESTADO "
                + "when 'i' then 'Inicial:' "
                + "when 'a' then 'Abonado:' "
                + "when 'p' then 'Pagado:' "
                + "when 'n' then 'Nulado:' "
                + "end "
                + "FROM GET_SUMA_DEUDA r "
                + estado;
        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {
            return ps.executeQuery();
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
