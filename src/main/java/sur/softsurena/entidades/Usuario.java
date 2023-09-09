package sur.softsurena.entidades;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static sur.softsurena.conexion.Conexion.getCnn;

@Getter
@SuperBuilder
public class Usuario extends Personas {
    
    /*
        Tenemos una vista y un procedimiento que muestran casi lo mismo... 
        
        La lista V_USUARIOS no se está utilizando.... y es casi igual que el
        procedimiento SP_SELECT_USUARIOS_TAGS, este se utiliza en 3 linea de 
        codigo. 
    */

    private static final Logger LOG = Logger.getLogger(Usuario.class.getName());
    private static final String USUARIO_BORRADO_CORRECTAMENTE = "Usuario borrado correctamente.";
    private static final String ERROR_AL_BORRAR_USUARIO = "Error al borrar usuario.";

    private final String clave;
    private final String descripcion;
    private final Boolean administrador;
    private final List<Roles> roles;
    private final List<Etiquetas> etiquetas;

    /**
     * Metodo que permite el cambio de contraseña de un usuario en el sistema.
     *
     * Este metodo evita que un usuario le cambie la contraseña a otro usuario.
     *
     * Metodo revisado el 24 de abril 2022. Metodo actualizado el 19 05 2022,
     * Nota, se cambia la posicion del parametro, primero usuario y luego la
     * clave.
     *
     * @param clave Es la clave del usuario actual que permite la actualizacion
     * de su clave.
     */
    public synchronized static boolean cambioClave(String clave) {
        final String CAMBIAR_CLAVE
                = "ALTER USER CURRENT_USER PASSWORD ?";

        try (PreparedStatement ps = getCnn().prepareStatement(CAMBIAR_CLAVE)) {
            ps.setString(1, clave);

            return ps.execute();

        } catch (SQLException ex) {
            //Instalar Logger
            return false;
        }
    }

    /**
     * Metodo que permite que los usuarios del sistema actualicen la contraseña
     * de otro usuario de otro sistema.
     *
     * @param userName
     * @param clave
     * @return
     */
    public synchronized static boolean cambioClaveUsuario(String userName, String clave) {
        final String CAMBIAR_CLAVE_USER_NAME
                = "ALTER USER ? PASSWORD ?";

        try (PreparedStatement ps = getCnn().prepareStatement(CAMBIAR_CLAVE_USER_NAME);) {
            ps.setString(1, userName);
            ps.setString(2, clave);

            return ps.execute();

        } catch (SQLException ex) {
            //Instalar Logger
            return false;
        }
    }

