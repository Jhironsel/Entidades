package sur.softsurena.metodos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import static sur.softsurena.conexion.Conexion.getCnn;
import sur.softsurena.entidades.Cliente;
import sur.softsurena.entidades.ContactoEmail;
import sur.softsurena.entidades.ContactoTel;
import sur.softsurena.entidades.Direccion;
import sur.softsurena.entidades.Generales;
import static sur.softsurena.metodos.M_ContactoEmail.agregarContactosEmail;
import static sur.softsurena.metodos.M_ContactoTel.agregarContactosTel;
import static sur.softsurena.metodos.M_Direccion.agregarDireccion;
import sur.softsurena.utilidades.FiltroBusqueda;
import sur.softsurena.utilidades.Resultado;
import static sur.softsurena.utilidades.Utilidades.LOG;

public class M_Cliente {

    /**
     * Metodos utilizado para agregar los clientes en el sistema, el cual es
     * utilizado para agregar los contactos de este.
     *
     * @param cliente Es el objecto de la clase cliente que contiene los metodos
     * necesario para obtener los datos del cliente.
     *
     * @return devuelve el resultado de la operacion a realizar, la cual este
     * objecto puede mostrar el identificador del cliente, el mensaje de la
     * operacion y la cantidad de registros afectados.
     */
    public synchronized static Resultado agregarCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "Objecto cliente nulo.");
        final String sql
                = """
                  SELECT V_ID 
                  FROM SP_I_CLIENTE_SB(?,?,?,?,?,?,?,?,?)
                  """;

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT
        )) {

            ps.setString(1, String.valueOf(cliente.getPersona()));
            ps.setString(2, cliente.getGenerales().getCedula());
            ps.setString(3, cliente.getPnombre());
            ps.setString(4, cliente.getSnombre());
            ps.setString(5, cliente.getApellidos());
            ps.setString(6, String.valueOf(cliente.getSexo()));
            ps.setDate(7, cliente.getFecha_nacimiento());
            ps.setBoolean(8, cliente.getEstado());
            ps.setString(9, String.valueOf(cliente.getGenerales().getEstado_civil()));

            ResultSet rs = ps.executeQuery();

            rs.next();

            int id_persona = rs.getInt(1);

            cliente.getContactosTel().stream().forEach(telefono -> {
                agregarContactosTel(
                        ContactoTel
                                .builder()
                                .id_persona(id_persona)
                                .telefono(telefono.getTelefono())
                                .tipo(telefono.getTipo())
                                .por_defecto(telefono.getPor_defecto())
                                .build()
                );
            });

            cliente.getContactosEmail().stream().forEach(contactoEmail -> {
                agregarContactosEmail(
                        ContactoEmail
                                .builder()
                                .id_persona(id_persona)
                                .email(contactoEmail.getEmail())
                                .por_defecto(contactoEmail.getPor_defecto())
                                .build()
                );
            });

            cliente.getDirecciones().stream().forEach(direccion -> {
                agregarDireccion(
                        Direccion
                                .builder()
                                .id_persona(id_persona)
                                .provincia(direccion.getProvincia())
                                .municipio(direccion.getMunicipio())
                                .distrito_municipal(direccion.getDistrito_municipal())
                                .direccion(direccion.getDireccion())
                                .por_defecto(direccion.getPor_defecto())
                                .build()
                );
            });

            return Resultado
                    .builder()
                    .id(id_persona)
                    .mensaje(CLIENTE__AGREGADO__CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL_INSERTAR__CLIENTE,
                    ex
            );
            return Resultado
                    .builder()
                    .id(-1)
                    .mensaje(ERROR_AL_INSERTAR__CLIENTE)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String ERROR_AL_INSERTAR__CLIENTE
            = "Error al insertar Cliente al sistema.";
    public static final String CLIENTE__AGREGADO__CORRECTAMENTE
            = "Cliente Agregado Correctamente";

    /**
     * Permite agregar un cliente ya registrado en la tabla de personas a
     * persona cliente.
     *
     * @param id
     * @return
     */
    public synchronized static Resultado agregarClienteById(int id) {
        final String sql
                = "EXECUTE PROCEDURE SP_I_CLIENTE_ID(?)";
        try (CallableStatement cs = getCnn().prepareCall(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            cs.setInt(1, id);
            
            cs.execute();
            
            return Resultado
                    .builder()
                    .mensaje(CLIENTE__AGREGADO__CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE, 
                    ERROR_AL_INSERTAR__CLIENTE, 
                    ex
            );
            return Resultado
                    .builder()
                    .estado(Boolean.TRUE)
                    .mensaje(ERROR_AL_INSERTAR__CLIENTE)
                    .icono(JOptionPane.ERROR_MESSAGE)
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
    public synchronized static Resultado modificarCliente(Cliente cliente) {
        final String sql
                = "EXECUTE PROCEDURE SP_U_CLIENTE_SB(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, cliente.getId_persona());
            ps.setString(2, String.valueOf(cliente.getPersona()));
            ps.setString(3, cliente.getGenerales().getCedula());
            ps.setString(4, cliente.getPnombre());
            ps.setString(5, cliente.getSnombre());
            ps.setString(6, cliente.getApellidos());
            ps.setString(7, String.valueOf(cliente.getSexo()));
            ps.setDate(8, cliente.getFecha_nacimiento());
            ps.setBoolean(9, cliente.getEstado());
            ps.setString(10, String.valueOf(
                    cliente.getGenerales().getEstado_civil()
            ));

            ps.executeUpdate();
            return Resultado
                    .builder()
                    .mensaje(CLIENTE__MODIFICADO__CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    ERROR_AL__MODIFICAR__CLIENTE,
                    ex
            );
            return Resultado
                    .builder()
                    .mensaje(ERROR_AL__MODIFICAR__CLIENTE)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String CLIENTE__MODIFICADO__CORRECTAMENTE
            = "Cliente modificado correctamente.";
    public static final String ERROR_AL__MODIFICAR__CLIENTE
            = "Error al modificar cliente.";

    /**
     * Metodo utilizado para presentar los datos en la tabla del formulario
     * clientes.
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
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.HOLD_CURSORS_OVER_COMMIT)) {

            //Parametros para el identificador
            ps.setInt(1, (Objects.isNull(filtro.getId()) ? -1 : (int) filtro.getId()));

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
    public synchronized static Resultado borrarCliente(int idCliente) {
        final String sql = "EXECUTE PROCEDURE SP_D_CLIENTE_SB(?);";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT
        )) {
            ps.setInt(1, idCliente);

            ps.executeUpdate();

            return Resultado
                    .builder()
                    .mensaje(CLIENTE_BORRADO_CORRECTAMENTE)
                    .icono(JOptionPane.INFORMATION_MESSAGE)
                    .estado(Boolean.TRUE)
                    .build();
        } catch (SQLException ex) {
            LOG.log(
                    Level.SEVERE,
                    CLIENTE_NO_PUEDE_SER_BORRADO,
                    ex
            );
            return Resultado
                    .builder()
                    .id(-1)
                    .mensaje(CLIENTE_NO_PUEDE_SER_BORRADO)
                    .icono(JOptionPane.ERROR_MESSAGE)
                    .estado(Boolean.FALSE)
                    .build();
        }
    }
    public static final String CLIENTE_BORRADO_CORRECTAMENTE
            = "Cliente borrado correctamente.";
    public static final String CLIENTE_NO_PUEDE_SER_BORRADO
            = "Cliente no puede ser borrado.";

}
