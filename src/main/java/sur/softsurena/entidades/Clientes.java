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

        final String INSERT
                = "SELECT p.V_ID FROM SP_INSERT_CLIENTE_SB (?, ?, ?, ?, ?, ?, ?, ?, ?) p;";

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
    
    /**
     * Metodo que permite modificar a los clientes del sistema de facturacion.
     *
     * @param c Este objeto se almacenan los numeros de contactos telefonicos.
     *
     * @param ct Son los listado telefonico de los clientes que se van a
     * modificar
     *
     * @param ce Es el listado de correo electronico del cliente.
     *
     * @return retorna un objecto de la clase resultado los cuales se envian lo
     * que es el mensaje, id y la cantidad de registro afetados.
     */
    public synchronized static Resultados modificarCliente(Clientes c,
            List<ContactosTel> ct, List<ContactosEmail> ce) {

        Resultados r;

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
                    mensaje(CLIENTE__MODIFICADO__CORRECTAMENTE).
                    cantidad(cant).build();

            return r;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            r = Resultados.builder().
                    id(-1).
                    mensaje(ERROR_AL__MODIFICAR__CLIENTE).
                    cantidad(-1).build();
            return r;
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
     * @param estado
     * @return
     */
    public synchronized static Resultados borrarCliente(int idCliente,
            boolean estado) {
        Resultados r;//Resultados obtenidos del metodos.

        final String DELETE = "EXECUTE PROCEDURE SP_DELETE_CLIENTE_SB (?, ?);";

        try (PreparedStatement ps = getCnn().prepareStatement(DELETE)) {
            
            ps.setInt(1, idCliente);
            ps.setBoolean(2, estado);

            int c = ps.executeUpdate();//Cantidad de registros afectados.

            r = Resultados.builder().
                    id(-1).
                    mensaje(CLIENTE_BORRADO_CORRECTAMENTE).
                    cantidad(c).build();

            return r;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            r = Resultados.builder().
                    id(-1).
                    mensaje(CLIENTE_NO_PUEDE_SER_BORRADO).
                    cantidad(-1).build();
            return r;
        }
    }

    /**
     * Metodo utilizado para llevar los comboBox del componente clientes.
     * 
     * Nota: este metodo no deberia devolver un resultset.
     * 
     * @return 
     */
    public synchronized static ResultSet getClientesCombo() {
        final String GET_CLIENTES_ESTADO_SB
                = "SELECT r.ID, r.CEDULA, r.ESTADO FROM GET_CLIENTES_ESTADO_SB r";

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
     * La vista GET_CLIENTES_ESTADO_SB contiene los campos necesario para hacer
     * la validaciones de los clientes. 
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
        LOG.log(Level.INFO, "Cedula del cliente recibida: {0}", cedula);
        
        final String GET_ID_CLIENTE_BY_CEDULA
                = "SELECT COALESCE(ID, -1) AS ID "
                + "FROM GET_CLIENTES_ESTADO_SB "
                + "WHERE TRIM(CEDULA) LIKE TRIM(?);";

        try (PreparedStatement ps = getCnn().prepareStatement(
                GET_ID_CLIENTE_BY_CEDULA,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

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
    public static synchronized DefaultTableModel getClientesTablaSB() {
        String titulos[] = {"Cedulas", "Persona", "Primer Nombre", "Segundo Nombre",
            "Apellidos", "Sexo", "Fecha nacimiento", "Fecha Ingreso", "Estado"};

        final String GET_CLIENTES_SB
                = "SELECT ID, CEDULA, PERSONA, PNOMBRE, SNOMBRE, APELLIDOS, "
                + "     SEXO, FECHA_NACIMIENTO, FECHA_INGRESO, ESTADO "
                + "FROM GET_CLIENTES_SB";

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
     * Consulta corta con solo 4 campos de la vista de GET_CLIENTES_SB,
     *
     * Es un metodo que rellena los comboBox de los clientes del sistema.
     *
     * Solo agrega el identificador, nombres y apellidos.
     *
     * @param cmbCliente
     */
    public static synchronized void getClientesTablaSBCombo(JComboBox<Clientes> cmbCliente) {
        final String GET_CLIENTES_SB_COMBO
                = "SELECT r.ID, r.PNOMBRE, r.SNOMBRE, r.APELLIDOS FROM GET_CLIENTES_SB r";

        cmbCliente.removeAllItems();
        cmbCliente.removeAll();

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

            try (ResultSet rs = ps.executeQuery();) {
                rs.next();

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