    /**
     * Para matener la mejor integridad de los datos los usuario se desactivan
     * del sistema.
     *
     * Metodo revisado 24 abril 2022.
     *
     * @param loginName
     * @return
     */
    public synchronized static String borrarUsuario(String loginName) {
        
        final String sql = "EXECUTE PROCEDURE SP_DELETE_USUARIO (?);";
        
        try (PreparedStatement ps = getCnn().prepareStatement(sql)) {
            ps.setString(1, loginName);
            ps.executeUpdate();
            return USUARIO_BORRADO_CORRECTAMENTE;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ERROR_AL_BORRAR_USUARIO, ex);
            if(ex.getMessage().contains("E_CAJERO_TURNO_ACTIVO")){
                JOptionPane.showMessageDialog(
                        null, 
                        "Cajero cuenta con un turno activo.", 
                        "Error...", 
                        JOptionPane.ERROR_MESSAGE
                );
            }
            return ERROR_AL_BORRAR_USUARIO;
        }
    }
    
    /**
     * Este metodo debe ser investigado pues no fue documentado.
     *
     * @param idUsuario Identificador unico de los usuarios del sistema.
     * @return
     */
    public synchronized static String getCreadorUsuario(String idUsuario) {
        try (PreparedStatement ps = getCnn().prepareStatement(
                "SELECT creador "
                + "FROM get_creador "
                + "WHERE TRIM(usuario) like ?;")) {

            ps.setString(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("creador");
                } else {
                    return null;
                }
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
     * Nitido. Metodo para consultar cual es el usuario actual del sistema.
     *
     * Una vez iniciada la session del usuario en el sistema, hacemos una
     * consulta a la base de datos, que nos devuelve el usuario y el rol de
     * este.
     *
     * Metodo actualizado el 17/05/2022.
     *
     * Metodo actualizado el 24 oct 2022:
     *
     * Nota:Se cambió el tipo de resultado que devuelve de un ResultSet a una
     * clase Usuarios.
     *
     * @return Retorna un String con el dato de cual es usuario del sistema que
     * ha iniciado sessión actualmente.
     */
    public synchronized static Usuario getUsuarioActual() {
        final String sql
                = "SELECT TRIM(CURRENT_USER) USUARIO, TRIM(CURRENT_ROLE) ROLE "
                + "FROM RDB$DATABASE";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            try (ResultSet rs = ps.executeQuery();) {
                rs.next();
                return Usuario.builder().
                        user_name(rs.getString("USUARIO")).
                        rol(rs.getString("ROLE")).
                        build();
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
     * Metodo para llamar a los usuarios del sistema, este ejecuta un
     * procedimiento almacenado que realizar el SELECT complejo en la BD.
     *
     * Actualizado 09 Julio 2022, se agregar el campo estatico de la clase
     * Usuario el cual contiene el SQL de la consulta de este metodo.
     *
     * Consulta que nos permite tener el nombre de usuario, primer nombre,
     * segundo nombre, apellidos, estado del usuario, si es administrador y una
     * breve descripcion del usuario que se haya registrado.
     * 
     * @param userName Es el identificador que utiliza el usuario para iniciar
     * session en el sistema, este campo tambien puede recibir un string con el
     * valor all para obtener todos los usuarios del sistema.
     *
     * @return retorna un conjunto de valores dependiendo del valor pasado por
     * parametro, si se le pasa all recibi un conjunto de datos de todos los
     * usuarios del sistema.
     */
    public synchronized static Usuario getUsuario(String userName) {
        final String GET_USER_BY_USER_NAME
            = "SELECT TRIM(p.O_USER_NAME) AS O_USER_NAME, "
            + "TRIM(p.O_PRIMER_NOMBRE) AS O_PRIMER_NOMBRE, "
            + "TRIM(p.O_SEGUNDO_NOMBRE) AS O_SEGUNDO_NOMBRE, "
            + "TRIM(p.O_APELLIDOS) AS O_APELLIDOS, "
            + "p.O_ESTADO_ACTIVO, p.O_ADMINISTRADOR, p.O_DESCRIPCION "
            + "FROM SP_SELECT_USUARIOS_TAGS (?) p;";
        
        try (PreparedStatement ps = getCnn().prepareStatement(
                GET_USER_BY_USER_NAME,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            ps.setString(1, userName);
            try (ResultSet rs = ps.executeQuery();) {
                rs.next();
                return Usuario.builder().
                        user_name(rs.getString("O_USER_NAME")).
                        pnombre(rs.getString("O_PRIMER_NOMBRE")).
                        snombre(rs.getString("O_SEGUNDO_NOMBRE")).
                        apellidos(rs.getString("O_APELLIDOS")).
                        estado(rs.getBoolean("O_ESTADO_ACTIVO")).
                        administrador(rs.getBoolean("O_ADMINISTRADOR")).
                        descripcion(rs.getString("O_DESCRIPCION")).build();
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
     * Se esta utilizando para llenar la tabla de usuarios.
     *
     * Este Query es utilizado para obtener Inf. de los usuarios del sistema.
     * Actualizado: 15 Sep 2022.
     *
     * @return
     */
    public synchronized static List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        Usuario u;

        final String sql
                = "SELECT TRIM(u.SEC$USER_NAME) AS USER_NAME, "
                + "     TRIM(u.SEC$FIRST_NAME) AS PNOMBRE, "
                + "     TRIM(u.SEC$MIDDLE_NAME) AS SNOMBRE, "
                + "     TRIM(u.SEC$LAST_NAME) AS APELLIDOS, "
                + "     u.SEC$ACTIVE AS ESTADO, "
                + "     u.SEC$ADMIN AS ADMINISTRADOR, "
                + "     TRIM(u.SEC$DESCRIPTION) AS DESCRIPCION "
                + "FROM SEC$USERS u";

        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    u = Usuario.builder().
                            pnombre((rs.getString("PNOMBRE") == null ? "" : rs.getString("PNOMBRE"))).
                            snombre((rs.getString("SNOMBRE") == null ? "" : rs.getString("SNOMBRE"))).
                            apellidos((rs.getString("APELLIDOS") == null ? "" : rs.getString("APELLIDOS"))).
                            administrador(rs.getBoolean("ADMINISTRADOR")).
                            estado(rs.getBoolean("ESTADO")).
                            user_name(rs.getString("USER_NAME")).
                            descripcion(rs.getString("DESCRIPCION")).build();
                    usuarios.add(u);
                }
            }
            return usuarios;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Metodo utilizado para obtener todos los nombres de usuarios del sistema.
     * En primera instacia se utilizada para consultar la base de datos y 
     * obtener los roles de este. 
     * 
     * @return Retorna una lista de usuarios del sistema.
     */
    public synchronized static List<Usuario> getNombresUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        Usuario u;
        final String sql = "SELECT USERNAME FROM V_USUARIOS";
        try (PreparedStatement ps = getCnn().prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT)) {

            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    u = Usuario.builder().
                            user_name(rs.getString("USERNAME")).build();
                    usuarios.add(u);
                }
            } 
            return usuarios;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
    
    /**
     * Metodo utilizado para modificar los usuarios del sistema con el rol
     * doctor, el cual permite agregar al registro su Exequatur y Especialidad.Metodo actualizado el 19 05 2022.
     *
     *
     * @param u Un objeto de la case Usuario.
     * @param sql
     * @return Devuelve un mensaje que indica si la actualizacion fue exitosa.
     */
    public static synchronized String agregarModificarUsuario(Usuario u, boolean sql) {
        /**
         * Query que permite agregar usuarios al sistema.
         */
        final String INSERT
                = "EXECUTE PROCEDURE SP_INSERT_USUARIO(?,?,?,?,?,?,?,?)";

        /**
         * Procedimiento que se encarga de actualizar a los usuarios del
         * sistema.
         */
        final String UPDATE
                = "EXECUTE PROCEDURE SP_UPDATE_USUARIO(?,?,?,?,?,?,?,?)";
        
        try (CallableStatement cs = getCnn().prepareCall(sql ? INSERT: UPDATE)) {
            cs.setString(1, u.getUser_name());
            cs.setString(2, u.getClave());
            cs.setString(3, u.getPnombre());
            cs.setString(4, u.getSnombre());
            cs.setString(5, u.getApellidos());
            cs.setBoolean(6, u.getEstado());
            cs.setBoolean(7, u.getAdministrador());
            cs.setString(8, u.getDescripcion());

            cs.executeUpdate();

            return "Usuario Agregado o Modificado Correctamente.";
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return "Error al Modificar Usuario...";
        }
    }

    /**
     * Metodo que permite verificar si existe un usuario en el sistema, esto con
     * el objetivo de permitir a un usuario ser registrado en la base de datos.
     *
     * Query que me permite comprobar si un usuario existe en el sistema.
     *
     * @param userName Identificador unico de un usuario para ser utilizado como
     * parte de login de inicio en el sistema.
     *
     * Actualizado el dia 09 julio 2022, Nota: se agrega el campo de la clase
     * Usuario, para que tome el SQL de la consulta.
     *
     * @return retorna un valor booleano que nos permite saber si existe "TRUE"
     * o no "FALSE" un usuario en el sistema.
     */
    public synchronized static boolean existeUsuarioByUserName(String userName) {

        final String EXISTE_USUARIO_BY_USER_NAME
                = "SELECT DISTINCT(1) "
                + "FROM SP_SELECT_USUARIOS_TAGS ('all') p "
                + "WHERE TRIM(p.O_USER_NAME) STARTING WITH TRIM(UPPER(?))";
        try (PreparedStatement ps = getCnn().prepareStatement(EXISTE_USUARIO_BY_USER_NAME)) {
            ps.setString(1, userName);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                return false;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public String toString() {
        return super.getUser_name();
    }

}
